package com.phattarapong.mobilepattern.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.phattarapong.mobilepattern.R;

/**
 * Created by Phattarapong on 10/6/2017.
 */

public class DialogiOS {

    public static void show(Context context, String msg, int resourceTitle
            , boolean cancelable, final OnNegativeListener onNegativeListener, final OnPositiveListener onPositiveListener) {
        show(context, msg, context.getString(resourceTitle), cancelable, onNegativeListener, onPositiveListener);
    }

    public static void show(Context context, int resourceMsg, String title
            , boolean cancelable, final OnNegativeListener onNegativeListener, final OnPositiveListener onPositiveListener) {
        show(context, context.getString(resourceMsg), title, cancelable, onNegativeListener, onPositiveListener);
    }

    public static void show(Context context, int resourceMsg, int resourceTitle
            , boolean cancelable, final OnNegativeListener onNegativeListener, final OnPositiveListener onPositiveListener) {
        show(context, context.getString(resourceMsg), context.getString(resourceTitle), cancelable, onNegativeListener, onPositiveListener);
    }

    public static void show(Context context, CharSequence message, CharSequence title
            , boolean cancelable, final OnNegativeListener onNegativeListener, final OnPositiveListener onPositiveListener) {

        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_request_notification);
        dialog.setCancelable(cancelable);

        TextView denyLabel =  dialog.findViewById(R.id.deny_label);
        TextView allowLabel =  dialog.findViewById(R.id.allow_label);

        denyLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (onNegativeListener != null) {
                    onNegativeListener.onNegative();
                }
            }
        });
        allowLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (onPositiveListener != null) {
                    onPositiveListener.onPositive();
                }
            }
        });

        TextView dialogTitleLabel = (TextView) dialog.findViewById(R.id.dialogTitleLabel);
        TextView dialogMsgLabel = (TextView) dialog.findViewById(R.id.dialogMsgLabel);
        dialogTitleLabel.setText(title);
        dialogMsgLabel.setText(message);

        dialog.show();
    }

    public interface OnNegativeListener {
        public void onNegative();
    }

    public interface OnPositiveListener {
        public void onPositive();
    }
}

