package com.amazon.device.ads;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
class StringUtils {
    private static final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(StringUtils.class.getSimpleName());

    private StringUtils() {
    }

    public static final boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }

    public static final boolean isNullOrWhiteSpace(String str) {
        return isNullOrEmpty(str) || str.trim().equals("");
    }

    public static String readStringFromInputStream(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                try {
                    int i = inputStream.read(bArr);
                    if (i != -1) {
                        sb.append(new String(bArr, 0, i));
                    }
                } catch (IOException unused) {
                    logger.e("Unable to read the stream.");
                }
                try {
                    break;
                } catch (IOException unused2) {
                }
            } finally {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                    logger.e("IOException while trying to close the stream.");
                }
            }
        }
        return sb.toString();
    }
}
