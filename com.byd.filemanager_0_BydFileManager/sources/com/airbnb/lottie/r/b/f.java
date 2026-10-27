package com.airbnb.lottie.r.b;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.r.c.a;
import java.util.List;

/* JADX INFO: compiled from: EllipseContent.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements m, a.b, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2679b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.f f2680c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.r.c.a<?, PointF> f2681d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.r.c.a<?, PointF> f2682e;
    private final com.airbnb.lottie.model.content.a f;
    private boolean h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f2678a = new Path();
    private b g = new b();

    public f(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.a aVar2) {
        this.f2679b = aVar2.b();
        this.f2680c = fVar;
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA = aVar2.d().a();
        this.f2681d = aVarA;
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA2 = aVar2.c().a();
        this.f2682e = aVarA2;
        this.f = aVar2;
        aVar.k(aVarA);
        aVar.k(aVarA2);
        aVarA.a(this);
        aVarA2.a(this);
    }

    private void d() {
        this.h = false;
        this.f2680c.invalidateSelf();
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
                    this.g.a(sVar);
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
        if (t == com.airbnb.lottie.k.g) {
            this.f2681d.m(cVar);
        } else if (t == com.airbnb.lottie.k.j) {
            this.f2682e.m(cVar);
        }
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path h() {
        if (this.h) {
            return this.f2678a;
        }
        this.f2678a.reset();
        if (this.f.e()) {
            this.h = true;
            return this.f2678a;
        }
        PointF pointFH = this.f2681d.h();
        float f = pointFH.x / 2.0f;
        float f2 = pointFH.y / 2.0f;
        float f3 = f * 0.55228f;
        float f4 = 0.55228f * f2;
        this.f2678a.reset();
        if (this.f.f()) {
            float f5 = -f2;
            this.f2678a.moveTo(0.0f, f5);
            float f6 = 0.0f - f3;
            float f7 = -f;
            float f8 = 0.0f - f4;
            this.f2678a.cubicTo(f6, f5, f7, f8, f7, 0.0f);
            float f9 = f4 + 0.0f;
            this.f2678a.cubicTo(f7, f9, f6, f2, 0.0f, f2);
            float f10 = f3 + 0.0f;
            this.f2678a.cubicTo(f10, f2, f, f9, f, 0.0f);
            this.f2678a.cubicTo(f, f8, f10, f5, 0.0f, f5);
        } else {
            float f11 = -f2;
            this.f2678a.moveTo(0.0f, f11);
            float f12 = f3 + 0.0f;
            float f13 = 0.0f - f4;
            this.f2678a.cubicTo(f12, f11, f, f13, f, 0.0f);
            float f14 = f4 + 0.0f;
            this.f2678a.cubicTo(f, f14, f12, f2, 0.0f, f2);
            float f15 = 0.0f - f3;
            float f16 = -f;
            this.f2678a.cubicTo(f15, f2, f16, f14, f16, 0.0f);
            this.f2678a.cubicTo(f16, f13, f15, f11, 0.0f, f11);
        }
        PointF pointFH2 = this.f2682e.h();
        this.f2678a.offset(pointFH2.x, pointFH2.y);
        this.f2678a.close();
        this.g.b(this.f2678a);
        this.h = true;
        return this.f2678a;
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.f2679b;
    }
}
