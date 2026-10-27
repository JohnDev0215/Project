package com.android.car.settings.wifi.details;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.LinkProperties;
import com.android.car.settings.common.FragmentController;
import java.net.InetAddress;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes5.dex */
public class WifiDnsPreferenceController extends WifiDetailsBasePreferenceController<WifiDetailsPreference> {
    public WifiDnsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
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
        String dnsServers = null;
        if (linkProperties != null) {
            dnsServers = (String) linkProperties.getDnsServers().stream().map(new Function() { // from class: com.android.car.settings.wifi.details.-$$Lambda$XZAGhHrbkIDyusER4MAM6luKcT0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((InetAddress) obj).getHostAddress();
                }
            }).collect(Collectors.joining(System.lineSeparator()));
        }
        if (dnsServers == null) {
            preference.setVisible(false);
        } else {
            preference.setDetailText(dnsServers);
            preference.setVisible(true);
        }
    }
}
