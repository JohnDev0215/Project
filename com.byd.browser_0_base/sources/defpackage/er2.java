package defpackage;

/* JADX INFO: compiled from: SpringStopEngine.java */
/* JADX INFO: loaded from: classes.dex */
public final class er2 implements eu2 {
    public double b;
    public double c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public double a = 0.5d;
    public int i = 0;

    @Override // defpackage.eu2
    public final boolean a() {
        double d = ((double) this.e) - this.c;
        double d2 = this.b;
        double d3 = this.f;
        return Math.sqrt((((d2 * d) * d) + ((d3 * d3) * ((double) this.g))) / d2) <= ((double) this.h);
    }

    @Override // defpackage.eu2
    public final float b() {
        return 0.0f;
    }

    @Override // defpackage.eu2
    public final float getInterpolation(float f) {
        er2 er2Var = this;
        double d = f - er2Var.d;
        if (d > 0.0d) {
            double d2 = er2Var.b;
            double d3 = er2Var.a;
            int iSqrt = (int) ((9.0d / ((Math.sqrt(d2 / ((double) er2Var.g)) * d) * 4.0d)) + 1.0d);
            double d4 = d / ((double) iSqrt);
            int i = 0;
            while (i < iSqrt) {
                float f2 = er2Var.e;
                double d5 = f2;
                double d6 = er2Var.c;
                int i2 = iSqrt;
                int i3 = i;
                double d7 = (-d2) * (d5 - d6);
                float f3 = er2Var.f;
                double d8 = d2;
                double d9 = f3;
                double d10 = er2Var.g;
                double d11 = ((((d7 - (d9 * d3)) / d10) * d4) / 2.0d) + d9;
                double d12 = ((((-((((d4 * d11) / 2.0d) + d5) - d6)) * d8) - (d11 * d3)) / d10) * d4;
                float f4 = f3 + ((float) d12);
                this.f = f4;
                float f5 = f2 + ((float) (((d12 / 2.0d) + d9) * d4));
                this.e = f5;
                int i4 = this.i;
                if (i4 > 0) {
                    if (f5 < 0.0f && (i4 & 1) == 1) {
                        this.e = -f5;
                        this.f = -f4;
                    }
                    float f6 = this.e;
                    if (f6 > 1.0f && (i4 & 2) == 2) {
                        this.e = 2.0f - f6;
                        this.f = -this.f;
                    }
                }
                i = i3 + 1;
                er2Var = this;
                iSqrt = i2;
                d2 = d8;
            }
        }
        er2 er2Var2 = er2Var;
        er2Var2.d = f;
        if (a()) {
            er2Var2.e = (float) er2Var2.c;
        }
        return er2Var2.e;
    }
}
