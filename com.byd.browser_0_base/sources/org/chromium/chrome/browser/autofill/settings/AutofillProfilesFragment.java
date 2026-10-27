package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.d;
import defpackage.c6;
import defpackage.fm3;
import defpackage.gq;
import defpackage.hu0;
import defpackage.i83;
import defpackage.ii2;
import defpackage.ku2;
import defpackage.qa;
import defpackage.tf0;
import defpackage.wv;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class AutofillProfilesFragment extends d implements PersonalDataManager.a {
    public static final /* synthetic */ int i0 = 0;
    public tf0 h0;

    public class a implements gq {
        @Override // defpackage.mb1
        public final boolean g(Preference preference) {
            return N.MCL0OG9d() && !PersonalDataManager.d().a("autofill.profile_enabled");
        }

        @Override // defpackage.mb1
        public final boolean r(Preference preference) {
            return N.MCL0OG9d();
        }
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.a
    public final void A() {
        M0();
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.autofill_addresses_settings_title);
        D0();
        PreferenceManager preferenceManager = this.a0;
        PreferenceScreen preferenceScreenA = preferenceManager.a(preferenceManager.a);
        if (preferenceScreenA.X) {
            throw new IllegalStateException("Cannot change the usage of generated IDs while attached to the preference hierarchy");
        }
        preferenceScreenA.b0 = false;
        L0(preferenceScreenA);
    }

    public final void M0() {
        ku2 ku2VarC;
        I0().c0();
        I0().V = true;
        ChromeSwitchPreference chromeSwitchPreference = new ChromeSwitchPreference(this.a0.a, null);
        chromeSwitchPreference.S(R$string.autofill_enable_profiles_toggle_label);
        chromeSwitchPreference.Q(R$string.autofill_enable_profiles_toggle_sublabel);
        chromeSwitchPreference.Y(PersonalDataManager.d().a("autofill.profile_enabled"));
        chromeSwitchPreference.setOnPreferenceChangeListener(new i83(3));
        chromeSwitchPreference.b0(new a());
        I0().Y(chromeSwitchPreference);
        PersonalDataManager personalDataManagerC = PersonalDataManager.c();
        personalDataManagerC.getClass();
        Object obj = ThreadUtils.a;
        long j = personalDataManagerC.a;
        for (PersonalDataManager.AutofillProfile autofillProfile : personalDataManagerC.e(N.M6XJvXko(j, personalDataManagerC), N.M4q3jK16(j, personalDataManagerC))) {
            AutofillProfileEditorPreference autofillProfileEditorPreference = new AutofillProfileEditorPreference(this.a0.a);
            autofillProfileEditorPreference.T(autofillProfile.getFullName());
            autofillProfileEditorPreference.R(autofillProfile.q);
            autofillProfileEditorPreference.M(autofillProfileEditorPreference.l.toString());
            autofillProfileEditorPreference.m().putString("guid", autofillProfile.getGUID());
            ku2VarC = ku2.C();
            try {
                I0().Y(autofillProfileEditorPreference);
                ku2VarC.close();
            } finally {
            }
        }
        if (PersonalDataManager.d().a("autofill.profile_enabled")) {
            AutofillProfileEditorPreference autofillProfileEditorPreference2 = new AutofillProfileEditorPreference(this.a0.a);
            Drawable drawableC = c6.c(N(), R$drawable.plus, 0);
            drawableC.mutate();
            drawableC.setColorFilter(ii2.b(K()), PorterDuff.Mode.SRC_IN);
            autofillProfileEditorPreference2.L(drawableC);
            autofillProfileEditorPreference2.S(R$string.autofill_create_profile);
            autofillProfileEditorPreference2.M("new_profile");
            ku2VarC = ku2.C();
            try {
                I0().Y(autofillProfileEditorPreference2);
                ku2VarC.close();
            } finally {
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void Y() {
        this.H = true;
        PersonalDataManager personalDataManagerC = PersonalDataManager.c();
        personalDataManagerC.getClass();
        Object obj = ThreadUtils.a;
        personalDataManagerC.b.add(this);
        N.Melg71WL(personalDataManagerC.a, personalDataManagerC);
    }

    @Override // androidx.fragment.app.Fragment
    public final void d0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menu.add(0, R$id.menu_id_targeted_help, 0, R$string.menu_help).setIcon(R$drawable.ic_help_and_feedback);
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void g0() {
        PersonalDataManager personalDataManagerC = PersonalDataManager.c();
        personalDataManagerC.getClass();
        Object obj = ThreadUtils.a;
        personalDataManagerC.b.remove(this);
        super.g0();
    }

    @Override // androidx.fragment.app.Fragment
    public final boolean k0(MenuItem menuItem) {
        if (menuItem.getItemId() != R$id.menu_id_targeted_help) {
            return false;
        }
        ((hu0) hu0.a()).c(D(), D().getString(R$string.help_context_autofill), null, Profile.d());
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        M0();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        this.H = true;
        tf0 tf0Var = this.h0;
        if (tf0Var != null) {
            tf0Var.e();
        }
    }

    @Override // androidx.preference.d, androidx.preference.PreferenceManager.a
    public final void y(Preference preference) {
        if (!(preference instanceof AutofillProfileEditorPreference)) {
            super.y(preference);
            return;
        }
        String string = ((AutofillProfileEditorPreference) preference).m().getString("guid");
        qa qaVar = null;
        this.h0 = new tf0(D(), string == null ? null : new wv(18, string), Profile.d(), false);
        if (string != null) {
            PersonalDataManager personalDataManagerC = PersonalDataManager.c();
            personalDataManagerC.getClass();
            Object obj = ThreadUtils.a;
            PersonalDataManager.AutofillProfile autofillProfile = (PersonalDataManager.AutofillProfile) N.M172IO7Q(personalDataManagerC.a, personalDataManagerC, string);
            if (autofillProfile != null) {
                qaVar = new qa(D(), autofillProfile);
            }
        }
        tf0 tf0Var = this.h0;
        org.chromium.chrome.browser.autofill.settings.a aVar = new org.chromium.chrome.browser.autofill.settings.a(true);
        aVar.a = tf0Var;
        aVar.b = tf0Var.getContext();
        fm3 fm3Var = new fm3(1);
        aVar.b(qaVar, fm3Var, fm3Var);
    }
}
