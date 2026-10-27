package com.android.car.settings.wifi.details;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;

/* JADX INFO: loaded from: classes5.dex */
public class WifiFrequencyPreferenceController extends WifiDetailsBasePreferenceController<WifiDetailsPreference> {
    private static final Logger LOG = new Logger((Class<?>) WifiFrequencyPreferenceController.class);

    public WifiFrequencyPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<WifiDetailsPreference> getPreferenceType() {
        return WifiDetailsPreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(WifiDetailsPreference preference) {
        int frequency = getWifiInfoProvider().getWifiInfo().getFrequency();
        String band = null;
        if (frequency >= 2400 && frequency < 2500) {
            band = getContext().getResources().getString(R.string.wifi_band_24ghz);
        } else if (frequency >= 4900 && frequency < 5900) {
            band = getContext().getResources().getString(R.string.wifi_band_5ghz);
        } else {
            LOG.e("Unexpected frequency " + frequency);
        }
        preference.setDetailText(band);
    }
}
