package com.airbnb.lottie.r.b;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.r.c.a;
import com.airbnb.lottie.t.k.q;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f implements m, a.InterfaceC0025a, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f736b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.f f737c;
    private final com.airbnb.lottie.r.c.a<?, PointF> d;
    private final com.airbnb.lottie.r.c.a<?, PointF> e;
    private final com.airbnb.lottie.t.k.a f;
    private boolean h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f735a = new Path();
    private b g = new b();

    public f(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, com.airbnb.lottie.t.k.a aVar2) {
        this.f736b = aVar2.b();
        this.f737c = fVar;
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA = aVar2.d().a();
        this.d = aVarA;
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA2 = aVar2.c().a();
        this.e = aVarA2;
        this.f = aVar2;
        aVar.h(aVarA);
        aVar.h(aVarA2);
        aVarA.a(this);
        aVarA2.a(this);
    }

    private void d() {
        this.h = false;
        this.f737c.invalidateSelf();
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
                    this.g.a(sVar);
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
        com.airbnb.lottie.r.c.a<?, PointF> aVar;
        if (t == com.airbnb.lottie.k.g) {
            aVar = this.d;
        } else if (t != com.airbnb.lottie.k.j) {
            return;
        } else {
            aVar = this.e;
        }
        aVar.m(cVar);
    }

    @Override // com.airbnb.lottie.r.b.c
    public String getName() {
        return this.f736b;
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path getPath() {
        if (this.h) {
            return this.f735a;
        }
        this.f735a.reset();
        if (!this.f.e()) {
            PointF pointFH = this.d.h();
            float f = pointFH.x / 2.0f;
            float f2 = pointFH.y / 2.0f;
            float f3 = f * 0.55228f;
            float f4 = 0.55228f * f2;
            this.f735a.reset();
            if (this.f.f()) {
                float f5 = -f2;
                this.f735a.moveTo(0.0f, f5);
                float f6 = 0.0f - f3;
                float f7 = -f;
                float f8 = 0.0f - f4;
                this.f735a.cubicTo(f6, f5, f7, f8, f7, 0.0f);
                float f9 = f4 + 0.0f;
                this.f735a.cubicTo(f7, f9, f6, f2, 0.0f, f2);
                float f10 = f3 + 0.0f;
                this.f735a.cubicTo(f10, f2, f, f9, f, 0.0f);
                this.f735a.cubicTo(f, f8, f10, f5, 0.0f, f5);
            } else {
                float f11 = -f2;
                this.f735a.moveTo(0.0f, f11);
                float f12 = f3 + 0.0f;
                float f13 = 0.0f - f4;
                this.f735a.cubicTo(f12, f11, f, f13, f, 0.0f);
                float f14 = f4 + 0.0f;
                this.f735a.cubicTo(f, f14, f12, f2, 0.0f, f2);
                float f15 = 0.0f - f3;
                float f16 = -f;
                this.f735a.cubicTo(f15, f2, f16, f14, f16, 0.0f);
                this.f735a.cubicTo(f16, f13, f15, f11, 0.0f, f11);
            }
            PointF pointFH2 = this.e.h();
            this.f735a.offset(pointFH2.x, pointFH2.y);
            this.f735a.close();
            this.g.b(this.f735a);
        }
        this.h = true;
        return this.f735a;
    }
}
