package com.amazon.identity.mobi.common.utils;

import android.util.Base64;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes3.dex */
public final class StringUtils {
    private StringUtils() {
    }

    public static String decodeBase64URLToString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new String(Base64.decode(str, 11), ParserUtil.UTF_8);
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalStateException("Error during decoding, this is a system bug that this device doesn't support UTF-8 encoding.");
        }
    }

    public static String encodeToBase64URL(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Base64.encodeToString(str.getBytes(ParserUtil.UTF_8), 11);
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalStateException("Error during encoding, this is a system bug that this device doesn't support UTF-8 encoding.");
        }
    }

    public static String decodeBase64URLToString(String str, String str2) {
        try {
            return decodeBase64URLToString(str);
        } catch (Exception unused) {
            return str2;
        }
    }
}
