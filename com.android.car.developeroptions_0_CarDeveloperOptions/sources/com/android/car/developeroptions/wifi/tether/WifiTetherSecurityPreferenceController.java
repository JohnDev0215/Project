package com.android.car.developeroptions.wifi.tether;

import android.content.Context;
import android.net.wifi.SoftApConfiguration;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.wifi.tether.WifiTetherBasePreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class WifiTetherSecurityPreferenceController extends WifiTetherBasePreferenceController {
    private final String[] mSecurityEntries;
    private int mSecurityValue;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "wifi_tether_security";
    }

    public WifiTetherSecurityPreferenceController(Context context, WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        super(context, onTetherConfigUpdateListener);
        this.mSecurityEntries = this.mContext.getResources().getStringArray(R.array.wifi_tether_security);
    }

    @Override // com.android.car.developeroptions.wifi.tether.WifiTetherBasePreferenceController
    public void updateDisplay() {
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        if (softApConfiguration != null && softApConfiguration.getSecurityType() == 0) {
            this.mSecurityValue = 0;
        } else {
            this.mSecurityValue = 1;
        }
        ListPreference listPreference = (ListPreference) this.mPreference;
        listPreference.setSummary(getSummaryForSecurityType(this.mSecurityValue));
        listPreference.setValue(String.valueOf(this.mSecurityValue));
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        int i = Integer.parseInt((String) obj);
        this.mSecurityValue = i;
        preference.setSummary(getSummaryForSecurityType(i));
        this.mListener.onTetherConfigUpdated();
        return true;
    }

    public int getSecurityType() {
        return this.mSecurityValue;
    }

    private String getSummaryForSecurityType(int i) {
        if (i == 0) {
            return this.mSecurityEntries[1];
        }
        return this.mSecurityEntries[0];
    }
}
