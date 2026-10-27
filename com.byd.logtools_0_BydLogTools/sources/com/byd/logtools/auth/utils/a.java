package com.byd.logtools.auth.utils;

import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: AESUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static String f3141a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static String f3142b = "abcdefghijklmnopqrstuvwxyz";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static String f3143c = "0123456789";

    public static String a(String str, byte[] bArr, String str2) {
        byte[] bArrDoFinal;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(1, secretKeySpec, new IvParameterSpec(str2.getBytes(StandardCharsets.UTF_8)));
            bArrDoFinal = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e2) {
            e2.printStackTrace();
            bArrDoFinal = null;
        }
        String strA = b.a(bArrDoFinal);
        Log.e("AESUtils", "加密后 base64EncodeStr:" + strA);
        return strA;
    }

    public static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] c(String str, char[] cArr, byte[] bArr, int i, int i2) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return SecretKeyFactory.getInstance(str).generateSecret(new PBEKeySpec(cArr, bArr, i, i2)).getEncoded();
    }

    public static String d(String str, String str2, int i, int i2, String str3, String str4) {
        return e(str, str2.getBytes(StandardCharsets.UTF_8), i, i2, str3, str4);
    }

    private static String e(String str, byte[] bArr, int i, int i2, String str2, String str3) {
        try {
            return a(str2, c("PBKDF2WithHmacSHA256", str.toCharArray(), bArr, i, i2), str3);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return HttpUrl.FRAGMENT_ENCODE_SET;
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
            return HttpUrl.FRAGMENT_ENCODE_SET;
        } catch (Exception e4) {
            e4.printStackTrace();
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
    }

    private static String f(String str, int i) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(str.charAt(random.nextInt(str.length())));
        }
        return stringBuffer.toString();
    }

    public static String g(int i) {
        return f(f3143c, i);
    }

    public static String h() {
        return i((int) ((Math.random() * 90.0d) + 10.0d));
    }

    public static String i(int i) {
        return f(f3142b + f3141a + f3143c, i);
    }

    public static String j(String str, boolean z) {
        return k(str, "SHA-256", z);
    }

    private static String k(String str, String str2, boolean z) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(bytes);
            String strB = b(messageDigest.digest());
            return z ? strB.toUpperCase() : strB;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
