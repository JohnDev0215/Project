package com.android.inputmethod.latin.common;

import java.util.Random;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CodePointUtils {
    public static final int[] LATIN_ALPHABETS_LOWER = {97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 249, 250, 251, 252, 253, 254, 255};

    private CodePointUtils() {
    }

    @Nonnull
    public static int[] generateCodePointSet(final int codePointSetSize, @Nonnull final Random random) {
        int i;
        int[] iArr = new int[codePointSetSize];
        int i2 = codePointSetSize - 1;
        while (i2 >= 0) {
            int iAbs = Math.abs(random.nextInt());
            if (iAbs >= 0 && ((i = (iAbs % 1114079) + 32) < 55296 || i > 57343)) {
                iArr[i2] = i;
                i2--;
            }
        }
        return iArr;
    }

    @Nonnull
    public static String generateWord(@Nonnull final Random random, @Nonnull final int[] codePointSet) {
        StringBuilder sb = new StringBuilder();
        int iAbs = (Math.abs(random.nextInt()) % 5) + 1 + (Math.abs(random.nextInt()) % 5) + (Math.abs(random.nextInt()) % 5) + (Math.abs(random.nextInt()) % 5) + (Math.abs(random.nextInt()) % 5) + (Math.abs(random.nextInt()) % 5) + (Math.abs(random.nextInt()) % 5) + (Math.abs(random.nextInt()) % 5);
        while (sb.length() < iAbs) {
            sb.appendCodePoint(codePointSet[Math.abs(random.nextInt()) % codePointSet.length]);
        }
        return sb.toString();
    }
}
