package l0;

/* JADX INFO: renamed from: l0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1711b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18325a;

    public static final boolean a(int i7, int i10) {
        return i7 == i10;
    }

    public static String b(int i7) {
        return a(i7, 1) ? "Next" : a(i7, 2) ? "Previous" : a(i7, 3) ? "Left" : a(i7, 4) ? "Right" : a(i7, 5) ? "Up" : a(i7, 6) ? "Down" : a(i7, 7) ? "Enter" : a(i7, 8) ? "Exit" : "Invalid FocusDirection";
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1711b) {
            return this.f18325a == ((C1711b) obj).f18325a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f18325a);
    }

    public final String toString() {
        return b(this.f18325a);
    }
}
