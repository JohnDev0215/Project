package com.android.car.developeroptions.location;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class LocationScanningPreferenceController extends BasePreferenceController {
    static final String KEY_LOCATION_SCANNING = "location_scanning";
    private final Context mContext;
    private final WifiManager mWifiManager;

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

    public LocationScanningPreferenceController(Context context) {
        super(context, KEY_LOCATION_SCANNING);
        this.mContext = context;
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        boolean zIsScanAlwaysAvailable = this.mWifiManager.isScanAlwaysAvailable();
        boolean z = Settings.Global.getInt(this.mContext.getContentResolver(), "ble_scan_always_enabled", 0) == 1;
        return this.mContext.getString((zIsScanAlwaysAvailable && z) ? R.string.scanning_status_text_wifi_on_ble_on : (!zIsScanAlwaysAvailable || z) ? (zIsScanAlwaysAvailable || !z) ? R.string.scanning_status_text_wifi_off_ble_off : R.string.scanning_status_text_wifi_off_ble_on : R.string.scanning_status_text_wifi_on_ble_off);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_location_scanning) ? 0 : 3;
    }
}
