package org.chromium.components.browser_ui.site_settings;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import defpackage.c6;
import defpackage.n32;
import defpackage.oj2;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionAndAuxButton;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;

/* JADX INFO: loaded from: classes2.dex */
public class TriStateCookieSettingsPreference extends Preference implements RadioGroup.OnCheckedChangeListener, RadioButtonWithDescriptionAndAuxButton.a {
    public a S;
    public b T;
    public RadioButtonWithDescription U;
    public RadioButtonWithDescription V;
    public RadioButtonWithDescription W;
    public RadioGroup X;
    public TextViewWithCompoundDrawables Y;
    public n32 Z;

    public interface a {
    }

    public static class b {
        public boolean a;
        public int b;
        public boolean c;
        public boolean d;
        public boolean e;
    }

    public TriStateCookieSettingsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.tri_state_cookie_settings_preference;
        O();
    }

    public final void Y(b bVar) {
        this.U.setEnabled(true);
        this.V.setEnabled(true);
        this.W.setEnabled(true);
        for (RadioButtonWithDescription radioButtonWithDescription : !bVar.d ? !bVar.c ? new RadioButtonWithDescription[]{this.V} : new RadioButtonWithDescription[0] : new RadioButtonWithDescription[]{this.U, this.V, this.W}) {
            radioButtonWithDescription.setEnabled(false);
        }
        this.Y.setVisibility(bVar.d ? 0 : 8);
        int i = bVar.b;
        int i2 = (i != 2 || bVar.c) ? i : 0;
        RadioButtonWithDescription radioButtonWithDescription2 = i2 != 0 ? i2 != 1 ? i2 != 2 ? null : this.V : this.W : this.U;
        radioButtonWithDescription2.setEnabled(true);
        radioButtonWithDescription2.setChecked(true);
        this.T = null;
    }

    public final Resources Z() {
        return this.e.getResources();
    }

    public final Integer a0() {
        if (this.X == null && this.T == null) {
            return null;
        }
        b bVar = this.T;
        if (bVar != null) {
            int i = bVar.b;
            return Integer.valueOf((i != 2 || bVar.c) ? i : 0);
        }
        if (this.U.b()) {
            return 0;
        }
        return this.V.b() ? 2 : 1;
    }

    public final void b0(b bVar) {
        if (!bVar.a) {
            this.V = (RadioButtonWithDescription) this.Z.u(R$id.block_third_party_incognito);
            this.W = (RadioButtonWithDescription) this.Z.u(R$id.block_third_party);
            return;
        }
        this.Z.u(R$id.block_third_party_incognito).setVisibility(8);
        this.Z.u(R$id.block_third_party).setVisibility(8);
        RadioButtonWithDescriptionAndAuxButton radioButtonWithDescriptionAndAuxButton = (RadioButtonWithDescriptionAndAuxButton) this.Z.u(R$id.block_third_party_incognito_with_aux);
        RadioButtonWithDescriptionAndAuxButton radioButtonWithDescriptionAndAuxButton2 = (RadioButtonWithDescriptionAndAuxButton) this.Z.u(R$id.block_third_party_with_aux);
        String string = Z().getString(R$string.website_settings_category_cookie_block_third_party_incognito_title);
        Resources resourcesZ = Z();
        int i = R$string.accessibility_expand_button_with_target_description;
        radioButtonWithDescriptionAndAuxButton.setAuxButtonContentDescription(resourcesZ.getString(i, string));
        radioButtonWithDescriptionAndAuxButton.setVisibility(0);
        radioButtonWithDescriptionAndAuxButton2.setAuxButtonContentDescription(Z().getString(i, Z().getString(R$string.website_settings_category_cookie_block_third_party_title)));
        radioButtonWithDescriptionAndAuxButton2.setVisibility(0);
        radioButtonWithDescriptionAndAuxButton.setAuxButtonClickedListener(this);
        radioButtonWithDescriptionAndAuxButton2.setAuxButtonClickedListener(this);
        this.V = radioButtonWithDescriptionAndAuxButton;
        this.W = radioButtonWithDescriptionAndAuxButton2;
        if (bVar.e) {
            radioButtonWithDescriptionAndAuxButton2.setDescriptionText(Z().getString(R$string.website_settings_third_party_cookies_page_block_radio_sub_label_fps_enabled));
        } else {
            radioButtonWithDescriptionAndAuxButton2.setDescriptionText(Z().getString(R$string.website_settings_third_party_cookies_page_block_radio_sub_label_fps_disabled));
        }
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionAndAuxButton.a
    public final void k(int i) {
        if (i == this.V.getId()) {
            ((SingleCategorySettings) this.S).R0(2);
        } else if (i == this.W.getId()) {
            ((SingleCategorySettings) this.S).R0(1);
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        h(a0());
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        this.Z = n32Var;
        this.U = (RadioButtonWithDescription) n32Var.u(R$id.allow);
        RadioGroup radioGroup = (RadioGroup) n32Var.u(R$id.radio_button_layout);
        this.X = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        if (oj2.y()) {
            this.Y = (TextViewWithCompoundDrawables) n32Var.u(R$id.managed_disclaimer_text);
        } else {
            TextViewWithCompoundDrawables textViewWithCompoundDrawables = (TextViewWithCompoundDrawables) n32Var.u(R$id.managed_view_legacy);
            this.Y = textViewWithCompoundDrawables;
            Drawable[] compoundDrawablesRelative = textViewWithCompoundDrawables.getCompoundDrawablesRelative();
            this.Y.setCompoundDrawablesRelativeWithIntrinsicBounds(c6.c(Z(), org.chromium.components.browser_ui.settings.R$drawable.ic_business_small, 0), compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
        }
        b bVar = this.T;
        if (bVar != null) {
            b0(bVar);
            Y(this.T);
        }
    }
}
