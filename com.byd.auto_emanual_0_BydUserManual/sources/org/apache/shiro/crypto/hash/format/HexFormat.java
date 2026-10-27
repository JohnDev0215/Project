package org.apache.shiro.crypto.hash.format;

import org.apache.shiro.crypto.hash.Hash;

/* JADX INFO: loaded from: classes.dex */
public class HexFormat implements HashFormat {
    @Override // org.apache.shiro.crypto.hash.format.HashFormat
    public String format(Hash hash) {
        if (hash != null) {
            return hash.toHex();
        }
        return null;
    }
}
