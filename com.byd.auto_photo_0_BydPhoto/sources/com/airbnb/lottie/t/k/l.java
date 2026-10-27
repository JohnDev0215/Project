package com.airbnb.lottie.t.k;

import android.graphics.PointF;
import androidx.annotation.FloatRange;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<com.airbnb.lottie.t.a> f851a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PointF f852b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f853c;

    public l() {
        this.f851a = new ArrayList();
    }

    public l(PointF pointF, boolean z, List<com.airbnb.lottie.t.a> list) {
        this.f852b = pointF;
        this.f853c = z;
        this.f851a = new ArrayList(list);
    }

    private void e(float f, float f2) {
        if (this.f852b == null) {
            this.f852b = new PointF();
        }
        this.f852b.set(f, f2);
    }

    public List<com.airbnb.lottie.t.a> a() {
        return this.f851a;
    }

    public PointF b() {
        return this.f852b;
    }

    public void c(l lVar, l lVar2, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.f852b == null) {
            this.f852b = new PointF();
        }
        this.f853c = lVar.d() || lVar2.d();
        if (lVar.a().size() != lVar2.a().size()) {
            com.airbnb.lottie.w.d.b("Curves must have the same number of control points. Shape 1: " + lVar.a().size() + "\tShape 2: " + lVar2.a().size());
        }
        int iMin = Math.min(lVar.a().size(), lVar2.a().size());
        if (this.f851a.size() < iMin) {
            for (int size = this.f851a.size(); size < iMin; size++) {
                this.f851a.add(new com.airbnb.lottie.t.a());
            }
        } else if (this.f851a.size() > iMin) {
            for (int size2 = this.f851a.size() - 1; size2 >= iMin; size2--) {
                List<com.airbnb.lottie.t.a> list = this.f851a;
                list.remove(list.size() - 1);
            }
        }
        PointF pointFB = lVar.b();
        PointF pointFB2 = lVar2.b();
        e(com.airbnb.lottie.w.g.j(pointFB.x, pointFB2.x, f), com.airbnb.lottie.w.g.j(pointFB.y, pointFB2.y, f));
        for (int size3 = this.f851a.size() - 1; size3 >= 0; size3--) {
            com.airbnb.lottie.t.a aVar = lVar.a().get(size3);
            com.airbnb.lottie.t.a aVar2 = lVar2.a().get(size3);
            PointF pointFA = aVar.a();
            PointF pointFB3 = aVar.b();
            PointF pointFC = aVar.c();
            PointF pointFA2 = aVar2.a();
            PointF pointFB4 = aVar2.b();
            PointF pointFC2 = aVar2.c();
            this.f851a.get(size3).d(com.airbnb.lottie.w.g.j(pointFA.x, pointFA2.x, f), com.airbnb.lottie.w.g.j(pointFA.y, pointFA2.y, f));
            this.f851a.get(size3).e(com.airbnb.lottie.w.g.j(pointFB3.x, pointFB4.x, f), com.airbnb.lottie.w.g.j(pointFB3.y, pointFB4.y, f));
            this.f851a.get(size3).f(com.airbnb.lottie.w.g.j(pointFC.x, pointFC2.x, f), com.airbnb.lottie.w.g.j(pointFC.y, pointFC2.y, f));
        }
    }

    public boolean d() {
        return this.f853c;
    }

    public String toString() {
        return "ShapeData{numCurves=" + this.f851a.size() + "closed=" + this.f853c + '}';
    }
}
