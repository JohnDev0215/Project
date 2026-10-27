package com.airbnb.lottie.v;

import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c.a f932a = c.a.a("fFamily", "fName", "fStyle", "ascent");

    static com.airbnb.lottie.t.c a(com.airbnb.lottie.v.k0.c cVar) {
        cVar.l();
        String strU = null;
        String strU2 = null;
        float fR = 0.0f;
        String strU3 = null;
        while (cVar.p()) {
            int iY = cVar.y(f932a);
            if (iY == 0) {
                strU = cVar.u();
            } else if (iY == 1) {
                strU3 = cVar.u();
            } else if (iY == 2) {
                strU2 = cVar.u();
            } else if (iY != 3) {
                cVar.z();
                cVar.A();
            } else {
                fR = (float) cVar.r();
            }
        }
        cVar.n();
        return new com.airbnb.lottie.t.c(strU, strU3, strU2, fR);
    }
}
