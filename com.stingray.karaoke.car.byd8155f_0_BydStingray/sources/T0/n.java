package T0;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8464a;

    public static final boolean a(int i7, int i10) {
        return i7 == i10;
    }

    public static String b(int i7) {
        return a(i7, -1) ? "Unspecified" : a(i7, 0) ? "None" : a(i7, 1) ? "Characters" : a(i7, 2) ? "Words" : a(i7, 3) ? "Sentences" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof n) {
            return this.f8464a == ((n) obj).f8464a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f8464a);
    }

    public final String toString() {
        return b(this.f8464a);
    }
}
