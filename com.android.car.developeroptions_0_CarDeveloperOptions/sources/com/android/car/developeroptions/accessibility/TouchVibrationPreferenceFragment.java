package com.android.car.developeroptions.accessibility;

import android.os.Vibrator;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class TouchVibrationPreferenceFragment extends VibrationPreferenceFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1294;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.accessibility_touch_vibration_settings;
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationPreferenceFragment
    protected int getPreviewVibrationAudioAttributesUsage() {
        return 13;
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationPreferenceFragment
    protected String getVibrationEnabledSetting() {
        return "haptic_feedback_enabled";
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationPreferenceFragment
    protected String getVibrationIntensitySetting() {
        return "haptic_feedback_intensity";
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationPreferenceFragment
    protected int getDefaultVibrationIntensity() {
        return ((Vibrator) getContext().getSystemService(Vibrator.class)).getDefaultHapticFeedbackIntensity();
    }
}
