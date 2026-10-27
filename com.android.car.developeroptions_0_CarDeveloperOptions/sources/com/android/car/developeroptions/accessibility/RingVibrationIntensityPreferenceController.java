package com.android.car.developeroptions.accessibility;

import android.content.Context;
import android.content.IntentFilter;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class RingVibrationIntensityPreferenceController extends VibrationIntensityPreferenceController {
    static final String PREF_KEY = "ring_vibration_preference_screen";

    @Override // com.android.car.developeroptions.accessibility.VibrationIntensityPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationIntensityPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationIntensityPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationIntensityPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationIntensityPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationIntensityPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationIntensityPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public RingVibrationIntensityPreferenceController(Context context) {
        super(context, PREF_KEY, "ring_vibration_intensity", "vibrate_when_ringing");
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationIntensityPreferenceController
    protected int getDefaultIntensity() {
        return this.mVibrator.getDefaultRingVibrationIntensity();
    }
}
