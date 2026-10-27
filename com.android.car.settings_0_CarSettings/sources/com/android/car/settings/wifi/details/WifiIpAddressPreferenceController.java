package com.android.car.settings.wifi.details;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.LinkAddress;
import android.net.LinkProperties;
import com.android.car.settings.common.FragmentController;
import java.net.Inet4Address;

/* JADX INFO: loaded from: classes5.dex */
public class WifiIpAddressPreferenceController extends WifiDetailsBasePreferenceController<WifiDetailsPreference> {
    public WifiIpAddressPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<WifiDetailsPreference> getPreferenceType() {
        return WifiDetailsPreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(WifiDetailsPreference preference) {
        LinkProperties linkProperties = getWifiInfoProvider().getLinkProperties();
        String ipv4Address = null;
        if (linkProperties != null) {
            for (LinkAddress addr : linkProperties.getLinkAddresses()) {
                if (addr.getAddress() instanceof Inet4Address) {
                    ipv4Address = addr.getAddress().getHostAddress();
                }
            }
        }
        if (ipv4Address == null) {
            preference.setVisible(false);
        } else {
            preference.setDetailText(ipv4Address);
            preference.setVisible(true);
        }
    }
}
