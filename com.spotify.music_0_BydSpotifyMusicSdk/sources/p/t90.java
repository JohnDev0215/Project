package p;

/* JADX INFO: loaded from: classes.dex */
public final class t90 extends ci1 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f187p;

    public t90(int i) {
        this.f187p = i;
        if (i <= 0) {
            throw new IllegalArgumentException("px must be > 0.".toString());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof t90) {
            if (this.f187p == ((t90) obj).f187p) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f187p;
    }

    public final String toString() {
        return String.valueOf(this.f187p);
    }
}
