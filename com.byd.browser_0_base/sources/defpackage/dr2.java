package defpackage;

import defpackage.af0;

/* JADX INFO: compiled from: SpringForce.java */
/* JADX INFO: loaded from: classes.dex */
public final class dr2 {
    public double a;
    public double b;
    public boolean c;
    public double d;
    public double e;
    public double f;
    public double g;
    public double h;
    public double i;
    public final af0.g j;

    public dr2() {
        this.a = Math.sqrt(1500.0d);
        this.b = 0.5d;
        this.c = false;
        this.i = Double.MAX_VALUE;
        this.j = new af0.g();
    }

    public final af0.g a(double d, double d2, long j) {
        double dCos;
        double dPow;
        if (!this.c) {
            if (this.i == Double.MAX_VALUE) {
                throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
            }
            double d3 = this.b;
            if (d3 > 1.0d) {
                double d4 = this.a;
                this.f = (Math.sqrt((d3 * d3) - 1.0d) * d4) + ((-d3) * d4);
                double d5 = this.b;
                double d6 = this.a;
                this.g = ((-d5) * d6) - (Math.sqrt((d5 * d5) - 1.0d) * d6);
            } else if (d3 >= 0.0d && d3 < 1.0d) {
                this.h = Math.sqrt(1.0d - (d3 * d3)) * this.a;
            }
            this.c = true;
        }
        double d7 = j / 1000.0d;
        double d8 = d - this.i;
        double d9 = this.b;
        if (d9 > 1.0d) {
            double d10 = this.g;
            double d11 = this.f;
            double d12 = d8 - (((d10 * d8) - d2) / (d10 - d11));
            double d13 = ((d8 * d10) - d2) / (d10 - d11);
            dPow = (Math.pow(2.718281828459045d, this.f * d7) * d13) + (Math.pow(2.718281828459045d, d10 * d7) * d12);
            double d14 = this.g;
            double dPow2 = Math.pow(2.718281828459045d, d14 * d7) * d12 * d14;
            double d15 = this.f;
            dCos = (Math.pow(2.718281828459045d, d15 * d7) * d13 * d15) + dPow2;
        } else if (d9 == 1.0d) {
            double d16 = this.a;
            double d17 = (d16 * d8) + d2;
            double d18 = (d17 * d7) + d8;
            double dPow3 = Math.pow(2.718281828459045d, (-d16) * d7) * d18;
            double dPow4 = Math.pow(2.718281828459045d, (-this.a) * d7) * d18;
            double d19 = this.a;
            dCos = (Math.pow(2.718281828459045d, (-d19) * d7) * d17) + (dPow4 * (-d19));
            dPow = dPow3;
        } else {
            double d20 = 1.0d / this.h;
            double d21 = this.a;
            double d22 = ((d9 * d21 * d8) + d2) * d20;
            double dSin = ((Math.sin(this.h * d7) * d22) + (Math.cos(this.h * d7) * d8)) * Math.pow(2.718281828459045d, (-d9) * d21 * d7);
            double d23 = this.a;
            double d24 = this.b;
            double d25 = (-d23) * dSin * d24;
            double dPow5 = Math.pow(2.718281828459045d, (-d24) * d23 * d7);
            double d26 = this.h;
            double dSin2 = Math.sin(d26 * d7) * (-d26) * d8;
            double d27 = this.h;
            dCos = (((Math.cos(d27 * d7) * d22 * d27) + dSin2) * dPow5) + d25;
            dPow = dSin;
        }
        float f = (float) (dPow + this.i);
        af0.g gVar = this.j;
        gVar.a = f;
        gVar.b = (float) dCos;
        return gVar;
    }

    public dr2(float f) {
        this.a = Math.sqrt(1500.0d);
        this.b = 0.5d;
        this.c = false;
        this.j = new af0.g();
        this.i = f;
    }
}
