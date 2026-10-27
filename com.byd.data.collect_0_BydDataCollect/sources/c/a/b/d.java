package c.a.b;

import c.a.a.g.k;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: KeyUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static SecretKey a(String str, byte[] bArr) {
        if (c.a.a.f.b.h(str) || !str.startsWith("DES")) {
            throw new b("Algorithm [{}] is not a DES algorithm!", str);
        }
        if (bArr == null) {
            return b(str);
        }
        try {
            return e(str, str.startsWith("DESede") ? new DESedeKeySpec(bArr) : new DESKeySpec(bArr));
        } catch (InvalidKeyException e2) {
            throw new b(e2);
        }
    }

    public static SecretKey b(String str) {
        return c(str, -1);
    }

    public static SecretKey c(String str, int i2) {
        return d(str, i2, null);
    }

    public static SecretKey d(String str, int i2, SecureRandom secureRandom) {
        String strI = i(str);
        KeyGenerator keyGeneratorH = h(strI);
        if (i2 <= 0 && c.a.b.j.e.AES.a().equals(strI)) {
            i2 = 128;
        }
        if (i2 > 0) {
            if (secureRandom == null) {
                keyGeneratorH.init(i2);
            } else {
                keyGeneratorH.init(i2, secureRandom);
            }
        }
        return keyGeneratorH.generateKey();
    }

    public static SecretKey e(String str, KeySpec keySpec) {
        try {
            return j(str).generateSecret(keySpec);
        } catch (InvalidKeySpecException e2) {
            throw new b(e2);
        }
    }

    public static SecretKey f(String str, byte[] bArr) {
        c.a.a.d.d.d(str, "Algorithm is blank!", new Object[0]);
        if (str.startsWith("PBE")) {
            return g(str, bArr == null ? null : k.w(bArr).toCharArray());
        }
        return str.startsWith("DES") ? a(str, bArr) : bArr == null ? b(str) : new SecretKeySpec(bArr, str);
    }

    public static SecretKey g(String str, char[] cArr) {
        if (c.a.a.f.b.h(str) || !str.startsWith("PBE")) {
            throw new b("Algorithm [{}] is not a PBE algorithm!", str);
        }
        if (cArr == null) {
            cArr = c.a.a.g.h.d(32).toCharArray();
        }
        return e(str, new PBEKeySpec(cArr));
    }

    public static KeyGenerator h(String str) {
        Provider providerA = c.INSTANCE.a();
        try {
            return providerA == null ? KeyGenerator.getInstance(i(str)) : KeyGenerator.getInstance(i(str), providerA);
        } catch (NoSuchAlgorithmException e2) {
            throw new b(e2);
        }
    }

    public static String i(String str) {
        c.a.a.d.d.d(str, "Algorithm must be not blank!", new Object[0]);
        int iIndexOf = str.indexOf(47);
        return iIndexOf > 0 ? str.substring(0, iIndexOf) : str;
    }

    public static SecretKeyFactory j(String str) {
        Provider providerA = c.INSTANCE.a();
        try {
            return providerA == null ? SecretKeyFactory.getInstance(i(str)) : SecretKeyFactory.getInstance(i(str), providerA);
        } catch (NoSuchAlgorithmException e2) {
            throw new b(e2);
        }
    }
}
