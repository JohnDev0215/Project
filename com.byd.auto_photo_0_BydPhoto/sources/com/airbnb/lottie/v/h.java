package com.airbnb.lottie.v;

import com.airbnb.lottie.t.b;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
public class h implements j0<com.airbnb.lottie.t.b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f924a = new h();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.a f925b = c.a.a("t", "f", "s", "j", "tr", "lh", "ls", "fc", "sc", "sw", "of");

    private h() {
    }

    @Override // com.airbnb.lottie.v.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.t.b a(com.airbnb.lottie.v.k0.c cVar, float f) {
        b.a aVar = b.a.CENTER;
        cVar.l();
        b.a aVar2 = aVar;
        String strU = null;
        String strU2 = null;
        int iS = 0;
        int iD = 0;
        int iD2 = 0;
        double dR = 0.0d;
        double dR2 = 0.0d;
        double dR3 = 0.0d;
        double dR4 = 0.0d;
        boolean zQ = true;
        while (cVar.p()) {
            switch (cVar.y(f925b)) {
                case 0:
                    strU = cVar.u();
                    break;
                case 1:
                    strU2 = cVar.u();
                    break;
                case 2:
                    dR = cVar.r();
                    break;
                case 3:
                    int iS2 = cVar.s();
                    aVar2 = b.a.CENTER;
                    if (iS2 <= aVar2.ordinal() && iS2 >= 0) {
                        aVar2 = b.a.values()[iS2];
                    }
                    break;
                case 4:
                    iS = cVar.s();
                    break;
                case 5:
                    dR2 = cVar.r();
                    break;
                case 6:
                    dR3 = cVar.r();
                    break;
                case 7:
                    iD = p.d(cVar);
                    break;
                case 8:
                    iD2 = p.d(cVar);
                    break;
                case 9:
                    dR4 = cVar.r();
                    break;
                case 10:
                    zQ = cVar.q();
                    break;
                default:
                    cVar.z();
                    cVar.A();
                    break;
            }
        }
        cVar.n();
        return new com.airbnb.lottie.t.b(strU, strU2, dR, aVar2, iS, dR2, dR3, iD, iD2, dR4, zQ);
    }
}
