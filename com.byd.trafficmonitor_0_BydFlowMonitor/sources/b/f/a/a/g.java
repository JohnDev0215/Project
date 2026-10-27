package b.f.a.a;

import b.f.a.b.o;

/* JADX INFO: compiled from: StopLogic.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f2016a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f2017b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f2018c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f2019d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f2020e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f2021f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f2022g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f2023h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f2024i;
    public int j;
    public String k;
    public boolean l = false;
    public float m;
    public float n;

    @Override // b.f.a.b.o
    public float a() {
        return this.l ? -d(this.n) : d(this.n);
    }

    public final float b(float f2) {
        float f3 = this.f2019d;
        if (f2 <= f3) {
            float f4 = this.f2016a;
            return (f4 * f2) + ((((this.f2017b - f4) * f2) * f2) / (f3 * 2.0f));
        }
        int i2 = this.j;
        if (i2 == 1) {
            return this.f2022g;
        }
        float f5 = f2 - f3;
        float f6 = this.f2020e;
        if (f5 < f6) {
            float f7 = this.f2022g;
            float f8 = this.f2017b;
            return f7 + (f8 * f5) + ((((this.f2018c - f8) * f5) * f5) / (f6 * 2.0f));
        }
        if (i2 == 2) {
            return this.f2023h;
        }
        float f9 = f5 - f6;
        float f10 = this.f2021f;
        if (f9 >= f10) {
            return this.f2024i;
        }
        float f11 = this.f2023h;
        float f12 = this.f2018c;
        return (f11 + (f12 * f9)) - (((f12 * f9) * f9) / (f10 * 2.0f));
    }

    public void c(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.m = f2;
        boolean z = f2 > f3;
        this.l = z;
        if (z) {
            e(-f4, f2 - f3, f6, f7, f5);
        } else {
            e(f4, f3 - f2, f6, f7, f5);
        }
    }

    public float d(float f2) {
        float f3 = this.f2019d;
        if (f2 <= f3) {
            float f4 = this.f2016a;
            return f4 + (((this.f2017b - f4) * f2) / f3);
        }
        int i2 = this.j;
        if (i2 == 1) {
            return 0.0f;
        }
        float f5 = f2 - f3;
        float f6 = this.f2020e;
        if (f5 < f6) {
            float f7 = this.f2017b;
            return f7 + (((this.f2018c - f7) * f5) / f6);
        }
        if (i2 == 2) {
            return this.f2023h;
        }
        float f8 = f5 - f6;
        float f9 = this.f2021f;
        if (f8 >= f9) {
            return this.f2024i;
        }
        float f10 = this.f2018c;
        return f10 - ((f8 * f10) / f9);
    }

    public final void e(float f2, float f3, float f4, float f5, float f6) {
        if (f2 == 0.0f) {
            f2 = 1.0E-4f;
        }
        this.f2016a = f2;
        float f7 = f2 / f4;
        float f8 = (f7 * f2) / 2.0f;
        if (f2 < 0.0f) {
            float fSqrt = (float) Math.sqrt((f3 - ((((-f2) / f4) * f2) / 2.0f)) * f4);
            if (fSqrt < f5) {
                this.k = "backward accelerate, decelerate";
                this.j = 2;
                this.f2016a = f2;
                this.f2017b = fSqrt;
                this.f2018c = 0.0f;
                float f9 = (fSqrt - f2) / f4;
                this.f2019d = f9;
                this.f2020e = fSqrt / f4;
                this.f2022g = ((f2 + fSqrt) * f9) / 2.0f;
                this.f2023h = f3;
                this.f2024i = f3;
                return;
            }
            this.k = "backward accelerate cruse decelerate";
            this.j = 3;
            this.f2016a = f2;
            this.f2017b = f5;
            this.f2018c = f5;
            float f10 = (f5 - f2) / f4;
            this.f2019d = f10;
            float f11 = f5 / f4;
            this.f2021f = f11;
            float f12 = ((f2 + f5) * f10) / 2.0f;
            float f13 = (f11 * f5) / 2.0f;
            this.f2020e = ((f3 - f12) - f13) / f5;
            this.f2022g = f12;
            this.f2023h = f3 - f13;
            this.f2024i = f3;
            return;
        }
        if (f8 >= f3) {
            this.k = "hard stop";
            this.j = 1;
            this.f2016a = f2;
            this.f2017b = 0.0f;
            this.f2022g = f3;
            this.f2019d = (2.0f * f3) / f2;
            return;
        }
        float f14 = f3 - f8;
        float f15 = f14 / f2;
        if (f15 + f7 < f6) {
            this.k = "cruse decelerate";
            this.j = 2;
            this.f2016a = f2;
            this.f2017b = f2;
            this.f2018c = 0.0f;
            this.f2022g = f14;
            this.f2023h = f3;
            this.f2019d = f15;
            this.f2020e = f7;
            return;
        }
        float fSqrt2 = (float) Math.sqrt((f4 * f3) + ((f2 * f2) / 2.0f));
        float f16 = (fSqrt2 - f2) / f4;
        this.f2019d = f16;
        float f17 = fSqrt2 / f4;
        this.f2020e = f17;
        if (fSqrt2 < f5) {
            this.k = "accelerate decelerate";
            this.j = 2;
            this.f2016a = f2;
            this.f2017b = fSqrt2;
            this.f2018c = 0.0f;
            this.f2019d = f16;
            this.f2020e = f17;
            this.f2022g = ((f2 + fSqrt2) * f16) / 2.0f;
            this.f2023h = f3;
            return;
        }
        this.k = "accelerate cruse decelerate";
        this.j = 3;
        this.f2016a = f2;
        this.f2017b = f5;
        this.f2018c = f5;
        float f18 = (f5 - f2) / f4;
        this.f2019d = f18;
        float f19 = f5 / f4;
        this.f2021f = f19;
        float f20 = ((f2 + f5) * f18) / 2.0f;
        float f21 = (f19 * f5) / 2.0f;
        this.f2020e = ((f3 - f20) - f21) / f5;
        this.f2022g = f20;
        this.f2023h = f3 - f21;
        this.f2024i = f3;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        float fB = b(f2);
        this.n = f2;
        return this.l ? this.m - fB : this.m + fB;
    }
}
