package org.apache.commons.lang3;

/* JADX INFO: loaded from: classes7.dex */
public class CharSequenceUtils {
    static int indexOf(CharSequence charSequence, CharSequence charSequence2, int i) {
        if (charSequence instanceof String) {
            return ((String) charSequence).indexOf(charSequence2.toString(), i);
        }
        if (charSequence instanceof StringBuilder) {
            return ((StringBuilder) charSequence).indexOf(charSequence2.toString(), i);
        }
        if (charSequence instanceof StringBuffer) {
            return ((StringBuffer) charSequence).indexOf(charSequence2.toString(), i);
        }
        return charSequence.toString().indexOf(charSequence2.toString(), i);
    }

    static boolean regionMatches(CharSequence charSequence, boolean z, int i, CharSequence charSequence2, int i2, int i3) {
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return ((String) charSequence).regionMatches(z, i, (String) charSequence2, i2, i3);
        }
        int length = charSequence.length() - i;
        int length2 = charSequence2.length() - i2;
        if (i < 0 || i2 < 0 || i3 < 0 || length < i3 || length2 < i3) {
            return false;
        }
        while (true) {
            int i4 = i3 - 1;
            if (i3 <= 0) {
                return true;
            }
            int i5 = i + 1;
            char cCharAt = charSequence.charAt(i);
            int i6 = i2 + 1;
            char cCharAt2 = charSequence2.charAt(i2);
            if (cCharAt != cCharAt2) {
                if (!z) {
                    return false;
                }
                char upperCase = Character.toUpperCase(cCharAt);
                char upperCase2 = Character.toUpperCase(cCharAt2);
                if (upperCase != upperCase2 && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return false;
                }
            }
            i = i5;
            i3 = i4;
            i2 = i6;
        }
    }
}
