package org.apache.commons.lang3;

import java.util.Random;

/* JADX INFO: loaded from: classes3.dex */
public class RandomStringUtils {
    private static final Random RANDOM = new Random();

    public static String random(int i) {
        return random(i, false, false);
    }

    public static String randomAscii(int i) {
        return random(i, 32, 127, false, false);
    }

    public static String randomAscii(int i, int i2) {
        return randomAscii(RandomUtils.nextInt(i, i2));
    }

    public static String randomAlphabetic(int i) {
        return random(i, true, false);
    }

    public static String randomAlphabetic(int i, int i2) {
        return randomAlphabetic(RandomUtils.nextInt(i, i2));
    }

    public static String randomAlphanumeric(int i) {
        return random(i, true, true);
    }

    public static String randomAlphanumeric(int i, int i2) {
        return randomAlphanumeric(RandomUtils.nextInt(i, i2));
    }

    public static String randomGraph(int i) {
        return random(i, 33, 126, false, false);
    }

    public static String randomGraph(int i, int i2) {
        return randomGraph(RandomUtils.nextInt(i, i2));
    }

    public static String randomNumeric(int i) {
        return random(i, false, true);
    }

    public static String randomNumeric(int i, int i2) {
        return randomNumeric(RandomUtils.nextInt(i, i2));
    }

    public static String randomPrint(int i) {
        return random(i, 32, 126, false, false);
    }

    public static String randomPrint(int i, int i2) {
        return randomPrint(RandomUtils.nextInt(i, i2));
    }

    public static String random(int i, boolean z, boolean z2) {
        return random(i, 0, 0, z, z2);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2) {
        return random(i, i2, i3, z, z2, null, RANDOM);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2, char... cArr) {
        return random(i, i2, i3, z, z2, cArr, RANDOM);
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String random(int r6, int r7, int r8, boolean r9, boolean r10, char[] r11, java.util.Random r12) {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.RandomStringUtils.random(int, int, int, boolean, boolean, char[], java.util.Random):java.lang.String");
    }

    public static String random(int i, String str) {
        if (str == null) {
            return random(i, 0, 0, false, false, null, RANDOM);
        }
        return random(i, str.toCharArray());
    }

    public static String random(int i, char... cArr) {
        if (cArr == null) {
            return random(i, 0, 0, false, false, null, RANDOM);
        }
        return random(i, 0, cArr.length, false, false, cArr, RANDOM);
    }
}
