package com.airbnb.lottie.v;

import android.graphics.PointF;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c.a f918a = c.a.a("nm", "p", "s", "hd", "d");

    static com.airbnb.lottie.t.k.a a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar, int i) {
        boolean z = i == 3;
        boolean zQ = false;
        String strU = null;
        com.airbnb.lottie.t.j.m<PointF, PointF> mVarB = null;
        com.airbnb.lottie.t.j.f fVarI = null;
        while (cVar.p()) {
            int iY = cVar.y(f918a);
            if (iY == 0) {
                strU = cVar.u();
            } else if (iY == 1) {
                mVarB = a.b(cVar, dVar);
            } else if (iY == 2) {
                fVarI = d.i(cVar, dVar);
            } else if (iY == 3) {
                zQ = cVar.q();
            } else if (iY != 4) {
                cVar.z();
                cVar.A();
            } else {
                z = cVar.s() == 3;
            }
        }
        return new com.airbnb.lottie.t.k.a(strU, mVarB, fVarI, z, zQ);
    }
}
