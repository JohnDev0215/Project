package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class WifiStatusPreferenceController extends WifiBasePreferenceController<Preference> {
    public WifiStatusPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [androidx.preference.Preference] */
    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.preference.Preference] */
    /* JADX WARN: Type inference failed for: r0v3, types: [androidx.preference.Preference] */
    /* JADX WARN: Type inference failed for: r0v4, types: [androidx.preference.Preference] */
    /* JADX WARN: Type inference failed for: r0v5, types: [androidx.preference.Preference] */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.preference.Preference] */
    /* JADX WARN: Type inference failed for: r0v7, types: [androidx.preference.Preference] */
    /* JADX WARN: Type inference failed for: r0v8, types: [androidx.preference.Preference] */
    /* JADX WARN: Type inference failed for: r0v9, types: [androidx.preference.Preference] */
    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        int wifiState = getCarWifiManager().getWifiState();
        if (wifiState == 1) {
            getPreference().setVisible(true);
            getPreference().setTitle(R.string.wifi_disabled);
            getPreference().setSelectable(false);
            getPreference().setIcon(R.drawable.ic_settings_about);
            return;
        }
        if (wifiState == 2) {
            getPreference().setVisible(true);
            getPreference().setTitle(R.string.loading_wifi_list);
            getPreference().setSelectable(true);
            getPreference().setIcon(null);
            return;
        }
        getPreference().setVisible(false);
    }

    @Override // com.android.car.settings.wifi.CarWifiManager.Listener
    public void onWifiStateChanged(int state) {
        refreshUi();
    }
}
