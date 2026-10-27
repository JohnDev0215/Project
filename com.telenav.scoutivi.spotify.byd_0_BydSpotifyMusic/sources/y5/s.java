package y5;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Strings.kt */
/* JADX INFO: loaded from: classes.dex */
public class s extends r {
    public static final String A0(String str, int i7) {
        kotlin.jvm.internal.l.e(str, "<this>");
        if (i7 >= 0) {
            String strSubstring = str.substring(v5.h.d(i7, str.length()));
            kotlin.jvm.internal.l.d(strSubstring, "this as java.lang.String).substring(startIndex)");
            return strSubstring;
        }
        throw new IllegalArgumentException(("Requested character count " + i7 + " is less than zero.").toString());
    }

    public static String B0(String str, int i7) {
        kotlin.jvm.internal.l.e(str, "<this>");
        if (i7 >= 0) {
            String strSubstring = str.substring(0, v5.h.d(i7, str.length()));
            kotlin.jvm.internal.l.d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            return strSubstring;
        }
        throw new IllegalArgumentException(("Requested character count " + i7 + " is less than zero.").toString());
    }
}
