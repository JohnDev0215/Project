package com.android.car.developeroptions.deviceinfo.hardwareinfo;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.slices.Sliceable;

/* JADX INFO: loaded from: classes.dex */
public class SerialNumberPreferenceController extends BasePreferenceController {
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
    public boolean isCopyableSlice() {
        return true;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return true;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public boolean useDynamicSliceSummary() {
        return true;
    }

    public SerialNumberPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_device_model) ? 0 : 3;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public void copy() {
        Sliceable.setCopyContent(this.mContext, getSummary(), this.mContext.getText(R.string.status_serial_number));
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return Build.getSerial();
    }
}
