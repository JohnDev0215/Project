package org.chromium.chrome.browser.night_mode.settings;

import J.N;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import defpackage.bq;
import defpackage.ie0;
import defpackage.n3;
import defpackage.n32;
import defpackage.oj2;
import defpackage.sk;
import java.util.ArrayList;
import java.util.Collections;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;

/* JADX INFO: loaded from: classes2.dex */
public class RadioButtonGroupThemePreference extends Preference implements RadioGroup.OnCheckedChangeListener {
    public int S;
    public RadioButtonWithDescription T;
    public RadioButtonWithDescriptionLayout U;
    public final ArrayList<RadioButtonWithDescription> V;
    public LinearLayout W;
    public boolean X;
    public CheckBox Y;

    public RadioButtonGroupThemePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.radio_button_group_theme_preference;
        this.V = new ArrayList<>(Collections.nCopies(3, null));
    }

    public final void Y() {
        sk skVar = bq.a;
        if (N.M09VlOh_("DarkenWebsitesCheckboxInThemesSetting")) {
            int i = this.S;
            if (i != 0 && i != 2) {
                this.W.setVisibility(8);
                return;
            }
            RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = this.U;
            LinearLayout linearLayout = this.W;
            RadioButtonWithDescription radioButtonWithDescription = this.T;
            radioButtonWithDescriptionLayout.getClass();
            if (linearLayout.getParent() != null) {
                ((ViewGroup) linearLayout.getParent()).removeView(linearLayout);
            }
            radioButtonWithDescriptionLayout.addView(linearLayout, radioButtonWithDescriptionLayout.indexOfChild(radioButtonWithDescription) + 1);
            this.W.setVisibility(0);
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        int i2 = 0;
        while (true) {
            if (i2 >= 3) {
                break;
            }
            ArrayList<RadioButtonWithDescription> arrayList = this.V;
            if (arrayList.get(i2).b()) {
                this.S = i2;
                this.T = arrayList.get(i2);
                break;
            }
            i2++;
        }
        Y();
        h(Integer.valueOf(this.S));
        int i3 = this.S;
        if (i3 == 0) {
            n3.X0("Android.DarkTheme.Preference.SystemDefault");
        } else if (i3 == 1) {
            n3.X0("Android.DarkTheme.Preference.Light");
        } else if (i3 == 2) {
            n3.X0("Android.DarkTheme.Preference.Dark");
        }
        oj2.U(i3, 3, "Android.DarkTheme.Preference.State");
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        this.W = (LinearLayout) n32Var.u(R$id.checkbox_container);
        this.Y = (CheckBox) n32Var.u(R$id.darken_websites);
        RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = (RadioButtonWithDescriptionLayout) n32Var.u(R$id.radio_button_layout);
        this.U = radioButtonWithDescriptionLayout;
        radioButtonWithDescriptionLayout.setOnCheckedChangeListener(this);
        this.W.setOnClickListener(new ie0(12, this));
        this.Y.setChecked(this.X);
        ArrayList<RadioButtonWithDescription> arrayList = this.V;
        arrayList.set(0, (RadioButtonWithDescription) n32Var.u(R$id.system_default));
        arrayList.get(0).setDescriptionText(this.e.getString(R$string.themes_system_default_summary_api_29));
        arrayList.set(1, (RadioButtonWithDescription) n32Var.u(R$id.light));
        arrayList.set(2, (RadioButtonWithDescription) n32Var.u(R$id.dark));
        RadioButtonWithDescription radioButtonWithDescription = arrayList.get(this.S);
        this.T = radioButtonWithDescription;
        radioButtonWithDescription.setChecked(true);
        Y();
    }
}
