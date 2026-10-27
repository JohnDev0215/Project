package defpackage;

import android.content.Context;
import android.text.TextUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* JADX INFO: compiled from: AutofillContact.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ra extends lf0 {
    public final PersonalDataManager.AutofillProfile k;
    public final Context l;
    public int m;
    public final boolean n;
    public final boolean o;
    public final boolean p;
    public String q;
    public String r;
    public String s;

    public ra(Context context, PersonalDataManager.AutofillProfile autofillProfile, String str, String str2, String str3, int i, boolean z, boolean z2, boolean z3) {
        super(autofillProfile.getGUID(), null, null, null, null);
        this.l = context;
        this.k = autofillProfile;
        this.n = z;
        this.o = z2;
        this.p = z3;
        this.c = true;
        f(autofillProfile.getGUID(), str, str2, str3);
        g(i);
    }

    @Override // defpackage.lf0
    public final boolean c() {
        return this.a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int e() {
        /*
            r2 = this;
            boolean r0 = r2.n
            if (r0 == 0) goto Lb
            int r0 = r2.m
            r1 = 1
            r0 = r0 & r1
            if (r0 != 0) goto Lb
            goto Lc
        Lb:
            r1 = 0
        Lc:
            boolean r0 = r2.o
            if (r0 == 0) goto L18
            int r0 = r2.m
            r0 = r0 & 2
            if (r0 != 0) goto L18
            int r1 = r1 + 1
        L18:
            boolean r0 = r2.p
            if (r0 == 0) goto L24
            int r2 = r2.m
            r2 = r2 & 4
            if (r2 != 0) goto L24
            int r1 = r1 + 1
        L24:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ra.e():int");
    }

    public final void f(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str2)) {
            str2 = null;
        }
        this.q = str2;
        if (TextUtils.isEmpty(str3)) {
            str3 = null;
        }
        this.r = str3;
        if (TextUtils.isEmpty(str4)) {
            str4 = null;
        }
        this.s = str4;
        String str5 = this.q;
        if (str5 != null) {
            String str6 = this.r;
            d(str, str5, str6 == null ? str4 : str6, str6 != null ? str4 : null);
            return;
        }
        String str7 = this.r;
        String str8 = str7 == null ? str4 : str7;
        if (str7 == null) {
            str4 = null;
        }
        d(str, str8, str4, null);
    }

    public final void g(int i) {
        this.m = i;
        this.a = i == 0;
        Context context = this.l;
        if (i == 0) {
            this.d = null;
            this.e = context.getString(R$string.payments_edit_contact_details_label);
            return;
        }
        if (i == 1) {
            this.d = context.getString(R$string.payments_name_required);
            this.e = context.getString(R$string.payments_add_name);
        } else if (i == 2) {
            this.d = context.getString(R$string.payments_phone_number_required);
            this.e = context.getString(R$string.payments_add_phone_number);
        } else if (i != 4) {
            this.d = context.getString(R$string.payments_more_information_required);
            this.e = context.getString(R$string.payments_add_more_information);
        } else {
            this.d = context.getString(R$string.payments_email_required);
            this.e = context.getString(R$string.payments_add_email);
        }
    }
}
