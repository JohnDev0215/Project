package com.amazon.mp3.download.generator;

import com.amazon.mp3.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class LocalLuidGenerator {
    public static boolean isLocalLuid(final String luid) {
        return StringUtils.isNotBlank(luid) && luid.startsWith("local-");
    }

    public static String generateLocalTrackLuid(final String asin, final String marketplace, final String localUri) {
        if (!StringUtil.isNullOrWhiteSpaces(asin) && !StringUtil.isNullOrWhiteSpaces(marketplace)) {
            return "local-" + marketplace + "-" + asin;
        }
        return generateLocalTrackLuid(localUri);
    }

    public static String generateLocalTrackLuid(final String localUri) {
        return "local-" + localUri.hashCode();
    }
}
