package i;

/* JADX INFO: compiled from: TwilightCalculator.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static j f3176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f3177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f3178c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3179d;

    public static j b() {
        if (f3176a == null) {
            f3176a = new j();
        }
        return f3176a;
    }

    public void a(long j3, double d3, double d4) {
        double d5 = (0.01720197f * ((j3 - 946728000000L) / 8.64E7f)) + 6.24006f;
        double dSin = (Math.sin(d5) * 0.03341960161924362d) + d5 + (Math.sin(2.0f * r4) * 3.4906598739326E-4d) + (Math.sin(r4 * 3.0f) * 5.236000106378924E-6d) + 1.796593063d + 3.141592653589793d;
        double dRound = ((double) (Math.round(((double) (r3 - 9.0E-4f)) - r9) + 9.0E-4f)) + ((-d4) / 360.0d) + (Math.sin(d5) * 0.0053d) + (Math.sin(2.0d * dSin) * (-0.0069d));
        double dAsin = Math.asin(Math.sin(dSin) * Math.sin(0.4092797040939331d));
        double d6 = 0.01745329238474369d * d3;
        double dSin2 = (Math.sin(-0.10471975803375244d) - (Math.sin(d6) * Math.sin(dAsin))) / (Math.cos(d6) * Math.cos(dAsin));
        if (dSin2 >= 1.0d) {
            this.f3179d = 1;
            this.f3177b = -1L;
            this.f3178c = -1L;
        } else {
            if (dSin2 <= -1.0d) {
                this.f3179d = 0;
                this.f3177b = -1L;
                this.f3178c = -1L;
                return;
            }
            double dAcos = (float) (Math.acos(dSin2) / 6.283185307179586d);
            this.f3177b = Math.round((dRound + dAcos) * 8.64E7d) + 946728000000L;
            long jRound = Math.round((dRound - dAcos) * 8.64E7d) + 946728000000L;
            this.f3178c = jRound;
            if (jRound >= j3 || this.f3177b <= j3) {
                this.f3179d = 1;
            } else {
                this.f3179d = 0;
            }
        }
    }
}
