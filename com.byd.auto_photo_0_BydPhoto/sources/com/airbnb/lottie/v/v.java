package com.airbnb.lottie.v;

import com.airbnb.lottie.t.k.h;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c.a f960a = c.a.a("nm", "mm", "hd");

    static com.airbnb.lottie.t.k.h a(com.airbnb.lottie.v.k0.c cVar) {
        String strU = null;
        boolean zQ = false;
        h.a aVarA = null;
        while (cVar.p()) {
            int iY = cVar.y(f960a);
            if (iY == 0) {
                strU = cVar.u();
            } else if (iY == 1) {
                aVarA = h.a.a(cVar.s());
            } else if (iY != 2) {
                cVar.z();
                cVar.A();
            } else {
                zQ = cVar.q();
            }
        }
        return new com.airbnb.lottie.t.k.h(strU, aVarA, zQ);
    }
}
