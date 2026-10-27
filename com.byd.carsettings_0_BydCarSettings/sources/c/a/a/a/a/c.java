package c.a.a.a.a;

import java.util.regex.Pattern;

/* JADX INFO: compiled from: StringUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f2351a = Pattern.compile("\\s+");

    public static String a(String str) {
        if (c(str)) {
            return str;
        }
        int length = str.length();
        char[] cArr = new char[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!Character.isWhitespace(str.charAt(i2))) {
                cArr[i] = str.charAt(i2);
                i++;
            }
        }
        return i == length ? str : new String(cArr, 0, i);
    }

    public static boolean b(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence == null ? charSequence2 == null : charSequence.equals(charSequence2);
    }

    public static boolean c(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
