package com.amazon.identity.frc.FrcCookiesManager;

import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: EncryptionHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a {
    private static final String TAG = "com.amazon.identity.frc.FrcCookiesManager.a";

    private a() {
    }

    @SuppressLint({"TrulyRandom"})
    public static String a(String str, String str2) {
        try {
            if (str == null) {
                Log.e(TAG, "The input string should not be null!");
                return null;
            }
            if (str2 == null) {
                Log.e(TAG, "Seed is null, cannot encrypt the input string! Just return the original string");
                return str;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes(ParserUtil.UTF_8));
            gZIPOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, b(str2, "AES/CBC/PKCS7Padding"));
            byte[] bArrDoFinal = cipher.doFinal(byteArray);
            byte[] iv = cipher.getIV();
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(b(str2, "HmacSHA256"));
            mac.update(iv);
            mac.update(bArrDoFinal);
            byte[] bArrDoFinal2 = mac.doFinal();
            byte[] bArr = new byte[bArrDoFinal.length + 25];
            bArr[0] = 0;
            System.arraycopy(bArrDoFinal2, 0, bArr, 1, 8);
            System.arraycopy(iv, 0, bArr, 9, 16);
            System.arraycopy(bArrDoFinal, 0, bArr, 25, bArrDoFinal.length);
            return Base64.encodeToString(bArr, 2);
        } catch (Exception e) {
            Log.e(TAG, String.format(Locale.getDefault(), "Exception happened when encrypting the string:%s", e.getClass().getName()), e);
            return null;
        }
    }

    private static Key b(String str, String str2) throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(str.toCharArray(), str2.getBytes(ParserUtil.UTF_8), 1000, 128)).getEncoded(), "AES");
    }
}
