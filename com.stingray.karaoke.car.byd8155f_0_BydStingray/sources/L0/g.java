package L0;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3883a;

    public static final boolean a(int i7, int i10) {
        return i7 == i10;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof g) {
            return this.f3883a == ((g) obj).f3883a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f3883a);
    }

    public final String toString() {
        int i7 = this.f3883a;
        return a(i7, 0) ? "Button" : a(i7, 1) ? "Checkbox" : a(i7, 2) ? "Switch" : a(i7, 3) ? "RadioButton" : a(i7, 4) ? "Tab" : a(i7, 5) ? "Image" : a(i7, 6) ? "DropdownList" : "Unknown";
    }
}
