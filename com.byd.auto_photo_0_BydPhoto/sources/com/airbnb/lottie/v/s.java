package com.airbnb.lottie.v;

import android.graphics.Color;
import android.graphics.Rect;
import com.airbnb.lottie.t.l.d;
import com.airbnb.lottie.v.k0.c;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c.a f954a = c.a.a("nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.a f955b = c.a.a("d", "a");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c.a f956c = c.a.a("nm");

    public static com.airbnb.lottie.t.l.d a(com.airbnb.lottie.d dVar) {
        Rect rectB = dVar.b();
        return new com.airbnb.lottie.t.l.d(Collections.emptyList(), dVar, "__container", -1L, d.a.PRE_COMP, -1L, null, Collections.emptyList(), new com.airbnb.lottie.t.j.l(), 0, 0, 0, 0.0f, 0.0f, rectB.width(), rectB.height(), null, null, Collections.emptyList(), d.b.NONE, null, false);
    }

    public static com.airbnb.lottie.t.l.d b(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        ArrayList arrayList;
        ArrayList arrayList2;
        float f;
        d.b bVar = d.b.NONE;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        cVar.l();
        Float fValueOf = Float.valueOf(1.0f);
        Float fValueOf2 = Float.valueOf(0.0f);
        d.b bVar2 = bVar;
        float fR = 1.0f;
        int iS = 0;
        int iS2 = 0;
        int color = 0;
        int iS3 = 0;
        int iS4 = 0;
        boolean zQ = false;
        d.a aVar = null;
        String strU = null;
        com.airbnb.lottie.t.j.l lVarG = null;
        com.airbnb.lottie.t.j.j jVarD = null;
        com.airbnb.lottie.t.j.k kVarA = null;
        com.airbnb.lottie.t.j.b bVarF = null;
        float fR2 = 0.0f;
        float fR3 = 0.0f;
        float fR4 = 0.0f;
        long jS = -1;
        long jS2 = 0;
        String strU2 = null;
        String strU3 = "UNSET";
        while (cVar.p()) {
            switch (cVar.y(f954a)) {
                case 0:
                    strU3 = cVar.u();
                    continue;
                case 1:
                    jS2 = cVar.s();
                    continue;
                case 2:
                    strU = cVar.u();
                    continue;
                case 3:
                    int iS5 = cVar.s();
                    aVar = d.a.UNKNOWN;
                    if (iS5 < aVar.ordinal()) {
                        aVar = d.a.values()[iS5];
                    } else {
                        continue;
                    }
                    break;
                case 4:
                    jS = cVar.s();
                    continue;
                case 5:
                    iS = (int) (cVar.s() * com.airbnb.lottie.w.h.e());
                    continue;
                case 6:
                    iS2 = (int) (cVar.s() * com.airbnb.lottie.w.h.e());
                    continue;
                case 7:
                    color = Color.parseColor(cVar.u());
                    continue;
                case 8:
                    lVarG = c.g(cVar, dVar);
                    continue;
                case 9:
                    bVar2 = d.b.values()[cVar.s()];
                    dVar.q(1);
                    continue;
                case 10:
                    cVar.k();
                    while (cVar.p()) {
                        arrayList3.add(u.a(cVar, dVar));
                    }
                    dVar.q(arrayList3.size());
                    break;
                case 11:
                    cVar.k();
                    while (cVar.p()) {
                        com.airbnb.lottie.t.k.b bVarA = g.a(cVar, dVar);
                        if (bVarA != null) {
                            arrayList4.add(bVarA);
                        }
                    }
                    break;
                case 12:
                    cVar.l();
                    while (cVar.p()) {
                        int iY = cVar.y(f955b);
                        if (iY == 0) {
                            jVarD = d.d(cVar, dVar);
                        } else if (iY != 1) {
                            cVar.z();
                            cVar.A();
                        } else {
                            cVar.k();
                            if (cVar.p()) {
                                kVarA = b.a(cVar, dVar);
                            }
                            while (cVar.p()) {
                                cVar.A();
                            }
                            cVar.m();
                        }
                    }
                    cVar.n();
                    continue;
                case 13:
                    cVar.k();
                    ArrayList arrayList5 = new ArrayList();
                    while (cVar.p()) {
                        cVar.l();
                        while (cVar.p()) {
                            if (cVar.y(f956c) != 0) {
                                cVar.z();
                                cVar.A();
                            } else {
                                arrayList5.add(cVar.u());
                            }
                        }
                        cVar.n();
                    }
                    cVar.m();
                    dVar.a("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + arrayList5);
                    continue;
                case 14:
                    fR = (float) cVar.r();
                    continue;
                case 15:
                    fR4 = (float) cVar.r();
                    continue;
                case 16:
                    iS3 = (int) (cVar.s() * com.airbnb.lottie.w.h.e());
                    continue;
                case 17:
                    iS4 = (int) (cVar.s() * com.airbnb.lottie.w.h.e());
                    continue;
                case 18:
                    fR2 = (float) cVar.r();
                    continue;
                case 19:
                    fR3 = (float) cVar.r();
                    continue;
                case 20:
                    bVarF = d.f(cVar, dVar, false);
                    continue;
                case 21:
                    strU2 = cVar.u();
                    continue;
                case 22:
                    zQ = cVar.q();
                    continue;
                default:
                    cVar.z();
                    cVar.A();
                    continue;
            }
            cVar.m();
        }
        cVar.n();
        float f2 = fR2 / fR;
        float f3 = fR3 / fR;
        ArrayList arrayList6 = new ArrayList();
        if (f2 > 0.0f) {
            arrayList = arrayList3;
            arrayList2 = arrayList6;
            arrayList2.add(new com.airbnb.lottie.x.a(dVar, fValueOf2, fValueOf2, null, 0.0f, Float.valueOf(f2)));
            f = 0.0f;
        } else {
            arrayList = arrayList3;
            arrayList2 = arrayList6;
            f = 0.0f;
        }
        if (f3 <= f) {
            f3 = dVar.f();
        }
        arrayList2.add(new com.airbnb.lottie.x.a(dVar, fValueOf, fValueOf, null, f2, Float.valueOf(f3)));
        arrayList2.add(new com.airbnb.lottie.x.a(dVar, fValueOf2, fValueOf2, null, f3, Float.valueOf(Float.MAX_VALUE)));
        if (strU3.endsWith(".ai") || "ai".equals(strU2)) {
            dVar.a("Convert your Illustrator layers to shape layers.");
        }
        return new com.airbnb.lottie.t.l.d(arrayList4, dVar, strU3, jS2, aVar, jS, strU, arrayList, lVarG, iS, iS2, color, fR, fR4, iS3, iS4, jVarD, kVarA, arrayList2, bVar2, bVarF, zQ);
    }
}
