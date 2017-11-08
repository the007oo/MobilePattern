package com.phattarapong.mobilepattern.manager.net;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Connection {

    private final String TAG = "Connection";
    private String url = null;
    private int delayed = 0;
    private List<Params> bodyParams = null;
    private List<Params> fileParams = null;
    private List<Params> headerParams = null;

    private String lineEnd = "\r\n";
    private String twoHyphens = "--";
    private String boundary = "*****";
    private int requestMethod = Request.Method.POST;

    private OnConnectionCallBackListener callbackListener = null;
    private OnConnectionUpdateListener updateListener = null;

    public Connection(String url) {
        this.url = url;
    }

    public void execute(final Context context) {
        if (delayed == 0) {
            if (fileParams == null) {
                doRequest(context);
            } else {
                doRequestWithFile(context);
            }
        } else {
            Handler handler = new Handler();
            Runnable action = new Runnable() {
                @Override
                public void run() {
                    doRequest(context);
                }
            };
            handler.postDelayed(action, delayed);
        }
    }

    private void doRequest(final Context context) {

        if (requestMethod == Request.Method.GET && bodyParams != null) {
            url += "?debug=0";
            int size = bodyParams.size();

            for (int i = 0; i < size; i++) {
                Params params1 = bodyParams.get(i);
                url += ("&" + params1.getKey() + "=" + params1.getValue());
            }
        }

        final StringRequest request = new StringRequest(requestMethod, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);
                if (callbackListener != null) {
                    callbackListener.onSuccess(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callbackListener != null) {
                    if (error != null) {
                        if (error.networkResponse == null) {
                            Log.d("CheckValue", "CheckValue 1 : " + error.getMessage());
                            callbackListener.onLostConnection(error);

                        } else if (error.networkResponse.statusCode == 404) {
                            callbackListener.onUnreachHost(error);
                        } else {
                            Log.d("CheckValue", "CheckValue 2 : " + error.getMessage());
                            callbackListener.onLostConnection(error);

                        }
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                if (requestMethod == Request.Method.POST) {
                    int size = bodyParams.size();
                    for (int i = 0; i < size; i++) {
                        Params params1 = bodyParams.get(i);
                        params.put(params1.getKey(), params1.getValue());
                    }
                }

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                if (headerParams != null) {
                    int size = headerParams.size();
                    for (int i = 0; i < size; i++) {
                        Params params1 = headerParams.get(i);
                        params.put(params1.getKey(), params1.getValue());
                    }
                }
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //  Request via queue

        ConnectionManager.getInstance(context).addToRequestQueue(request);
    }

    private void doRequestWithFile(final Context context) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);
                if (callbackListener != null) {
                    callbackListener.onSuccess(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callbackListener != null) {
                    if (error != null) {
                        if (error.networkResponse.statusCode == 404) {
                            callbackListener.onUnreachHost(error);
                        } else {
                            Log.d("CheckValue", "CheckValue 3 : " + error.networkResponse.statusCode);
                            callbackListener.onLostConnection(error);
                        }
                    }
                }
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                //  Create output stream
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                if (fileParams == null) {
                    if (bodyParams != null) {
                        try {
                            int size = bodyParams.size();
                            for (int i = 0; i < size; i++) {
                                Params params = bodyParams.get(i);
                                String key = params.getKey();
                                String value = params.getValue();

                                //  Define content data
                                dos.writeBytes(twoHyphens + boundary + lineEnd);
                                dos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd);
                                dos.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
                                dos.writeBytes("Content-Length: " + value.length() + lineEnd + lineEnd);
                                dos.writeBytes(value);
                                dos.writeBytes(lineEnd);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    //  Parameter - data
                    if (bodyParams != null) {
                        try {
                            int size = bodyParams.size();
                            for (int i = 0; i < size; i++) {
                                Params params = bodyParams.get(i);
                                String key = params.getKey();
                                String value = params.getValue();//URLEncoder.encode(params.getValue(), "UTF-8");

                                //  Define content data
                                dos.writeBytes(twoHyphens + boundary + lineEnd);
                                dos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd);
                                dos.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
                                dos.writeBytes("Content-Length: " + value.length() + lineEnd + lineEnd);
                                dos.writeBytes(value);
                                dos.writeBytes(lineEnd);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    //  Parameter - file
                    try {
                        int fileIndex = 0;
                        int size = fileParams.size();
                        for (int i = 0; i < size; i++) {
                            Params params = fileParams.get(i);
                            String key = params.getKey();
                            String value = params.getValue();

                            //  Check file exist
                            File file = new File(value);
                            if (file.exists()) {
                                String mimeType = null;
                                String extension = MimeTypeMap.getFileExtensionFromUrl(value);
                                if (extension != null) {
                                    mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                                }

                                //  Define content data
                                dos.writeBytes(twoHyphens + boundary + lineEnd);
                                dos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + file.getName() + "\"" + lineEnd);
                                dos.writeBytes("Content-Type: " + mimeType + lineEnd + lineEnd);

                                //  Create input stream
                                BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
                                long fileLength = file.length();
                                int bufferSize = 1024;
                                byte data[] = new byte[bufferSize];
                                int count = input.read(data);
                                long total = 0;
                                while (count >= 0) {
                                    total += count;
                                    dos.write(data, 0, count);
                                    if (fileLength > 0) {
                                        publishProgress(fileIndex, (int) (total * 100 / fileLength));
                                    }
                                    count = input.read(data);
                                }
                                input.close();

                                dos.writeBytes(lineEnd);
                                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                            }
                            fileIndex++;
                        }

                        dos.flush();
                        dos.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                return baos.toByteArray();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                if (headerParams == null) {
//                    if (fileParams == null) {
//                        params.put("Content-Type", "application/x-www-form-urlencoded");
//                    } else {
                    params.put("Content-Type", "multipart/form-data;boundary=" + boundary);
//                    }
                } else {
                    int size = headerParams.size();
                    for (int i = 0; i < size; i++) {
                        Params params1 = headerParams.get(i);
                        params.put(params1.getKey(), params1.getValue());
                    }
                }
                return params;
            }
        };

        //  Request via queue
        ConnectionManager.getInstance(context).addToRequestQueue(request);
    }

    private void publishProgress(Integer... progress) {
        Log.d(TAG, "Progress " + progress[1]);
        if (updateListener != null) {
            updateListener.onUpdate(progress);
        }
    }

    public void addPostData(String name, String value) {
        if (bodyParams == null) {
            bodyParams = new ArrayList<>();
        }
        Params params = new Params(name, value);
        bodyParams.add(params);
    }

    public void addFileData(String name, String value) {
        if (fileParams == null) {
            fileParams = new ArrayList<>();
        }
        Params params = new Params(name, value);
        fileParams.add(params);
    }

    public void addHeaderData(String name, String value) {
        if (headerParams == null) {
            headerParams = new ArrayList<>();
        }
        Params params = new Params(name, value);
        headerParams.add(params);
    }

    public void setDelayed(int delayed) {
        this.delayed = delayed;
    }

    public void setOnConnectionCallBackListener(OnConnectionCallBackListener listener) {
        callbackListener = listener;
    }

    public void setOnConnectionUpdateListener(OnConnectionUpdateListener listener) {
        updateListener = listener;
    }

    public void setRequestMethod(int requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * Connection listener
     */

    public interface OnConnectionCallBackListener {
        public void onSuccess(String result);

        public void onLostConnection(VolleyError volleyError);

        public void onUnreachHost(VolleyError volleyError);
    }

    public interface OnConnectionUpdateListener {
        public void onUpdate(Integer... progress);
    }

    public class Params {
        private String key;
        private String value;

        public Params(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

}
