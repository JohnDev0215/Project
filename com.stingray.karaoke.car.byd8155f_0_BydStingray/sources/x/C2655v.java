package x;

/* JADX INFO: renamed from: x.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2655v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f23645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f23646b;

    public C2655v(double d, double d10) {
        this.f23645a = d;
        this.f23646b = d10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2655v)) {
            return false;
        }
        C2655v c2655v = (C2655v) obj;
        return Double.compare(this.f23645a, c2655v.f23645a) == 0 && Double.compare(this.f23646b, c2655v.f23646b) == 0;
    }

    public final int hashCode() {
        return Double.hashCode(this.f23646b) + (Double.hashCode(this.f23645a) * 31);
    }

    public final String toString() {
        return "ComplexDouble(_real=" + this.f23645a + ", _imaginary=" + this.f23646b + ')';
    }
}
