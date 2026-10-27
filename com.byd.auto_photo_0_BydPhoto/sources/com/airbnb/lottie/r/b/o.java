package com.airbnb.lottie.r.b;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.r.c.a;
import com.airbnb.lottie.t.k.q;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class o implements a.InterfaceC0025a, k, m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f754c;
    private final boolean d;
    private final com.airbnb.lottie.f e;
    private final com.airbnb.lottie.r.c.a<?, PointF> f;
    private final com.airbnb.lottie.r.c.a<?, PointF> g;
    private final com.airbnb.lottie.r.c.a<?, Float> h;
    private boolean j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f752a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final RectF f753b = new RectF();
    private b i = new b();

    public o(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, com.airbnb.lottie.t.k.j jVar) {
        this.f754c = jVar.c();
        this.d = jVar.f();
        this.e = fVar;
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA = jVar.d().a();
        this.f = aVarA;
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA2 = jVar.e().a();
        this.g = aVarA2;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA3 = jVar.b().a();
        this.h = aVarA3;
        aVar.h(aVarA);
        aVar.h(aVarA2);
        aVar.h(aVarA3);
        aVarA.a(this);
        aVarA2.a(this);
        aVarA3.a(this);
    }

    private void d() {
        this.j = false;
        this.e.invalidateSelf();
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0025a
    public void b() {
        d();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        for (int i = 0; i < list.size(); i++) {
            c cVar = list.get(i);
            if (cVar instanceof s) {
                s sVar = (s) cVar;
                if (sVar.i() == q.a.SIMULTANEOUSLY) {
                    this.i.a(sVar);
                    sVar.d(this);
                }
            }
        }
    }

    @Override // com.airbnb.lottie.t.f
    public void e(com.airbnb.lottie.t.e eVar, int i, List<com.airbnb.lottie.t.e> list, com.airbnb.lottie.t.e eVar2) {
        com.airbnb.lottie.w.g.l(eVar, i, list, eVar2, this);
    }

    @Override // com.airbnb.lottie.t.f
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        com.airbnb.lottie.r.c.a aVar;
        if (t == com.airbnb.lottie.k.h) {
            aVar = this.g;
        } else if (t == com.airbnb.lottie.k.j) {
            aVar = this.f;
        } else if (t != com.airbnb.lottie.k.i) {
            return;
        } else {
            aVar = this.h;
        }
        aVar.m(cVar);
    }

    @Override // com.airbnb.lottie.r.b.c
    public String getName() {
        return this.f754c;
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path getPath() {
        if (this.j) {
            return this.f752a;
        }
        this.f752a.reset();
        if (!this.d) {
            PointF pointFH = this.g.h();
            float f = pointFH.x / 2.0f;
            float f2 = pointFH.y / 2.0f;
            com.airbnb.lottie.r.c.a<?, Float> aVar = this.h;
            float fN = aVar == null ? 0.0f : ((com.airbnb.lottie.r.c.c) aVar).n();
            float fMin = Math.min(f, f2);
            if (fN > fMin) {
                fN = fMin;
            }
            PointF pointFH2 = this.f.h();
            this.f752a.moveTo(pointFH2.x + f, (pointFH2.y - f2) + fN);
            this.f752a.lineTo(pointFH2.x + f, (pointFH2.y + f2) - fN);
            if (fN > 0.0f) {
                RectF rectF = this.f753b;
                float f3 = pointFH2.x;
                float f4 = fN * 2.0f;
                float f5 = pointFH2.y;
                rectF.set((f3 + f) - f4, (f5 + f2) - f4, f3 + f, f5 + f2);
                this.f752a.arcTo(this.f753b, 0.0f, 90.0f, false);
            }
            this.f752a.lineTo((pointFH2.x - f) + fN, pointFH2.y + f2);
            if (fN > 0.0f) {
                RectF rectF2 = this.f753b;
                float f6 = pointFH2.x;
                float f7 = pointFH2.y;
                float f8 = fN * 2.0f;
                rectF2.set(f6 - f, (f7 + f2) - f8, (f6 - f) + f8, f7 + f2);
                this.f752a.arcTo(this.f753b, 90.0f, 90.0f, false);
            }
            this.f752a.lineTo(pointFH2.x - f, (pointFH2.y - f2) + fN);
            if (fN > 0.0f) {
                RectF rectF3 = this.f753b;
                float f9 = pointFH2.x;
                float f10 = pointFH2.y;
                float f11 = fN * 2.0f;
                rectF3.set(f9 - f, f10 - f2, (f9 - f) + f11, (f10 - f2) + f11);
                this.f752a.arcTo(this.f753b, 180.0f, 90.0f, false);
            }
            this.f752a.lineTo((pointFH2.x + f) - fN, pointFH2.y - f2);
            if (fN > 0.0f) {
                RectF rectF4 = this.f753b;
                float f12 = pointFH2.x;
                float f13 = fN * 2.0f;
                float f14 = pointFH2.y;
                rectF4.set((f12 + f) - f13, f14 - f2, f12 + f, (f14 - f2) + f13);
                this.f752a.arcTo(this.f753b, 270.0f, 90.0f, false);
            }
            this.f752a.close();
            this.i.b(this.f752a);
        }
        this.j = true;
        return this.f752a;
    }
}
