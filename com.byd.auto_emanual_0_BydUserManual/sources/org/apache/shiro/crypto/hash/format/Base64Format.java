package org.apache.shiro.crypto.hash.format;

import org.apache.shiro.crypto.hash.Hash;

/* JADX INFO: loaded from: classes.dex */
public class Base64Format implements HashFormat {
    @Override // org.apache.shiro.crypto.hash.format.HashFormat
    public String format(Hash hash) {
        if (hash != null) {
            return hash.toBase64();
        }
        return null;
    }
}
