package android.ext.services.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Telephony;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class SmsHelper {
    private static String ACTION_DEFAULT_SMS_PACKAGE_CHANGED_INTERNAL = "android.provider.action.DEFAULT_SMS_PACKAGE_CHANGED_INTERNAL";
    private BroadcastReceiver mBroadcastReceiver;
    private final Context mContext;
    private String mDefaultSmsPackage;

    SmsHelper(Context context) {
        this.mContext = context.getApplicationContext();
    }

    void initialize() {
        if (this.mBroadcastReceiver == null) {
            this.mDefaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(this.mContext);
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: android.ext.services.notification.SmsHelper.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (SmsHelper.ACTION_DEFAULT_SMS_PACKAGE_CHANGED_INTERNAL.equals(intent.getAction())) {
                        SmsHelper smsHelper = SmsHelper.this;
                        smsHelper.mDefaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(smsHelper.mContext);
                    } else {
                        Log.w("SmsHelper", "Unknown broadcast received: " + intent.getAction());
                    }
                }
            };
            this.mBroadcastReceiver = broadcastReceiver;
            this.mContext.registerReceiver(broadcastReceiver, new IntentFilter(ACTION_DEFAULT_SMS_PACKAGE_CHANGED_INTERNAL));
        }
    }

    void destroy() {
        BroadcastReceiver broadcastReceiver = this.mBroadcastReceiver;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
            this.mBroadcastReceiver = null;
        }
    }

    public String getDefaultSmsPackage() {
        return this.mDefaultSmsPackage;
    }
}
