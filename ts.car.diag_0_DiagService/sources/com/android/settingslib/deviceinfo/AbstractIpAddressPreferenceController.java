package com.android.settingslib.deviceinfo;

import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractIpAddressPreferenceController extends AbstractConnectivityPreferenceController {

    @VisibleForTesting
    static final String KEY_IP_ADDRESS = "wifi_ip_address";
    private static final String[] e = {"android.net.conn.CONNECTIVITY_CHANGE", "android.net.wifi.LINK_CONFIGURATION_CHANGED", "android.net.wifi.STATE_CHANGE"};

    @Override // com.android.settingslib.deviceinfo.AbstractConnectivityPreferenceController
    protected String[] n() {
        return e;
    }
}
