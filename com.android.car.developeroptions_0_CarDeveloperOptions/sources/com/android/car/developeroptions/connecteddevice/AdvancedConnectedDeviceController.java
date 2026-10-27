package com.android.car.developeroptions.connecteddevice;

import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.nfc.NfcPreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class AdvancedConnectedDeviceController extends BasePreferenceController {
    private static final String DRIVING_MODE_SETTINGS_ENABLED = "gearhead:driving_mode_settings_enabled";

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

    public AdvancedConnectedDeviceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        Context context = this.mContext;
        return context.getText(getConnectedDevicesSummaryResourceId(context));
    }

    public static int getConnectedDevicesSummaryResourceId(Context context) {
        return getConnectedDevicesSummaryResourceId(new NfcPreferenceController(context, NfcPreferenceController.KEY_TOGGLE_NFC), isDrivingModeAvailable(context));
    }

    static boolean isDrivingModeAvailable(Context context) {
        return Settings.System.getInt(context.getContentResolver(), DRIVING_MODE_SETTINGS_ENABLED, 0) == 1;
    }

    static int getConnectedDevicesSummaryResourceId(NfcPreferenceController nfcPreferenceController, boolean z) {
        return nfcPreferenceController.isAvailable() ? z ? R.string.connected_devices_dashboard_summary : R.string.connected_devices_dashboard_no_driving_mode_summary : z ? R.string.connected_devices_dashboard_no_nfc_summary : R.string.connected_devices_dashboard_no_driving_mode_no_nfc_summary;
    }
}
