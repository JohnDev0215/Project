package com.android.settingslib.deviceinfo;

import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractWifiMacAddressPreferenceController extends AbstractConnectivityPreferenceController {

    @VisibleForTesting
    static final String KEY_WIFI_MAC_ADDRESS = "wifi_mac_address";

    @VisibleForTesting
    static final int OFF = 0;

    @VisibleForTesting
    static final int ON = 1;
    private static final String[] e = {"android.net.conn.CONNECTIVITY_CHANGE", "android.net.wifi.LINK_CONFIGURATION_CHANGED", "android.net.wifi.STATE_CHANGE"};

    @Override // com.android.settingslib.deviceinfo.AbstractConnectivityPreferenceController
    protected String[] n() {
        return e;
    }
}
