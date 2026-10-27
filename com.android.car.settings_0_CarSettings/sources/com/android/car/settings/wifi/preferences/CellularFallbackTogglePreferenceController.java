package com.android.car.settings.wifi.preferences;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class CellularFallbackTogglePreferenceController extends PreferenceController<TwoStatePreference> {
    private static final String DISABLED = null;
    private static final String ENABLED = "1";

    public CellularFallbackTogglePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<TwoStatePreference> getPreferenceType() {
        return TwoStatePreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        return avoidBadWifiConfigEnabled() ? 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(TwoStatePreference preference) {
        preference.setChecked(cellularFallbackEnabled());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(TwoStatePreference preference, Object newValue) {
        boolean enableCellularFallback = ((Boolean) newValue).booleanValue();
        Settings.Global.putString(getContext().getContentResolver(), "network_avoid_bad_wifi", enableCellularFallback ? ENABLED : DISABLED);
        return true;
    }

    private boolean avoidBadWifiConfigEnabled() {
        return getContext().getResources().getInteger(R.integer.config_networkAvoidBadWifi) == 1;
    }

    private boolean cellularFallbackEnabled() {
        return TextUtils.equals(ENABLED, Settings.Global.getString(getContext().getContentResolver(), "network_avoid_bad_wifi"));
    }
}
