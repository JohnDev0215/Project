package com.android.dex;

/* JADX INFO: loaded from: classes.dex */
public final class DexFormat {
    public static final int API_CURRENT = 24;
    public static final int API_NO_EXTENDED_OPCODES = 13;
    public static final String DEX_IN_JAR_NAME = "classes.dex";
    public static final int ENDIAN_TAG = 305419896;
    public static final String MAGIC_PREFIX = "dex\n";
    public static final String MAGIC_SUFFIX = "\u0000";
    public static final int MAX_MEMBER_IDX = 65535;
    public static final int MAX_TYPE_IDX = 65535;
    public static final String VERSION_CURRENT = "037";
    public static final String VERSION_FOR_API_13 = "035";

    private DexFormat() {
    }

    public static int magicToApi(byte[] bArr) {
        if (bArr.length == 8 && bArr[0] == 100 && bArr[1] == 101 && bArr[2] == 120 && bArr[3] == 10 && bArr[7] == 0) {
            String str = "" + ((char) bArr[4]) + ((char) bArr[5]) + ((char) bArr[6]);
            if (str.equals(VERSION_CURRENT)) {
                return 24;
            }
            if (str.equals(VERSION_FOR_API_13)) {
                return 13;
            }
        }
        return -1;
    }

    public static String apiToMagic(int i) {
        return MAGIC_PREFIX + (i >= 24 ? VERSION_CURRENT : VERSION_FOR_API_13) + "\u0000";
    }

    public static boolean isSupportedDexMagic(byte[] bArr) {
        int iMagicToApi = magicToApi(bArr);
        return iMagicToApi == 13 || iMagicToApi == 24;
    }
}
