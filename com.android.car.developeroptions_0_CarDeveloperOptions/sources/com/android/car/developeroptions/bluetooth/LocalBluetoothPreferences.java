package com.android.car.developeroptions.bluetooth;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
final class LocalBluetoothPreferences {
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("bluetooth_settings", 0);
    }

    static void persistSelectedDeviceInPicker(Context context, String str) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(context).edit();
        editorEdit.putString("last_selected_device", str);
        editorEdit.putLong("last_selected_device_time", System.currentTimeMillis());
        editorEdit.apply();
    }
}
