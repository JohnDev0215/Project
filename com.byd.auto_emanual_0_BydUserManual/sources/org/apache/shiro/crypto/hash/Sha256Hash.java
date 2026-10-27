package org.apache.shiro.crypto.hash;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

/* JADX INFO: loaded from: classes.dex */
public class Sha256Hash extends SimpleHash {
    public static final String ALGORITHM_NAME = "SHA-256";

    public Sha256Hash() {
        super("SHA-256");
    }

    public Sha256Hash(Object obj) {
        super("SHA-256", obj);
    }

    public Sha256Hash(Object obj, Object obj2) {
        super("SHA-256", obj, obj2);
    }

    public Sha256Hash(Object obj, Object obj2, int i) {
        super("SHA-256", obj, obj2, i);
    }

    public static Sha256Hash fromHexString(String str) {
        Sha256Hash sha256Hash = new Sha256Hash();
        sha256Hash.setBytes(Hex.decode(str));
        return sha256Hash;
    }

    public static Sha256Hash fromBase64String(String str) {
        Sha256Hash sha256Hash = new Sha256Hash();
        sha256Hash.setBytes(Base64.decode(str));
        return sha256Hash;
    }
}
