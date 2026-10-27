package G;

/* JADX INFO: renamed from: G.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0303m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2601a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2602b;

    public C0303m(int i7, int i10) {
        this.f2601a = i7;
        this.f2602b = i10;
        if (i7 < 0) {
            throw new IllegalArgumentException("negative start index");
        }
        if (i10 < i7) {
            throw new IllegalArgumentException("end index greater than start");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0303m)) {
            return false;
        }
        C0303m c0303m = (C0303m) obj;
        return this.f2601a == c0303m.f2601a && this.f2602b == c0303m.f2602b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f2602b) + (Integer.hashCode(this.f2601a) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Interval(start=");
        sb.append(this.f2601a);
        sb.append(", end=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f2602b, ')');
    }
}
