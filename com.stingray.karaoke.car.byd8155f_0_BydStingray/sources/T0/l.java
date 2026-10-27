package T0;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8458a;

    public static final boolean a(int i7, int i10) {
        return i7 == i10;
    }

    public static String b(int i7) {
        return a(i7, -1) ? "Unspecified" : a(i7, 0) ? "None" : a(i7, 1) ? "Default" : a(i7, 2) ? "Go" : a(i7, 3) ? "Search" : a(i7, 4) ? "Send" : a(i7, 5) ? "Previous" : a(i7, 6) ? "Next" : a(i7, 7) ? "Done" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof l) {
            return this.f8458a == ((l) obj).f8458a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f8458a);
    }

    public final String toString() {
        return b(this.f8458a);
    }
}
