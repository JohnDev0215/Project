package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class og2 extends kotlin.text.b {
    public static String H0(int i, String str) {
        v03.j(str, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(u4.f("Requested character count ", i, " is less than zero.").toString());
        }
        int length = str.length();
        if (i > length) {
            i = length;
        }
        String strSubstring = str.substring(0, i);
        v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }
}
