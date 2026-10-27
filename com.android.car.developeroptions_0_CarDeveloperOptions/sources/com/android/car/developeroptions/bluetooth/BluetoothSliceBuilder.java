package com.android.car.developeroptions.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothSliceBuilder {
    public static final IntentFilter INTENT_FILTER;

    static {
        IntentFilter intentFilter = new IntentFilter();
        INTENT_FILTER = intentFilter;
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        INTENT_FILTER.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
    }

    public static void handleUriChange(Context context, Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", false);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (booleanExtra) {
            defaultAdapter.enable();
        } else {
            defaultAdapter.disable();
        }
    }
}
