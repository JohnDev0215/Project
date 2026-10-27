package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiManager;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class WifiTetherAutoOffPreferenceController extends PreferenceController<TwoStatePreference> {
    private final WifiManager mWifiManager;

    public WifiTetherAutoOffPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<TwoStatePreference> getPreferenceType() {
        return TwoStatePreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(TwoStatePreference preference) {
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        boolean settingsOn = softApConfiguration.isAutoShutdownEnabled();
        preference.setChecked(settingsOn);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(TwoStatePreference preference, Object newValue) {
        boolean settingsOn = ((Boolean) newValue).booleanValue();
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        SoftApConfiguration newSoftApConfiguration = new SoftApConfiguration.Builder(softApConfiguration).setAutoShutdownEnabled(settingsOn).build();
        return this.mWifiManager.setSoftApConfiguration(newSoftApConfiguration);
    }
}
