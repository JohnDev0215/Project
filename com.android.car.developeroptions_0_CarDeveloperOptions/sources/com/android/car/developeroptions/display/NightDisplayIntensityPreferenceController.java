package com.android.car.developeroptions.display;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.display.ColorDisplayManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.SliderPreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.SeekBarPreference;

/* JADX INFO: loaded from: classes.dex */
public class NightDisplayIntensityPreferenceController extends SliderPreferenceController {
    private ColorDisplayManager mColorDisplayManager;

    @Override // com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public NightDisplayIntensityPreferenceController(Context context, String str) {
        super(context, str);
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (ColorDisplayManager.isNightDisplayAvailable(this.mContext)) {
            return !this.mColorDisplayManager.isNightDisplayActivated() ? 5 : 0;
        }
        return 3;
    }

    @Override // com.android.car.developeroptions.core.SliderPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), "night_display_temperature");
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        SeekBarPreference seekBarPreference = (SeekBarPreference) preferenceScreen.findPreference(getPreferenceKey());
        seekBarPreference.setContinuousUpdates(true);
        seekBarPreference.setMax(getMaxSteps());
    }

    @Override // com.android.car.developeroptions.core.SliderPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public final void updateState(Preference preference) {
        super.updateState(preference);
        preference.setEnabled(this.mColorDisplayManager.isNightDisplayActivated());
    }

    @Override // com.android.car.developeroptions.core.SliderPreferenceController
    public int getSliderPosition() {
        return convertTemperature(this.mColorDisplayManager.getNightDisplayColorTemperature());
    }

    @Override // com.android.car.developeroptions.core.SliderPreferenceController
    public boolean setSliderPosition(int i) {
        return this.mColorDisplayManager.setNightDisplayColorTemperature(convertTemperature(i));
    }

    @Override // com.android.car.developeroptions.core.SliderPreferenceController
    public int getMaxSteps() {
        return convertTemperature(ColorDisplayManager.getMinimumColorTemperature(this.mContext));
    }

    private int convertTemperature(int i) {
        return ColorDisplayManager.getMaximumColorTemperature(this.mContext) - i;
    }
}
