package org.apache.shiro.crypto.hash;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

/* JADX INFO: loaded from: classes.dex */
public class Md2Hash extends SimpleHash {
    public static final String ALGORITHM_NAME = "MD2";

    public Md2Hash() {
        super(ALGORITHM_NAME);
    }

    public Md2Hash(Object obj) {
        super(ALGORITHM_NAME, obj);
    }

    public Md2Hash(Object obj, Object obj2) {
        super(ALGORITHM_NAME, obj, obj2);
    }

    public Md2Hash(Object obj, Object obj2, int i) {
        super(ALGORITHM_NAME, obj, obj2, i);
    }

    public static Md2Hash fromHexString(String str) {
        Md2Hash md2Hash = new Md2Hash();
        md2Hash.setBytes(Hex.decode(str));
        return md2Hash;
    }

    public static Md2Hash fromBase64String(String str) {
        Md2Hash md2Hash = new Md2Hash();
        md2Hash.setBytes(Base64.decode(str));
        return md2Hash;
    }
}
