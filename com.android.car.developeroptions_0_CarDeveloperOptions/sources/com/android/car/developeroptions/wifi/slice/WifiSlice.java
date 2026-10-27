package com.android.car.developeroptions.wifi.slice;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import com.android.car.developeroptions.slices.CustomSliceable;

/* JADX INFO: loaded from: classes.dex */
public class WifiSlice implements CustomSliceable {
    static final int DEFAULT_EXPANDED_ROW_COUNT = 3;
    protected final Context mContext;
    protected final WifiManager mWifiManager;

    public WifiSlice(Context context) {
        this.mContext = context;
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
    }

    @Override // com.android.car.developeroptions.slices.CustomSliceable
    public void onNotifyChange(Intent intent) {
        this.mWifiManager.setWifiEnabled(intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", this.mWifiManager.isWifiEnabled()));
    }
}
