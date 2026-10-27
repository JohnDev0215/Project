package com.airbnb.lottie.r.b;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.r.c.a;
import java.util.List;

/* JADX INFO: compiled from: PolystarContent.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements m, a.b, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2700b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.f f2701c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final PolystarShape.Type f2702d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f2703e;
    private final com.airbnb.lottie.r.c.a<?, Float> f;
    private final com.airbnb.lottie.r.c.a<?, PointF> g;
    private final com.airbnb.lottie.r.c.a<?, Float> h;
    private final com.airbnb.lottie.r.c.a<?, Float> i;
    private final com.airbnb.lottie.r.c.a<?, Float> j;
    private final com.airbnb.lottie.r.c.a<?, Float> k;
    private final com.airbnb.lottie.r.c.a<?, Float> l;
    private boolean n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f2699a = new Path();
    private b m = new b();

    /* JADX INFO: compiled from: PolystarContent.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2704a;

        static {
            int[] iArr = new int[PolystarShape.Type.values().length];
            f2704a = iArr;
            try {
                iArr[PolystarShape.Type.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2704a[PolystarShape.Type.POLYGON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public n(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, PolystarShape polystarShape) {
        this.f2701c = fVar;
        this.f2700b = polystarShape.d();
        PolystarShape.Type typeJ = polystarShape.j();
        this.f2702d = typeJ;
        this.f2703e = polystarShape.k();
        com.airbnb.lottie.r.c.a<Float, Float> aVarA = polystarShape.g().a();
        this.f = aVarA;
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA2 = polystarShape.h().a();
        this.g = aVarA2;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA3 = polystarShape.i().a();
        this.h = aVarA3;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA4 = polystarShape.e().a();
        this.j = aVarA4;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA5 = polystarShape.f().a();
        this.l = aVarA5;
        PolystarShape.Type type = PolystarShape.Type.STAR;
        if (typeJ == type) {
            this.i = polystarShape.b().a();
            this.k = polystarShape.c().a();
        } else {
            this.i = null;
            this.k = null;
        }
        aVar.k(aVarA);
        aVar.k(aVarA2);
        aVar.k(aVarA3);
        aVar.k(aVarA4);
        aVar.k(aVarA5);
        if (typeJ == type) {
            aVar.k(this.i);
            aVar.k(this.k);
        }
        aVarA.a(this);
        aVarA2.a(this);
        aVarA3.a(this);
        aVarA4.a(this);
        aVarA5.a(this);
        if (typeJ == type) {
            this.i.a(this);
            this.k.a(this);
        }
    }

    private void d() {
        double d2;
        double d3;
        double d4;
        int i;
        int iFloor = (int) Math.floor(this.f.h().floatValue());
        double radians = Math.toRadians((this.h == null ? 0.0d : r2.h().floatValue()) - 90.0d);
        double d5 = iFloor;
        float fFloatValue = this.l.h().floatValue() / 100.0f;
        float fFloatValue2 = this.j.h().floatValue();
        double d6 = fFloatValue2;
        float fCos = (float) (Math.cos(radians) * d6);
        float fSin = (float) (Math.sin(radians) * d6);
        this.f2699a.moveTo(fCos, fSin);
        double d7 = (float) (6.283185307179586d / d5);
        double d8 = radians + d7;
        double dCeil = Math.ceil(d5);
        int i2 = 0;
        while (i2 < dCeil) {
            float fCos2 = (float) (Math.cos(d8) * d6);
            double d9 = dCeil;
            float fSin2 = (float) (d6 * Math.sin(d8));
            if (fFloatValue != 0.0f) {
                d3 = d6;
                i = i2;
                d2 = d8;
                double dAtan2 = (float) (Math.atan2(fSin, fCos) - 1.5707963267948966d);
                float fCos3 = (float) Math.cos(dAtan2);
                float fSin3 = (float) Math.sin(dAtan2);
                d4 = d7;
                double dAtan22 = (float) (Math.atan2(fSin2, fCos2) - 1.5707963267948966d);
                float f = fFloatValue2 * fFloatValue * 0.25f;
                this.f2699a.cubicTo(fCos - (fCos3 * f), fSin - (fSin3 * f), fCos2 + (((float) Math.cos(dAtan22)) * f), fSin2 + (f * ((float) Math.sin(dAtan22))), fCos2, fSin2);
            } else {
                d2 = d8;
                d3 = d6;
                d4 = d7;
                i = i2;
                this.f2699a.lineTo(fCos2, fSin2);
            }
            d8 = d2 + d4;
            i2 = i + 1;
            fSin = fSin2;
            fCos = fCos2;
            dCeil = d9;
            d6 = d3;
            d7 = d4;
        }
        PointF pointFH = this.g.h();
        this.f2699a.offset(pointFH.x, pointFH.y);
        this.f2699a.close();
    }

    private void j() {
        double d2;
        int i;
        double d3;
        float fCos;
        float fSin;
        float f;
        float f2;
        float f3;
        float f4;
        double d4;
        float f5;
        float f6;
        float f7;
        float fFloatValue = this.f.h().floatValue();
        double radians = Math.toRadians((this.h == null ? 0.0d : r2.h().floatValue()) - 90.0d);
        double d5 = fFloatValue;
        float f8 = (float) (6.283185307179586d / d5);
        float f9 = f8 / 2.0f;
        float f10 = fFloatValue - ((int) fFloatValue);
        int i2 = (f10 > 0.0f ? 1 : (f10 == 0.0f ? 0 : -1));
        if (i2 != 0) {
            radians += (double) ((1.0f - f10) * f9);
        }
        float fFloatValue2 = this.j.h().floatValue();
        float fFloatValue3 = this.i.h().floatValue();
        com.airbnb.lottie.r.c.a<?, Float> aVar = this.k;
        float fFloatValue4 = aVar != null ? aVar.h().floatValue() / 100.0f : 0.0f;
        com.airbnb.lottie.r.c.a<?, Float> aVar2 = this.l;
        float fFloatValue5 = aVar2 != null ? aVar2.h().floatValue() / 100.0f : 0.0f;
        if (i2 != 0) {
            f = ((fFloatValue2 - fFloatValue3) * f10) + fFloatValue3;
            i = i2;
            double d6 = f;
            d2 = d5;
            fCos = (float) (d6 * Math.cos(radians));
            fSin = (float) (d6 * Math.sin(radians));
            this.f2699a.moveTo(fCos, fSin);
            d3 = radians + ((double) ((f8 * f10) / 2.0f));
        } else {
            d2 = d5;
            i = i2;
            double d7 = fFloatValue2;
            float fCos2 = (float) (Math.cos(radians) * d7);
            float fSin2 = (float) (d7 * Math.sin(radians));
            this.f2699a.moveTo(fCos2, fSin2);
            d3 = radians + ((double) f9);
            fCos = fCos2;
            fSin = fSin2;
            f = 0.0f;
        }
        double dCeil = Math.ceil(d2) * 2.0d;
        int i3 = 0;
        boolean z = false;
        while (true) {
            double d8 = i3;
            if (d8 >= dCeil) {
                PointF pointFH = this.g.h();
                this.f2699a.offset(pointFH.x, pointFH.y);
                this.f2699a.close();
                return;
            }
            float f11 = z ? fFloatValue2 : fFloatValue3;
            if (f == 0.0f || d8 != dCeil - 2.0d) {
                f2 = f8;
                f3 = f9;
            } else {
                f2 = f8;
                f3 = (f8 * f10) / 2.0f;
            }
            if (f == 0.0f || d8 != dCeil - 1.0d) {
                f4 = f9;
                d4 = d8;
                f5 = f11;
            } else {
                f4 = f9;
                d4 = d8;
                f5 = f;
            }
            double d9 = f5;
            double d10 = dCeil;
            float fCos3 = (float) (d9 * Math.cos(d3));
            float fSin3 = (float) (d9 * Math.sin(d3));
            if (fFloatValue4 == 0.0f && fFloatValue5 == 0.0f) {
                this.f2699a.lineTo(fCos3, fSin3);
                f6 = fFloatValue4;
                f7 = f;
            } else {
                f6 = fFloatValue4;
                f7 = f;
                double dAtan2 = (float) (Math.atan2(fSin, fCos) - 1.5707963267948966d);
                float fCos4 = (float) Math.cos(dAtan2);
                float fSin4 = (float) Math.sin(dAtan2);
                double dAtan22 = (float) (Math.atan2(fSin3, fCos3) - 1.5707963267948966d);
                float fCos5 = (float) Math.cos(dAtan22);
                float fSin5 = (float) Math.sin(dAtan22);
                float f12 = z ? f6 : fFloatValue5;
                float f13 = z ? fFloatValue5 : f6;
                float f14 = (z ? fFloatValue3 : fFloatValue2) * f12 * 0.47829f;
                float f15 = fCos4 * f14;
                float f16 = f14 * fSin4;
                float f17 = (z ? fFloatValue2 : fFloatValue3) * f13 * 0.47829f;
                float f18 = fCos5 * f17;
                float f19 = f17 * fSin5;
                if (i != 0) {
                    if (i3 == 0) {
                        f15 *= f10;
                        f16 *= f10;
                    } else if (d4 == d10 - 1.0d) {
                        f18 *= f10;
                        f19 *= f10;
                    }
                }
                this.f2699a.cubicTo(fCos - f15, fSin - f16, fCos3 + f18, fSin3 + f19, fCos3, fSin3);
            }
            d3 += (double) f3;
            z = !z;
            i3++;
            fCos = fCos3;
            fSin = fSin3;
            fFloatValue4 = f6;
            f = f7;
            f9 = f4;
            f8 = f2;
            dCeil = d10;
        }
    }

    private void k() {
        this.n = false;
        this.f2701c.invalidateSelf();
    }

    @Override // com.airbnb.lottie.r.c.a.b
    public void b() {
        k();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        for (int i = 0; i < list.size(); i++) {
            c cVar = list.get(i);
            if (cVar instanceof s) {
                s sVar = (s) cVar;
                if (sVar.k() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.m.a(sVar);
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
        com.airbnb.lottie.r.c.a<?, Float> aVar;
        com.airbnb.lottie.r.c.a<?, Float> aVar2;
        if (t == com.airbnb.lottie.k.s) {
            this.f.m(cVar);
            return;
        }
        if (t == com.airbnb.lottie.k.t) {
            this.h.m(cVar);
            return;
        }
        if (t == com.airbnb.lottie.k.j) {
            this.g.m(cVar);
            return;
        }
        if (t == com.airbnb.lottie.k.u && (aVar2 = this.i) != null) {
            aVar2.m(cVar);
            return;
        }
        if (t == com.airbnb.lottie.k.v) {
            this.j.m(cVar);
            return;
        }
        if (t == com.airbnb.lottie.k.w && (aVar = this.k) != null) {
            aVar.m(cVar);
        } else if (t == com.airbnb.lottie.k.x) {
            this.l.m(cVar);
        }
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path h() {
        if (this.n) {
            return this.f2699a;
        }
        this.f2699a.reset();
        if (this.f2703e) {
            this.n = true;
            return this.f2699a;
        }
        int i = a.f2704a[this.f2702d.ordinal()];
        if (i == 1) {
            j();
        } else if (i == 2) {
            d();
        }
        this.f2699a.close();
        this.m.b(this.f2699a);
        this.n = true;
        return this.f2699a;
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.f2700b;
    }
}
