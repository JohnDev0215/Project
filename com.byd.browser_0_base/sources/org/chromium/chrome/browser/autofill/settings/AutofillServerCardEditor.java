package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.ab;
import defpackage.bq;
import defpackage.by1;
import defpackage.ib;
import defpackage.oj2;
import defpackage.sa;
import defpackage.sk;
import defpackage.vt;
import org.chromium.base.ThreadUtils;
import org.chromium.build.annotations.UsedByReflection;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.d;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes.dex */
public class AutofillServerCardEditor extends sa {
    public static final /* synthetic */ int n0 = 0;
    public View f0;
    public View g0;
    public TextView h0;
    public boolean i0;
    public ab j0;
    public boolean k0;
    public boolean l0;
    public ib m0;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            PersonalDataManager personalDataManagerC = PersonalDataManager.c();
            int i = AutofillServerCardEditor.n0;
            AutofillServerCardEditor autofillServerCardEditor = AutofillServerCardEditor.this;
            N.MSOj73VL(personalDataManagerC.a, personalDataManagerC, autofillServerCardEditor.Z);
            ViewGroup viewGroup = (ViewGroup) autofillServerCardEditor.g0.getParent();
            if (viewGroup == null) {
                return;
            }
            viewGroup.removeView(autofillServerCardEditor.f0);
            viewGroup.removeView(autofillServerCardEditor.g0);
        }
    }

    @UsedByReflection
    public AutofillServerCardEditor() {
    }

    public static void M0(int i, int i2) {
        StringBuilder sb = new StringBuilder("Autofill.SettingsPage.ButtonClicked.");
        String str = "None";
        sb.append(i != 1 ? i != 2 ? "None" : "VirtualCard" : "ServerCard");
        sb.append(".");
        if (i2 == 1) {
            str = "EditCard";
        } else if (i2 == 2) {
            str = "VirtualCardEnroll";
        } else if (i2 == 3) {
            str = "VirtualCardUnenroll";
        }
        sb.append(str);
        oj2.O(sb.toString(), true);
    }

    @Override // org.chromium.chrome.browser.autofill.AutofillEditorBase
    public final int I0() {
        return R$layout.autofill_server_card_editor;
    }

    @Override // org.chromium.chrome.browser.autofill.AutofillEditorBase
    public final int J0(boolean z) {
        return R$string.autofill_edit_credit_card;
    }

    @Override // org.chromium.chrome.browser.autofill.AutofillEditorBase
    public final boolean K0() {
        if (this.d0.getSelectedItem() == null || !(this.d0.getSelectedItem() instanceof PersonalDataManager.AutofillProfile)) {
            return true;
        }
        this.c0.l = ((PersonalDataManager.AutofillProfile) this.d0.getSelectedItem()).getGUID();
        PersonalDataManager personalDataManagerC = PersonalDataManager.c();
        PersonalDataManager.CreditCard creditCard = this.c0;
        personalDataManagerC.getClass();
        Object obj = ThreadUtils.a;
        N.MmUEbunT(personalDataManagerC.a, personalDataManagerC, creditCard);
        return true;
    }

    public final boolean N0() {
        sk skVar = bq.a;
        return N.M09VlOh_("AutofillEnableUpdateVirtualCardEnrollment") && (this.c0.getVirtualCardEnrollmentState() == 2 || this.c0.getVirtualCardEnrollmentState() == 4);
    }

    @Override // androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        sk skVar = bq.a;
        if (N.M09VlOh_("AutofillEnableUpdateVirtualCardEnrollment")) {
            this.j0 = new ab(Profile.d());
            this.m0 = new ib(this, 0);
        }
    }

    @Override // defpackage.sa, org.chromium.chrome.browser.autofill.AutofillEditorBase, androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewE0 = super.e0(layoutInflater, viewGroup, bundle);
        if (this.c0 == null) {
            D().finish();
            return viewE0;
        }
        ((ImageView) viewE0.findViewById(R$id.card_icon)).setImageDrawable(d.b(K(), this.c0, R$dimen.settings_page_card_icon_width, R$dimen.settings_page_card_icon_height));
        ((TextView) viewE0.findViewById(R$id.card_name)).setText(this.c0.t);
        ((TextView) viewE0.findViewById(R$id.card_last_four)).setText(this.c0.u);
        ((TextView) viewE0.findViewById(R$id.card_expiration)).setText(this.c0.a(D()));
        int i = 4;
        viewE0.findViewById(R$id.edit_server_card).setOnClickListener(new by1(4, this));
        LinearLayout linearLayout = (LinearLayout) viewE0.findViewById(R$id.virtual_card_ui);
        this.h0 = (TextView) viewE0.findViewById(R$id.virtual_card_enrollment_button);
        if (N0()) {
            linearLayout.setVisibility(0);
            this.i0 = this.c0.getVirtualCardEnrollmentState() == 2;
            this.h0.setEnabled(true);
            this.h0.setText(this.i0 ? R$string.autofill_card_editor_virtual_card_turn_off_button_label : R$string.autofill_card_editor_virtual_card_turn_on_button_label);
            this.h0.setOnClickListener(new vt(i, this));
        } else {
            linearLayout.setVisibility(8);
        }
        this.f0 = viewE0.findViewById(R$id.local_copy_label);
        this.g0 = viewE0.findViewById(R$id.clear_local_copy);
        if (this.c0.getIsCached()) {
            this.g0.setOnClickListener(new a());
        } else {
            ViewGroup viewGroup2 = (ViewGroup) this.g0.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.f0);
                viewGroup2.removeView(this.g0);
            }
        }
        L0(viewE0);
        return viewE0;
    }

    @Override // androidx.fragment.app.Fragment
    public final void f0() {
        this.H = true;
        sk skVar = bq.a;
        if (N.M09VlOh_("AutofillEnableUpdateVirtualCardEnrollment")) {
            if (this.k0) {
                this.l0 = true;
                return;
            }
            ab abVar = this.j0;
            long j = abVar.a;
            if (j != 0) {
                N.MgCnSGKp(j);
                abVar.a = 0L;
            }
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView != this.d0 || i == this.e0) {
            return;
        }
        ((Button) this.f12J.findViewById(R$id.button_primary)).setEnabled(true);
    }
}
