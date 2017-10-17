package com.phattarapong.mobilepattern.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.phattarapong.mobilepattern.R;
import com.phattarapong.mobilepattern.baseactivity.ToolBarNotificationActivity;
import com.phattarapong.mobilepattern.view.DialogiOS;

public class NotificationActivity extends ToolBarNotificationActivity {

    private Switch allowSwitch, newSwitch, promotionsSwitch, orderSwitch;
    private LinearLayout subNotificationLayout;

    private String statusAllow = "false";
    private String statusNewsAllow = "false";
    private String statusPromotionAllow = "false";
    private String statusOrderAllow = "false";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initWidget();
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        setTitleToolBar(getResources().getString(R.string.title_activity_notifications));

        allowSwitch = (Switch) findViewById(R.id.allowSwitch);
        newSwitch = (Switch) findViewById(R.id.newSwitch);
        promotionsSwitch = (Switch) findViewById(R.id.promotionsSwitch);
        orderSwitch = (Switch) findViewById(R.id.orderSwitch);
        subNotificationLayout = (LinearLayout) findViewById(R.id.subNotificationLayout);

        checkNotification();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    private void checkNotification() {
        statusAllow = settingPreference.getValueString(settingPreference.NOTIFICATION);

        if (statusAllow.equals("true")) {
            allowSwitch.setChecked(true);
            subNotificationLayout.setVisibility(View.VISIBLE);
        } else {
            allowSwitch.setChecked(false);
            subNotificationLayout.setVisibility(View.GONE);
        }

        allowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    settingPreference.setValueString(settingPreference.NOTIFICATION, "true");
                    //dialogRequestNotificationAndroid();

                    DialogiOS.show(NotificationActivity.this, getString(R.string.dialog_notification_desc),
                            getString(R.string.dialog_notification_title), true, new DialogiOS.OnNegativeListener() {
                                @Override
                                public void onNegative() {
                                    allowSwitch.setChecked(false);
                                    statusAllow = "false";
                                    subNotificationLayout.setVisibility(View.GONE);
                                }
                            }, new DialogiOS.OnPositiveListener() {
                                @Override
                                public void onPositive() {
                                    statusAllow = "true";
                                    newSwitch.setChecked(true);
                                    promotionsSwitch.setChecked(true);
                                    orderSwitch.setChecked(true);
                                    subNotificationLayout.setVisibility(View.VISIBLE);
                                }
                            });

                } else {
                    settingPreference.setValueString(settingPreference.NOTIFICATION, "false");
                    subNotificationLayout.setVisibility(View.GONE);
                    return;
                }
            }
        });

        checkNotificationNews();
        checkNotificationPromotions();
        checkNotificationOrder();
    }

    private void checkNotificationOrder() {
        statusOrderAllow = settingPreference.getValueString(settingPreference.NOTIFICATION_ORDER);
        if (statusOrderAllow.equals("true")) {
            orderSwitch.setChecked(true);
        } else {
            orderSwitch.setChecked(false);
        }
        orderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    settingPreference.setValueString(settingPreference.NOTIFICATION_ORDER, "true");
                } else {
                    settingPreference.setValueString(settingPreference.NOTIFICATION_ORDER, "false");
                    return;
                }
            }
        });
    }

    private void checkNotificationPromotions() {
        statusPromotionAllow = settingPreference.getValueString(settingPreference.NOTIFICATION_PROMOTION);
        if (statusPromotionAllow.equals("true")) {
            promotionsSwitch.setChecked(true);
        } else {
            promotionsSwitch.setChecked(false);
        }
        promotionsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    settingPreference.setValueString(settingPreference.NOTIFICATION_PROMOTION, "true");
                } else {
                    settingPreference.setValueString(settingPreference.NOTIFICATION_PROMOTION, "false");
                    return;
                }
            }
        });
    }

    private void checkNotificationNews() {
        statusNewsAllow = settingPreference.getValueString(settingPreference.NOTIFICATION_NEWS);
        if (statusNewsAllow.equals("true")) {
            newSwitch.setChecked(true);
        } else {
            newSwitch.setChecked(false);
        }

        newSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    settingPreference.setValueString(settingPreference.NOTIFICATION_NEWS, "true");
                } else {
                    settingPreference.setValueString(settingPreference.NOTIFICATION_NEWS, "false");
                    return;
                }
            }
        });
    }

    private void dialogRequestNotificationAndroid() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(NotificationActivity.this);
        builder.setTitle(getString(R.string.dialog_notification_title));
        builder.setMessage(getString(R.string.dialog_notification_desc));
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.action_allow), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                statusAllow = "true";
                newSwitch.setChecked(true);
                promotionsSwitch.setChecked(true);
                orderSwitch.setChecked(true);
                subNotificationLayout.setVisibility(View.VISIBLE);
            }
        });
        builder.setNegativeButton(getString(R.string.action_deny), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                allowSwitch.setChecked(false);
                statusAllow = "false";
                subNotificationLayout.setVisibility(View.GONE);
            }
        });
        builder.show();
    }

}
