package c7;

import java.util.regex.Pattern;

/* JADX INFO: compiled from: StringUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f2367a = Pattern.compile("\\s+");

    public static String a(String str) {
        if (c(str)) {
            return str;
        }
        int length = str.length();
        char[] cArr = new char[length];
        int i8 = 0;
        for (int i9 = 0; i9 < length; i9++) {
            if (!Character.isWhitespace(str.charAt(i9))) {
                cArr[i8] = str.charAt(i9);
                i8++;
            }
        }
        return i8 == length ? str : new String(cArr, 0, i8);
    }

    public static boolean b(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence == null ? charSequence2 == null : charSequence.equals(charSequence2);
    }

    public static boolean c(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
