package com.airbnb.lottie.v;

import android.graphics.PointF;
import com.airbnb.lottie.v.k0.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d0 implements j0<com.airbnb.lottie.t.k.l> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d0 f916a = new d0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c.a f917b = c.a.a("c", "v", "i", "o");

    private d0() {
    }

    @Override // com.airbnb.lottie.v.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.airbnb.lottie.t.k.l a(com.airbnb.lottie.v.k0.c cVar, float f) {
        if (cVar.w() == c.b.BEGIN_ARRAY) {
            cVar.k();
        }
        cVar.l();
        List<PointF> listF = null;
        List<PointF> listF2 = null;
        List<PointF> listF3 = null;
        boolean zQ = false;
        while (cVar.p()) {
            int iY = cVar.y(f917b);
            if (iY == 0) {
                zQ = cVar.q();
            } else if (iY == 1) {
                listF = p.f(cVar, f);
            } else if (iY == 2) {
                listF2 = p.f(cVar, f);
            } else if (iY != 3) {
                cVar.z();
                cVar.A();
            } else {
                listF3 = p.f(cVar, f);
            }
        }
        cVar.n();
        if (cVar.w() == c.b.END_ARRAY) {
            cVar.m();
        }
        if (listF == null || listF2 == null || listF3 == null) {
            throw new IllegalArgumentException("Shape data was missing information.");
        }
        if (listF.isEmpty()) {
            return new com.airbnb.lottie.t.k.l(new PointF(), false, Collections.emptyList());
        }
        int size = listF.size();
        PointF pointF = listF.get(0);
        ArrayList arrayList = new ArrayList(size);
        for (int i = 1; i < size; i++) {
            PointF pointF2 = listF.get(i);
            int i2 = i - 1;
            arrayList.add(new com.airbnb.lottie.t.a(com.airbnb.lottie.w.g.a(listF.get(i2), listF3.get(i2)), com.airbnb.lottie.w.g.a(pointF2, listF2.get(i)), pointF2));
        }
        if (zQ) {
            PointF pointF3 = listF.get(0);
            int i3 = size - 1;
            arrayList.add(new com.airbnb.lottie.t.a(com.airbnb.lottie.w.g.a(listF.get(i3), listF3.get(i3)), com.airbnb.lottie.w.g.a(pointF3, listF2.get(0)), pointF3));
        }
        return new com.airbnb.lottie.t.k.l(pointF, zQ, arrayList);
    }
}
