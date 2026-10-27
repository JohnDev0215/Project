package cn.hutool.core.util;

/* JADX INFO: compiled from: CharUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static boolean a(char c2) {
        return b(c2);
    }

    public static boolean b(int i) {
        return Character.isWhitespace(i) || Character.isSpaceChar(i) || i == 65279 || i == 8234 || i == 0;
    }
}
