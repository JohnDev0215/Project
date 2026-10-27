package org.apache.shiro.crypto.hash.format;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public enum ProvidedHashFormat {
    HEX(HexFormat.class),
    BASE64(Base64Format.class),
    SHIRO1(Shiro1CryptFormat.class);

    private final Class<? extends HashFormat> clazz;

    ProvidedHashFormat(Class cls) {
        this.clazz = cls;
    }

    Class<? extends HashFormat> getHashFormatClass() {
        return this.clazz;
    }

    public static ProvidedHashFormat byId(String str) {
        if (str == null) {
            return null;
        }
        try {
            return valueOf(str.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
