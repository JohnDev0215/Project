package com.android.car.developeroptions.deviceinfo;

import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.deviceinfo.AbstractWifiMacAddressPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class WifiMacAddressPreferenceController extends AbstractWifiMacAddressPreferenceController implements PreferenceControllerMixin {
    public WifiMacAddressPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, lifecycle);
    }

    @Override // com.android.settingslib.deviceinfo.AbstractWifiMacAddressPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_wifi_mac_address);
    }
}
