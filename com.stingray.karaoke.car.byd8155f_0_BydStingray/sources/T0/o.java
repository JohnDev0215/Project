package T0;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f8465a;

    public static final boolean a(int i7, int i10) {
        return i7 == i10;
    }

    public static String b(int i7) {
        return a(i7, 0) ? "Unspecified" : a(i7, 1) ? "Text" : a(i7, 2) ? "Ascii" : a(i7, 3) ? "Number" : a(i7, 4) ? "Phone" : a(i7, 5) ? "Uri" : a(i7, 6) ? "Email" : a(i7, 7) ? "Password" : a(i7, 8) ? "NumberPassword" : a(i7, 9) ? "Decimal" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof o) {
            return this.f8465a == ((o) obj).f8465a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f8465a);
    }

    public final String toString() {
        return b(this.f8465a);
    }
}
