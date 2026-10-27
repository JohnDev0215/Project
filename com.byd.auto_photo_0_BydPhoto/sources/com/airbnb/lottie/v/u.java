package com.airbnb.lottie.v;

import com.airbnb.lottie.t.k.g;

/* JADX INFO: loaded from: classes.dex */
class u {
    static com.airbnb.lottie.t.k.g a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        String strT;
        cVar.l();
        g.a aVar = null;
        boolean zQ = false;
        com.airbnb.lottie.t.j.h hVarK = null;
        com.airbnb.lottie.t.j.d dVarH = null;
        while (cVar.p()) {
            strT = cVar.t();
            strT.hashCode();
            switch (strT) {
                case "o":
                    dVarH = d.h(cVar, dVar);
                    break;
                case "pt":
                    hVarK = d.k(cVar, dVar);
                    break;
                case "inv":
                    zQ = cVar.q();
                    break;
                case "mode":
                    String strU = cVar.u();
                    strU.hashCode();
                    switch (strU) {
                        case "a":
                            break;
                        case "i":
                            dVar.a("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                            aVar = g.a.MASK_MODE_INTERSECT;
                            continue;
                            break;
                        case "s":
                            aVar = g.a.MASK_MODE_SUBTRACT;
                            continue;
                            break;
                        default:
                            com.airbnb.lottie.w.d.b("Unknown mask mode " + strT + ". Defaulting to Add.");
                            break;
                    }
                    aVar = g.a.MASK_MODE_ADD;
                    break;
                default:
                    cVar.A();
                    break;
            }
        }
        cVar.n();
        return new com.airbnb.lottie.t.k.g(aVar, hVarK, dVarH, zQ);
    }
}
