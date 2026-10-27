package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.SoftApConfiguration;
import android.os.SystemProperties;
import android.util.Log;
import androidx.preference.ListPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class WifiTetherApBandPreferenceController extends WifiTetherBasePreferenceController<ListPreference> {
    private static final int SOFTAP_CONCURRENT_BAND_2GHZ_AND_5GHZ = 513;
    private static final String TAG = "CarWifiTetherApBandPref";
    private int mBand;
    private String[] mBandEntries;
    private String[] mBandSummaries;

    public WifiTetherApBandPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ListPreference> getPreferenceType() {
        return ListPreference.class;
    }

    private int getCarSoftApBand() {
        SoftApConfiguration config = getCarSoftApConfig();
        if (config == null) {
            return 1;
        }
        if (isDualApSupported() && isDualApConfigured(config.getBands())) {
            return 513;
        }
        if (!is5GhzBandSupported() && config.getBand() == 2) {
            SoftApConfiguration newConfig = new SoftApConfiguration.Builder(config).setBand(1).build();
            setCarSoftApConfig(newConfig);
            return newConfig.getBand();
        }
        return validateSelection(config.getBand());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController, com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        super.onCreateInternal();
        updatePreferenceEntries();
        this.mBand = getCarSoftApBand();
        ((ListPreference) getPreference()).setEntries(this.mBandSummaries);
        ((ListPreference) getPreference()).setEntryValues(this.mBandEntries);
        ((ListPreference) getPreference()).setValue(getBandEntry());
    }

    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController, com.android.car.settings.common.PreferenceController
    public void updateState(ListPreference preference) {
        super.updateState(preference);
        if (!is5GhzBandSupported()) {
            preference.setEnabled(false);
            preference.setSummary(R.string.wifi_ap_choose_2G);
        } else {
            preference.setValue(getBandEntry());
            preference.setSummary(getSummary());
        }
    }

    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController
    protected String getSummary() {
        if (!is5GhzBandSupported()) {
            return getContext().getString(R.string.wifi_ap_choose_2G);
        }
        int i = this.mBand;
        if (i == 1) {
            return this.mBandSummaries[0];
        }
        if (i == 2) {
            return this.mBandSummaries[1];
        }
        if (i == 3) {
            return getContext().getString(R.string.wifi_ap_prefer_5G);
        }
        if (i == 513 && isDualApSupported()) {
            return this.mBandSummaries[2];
        }
        Log.e(TAG, "Unknown band: " + this.mBand);
        return getContext().getString(R.string.wifi_ap_prefer_5G);
    }

    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController
    protected String getDefaultSummary() {
        return null;
    }

    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(ListPreference preference, Object newValue) {
        this.mBand = validateSelection(Integer.parseInt((String) newValue));
        updateApBand();
        refreshUi();
        return true;
    }

    private int validateSelection(int band) {
        if (2 == band && !is5GhzBandSupported()) {
            return 1;
        }
        return band;
    }

    private void updatePreferenceEntries() {
        Resources res = getContext().getResources();
        int entriesRes = R.array.wifi_ap_band;
        int summariesRes = R.array.wifi_ap_band_summary;
        this.mBandEntries = res.getStringArray(entriesRes);
        this.mBandSummaries = res.getStringArray(summariesRes);
        if (isDualApSupported()) {
            String[] strArr = this.mBandEntries;
            int length = strArr.length;
            String[] strArr2 = (String[]) Arrays.copyOf(strArr, length + 1);
            this.mBandEntries = strArr2;
            strArr2[length] = Integer.toString(513);
            String[] strArr3 = this.mBandSummaries;
            int length2 = strArr3.length;
            String[] strArr4 = (String[]) Arrays.copyOf(strArr3, length2 + 1);
            this.mBandSummaries = strArr4;
            strArr4[length2] = getContext().getString(R.string.wifi_ap_choose_both);
        }
    }

    private void updateApBand() {
        List<Integer> bands = new ArrayList<>();
        if (isDualApSupported() && this.mBand == 513) {
            bands.add(1);
            bands.add(2);
        }
        SoftApConfiguration config = new SoftApConfiguration.Builder(getCarSoftApConfig()).setBand(bands.size() <= 1 ? this.mBand : 1).setBands(bands).build();
        setCarSoftApConfig(config);
    }

    private String getBandEntry() {
        int i = this.mBand;
        if (i == 1) {
            return this.mBandEntries[0];
        }
        if (i == 2 || i == 3) {
            return this.mBandEntries[1];
        }
        if (i == 513 && isDualApSupported()) {
            return this.mBandEntries[2];
        }
        Log.e(TAG, "Unknown band: " + this.mBand + ", defaulting to 2GHz");
        return this.mBandEntries[0];
    }

    private boolean is5GhzBandSupported() {
        String countryCode = getCarWifiManager().getCountryCode();
        return getCarWifiManager().is5GhzBandSupported() && countryCode != null;
    }

    private boolean isDualApSupported() {
        return SystemProperties.getInt("wlan.vendor.multiap.disable", 0) == 0;
    }

    private boolean isDualApConfigured(List<Integer> bands) {
        if (bands == null || bands.size() != 2) {
            return false;
        }
        if (bands.get(1).intValue() == 1 && bands.get(0).intValue() == 2) {
            return true;
        }
        if (bands.get(1).intValue() != 2 || bands.get(0).intValue() != 1) {
            return false;
        }
        return true;
    }
}
