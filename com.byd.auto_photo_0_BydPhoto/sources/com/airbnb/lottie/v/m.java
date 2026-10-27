package com.airbnb.lottie.v;

import android.graphics.Path;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c.a f942a = c.a.a("nm", "g", "o", "t", "s", "e", "r", "hd");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.a f943b = c.a.a("p", "k");

    static com.airbnb.lottie.t.k.d a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        Path.FillType fillType = Path.FillType.WINDING;
        String strU = null;
        com.airbnb.lottie.t.k.f fVar = null;
        com.airbnb.lottie.t.j.c cVarG = null;
        com.airbnb.lottie.t.j.d dVarH = null;
        com.airbnb.lottie.t.j.f fVarI = null;
        com.airbnb.lottie.t.j.f fVarI2 = null;
        boolean zQ = false;
        while (cVar.p()) {
            switch (cVar.y(f942a)) {
                case 0:
                    strU = cVar.u();
                    break;
                case 1:
                    int iS = -1;
                    cVar.l();
                    while (cVar.p()) {
                        int iY = cVar.y(f943b);
                        if (iY == 0) {
                            iS = cVar.s();
                        } else if (iY != 1) {
                            cVar.z();
                            cVar.A();
                        } else {
                            cVarG = d.g(cVar, dVar, iS);
                        }
                    }
                    cVar.n();
                    break;
                case 2:
                    dVarH = d.h(cVar, dVar);
                    break;
                case 3:
                    fVar = cVar.s() == 1 ? com.airbnb.lottie.t.k.f.LINEAR : com.airbnb.lottie.t.k.f.RADIAL;
                    break;
                case 4:
                    fVarI = d.i(cVar, dVar);
                    break;
                case 5:
                    fVarI2 = d.i(cVar, dVar);
                    break;
                case 6:
                    fillType = cVar.s() == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
                    break;
                case 7:
                    zQ = cVar.q();
                    break;
                default:
                    cVar.z();
                    cVar.A();
                    break;
            }
        }
        return new com.airbnb.lottie.t.k.d(strU, fVar, fillType, cVarG, dVarH, fVarI, fVarI2, null, null, zQ);
    }
}
