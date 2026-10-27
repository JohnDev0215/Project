package com.airbnb.lottie.v;

import android.graphics.Path;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c.a f919a = c.a.a("nm", "c", "o", "fillEnabled", "r", "hd");

    static com.airbnb.lottie.t.k.m a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        boolean zQ = false;
        boolean zQ2 = false;
        int iS = 1;
        String strU = null;
        com.airbnb.lottie.t.j.a aVarC = null;
        com.airbnb.lottie.t.j.d dVarH = null;
        while (cVar.p()) {
            int iY = cVar.y(f919a);
            if (iY == 0) {
                strU = cVar.u();
            } else if (iY == 1) {
                aVarC = d.c(cVar, dVar);
            } else if (iY == 2) {
                dVarH = d.h(cVar, dVar);
            } else if (iY == 3) {
                zQ = cVar.q();
            } else if (iY == 4) {
                iS = cVar.s();
            } else if (iY != 5) {
                cVar.z();
                cVar.A();
            } else {
                zQ2 = cVar.q();
            }
        }
        return new com.airbnb.lottie.t.k.m(strU, zQ, iS == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD, aVarC, dVarH, zQ2);
    }
}
