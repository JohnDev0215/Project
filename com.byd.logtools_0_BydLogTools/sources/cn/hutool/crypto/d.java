package cn.hutool.crypto;

import c.a.a.c.h;
import cn.hutool.crypto.symmetric.DESede;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/* JADX INFO: compiled from: SecureUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static Cipher a(String str) {
        Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();
        try {
            return provider == null ? Cipher.getInstance(str) : Cipher.getInstance(str, provider);
        } catch (Exception e2) {
            throw new CryptoException(e2);
        }
    }

    public static MessageDigest b(String str) {
        Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();
        try {
            return provider == null ? MessageDigest.getInstance(str) : MessageDigest.getInstance(str, provider);
        } catch (NoSuchAlgorithmException e2) {
            throw new CryptoException(e2);
        }
    }

    public static byte[] c(String str) {
        return h.a(str) ? cn.hutool.core.util.d.b(str) : c.a.a.a.b.a(str);
    }

    public static DESede d(byte[] bArr) {
        return new DESede(bArr);
    }

    public static SecretKey e(String str, byte[] bArr) {
        return b.f(str, bArr);
    }
}
