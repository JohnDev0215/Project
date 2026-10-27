package com.airbnb.lottie.r.c;

import android.graphics.Matrix;
import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.r.c.a;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Matrix f770a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Matrix f771b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f772c;
    private final Matrix d;
    private final float[] e;

    @NonNull
    private a<PointF, PointF> f;

    @NonNull
    private a<?, PointF> g;

    @NonNull
    private a<com.airbnb.lottie.x.d, com.airbnb.lottie.x.d> h;

    @NonNull
    private a<Float, Float> i;

    @NonNull
    private a<Integer, Integer> j;

    @Nullable
    private c k;

    @Nullable
    private c l;

    @Nullable
    private a<?, Float> m;

    @Nullable
    private a<?, Float> n;

    public o(com.airbnb.lottie.t.j.l lVar) {
        this.f = lVar.c() == null ? null : lVar.c().a();
        this.g = lVar.f() == null ? null : lVar.f().a();
        this.h = lVar.h() == null ? null : lVar.h().a();
        this.i = lVar.g() == null ? null : lVar.g().a();
        c cVar = lVar.i() == null ? null : (c) lVar.i().a();
        this.k = cVar;
        if (cVar != null) {
            this.f771b = new Matrix();
            this.f772c = new Matrix();
            this.d = new Matrix();
            this.e = new float[9];
        } else {
            this.f771b = null;
            this.f772c = null;
            this.d = null;
            this.e = null;
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
            this.e[i] = 0.0f;
        }
    }

    public void a(com.airbnb.lottie.t.l.a aVar) {
        aVar.h(this.j);
        aVar.h(this.m);
        aVar.h(this.n);
        aVar.h(this.f);
        aVar.h(this.g);
        aVar.h(this.h);
        aVar.h(this.i);
        aVar.h(this.k);
        aVar.h(this.l);
    }

    public void b(a.InterfaceC0025a interfaceC0025a) {
        a<Integer, Integer> aVar = this.j;
        if (aVar != null) {
            aVar.a(interfaceC0025a);
        }
        a<?, Float> aVar2 = this.m;
        if (aVar2 != null) {
            aVar2.a(interfaceC0025a);
        }
        a<?, Float> aVar3 = this.n;
        if (aVar3 != null) {
            aVar3.a(interfaceC0025a);
        }
        a<PointF, PointF> aVar4 = this.f;
        if (aVar4 != null) {
            aVar4.a(interfaceC0025a);
        }
        a<?, PointF> aVar5 = this.g;
        if (aVar5 != null) {
            aVar5.a(interfaceC0025a);
        }
        a<com.airbnb.lottie.x.d, com.airbnb.lottie.x.d> aVar6 = this.h;
        if (aVar6 != null) {
            aVar6.a(interfaceC0025a);
        }
        a<Float, Float> aVar7 = this.i;
        if (aVar7 != null) {
            aVar7.a(interfaceC0025a);
        }
        c cVar = this.k;
        if (cVar != null) {
            cVar.a(interfaceC0025a);
        }
        c cVar2 = this.l;
        if (cVar2 != null) {
            cVar2.a(interfaceC0025a);
        }
    }

    public <T> boolean c(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        c cVar2;
        a aVar;
        c cVar3;
        a<?, Float> aVar2;
        if (t == com.airbnb.lottie.k.e) {
            aVar = this.f;
            if (aVar == null) {
                this.f = new p(cVar, new PointF());
                return true;
            }
        } else if (t == com.airbnb.lottie.k.f) {
            aVar = this.g;
            if (aVar == null) {
                this.g = new p(cVar, new PointF());
                return true;
            }
        } else if (t == com.airbnb.lottie.k.k) {
            aVar = this.h;
            if (aVar == null) {
                this.h = new p(cVar, new com.airbnb.lottie.x.d());
                return true;
            }
        } else if (t == com.airbnb.lottie.k.l) {
            aVar = this.i;
            if (aVar == null) {
                this.i = new p(cVar, Float.valueOf(0.0f));
                return true;
            }
        } else {
            if (t != com.airbnb.lottie.k.f711c) {
                if (t != com.airbnb.lottie.k.y || (aVar2 = this.m) == null) {
                    if (t != com.airbnb.lottie.k.z || (aVar2 = this.n) == null) {
                        if (t == com.airbnb.lottie.k.m && (cVar3 = this.k) != null) {
                            if (cVar3 == null) {
                                this.k = new c(Collections.singletonList(new com.airbnb.lottie.x.a(Float.valueOf(0.0f))));
                            }
                            aVar = this.k;
                        } else {
                            if (t != com.airbnb.lottie.k.n || (cVar2 = this.l) == null) {
                                return false;
                            }
                            if (cVar2 == null) {
                                this.l = new c(Collections.singletonList(new com.airbnb.lottie.x.a(Float.valueOf(0.0f))));
                            }
                            aVar = this.l;
                        }
                    } else if (aVar2 == null) {
                        this.n = new p(cVar, 100);
                        return true;
                    }
                } else if (aVar2 == null) {
                    this.m = new p(cVar, 100);
                    return true;
                }
                aVar2.m(cVar);
                return true;
            }
            aVar = this.j;
            if (aVar == null) {
                this.j = new p(cVar, 100);
                return true;
            }
        }
        aVar.m(cVar);
        return true;
    }

    @Nullable
    public a<?, Float> e() {
        return this.n;
    }

    public Matrix f() {
        this.f770a.reset();
        a<?, PointF> aVar = this.g;
        if (aVar != null) {
            PointF pointFH = aVar.h();
            float f = pointFH.x;
            if (f != 0.0f || pointFH.y != 0.0f) {
                this.f770a.preTranslate(f, pointFH.y);
            }
        }
        a<Float, Float> aVar2 = this.i;
        if (aVar2 != null) {
            float fFloatValue = aVar2 instanceof p ? aVar2.h().floatValue() : ((c) aVar2).n();
            if (fFloatValue != 0.0f) {
                this.f770a.preRotate(fFloatValue);
            }
        }
        if (this.k != null) {
            float fCos = this.l == null ? 0.0f : (float) Math.cos(Math.toRadians((-r0.n()) + 90.0f));
            float fSin = this.l == null ? 1.0f : (float) Math.sin(Math.toRadians((-r4.n()) + 90.0f));
            float fTan = (float) Math.tan(Math.toRadians(this.k.n()));
            d();
            float[] fArr = this.e;
            fArr[0] = fCos;
            fArr[1] = fSin;
            float f2 = -fSin;
            fArr[3] = f2;
            fArr[4] = fCos;
            fArr[8] = 1.0f;
            this.f771b.setValues(fArr);
            d();
            float[] fArr2 = this.e;
            fArr2[0] = 1.0f;
            fArr2[3] = fTan;
            fArr2[4] = 1.0f;
            fArr2[8] = 1.0f;
            this.f772c.setValues(fArr2);
            d();
            float[] fArr3 = this.e;
            fArr3[0] = fCos;
            fArr3[1] = f2;
            fArr3[3] = fSin;
            fArr3[4] = fCos;
            fArr3[8] = 1.0f;
            this.d.setValues(fArr3);
            this.f772c.preConcat(this.f771b);
            this.d.preConcat(this.f772c);
            this.f770a.preConcat(this.d);
        }
        a<com.airbnb.lottie.x.d, com.airbnb.lottie.x.d> aVar3 = this.h;
        if (aVar3 != null) {
            com.airbnb.lottie.x.d dVarH = aVar3.h();
            if (dVarH.b() != 1.0f || dVarH.c() != 1.0f) {
                this.f770a.preScale(dVarH.b(), dVarH.c());
            }
        }
        a<PointF, PointF> aVar4 = this.f;
        if (aVar4 != null) {
            PointF pointFH2 = aVar4.h();
            float f3 = pointFH2.x;
            if (f3 != 0.0f || pointFH2.y != 0.0f) {
                this.f770a.preTranslate(-f3, -pointFH2.y);
            }
        }
        return this.f770a;
    }

    public Matrix g(float f) {
        a<?, PointF> aVar = this.g;
        PointF pointFH = aVar == null ? null : aVar.h();
        a<com.airbnb.lottie.x.d, com.airbnb.lottie.x.d> aVar2 = this.h;
        com.airbnb.lottie.x.d dVarH = aVar2 == null ? null : aVar2.h();
        this.f770a.reset();
        if (pointFH != null) {
            this.f770a.preTranslate(pointFH.x * f, pointFH.y * f);
        }
        if (dVarH != null) {
            double d = f;
            this.f770a.preScale((float) Math.pow(dVarH.b(), d), (float) Math.pow(dVarH.c(), d));
        }
        a<Float, Float> aVar3 = this.i;
        if (aVar3 != null) {
            float fFloatValue = aVar3.h().floatValue();
            a<PointF, PointF> aVar4 = this.f;
            PointF pointFH2 = aVar4 != null ? aVar4.h() : null;
            this.f770a.preRotate(fFloatValue * f, pointFH2 == null ? 0.0f : pointFH2.x, pointFH2 != null ? pointFH2.y : 0.0f);
        }
        return this.f770a;
    }

    @Nullable
    public a<?, Integer> h() {
        return this.j;
    }

    @Nullable
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
        a<com.airbnb.lottie.x.d, com.airbnb.lottie.x.d> aVar6 = this.h;
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
