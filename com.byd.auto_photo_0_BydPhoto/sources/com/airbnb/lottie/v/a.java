package com.airbnb.lottie.v;

import android.graphics.PointF;
import com.airbnb.lottie.v.k0.c;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c.a f908a = c.a.a("k", "x", "y");

    public static com.airbnb.lottie.t.j.e a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        ArrayList arrayList = new ArrayList();
        if (cVar.w() == c.b.BEGIN_ARRAY) {
            cVar.k();
            while (cVar.p()) {
                arrayList.add(w.a(cVar, dVar));
            }
            cVar.m();
            r.b(arrayList);
        } else {
            arrayList.add(new com.airbnb.lottie.x.a(p.e(cVar, com.airbnb.lottie.w.h.e())));
        }
        return new com.airbnb.lottie.t.j.e(arrayList);
    }

    static com.airbnb.lottie.t.j.m<PointF, PointF> b(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        cVar.l();
        com.airbnb.lottie.t.j.e eVarA = null;
        com.airbnb.lottie.t.j.b bVarE = null;
        boolean z = false;
        com.airbnb.lottie.t.j.b bVarE2 = null;
        while (cVar.w() != c.b.END_OBJECT) {
            int iY = cVar.y(f908a);
            if (iY == 0) {
                eVarA = a(cVar, dVar);
            } else if (iY != 1) {
                if (iY != 2) {
                    cVar.z();
                    cVar.A();
                } else if (cVar.w() == c.b.STRING) {
                    cVar.A();
                    z = true;
                } else {
                    bVarE = d.e(cVar, dVar);
                }
            } else if (cVar.w() == c.b.STRING) {
                cVar.A();
                z = true;
            } else {
                bVarE2 = d.e(cVar, dVar);
            }
        }
        cVar.n();
        if (z) {
            dVar.a("Lottie doesn't support expressions.");
        }
        return eVarA != null ? eVarA : new com.airbnb.lottie.t.j.i(bVarE2, bVarE);
    }
}
