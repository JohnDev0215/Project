package org.chromium.chrome.browser.homepage.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.preference.Preference;
import defpackage.n32;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;
import org.chromium.components.browser_ui.widget.RadioButtonWithEditText;

/* JADX INFO: loaded from: classes.dex */
public final class RadioButtonGroupHomepagePreference extends Preference implements RadioGroup.OnCheckedChangeListener, RadioButtonWithEditText.b {
    public boolean S;
    public RadioButtonWithEditText T;
    public RadioButtonWithDescription U;
    public RadioButtonWithDescriptionLayout V;
    public TextView W;
    public a X;

    public static class a {
        public int a;
        public String b;
        public final boolean c;
        public final boolean d;
        public final boolean e;

        public a(int i, String str, boolean z, boolean z2, boolean z3) {
            this.a = i;
            this.b = str;
            this.c = z;
            this.d = z2;
            this.e = z3;
        }
    }

    public RadioButtonGroupHomepagePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.radio_button_group_homepage_preference;
    }

    public final void Y(a aVar) {
        if (this.S) {
            this.V.setEnabled(aVar.c);
            this.W.setEnabled(aVar.c);
            this.T.setPrimaryText(aVar.b);
            if (aVar.a == 0) {
                this.U.setChecked(true);
            } else {
                this.T.setChecked(true);
            }
            this.U.setVisibility(aVar.d ? 0 : 8);
            this.T.setVisibility(aVar.e ? 0 : 8);
        }
        this.X = aVar;
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithEditText.b
    public final void a(CharSequence charSequence) {
        if (this.X.b.equals(charSequence.toString())) {
            return;
        }
        a aVar = this.X;
        aVar.a = 1;
        aVar.b = charSequence.toString();
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.X.a = !this.U.b() ? 1 : 0;
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        this.U = (RadioButtonWithDescription) n32Var.u(R$id.radio_button_chrome_ntp);
        this.T = (RadioButtonWithEditText) n32Var.u(R$id.radio_button_uri_edit);
        RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = (RadioButtonWithDescriptionLayout) n32Var.u(R$id.radio_button_group);
        this.V = radioButtonWithDescriptionLayout;
        radioButtonWithDescriptionLayout.setOnCheckedChangeListener(this);
        this.W = (TextView) n32Var.u(R$id.title);
        this.S = true;
        a aVar = this.X;
        if (aVar != null) {
            Y(aVar);
        }
        this.T.addTextChangeListener(this);
    }
}
