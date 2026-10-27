package com.airbnb.lottie.v;

import com.airbnb.lottie.t.k.p;
import com.airbnb.lottie.v.k0.c;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c.a f944a = c.a.a("nm", "g", "o", "t", "s", "e", "w", "lc", "lj", "ml", "hd", "d");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.a f945b = c.a.a("p", "k");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c.a f946c = c.a.a("n", "v");

    static com.airbnb.lottie.t.k.e a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        ArrayList arrayList = new ArrayList();
        float fR = 0.0f;
        String strU = null;
        com.airbnb.lottie.t.k.f fVar = null;
        com.airbnb.lottie.t.j.c cVarG = null;
        com.airbnb.lottie.t.j.d dVarH = null;
        com.airbnb.lottie.t.j.f fVarI = null;
        com.airbnb.lottie.t.j.f fVarI2 = null;
        com.airbnb.lottie.t.j.b bVarE = null;
        p.b bVar = null;
        p.c cVar2 = null;
        com.airbnb.lottie.t.j.b bVar2 = null;
        boolean zQ = false;
        while (cVar.p()) {
            switch (cVar.y(f944a)) {
                case 0:
                    strU = cVar.u();
                    break;
                case 1:
                    int iS = -1;
                    cVar.l();
                    while (cVar.p()) {
                        int iY = cVar.y(f945b);
                        com.airbnb.lottie.t.j.c cVar3 = cVarG;
                        if (iY == 0) {
                            iS = cVar.s();
                        } else if (iY != 1) {
                            cVar.z();
                            cVar.A();
                        } else {
                            cVarG = d.g(cVar, dVar, iS);
                        }
                        cVarG = cVar3;
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
                    bVarE = d.e(cVar, dVar);
                    break;
                case 7:
                    bVar = p.b.values()[cVar.s() - 1];
                    break;
                case 8:
                    cVar2 = p.c.values()[cVar.s() - 1];
                    break;
                case 9:
                    fR = (float) cVar.r();
                    break;
                case 10:
                    zQ = cVar.q();
                    break;
                case 11:
                    cVar.k();
                    while (cVar.p()) {
                        cVar.l();
                        String strU2 = null;
                        com.airbnb.lottie.t.j.b bVarE2 = null;
                        while (cVar.p()) {
                            int iY2 = cVar.y(f946c);
                            com.airbnb.lottie.t.j.b bVar3 = bVar2;
                            if (iY2 != 0) {
                                if (iY2 != 1) {
                                    cVar.z();
                                    cVar.A();
                                } else {
                                    bVarE2 = d.e(cVar, dVar);
                                }
                                bVar2 = bVar3;
                            } else {
                                strU2 = cVar.u();
                            }
                        }
                        com.airbnb.lottie.t.j.b bVar4 = bVar2;
                        cVar.n();
                        if (strU2.equals("o")) {
                            bVar2 = bVarE2;
                        } else {
                            if (strU2.equals("d") || strU2.equals("g")) {
                                dVar.t(true);
                                arrayList.add(bVarE2);
                            }
                            bVar2 = bVar4;
                        }
                    }
                    com.airbnb.lottie.t.j.b bVar5 = bVar2;
                    cVar.m();
                    if (arrayList.size() == 1) {
                        arrayList.add(arrayList.get(0));
                    }
                    bVar2 = bVar5;
                    break;
                default:
                    cVar.z();
                    cVar.A();
                    break;
            }
        }
        return new com.airbnb.lottie.t.k.e(strU, fVar, cVarG, dVarH, fVarI, fVarI2, bVarE, bVar, cVar2, fR, arrayList, bVar2, zQ);
    }
}
