package com.ecarx.xui.adaptapi.wifiap.impl;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import com.ecarx.xui.adaptapi.wifiap.IWifiAPHost;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class WifiAPHostImpl implements IWifiAPHost {
    private static final String TAG = "WifiAPHostImpl";
    private WifiConfiguration _wifiConfig;
    private WifiManager _wifiManager;
    private Context mContext;

    @Override // com.ecarx.xui.adaptapi.wifiap.IWifiAPHost
    public boolean registerWifiAPFrequencyCallBack(IWifiAPHost.IWifiAPFrequencyChangeCallBack iWifiAPFrequencyChangeCallBack) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.IWifiAPHost
    public boolean unregisterWifiAPFrequencyCallBack(IWifiAPHost.IWifiAPFrequencyChangeCallBack iWifiAPFrequencyChangeCallBack) {
        return false;
    }

    public WifiAPHostImpl(Context context) {
        this.mContext = context;
        LogTool.d(TAG, TAG);
        WifiManager wifiManager = (WifiManager) this.mContext.getApplicationContext().getSystemService("wifi");
        this._wifiManager = wifiManager;
        this._wifiConfig = wifiManager.getWifiApConfiguration();
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.IWifiAPHost
    public int getCurrentFrequencyMode() {
        LogTool.d(TAG, "getCurrentFrequencyMode " + this._wifiConfig.channel);
        int i = this._wifiConfig.channel;
        return (i > 13 || i < 1) ? 2 : 1;
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.IWifiAPHost
    public void setFrequencyMode(int i) {
        LogTool.d(TAG, "setFrequencyMode: " + i);
        if (i == 1) {
            this._wifiConfig.channel = 6;
        } else {
            this._wifiConfig.channel = 149;
        }
    }
}
