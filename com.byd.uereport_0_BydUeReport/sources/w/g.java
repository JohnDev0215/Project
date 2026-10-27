package w;

import x.o;

/* JADX INFO: compiled from: StopLogic.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f8645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f8646b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f8647c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f8648d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f8649e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f8650f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f8651g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f8652h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f8653i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8654j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f8655k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f8656l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f8657m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f8658n;

    @Override // x.o
    public float a() {
        return this.f8656l ? -d(this.f8658n) : d(this.f8658n);
    }

    public final float b(float f8) {
        float f9 = this.f8648d;
        if (f8 <= f9) {
            float f10 = this.f8645a;
            return (f10 * f8) + ((((this.f8646b - f10) * f8) * f8) / (f9 * 2.0f));
        }
        int i8 = this.f8654j;
        if (i8 == 1) {
            return this.f8651g;
        }
        float f11 = f8 - f9;
        float f12 = this.f8649e;
        if (f11 < f12) {
            float f13 = this.f8651g;
            float f14 = this.f8646b;
            return f13 + (f14 * f11) + ((((this.f8647c - f14) * f11) * f11) / (f12 * 2.0f));
        }
        if (i8 == 2) {
            return this.f8652h;
        }
        float f15 = f11 - f12;
        float f16 = this.f8650f;
        if (f15 >= f16) {
            return this.f8653i;
        }
        float f17 = this.f8652h;
        float f18 = this.f8647c;
        return (f17 + (f18 * f15)) - (((f18 * f15) * f15) / (f16 * 2.0f));
    }

    public void c(float f8, float f9, float f10, float f11, float f12, float f13) {
        this.f8657m = f8;
        boolean z7 = f8 > f9;
        this.f8656l = z7;
        if (z7) {
            e(-f10, f8 - f9, f12, f13, f11);
        } else {
            e(f10, f9 - f8, f12, f13, f11);
        }
    }

    public float d(float f8) {
        float f9 = this.f8648d;
        if (f8 <= f9) {
            float f10 = this.f8645a;
            return f10 + (((this.f8646b - f10) * f8) / f9);
        }
        int i8 = this.f8654j;
        if (i8 == 1) {
            return 0.0f;
        }
        float f11 = f8 - f9;
        float f12 = this.f8649e;
        if (f11 < f12) {
            float f13 = this.f8646b;
            return f13 + (((this.f8647c - f13) * f11) / f12);
        }
        if (i8 == 2) {
            return this.f8652h;
        }
        float f14 = f11 - f12;
        float f15 = this.f8650f;
        if (f14 >= f15) {
            return this.f8653i;
        }
        float f16 = this.f8647c;
        return f16 - ((f14 * f16) / f15);
    }

    public final void e(float f8, float f9, float f10, float f11, float f12) {
        if (f8 == 0.0f) {
            f8 = 1.0E-4f;
        }
        this.f8645a = f8;
        float f13 = f8 / f10;
        float f14 = (f13 * f8) / 2.0f;
        if (f8 < 0.0f) {
            float fSqrt = (float) Math.sqrt((f9 - ((((-f8) / f10) * f8) / 2.0f)) * f10);
            if (fSqrt < f11) {
                this.f8655k = "backward accelerate, decelerate";
                this.f8654j = 2;
                this.f8645a = f8;
                this.f8646b = fSqrt;
                this.f8647c = 0.0f;
                float f15 = (fSqrt - f8) / f10;
                this.f8648d = f15;
                this.f8649e = fSqrt / f10;
                this.f8651g = ((f8 + fSqrt) * f15) / 2.0f;
                this.f8652h = f9;
                this.f8653i = f9;
                return;
            }
            this.f8655k = "backward accelerate cruse decelerate";
            this.f8654j = 3;
            this.f8645a = f8;
            this.f8646b = f11;
            this.f8647c = f11;
            float f16 = (f11 - f8) / f10;
            this.f8648d = f16;
            float f17 = f11 / f10;
            this.f8650f = f17;
            float f18 = ((f8 + f11) * f16) / 2.0f;
            float f19 = (f17 * f11) / 2.0f;
            this.f8649e = ((f9 - f18) - f19) / f11;
            this.f8651g = f18;
            this.f8652h = f9 - f19;
            this.f8653i = f9;
            return;
        }
        if (f14 >= f9) {
            this.f8655k = "hard stop";
            this.f8654j = 1;
            this.f8645a = f8;
            this.f8646b = 0.0f;
            this.f8651g = f9;
            this.f8648d = (2.0f * f9) / f8;
            return;
        }
        float f20 = f9 - f14;
        float f21 = f20 / f8;
        if (f21 + f13 < f12) {
            this.f8655k = "cruse decelerate";
            this.f8654j = 2;
            this.f8645a = f8;
            this.f8646b = f8;
            this.f8647c = 0.0f;
            this.f8651g = f20;
            this.f8652h = f9;
            this.f8648d = f21;
            this.f8649e = f13;
            return;
        }
        float fSqrt2 = (float) Math.sqrt((f10 * f9) + ((f8 * f8) / 2.0f));
        float f22 = (fSqrt2 - f8) / f10;
        this.f8648d = f22;
        float f23 = fSqrt2 / f10;
        this.f8649e = f23;
        if (fSqrt2 < f11) {
            this.f8655k = "accelerate decelerate";
            this.f8654j = 2;
            this.f8645a = f8;
            this.f8646b = fSqrt2;
            this.f8647c = 0.0f;
            this.f8648d = f22;
            this.f8649e = f23;
            this.f8651g = ((f8 + fSqrt2) * f22) / 2.0f;
            this.f8652h = f9;
            return;
        }
        this.f8655k = "accelerate cruse decelerate";
        this.f8654j = 3;
        this.f8645a = f8;
        this.f8646b = f11;
        this.f8647c = f11;
        float f24 = (f11 - f8) / f10;
        this.f8648d = f24;
        float f25 = f11 / f10;
        this.f8650f = f25;
        float f26 = ((f8 + f11) * f24) / 2.0f;
        float f27 = (f25 * f11) / 2.0f;
        this.f8649e = ((f9 - f26) - f27) / f11;
        this.f8651g = f26;
        this.f8652h = f9 - f27;
        this.f8653i = f9;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f8) {
        float fB = b(f8);
        this.f8658n = f8;
        return this.f8656l ? this.f8657m - fB : this.f8657m + fB;
    }
}
