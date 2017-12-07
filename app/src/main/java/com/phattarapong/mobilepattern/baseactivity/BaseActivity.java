package com.phattarapong.mobilepattern.baseactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.config.MemberPreference;
import com.phattarapong.mobilepattern.config.SettingPreference;
import com.phattarapong.mobilepattern.manager.Contextor;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Phattarapong on 9/15/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private String[] permission = {};
    protected static final int MY_PERMISSIONS_REQUEST_READ = 1123;
    private Context context = null;

    private ViewGroup viewGroup = null;
    private OnRequestPermission onRequestPermission = null;

    protected SettingPreference settingPreference;
    protected MemberPreference memberPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CSPraKas.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ:
                if (grantResults.length > 0) {
                    ArrayList<Integer> allGrant = new ArrayList<>();
                    ArrayList<Integer> ask = new ArrayList<>();

                    for (int grantResult : grantResults) {
                        allGrant.add(grantResult);
                    }

                    if (allGrant.contains(PackageManager.PERMISSION_GRANTED) && !allGrant.contains(PackageManager.PERMISSION_DENIED)) {
                        if (onRequestPermission != null) {
                            onRequestPermission.onGranted();
                        }
                    } else {
                        if (onRequestPermission != null) {
                            onRequestPermission.onDenied();
                        }

                        for (String permission : permissions) {
                            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
                                    && !shouldShowRequestPermissionRationale(permission)) {

                                ask.add(PackageManager.PERMISSION_DENIED);
                            }
                        }

                        if (ask.size() != 0 && ask.contains(PackageManager.PERMISSION_DENIED)) {
                            Snackbar snackbar = Snackbar.make(viewGroup, "กรุณาอนุมัติ Permission", Snackbar.LENGTH_INDEFINITE);
                            snackbar.setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            });
                            snackbar.show();
                        }
                    }
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    protected void initWidget() {
        settingPreference = SettingPreference.getInstance(Contextor.getInstance().getContext());
        memberPreference = MemberPreference.getInstance(Contextor.getInstance().getContext());
    }

    protected void startActivityNoAnimation(Class<?> aClass) {
        this.startActivityNoAnimation(aClass, null);
    }

    protected void startActivityNoAnimation(Class<?> aClass, Bundle bundle) {
        if (!getClass().equals(aClass)) {
            Intent intent = new Intent(this, aClass);
            if (bundle != null)
                intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void setOnSelectPermission(OnRequestPermission onSelectPermission) {
        this.onRequestPermission = onSelectPermission;
    }

    protected void requestMultiplePermission(Context context, ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        this.context = context;
        try {
            permission = getPermissions(context);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        // Creating String Array with Permissions.
        if (permission != null) {
            ActivityCompat.requestPermissions((Activity) context, permission, MY_PERMISSIONS_REQUEST_READ);
        }
    }

    public static String[] getPermissions(Context context)
            throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(
                context.getPackageName(), PackageManager.GET_PERMISSIONS);

        return info.requestedPermissions;
    }

}
