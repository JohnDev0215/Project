package p;

/* JADX INFO: compiled from: SpringStopEngine.java */
/* JADX INFO: loaded from: classes.dex */
public class k implements m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f8771c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private double f8772d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private double f8773e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f8774f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f8775g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f8776h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f8777i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private float f8778j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    double f8769a = 0.5d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f8770b = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f8779k = 0;

    private void c(double d7) {
        double d8 = this.f8771c;
        double d9 = this.f8769a;
        int iSqrt = (int) ((9.0d / ((Math.sqrt(d8 / ((double) this.f8777i)) * d7) * 4.0d)) + 1.0d);
        double d10 = d7 / ((double) iSqrt);
        int i7 = 0;
        while (i7 < iSqrt) {
            float f7 = this.f8775g;
            double d11 = this.f8772d;
            float f8 = this.f8776h;
            double d12 = d8;
            double d13 = ((-d8) * (((double) f7) - d11)) - (((double) f8) * d9);
            float f9 = this.f8777i;
            double d14 = d9;
            double d15 = ((double) f8) + (((d13 / ((double) f9)) * d10) / 2.0d);
            double d16 = ((((-((((double) f7) + ((d10 * d15) / 2.0d)) - d11)) * d12) - (d15 * d14)) / ((double) f9)) * d10;
            float f10 = (float) (((double) f8) + d16);
            this.f8776h = f10;
            float f11 = (float) (((double) f7) + ((((double) f8) + (d16 / 2.0d)) * d10));
            this.f8775g = f11;
            int i8 = this.f8779k;
            if (i8 > 0) {
                if (f11 < 0.0f && (i8 & 1) == 1) {
                    this.f8775g = -f11;
                    this.f8776h = -f10;
                }
                float f12 = this.f8775g;
                if (f12 > 1.0f && (i8 & 2) == 2) {
                    this.f8775g = 2.0f - f12;
                    this.f8776h = -this.f8776h;
                }
            }
            i7++;
            d8 = d12;
            d9 = d14;
        }
    }

    @Override // p.m
    public boolean a() {
        double d7 = ((double) this.f8775g) - this.f8772d;
        double d8 = this.f8771c;
        double d9 = this.f8776h;
        return Math.sqrt((((d9 * d9) * ((double) this.f8777i)) + ((d8 * d7) * d7)) / d8) <= ((double) this.f8778j);
    }

    @Override // p.m
    public float b() {
        return 0.0f;
    }

    public void d(float f7, float f8, float f9, float f10, float f11, float f12, float f13, int i7) {
        this.f8772d = f8;
        this.f8769a = f12;
        this.f8770b = false;
        this.f8775g = f7;
        this.f8773e = f9;
        this.f8771c = f11;
        this.f8777i = f10;
        this.f8778j = f13;
        this.f8779k = i7;
        this.f8774f = 0.0f;
    }

    @Override // p.m
    public float getInterpolation(float f7) {
        c(f7 - this.f8774f);
        this.f8774f = f7;
        return this.f8775g;
    }
}
