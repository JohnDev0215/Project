package com.android.car.settings.wifi.details;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.LinkProperties;
import android.net.RouteInfo;
import com.android.car.settings.common.FragmentController;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public class WifiGatewayPreferenceController extends WifiDetailsBasePreferenceController<WifiDetailsPreference> {
    public WifiGatewayPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
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
        String gateway = null;
        if (linkProperties != null) {
            Iterator<RouteInfo> it = linkProperties.getRoutes().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                RouteInfo routeInfo = it.next();
                if (routeInfo.isIPv4Default() && routeInfo.hasGateway()) {
                    gateway = routeInfo.getGateway().getHostAddress();
                    break;
                }
            }
        }
        if (gateway == null) {
            preference.setVisible(false);
        } else {
            preference.setDetailText(gateway);
            preference.setVisible(true);
        }
    }
}
