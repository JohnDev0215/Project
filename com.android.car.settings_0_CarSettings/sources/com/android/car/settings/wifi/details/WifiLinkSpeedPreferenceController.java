package com.android.car.settings.wifi.details;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class WifiLinkSpeedPreferenceController extends WifiDetailsBasePreferenceController<WifiDetailsPreference> {
    public WifiLinkSpeedPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<WifiDetailsPreference> getPreferenceType() {
        return WifiDetailsPreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(WifiDetailsPreference preference) {
        int linkSpeedMbps = getWifiInfoProvider().getWifiInfo().getLinkSpeed();
        preference.setVisible(linkSpeedMbps >= 0);
        preference.setDetailText(getContext().getString(R.string.link_speed, Integer.valueOf(getWifiInfoProvider().getWifiInfo().getLinkSpeed())));
    }
}
