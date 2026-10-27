package y5;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: CharJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static int a(int i7) {
        boolean z6 = false;
        if (2 <= i7 && i7 < 37) {
            z6 = true;
        }
        if (z6) {
            return i7;
        }
        throw new IllegalArgumentException("radix " + i7 + " was not in valid range " + new v5.c(2, 36));
    }

    public static final int b(char c7, int i7) {
        return Character.digit((int) c7, i7);
    }

    public static final boolean c(char c7) {
        return Character.isWhitespace(c7) || Character.isSpaceChar(c7);
    }
}
