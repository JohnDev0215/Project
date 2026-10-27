package com.airbnb.lottie.v;

import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static c.a f923a = c.a.a("nm", "ind", "ks", "hd");

    static com.airbnb.lottie.t.k.o a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        int iS = 0;
        String strU = null;
        com.airbnb.lottie.t.j.h hVarK = null;
        boolean zQ = false;
        while (cVar.p()) {
            int iY = cVar.y(f923a);
            if (iY == 0) {
                strU = cVar.u();
            } else if (iY == 1) {
                iS = cVar.s();
            } else if (iY == 2) {
                hVarK = d.k(cVar, dVar);
            } else if (iY != 3) {
                cVar.A();
            } else {
                zQ = cVar.q();
            }
        }
        return new com.airbnb.lottie.t.k.o(strU, iS, hVarK, zQ);
    }
}
