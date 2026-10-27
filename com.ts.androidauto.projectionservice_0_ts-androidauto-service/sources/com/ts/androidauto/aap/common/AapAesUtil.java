package com.ts.androidauto.aap.common;

import android.util.Base64;
import android.util.Log;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class AapAesUtil {
    private static final int MD5_BYTE = 16;
    private static final String TAG = "AAP" + AapAesUtil.class.getSimpleName();

    public static String encrypt(String content, String key) throws Exception {
        byte[] aesKey = generateAesKey(key);
        Log.d(TAG, "encrypt string=" + content);
        SecretKeySpec skeySpec = new SecretKeySpec(aesKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, skeySpec);
        byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
        String result = Base64.encodeToString(encrypted, 8);
        return result;
    }

    public static String decrypt(String content, String key) throws Exception {
        byte[] aesKey = generateAesKey(key);
        Log.d(TAG, "decrypt string=" + content);
        byte[] contentByte = Base64.decode(content, 8);
        SecretKeySpec skeySpec = new SecretKeySpec(aesKey, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, skeySpec);
            byte[] original = cipher.doFinal(contentByte);
            String decodeResult = new String(original, "utf-8");
            return decodeResult;
        } catch (Exception ex) {
            Log.i(TAG, "decode exception=" + ex);
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
    }

    private static byte[] generateAesKey(String keyWord) {
        byte[] firstResult = generateMd5(keyWord);
        byte[] fourthResult = new byte[0];
        try {
            byte[] secondResult = generateMd5(new String(firstResult, "utf-8"));
            byte[] thirdResult = generateMd5(new String(secondResult, "utf-8"));
            byte[] fourthResult2 = generateMd5(new String(thirdResult, "utf-8"));
            return fourthResult2;
        } catch (Exception ex) {
            Log.i(TAG, "decode process Exception=" + ex);
            return fourthResult;
        }
    }

    private static byte[] generateMd5(String key) {
        try {
            byte[] md5 = MessageDigest.getInstance("MD5").digest(key.getBytes("utf-8"));
            return md5;
        } catch (NoSuchAlgorithmException ex) {
            Log.e(TAG, "getEncodedKey NoSuchAlgorithmException ex=" + ex);
            byte[] md52 = new byte[16];
            return md52;
        } catch (Exception ex2) {
            Log.e(TAG, "getEncodedKey Exception ex=" + ex2);
            byte[] md53 = new byte[16];
            return md53;
        }
    }
}
