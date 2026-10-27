package com.android.car.settings.wifi.details;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.NetworkUtils;
import com.android.car.settings.common.FragmentController;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes5.dex */
public class WifiSubnetPreferenceController extends WifiDetailsBasePreferenceController<WifiDetailsPreference> {
    public WifiSubnetPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
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
        String subnet = null;
        if (linkProperties != null) {
            for (LinkAddress addr : linkProperties.getLinkAddresses()) {
                if (addr.getAddress() instanceof Inet4Address) {
                    subnet = ipv4PrefixLengthToSubnetMask(addr.getPrefixLength());
                }
            }
        }
        if (subnet == null) {
            preference.setVisible(false);
        } else {
            preference.setDetailText(subnet);
            preference.setVisible(true);
        }
    }

    private static String ipv4PrefixLengthToSubnetMask(int prefixLength) {
        try {
            InetAddress all = InetAddress.getByAddress(new byte[]{-1, -1, -1, -1});
            return NetworkUtils.getNetworkPart(all, prefixLength).getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }
    }
}
