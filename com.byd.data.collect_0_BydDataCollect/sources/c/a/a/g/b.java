package c.a.a.g;

/* JADX INFO: compiled from: CharUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static boolean a(char c2) {
        return b(c2);
    }

    public static boolean b(int i2) {
        return Character.isWhitespace(i2) || Character.isSpaceChar(i2) || i2 == 65279 || i2 == 8234 || i2 == 0;
    }
}
