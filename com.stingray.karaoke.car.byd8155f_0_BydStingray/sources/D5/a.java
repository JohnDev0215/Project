package D5;

import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeZone f1190a = TimeZone.getTimeZone("GMT");

    public static boolean a(String str, int i7, char c) {
        return i7 < str.length() && str.charAt(i7) == c;
    }

    public static void b(int i7, int i10, StringBuilder sb) {
        String string = Integer.toString(i7);
        for (int length = i10 - string.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(string);
    }

    public static int c(String str, int i7, int i10) {
        int i11;
        int i12;
        if (i7 < 0 || i10 > str.length() || i7 > i10) {
            throw new NumberFormatException(str);
        }
        if (i7 < i10) {
            i12 = i7 + 1;
            int iDigit = Character.digit(str.charAt(i7), 10);
            if (iDigit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i7, i10));
            }
            i11 = -iDigit;
        } else {
            i11 = 0;
            i12 = i7;
        }
        while (i12 < i10) {
            int i13 = i12 + 1;
            int iDigit2 = Character.digit(str.charAt(i12), 10);
            if (iDigit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i7, i10));
            }
            i11 = (i11 * 10) - iDigit2;
            i12 = i13;
        }
        return -i11;
    }
}
