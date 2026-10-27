package com.airbnb.lottie.v;

import com.airbnb.lottie.v.k0.c;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c.a f930a = c.a.a("ch", "size", "w", "style", "fFamily", "data");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.a f931b = c.a.a("shapes");

    static com.airbnb.lottie.t.d a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        ArrayList arrayList = new ArrayList();
        cVar.l();
        String strU = null;
        String strU2 = null;
        double dR = 0.0d;
        double dR2 = 0.0d;
        char cCharAt = 0;
        while (cVar.p()) {
            int iY = cVar.y(f930a);
            if (iY == 0) {
                cCharAt = cVar.u().charAt(0);
            } else if (iY == 1) {
                dR = cVar.r();
            } else if (iY == 2) {
                dR2 = cVar.r();
            } else if (iY == 3) {
                strU = cVar.u();
            } else if (iY == 4) {
                strU2 = cVar.u();
            } else if (iY != 5) {
                cVar.z();
                cVar.A();
            } else {
                cVar.l();
                while (cVar.p()) {
                    if (cVar.y(f931b) != 0) {
                        cVar.z();
                        cVar.A();
                    } else {
                        cVar.k();
                        while (cVar.p()) {
                            arrayList.add((com.airbnb.lottie.t.k.n) g.a(cVar, dVar));
                        }
                        cVar.m();
                    }
                }
                cVar.n();
            }
        }
        cVar.n();
        return new com.airbnb.lottie.t.d(arrayList, cCharAt, dR, dR2, strU, strU2);
    }
}
