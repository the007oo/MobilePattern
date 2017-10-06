package com.phattarapong.mobilepattern.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.ToolBarWebViewActivity;

public class HelpActivity extends ToolBarWebViewActivity {
    private WebView webView;
    private final String URL = "https://www.unlockmen.com";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar_WebView);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initWidget();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && url.startsWith("http://")) {
                    view.getContext().startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                } else {
                    return false;
                }
            }
        });
        webView.loadUrl(URL);

        setTitleToolBar(getString(R.string.title_activity_help));
        setUrlToolBar(URL.toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.share_image_view) {
            shareUrl(URL);
        } else if (view.getId() == R.id.previous_image_view) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                onBackPressed();
            }
        } else if (view.getId() == R.id.next_image_view) {
            webView.goForward();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
