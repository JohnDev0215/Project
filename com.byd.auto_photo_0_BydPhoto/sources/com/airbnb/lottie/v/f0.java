package com.airbnb.lottie.v;

import com.airbnb.lottie.v.k0.c;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c.a f921a = c.a.a("nm", "hd", "it");

    static com.airbnb.lottie.t.k.n a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        ArrayList arrayList = new ArrayList();
        String strU = null;
        boolean zQ = false;
        while (cVar.p()) {
            int iY = cVar.y(f921a);
            if (iY == 0) {
                strU = cVar.u();
            } else if (iY == 1) {
                zQ = cVar.q();
            } else if (iY != 2) {
                cVar.A();
            } else {
                cVar.k();
                while (cVar.p()) {
                    com.airbnb.lottie.t.k.b bVarA = g.a(cVar, dVar);
                    if (bVarA != null) {
                        arrayList.add(bVarA);
                    }
                }
                cVar.m();
            }
        }
        return new com.airbnb.lottie.t.k.n(strU, arrayList, zQ);
    }
}
