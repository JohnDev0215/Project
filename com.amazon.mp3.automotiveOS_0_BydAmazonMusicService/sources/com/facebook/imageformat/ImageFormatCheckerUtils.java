package com.facebook.imageformat;

import com.facebook.common.internal.Preconditions;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes5.dex */
public class ImageFormatCheckerUtils {
    public static byte[] asciiBytes(String value) {
        Preconditions.checkNotNull(value);
        try {
            return value.getBytes("ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    public static boolean startsWithPattern(final byte[] byteArray, final byte[] pattern) {
        return hasPatternAt(byteArray, pattern, 0);
    }

    public static boolean hasPatternAt(final byte[] byteArray, final byte[] pattern, int offset) {
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkNotNull(pattern);
        if (pattern.length + offset > byteArray.length) {
            return false;
        }
        for (int i = 0; i < pattern.length; i++) {
            if (byteArray[offset + i] != pattern[i]) {
                return false;
            }
        }
        return true;
    }

    private ImageFormatCheckerUtils() {
    }
}
