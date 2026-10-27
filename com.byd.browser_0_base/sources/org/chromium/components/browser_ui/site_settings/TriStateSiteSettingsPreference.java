package org.chromium.components.browser_ui.site_settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import defpackage.n32;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;

/* JADX INFO: loaded from: classes2.dex */
public class TriStateSiteSettingsPreference extends Preference implements RadioGroup.OnCheckedChangeListener {
    public int S;
    public int[] T;
    public RadioButtonWithDescription U;
    public RadioButtonWithDescription V;
    public RadioButtonWithDescription W;

    public TriStateSiteSettingsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.S = 0;
        this.f17J = R$layout.tri_state_site_settings_preference;
        O();
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (this.U.b()) {
            this.S = 1;
        } else if (this.V.b()) {
            this.S = 3;
        } else if (this.W.b()) {
            this.S = 2;
        }
        h(Integer.valueOf(this.S));
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        this.U = (RadioButtonWithDescription) n32Var.u(R$id.allowed);
        this.V = (RadioButtonWithDescription) n32Var.u(R$id.ask);
        this.W = (RadioButtonWithDescription) n32Var.u(R$id.blocked);
        ((RadioGroup) n32Var.u(R$id.radio_button_layout)).setOnCheckedChangeListener(this);
        int[] iArr = this.T;
        if (iArr != null) {
            RadioButtonWithDescription radioButtonWithDescription = this.U;
            int i = iArr[0];
            Context context = this.e;
            radioButtonWithDescription.setDescriptionText(context.getText(i));
            this.V.setDescriptionText(context.getText(this.T[1]));
            this.W.setDescriptionText(context.getText(this.T[2]));
        }
        int i2 = this.S;
        RadioButtonWithDescription radioButtonWithDescription2 = i2 == 1 ? this.U : i2 == 3 ? this.V : i2 == 2 ? this.W : null;
        if (radioButtonWithDescription2 != null) {
            radioButtonWithDescription2.setChecked(true);
        }
    }
}
