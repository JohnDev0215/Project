package r1;

import android.graphics.Matrix;
import android.graphics.PointF;
import java.util.Collections;
import r1.a;

/* JADX INFO: compiled from: TransformKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matrix f7772a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix f7773b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f7774c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Matrix f7775d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float[] f7776e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a<PointF, PointF> f7777f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a<?, PointF> f7778g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a<b2.d, b2.d> f7779h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a<Float, Float> f7780i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public a<Integer, Integer> f7781j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public c f7782k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public c f7783l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public a<?, Float> f7784m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public a<?, Float> f7785n;

    public o(u1.l lVar) {
        this.f7777f = lVar.c() == null ? null : lVar.c().a();
        this.f7778g = lVar.f() == null ? null : lVar.f().a();
        this.f7779h = lVar.h() == null ? null : lVar.h().a();
        this.f7780i = lVar.g() == null ? null : lVar.g().a();
        c cVar = lVar.i() == null ? null : (c) lVar.i().a();
        this.f7782k = cVar;
        if (cVar != null) {
            this.f7773b = new Matrix();
            this.f7774c = new Matrix();
            this.f7775d = new Matrix();
            this.f7776e = new float[9];
        } else {
            this.f7773b = null;
            this.f7774c = null;
            this.f7775d = null;
            this.f7776e = null;
        }
        this.f7783l = lVar.j() == null ? null : (c) lVar.j().a();
        if (lVar.e() != null) {
            this.f7781j = lVar.e().a();
        }
        if (lVar.k() != null) {
            this.f7784m = lVar.k().a();
        } else {
            this.f7784m = null;
        }
        if (lVar.d() != null) {
            this.f7785n = lVar.d().a();
        } else {
            this.f7785n = null;
        }
    }

    public void a(w1.a aVar) {
        aVar.k(this.f7781j);
        aVar.k(this.f7784m);
        aVar.k(this.f7785n);
        aVar.k(this.f7777f);
        aVar.k(this.f7778g);
        aVar.k(this.f7779h);
        aVar.k(this.f7780i);
        aVar.k(this.f7782k);
        aVar.k(this.f7783l);
    }

    public void b(a.b bVar) {
        a<Integer, Integer> aVar = this.f7781j;
        if (aVar != null) {
            aVar.a(bVar);
        }
        a<?, Float> aVar2 = this.f7784m;
        if (aVar2 != null) {
            aVar2.a(bVar);
        }
        a<?, Float> aVar3 = this.f7785n;
        if (aVar3 != null) {
            aVar3.a(bVar);
        }
        a<PointF, PointF> aVar4 = this.f7777f;
        if (aVar4 != null) {
            aVar4.a(bVar);
        }
        a<?, PointF> aVar5 = this.f7778g;
        if (aVar5 != null) {
            aVar5.a(bVar);
        }
        a<b2.d, b2.d> aVar6 = this.f7779h;
        if (aVar6 != null) {
            aVar6.a(bVar);
        }
        a<Float, Float> aVar7 = this.f7780i;
        if (aVar7 != null) {
            aVar7.a(bVar);
        }
        c cVar = this.f7782k;
        if (cVar != null) {
            cVar.a(bVar);
        }
        c cVar2 = this.f7783l;
        if (cVar2 != null) {
            cVar2.a(bVar);
        }
    }

    public <T> boolean c(T t7, b2.c<T> cVar) {
        c cVar2;
        c cVar3;
        a<?, Float> aVar;
        a<?, Float> aVar2;
        if (t7 == o1.l.f6832e) {
            a<PointF, PointF> aVar3 = this.f7777f;
            if (aVar3 == null) {
                this.f7777f = new p(cVar, new PointF());
                return true;
            }
            aVar3.m(cVar);
            return true;
        }
        if (t7 == o1.l.f6833f) {
            a<?, PointF> aVar4 = this.f7778g;
            if (aVar4 == null) {
                this.f7778g = new p(cVar, new PointF());
                return true;
            }
            aVar4.m(cVar);
            return true;
        }
        if (t7 == o1.l.f6838k) {
            a<b2.d, b2.d> aVar5 = this.f7779h;
            if (aVar5 == null) {
                this.f7779h = new p(cVar, new b2.d());
                return true;
            }
            aVar5.m(cVar);
            return true;
        }
        if (t7 == o1.l.f6839l) {
            a<Float, Float> aVar6 = this.f7780i;
            if (aVar6 == null) {
                this.f7780i = new p(cVar, Float.valueOf(0.0f));
                return true;
            }
            aVar6.m(cVar);
            return true;
        }
        if (t7 == o1.l.f6830c) {
            a<Integer, Integer> aVar7 = this.f7781j;
            if (aVar7 == null) {
                this.f7781j = new p(cVar, 100);
                return true;
            }
            aVar7.m(cVar);
            return true;
        }
        if (t7 == o1.l.f6852y && (aVar2 = this.f7784m) != null) {
            if (aVar2 == null) {
                this.f7784m = new p(cVar, 100);
                return true;
            }
            aVar2.m(cVar);
            return true;
        }
        if (t7 == o1.l.f6853z && (aVar = this.f7785n) != null) {
            if (aVar == null) {
                this.f7785n = new p(cVar, 100);
                return true;
            }
            aVar.m(cVar);
            return true;
        }
        if (t7 == o1.l.f6840m && (cVar3 = this.f7782k) != null) {
            if (cVar3 == null) {
                this.f7782k = new c(Collections.singletonList(new b2.a(Float.valueOf(0.0f))));
            }
            this.f7782k.m(cVar);
            return true;
        }
        if (t7 != o1.l.f6841n || (cVar2 = this.f7783l) == null) {
            return false;
        }
        if (cVar2 == null) {
            this.f7783l = new c(Collections.singletonList(new b2.a(Float.valueOf(0.0f))));
        }
        this.f7783l.m(cVar);
        return true;
    }

    public final void d() {
        for (int i8 = 0; i8 < 9; i8++) {
            this.f7776e[i8] = 0.0f;
        }
    }

    public a<?, Float> e() {
        return this.f7785n;
    }

    public Matrix f() {
        this.f7772a.reset();
        a<?, PointF> aVar = this.f7778g;
        if (aVar != null) {
            PointF pointFH = aVar.h();
            float f8 = pointFH.x;
            if (f8 != 0.0f || pointFH.y != 0.0f) {
                this.f7772a.preTranslate(f8, pointFH.y);
            }
        }
        a<Float, Float> aVar2 = this.f7780i;
        if (aVar2 != null) {
            float fFloatValue = aVar2 instanceof p ? aVar2.h().floatValue() : ((c) aVar2).o();
            if (fFloatValue != 0.0f) {
                this.f7772a.preRotate(fFloatValue);
            }
        }
        if (this.f7782k != null) {
            float fCos = this.f7783l == null ? 0.0f : (float) Math.cos(Math.toRadians((-r0.o()) + 90.0f));
            float fSin = this.f7783l == null ? 1.0f : (float) Math.sin(Math.toRadians((-r4.o()) + 90.0f));
            float fTan = (float) Math.tan(Math.toRadians(this.f7782k.o()));
            d();
            float[] fArr = this.f7776e;
            fArr[0] = fCos;
            fArr[1] = fSin;
            float f9 = -fSin;
            fArr[3] = f9;
            fArr[4] = fCos;
            fArr[8] = 1.0f;
            this.f7773b.setValues(fArr);
            d();
            float[] fArr2 = this.f7776e;
            fArr2[0] = 1.0f;
            fArr2[3] = fTan;
            fArr2[4] = 1.0f;
            fArr2[8] = 1.0f;
            this.f7774c.setValues(fArr2);
            d();
            float[] fArr3 = this.f7776e;
            fArr3[0] = fCos;
            fArr3[1] = f9;
            fArr3[3] = fSin;
            fArr3[4] = fCos;
            fArr3[8] = 1.0f;
            this.f7775d.setValues(fArr3);
            this.f7774c.preConcat(this.f7773b);
            this.f7775d.preConcat(this.f7774c);
            this.f7772a.preConcat(this.f7775d);
        }
        a<b2.d, b2.d> aVar3 = this.f7779h;
        if (aVar3 != null) {
            b2.d dVarH = aVar3.h();
            if (dVarH.b() != 1.0f || dVarH.c() != 1.0f) {
                this.f7772a.preScale(dVarH.b(), dVarH.c());
            }
        }
        a<PointF, PointF> aVar4 = this.f7777f;
        if (aVar4 != null) {
            PointF pointFH2 = aVar4.h();
            float f10 = pointFH2.x;
            if (f10 != 0.0f || pointFH2.y != 0.0f) {
                this.f7772a.preTranslate(-f10, -pointFH2.y);
            }
        }
        return this.f7772a;
    }

    public Matrix g(float f8) {
        a<?, PointF> aVar = this.f7778g;
        PointF pointFH = aVar == null ? null : aVar.h();
        a<b2.d, b2.d> aVar2 = this.f7779h;
        b2.d dVarH = aVar2 == null ? null : aVar2.h();
        this.f7772a.reset();
        if (pointFH != null) {
            this.f7772a.preTranslate(pointFH.x * f8, pointFH.y * f8);
        }
        if (dVarH != null) {
            double d8 = f8;
            this.f7772a.preScale((float) Math.pow(dVarH.b(), d8), (float) Math.pow(dVarH.c(), d8));
        }
        a<Float, Float> aVar3 = this.f7780i;
        if (aVar3 != null) {
            float fFloatValue = aVar3.h().floatValue();
            a<PointF, PointF> aVar4 = this.f7777f;
            PointF pointFH2 = aVar4 != null ? aVar4.h() : null;
            this.f7772a.preRotate(fFloatValue * f8, pointFH2 == null ? 0.0f : pointFH2.x, pointFH2 != null ? pointFH2.y : 0.0f);
        }
        return this.f7772a;
    }

    public a<?, Integer> h() {
        return this.f7781j;
    }

    public a<?, Float> i() {
        return this.f7784m;
    }

    public void j(float f8) {
        a<Integer, Integer> aVar = this.f7781j;
        if (aVar != null) {
            aVar.l(f8);
        }
        a<?, Float> aVar2 = this.f7784m;
        if (aVar2 != null) {
            aVar2.l(f8);
        }
        a<?, Float> aVar3 = this.f7785n;
        if (aVar3 != null) {
            aVar3.l(f8);
        }
        a<PointF, PointF> aVar4 = this.f7777f;
        if (aVar4 != null) {
            aVar4.l(f8);
        }
        a<?, PointF> aVar5 = this.f7778g;
        if (aVar5 != null) {
            aVar5.l(f8);
        }
        a<b2.d, b2.d> aVar6 = this.f7779h;
        if (aVar6 != null) {
            aVar6.l(f8);
        }
        a<Float, Float> aVar7 = this.f7780i;
        if (aVar7 != null) {
            aVar7.l(f8);
        }
        c cVar = this.f7782k;
        if (cVar != null) {
            cVar.l(f8);
        }
        c cVar2 = this.f7783l;
        if (cVar2 != null) {
            cVar2.l(f8);
        }
    }
}
