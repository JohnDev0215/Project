package android.graphics.fonts;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class FontFileUtil {
    private static final int ANALYZE_ERROR = -1;
    private static final int OS2_TABLE_TAG = 1330851634;
    private static final int SFNT_VERSION_1 = 65536;
    private static final int SFNT_VERSION_OTTO = 1330926671;
    private static final int TTC_TAG = 1953784678;

    public static boolean isSuccess(int i) {
        return i != -1;
    }

    private static int pack(int i, boolean z) {
        return i | (z ? 65536 : 0);
    }

    public static boolean unpackItalic(int i) {
        return (i & 65536) != 0;
    }

    public static int unpackWeight(int i) {
        return i & 65535;
    }

    private FontFileUtil() {
    }

    public static final int analyzeStyle(ByteBuffer byteBuffer, int i, FontVariationAxis[] fontVariationAxisArr) {
        int styleValue;
        byte b;
        int i2;
        int i3;
        if (fontVariationAxisArr != null) {
            styleValue = -1;
            b = -1;
            for (FontVariationAxis fontVariationAxis : fontVariationAxisArr) {
                if ("wght".equals(fontVariationAxis.getTag())) {
                    styleValue = (int) fontVariationAxis.getStyleValue();
                } else if ("ital".equals(fontVariationAxis.getTag())) {
                    b = fontVariationAxis.getStyleValue() == 1.0f ? (byte) 1 : (byte) 0;
                }
            }
        } else {
            styleValue = -1;
            b = -1;
        }
        if (styleValue != -1 && b != -1) {
            return pack(styleValue, b == 1);
        }
        ByteOrder byteOrderOrder = byteBuffer.order();
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        try {
            if (byteBuffer.getInt(0) != TTC_TAG) {
                i2 = 0;
            } else {
                if (i >= byteBuffer.getInt(8)) {
                    return -1;
                }
                i2 = byteBuffer.getInt((i * 4) + 12);
            }
            int i4 = byteBuffer.getInt(i2);
            if (i4 != 65536 && i4 != SFNT_VERSION_OTTO) {
                return -1;
            }
            short s = byteBuffer.getShort(i2 + 4);
            int i5 = 0;
            while (true) {
                if (i5 >= s) {
                    i3 = -1;
                    break;
                }
                int i6 = i2 + 12 + (i5 * 16);
                if (byteBuffer.getInt(i6) == OS2_TABLE_TAG) {
                    i3 = byteBuffer.getInt(i6 + 8);
                    break;
                }
                i5++;
            }
            if (i3 == -1) {
                return pack(400, false);
            }
            short s2 = byteBuffer.getShort(i3 + 4);
            boolean z = (byteBuffer.getShort(i3 + 62) & 1) != 0;
            if (styleValue == -1) {
                styleValue = s2;
            }
            if (b == -1) {
                z = z;
            } else if (b != 1) {
                z = false;
            }
            return pack(styleValue, z);
        } finally {
            byteBuffer.order(byteOrderOrder);
        }
    }
}
