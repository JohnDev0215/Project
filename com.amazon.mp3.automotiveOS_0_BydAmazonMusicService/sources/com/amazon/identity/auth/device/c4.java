package com.amazon.identity.auth.device;

import android.util.Base64;
import android.util.Log;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class c4 {
    public static final String b = "com.amazon.identity.auth.device.c4";
    public static final SecureRandom c = new SecureRandom();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SecretKeySpec f259a;

    public c4(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("encryption key was null");
        }
        this.f259a = new SecretKeySpec(bArr, "AES");
    }

    public static String a() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            return Base64.encodeToString(keyGenerator.generateKey().getEncoded(), 0);
        } catch (NoSuchAlgorithmException unused) {
            Log.e(ga.a(b), "Could not generate a symmetric key for algorithm AES");
            return null;
        }
    }

    public byte[] b(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("dataToEncrypt is null");
        }
        byte[] bArr2 = new byte[16];
        c.nextBytes(bArr2);
        try {
            byte[] bArrA = a(a(1, "AES/CBC/PKCS7Padding", (AlgorithmParameterSpec) new IvParameterSpec(bArr2), true), bArr, 0, bArr.length);
            try {
                byte[] bArr3 = new byte[bArrA.length + 16];
                System.arraycopy(bArr2, 0, bArr3, 0, 16);
                System.arraycopy(bArrA, 0, bArr3, 16, bArrA.length);
                return bArr3;
            } catch (Exception e) {
                Log.e(ga.a(b), "Exception happended during concatenating the initialization vectors and the cipher text", e);
                return null;
            }
        } catch (BadPaddingException e2) {
            Log.e(ga.a(b), "BadPaddingException in encryption, should never happen.", e2);
            yd.a("BadPaddingExceptionInEncryption", new String[0]);
            return null;
        }
    }

    public final Cipher a(int i, String str, AlgorithmParameterSpec algorithmParameterSpec, boolean z) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(str);
            cipher.init(i, this.f259a, algorithmParameterSpec);
            return cipher;
        } catch (AssertionError e) {
            String str2 = b;
            Log.e(ga.a(str2), "Android throws AssertionError: ", e);
            if (!z || !"AES".equals(str)) {
                return cipher;
            }
            Log.i(ga.a(str2), "Retrying creating cipher");
            String.format("Retrying use a more specified mode %s, instead of %s", "AES/ECB/PKCS5Padding", "AES");
            ga.a(str2);
            return a(i, "AES/ECB/PKCS5Padding", algorithmParameterSpec, false);
        } catch (Exception e2) {
            Log.e(ga.a(b), "Aborting cipher creation", e2);
            return cipher;
        }
    }

    public static byte[] a(Cipher cipher, byte[] bArr, int i, int i2) throws BadPaddingException {
        if (cipher == null) {
            return null;
        }
        try {
            return cipher.doFinal(bArr, i, i2);
        } catch (BadPaddingException e) {
            Log.e(ga.a(b), "BadPaddingException occurs, MAP will handle it in upper level but won't cause a crash.");
            throw e;
        } catch (Exception e2) {
            Log.e(ga.a(b), "Encrypting / Decrypting failed with the given key. Aborting!", e2);
            return null;
        }
    }

    public byte[] a(byte[] bArr) throws BadPaddingException {
        if (bArr != null) {
            return a(a(2, "AES/CBC/PKCS7Padding", (AlgorithmParameterSpec) new IvParameterSpec(bArr, 0, 16), true), bArr, 16, bArr.length - 16);
        }
        throw new IllegalArgumentException("dataToDecrypt is null");
    }
}
