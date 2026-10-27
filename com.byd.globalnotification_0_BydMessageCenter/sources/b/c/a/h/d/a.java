package b.c.a.h.d;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static boolean a(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
