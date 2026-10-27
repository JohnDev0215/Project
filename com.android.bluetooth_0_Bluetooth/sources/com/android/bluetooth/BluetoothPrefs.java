package com.android.bluetooth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPrefs extends Activity {
    public static final String BLUETOOTH_SETTING_ACTION = "android.settings.BLUETOOTH_SETTINGS";
    public static final String BLUETOOTH_SETTING_CATEGORY = "android.intent.category.DEFAULT";

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent launchIntent = new Intent();
        launchIntent.setAction(BLUETOOTH_SETTING_ACTION);
        launchIntent.addCategory(BLUETOOTH_SETTING_CATEGORY);
        startActivity(launchIntent);
        finish();
    }
}
