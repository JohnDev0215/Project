package n0;

/* JADX INFO: renamed from: n0.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1852B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f19370a;

    public static final boolean a(int i7, int i10) {
        return i7 == i10;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1852B) {
            return this.f19370a == ((C1852B) obj).f19370a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f19370a);
    }

    public final String toString() {
        int i7 = this.f19370a;
        return a(i7, 0) ? "Argb8888" : a(i7, 1) ? "Alpha8" : a(i7, 2) ? "Rgb565" : a(i7, 3) ? "F16" : a(i7, 4) ? "Gpu" : "Unknown";
    }
}
