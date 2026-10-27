package org.apache.shiro.crypto.hash;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

/* JADX INFO: loaded from: classes.dex */
public class Sha1Hash extends SimpleHash {
    public static final String ALGORITHM_NAME = "SHA-1";

    public Sha1Hash() {
        super(ALGORITHM_NAME);
    }

    public Sha1Hash(Object obj) {
        super(ALGORITHM_NAME, obj);
    }

    public Sha1Hash(Object obj, Object obj2) {
        super(ALGORITHM_NAME, obj, obj2);
    }

    public Sha1Hash(Object obj, Object obj2, int i) {
        super(ALGORITHM_NAME, obj, obj2, i);
    }

    public static Sha1Hash fromHexString(String str) {
        Sha1Hash sha1Hash = new Sha1Hash();
        sha1Hash.setBytes(Hex.decode(str));
        return sha1Hash;
    }

    public static Sha1Hash fromBase64String(String str) {
        Sha1Hash sha1Hash = new Sha1Hash();
        sha1Hash.setBytes(Base64.decode(str));
        return sha1Hash;
    }
}
