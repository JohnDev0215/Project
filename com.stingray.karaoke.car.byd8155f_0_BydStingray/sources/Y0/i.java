package Y0;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f10618a;

    public static final boolean a(int i7, int i10) {
        return i7 == i10;
    }

    public static String b(int i7) {
        return a(i7, 1) ? "Left" : a(i7, 2) ? "Right" : a(i7, 3) ? "Center" : a(i7, 4) ? "Justify" : a(i7, 5) ? "Start" : a(i7, 6) ? "End" : a(i7, Integer.MIN_VALUE) ? "Unspecified" : "Invalid";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof i) {
            return this.f10618a == ((i) obj).f10618a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f10618a);
    }

    public final String toString() {
        return b(this.f10618a);
    }
}
