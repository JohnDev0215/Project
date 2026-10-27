package androidx.constraintlayout.core.j.a;

/* JADX INFO: compiled from: SpringStopEngine.java */
/* JADX INFO: loaded from: classes.dex */
public class k implements m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f791c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private double f792d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private double f793e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    double f789a = 0.5d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f790b = false;
    private int k = 0;

    private void c(double d2) {
        double d3 = this.f791c;
        double d4 = this.f789a;
        int iSqrt = (int) ((9.0d / ((Math.sqrt(d3 / ((double) this.i)) * d2) * 4.0d)) + 1.0d);
        double d5 = d2 / ((double) iSqrt);
        int i = 0;
        while (i < iSqrt) {
            float f = this.g;
            double d6 = this.f792d;
            float f2 = this.h;
            double d7 = d3;
            double d8 = ((-d3) * (((double) f) - d6)) - (((double) f2) * d4);
            float f3 = this.i;
            double d9 = d4;
            double d10 = ((double) f2) + (((d8 / ((double) f3)) * d5) / 2.0d);
            double d11 = ((((-((((double) f) + ((d5 * d10) / 2.0d)) - d6)) * d7) - (d10 * d9)) / ((double) f3)) * d5;
            float f4 = (float) (((double) f2) + d11);
            this.h = f4;
            float f5 = (float) (((double) f) + ((((double) f2) + (d11 / 2.0d)) * d5));
            this.g = f5;
            int i2 = this.k;
            if (i2 > 0) {
                if (f5 < 0.0f && (i2 & 1) == 1) {
                    this.g = -f5;
                    this.h = -f4;
                }
                float f6 = this.g;
                if (f6 > 1.0f && (i2 & 2) == 2) {
                    this.g = 2.0f - f6;
                    this.h = -this.h;
                }
            }
            i++;
            d3 = d7;
            d4 = d9;
        }
    }

    @Override // androidx.constraintlayout.core.j.a.m
    public boolean a() {
        double d2 = ((double) this.g) - this.f792d;
        double d3 = this.f791c;
        double d4 = this.h;
        return Math.sqrt((((d4 * d4) * ((double) this.i)) + ((d3 * d2) * d2)) / d3) <= ((double) this.j);
    }

    @Override // androidx.constraintlayout.core.j.a.m
    public float b() {
        return 0.0f;
    }

    public void d(float f, float f2, float f3, float f4, float f5, float f6, float f7, int i) {
        this.f792d = f2;
        this.f789a = f6;
        this.f790b = false;
        this.g = f;
        this.f793e = f3;
        this.f791c = f5;
        this.i = f4;
        this.j = f7;
        this.k = i;
        this.f = 0.0f;
    }

    @Override // androidx.constraintlayout.core.j.a.m
    public float getInterpolation(float f) {
        c(f - this.f);
        this.f = f;
        return this.g;
    }
}
