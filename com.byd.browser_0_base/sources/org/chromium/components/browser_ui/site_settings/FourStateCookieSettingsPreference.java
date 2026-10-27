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
public class FourStateCookieSettingsPreference extends Preference implements RadioGroup.OnCheckedChangeListener, RadioButtonWithDescriptionAndAuxButton.a {
    public b S;
    public c T;
    public RadioButtonWithDescription U;
    public RadioButtonWithDescription V;
    public RadioButtonWithDescription W;
    public RadioButtonWithDescription X;
    public RadioGroup Y;
    public TextViewWithCompoundDrawables Z;
    public n32 a0;

    public enum a {
        UNINITIALIZED,
        ALLOW,
        BLOCK_THIRD_PARTY_INCOGNITO,
        BLOCK_THIRD_PARTY,
        BLOCK
    }

    public interface b {
    }

    public static class c {
        public boolean a;
        public boolean b;
        public int c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;
    }

    public FourStateCookieSettingsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.four_state_cookie_settings_preference;
        O();
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Y(org.chromium.components.browser_ui.site_settings.FourStateCookieSettingsPreference.c r10) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.site_settings.FourStateCookieSettingsPreference.Y(org.chromium.components.browser_ui.site_settings.FourStateCookieSettingsPreference$c):void");
    }

    public final Resources Z() {
        return this.e.getResources();
    }

    public final a a0() {
        if (this.Y == null && this.T == null) {
            return a.UNINITIALIZED;
        }
        c cVar = this.T;
        a aVar = a.BLOCK_THIRD_PARTY_INCOGNITO;
        a aVar2 = a.ALLOW;
        a aVar3 = a.BLOCK_THIRD_PARTY;
        a aVar4 = a.BLOCK;
        if (cVar == null) {
            return this.U.b() ? aVar2 : this.V.b() ? aVar : this.W.b() ? aVar3 : aVar4;
        }
        if (!cVar.b) {
            return aVar4;
        }
        int i = cVar.c;
        return i == 1 ? aVar3 : (i == 2 && cVar.d) ? aVar : aVar2;
    }

    public final void b0(c cVar) {
        if (!cVar.a) {
            this.V = (RadioButtonWithDescription) this.a0.u(R$id.block_third_party_incognito);
            this.W = (RadioButtonWithDescription) this.a0.u(R$id.block_third_party);
            return;
        }
        this.a0.u(R$id.block_third_party_incognito).setVisibility(8);
        this.a0.u(R$id.block_third_party).setVisibility(8);
        RadioButtonWithDescriptionAndAuxButton radioButtonWithDescriptionAndAuxButton = (RadioButtonWithDescriptionAndAuxButton) this.a0.u(R$id.block_third_party_incognito_with_aux);
        RadioButtonWithDescriptionAndAuxButton radioButtonWithDescriptionAndAuxButton2 = (RadioButtonWithDescriptionAndAuxButton) this.a0.u(R$id.block_third_party_with_aux);
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
        String string2 = Z().getString(R$string.website_settings_third_party_cookies_page_block_radio_sub_label);
        if (!cVar.g) {
            this.W.setDescriptionText(string2);
        } else {
            this.W.setDescriptionText(Z().getString(R$string.concat_two_strings_with_periods, string2, Z().getString(R$string.website_settings_category_cookie_block_third_party_fps_addition)));
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
        this.a0 = n32Var;
        this.U = (RadioButtonWithDescription) n32Var.u(R$id.allow);
        this.X = (RadioButtonWithDescription) n32Var.u(R$id.block);
        RadioGroup radioGroup = (RadioGroup) n32Var.u(R$id.radio_button_layout);
        this.Y = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        if (oj2.y()) {
            this.Z = (TextViewWithCompoundDrawables) n32Var.u(R$id.managed_disclaimer_text);
        } else {
            TextViewWithCompoundDrawables textViewWithCompoundDrawables = (TextViewWithCompoundDrawables) n32Var.u(R$id.managed_view_legacy);
            this.Z = textViewWithCompoundDrawables;
            Drawable[] compoundDrawablesRelative = textViewWithCompoundDrawables.getCompoundDrawablesRelative();
            this.Z.setCompoundDrawablesRelativeWithIntrinsicBounds(c6.c(Z(), org.chromium.components.browser_ui.settings.R$drawable.ic_business_small, 0), compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
        }
        c cVar = this.T;
        if (cVar != null) {
            b0(cVar);
            Y(this.T);
        }
    }
}
