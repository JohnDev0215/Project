package com.airbnb.lottie.r.b;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.r.c.a;
import com.airbnb.lottie.t.k.i;
import com.airbnb.lottie.t.k.q;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class n implements m, a.InterfaceC0025a, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f749b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.f f750c;
    private final i.a d;
    private final boolean e;
    private final com.airbnb.lottie.r.c.a<?, Float> f;
    private final com.airbnb.lottie.r.c.a<?, PointF> g;
    private final com.airbnb.lottie.r.c.a<?, Float> h;

    @Nullable
    private final com.airbnb.lottie.r.c.a<?, Float> i;
    private final com.airbnb.lottie.r.c.a<?, Float> j;

    @Nullable
    private final com.airbnb.lottie.r.c.a<?, Float> k;
    private final com.airbnb.lottie.r.c.a<?, Float> l;
    private boolean n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f748a = new Path();
    private b m = new b();

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f751a;

        static {
            int[] iArr = new int[i.a.values().length];
            f751a = iArr;
            try {
                iArr[i.a.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f751a[i.a.POLYGON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public n(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, com.airbnb.lottie.t.k.i iVar) {
        com.airbnb.lottie.r.c.a<Float, Float> aVarA;
        this.f750c = fVar;
        this.f749b = iVar.d();
        i.a aVarJ = iVar.j();
        this.d = aVarJ;
        this.e = iVar.k();
        com.airbnb.lottie.r.c.a<Float, Float> aVarA2 = iVar.g().a();
        this.f = aVarA2;
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA3 = iVar.h().a();
        this.g = aVarA3;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA4 = iVar.i().a();
        this.h = aVarA4;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA5 = iVar.e().a();
        this.j = aVarA5;
        com.airbnb.lottie.r.c.a<Float, Float> aVarA6 = iVar.f().a();
        this.l = aVarA6;
        i.a aVar2 = i.a.STAR;
        if (aVarJ == aVar2) {
            this.i = iVar.b().a();
            aVarA = iVar.c().a();
        } else {
            aVarA = null;
            this.i = null;
        }
        this.k = aVarA;
        aVar.h(aVarA2);
        aVar.h(aVarA3);
        aVar.h(aVarA4);
        aVar.h(aVarA5);
        aVar.h(aVarA6);
        if (aVarJ == aVar2) {
            aVar.h(this.i);
            aVar.h(this.k);
        }
        aVarA2.a(this);
        aVarA3.a(this);
        aVarA4.a(this);
        aVarA5.a(this);
        aVarA6.a(this);
        if (aVarJ == aVar2) {
            this.i.a(this);
            this.k.a(this);
        }
    }

    private void d() {
        double d;
        double d2;
        double d3;
        int i;
        int iFloor = (int) Math.floor(this.f.h().floatValue());
        double radians = Math.toRadians((this.h == null ? 0.0d : r2.h().floatValue()) - 90.0d);
        double d4 = iFloor;
        float fFloatValue = this.l.h().floatValue() / 100.0f;
        float fFloatValue2 = this.j.h().floatValue();
        double d5 = fFloatValue2;
        float fCos = (float) (Math.cos(radians) * d5);
        float fSin = (float) (Math.sin(radians) * d5);
        this.f748a.moveTo(fCos, fSin);
        double d6 = (float) (6.283185307179586d / d4);
        double d7 = radians + d6;
        double dCeil = Math.ceil(d4);
        int i2 = 0;
        while (i2 < dCeil) {
            float fCos2 = (float) (Math.cos(d7) * d5);
            double d8 = dCeil;
            float fSin2 = (float) (d5 * Math.sin(d7));
            if (fFloatValue != 0.0f) {
                d2 = d5;
                i = i2;
                d = d7;
                double dAtan2 = (float) (Math.atan2(fSin, fCos) - 1.5707963267948966d);
                float fCos3 = (float) Math.cos(dAtan2);
                float fSin3 = (float) Math.sin(dAtan2);
                d3 = d6;
                double dAtan22 = (float) (Math.atan2(fSin2, fCos2) - 1.5707963267948966d);
                float f = fFloatValue2 * fFloatValue * 0.25f;
                this.f748a.cubicTo(fCos - (fCos3 * f), fSin - (fSin3 * f), fCos2 + (((float) Math.cos(dAtan22)) * f), fSin2 + (f * ((float) Math.sin(dAtan22))), fCos2, fSin2);
            } else {
                d = d7;
                d2 = d5;
                d3 = d6;
                i = i2;
                this.f748a.lineTo(fCos2, fSin2);
            }
            d7 = d + d3;
            i2 = i + 1;
            fSin = fSin2;
            fCos = fCos2;
            dCeil = d8;
            d5 = d2;
            d6 = d3;
        }
        PointF pointFH = this.g.h();
        this.f748a.offset(pointFH.x, pointFH.y);
        this.f748a.close();
    }

    private void h() {
        double d;
        int i;
        double d2;
        float fCos;
        float fSin;
        float f;
        float f2;
        float f3;
        float f4;
        double d3;
        float f5;
        float f6;
        float f7;
        float fFloatValue = this.f.h().floatValue();
        double radians = Math.toRadians((this.h == null ? 0.0d : r2.h().floatValue()) - 90.0d);
        double d4 = fFloatValue;
        float f8 = (float) (6.283185307179586d / d4);
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
            double d5 = f;
            d = d4;
            fCos = (float) (d5 * Math.cos(radians));
            fSin = (float) (d5 * Math.sin(radians));
            this.f748a.moveTo(fCos, fSin);
            d2 = radians + ((double) ((f8 * f10) / 2.0f));
        } else {
            d = d4;
            i = i2;
            double d6 = fFloatValue2;
            float fCos2 = (float) (Math.cos(radians) * d6);
            float fSin2 = (float) (d6 * Math.sin(radians));
            this.f748a.moveTo(fCos2, fSin2);
            d2 = radians + ((double) f9);
            fCos = fCos2;
            fSin = fSin2;
            f = 0.0f;
        }
        double dCeil = Math.ceil(d) * 2.0d;
        int i3 = 0;
        boolean z = false;
        while (true) {
            double d7 = i3;
            if (d7 >= dCeil) {
                PointF pointFH = this.g.h();
                this.f748a.offset(pointFH.x, pointFH.y);
                this.f748a.close();
                return;
            }
            float f11 = z ? fFloatValue2 : fFloatValue3;
            if (f == 0.0f || d7 != dCeil - 2.0d) {
                f2 = f8;
                f3 = f9;
            } else {
                f2 = f8;
                f3 = (f8 * f10) / 2.0f;
            }
            if (f == 0.0f || d7 != dCeil - 1.0d) {
                f4 = f9;
                d3 = d7;
                f5 = f11;
            } else {
                f4 = f9;
                d3 = d7;
                f5 = f;
            }
            double d8 = f5;
            double d9 = dCeil;
            float fCos3 = (float) (d8 * Math.cos(d2));
            float fSin3 = (float) (d8 * Math.sin(d2));
            if (fFloatValue4 == 0.0f && fFloatValue5 == 0.0f) {
                this.f748a.lineTo(fCos3, fSin3);
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
                    } else if (d3 == d9 - 1.0d) {
                        f18 *= f10;
                        f19 *= f10;
                    }
                }
                this.f748a.cubicTo(fCos - f15, fSin - f16, fCos3 + f18, fSin3 + f19, fCos3, fSin3);
            }
            d2 += (double) f3;
            z = !z;
            i3++;
            fCos = fCos3;
            fSin = fSin3;
            fFloatValue4 = f6;
            f = f7;
            f9 = f4;
            f8 = f2;
            dCeil = d9;
        }
    }

    private void i() {
        this.n = false;
        this.f750c.invalidateSelf();
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0025a
    public void b() {
        i();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        for (int i = 0; i < list.size(); i++) {
            c cVar = list.get(i);
            if (cVar instanceof s) {
                s sVar = (s) cVar;
                if (sVar.i() == q.a.SIMULTANEOUSLY) {
                    this.m.a(sVar);
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
        com.airbnb.lottie.r.c.a<?, Float> aVar2;
        if (t == com.airbnb.lottie.k.s) {
            aVar = this.f;
        } else if (t == com.airbnb.lottie.k.t) {
            aVar = this.h;
        } else {
            if (t != com.airbnb.lottie.k.j) {
                if (t != com.airbnb.lottie.k.u || (aVar2 = this.i) == null) {
                    if (t == com.airbnb.lottie.k.v) {
                        aVar = this.j;
                    } else if (t != com.airbnb.lottie.k.w || (aVar2 = this.k) == null) {
                        if (t != com.airbnb.lottie.k.x) {
                            return;
                        } else {
                            aVar = this.l;
                        }
                    }
                }
                aVar2.m(cVar);
                return;
            }
            aVar = this.g;
        }
        aVar.m(cVar);
    }

    @Override // com.airbnb.lottie.r.b.c
    public String getName() {
        return this.f749b;
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path getPath() {
        if (this.n) {
            return this.f748a;
        }
        this.f748a.reset();
        if (!this.e) {
            int i = a.f751a[this.d.ordinal()];
            if (i == 1) {
                h();
            } else if (i == 2) {
                d();
            }
            this.f748a.close();
            this.m.b(this.f748a);
        }
        this.n = true;
        return this.f748a;
    }
}
