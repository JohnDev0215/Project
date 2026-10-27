package com.airbnb.lottie.v;

import com.airbnb.lottie.t.k.q;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c.a f929a = c.a.a("s", "e", "o", "nm", "m", "hd");

    static com.airbnb.lottie.t.k.q a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        boolean zQ = false;
        String strU = null;
        q.a aVarA = null;
        com.airbnb.lottie.t.j.b bVarF = null;
        com.airbnb.lottie.t.j.b bVarF2 = null;
        com.airbnb.lottie.t.j.b bVarF3 = null;
        while (cVar.p()) {
            int iY = cVar.y(f929a);
            if (iY == 0) {
                bVarF = d.f(cVar, dVar, false);
            } else if (iY == 1) {
                bVarF2 = d.f(cVar, dVar, false);
            } else if (iY == 2) {
                bVarF3 = d.f(cVar, dVar, false);
            } else if (iY == 3) {
                strU = cVar.u();
            } else if (iY == 4) {
                aVarA = q.a.a(cVar.s());
            } else if (iY != 5) {
                cVar.A();
            } else {
                zQ = cVar.q();
            }
        }
        return new com.airbnb.lottie.t.k.q(strU, aVarA, bVarF, bVarF2, bVarF3, zQ);
    }
}
