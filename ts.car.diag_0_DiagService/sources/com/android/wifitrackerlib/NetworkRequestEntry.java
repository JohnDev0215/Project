package com.android.wifitrackerlib;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.android.internal.annotations.VisibleForTesting;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@VisibleForTesting
public class NetworkRequestEntry extends StandardWifiEntry {
    NetworkRequestEntry(@NonNull Context context, @NonNull Handler handler, @NonNull String str, @NonNull WifiManager wifiManager, @NonNull WifiNetworkScoreCache wifiNetworkScoreCache, boolean z) {
        super(context, handler, str, wifiManager, wifiNetworkScoreCache, z);
    }

    @NonNull
    static String i0(@NonNull WifiConfiguration wifiConfiguration) {
        androidx.core.d.e.c(wifiConfiguration, "Cannot create key with null config!");
        androidx.core.d.e.c(wifiConfiguration.SSID, "Cannot create key with null SSID in config!");
        return "NetworkRequestEntry:" + WifiInfo.sanitizeSsid(wifiConfiguration.SSID) + "," + e0.l(wifiConfiguration);
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public boolean A() {
        return false;
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public boolean a() {
        return false;
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public boolean b() {
        return false;
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public int l() {
        return 0;
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public WifiConfiguration u() {
        return null;
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public boolean w() {
        return true;
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public boolean x() {
        return false;
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public boolean y() {
        return false;
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public boolean z() {
        return false;
    }
}
