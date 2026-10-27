package defpackage;

import J.N;
import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* JADX INFO: compiled from: AutofillAddress.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qa extends lf0 {
    public static Pattern p;
    public final Context k;
    public PersonalDataManager.AutofillProfile l;
    public String m;
    public String n;
    public final int o;

    public qa() {
        throw null;
    }

    public qa(Context context, PersonalDataManager.AutofillProfile autofillProfile) {
        super(autofillProfile.getGUID(), autofillProfile.getFullName(), autofillProfile.q, autofillProfile.getPhoneNumber(), null);
        this.k = context;
        this.l = autofillProfile;
        this.c = true;
        this.o = 0;
        f();
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    public static int e(PersonalDataManager.AutofillProfile autofillProfile, int i) {
        boolean zIsEmpty = TextUtils.isEmpty(autofillProfile.getFullName());
        ku2 ku2VarT = ku2.t();
        ?? r0 = zIsEmpty;
        if (i != 1) {
            try {
                r0 = zIsEmpty;
                if (!PhoneNumberUtils.isGlobalPhoneNumber(PhoneNumberUtils.stripSeparators(autofillProfile.getPhoneNumber().toString()))) {
                    r0 = (zIsEmpty ? 1 : 0) | 2;
                }
            } catch (Throwable th) {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        ku2VarT.close();
        String strG = g(autofillProfile);
        ArrayList arrayList = new ArrayList();
        N.MdAohWFG(strG, arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            if (iIntValue != 8 && iIntValue != 0 && TextUtils.isEmpty(h(autofillProfile, iIntValue))) {
                return r0 | 8;
            }
        }
        return r0;
    }

    public static String g(PersonalDataManager.AutofillProfile autofillProfile) {
        if (p == null) {
            p = Pattern.compile("^[A-Z]{2}$");
        }
        return (autofillProfile == null || TextUtils.isEmpty(autofillProfile.getCountryCode()) || !p.matcher(autofillProfile.getCountryCode()).matches()) ? Locale.getDefault().getCountry() : autofillProfile.getCountryCode();
    }

    public static String h(PersonalDataManager.AutofillProfile autofillProfile, int i) {
        switch (i) {
            case 0:
                return autofillProfile.getCountryCode();
            case 1:
                return autofillProfile.getRegion();
            case 2:
                return autofillProfile.getLocality();
            case 3:
                return autofillProfile.getDependentLocality();
            case 4:
                return autofillProfile.getSortingCode();
            case 5:
                return autofillProfile.getPostalCode();
            case 6:
                return autofillProfile.getStreetAddress();
            case 7:
                return autofillProfile.getCompanyName();
            case 8:
                return autofillProfile.getFullName();
            default:
                return null;
        }
    }

    public final void f() {
        int i;
        int i2;
        PersonalDataManager.AutofillProfile autofillProfile = this.l;
        int i3 = this.o;
        int iE = e(autofillProfile, i3);
        if (iE == 0) {
            i = R$string.payments_edit_address;
            i2 = 0;
        } else if (iE == 1) {
            i2 = R$string.payments_recipient_required;
            i = R$string.payments_add_recipient;
        } else if (iE == 2) {
            i2 = R$string.payments_phone_number_required;
            i = R$string.payments_add_phone_number;
        } else if (iE != 8) {
            i2 = R$string.payments_more_information_required;
            i = R$string.payments_add_more_information;
        } else {
            i2 = R$string.payments_invalid_address;
            i = R$string.payments_add_valid_address;
        }
        Pair pair = new Pair(Integer.valueOf(i2), Integer.valueOf(i));
        int iIntValue = ((Integer) pair.first).intValue();
        Context context = this.k;
        this.d = iIntValue == 0 ? null : context.getString(((Integer) pair.first).intValue());
        this.e = ((Integer) pair.second).intValue() != 0 ? context.getString(((Integer) pair.second).intValue()) : null;
        this.a = this.d == null;
        int iE2 = e(this.l, i3);
        int i4 = (iE2 & 1) == 0 ? 1 : 0;
        if ((iE2 & 2) == 0) {
            i4++;
        }
        if ((iE2 & 8) == 0) {
            i4 += 10;
        }
        this.b = i4;
    }

    public final void i() {
        if (this.m == null) {
            PersonalDataManager personalDataManagerC = PersonalDataManager.c();
            this.m = N.MGJNOClH(personalDataManagerC.a, personalDataManagerC, this.l);
        }
        PersonalDataManager.AutofillProfile autofillProfile = this.l;
        String str = this.m;
        autofillProfile.q = str;
        this.i[1] = str;
    }

    public final ky1 j() {
        ky1 ky1Var = new ky1();
        ky1Var.b = g(this.l);
        ky1Var.c = this.l.getStreetAddress().split("\n");
        ky1Var.d = this.l.getRegion();
        ky1Var.e = this.l.getLocality();
        ky1Var.f = this.l.getDependentLocality();
        ky1Var.g = this.l.getPostalCode();
        ky1Var.h = this.l.getSortingCode();
        ky1Var.i = this.l.getCompanyName();
        ky1Var.j = this.l.getFullName();
        ky1Var.k = this.l.getPhoneNumber();
        return ky1Var;
    }

    public final void k(PersonalDataManager.AutofillProfile autofillProfile) {
        this.m = null;
        this.n = null;
        this.l = autofillProfile;
        String guid = autofillProfile.getGUID();
        String fullName = this.l.getFullName();
        PersonalDataManager.AutofillProfile autofillProfile2 = this.l;
        d(guid, fullName, autofillProfile2.q, autofillProfile2.getPhoneNumber());
        f();
    }
}
