package com.airbnb.lottie.v;

import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c.a f912a = c.a.a("nm", "c", "o", "tr", "hd");

    static com.airbnb.lottie.t.k.k a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        boolean zQ = false;
        String strU = null;
        com.airbnb.lottie.t.j.b bVarF = null;
        com.airbnb.lottie.t.j.b bVarF2 = null;
        com.airbnb.lottie.t.j.l lVarG = null;
        while (cVar.p()) {
            int iY = cVar.y(f912a);
            if (iY == 0) {
                strU = cVar.u();
            } else if (iY == 1) {
                bVarF = d.f(cVar, dVar, false);
            } else if (iY == 2) {
                bVarF2 = d.f(cVar, dVar, false);
            } else if (iY == 3) {
                lVarG = c.g(cVar, dVar);
            } else if (iY != 4) {
                cVar.A();
            } else {
                zQ = cVar.q();
            }
        }
        return new com.airbnb.lottie.t.k.k(strU, bVarF, bVarF2, lVarG, zQ);
    }
}
