package com.byd.music.utils;

/* JADX INFO: loaded from: classes5.dex */
public class Pinyin {
    public static final String DEFAULT_PREFIX = "#";
    private static final int DIGITAL = 0;
    private static final int DIGITAL_BEGIN = 48;
    private static final int DIGITAL_END = 57;
    private static final int LETTER = 1;
    private static final int LOWER_BEGIN = 97;
    private static final int LOWER_END = 122;
    private static final int OTHERS = 3;
    private static final String TAG = "Pinyin";
    private static final int UNICODE_BEGIN = 19968;
    private static final int UNICODE_END = 40869;
    private static final int UPPER_BEGIN = 65;
    private static final int UPPER_END = 90;

    private static boolean isCN(int i) {
        return i >= UNICODE_BEGIN && i <= UNICODE_END;
    }

    private static boolean isChineseCharacter(char c) {
        return UNICODE_BEGIN < c && c < UNICODE_END;
    }

    private static boolean isDigital(int i) {
        return i >= 48 && i <= 57;
    }

    private static boolean isLetter(int i) {
        return (i >= 97 && i <= 122) || (i >= 65 && i <= 90);
    }

    private static int getType(int i) {
        if (isDigital(i)) {
            return 0;
        }
        if (isLetter(i)) {
            return 1;
        }
        return isCN(i) ? 2 : 3;
    }

    public static String getPrefix(String str) {
        return com.byd.common.pinyin.Pinyin.getPrefix(str);
    }
}
