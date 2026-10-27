package org.apache.http.util;

import java.io.UnsupportedEncodingException;
import org.apache.http.Consts;

/* JADX INFO: loaded from: classes7.dex */
public final class EncodingUtils {
    public static byte[] getBytes(String str, String str2) {
        Args.notNull(str, "Input");
        Args.notEmpty(str2, "Charset");
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public static byte[] getAsciiBytes(String str) {
        Args.notNull(str, "Input");
        return str.getBytes(Consts.ASCII);
    }

    private EncodingUtils() {
    }
}
