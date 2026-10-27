package com.airbnb.lottie.v;

import android.graphics.PointF;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c.a f909a = c.a.a("nm", "p", "s", "r", "hd");

    static com.airbnb.lottie.t.k.j a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        String strU = null;
        com.airbnb.lottie.t.j.m<PointF, PointF> mVarB = null;
        com.airbnb.lottie.t.j.f fVarI = null;
        com.airbnb.lottie.t.j.b bVarE = null;
        boolean zQ = false;
        while (cVar.p()) {
            int iY = cVar.y(f909a);
            if (iY == 0) {
                strU = cVar.u();
            } else if (iY == 1) {
                mVarB = a.b(cVar, dVar);
            } else if (iY == 2) {
                fVarI = d.i(cVar, dVar);
            } else if (iY == 3) {
                bVarE = d.e(cVar, dVar);
            } else if (iY != 4) {
                cVar.A();
            } else {
                zQ = cVar.q();
            }
        }
        return new com.airbnb.lottie.t.k.j(strU, mVarB, fVarI, bVarE, zQ);
    }
}
