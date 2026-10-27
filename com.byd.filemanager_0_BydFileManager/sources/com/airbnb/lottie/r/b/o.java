package com.airbnb.lottie.r.b;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.r.c.a;
import java.util.List;

/* JADX INFO: compiled from: RectangleContent.java */
/* JADX INFO: loaded from: classes.dex */
public class o implements a.b, k, m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2707c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f2708d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.f f2709e;
    private final com.airbnb.lottie.r.c.a<?, PointF> f;
    private final com.airbnb.lottie.r.c.a<?, PointF> g;
    private final com.airbnb.lottie.r.c.a<?, Float> h;
    private boolean j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f2705a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final RectF f2706b = new RectF();
    private b i = new b();

    public o(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.f fVar2) {
        this.f2707c = fVar2.c();
        this.f2708d = fVar2.f();
        this.f2709e = fVar;
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA = fVar2.d().a();
        this.f = aVarA;
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA2 = fVar2.e().a();
        this.g = aVarA2;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA3 = fVar2.b().a();
        this.h = aVarA3;
        aVar.k(aVarA);
        aVar.k(aVarA2);
        aVar.k(aVarA3);
        aVarA.a(this);
        aVarA2.a(this);
        aVarA3.a(this);
    }

    private void d() {
        this.j = false;
        this.f2709e.invalidateSelf();
    }

    @Override // com.airbnb.lottie.r.c.a.b
    public void b() {
        d();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        for (int i = 0; i < list.size(); i++) {
            c cVar = list.get(i);
            if (cVar instanceof s) {
                s sVar = (s) cVar;
                if (sVar.k() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.i.a(sVar);
                    sVar.d(this);
                }
            }
        }
    }

    @Override // com.airbnb.lottie.model.e
    public void e(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2) {
        com.airbnb.lottie.u.g.l(dVar, i, list, dVar2, this);
    }

    @Override // com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        if (t == com.airbnb.lottie.k.h) {
            this.g.m(cVar);
        } else if (t == com.airbnb.lottie.k.j) {
            this.f.m(cVar);
        } else if (t == com.airbnb.lottie.k.i) {
            this.h.m(cVar);
        }
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path h() {
        if (this.j) {
            return this.f2705a;
        }
        this.f2705a.reset();
        if (this.f2708d) {
            this.j = true;
            return this.f2705a;
        }
        PointF pointFH = this.g.h();
        float f = pointFH.x / 2.0f;
        float f2 = pointFH.y / 2.0f;
        com.airbnb.lottie.r.c.a<?, Float> aVar = this.h;
        float fO = aVar == null ? 0.0f : ((com.airbnb.lottie.r.c.c) aVar).o();
        float fMin = Math.min(f, f2);
        if (fO > fMin) {
            fO = fMin;
        }
        PointF pointFH2 = this.f.h();
        this.f2705a.moveTo(pointFH2.x + f, (pointFH2.y - f2) + fO);
        this.f2705a.lineTo(pointFH2.x + f, (pointFH2.y + f2) - fO);
        if (fO > 0.0f) {
            RectF rectF = this.f2706b;
            float f3 = pointFH2.x;
            float f4 = fO * 2.0f;
            float f5 = pointFH2.y;
            rectF.set((f3 + f) - f4, (f5 + f2) - f4, f3 + f, f5 + f2);
            this.f2705a.arcTo(this.f2706b, 0.0f, 90.0f, false);
        }
        this.f2705a.lineTo((pointFH2.x - f) + fO, pointFH2.y + f2);
        if (fO > 0.0f) {
            RectF rectF2 = this.f2706b;
            float f6 = pointFH2.x;
            float f7 = pointFH2.y;
            float f8 = fO * 2.0f;
            rectF2.set(f6 - f, (f7 + f2) - f8, (f6 - f) + f8, f7 + f2);
            this.f2705a.arcTo(this.f2706b, 90.0f, 90.0f, false);
        }
        this.f2705a.lineTo(pointFH2.x - f, (pointFH2.y - f2) + fO);
        if (fO > 0.0f) {
            RectF rectF3 = this.f2706b;
            float f9 = pointFH2.x;
            float f10 = pointFH2.y;
            float f11 = fO * 2.0f;
            rectF3.set(f9 - f, f10 - f2, (f9 - f) + f11, (f10 - f2) + f11);
            this.f2705a.arcTo(this.f2706b, 180.0f, 90.0f, false);
        }
        this.f2705a.lineTo((pointFH2.x + f) - fO, pointFH2.y - f2);
        if (fO > 0.0f) {
            RectF rectF4 = this.f2706b;
            float f12 = pointFH2.x;
            float f13 = fO * 2.0f;
            float f14 = pointFH2.y;
            rectF4.set((f12 + f) - f13, f14 - f2, f12 + f, (f14 - f2) + f13);
            this.f2705a.arcTo(this.f2706b, 270.0f, 90.0f, false);
        }
        this.f2705a.close();
        this.i.b(this.f2705a);
        this.j = true;
        return this.f2705a;
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.f2707c;
    }
}
