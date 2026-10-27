package com.android.car.developeroptions.wifi.tether;

import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.SoftApConfiguration;
import android.util.Log;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.wifi.tether.WifiTetherBasePreferenceController;

/* JADX INFO: loaded from: classes.dex */
public class WifiTetherApBandPreferenceController extends WifiTetherBasePreferenceController {
    private int mBand;
    private String[] mBandEntries;
    private String[] mBandSummaries;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "wifi_tether_network_ap_band";
    }

    public WifiTetherApBandPreferenceController(Context context, WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        super(context, onTetherConfigUpdateListener);
        updatePreferenceEntries();
    }

    @Override // com.android.car.developeroptions.wifi.tether.WifiTetherBasePreferenceController
    public void updateDisplay() {
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        if (softApConfiguration == null) {
            this.mBand = 1;
            Log.d("WifiTetherApBandPref", "Updating band to 2GHz because no config");
        } else if (is5GhzBandSupported()) {
            this.mBand = validateSelection(softApConfiguration.getBand());
            Log.d("WifiTetherApBandPref", "Updating band to " + this.mBand);
        } else {
            SoftApConfiguration softApConfigurationBuild = new SoftApConfiguration.Builder(softApConfiguration).setBand(1).build();
            this.mWifiManager.setSoftApConfiguration(softApConfigurationBuild);
            this.mBand = softApConfigurationBuild.getBand();
            Log.d("WifiTetherApBandPref", "5Ghz not supported, updating band to " + this.mBand);
        }
        ListPreference listPreference = (ListPreference) this.mPreference;
        listPreference.setEntries(this.mBandSummaries);
        listPreference.setEntryValues(this.mBandEntries);
        if (!is5GhzBandSupported()) {
            listPreference.setEnabled(false);
            listPreference.setSummary(R.string.wifi_ap_choose_2G);
        } else {
            listPreference.setValue(Integer.toString(softApConfiguration.getBand()));
            listPreference.setSummary(getConfigSummary());
        }
    }

    String getConfigSummary() {
        int i = this.mBand;
        if (i == 1) {
            return this.mBandSummaries[0];
        }
        if (i == 2) {
            return this.mBandSummaries[1];
        }
        if (i == 3) {
            return this.mContext.getString(R.string.wifi_ap_prefer_5G);
        }
        Log.e("WifiTetherApBandPref", "Unknown band: " + this.mBand);
        return this.mContext.getString(R.string.wifi_ap_prefer_5G);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mBand = validateSelection(Integer.parseInt((String) obj));
        Log.d("WifiTetherApBandPref", "Band preference changed, updating band to " + this.mBand);
        preference.setSummary(getConfigSummary());
        this.mListener.onTetherConfigUpdated();
        return true;
    }

    private int validateSelection(int i) {
        return 2 == i ? !is5GhzBandSupported() ? 1 : 3 : i;
    }

    void updatePreferenceEntries() {
        Resources resources = this.mContext.getResources();
        this.mBandEntries = resources.getStringArray(R.array.wifi_ap_band);
        this.mBandSummaries = resources.getStringArray(R.array.wifi_ap_band_summary);
    }

    private boolean is5GhzBandSupported() {
        return this.mWifiManager.is5GHzBandSupported() && this.mWifiManager.getCountryCode() != null;
    }

    public int getBand() {
        return this.mBand;
    }
}
