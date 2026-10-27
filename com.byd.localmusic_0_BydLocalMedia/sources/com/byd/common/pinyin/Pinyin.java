package com.byd.common.pinyin;

import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;

/* JADX INFO: loaded from: classes5.dex */
public class Pinyin {
    private static final int CN = 2;
    public static final String DEFAULT_PREFIX = "#";
    private static final int DIGITAL = 0;
    private static final int DIGITAL_BEGIN = 48;
    private static final int DIGITAL_END = 57;
    private static final int LETTER = 1;
    private static final int LOWER_BEGIN = 97;
    private static final int LOWER_END = 122;
    private static final int OTHERS = 3;
    private static final int UNICODE_BEGIN = 19968;
    private static final int UNICODE_END = 40869;
    private static final int UPPER_BEGIN = 65;
    private static final int UPPER_END = 90;

    public static native int getPinyinCode(int i);

    private static native char getPinyinPrefix(int i);

    private static boolean isCN(int i) {
        return i >= UNICODE_BEGIN && i <= UNICODE_END;
    }

    private static boolean isDigital(int i) {
        return i >= 48 && i <= 57;
    }

    private static boolean isLetter(int i) {
        return (i >= 97 && i <= 122) || (i >= 65 && i <= 90);
    }

    static {
        System.loadLibrary("mediacenter_pinyin");
    }

    public static String getPrefix(String str) {
        if (str == null || str.trim().equals("")) {
            return "#";
        }
        if (LocalMusicUtils.BYD_SHOW_CAR_ARTIST.equals(str)) {
            return "w";
        }
        int iCodePointAt = str.codePointAt(0);
        int type = getType(iCodePointAt);
        if (type == 1) {
            return str.substring(0, 1);
        }
        if (type != 2) {
            return "#";
        }
        return getPinyinPrefix(iCodePointAt) + "";
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
}
