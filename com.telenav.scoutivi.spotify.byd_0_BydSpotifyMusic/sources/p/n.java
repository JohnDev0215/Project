package p;

/* JADX INFO: compiled from: StopLogicEngine.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f8781a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f8782b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f8783c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f8784d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f8785e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f8786f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f8787g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f8788h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f8789i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f8790j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private String f8791k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private float f8793m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f8794n;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f8792l = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f8795o = false;

    private float c(float f7) {
        this.f8795o = false;
        float f8 = this.f8784d;
        if (f7 <= f8) {
            float f9 = this.f8781a;
            return (f9 * f7) + ((((this.f8782b - f9) * f7) * f7) / (f8 * 2.0f));
        }
        int i7 = this.f8790j;
        if (i7 == 1) {
            return this.f8787g;
        }
        float f10 = f7 - f8;
        float f11 = this.f8785e;
        if (f10 < f11) {
            float f12 = this.f8787g;
            float f13 = this.f8782b;
            return f12 + (f13 * f10) + ((((this.f8783c - f13) * f10) * f10) / (f11 * 2.0f));
        }
        if (i7 == 2) {
            return this.f8788h;
        }
        float f14 = f10 - f11;
        float f15 = this.f8786f;
        if (f14 > f15) {
            this.f8795o = true;
            return this.f8789i;
        }
        float f16 = this.f8788h;
        float f17 = this.f8783c;
        return (f16 + (f17 * f14)) - (((f17 * f14) * f14) / (f15 * 2.0f));
    }

    private void f(float f7, float f8, float f9, float f10, float f11) {
        this.f8795o = false;
        if (f7 == 0.0f) {
            f7 = 1.0E-4f;
        }
        this.f8781a = f7;
        float f12 = f7 / f9;
        float f13 = (f12 * f7) / 2.0f;
        if (f7 < 0.0f) {
            float fSqrt = (float) Math.sqrt((f8 - ((((-f7) / f9) * f7) / 2.0f)) * f9);
            if (fSqrt < f10) {
                this.f8791k = "backward accelerate, decelerate";
                this.f8790j = 2;
                this.f8781a = f7;
                this.f8782b = fSqrt;
                this.f8783c = 0.0f;
                float f14 = (fSqrt - f7) / f9;
                this.f8784d = f14;
                this.f8785e = fSqrt / f9;
                this.f8787g = ((f7 + fSqrt) * f14) / 2.0f;
                this.f8788h = f8;
                this.f8789i = f8;
                return;
            }
            this.f8791k = "backward accelerate cruse decelerate";
            this.f8790j = 3;
            this.f8781a = f7;
            this.f8782b = f10;
            this.f8783c = f10;
            float f15 = (f10 - f7) / f9;
            this.f8784d = f15;
            float f16 = f10 / f9;
            this.f8786f = f16;
            float f17 = ((f7 + f10) * f15) / 2.0f;
            float f18 = (f16 * f10) / 2.0f;
            this.f8785e = ((f8 - f17) - f18) / f10;
            this.f8787g = f17;
            this.f8788h = f8 - f18;
            this.f8789i = f8;
            return;
        }
        if (f13 >= f8) {
            this.f8791k = "hard stop";
            this.f8790j = 1;
            this.f8781a = f7;
            this.f8782b = 0.0f;
            this.f8787g = f8;
            this.f8784d = (2.0f * f8) / f7;
            return;
        }
        float f19 = f8 - f13;
        float f20 = f19 / f7;
        if (f20 + f12 < f11) {
            this.f8791k = "cruse decelerate";
            this.f8790j = 2;
            this.f8781a = f7;
            this.f8782b = f7;
            this.f8783c = 0.0f;
            this.f8787g = f19;
            this.f8788h = f8;
            this.f8784d = f20;
            this.f8785e = f12;
            return;
        }
        float fSqrt2 = (float) Math.sqrt((f9 * f8) + ((f7 * f7) / 2.0f));
        float f21 = (fSqrt2 - f7) / f9;
        this.f8784d = f21;
        float f22 = fSqrt2 / f9;
        this.f8785e = f22;
        if (fSqrt2 < f10) {
            this.f8791k = "accelerate decelerate";
            this.f8790j = 2;
            this.f8781a = f7;
            this.f8782b = fSqrt2;
            this.f8783c = 0.0f;
            this.f8784d = f21;
            this.f8785e = f22;
            this.f8787g = ((f7 + fSqrt2) * f21) / 2.0f;
            this.f8788h = f8;
            return;
        }
        this.f8791k = "accelerate cruse decelerate";
        this.f8790j = 3;
        this.f8781a = f7;
        this.f8782b = f10;
        this.f8783c = f10;
        float f23 = (f10 - f7) / f9;
        this.f8784d = f23;
        float f24 = f10 / f9;
        this.f8786f = f24;
        float f25 = ((f7 + f10) * f23) / 2.0f;
        float f26 = (f24 * f10) / 2.0f;
        this.f8785e = ((f8 - f25) - f26) / f10;
        this.f8787g = f25;
        this.f8788h = f8 - f26;
        this.f8789i = f8;
    }

    @Override // p.m
    public boolean a() {
        return b() < 1.0E-5f && Math.abs(this.f8789i - this.f8794n) < 1.0E-5f;
    }

    @Override // p.m
    public float b() {
        return this.f8792l ? -e(this.f8794n) : e(this.f8794n);
    }

    public void d(float f7, float f8, float f9, float f10, float f11, float f12) {
        this.f8795o = false;
        this.f8793m = f7;
        boolean z6 = f7 > f8;
        this.f8792l = z6;
        if (z6) {
            f(-f9, f7 - f8, f11, f12, f10);
        } else {
            f(f9, f8 - f7, f11, f12, f10);
        }
    }

    public float e(float f7) {
        float f8 = this.f8784d;
        if (f7 <= f8) {
            float f9 = this.f8781a;
            return f9 + (((this.f8782b - f9) * f7) / f8);
        }
        int i7 = this.f8790j;
        if (i7 == 1) {
            return 0.0f;
        }
        float f10 = f7 - f8;
        float f11 = this.f8785e;
        if (f10 < f11) {
            float f12 = this.f8782b;
            return f12 + (((this.f8783c - f12) * f10) / f11);
        }
        if (i7 == 2) {
            return this.f8788h;
        }
        float f13 = f10 - f11;
        float f14 = this.f8786f;
        if (f13 >= f14) {
            return this.f8789i;
        }
        float f15 = this.f8783c;
        return f15 - ((f13 * f15) / f14);
    }

    @Override // p.m
    public float getInterpolation(float f7) {
        float fC = c(f7);
        this.f8794n = f7;
        return this.f8792l ? this.f8793m - fC : this.f8793m + fC;
    }
}
