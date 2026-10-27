package androidx.appcompat.app;

/* JADX INFO: compiled from: TwilightCalculator.java */
/* JADX INFO: loaded from: classes.dex */
class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static h f174a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f175b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f176c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f177d;

    h() {
    }

    static h b() {
        if (f174a == null) {
            f174a = new h();
        }
        return f174a;
    }

    public void a(long j, double d2, double d3) {
        double d4 = (0.01720197f * ((j - 946728000000L) / 8.64E7f)) + 6.24006f;
        double dSin = (Math.sin(d4) * 0.03341960161924362d) + d4 + (Math.sin(2.0f * r4) * 3.4906598739326E-4d) + (Math.sin(r4 * 3.0f) * 5.236000106378924E-6d) + 1.796593063d + 3.141592653589793d;
        double dRound = ((double) (Math.round(((double) (r3 - 9.0E-4f)) - r9) + 9.0E-4f)) + ((-d3) / 360.0d) + (Math.sin(d4) * 0.0053d) + (Math.sin(2.0d * dSin) * (-0.0069d));
        double dAsin = Math.asin(Math.sin(dSin) * Math.sin(0.4092797040939331d));
        double d5 = 0.01745329238474369d * d2;
        double dSin2 = (Math.sin(-0.10471975803375244d) - (Math.sin(d5) * Math.sin(dAsin))) / (Math.cos(d5) * Math.cos(dAsin));
        if (dSin2 >= 1.0d) {
            this.f177d = 1;
            this.f175b = -1L;
            this.f176c = -1L;
        } else {
            if (dSin2 <= -1.0d) {
                this.f177d = 0;
                this.f175b = -1L;
                this.f176c = -1L;
                return;
            }
            double dAcos = (float) (Math.acos(dSin2) / 6.283185307179586d);
            this.f175b = Math.round((dRound + dAcos) * 8.64E7d) + 946728000000L;
            long jRound = Math.round((dRound - dAcos) * 8.64E7d) + 946728000000L;
            this.f176c = jRound;
            if (jRound >= j || this.f175b <= j) {
                this.f177d = 1;
            } else {
                this.f177d = 0;
            }
        }
    }
}
