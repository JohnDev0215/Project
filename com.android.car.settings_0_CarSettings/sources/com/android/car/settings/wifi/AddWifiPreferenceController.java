package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class AddWifiPreferenceController extends WifiBasePreferenceController<Preference> {
    public AddWifiPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.preference.Preference] */
    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.preference.Preference] */
    @Override // com.android.car.settings.wifi.CarWifiManager.Listener
    public void onWifiStateChanged(int state) {
        if (state != 1) {
            getPreference().setVisible(true);
        } else {
            getPreference().setVisible(false);
        }
    }
}
