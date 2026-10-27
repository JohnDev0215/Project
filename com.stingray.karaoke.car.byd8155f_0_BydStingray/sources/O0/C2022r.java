package o0;

/* JADX INFO: renamed from: o0.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2022r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final double f20226a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final double f20227b;
    public final double c;
    public final double d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final double f20228e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final double f20229f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final double f20230g;

    public /* synthetic */ C2022r(double d, double d10, double d11, double d12, double d13) {
        this(d, d10, d11, d12, d13, 0.0d, 0.0d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2022r)) {
            return false;
        }
        C2022r c2022r = (C2022r) obj;
        return Double.compare(this.f20226a, c2022r.f20226a) == 0 && Double.compare(this.f20227b, c2022r.f20227b) == 0 && Double.compare(this.c, c2022r.c) == 0 && Double.compare(this.d, c2022r.d) == 0 && Double.compare(this.f20228e, c2022r.f20228e) == 0 && Double.compare(this.f20229f, c2022r.f20229f) == 0 && Double.compare(this.f20230g, c2022r.f20230g) == 0;
    }

    public final int hashCode() {
        return Double.hashCode(this.f20230g) + ((Double.hashCode(this.f20229f) + ((Double.hashCode(this.f20228e) + ((Double.hashCode(this.d) + ((Double.hashCode(this.c) + ((Double.hashCode(this.f20227b) + (Double.hashCode(this.f20226a) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "TransferParameters(gamma=" + this.f20226a + ", a=" + this.f20227b + ", b=" + this.c + ", c=" + this.d + ", d=" + this.f20228e + ", e=" + this.f20229f + ", f=" + this.f20230g + ')';
    }

    public C2022r(double d, double d10, double d11, double d12, double d13, double d14, double d15) {
        this.f20226a = d;
        this.f20227b = d10;
        this.c = d11;
        this.d = d12;
        this.f20228e = d13;
        this.f20229f = d14;
        this.f20230g = d15;
        if (Double.isNaN(d10) || Double.isNaN(d11) || Double.isNaN(d12) || Double.isNaN(d13) || Double.isNaN(d14) || Double.isNaN(d15) || Double.isNaN(d)) {
            throw new IllegalArgumentException("Parameters cannot be NaN");
        }
        if (d13 < 0.0d || d13 > 1.0d) {
            throw new IllegalArgumentException("Parameter d must be in the range [0..1], was " + d13);
        }
        if (d13 == 0.0d && (d10 == 0.0d || d == 0.0d)) {
            throw new IllegalArgumentException("Parameter a or g is zero, the transfer function is constant");
        }
        if (d13 >= 1.0d && d12 == 0.0d) {
            throw new IllegalArgumentException("Parameter c is zero, the transfer function is constant");
        }
        if ((d10 == 0.0d || d == 0.0d) && d12 == 0.0d) {
            throw new IllegalArgumentException("Parameter a or g is zero, and c is zero, the transfer function is constant");
        }
        if (d12 < 0.0d) {
            throw new IllegalArgumentException("The transfer function must be increasing");
        }
        if (d10 < 0.0d || d < 0.0d) {
            throw new IllegalArgumentException("The transfer function must be positive or increasing");
        }
    }
}
