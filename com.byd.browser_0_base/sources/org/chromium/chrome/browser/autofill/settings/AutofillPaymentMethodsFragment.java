package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.d;
import defpackage.bb;
import defpackage.bq;
import defpackage.c6;
import defpackage.gq;
import defpackage.hu0;
import defpackage.ii2;
import defpackage.qh1;
import defpackage.rh3;
import defpackage.sk;
import defpackage.ut1;
import defpackage.wd0;
import defpackage.wt1;
import java.util.ArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* JADX INFO: loaded from: classes.dex */
public class AutofillPaymentMethodsFragment extends d implements PersonalDataManager.a {
    public static final /* synthetic */ int h0 = 0;

    public class a implements gq {
        @Override // defpackage.mb1
        public final boolean g(Preference preference) {
            return N.MbTLtWkf() && !PersonalDataManager.d().a("autofill.credit_card_enabled");
        }

        @Override // defpackage.mb1
        public final boolean r(Preference preference) {
            return N.MbTLtWkf();
        }
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.a
    public final void A() {
        M0();
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.autofill_payment_methods);
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
        I0().c0();
        I0().V = true;
        ChromeSwitchPreference chromeSwitchPreference = new ChromeSwitchPreference(this.a0.a, null);
        chromeSwitchPreference.S(R$string.autofill_enable_credit_cards_toggle_label);
        chromeSwitchPreference.Q(R$string.autofill_enable_credit_cards_toggle_sublabel);
        chromeSwitchPreference.Y(PersonalDataManager.d().a("autofill.credit_card_enabled"));
        chromeSwitchPreference.setOnPreferenceChangeListener(new qh1(1));
        chromeSwitchPreference.b0(new a());
        I0().Y(chromeSwitchPreference);
        BiometricManager biometricManager = (BiometricManager) this.a0.a.getSystemService(BiometricManager.class);
        int i = 2;
        if (biometricManager != null && biometricManager.canAuthenticate() == 0) {
            PersonalDataManager personalDataManagerC = PersonalDataManager.c();
            personalDataManagerC.getClass();
            if (PersonalDataManager.d().a("autofill.credit_card_enabled") && N.M6rxmiWN(personalDataManagerC.a)) {
                ChromeSwitchPreference chromeSwitchPreference2 = new ChromeSwitchPreference(this.a0.a, null);
                chromeSwitchPreference2.S(R$string.enable_credit_card_fido_auth_label);
                chromeSwitchPreference2.Q(R$string.enable_credit_card_fido_auth_sublabel);
                chromeSwitchPreference2.Y(PersonalDataManager.d().a("autofill.credit_card_fido_auth_enabled"));
                chromeSwitchPreference2.setOnPreferenceChangeListener(new wd0(i));
                I0().Y(chromeSwitchPreference2);
            }
        }
        PersonalDataManager personalDataManagerC2 = PersonalDataManager.c();
        personalDataManagerC2.getClass();
        Object obj = ThreadUtils.a;
        long j = personalDataManagerC2.a;
        String[] strArrMQERVwyl = N.MQERVwyl(j, personalDataManagerC2);
        ArrayList<PersonalDataManager.CreditCard> arrayList = new ArrayList(strArrMQERVwyl.length);
        for (String str : strArrMQERVwyl) {
            arrayList.add((PersonalDataManager.CreditCard) N.M3g2doJx(j, personalDataManagerC2, str));
        }
        for (PersonalDataManager.CreditCard creditCard : arrayList) {
            Preference preference = new Preference(this.a0.a);
            preference.P();
            preference.T(creditCard.o);
            sk skVar = bq.a;
            if (!N.M09VlOh_("AutofillEnableVirtualCardMetadata")) {
                preference.R(creditCard.a(D()));
            } else if (creditCard.getVirtualCardEnrollmentState() == 2) {
                preference.Q(R$string.autofill_virtual_card_enrolled_text);
            } else if (creditCard.getVirtualCardEnrollmentState() == 4) {
                preference.Q(R$string.autofill_virtual_card_enrollment_eligible_text);
            } else {
                preference.R(creditCard.a(D()));
            }
            preference.L(org.chromium.chrome.browser.autofill.d.b(this.a0.a, creditCard, R$dimen.settings_page_card_icon_width, R$dimen.settings_page_card_icon_height));
            if (creditCard.getIsLocal()) {
                preference.r = AutofillLocalCardEditor.class.getName();
            } else {
                preference.r = AutofillServerCardEditor.class.getName();
                if (N.M09VlOh_("AutofillEnableVirtualCardMetadata")) {
                    preference.K = R$layout.autofill_server_data_label;
                } else {
                    preference.K = R$layout.autofill_server_data_text_label;
                }
            }
            preference.m().putString("guid", creditCard.getGUID());
            I0().Y(preference);
        }
        if (PersonalDataManager.d().a("autofill.credit_card_enabled")) {
            Preference preference2 = new Preference(this.a0.a);
            Drawable drawableC = c6.c(N(), R$drawable.plus, 0);
            drawableC.mutate();
            drawableC.setColorFilter(ii2.b(K()), PorterDuff.Mode.SRC_IN);
            preference2.L(drawableC);
            preference2.S(R$string.autofill_create_credit_card);
            preference2.r = AutofillLocalCardEditor.class.getName();
            I0().Y(preference2);
        }
        Preference preference3 = new Preference(this.a0.a);
        preference3.S(R$string.payment_apps_title);
        preference3.r = AndroidPaymentAppsFragment.class.getCanonicalName();
        if (!preference3.I) {
            preference3.I = true;
            preference3.s();
        }
        preference3.M("payment_apps");
        I0().Y(preference3);
        new ut1();
        if (!wt1.c(new Intent("org.chromium.intent.action.PAY"), 0).isEmpty()) {
            preference3.R(null);
            preference3.J(true);
            return;
        }
        bb bbVar = new bb(this, preference3);
        Object obj2 = ThreadUtils.a;
        if (N.M1X7xdZV("ServiceWorkerPaymentApps")) {
            N.MCqa2KNs(bbVar);
        } else {
            PostTask.c(rh3.a, new org.chromium.chrome.browser.payments.a(bbVar));
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
}
