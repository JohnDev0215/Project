package S0;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7861a;

    public static final boolean a(int i7, int i10) {
        return i7 == i10;
    }

    public static String b(int i7) {
        return a(i7, 0) ? "None" : a(i7, 1) ? "All" : a(i7, 2) ? "Weight" : a(i7, 3) ? "Style" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof x) {
            return this.f7861a == ((x) obj).f7861a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f7861a);
    }

    public final String toString() {
        return b(this.f7861a);
    }
}
