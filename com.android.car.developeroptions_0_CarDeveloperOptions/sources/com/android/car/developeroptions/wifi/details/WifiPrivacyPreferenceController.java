package com.android.car.developeroptions.wifi.details;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class WifiPrivacyPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private static final String KEY_WIFI_PRIVACY = "privacy";
    private static final int PREF_RANDOMIZATION_NONE = 1;
    private static final int PREF_RANDOMIZATION_PERSISTENT = 0;
    private boolean mIsEphemeral;
    private boolean mIsPasspoint;
    private WifiConfiguration mWifiConfiguration;
    private WifiManager mWifiManager;

    public static int translateMacRandomizedValueToPrefValue(int i) {
        return i == 1 ? 0 : 1;
    }

    public static int translatePrefValueToMacRandomizedValue(int i) {
        return i == 0 ? 1 : 0;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public WifiPrivacyPreferenceController(Context context) {
        super(context, KEY_WIFI_PRIVACY);
        this.mIsEphemeral = false;
        this.mIsPasspoint = false;
        this.mWifiConfiguration = null;
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
    }

    public void setWifiConfiguration(WifiConfiguration wifiConfiguration) {
        this.mWifiConfiguration = wifiConfiguration;
    }

    public void setIsEphemeral(boolean z) {
        this.mIsEphemeral = z;
    }

    public void setIsPasspoint(boolean z) {
        this.mIsPasspoint = z;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mWifiManager.isConnectedMacRandomizationSupported() ? 0 : 2;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        DropDownPreference dropDownPreference = (DropDownPreference) preference;
        int randomizationValue = getRandomizationValue();
        dropDownPreference.setValue(Integer.toString(randomizationValue));
        updateSummary(dropDownPreference, randomizationValue);
        if (this.mIsEphemeral || this.mIsPasspoint) {
            preference.setSelectable(false);
            dropDownPreference.setSummary(R.string.wifi_privacy_settings_ephemeral_summary);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        WifiConfiguration wifiConfiguration = this.mWifiConfiguration;
        if (wifiConfiguration != null) {
            wifiConfiguration.macRandomizationSetting = Integer.parseInt((String) obj);
            this.mWifiManager.updateNetwork(this.mWifiConfiguration);
            this.mWifiManager.disconnect();
        }
        updateSummary((DropDownPreference) preference, Integer.parseInt((String) obj));
        return true;
    }

    int getRandomizationValue() {
        WifiConfiguration wifiConfiguration = this.mWifiConfiguration;
        if (wifiConfiguration != null) {
            return wifiConfiguration.macRandomizationSetting;
        }
        return 1;
    }

    private void updateSummary(DropDownPreference dropDownPreference, int i) {
        dropDownPreference.setSummary(dropDownPreference.getEntries()[translateMacRandomizedValueToPrefValue(i)]);
    }
}
