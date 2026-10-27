package com.airbnb.lottie.v;

import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c.a f910a = c.a.a("a");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static c.a f911b = c.a.a("fc", "sc", "sw", "t");

    public static com.airbnb.lottie.t.j.k a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        cVar.l();
        com.airbnb.lottie.t.j.k kVarB = null;
        while (cVar.p()) {
            if (cVar.y(f910a) != 0) {
                cVar.z();
                cVar.A();
            } else {
                kVarB = b(cVar, dVar);
            }
        }
        cVar.n();
        return kVarB == null ? new com.airbnb.lottie.t.j.k(null, null, null, null) : kVarB;
    }

    private static com.airbnb.lottie.t.j.k b(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        cVar.l();
        com.airbnb.lottie.t.j.a aVarC = null;
        com.airbnb.lottie.t.j.a aVarC2 = null;
        com.airbnb.lottie.t.j.b bVarE = null;
        com.airbnb.lottie.t.j.b bVarE2 = null;
        while (cVar.p()) {
            int iY = cVar.y(f911b);
            if (iY == 0) {
                aVarC = d.c(cVar, dVar);
            } else if (iY == 1) {
                aVarC2 = d.c(cVar, dVar);
            } else if (iY == 2) {
                bVarE = d.e(cVar, dVar);
            } else if (iY != 3) {
                cVar.z();
                cVar.A();
            } else {
                bVarE2 = d.e(cVar, dVar);
            }
        }
        cVar.n();
        return new com.airbnb.lottie.t.j.k(aVarC, aVarC2, bVarE, bVarE2);
    }
}
