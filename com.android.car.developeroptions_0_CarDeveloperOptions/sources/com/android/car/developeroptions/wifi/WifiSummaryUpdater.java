package com.android.car.developeroptions.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkScoreManager;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.SummaryUpdater;
import com.android.settingslib.wifi.WifiStatusTracker;

/* JADX INFO: loaded from: classes.dex */
public final class WifiSummaryUpdater extends SummaryUpdater {
    private static final IntentFilter INTENT_FILTER;
    private final BroadcastReceiver mReceiver;
    private final WifiStatusTracker mWifiTracker;

    static {
        IntentFilter intentFilter = new IntentFilter();
        INTENT_FILTER = intentFilter;
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        INTENT_FILTER.addAction("android.net.wifi.STATE_CHANGE");
        INTENT_FILTER.addAction("android.net.wifi.RSSI_CHANGED");
    }

    public WifiSummaryUpdater(Context context, SummaryUpdater.OnSummaryChangeListener onSummaryChangeListener) {
        this(context, onSummaryChangeListener, null);
    }

    public WifiSummaryUpdater(Context context, SummaryUpdater.OnSummaryChangeListener onSummaryChangeListener, WifiStatusTracker wifiStatusTracker) {
        super(context, onSummaryChangeListener);
        this.mWifiTracker = wifiStatusTracker == null ? new WifiStatusTracker(context, (WifiManager) context.getSystemService(WifiManager.class), (NetworkScoreManager) context.getSystemService(NetworkScoreManager.class), (ConnectivityManager) context.getSystemService(ConnectivityManager.class), new Runnable() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$WifiSummaryUpdater$AH3iJDV7TmPcui7tdjAH2KYnM4Q
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.notifyChangeIfNeeded();
            }
        }) : wifiStatusTracker;
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.wifi.WifiSummaryUpdater.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                WifiSummaryUpdater.this.mWifiTracker.handleBroadcast(intent);
                WifiSummaryUpdater.this.notifyChangeIfNeeded();
            }
        };
    }

    public void register(boolean z) {
        if (z) {
            notifyChangeIfNeeded();
            this.mContext.registerReceiver(this.mReceiver, INTENT_FILTER);
        } else {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
        this.mWifiTracker.setListening(z);
    }

    @Override // com.android.car.developeroptions.widget.SummaryUpdater
    public String getSummary() {
        WifiStatusTracker wifiStatusTracker = this.mWifiTracker;
        if (!wifiStatusTracker.enabled) {
            return this.mContext.getString(R.string.switch_off_text);
        }
        if (!wifiStatusTracker.connected) {
            return this.mContext.getString(R.string.disconnected);
        }
        String strSanitizeSsid = android.net.wifi.WifiInfo.sanitizeSsid(wifiStatusTracker.ssid);
        return TextUtils.isEmpty(this.mWifiTracker.statusLabel) ? strSanitizeSsid : this.mContext.getResources().getString(R.string.preference_summary_default_combination, strSanitizeSsid, this.mWifiTracker.statusLabel);
    }
}
