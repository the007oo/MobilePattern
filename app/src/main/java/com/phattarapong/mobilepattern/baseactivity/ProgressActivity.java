package com.phattarapong.mobilepattern.baseactivity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.activity.FeedBackActivity;

/**
 * Created by Phattarapong on 9/15/2017.
 */

public abstract class ProgressActivity extends BaseActivity {

    private View loadErrorLayout;
    private View loadingLayout;
    private TextView errorLabel;
    protected Button retryButton;

    @Override
    protected void initWidget() {
        super.initWidget();

        loadErrorLayout = findViewById(R.id.loadErrorLayout);
        loadingLayout = findViewById(R.id.loadingLayout);

        errorLabel = (TextView) findViewById(R.id.errorLabel);
        retryButton = (Button) findViewById(R.id.retryButton);

    }


    protected void alertDialog(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ProgressActivity.this);
        builder.setMessage(message);
        TextView titleDialog = new TextView(this);
        titleDialog.setText(title);
        titleDialog.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        titleDialog.setPadding(10, 10, 10, 10);
        titleDialog.setGravity(Gravity.CENTER);
        titleDialog.setTextColor(ContextCompat.getColor(this, R.color.white));
        titleDialog.setTextSize(getResources().getDimension(R.dimen.default_text_body_normal));
        builder.setCustomTitle(titleDialog);
        builder.setCancelable(false);

        builder.setPositiveButton(getString(R.string.action_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }


    protected void showContentLayout() {
        loadErrorLayout.setVisibility(View.GONE);
        loadingLayout.setVisibility(View.GONE);
    }

    protected void showLoadingLayout() {
        loadErrorLayout.setVisibility(View.GONE);
        loadingLayout.setVisibility(View.VISIBLE);
    }

    protected void showErrorLayout(String s) {
        loadErrorLayout.setVisibility(View.GONE);
        loadErrorLayout.setVisibility(View.VISIBLE);
        errorLabel.setText(s);
    }

}
