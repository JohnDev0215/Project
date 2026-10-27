package S0;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7860a;

    public static final boolean a(int i7, int i10) {
        return i7 == i10;
    }

    public static String b(int i7) {
        return a(i7, 0) ? "Normal" : a(i7, 1) ? "Italic" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof w) {
            return this.f7860a == ((w) obj).f7860a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f7860a);
    }

    public final String toString() {
        return b(this.f7860a);
    }
}
