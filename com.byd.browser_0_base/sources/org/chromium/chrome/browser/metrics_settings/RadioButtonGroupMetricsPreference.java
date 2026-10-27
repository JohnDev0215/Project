package org.chromium.chrome.browser.metrics_settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;

/* JADX INFO: loaded from: classes2.dex */
public class RadioButtonGroupMetricsPreference extends Preference implements RadioGroup.OnCheckedChangeListener {
    public RadioButtonGroupMetricsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.radio_button_group_metrics_preference;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
    }
}
