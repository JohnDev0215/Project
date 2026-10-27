package b.e.a.a;

import androidx.constraintlayout.motion.widget.o;

/* JADX INFO: compiled from: StopLogic.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f1796a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f1797b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f1798c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f1799d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f1800e;
    private float f;
    private float g;
    private float h;
    private float i;
    private int j;
    private String k;
    private boolean l = false;
    private float m;
    private float n;

    private float b(float f) {
        float f2 = this.f1799d;
        if (f <= f2) {
            float f3 = this.f1796a;
            return (f3 * f) + ((((this.f1797b - f3) * f) * f) / (f2 * 2.0f));
        }
        int i = this.j;
        if (i == 1) {
            return this.g;
        }
        float f4 = f - f2;
        float f5 = this.f1800e;
        if (f4 < f5) {
            float f6 = this.g;
            float f7 = this.f1797b;
            return f6 + (f7 * f4) + ((((this.f1798c - f7) * f4) * f4) / (f5 * 2.0f));
        }
        if (i == 2) {
            return this.h;
        }
        float f8 = f4 - f5;
        float f9 = this.f;
        if (f8 >= f9) {
            return this.i;
        }
        float f10 = this.h;
        float f11 = this.f1798c;
        return (f10 + (f11 * f8)) - (((f11 * f8) * f8) / (f9 * 2.0f));
    }

    private void e(float f, float f2, float f3, float f4, float f5) {
        if (f == 0.0f) {
            f = 1.0E-4f;
        }
        this.f1796a = f;
        float f6 = f / f3;
        float f7 = (f6 * f) / 2.0f;
        if (f < 0.0f) {
            float fSqrt = (float) Math.sqrt((f2 - ((((-f) / f3) * f) / 2.0f)) * f3);
            if (fSqrt < f4) {
                this.k = "backward accelerate, decelerate";
                this.j = 2;
                this.f1796a = f;
                this.f1797b = fSqrt;
                this.f1798c = 0.0f;
                float f8 = (fSqrt - f) / f3;
                this.f1799d = f8;
                this.f1800e = fSqrt / f3;
                this.g = ((f + fSqrt) * f8) / 2.0f;
                this.h = f2;
                this.i = f2;
                return;
            }
            this.k = "backward accelerate cruse decelerate";
            this.j = 3;
            this.f1796a = f;
            this.f1797b = f4;
            this.f1798c = f4;
            float f9 = (f4 - f) / f3;
            this.f1799d = f9;
            float f10 = f4 / f3;
            this.f = f10;
            float f11 = ((f + f4) * f9) / 2.0f;
            float f12 = (f10 * f4) / 2.0f;
            this.f1800e = ((f2 - f11) - f12) / f4;
            this.g = f11;
            this.h = f2 - f12;
            this.i = f2;
            return;
        }
        if (f7 >= f2) {
            this.k = "hard stop";
            this.j = 1;
            this.f1796a = f;
            this.f1797b = 0.0f;
            this.g = f2;
            this.f1799d = (2.0f * f2) / f;
            return;
        }
        float f13 = f2 - f7;
        float f14 = f13 / f;
        if (f14 + f6 < f5) {
            this.k = "cruse decelerate";
            this.j = 2;
            this.f1796a = f;
            this.f1797b = f;
            this.f1798c = 0.0f;
            this.g = f13;
            this.h = f2;
            this.f1799d = f14;
            this.f1800e = f6;
            return;
        }
        float fSqrt2 = (float) Math.sqrt((f3 * f2) + ((f * f) / 2.0f));
        float f15 = (fSqrt2 - f) / f3;
        this.f1799d = f15;
        float f16 = fSqrt2 / f3;
        this.f1800e = f16;
        if (fSqrt2 < f4) {
            this.k = "accelerate decelerate";
            this.j = 2;
            this.f1796a = f;
            this.f1797b = fSqrt2;
            this.f1798c = 0.0f;
            this.f1799d = f15;
            this.f1800e = f16;
            this.g = ((f + fSqrt2) * f15) / 2.0f;
            this.h = f2;
            return;
        }
        this.k = "accelerate cruse decelerate";
        this.j = 3;
        this.f1796a = f;
        this.f1797b = f4;
        this.f1798c = f4;
        float f17 = (f4 - f) / f3;
        this.f1799d = f17;
        float f18 = f4 / f3;
        this.f = f18;
        float f19 = ((f + f4) * f17) / 2.0f;
        float f20 = (f18 * f4) / 2.0f;
        this.f1800e = ((f2 - f19) - f20) / f4;
        this.g = f19;
        this.h = f2 - f20;
        this.i = f2;
    }

    @Override // androidx.constraintlayout.motion.widget.o
    public float a() {
        return this.l ? -d(this.n) : d(this.n);
    }

    public void c(float f, float f2, float f3, float f4, float f5, float f6) {
        this.m = f;
        boolean z = f > f2;
        this.l = z;
        if (z) {
            e(-f3, f - f2, f5, f6, f4);
        } else {
            e(f3, f2 - f, f5, f6, f4);
        }
    }

    public float d(float f) {
        float f2 = this.f1799d;
        if (f <= f2) {
            float f3 = this.f1796a;
            return f3 + (((this.f1797b - f3) * f) / f2);
        }
        int i = this.j;
        if (i == 1) {
            return 0.0f;
        }
        float f4 = f - f2;
        float f5 = this.f1800e;
        if (f4 < f5) {
            float f6 = this.f1797b;
            return f6 + (((this.f1798c - f6) * f4) / f5);
        }
        if (i == 2) {
            return this.h;
        }
        float f7 = f4 - f5;
        float f8 = this.f;
        if (f7 >= f8) {
            return this.i;
        }
        float f9 = this.f1798c;
        return f9 - ((f7 * f9) / f8);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        float fB = b(f);
        this.n = f;
        return this.l ? this.m - fB : this.m + fB;
    }
}
