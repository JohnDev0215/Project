package com.google.zxing.common;

/* JADX INFO: compiled from: PerspectiveTransform.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f4217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f4218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final float f4219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float f4220d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final float f4221e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final float f4222f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final float f4223g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final float f4224h;
    private final float i;

    private j(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        this.f4217a = f2;
        this.f4218b = f5;
        this.f4219c = f8;
        this.f4220d = f3;
        this.f4221e = f6;
        this.f4222f = f9;
        this.f4223g = f4;
        this.f4224h = f7;
        this.i = f10;
    }

    public static j b(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) {
        return d(f10, f11, f12, f13, f14, f15, f16, f17).e(c(f2, f3, f4, f5, f6, f7, f8, f9));
    }

    public static j c(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        return d(f2, f3, f4, f5, f6, f7, f8, f9).a();
    }

    public static j d(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        float f10 = ((f2 - f4) + f6) - f8;
        float f11 = ((f3 - f5) + f7) - f9;
        if (f10 == 0.0f && f11 == 0.0f) {
            return new j(f4 - f2, f6 - f4, f2, f5 - f3, f7 - f5, f3, 0.0f, 0.0f, 1.0f);
        }
        float f12 = f4 - f6;
        float f13 = f8 - f6;
        float f14 = f5 - f7;
        float f15 = f9 - f7;
        float f16 = (f12 * f15) - (f13 * f14);
        float f17 = ((f15 * f10) - (f13 * f11)) / f16;
        float f18 = ((f12 * f11) - (f10 * f14)) / f16;
        return new j((f17 * f4) + (f4 - f2), (f18 * f8) + (f8 - f2), f2, (f5 - f3) + (f17 * f5), (f9 - f3) + (f18 * f9), f3, f17, f18, 1.0f);
    }

    j a() {
        float f2 = this.f4221e;
        float f3 = this.i;
        float f4 = this.f4222f;
        float f5 = this.f4224h;
        float f6 = (f2 * f3) - (f4 * f5);
        float f7 = this.f4223g;
        float f8 = this.f4220d;
        float f9 = (f4 * f7) - (f8 * f3);
        float f10 = (f8 * f5) - (f2 * f7);
        float f11 = this.f4219c;
        float f12 = this.f4218b;
        float f13 = (f11 * f5) - (f12 * f3);
        float f14 = this.f4217a;
        return new j(f6, f9, f10, f13, (f3 * f14) - (f11 * f7), (f7 * f12) - (f5 * f14), (f12 * f4) - (f11 * f2), (f11 * f8) - (f4 * f14), (f14 * f2) - (f12 * f8));
    }

    j e(j jVar) {
        float f2 = this.f4217a;
        float f3 = jVar.f4217a;
        float f4 = this.f4220d;
        float f5 = jVar.f4218b;
        float f6 = this.f4223g;
        float f7 = jVar.f4219c;
        float f8 = (f2 * f3) + (f4 * f5) + (f6 * f7);
        float f9 = jVar.f4220d;
        float f10 = jVar.f4221e;
        float f11 = jVar.f4222f;
        float f12 = (f2 * f9) + (f4 * f10) + (f6 * f11);
        float f13 = jVar.f4223g;
        float f14 = jVar.f4224h;
        float f15 = jVar.i;
        float f16 = (f2 * f13) + (f4 * f14) + (f6 * f15);
        float f17 = this.f4218b;
        float f18 = this.f4221e;
        float f19 = this.f4224h;
        float f20 = (f17 * f3) + (f18 * f5) + (f19 * f7);
        float f21 = (f17 * f9) + (f18 * f10) + (f19 * f11);
        float f22 = (f19 * f15) + (f17 * f13) + (f18 * f14);
        float f23 = this.f4219c;
        float f24 = this.f4222f;
        float f25 = (f3 * f23) + (f5 * f24);
        float f26 = this.i;
        return new j(f8, f12, f16, f20, f21, f22, (f7 * f26) + f25, (f9 * f23) + (f10 * f24) + (f11 * f26), (f23 * f13) + (f24 * f14) + (f26 * f15));
    }

    public void f(float[] fArr) {
        int length = fArr.length;
        float f2 = this.f4217a;
        float f3 = this.f4218b;
        float f4 = this.f4219c;
        float f5 = this.f4220d;
        float f6 = this.f4221e;
        float f7 = this.f4222f;
        float f8 = this.f4223g;
        float f9 = this.f4224h;
        float f10 = this.i;
        for (int i = 0; i < length; i += 2) {
            float f11 = fArr[i];
            int i2 = i + 1;
            float f12 = fArr[i2];
            float f13 = (f4 * f11) + (f7 * f12) + f10;
            fArr[i] = (((f2 * f11) + (f5 * f12)) + f8) / f13;
            fArr[i2] = (((f11 * f3) + (f12 * f6)) + f9) / f13;
        }
    }
}
