package org.chromium.chrome.browser.image_descriptions;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import defpackage.n32;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;

/* JADX INFO: loaded from: classes.dex */
public class RadioButtonGroupAccessibilityPreference extends Preference implements RadioGroup.OnCheckedChangeListener {
    public RadioButtonWithDescription S;
    public RadioButtonWithDescription T;
    public boolean U;

    public RadioButtonGroupAccessibilityPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.radio_button_group_accessibility_preference;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        boolean zB = this.S.b();
        this.U = zB;
        h(Boolean.valueOf(zB));
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        this.S = (RadioButtonWithDescription) n32Var.u(R$id.image_descriptions_settings_only_on_wifi_radio_button);
        this.T = (RadioButtonWithDescription) n32Var.u(R$id.image_descriptions_settings_mobile_data_radio_button);
        ((RadioButtonWithDescriptionLayout) this.S.getParent()).setOnCheckedChangeListener(this);
        if (this.U) {
            this.S.setChecked(true);
        } else {
            this.T.setChecked(true);
        }
    }
}
