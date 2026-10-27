package org.chromium.chrome.browser.toolbar.adaptive.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import defpackage.n3;
import defpackage.n32;
import defpackage.on0;
import defpackage.p3;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;

/* JADX INFO: loaded from: classes2.dex */
public class RadioButtonGroupAdaptiveToolbarPreference extends Preference implements RadioGroup.OnCheckedChangeListener {
    public RadioButtonWithDescriptionLayout S;
    public RadioButtonWithDescription T;
    public RadioButtonWithDescription U;
    public RadioButtonWithDescription V;
    public int W;
    public p3 X;
    public boolean Y;

    public RadioButtonGroupAdaptiveToolbarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Y = true;
        this.f17J = R$layout.radio_button_group_adaptive_toolbar_preference;
    }

    public final void Y() {
        RadioButtonWithDescription radioButtonWithDescription = this.V;
        if (radioButtonWithDescription == null) {
            return;
        }
        radioButtonWithDescription.setVisibility(this.Y ? 0 : 8);
        if (!this.V.b() || this.Y) {
            return;
        }
        this.T.setChecked(true);
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        p3 p3Var;
        int i2 = this.W;
        if (this.T.b()) {
            this.W = 5;
        } else if (this.U.b()) {
            this.W = 2;
        } else if (this.V.b()) {
            this.W = 4;
        }
        h(Integer.valueOf(this.W));
        if (i2 == this.W || (p3Var = this.X) == null) {
            return;
        }
        p3Var.b(new on0(23, "Android.AdaptiveToolbarButton.Settings.Changed"));
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = (RadioButtonWithDescriptionLayout) n32Var.u(R$id.adaptive_radio_group);
        this.S = radioButtonWithDescriptionLayout;
        radioButtonWithDescriptionLayout.setOnCheckedChangeListener(this);
        this.T = (RadioButtonWithDescription) n32Var.u(R$id.adaptive_option_based_on_usage);
        this.U = (RadioButtonWithDescription) n32Var.u(R$id.adaptive_option_new_tab);
        this.V = (RadioButtonWithDescription) n32Var.u(R$id.adaptive_option_voice_search);
        Y();
        p3 p3Var = this.X;
        if (p3Var != null && this.S != null) {
            p3Var.b(new on0(24, this));
            this.X.b(new on0(23, "Android.AdaptiveToolbarButton.Settings.Startup"));
        }
        n3.X0("Mobile.AdaptiveToolbarButton.SettingsPage.Opened");
    }
}
