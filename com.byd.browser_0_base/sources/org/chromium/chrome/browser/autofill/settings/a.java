package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import defpackage.bq;
import defpackage.il;
import defpackage.l91;
import defpackage.m91;
import defpackage.mf0;
import defpackage.qa;
import defpackage.sk;
import defpackage.v02;
import defpackage.xf0;
import defpackage.z7;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillProfileBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: compiled from: AddressEditor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends mf0<qa> {
    public final boolean f;
    public AutofillProfileBridge i;
    public xf0 j;
    public xf0 k;
    public xf0 l;
    public xf0 m;
    public xf0 n;
    public ArrayList o;
    public String p;
    public PersonalDataManager.AutofillProfile q;
    public il r;
    public final Handler c = new Handler();
    public final HashMap d = new HashMap();
    public final HashSet e = new HashSet();
    public final v02 g = new v02();
    public final b h = new b();

    /* JADX INFO: renamed from: org.chromium.chrome.browser.autofill.settings.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AddressEditor.java */
    public class C0073a implements Callback<Pair<String, Runnable>> {
        public final /* synthetic */ boolean e;

        public C0073a(boolean z) {
            this.e = z;
        }

        @Override // org.chromium.base.Callback
        public final void onResult(Pair<String, Runnable> pair) {
            Pair<String, Runnable> pair2 = pair;
            a aVar = a.this;
            ((List) aVar.r.f).clear();
            String str = (String) pair2.first;
            aVar.g.f = str;
            aVar.h.a = str;
            aVar.a(str, Locale.getDefault().getLanguage(), this.e);
            aVar.c.post((Runnable) pair2.second);
        }
    }

    public a(boolean z) {
        this.f = z;
    }

    public static String c(CharSequence charSequence) {
        return charSequence == null ? "" : charSequence.toString();
    }

    public static void d(PersonalDataManager.AutofillProfile autofillProfile, int i, CharSequence charSequence) {
        switch (i) {
            case 0:
                autofillProfile.n = new PersonalDataManager.b(c(charSequence), 4);
                break;
            case 1:
                autofillProfile.i = new PersonalDataManager.b(c(charSequence), 4);
                break;
            case 2:
                autofillProfile.j = new PersonalDataManager.b(c(charSequence), 4);
                break;
            case 3:
                autofillProfile.k = new PersonalDataManager.b(c(charSequence), 4);
                break;
            case 4:
                autofillProfile.m = new PersonalDataManager.b(c(charSequence), 4);
                break;
            case 5:
                autofillProfile.l = new PersonalDataManager.b(c(charSequence), 4);
                break;
            case 6:
                autofillProfile.h = new PersonalDataManager.b(c(charSequence), 4);
                break;
            case 7:
                autofillProfile.g = new PersonalDataManager.b(c(charSequence), 4);
                break;
            case 8:
                autofillProfile.f = new PersonalDataManager.b(c(charSequence), 4);
                break;
        }
    }

    public final void a(String str, String str2, boolean z) {
        xf0 xf0Var;
        this.o = this.i.a(1, str, str2);
        this.r.a(this.j);
        boolean z2 = this.q.getSource() == 1;
        for (int i = 0; i < this.o.size(); i++) {
            AutofillProfileBridge.a aVar = (AutofillProfileBridge.a) this.o.get(i);
            if (aVar.a == 8 && (xf0Var = this.k) != null) {
                this.r.a(xf0Var);
            }
            HashMap map = this.d;
            int i2 = aVar.a;
            xf0 xf0Var2 = (xf0) map.get(Integer.valueOf(i2));
            String str3 = aVar.b;
            xf0Var2.m = str3;
            xf0Var2.q = aVar.d || i2 == 2 || i2 == 3;
            String strH = qa.h(this.q, i2);
            if (z2 && aVar.c && (z || !TextUtils.isEmpty(strH))) {
                xf0Var2.i = this.b.getString(R$string.autofill_edit_address_required_field_error).replace("$1", str3);
            }
            this.r.a(xf0Var2);
        }
        xf0 xf0Var3 = this.l;
        if (xf0Var3 != null) {
            this.r.a(xf0Var3);
        }
        xf0 xf0Var4 = this.m;
        if (xf0Var4 != null) {
            this.r.a(xf0Var4);
        }
        xf0 xf0Var5 = this.n;
        if (xf0Var5 != null) {
            this.r.a(xf0Var5);
        }
    }

    public final void b(qa qaVar, Callback<qa> callback, Callback<qa> callback2) {
        String string;
        qa qaVar2;
        String string2;
        String str;
        String strReplace;
        String strReplace2;
        if (this.i == null) {
            this.i = new AutofillProfileBridge();
        }
        boolean z = qaVar == null;
        if (z) {
            qaVar2 = new qa(this.b, new PersonalDataManager.AutofillProfile());
            string = this.b.getString(R$string.autofill_create_profile);
        } else {
            string = this.b.getString(R$string.autofill_edit_address_dialog_title);
            qaVar2 = qaVar;
        }
        String str2 = string;
        this.q = qaVar2.l;
        String string3 = this.b.getString(R$string.autofill_delete_address_confirmation_dialog_title);
        if (this.q.getSource() == 1) {
            z7 z7VarM = z7.m();
            Profile profileD = Profile.d();
            z7VarM.getClass();
            String strB = CoreAccountInfo.b(z7.v(profileD).a(0));
            if (strB != null) {
                strReplace2 = this.b.getString(R$string.autofill_edit_account_address_source_notice).replace("$1", strB);
                strReplace = this.b.getString(R$string.autofill_delete_account_address_source_notice).replace("$1", strB);
            } else {
                strReplace = null;
                strReplace2 = null;
            }
            string2 = strReplace;
            str = strReplace2;
        } else {
            string2 = this.b.getString(R$string.autofill_delete_local_address_source_notice);
            str = null;
        }
        this.r = new il(str2, this.p, str, string3, string2);
        if (this.j == null) {
            this.j = xf0.a(this.b.getString(R$string.autofill_profile_editor_country), AutofillProfileBridge.b(), null);
        }
        xf0 xf0Var = this.j;
        xf0Var.p = new C0073a(z);
        xf0Var.n = qa.g(this.q);
        this.h.a = this.j.n.toString();
        this.g.f = this.j.n.toString();
        sk skVar = bq.a;
        if (N.M09VlOh_("AutofillEnableSupportForHonorificPrefixes")) {
            if (this.k == null) {
                xf0 xf0VarB = xf0.b();
                this.k = xf0VarB;
                xf0VarB.m = this.b.getString(R$string.autofill_profile_editor_honorific_prefix);
            }
            this.k.n = this.q.getHonorificPrefix();
        }
        HashMap map = this.d;
        if (map.isEmpty()) {
            map.put(1, xf0.b());
            map.put(2, xf0.b());
            map.put(3, xf0.b());
            map.put(7, xf0.b());
            map.put(4, new xf0(6));
            map.put(5, new xf0(6));
            map.put(6, new xf0(3));
            map.put(8, new xf0(4));
        }
        if (this.l == null) {
            this.l = xf0.c(1, this.b.getString(R$string.autofill_profile_editor_phone_number), this.e, this.g, this.h, null, this.b.getString(R$string.payments_phone_invalid_validation_message), null);
        }
        this.l.n = this.q.getPhoneNumber();
        if (this.m == null) {
            this.m = xf0.c(2, this.b.getString(R$string.autofill_profile_editor_email_address), null, null, null, null, this.b.getString(R$string.payments_email_invalid_validation_message), null);
        }
        this.m.n = this.q.getEmailAddress();
        if (N.M09VlOh_("AutofillAddressProfileSavePromptNicknameSupport") && this.n == null) {
            xf0 xf0VarB2 = xf0.b();
            this.n = xf0VarB2;
            xf0VarB2.m = "Label";
        }
        il ilVar = this.r;
        ilVar.h = new l91(callback2, 6, qaVar);
        ilVar.g = new m91(this, 3, qaVar2, callback);
        for (Map.Entry entry : map.entrySet()) {
            ((xf0) entry.getValue()).n = qa.h(this.q, ((Integer) entry.getKey()).intValue());
        }
        a(this.j.n.toString(), this.q.getLanguageCode(), z);
        this.a.i(this.r);
    }

    /* JADX INFO: compiled from: AddressEditor.java */
    public static class b implements xf0.b {
        public String a;

        @Override // xf0.b
        public final boolean isValid(CharSequence charSequence) {
            if (TextUtils.isEmpty(charSequence)) {
                return true;
            }
            return N.MgAmKYAu(charSequence.toString(), this.a);
        }

        @Override // xf0.b
        public final void a() {
        }
    }
}
