package com.ts.carplay.adapter.bluetooth;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothAdapter {
    void init(Context context);

    boolean setBluetoothAutoConnectEnabled(boolean z, String str, boolean z2);

    void setBtOobInfo(String str, String str2, String str3, int i);
}
