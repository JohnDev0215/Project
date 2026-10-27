package org.apache.shiro.crypto.hash.format;

/* JADX INFO: loaded from: classes.dex */
public interface ModularCryptFormat extends HashFormat {
    public static final String TOKEN_DELIMITER = "$";

    String getId();
}
