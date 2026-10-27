package F0;

/* JADX INFO: renamed from: F0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0199e extends AbstractC0193b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static C0199e f2191n;

    @Override // F0.AbstractC0193b
    public final int[] a(int i7) {
        int length = c().length();
        if (length <= 0 || i7 >= length) {
            return null;
        }
        if (i7 < 0) {
            i7 = 0;
        }
        while (i7 < length && c().charAt(i7) == '\n' && !f(i7)) {
            i7++;
        }
        if (i7 >= length) {
            return null;
        }
        int i10 = i7 + 1;
        while (i10 < length && !e(i10)) {
            i10++;
        }
        return b(i7, i10);
    }

    @Override // F0.AbstractC0193b
    public final int[] d(int i7) {
        int length = c().length();
        if (length <= 0 || i7 <= 0) {
            return null;
        }
        if (i7 > length) {
            i7 = length;
        }
        while (i7 > 0 && c().charAt(i7 - 1) == '\n' && !e(i7)) {
            i7--;
        }
        if (i7 <= 0) {
            return null;
        }
        int i10 = i7 - 1;
        while (i10 > 0 && !f(i10)) {
            i10--;
        }
        return b(i10, i7);
    }

    public final boolean e(int i7) {
        return i7 > 0 && c().charAt(i7 + (-1)) != '\n' && (i7 == c().length() || c().charAt(i7) == '\n');
    }

    public final boolean f(int i7) {
        return c().charAt(i7) != '\n' && (i7 == 0 || c().charAt(i7 - 1) == '\n');
    }
}
