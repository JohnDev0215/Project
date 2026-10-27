package com.android.settingslib.deviceinfo;

import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractBluetoothAddressPreferenceController extends AbstractConnectivityPreferenceController {

    @VisibleForTesting
    static final String KEY_BT_ADDRESS = "bt_address";
    private static final String[] e = {"android.bluetooth.adapter.action.STATE_CHANGED"};

    @Override // com.android.settingslib.deviceinfo.AbstractConnectivityPreferenceController
    protected String[] n() {
        return e;
    }
}
