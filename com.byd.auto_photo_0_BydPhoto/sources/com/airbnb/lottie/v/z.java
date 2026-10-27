package com.airbnb.lottie.v;

import android.graphics.PointF;
import com.airbnb.lottie.t.k.i;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c.a f963a = c.a.a("nm", "sy", "pt", "p", "r", "or", "os", "ir", "is", "hd");

    static com.airbnb.lottie.t.k.i a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        boolean zQ = false;
        String strU = null;
        i.a aVarA = null;
        com.airbnb.lottie.t.j.b bVarF = null;
        com.airbnb.lottie.t.j.m<PointF, PointF> mVarB = null;
        com.airbnb.lottie.t.j.b bVarF2 = null;
        com.airbnb.lottie.t.j.b bVarE = null;
        com.airbnb.lottie.t.j.b bVarE2 = null;
        com.airbnb.lottie.t.j.b bVarF3 = null;
        com.airbnb.lottie.t.j.b bVarF4 = null;
        while (cVar.p()) {
            switch (cVar.y(f963a)) {
                case 0:
                    strU = cVar.u();
                    break;
                case 1:
                    aVarA = i.a.a(cVar.s());
                    break;
                case 2:
                    bVarF = d.f(cVar, dVar, false);
                    break;
                case 3:
                    mVarB = a.b(cVar, dVar);
                    break;
                case 4:
                    bVarF2 = d.f(cVar, dVar, false);
                    break;
                case 5:
                    bVarE2 = d.e(cVar, dVar);
                    break;
                case 6:
                    bVarF4 = d.f(cVar, dVar, false);
                    break;
                case 7:
                    bVarE = d.e(cVar, dVar);
                    break;
                case 8:
                    bVarF3 = d.f(cVar, dVar, false);
                    break;
                case 9:
                    zQ = cVar.q();
                    break;
                default:
                    cVar.z();
                    cVar.A();
                    break;
            }
        }
        return new com.airbnb.lottie.t.k.i(strU, aVarA, bVarF, mVarB, bVarF2, bVarE, bVarE2, bVarF3, bVarF4, zQ);
    }
}
