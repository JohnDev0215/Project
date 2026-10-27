package o6;

import h2.AbstractC1347d;
import n0.C1853C;
import n0.C1891t;

/* JADX INFO: renamed from: o6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2048a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f20530a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f20531b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f20532e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f20533f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f20534g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f20535h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f20536i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f20537j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C1853C f20538k;
    public final C1853C l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1853C f20539m;

    public C2048a(long j3, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, C1853C c1853c, C1853C c1853c2, C1853C c1853c3) {
        this.f20530a = j3;
        this.f20531b = j10;
        this.c = j11;
        this.d = j12;
        this.f20532e = j13;
        this.f20533f = j14;
        this.f20534g = j15;
        this.f20535h = j16;
        this.f20536i = j17;
        this.f20537j = j18;
        this.f20538k = c1853c;
        this.l = c1853c2;
        this.f20539m = c1853c3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2048a)) {
            return false;
        }
        C2048a c2048a = (C2048a) obj;
        return C1891t.c(this.f20530a, c2048a.f20530a) && C1891t.c(this.f20531b, c2048a.f20531b) && C1891t.c(this.c, c2048a.c) && C1891t.c(this.d, c2048a.d) && C1891t.c(this.f20532e, c2048a.f20532e) && C1891t.c(this.f20533f, c2048a.f20533f) && C1891t.c(this.f20534g, c2048a.f20534g) && C1891t.c(this.f20535h, c2048a.f20535h) && C1891t.c(this.f20536i, c2048a.f20536i) && C1891t.c(this.f20537j, c2048a.f20537j) && this.f20538k.equals(c2048a.f20538k) && this.l.equals(c2048a.l) && this.f20539m.equals(c2048a.f20539m);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return this.f20539m.hashCode() + ((this.l.hashCode() + ((this.f20538k.hashCode() + AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(Long.hashCode(this.f20530a) * 31, 31, this.f20531b), 31, this.c), 31, this.d), 31, this.f20532e), 31, this.f20533f), 31, this.f20534g), 31, this.f20535h), 31, this.f20536i), 31, this.f20537j)) * 31)) * 31);
    }

    public final String toString() {
        String strI = C1891t.i(this.f20530a);
        String strI2 = C1891t.i(this.f20531b);
        String strI3 = C1891t.i(this.c);
        String strI4 = C1891t.i(this.d);
        String strI5 = C1891t.i(this.f20532e);
        String strI6 = C1891t.i(this.f20533f);
        String strI7 = C1891t.i(this.f20534g);
        String strI8 = C1891t.i(this.f20535h);
        String strI9 = C1891t.i(this.f20536i);
        String strI10 = C1891t.i(this.f20537j);
        StringBuilder sb = new StringBuilder("ScoringViewColorConfig(lyricsText=");
        sb.append(strI);
        sb.append(", scoreText=");
        sb.append(strI2);
        sb.append(", pitchIndicator=");
        AbstractC1347d.r(sb, strI3, ", startLine=", strI4, ", noteProgressFill=");
        AbstractC1347d.r(sb, strI5, ", noteSuccessFill=", strI6, ", noteSucceededStroke=");
        AbstractC1347d.r(sb, strI7, ", noteStroke=", strI8, ", particleColor=");
        AbstractC1347d.r(sb, strI9, ", rhythmSectionBackgroundColor=", strI10, ", borderHorizontalGradient=");
        sb.append(this.f20538k);
        sb.append(", backgroundHorizontalGradientLine1=");
        sb.append(this.l);
        sb.append(", backgroundHorizontalGradientLine2=");
        sb.append(this.f20539m);
        sb.append(")");
        return sb.toString();
    }
}
