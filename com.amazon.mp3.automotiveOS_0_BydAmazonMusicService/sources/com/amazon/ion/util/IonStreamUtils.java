package com.amazon.ion.util;

import com.amazon.ion.impl._Private_IonConstants;

/* JADX INFO: loaded from: classes3.dex */
public class IonStreamUtils {
    public static boolean isIonBinary(byte[] bArr, int i, int i2) {
        return cookieMatches(_Private_IonConstants.BINARY_VERSION_MARKER_1_0, bArr, i, i2);
    }

    public static boolean isGzip(byte[] bArr, int i, int i2) {
        return cookieMatches(GzipOrRawInputStream.GZIP_HEADER, bArr, i, i2);
    }

    private static boolean cookieMatches(byte[] bArr, byte[] bArr2, int i, int i2) {
        if (bArr2 == null || i2 < bArr.length) {
            return false;
        }
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (bArr[i3] != bArr2[i + i3]) {
                return false;
            }
        }
        return true;
    }
}
