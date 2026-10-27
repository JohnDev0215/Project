package c.a.b;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Cipher;

/* JADX INFO: compiled from: SecureUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class h {
    public static Cipher a(String str) {
        Provider providerA = c.INSTANCE.a();
        try {
            return providerA == null ? Cipher.getInstance(str) : Cipher.getInstance(str, providerA);
        } catch (Exception e2) {
            throw new b(e2);
        }
    }

    public static MessageDigest b(String str) {
        Provider providerA = c.INSTANCE.a();
        try {
            return providerA == null ? MessageDigest.getInstance(str) : MessageDigest.getInstance(str, providerA);
        } catch (NoSuchAlgorithmException e2) {
            throw new b(e2);
        }
    }

    public static byte[] c(String str) {
        return c.a.a.d.h.a(str) ? c.a.a.g.d.b(str) : c.a.a.a.b.a(str);
    }

    public static c.a.b.j.d d(byte[] bArr) {
        return new c.a.b.j.d(bArr);
    }
}
