package org.apache.shiro.crypto.hash;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

/* JADX INFO: loaded from: classes.dex */
public class Sha512Hash extends SimpleHash {
    public static final String ALGORITHM_NAME = "SHA-512";

    public Sha512Hash() {
        super(ALGORITHM_NAME);
    }

    public Sha512Hash(Object obj) {
        super(ALGORITHM_NAME, obj);
    }

    public Sha512Hash(Object obj, Object obj2) {
        super(ALGORITHM_NAME, obj, obj2);
    }

    public Sha512Hash(Object obj, Object obj2, int i) {
        super(ALGORITHM_NAME, obj, obj2, i);
    }

    public static Sha512Hash fromHexString(String str) {
        Sha512Hash sha512Hash = new Sha512Hash();
        sha512Hash.setBytes(Hex.decode(str));
        return sha512Hash;
    }

    public static Sha512Hash fromBase64String(String str) {
        Sha512Hash sha512Hash = new Sha512Hash();
        sha512Hash.setBytes(Base64.decode(str));
        return sha512Hash;
    }
}
