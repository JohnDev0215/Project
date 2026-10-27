package com.android.car.developeroptions.accessibility;

import android.os.Vibrator;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class NotificationVibrationPreferenceFragment extends VibrationPreferenceFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1293;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.accessibility_notification_vibration_settings;
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationPreferenceFragment
    protected int getPreviewVibrationAudioAttributesUsage() {
        return 5;
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationPreferenceFragment
    protected String getVibrationEnabledSetting() {
        return "";
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationPreferenceFragment
    protected String getVibrationIntensitySetting() {
        return "notification_vibration_intensity";
    }

    @Override // com.android.car.developeroptions.accessibility.VibrationPreferenceFragment
    protected int getDefaultVibrationIntensity() {
        return ((Vibrator) getContext().getSystemService(Vibrator.class)).getDefaultNotificationVibrationIntensity();
    }
}
