package com.amazon.music.push.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.push.citadel.CitadelClient;
import com.amazon.music.push.sonarpush.NotificationUtil;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationOpenHandlerActivity extends Activity {
    private static final String TAG = "NotificationOpenHandlerActivity";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("deeplink");
        String stringExtra2 = intent.getStringExtra(Splash.PARAMS_CAMPAIGN_ID);
        Log.i(TAG, "Push received with deeplink " + stringExtra);
        CitadelClient.getInstance().updateCustomerAction(this, "Push Clicked", "", stringExtra2, stringExtra);
        NotificationUtil.publishPushClickedMetrics(this, stringExtra2);
        super.onCreate(bundle);
        if (stringExtra != null) {
            openURL(stringExtra);
        } else {
            openApp();
        }
        finish();
    }

    private void openURL(String str) {
        Log.i(TAG, "Opening push deeplink");
        if (!str.startsWith("https://") && !str.startsWith("http://")) {
            str = "http://" + str;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.setFlags(268435456);
        startActivity(intent);
    }

    private void openApp() {
        Log.i(TAG, "Open app from push");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(270532608);
        startActivity(intent);
    }
}
