package defpackage;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* JADX INFO: compiled from: ContactDetailsSection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zx extends dh2 {
    public final Context f;
    public final ey g;
    public final ArrayList h;

    public zx(Context context, List list, ey eyVar, s31 s31Var) {
        boolean z;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        super(3);
        this.f = context;
        this.g = eyVar;
        this.h = new ArrayList(list);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        while (true) {
            ArrayList arrayList3 = this.h;
            if (i >= arrayList3.size()) {
                break;
            }
            ra raVarF = f((PersonalDataManager.AutofillProfile) arrayList3.get(i));
            if (raVarF != null) {
                String str7 = raVarF.q;
                ey eyVar2 = this.g;
                eyVar2.getClass();
                if (!TextUtils.isEmpty(str7)) {
                    eyVar2.g.add(str7);
                }
                String str8 = raVarF.r;
                if (eyVar2.k == null) {
                    eyVar2.k = new cy();
                }
                if (eyVar2.k.isValid(str8)) {
                    eyVar2.h.add(str8);
                }
                String str9 = raVarF.s;
                if (eyVar2.l == null) {
                    eyVar2.l = new dy();
                }
                if (eyVar2.l.isValid(str9)) {
                    eyVar2.i.add(str9);
                }
                arrayList.add(raVarF);
            }
            i++;
        }
        Collections.sort(arrayList, new yx());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ra raVar = (ra) arrayList.get(i2);
            int i3 = 0;
            while (true) {
                if (i3 >= arrayList2.size()) {
                    z = true;
                    break;
                }
                ra raVar2 = (ra) arrayList2.get(i3);
                if ((!raVar2.n || (((str5 = raVar2.q) != null || raVar.q == null) && (str5 == null || (str6 = raVar.q) == null || str5.equalsIgnoreCase(str6)))) && (!raVar2.o || (((str3 = raVar2.r) != null || raVar.r == null) && (str3 == null || (str4 = raVar.r) == null || TextUtils.equals(str3, str4)))) && (!raVar2.p || (((str = raVar2.s) != null || raVar.s == null) && (str == null || (str2 = raVar.s) == null || str.equalsIgnoreCase(str2))))) {
                    z = false;
                    break;
                }
                i3++;
            }
            if (z) {
                arrayList2.add(raVar);
            }
            if (arrayList2.size() == 4) {
                break;
            }
        }
        int i4 = (arrayList2.isEmpty() || !((ra) arrayList2.get(0)).a) ? -1 : 0;
        if (s31Var != null) {
            N.MPFG5SwC(s31Var.a, s31Var, 0, arrayList2.size(), i4 != -1);
        }
        ArrayList<lf0> arrayList4 = this.a;
        arrayList4.clear();
        if (arrayList2.isEmpty()) {
            this.c = -1;
        } else {
            this.c = i4;
            arrayList4.addAll(arrayList2);
        }
    }

    public final void e(qa qaVar) {
        ra raVarF;
        if (qaVar == null || (raVarF = f(qaVar.l)) == null) {
            return;
        }
        int i = 0;
        while (true) {
            ArrayList<lf0> arrayList = this.a;
            if (i >= arrayList.size()) {
                arrayList.add(raVarF);
                return;
            } else {
                if (((ra) arrayList.get(i)).k.getGUID().equals(qaVar.l.getGUID())) {
                    arrayList.remove(i);
                    arrayList.add(i, raVarF);
                    return;
                }
                i++;
            }
        }
    }

    public final ra f(PersonalDataManager.AutofillProfile autofillProfile) {
        ey eyVar = this.g;
        boolean z = eyVar.c;
        boolean z2 = eyVar.d;
        boolean z3 = eyVar.e;
        String fullName = (!z || TextUtils.isEmpty(autofillProfile.getFullName())) ? null : autofillProfile.getFullName();
        String phoneNumber = (!z2 || TextUtils.isEmpty(autofillProfile.getPhoneNumber())) ? null : autofillProfile.getPhoneNumber();
        String emailAddress = (!z3 || TextUtils.isEmpty(autofillProfile.getEmailAddress())) ? null : autofillProfile.getEmailAddress();
        if (fullName == null && phoneNumber == null && emailAddress == null) {
            return null;
        }
        return new ra(this.f, autofillProfile, fullName, phoneNumber, emailAddress, eyVar.a(fullName, phoneNumber, emailAddress), z, z2, z3);
    }
}
