package com.amazon.minerva.client.thirdparty.configuration;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class DenyListConfiguration {
    private static final String TAG = "DenyListConfiguration";
    private String mDenyListBits;

    public DenyListConfiguration(String str, int i) {
        if (isValidInput(str, i)) {
            this.mDenyListBits = str;
        }
    }

    private boolean isValidInput(String str, int i) {
        if (str != null && str.length() == i) {
            return true;
        }
        Log.e(TAG, String.format("Invalid denyListBits: %s.", str));
        return false;
    }
}
