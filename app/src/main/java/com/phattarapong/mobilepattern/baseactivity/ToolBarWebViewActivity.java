package com.phattarapong.mobilepattern.baseactivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phattarapong.mobilepattern.R;


public abstract class ToolBarWebViewActivity extends ProgressActivity implements View.OnClickListener {

    private ImageView previousImageView;
    private ImageView nextImageView;
    private ImageView closeImageView;
    private ImageView shareImageView;
    private TextView titleLabel;
    private TextView urlLabel;

    @Override
    protected void initWidget() {
        super.initWidget();

        previousImageView = (ImageView) findViewById(R.id.previousImageView);
        nextImageView = (ImageView) findViewById(R.id.nextImageView);
        closeImageView = (ImageView) findViewById(R.id.closeImageView);
        shareImageView = (ImageView) findViewById(R.id.shareImageView);
        titleLabel = (TextView) findViewById(R.id.titleLabel);
        urlLabel = (TextView) findViewById(R.id.urlLabel);

        closeImageView.setOnClickListener(this);
        shareImageView.setOnClickListener(this);
        previousImageView.setOnClickListener(this);
        nextImageView.setOnClickListener(this);

    }
    protected void setTitleToolBar(String text) {
        titleLabel.setText(text);
    }

    protected void setUrlToolBar(String text) {
        urlLabel.setText(text);
    }

    protected void shareUrl(String text) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/*");
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.closeImageView) {
            finish();
        }
    }
}
