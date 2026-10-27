package org.chromium.chrome.browser.suggestions.mostvisited;

import J.N;
import defpackage.aa3;
import defpackage.ao2;
import defpackage.ni1;
import defpackage.w93;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class MostVisitedSitesBridge {
    public long a;
    public ni1 b;

    public MostVisitedSitesBridge(Profile profile) {
        this.a = N.M8pqI3Tk(this, profile);
    }

    @CalledByNative
    public final void onIconMadeAvailable(GURL gurl) {
        if (this.a != 0) {
            aa3 aa3Var = (aa3) this.b;
            aa3Var.getClass();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < aa3Var.h.size(); i++) {
                for (w93 w93Var : aa3Var.h.valueAt(i)) {
                    if (w93Var.a.b.equals(gurl)) {
                        arrayList.add(w93Var);
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                aa3Var.e.d((w93) it.next(), aa3Var.m);
            }
        }
    }

    @CalledByNative
    public final void onURLsAvailable(String[] strArr, GURL[] gurlArr, int[] iArr, int[] iArr2, int[] iArr3) {
        if (this.a == 0) {
            return;
        }
        ArrayList<ao2> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(strArr.length);
        boolean z = false;
        for (int i = 0; i < strArr.length; i++) {
            arrayList2.add(new ao2(iArr2[i], iArr3[i], iArr[i], strArr[i], gurlArr[i]));
        }
        arrayList.addAll(arrayList2);
        aa3 aa3Var = (aa3) this.b;
        boolean z2 = true;
        boolean z3 = aa3Var.j != null;
        boolean z4 = aa3Var.k == null;
        aa3Var.i = new ArrayList();
        for (ao2 ao2Var : arrayList) {
            aa3Var.i.add(ao2Var);
            if (ao2Var.e == 1) {
                GURL gurl = aa3Var.j;
                GURL gurl2 = ao2Var.b;
                if (gurl2.equals(gurl)) {
                    z3 = false;
                }
                if (gurl2.equals(aa3Var.k)) {
                    z4 = true;
                }
            }
        }
        if (aa3Var.j != null && z3) {
            aa3Var.j = null;
            z = true;
        }
        if (aa3Var.k == null || !z4) {
            z2 = z;
        } else {
            aa3Var.k = null;
        }
        if (aa3Var.l && aa3Var.a.isVisible() && !z2) {
            return;
        }
        aa3Var.c();
    }
}
