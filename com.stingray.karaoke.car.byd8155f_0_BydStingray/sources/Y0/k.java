package Y0;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f10621a;

    public static final boolean a(int i7, int i10) {
        return i7 == i10;
    }

    public static String b(int i7) {
        return a(i7, 1) ? "Ltr" : a(i7, 2) ? "Rtl" : a(i7, 3) ? "Content" : a(i7, 4) ? "ContentOrLtr" : a(i7, 5) ? "ContentOrRtl" : a(i7, Integer.MIN_VALUE) ? "Unspecified" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof k) {
            return this.f10621a == ((k) obj).f10621a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f10621a);
    }

    public final String toString() {
        return b(this.f10621a);
    }
}
