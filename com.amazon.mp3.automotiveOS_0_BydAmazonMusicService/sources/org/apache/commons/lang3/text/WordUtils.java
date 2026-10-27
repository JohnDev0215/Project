package org.apache.commons.lang3.text;

import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public class WordUtils {
    public static String capitalize(String str) {
        return capitalize(str, null);
    }

    public static String capitalize(String str, char... cArr) {
        int length = cArr == null ? -1 : cArr.length;
        if (StringUtils.isEmpty(str) || length == 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        boolean z = true;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (isDelimiter(c, cArr)) {
                z = true;
            } else if (z) {
                charArray[i] = Character.toTitleCase(c);
                z = false;
            }
        }
        return new String(charArray);
    }

    private static boolean isDelimiter(char c, char[] cArr) {
        if (cArr == null) {
            return Character.isWhitespace(c);
        }
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }
}
