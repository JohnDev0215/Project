package Y0;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f10609a;

    public static final boolean a(int i7, int i10) {
        return i7 == i10;
    }

    public static String b(int i7) {
        return a(i7, 1) ? "Hyphens.None" : a(i7, 2) ? "Hyphens.Auto" : a(i7, Integer.MIN_VALUE) ? "Hyphens.Unspecified" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof d) {
            return this.f10609a == ((d) obj).f10609a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f10609a);
    }

    public final String toString() {
        return b(this.f10609a);
    }
}
