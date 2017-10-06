package com.phattarapong.mobilepattern.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.phattarapong.mobilepattern.BuildConfig;
import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.ToolBarSimpleActivity;

public class VersionActivity extends ToolBarSimpleActivity {

    private TextView versionLable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        versionLable = (TextView) findViewById(R.id.version_lable);
        versionLable.setText("เวอร์ชั่น : " + BuildConfig.VERSION_NAME);

        initWidget();
        setTitleToolBar("เวอร์ชั่น");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
