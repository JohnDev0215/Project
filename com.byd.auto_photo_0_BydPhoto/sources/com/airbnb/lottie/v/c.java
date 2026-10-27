package com.airbnb.lottie.v;

import android.graphics.PointF;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c.a f913a = c.a.a("a", "p", "s", "rz", "r", "o", "so", "eo", "sk", "sa");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static c.a f914b = c.a.a("k");

    private static boolean a(com.airbnb.lottie.t.j.e eVar) {
        return eVar == null || (eVar.c() && eVar.b().get(0).f976b.equals(0.0f, 0.0f));
    }

    private static boolean b(com.airbnb.lottie.t.j.m<PointF, PointF> mVar) {
        return mVar == null || (!(mVar instanceof com.airbnb.lottie.t.j.i) && mVar.c() && mVar.b().get(0).f976b.equals(0.0f, 0.0f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean c(com.airbnb.lottie.t.j.b bVar) {
        return bVar == null || (bVar.c() && ((Float) ((com.airbnb.lottie.x.a) bVar.b().get(0)).f976b).floatValue() == 0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean d(com.airbnb.lottie.t.j.g gVar) {
        return gVar == null || (gVar.c() && ((com.airbnb.lottie.x.d) ((com.airbnb.lottie.x.a) gVar.b().get(0)).f976b).a(1.0f, 1.0f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean e(com.airbnb.lottie.t.j.b bVar) {
        return bVar == null || (bVar.c() && ((Float) ((com.airbnb.lottie.x.a) bVar.b().get(0)).f976b).floatValue() == 0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean f(com.airbnb.lottie.t.j.b bVar) {
        return bVar == null || (bVar.c() && ((Float) ((com.airbnb.lottie.x.a) bVar.b().get(0)).f976b).floatValue() == 0.0f);
    }

    public static com.airbnb.lottie.t.j.l g(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        boolean z;
        boolean z2 = false;
        boolean z3 = cVar.w() == c.b.BEGIN_OBJECT;
        if (z3) {
            cVar.l();
        }
        com.airbnb.lottie.t.j.b bVar = null;
        com.airbnb.lottie.t.j.e eVarA = null;
        com.airbnb.lottie.t.j.m<PointF, PointF> mVarB = null;
        com.airbnb.lottie.t.j.g gVarJ = null;
        com.airbnb.lottie.t.j.b bVarF = null;
        com.airbnb.lottie.t.j.b bVarF2 = null;
        com.airbnb.lottie.t.j.d dVarH = null;
        com.airbnb.lottie.t.j.b bVarF3 = null;
        com.airbnb.lottie.t.j.b bVarF4 = null;
        while (cVar.p()) {
            switch (cVar.y(f913a)) {
                case 0:
                    boolean z4 = z2;
                    cVar.l();
                    while (cVar.p()) {
                        if (cVar.y(f914b) != 0) {
                            cVar.z();
                            cVar.A();
                        } else {
                            eVarA = a.a(cVar, dVar);
                        }
                    }
                    cVar.n();
                    z2 = z4;
                    continue;
                case 1:
                    mVarB = a.b(cVar, dVar);
                    continue;
                case 2:
                    gVarJ = d.j(cVar, dVar);
                    continue;
                case 3:
                    dVar.a("Lottie doesn't support 3D layers.");
                    break;
                case 4:
                    break;
                case 5:
                    dVarH = d.h(cVar, dVar);
                    continue;
                case 6:
                    bVarF3 = d.f(cVar, dVar, z2);
                    continue;
                case 7:
                    bVarF4 = d.f(cVar, dVar, z2);
                    continue;
                case 8:
                    bVarF = d.f(cVar, dVar, z2);
                    continue;
                case 9:
                    bVarF2 = d.f(cVar, dVar, z2);
                    continue;
                default:
                    cVar.z();
                    cVar.A();
                    continue;
            }
            com.airbnb.lottie.t.j.b bVarF5 = d.f(cVar, dVar, z2);
            if (bVarF5.b().isEmpty()) {
                bVarF5.b().add(new com.airbnb.lottie.x.a(dVar, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(dVar.f())));
            } else {
                if (((com.airbnb.lottie.x.a) bVarF5.b().get(0)).f976b == 0) {
                    z = false;
                    bVarF5.b().set(0, new com.airbnb.lottie.x.a(dVar, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(dVar.f())));
                }
                z2 = z;
                bVar = bVarF5;
            }
            z = false;
            z2 = z;
            bVar = bVarF5;
        }
        if (z3) {
            cVar.n();
        }
        com.airbnb.lottie.t.j.e eVar = a(eVarA) ? null : eVarA;
        com.airbnb.lottie.t.j.m<PointF, PointF> mVar = b(mVarB) ? null : mVarB;
        com.airbnb.lottie.t.j.b bVar2 = c(bVar) ? null : bVar;
        if (d(gVarJ)) {
            gVarJ = null;
        }
        return new com.airbnb.lottie.t.j.l(eVar, mVar, gVarJ, bVar2, dVarH, bVarF3, bVarF4, f(bVarF) ? null : bVarF, e(bVarF2) ? null : bVarF2);
    }
}
