package com.android.car.settings.wifi.details;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.LinkAddress;
import android.net.LinkProperties;
import androidx.core.text.BidiFormatter;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import java.net.Inet6Address;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes5.dex */
public class WifiIpv6AddressPreferenceController extends WifiDetailsBasePreferenceController<Preference> {
    public WifiIpv6AddressPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        LinkProperties linkProperties = getWifiInfoProvider().getLinkProperties();
        StringJoiner ipv6Addresses = new StringJoiner(System.lineSeparator());
        if (linkProperties != null) {
            for (LinkAddress addr : linkProperties.getLinkAddresses()) {
                if (addr.getAddress() instanceof Inet6Address) {
                    ipv6Addresses.add(addr.getAddress().getHostAddress());
                }
            }
        }
        if (ipv6Addresses.length() > 0) {
            preference.setSummary(BidiFormatter.getInstance().unicodeWrap(ipv6Addresses.toString()));
            preference.setVisible(true);
        } else {
            preference.setVisible(false);
        }
    }
}
