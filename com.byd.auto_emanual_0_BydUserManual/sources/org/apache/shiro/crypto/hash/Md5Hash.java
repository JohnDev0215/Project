package org.apache.shiro.crypto.hash;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

/* JADX INFO: loaded from: classes.dex */
public class Md5Hash extends SimpleHash {
    public static final String ALGORITHM_NAME = "MD5";

    public Md5Hash() {
        super(ALGORITHM_NAME);
    }

    public Md5Hash(Object obj) {
        super(ALGORITHM_NAME, obj);
    }

    public Md5Hash(Object obj, Object obj2) {
        super(ALGORITHM_NAME, obj, obj2);
    }

    public Md5Hash(Object obj, Object obj2, int i) {
        super(ALGORITHM_NAME, obj, obj2, i);
    }

    public static Md5Hash fromHexString(String str) {
        Md5Hash md5Hash = new Md5Hash();
        md5Hash.setBytes(Hex.decode(str));
        return md5Hash;
    }

    public static Md5Hash fromBase64String(String str) {
        Md5Hash md5Hash = new Md5Hash();
        md5Hash.setBytes(Base64.decode(str));
        return md5Hash;
    }
}
