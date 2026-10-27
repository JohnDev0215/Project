package t;

import u.o;

/* JADX INFO: compiled from: StopLogic.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f4970a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f4971b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f4972c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f4973d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f4974e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f4975f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4976g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f4977h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f4978i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4979j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f4980k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f4981l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f4982m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f4983n;

    @Override // u.o
    public float a() {
        return this.f4981l ? -d(this.f4983n) : d(this.f4983n);
    }

    public final float b(float f3) {
        float f4 = this.f4973d;
        if (f3 <= f4) {
            float f5 = this.f4970a;
            return (f5 * f3) + ((((this.f4971b - f5) * f3) * f3) / (f4 * 2.0f));
        }
        int i3 = this.f4979j;
        if (i3 == 1) {
            return this.f4976g;
        }
        float f6 = f3 - f4;
        float f7 = this.f4974e;
        if (f6 < f7) {
            float f8 = this.f4976g;
            float f9 = this.f4971b;
            return f8 + (f9 * f6) + ((((this.f4972c - f9) * f6) * f6) / (f7 * 2.0f));
        }
        if (i3 == 2) {
            return this.f4977h;
        }
        float f10 = f6 - f7;
        float f11 = this.f4975f;
        if (f10 >= f11) {
            return this.f4978i;
        }
        float f12 = this.f4977h;
        float f13 = this.f4972c;
        return (f12 + (f13 * f10)) - (((f13 * f10) * f10) / (f11 * 2.0f));
    }

    public void c(float f3, float f4, float f5, float f6, float f7, float f8) {
        this.f4982m = f3;
        boolean z3 = f3 > f4;
        this.f4981l = z3;
        if (z3) {
            e(-f5, f3 - f4, f7, f8, f6);
        } else {
            e(f5, f4 - f3, f7, f8, f6);
        }
    }

    public float d(float f3) {
        float f4 = this.f4973d;
        if (f3 <= f4) {
            float f5 = this.f4970a;
            return f5 + (((this.f4971b - f5) * f3) / f4);
        }
        int i3 = this.f4979j;
        if (i3 == 1) {
            return 0.0f;
        }
        float f6 = f3 - f4;
        float f7 = this.f4974e;
        if (f6 < f7) {
            float f8 = this.f4971b;
            return f8 + (((this.f4972c - f8) * f6) / f7);
        }
        if (i3 == 2) {
            return this.f4977h;
        }
        float f9 = f6 - f7;
        float f10 = this.f4975f;
        if (f9 >= f10) {
            return this.f4978i;
        }
        float f11 = this.f4972c;
        return f11 - ((f9 * f11) / f10);
    }

    public final void e(float f3, float f4, float f5, float f6, float f7) {
        if (f3 == 0.0f) {
            f3 = 1.0E-4f;
        }
        this.f4970a = f3;
        float f8 = f3 / f5;
        float f9 = (f8 * f3) / 2.0f;
        if (f3 < 0.0f) {
            float fSqrt = (float) Math.sqrt((f4 - ((((-f3) / f5) * f3) / 2.0f)) * f5);
            if (fSqrt < f6) {
                this.f4980k = "backward accelerate, decelerate";
                this.f4979j = 2;
                this.f4970a = f3;
                this.f4971b = fSqrt;
                this.f4972c = 0.0f;
                float f10 = (fSqrt - f3) / f5;
                this.f4973d = f10;
                this.f4974e = fSqrt / f5;
                this.f4976g = ((f3 + fSqrt) * f10) / 2.0f;
                this.f4977h = f4;
                this.f4978i = f4;
                return;
            }
            this.f4980k = "backward accelerate cruse decelerate";
            this.f4979j = 3;
            this.f4970a = f3;
            this.f4971b = f6;
            this.f4972c = f6;
            float f11 = (f6 - f3) / f5;
            this.f4973d = f11;
            float f12 = f6 / f5;
            this.f4975f = f12;
            float f13 = ((f3 + f6) * f11) / 2.0f;
            float f14 = (f12 * f6) / 2.0f;
            this.f4974e = ((f4 - f13) - f14) / f6;
            this.f4976g = f13;
            this.f4977h = f4 - f14;
            this.f4978i = f4;
            return;
        }
        if (f9 >= f4) {
            this.f4980k = "hard stop";
            this.f4979j = 1;
            this.f4970a = f3;
            this.f4971b = 0.0f;
            this.f4976g = f4;
            this.f4973d = (2.0f * f4) / f3;
            return;
        }
        float f15 = f4 - f9;
        float f16 = f15 / f3;
        if (f16 + f8 < f7) {
            this.f4980k = "cruse decelerate";
            this.f4979j = 2;
            this.f4970a = f3;
            this.f4971b = f3;
            this.f4972c = 0.0f;
            this.f4976g = f15;
            this.f4977h = f4;
            this.f4973d = f16;
            this.f4974e = f8;
            return;
        }
        float fSqrt2 = (float) Math.sqrt((f5 * f4) + ((f3 * f3) / 2.0f));
        float f17 = (fSqrt2 - f3) / f5;
        this.f4973d = f17;
        float f18 = fSqrt2 / f5;
        this.f4974e = f18;
        if (fSqrt2 < f6) {
            this.f4980k = "accelerate decelerate";
            this.f4979j = 2;
            this.f4970a = f3;
            this.f4971b = fSqrt2;
            this.f4972c = 0.0f;
            this.f4973d = f17;
            this.f4974e = f18;
            this.f4976g = ((f3 + fSqrt2) * f17) / 2.0f;
            this.f4977h = f4;
            return;
        }
        this.f4980k = "accelerate cruse decelerate";
        this.f4979j = 3;
        this.f4970a = f3;
        this.f4971b = f6;
        this.f4972c = f6;
        float f19 = (f6 - f3) / f5;
        this.f4973d = f19;
        float f20 = f6 / f5;
        this.f4975f = f20;
        float f21 = ((f3 + f6) * f19) / 2.0f;
        float f22 = (f20 * f6) / 2.0f;
        this.f4974e = ((f4 - f21) - f22) / f6;
        this.f4976g = f21;
        this.f4977h = f4 - f22;
        this.f4978i = f4;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f3) {
        float fB = b(f3);
        this.f4983n = f3;
        return this.f4981l ? this.f4982m - fB : this.f4982m + fB;
    }
}
