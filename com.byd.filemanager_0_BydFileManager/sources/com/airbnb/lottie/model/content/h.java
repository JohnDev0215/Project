package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ShapeData.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<com.airbnb.lottie.model.a> f2586a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PointF f2587b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2588c;

    public h(PointF pointF, boolean z, List<com.airbnb.lottie.model.a> list) {
        this.f2587b = pointF;
        this.f2588c = z;
        this.f2586a = new ArrayList(list);
    }

    private void e(float f, float f2) {
        if (this.f2587b == null) {
            this.f2587b = new PointF();
        }
        this.f2587b.set(f, f2);
    }

    public List<com.airbnb.lottie.model.a> a() {
        return this.f2586a;
    }

    public PointF b() {
        return this.f2587b;
    }

    public void c(h hVar, h hVar2, float f) {
        if (this.f2587b == null) {
            this.f2587b = new PointF();
        }
        this.f2588c = hVar.d() || hVar2.d();
        if (hVar.a().size() != hVar2.a().size()) {
            com.airbnb.lottie.u.d.c("Curves must have the same number of control points. Shape 1: " + hVar.a().size() + "\tShape 2: " + hVar2.a().size());
        }
        int iMin = Math.min(hVar.a().size(), hVar2.a().size());
        if (this.f2586a.size() < iMin) {
            for (int size = this.f2586a.size(); size < iMin; size++) {
                this.f2586a.add(new com.airbnb.lottie.model.a());
            }
        } else if (this.f2586a.size() > iMin) {
            for (int size2 = this.f2586a.size() - 1; size2 >= iMin; size2--) {
                List<com.airbnb.lottie.model.a> list = this.f2586a;
                list.remove(list.size() - 1);
            }
        }
        PointF pointFB = hVar.b();
        PointF pointFB2 = hVar2.b();
        e(com.airbnb.lottie.u.g.j(pointFB.x, pointFB2.x, f), com.airbnb.lottie.u.g.j(pointFB.y, pointFB2.y, f));
        for (int size3 = this.f2586a.size() - 1; size3 >= 0; size3--) {
            com.airbnb.lottie.model.a aVar = hVar.a().get(size3);
            com.airbnb.lottie.model.a aVar2 = hVar2.a().get(size3);
            PointF pointFA = aVar.a();
            PointF pointFB3 = aVar.b();
            PointF pointFC = aVar.c();
            PointF pointFA2 = aVar2.a();
            PointF pointFB4 = aVar2.b();
            PointF pointFC2 = aVar2.c();
            this.f2586a.get(size3).d(com.airbnb.lottie.u.g.j(pointFA.x, pointFA2.x, f), com.airbnb.lottie.u.g.j(pointFA.y, pointFA2.y, f));
            this.f2586a.get(size3).e(com.airbnb.lottie.u.g.j(pointFB3.x, pointFB4.x, f), com.airbnb.lottie.u.g.j(pointFB3.y, pointFB4.y, f));
            this.f2586a.get(size3).f(com.airbnb.lottie.u.g.j(pointFC.x, pointFC2.x, f), com.airbnb.lottie.u.g.j(pointFC.y, pointFC2.y, f));
        }
    }

    public boolean d() {
        return this.f2588c;
    }

    public String toString() {
        return "ShapeData{numCurves=" + this.f2586a.size() + "closed=" + this.f2588c + '}';
    }

    public h() {
        this.f2586a = new ArrayList();
    }
}
