package com.blankj.utilcode.util;

import android.os.Build;
import com.android.volley.BuildConfig;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: EncryptUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static byte[] a(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return i(bArr, bArr2, "AES", str, bArr3, false);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, int i, String str) {
        return h(bArr, bArr2, i, str, false);
    }

    public static byte[] c(byte[] bArr) {
        return f(bArr, "MD5");
    }

    public static String d(String str) {
        return (str == null || str.length() == 0) ? BuildConfig.FLAVOR : e(str.getBytes());
    }

    public static String e(byte[] bArr) {
        return y.b(c(bArr));
    }

    static byte[] f(byte[] bArr, String str) {
        if (bArr != null && bArr.length > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str);
                messageDigest.update(bArr);
                return messageDigest.digest();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static byte[] g(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private static byte[] h(byte[] bArr, byte[] bArr2, int i, String str, boolean z) {
        if (bArr != null && bArr.length != 0 && bArr2 != null && bArr2.length != 0) {
            try {
                KeyFactory keyFactory = Build.VERSION.SDK_INT < 28 ? KeyFactory.getInstance("RSA", "BC") : KeyFactory.getInstance("RSA");
                Key keyGeneratePublic = z ? keyFactory.generatePublic(new X509EncodedKeySpec(bArr2)) : keyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArr2));
                if (keyGeneratePublic == null) {
                    return null;
                }
                Cipher cipher = Cipher.getInstance(str);
                cipher.init(z ? 1 : 2, keyGeneratePublic);
                int length = bArr.length;
                int i2 = i / 8;
                if (z && str.toLowerCase().endsWith("pkcs1padding")) {
                    i2 -= 11;
                }
                int i3 = length / i2;
                if (i3 <= 0) {
                    return cipher.doFinal(bArr);
                }
                byte[] bArrG = new byte[0];
                byte[] bArr3 = new byte[i2];
                int i4 = 0;
                for (int i5 = 0; i5 < i3; i5++) {
                    System.arraycopy(bArr, i4, bArr3, 0, i2);
                    bArrG = g(bArrG, cipher.doFinal(bArr3));
                    i4 += i2;
                }
                if (i4 == length) {
                    return bArrG;
                }
                int i6 = length - i4;
                byte[] bArr4 = new byte[i6];
                System.arraycopy(bArr, i4, bArr4, 0, i6);
                return g(bArrG, cipher.doFinal(bArr4));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static byte[] i(byte[] bArr, byte[] bArr2, String str, String str2, byte[] bArr3, boolean z) {
        SecretKey secretKeySpec;
        if (bArr != null && bArr.length != 0 && bArr2 != null && bArr2.length != 0) {
            try {
                if ("DES".equals(str)) {
                    secretKeySpec = SecretKeyFactory.getInstance(str).generateSecret(new DESKeySpec(bArr2));
                } else {
                    secretKeySpec = new SecretKeySpec(bArr2, str);
                }
                Cipher cipher = Cipher.getInstance(str2);
                int i = 1;
                if (bArr3 == null || bArr3.length == 0) {
                    if (!z) {
                        i = 2;
                    }
                    cipher.init(i, secretKeySpec);
                } else {
                    IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
                    if (!z) {
                        i = 2;
                    }
                    cipher.init(i, secretKeySpec, ivParameterSpec);
                }
                return cipher.doFinal(bArr);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }
}
