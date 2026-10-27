package defpackage;

import J.N;
import android.app.ProgressDialog;
import android.os.Handler;
import android.text.TextUtils;
import defpackage.xf0;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillProfileBridge;

/* JADX INFO: compiled from: AddressEditor.java */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class c4 extends mf0<qa> implements PersonalDataManager.GetSubKeysRequestDelegate {
    public final boolean f;
    public AutofillProfileBridge i;
    public xf0 j;
    public xf0 k;
    public ArrayList l;
    public boolean m;
    public String n;
    public Runnable o;
    public PersonalDataManager.AutofillProfile p;
    public il q;
    public ProgressDialog r;
    public d4 s;
    public final Handler c = new Handler();
    public final HashMap d = new HashMap();
    public final HashSet e = new HashSet();
    public final v02 g = new v02();
    public final a h = new a();

    public c4(boolean z) {
        this.f = z;
    }

    public static String b(CharSequence charSequence) {
        return charSequence == null ? "" : charSequence.toString();
    }

    public static void e(PersonalDataManager.AutofillProfile autofillProfile, int i, CharSequence charSequence) {
        switch (i) {
            case 0:
                autofillProfile.n = new PersonalDataManager.b(b(charSequence), 4);
                break;
            case 1:
                autofillProfile.i = new PersonalDataManager.b(b(charSequence), 4);
                break;
            case 2:
                autofillProfile.j = new PersonalDataManager.b(b(charSequence), 4);
                break;
            case 3:
                autofillProfile.k = new PersonalDataManager.b(b(charSequence), 4);
                break;
            case 4:
                autofillProfile.m = new PersonalDataManager.b(b(charSequence), 4);
                break;
            case 5:
                autofillProfile.l = new PersonalDataManager.b(b(charSequence), 4);
                break;
            case 6:
                autofillProfile.h = new PersonalDataManager.b(b(charSequence), 4);
                break;
            case 7:
                autofillProfile.g = new PersonalDataManager.b(b(charSequence), 4);
                break;
            case 8:
                autofillProfile.f = new PersonalDataManager.b(b(charSequence), 4);
                break;
        }
    }

    public final void a(String str, String str2) {
        this.l = this.i.a(0, str, str2);
        this.j.k = c(0);
        this.q.a(this.j);
        int i = 0;
        while (true) {
            if (i >= this.l.size()) {
                break;
            }
            AutofillProfileBridge.a aVar = (AutofillProfileBridge.a) this.l.get(i);
            xf0 xf0Var = (xf0) this.d.get(Integer.valueOf(aVar.a));
            xf0Var.m = aVar.b;
            boolean z = aVar.d;
            int i2 = aVar.a;
            xf0Var.q = z || i2 == 2 || i2 == 3;
            if (aVar.c || i2 == 8) {
                string = this.b.getString(R$string.pref_edit_dialog_field_required_validation_message);
            }
            xf0Var.i = string;
            xf0Var.k = c(i2);
            this.q.a(xf0Var);
            i++;
        }
        xf0 xf0Var2 = this.k;
        d4 d4Var = this.s;
        xf0Var2.k = d4Var != null ? d4Var.g : null;
        this.q.a(xf0Var2);
    }

    public final String c(int i) {
        d4 d4Var = this.s;
        if (d4Var == null) {
            return null;
        }
        switch (i) {
            case 0:
                return d4Var.d;
            case 1:
                return d4Var.j;
            case 2:
                return d4Var.c;
            case 3:
                return d4Var.e;
            case 4:
                return d4Var.k;
            case 5:
                return d4Var.h;
            case 6:
                return d4Var.b;
            case 7:
                return d4Var.f;
            case 8:
                return d4Var.i;
            default:
                return null;
        }
    }

    public final void d(String str) {
        this.m = false;
        this.a.A = false;
        if (((long) 5) * 1000 == 0) {
            onSubKeysReceived(null, null);
            return;
        }
        PersonalDataManager personalDataManagerC = PersonalDataManager.c();
        personalDataManagerC.getClass();
        Object obj = ThreadUtils.a;
        N.M4kIHYDl(personalDataManagerC.a, personalDataManagerC, str);
        PersonalDataManager personalDataManagerC2 = PersonalDataManager.c();
        personalDataManagerC2.getClass();
        N.M8TAYWBI(personalDataManagerC2.a, personalDataManagerC2, str, 5, this);
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.GetSubKeysRequestDelegate
    public final void onSubKeysReceived(String[] strArr, String[] strArr2) {
        xf0 xf0Var;
        if (this.m) {
            return;
        }
        this.m = true;
        if (this.a.A) {
            return;
        }
        HashMap map = this.d;
        if (strArr == null || strArr2 == null || strArr.length == 0 || strArr.length != strArr2.length) {
            xf0Var = new xf0(5);
        } else {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < strArr.length; i++) {
                arrayList.add(new xf0.a(strArr[i], strArr2[i]));
            }
            Collator collator = Collator.getInstance(Locale.getDefault());
            collator.setStrength(0);
            Collections.sort(arrayList, new fb(collator));
            xf0Var = xf0.a(null, arrayList, this.b.getString(R$string.select));
        }
        map.put(1, xf0Var);
        if (this.n != null) {
            ProgressDialog progressDialog = this.r;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.r.dismiss();
            }
            this.r = null;
            a(this.n, Locale.getDefault().getLanguage());
            this.c.post(this.o);
            return;
        }
        for (Map.Entry entry : map.entrySet()) {
            ((xf0) entry.getValue()).n = qa.h(this.p, ((Integer) entry.getKey()).intValue());
        }
        a(this.j.n.toString(), this.p.getLanguageCode());
        this.a.i(this.q);
    }

    /* JADX INFO: compiled from: AddressEditor.java */
    public static class a implements xf0.b {
        public String a;

        @Override // xf0.b
        public final boolean isValid(CharSequence charSequence) {
            return !TextUtils.isEmpty(charSequence) && N.MgAmKYAu(charSequence.toString(), this.a);
        }

        @Override // xf0.b
        public final void a() {
        }
    }
}
