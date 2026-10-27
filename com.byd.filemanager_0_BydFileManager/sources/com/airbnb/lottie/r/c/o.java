package com.airbnb.lottie.r.c;

import android.graphics.Matrix;
import android.graphics.PointF;
import com.airbnb.lottie.r.c.a;
import java.util.Collections;

/* JADX INFO: compiled from: TransformKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Matrix f2739a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Matrix f2740b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f2741c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Matrix f2742d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final float[] f2743e;
    private a<PointF, PointF> f;
    private a<?, PointF> g;
    private a<com.airbnb.lottie.v.d, com.airbnb.lottie.v.d> h;
    private a<Float, Float> i;
    private a<Integer, Integer> j;
    private c k;
    private c l;
    private a<?, Float> m;
    private a<?, Float> n;

    public o(com.airbnb.lottie.model.i.l lVar) {
        this.f = lVar.c() == null ? null : lVar.c().a();
        this.g = lVar.f() == null ? null : lVar.f().a();
        this.h = lVar.h() == null ? null : lVar.h().a();
        this.i = lVar.g() == null ? null : lVar.g().a();
        c cVar = lVar.i() == null ? null : (c) lVar.i().a();
        this.k = cVar;
        if (cVar != null) {
            this.f2740b = new Matrix();
            this.f2741c = new Matrix();
            this.f2742d = new Matrix();
            this.f2743e = new float[9];
        } else {
            this.f2740b = null;
            this.f2741c = null;
            this.f2742d = null;
            this.f2743e = null;
        }
        this.l = lVar.j() == null ? null : (c) lVar.j().a();
        if (lVar.e() != null) {
            this.j = lVar.e().a();
        }
        if (lVar.k() != null) {
            this.m = lVar.k().a();
        } else {
            this.m = null;
        }
        if (lVar.d() != null) {
            this.n = lVar.d().a();
        } else {
            this.n = null;
        }
    }

    private void d() {
        for (int i = 0; i < 9; i++) {
            this.f2743e[i] = 0.0f;
        }
    }

    public void a(com.airbnb.lottie.model.layer.a aVar) {
        aVar.k(this.j);
        aVar.k(this.m);
        aVar.k(this.n);
        aVar.k(this.f);
        aVar.k(this.g);
        aVar.k(this.h);
        aVar.k(this.i);
        aVar.k(this.k);
        aVar.k(this.l);
    }

    public void b(a.b bVar) {
        a<Integer, Integer> aVar = this.j;
        if (aVar != null) {
            aVar.a(bVar);
        }
        a<?, Float> aVar2 = this.m;
        if (aVar2 != null) {
            aVar2.a(bVar);
        }
        a<?, Float> aVar3 = this.n;
        if (aVar3 != null) {
            aVar3.a(bVar);
        }
        a<PointF, PointF> aVar4 = this.f;
        if (aVar4 != null) {
            aVar4.a(bVar);
        }
        a<?, PointF> aVar5 = this.g;
        if (aVar5 != null) {
            aVar5.a(bVar);
        }
        a<com.airbnb.lottie.v.d, com.airbnb.lottie.v.d> aVar6 = this.h;
        if (aVar6 != null) {
            aVar6.a(bVar);
        }
        a<Float, Float> aVar7 = this.i;
        if (aVar7 != null) {
            aVar7.a(bVar);
        }
        c cVar = this.k;
        if (cVar != null) {
            cVar.a(bVar);
        }
        c cVar2 = this.l;
        if (cVar2 != null) {
            cVar2.a(bVar);
        }
    }

    public <T> boolean c(T t, com.airbnb.lottie.v.c<T> cVar) {
        c cVar2;
        c cVar3;
        a<?, Float> aVar;
        a<?, Float> aVar2;
        if (t == com.airbnb.lottie.k.f2500e) {
            a<PointF, PointF> aVar3 = this.f;
            if (aVar3 == null) {
                this.f = new p(cVar, new PointF());
                return true;
            }
            aVar3.m(cVar);
            return true;
        }
        if (t == com.airbnb.lottie.k.f) {
            a<?, PointF> aVar4 = this.g;
            if (aVar4 == null) {
                this.g = new p(cVar, new PointF());
                return true;
            }
            aVar4.m(cVar);
            return true;
        }
        if (t == com.airbnb.lottie.k.k) {
            a<com.airbnb.lottie.v.d, com.airbnb.lottie.v.d> aVar5 = this.h;
            if (aVar5 == null) {
                this.h = new p(cVar, new com.airbnb.lottie.v.d());
                return true;
            }
            aVar5.m(cVar);
            return true;
        }
        if (t == com.airbnb.lottie.k.l) {
            a<Float, Float> aVar6 = this.i;
            if (aVar6 == null) {
                this.i = new p(cVar, Float.valueOf(0.0f));
                return true;
            }
            aVar6.m(cVar);
            return true;
        }
        if (t == com.airbnb.lottie.k.f2498c) {
            a<Integer, Integer> aVar7 = this.j;
            if (aVar7 == null) {
                this.j = new p(cVar, 100);
                return true;
            }
            aVar7.m(cVar);
            return true;
        }
        if (t == com.airbnb.lottie.k.y && (aVar2 = this.m) != null) {
            if (aVar2 == null) {
                this.m = new p(cVar, 100);
                return true;
            }
            aVar2.m(cVar);
            return true;
        }
        if (t == com.airbnb.lottie.k.z && (aVar = this.n) != null) {
            if (aVar == null) {
                this.n = new p(cVar, 100);
                return true;
            }
            aVar.m(cVar);
            return true;
        }
        if (t == com.airbnb.lottie.k.m && (cVar3 = this.k) != null) {
            if (cVar3 == null) {
                this.k = new c(Collections.singletonList(new com.airbnb.lottie.v.a(Float.valueOf(0.0f))));
            }
            this.k.m(cVar);
            return true;
        }
        if (t != com.airbnb.lottie.k.n || (cVar2 = this.l) == null) {
            return false;
        }
        if (cVar2 == null) {
            this.l = new c(Collections.singletonList(new com.airbnb.lottie.v.a(Float.valueOf(0.0f))));
        }
        this.l.m(cVar);
        return true;
    }

    public a<?, Float> e() {
        return this.n;
    }

    public Matrix f() {
        this.f2739a.reset();
        a<?, PointF> aVar = this.g;
        if (aVar != null) {
            PointF pointFH = aVar.h();
            float f = pointFH.x;
            if (f != 0.0f || pointFH.y != 0.0f) {
                this.f2739a.preTranslate(f, pointFH.y);
            }
        }
        a<Float, Float> aVar2 = this.i;
        if (aVar2 != null) {
            float fFloatValue = aVar2 instanceof p ? aVar2.h().floatValue() : ((c) aVar2).o();
            if (fFloatValue != 0.0f) {
                this.f2739a.preRotate(fFloatValue);
            }
        }
        if (this.k != null) {
            float fCos = this.l == null ? 0.0f : (float) Math.cos(Math.toRadians((-r0.o()) + 90.0f));
            float fSin = this.l == null ? 1.0f : (float) Math.sin(Math.toRadians((-r4.o()) + 90.0f));
            float fTan = (float) Math.tan(Math.toRadians(this.k.o()));
            d();
            float[] fArr = this.f2743e;
            fArr[0] = fCos;
            fArr[1] = fSin;
            float f2 = -fSin;
            fArr[3] = f2;
            fArr[4] = fCos;
            fArr[8] = 1.0f;
            this.f2740b.setValues(fArr);
            d();
            float[] fArr2 = this.f2743e;
            fArr2[0] = 1.0f;
            fArr2[3] = fTan;
            fArr2[4] = 1.0f;
            fArr2[8] = 1.0f;
            this.f2741c.setValues(fArr2);
            d();
            float[] fArr3 = this.f2743e;
            fArr3[0] = fCos;
            fArr3[1] = f2;
            fArr3[3] = fSin;
            fArr3[4] = fCos;
            fArr3[8] = 1.0f;
            this.f2742d.setValues(fArr3);
            this.f2741c.preConcat(this.f2740b);
            this.f2742d.preConcat(this.f2741c);
            this.f2739a.preConcat(this.f2742d);
        }
        a<com.airbnb.lottie.v.d, com.airbnb.lottie.v.d> aVar3 = this.h;
        if (aVar3 != null) {
            com.airbnb.lottie.v.d dVarH = aVar3.h();
            if (dVarH.b() != 1.0f || dVarH.c() != 1.0f) {
                this.f2739a.preScale(dVarH.b(), dVarH.c());
            }
        }
        a<PointF, PointF> aVar4 = this.f;
        if (aVar4 != null) {
            PointF pointFH2 = aVar4.h();
            float f3 = pointFH2.x;
            if (f3 != 0.0f || pointFH2.y != 0.0f) {
                this.f2739a.preTranslate(-f3, -pointFH2.y);
            }
        }
        return this.f2739a;
    }

    public Matrix g(float f) {
        a<?, PointF> aVar = this.g;
        PointF pointFH = aVar == null ? null : aVar.h();
        a<com.airbnb.lottie.v.d, com.airbnb.lottie.v.d> aVar2 = this.h;
        com.airbnb.lottie.v.d dVarH = aVar2 == null ? null : aVar2.h();
        this.f2739a.reset();
        if (pointFH != null) {
            this.f2739a.preTranslate(pointFH.x * f, pointFH.y * f);
        }
        if (dVarH != null) {
            double d2 = f;
            this.f2739a.preScale((float) Math.pow(dVarH.b(), d2), (float) Math.pow(dVarH.c(), d2));
        }
        a<Float, Float> aVar3 = this.i;
        if (aVar3 != null) {
            float fFloatValue = aVar3.h().floatValue();
            a<PointF, PointF> aVar4 = this.f;
            PointF pointFH2 = aVar4 != null ? aVar4.h() : null;
            this.f2739a.preRotate(fFloatValue * f, pointFH2 == null ? 0.0f : pointFH2.x, pointFH2 != null ? pointFH2.y : 0.0f);
        }
        return this.f2739a;
    }

    public a<?, Integer> h() {
        return this.j;
    }

    public a<?, Float> i() {
        return this.m;
    }

    public void j(float f) {
        a<Integer, Integer> aVar = this.j;
        if (aVar != null) {
            aVar.l(f);
        }
        a<?, Float> aVar2 = this.m;
        if (aVar2 != null) {
            aVar2.l(f);
        }
        a<?, Float> aVar3 = this.n;
        if (aVar3 != null) {
            aVar3.l(f);
        }
        a<PointF, PointF> aVar4 = this.f;
        if (aVar4 != null) {
            aVar4.l(f);
        }
        a<?, PointF> aVar5 = this.g;
        if (aVar5 != null) {
            aVar5.l(f);
        }
        a<com.airbnb.lottie.v.d, com.airbnb.lottie.v.d> aVar6 = this.h;
        if (aVar6 != null) {
            aVar6.l(f);
        }
        a<Float, Float> aVar7 = this.i;
        if (aVar7 != null) {
            aVar7.l(f);
        }
        c cVar = this.k;
        if (cVar != null) {
            cVar.l(f);
        }
        c cVar2 = this.l;
        if (cVar2 != null) {
            cVar2.l(f);
        }
    }
}
