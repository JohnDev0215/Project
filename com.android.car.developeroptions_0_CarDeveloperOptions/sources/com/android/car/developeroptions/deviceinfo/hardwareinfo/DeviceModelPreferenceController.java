package com.android.car.developeroptions.deviceinfo.hardwareinfo;

import android.content.Context;
import android.content.IntentFilter;
import com.android.car.developeroptions.deviceinfo.HardwareInfoPreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class DeviceModelPreferenceController extends HardwareInfoPreferenceController {
    @Override // com.android.car.developeroptions.deviceinfo.HardwareInfoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.deviceinfo.HardwareInfoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.deviceinfo.HardwareInfoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.deviceinfo.HardwareInfoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.deviceinfo.HardwareInfoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.deviceinfo.HardwareInfoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return true;
    }

    @Override // com.android.car.developeroptions.deviceinfo.HardwareInfoPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean useDynamicSliceSummary() {
        return true;
    }

    public DeviceModelPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.deviceinfo.HardwareInfoPreferenceController, com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        int availabilityStatus = super.getAvailabilityStatus();
        if (availabilityStatus == 1) {
            return 0;
        }
        return availabilityStatus;
    }

    @Override // com.android.car.developeroptions.deviceinfo.HardwareInfoPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return HardwareInfoPreferenceController.getDeviceModel();
    }
}
