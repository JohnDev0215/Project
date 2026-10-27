package org.apache.shiro.crypto.hash;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

/* JADX INFO: loaded from: classes.dex */
public class Sha384Hash extends SimpleHash {
    public static final String ALGORITHM_NAME = "SHA-384";

    public Sha384Hash() {
        super(ALGORITHM_NAME);
    }

    public Sha384Hash(Object obj) {
        super(ALGORITHM_NAME, obj);
    }

    public Sha384Hash(Object obj, Object obj2) {
        super(ALGORITHM_NAME, obj, obj2);
    }

    public Sha384Hash(Object obj, Object obj2, int i) {
        super(ALGORITHM_NAME, obj, obj2, i);
    }

    public static Sha384Hash fromHexString(String str) {
        Sha384Hash sha384Hash = new Sha384Hash();
        sha384Hash.setBytes(Hex.decode(str));
        return sha384Hash;
    }

    public static Sha384Hash fromBase64String(String str) {
        Sha384Hash sha384Hash = new Sha384Hash();
        sha384Hash.setBytes(Base64.decode(str));
        return sha384Hash;
    }
}
