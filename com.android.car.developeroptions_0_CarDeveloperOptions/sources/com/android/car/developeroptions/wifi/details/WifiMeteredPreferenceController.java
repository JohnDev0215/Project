package com.android.car.developeroptions.wifi.details;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class WifiMeteredPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private static final String KEY_WIFI_METERED = "metered";
    private WifiConfiguration mWifiConfiguration;
    private WifiManager mWifiManager;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
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

    public WifiMeteredPreferenceController(Context context, WifiConfiguration wifiConfiguration) {
        super(context, KEY_WIFI_METERED);
        this.mWifiConfiguration = wifiConfiguration;
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        DropDownPreference dropDownPreference = (DropDownPreference) preference;
        int meteredOverride = getMeteredOverride();
        dropDownPreference.setValue(Integer.toString(meteredOverride));
        updateSummary(dropDownPreference, meteredOverride);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        WifiConfiguration wifiConfiguration = this.mWifiConfiguration;
        if (wifiConfiguration != null) {
            wifiConfiguration.meteredOverride = Integer.parseInt((String) obj);
        }
        this.mWifiManager.updateNetwork(this.mWifiConfiguration);
        BackupManager.dataChanged("com.android.providers.settings");
        updateSummary((DropDownPreference) preference, getMeteredOverride());
        return true;
    }

    int getMeteredOverride() {
        WifiConfiguration wifiConfiguration = this.mWifiConfiguration;
        if (wifiConfiguration != null) {
            return wifiConfiguration.meteredOverride;
        }
        return 0;
    }

    private void updateSummary(DropDownPreference dropDownPreference, int i) {
        dropDownPreference.setSummary(dropDownPreference.getEntries()[i]);
    }
}
