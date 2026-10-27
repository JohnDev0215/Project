package com.android.car.developeroptions.core;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.SeekBarPreference;

/* JADX INFO: loaded from: classes.dex */
public abstract class SliderPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
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

    public abstract int getMaxSteps();

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getSliceType() {
        return 2;
    }

    public abstract int getSliderPosition();

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

    public abstract boolean setSliderPosition(int i);

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public SliderPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        return setSliderPosition(((Integer) obj).intValue());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (preference instanceof SeekBarPreference) {
            ((SeekBarPreference) preference).setProgress(getSliderPosition());
        } else if (preference instanceof androidx.preference.SeekBarPreference) {
            ((androidx.preference.SeekBarPreference) preference).setValue(getSliderPosition());
        }
    }
}
