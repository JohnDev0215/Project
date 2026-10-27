package x;

/* JADX INFO: renamed from: x.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2638h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f23539a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f23540b;
    public boolean c;
    public double d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f23541e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public double f23542f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f23543g;

    public final long a(float f10, float f11, long j3) {
        double dCos;
        double dExp;
        if (!this.c) {
            if (this.f23539a == Float.MAX_VALUE) {
                throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
            }
            float f12 = this.f23543g;
            double d = f12;
            double d10 = d * d;
            if (f12 > 1.0f) {
                double d11 = this.f23540b;
                double d12 = d10 - ((double) 1);
                this.d = (Math.sqrt(d12) * d11) + (((double) (-f12)) * d11);
                double d13 = -this.f23543g;
                double d14 = this.f23540b;
                this.f23541e = (d13 * d14) - (Math.sqrt(d12) * d14);
            } else if (f12 >= 0.0f && f12 < 1.0f) {
                this.f23542f = Math.sqrt(((double) 1) - d10) * this.f23540b;
            }
            this.c = true;
        }
        float f13 = f10 - this.f23539a;
        double d15 = j3 / 1000.0d;
        float f14 = this.f23543g;
        if (f14 > 1.0f) {
            double d16 = f13;
            double d17 = this.f23541e;
            double d18 = ((d17 * d16) - ((double) f11)) / (d17 - this.d);
            double d19 = d16 - d18;
            dExp = (Math.exp(this.d * d15) * d18) + (Math.exp(d17 * d15) * d19);
            double d20 = this.f23541e;
            double dExp2 = Math.exp(d20 * d15) * d19 * d20;
            double d21 = this.d;
            dCos = (Math.exp(d21 * d15) * d18 * d21) + dExp2;
        } else if (f14 == 1.0f) {
            double d22 = this.f23540b;
            double d23 = f13;
            double d24 = (d22 * d23) + ((double) f11);
            double d25 = (d24 * d15) + d23;
            dExp = Math.exp((-d22) * d15) * d25;
            double dExp3 = Math.exp((-this.f23540b) * d15) * d25;
            double d26 = -this.f23540b;
            dCos = (dExp3 * d26) + (Math.exp(d26 * d15) * d24);
        } else {
            double d27 = ((double) 1) / this.f23542f;
            double d28 = this.f23540b;
            double d29 = f13;
            double d30 = ((((double) f14) * d28 * d29) + ((double) f11)) * d27;
            double dExp4 = Math.exp(((double) (-f14)) * d28 * d15) * ((Math.sin(this.f23542f * d15) * d30) + (Math.cos(this.f23542f * d15) * d29));
            double d31 = this.f23540b;
            float f15 = this.f23543g;
            double d32 = (-d31) * dExp4 * ((double) f15);
            double dExp5 = Math.exp(((double) (-f15)) * d31 * d15);
            double d33 = this.f23542f;
            double dSin = Math.sin(d33 * d15) * (-d33) * d29;
            double d34 = this.f23542f;
            dCos = (((Math.cos(d34 * d15) * d30 * d34) + dSin) * dExp5) + d32;
            dExp = dExp4;
        }
        return (((long) Float.floatToRawIntBits((float) dCos)) & 4294967295L) | (Float.floatToRawIntBits((float) (dExp + ((double) this.f23539a))) << 32);
    }
}
