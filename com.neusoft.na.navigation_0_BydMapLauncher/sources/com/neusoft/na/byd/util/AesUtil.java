package com.neusoft.na.byd.util;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.neusoft.na.common.FileUtils;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* JADX INFO: loaded from: classes.dex */
public class AesUtil {
    private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String DEFAULT_VALUE = "0";
    private static final String KEY_ALGORITHM = "AES";
    public static final int SECRET_KEY_LENGTH = 32;
    private static final String TAG = "XjAesUtil";

    public static String getMD5(Context context) throws Exception {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(context.getPackageManager().getPackageInfo(context.getPackageName(), 134217728).signingInfo.getApkContentsSigners()[0].toByteArray());
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3).toUpperCase());
            }
            return sb.substring(0, sb.length());
        } catch (Exception e) {
            e.printStackTrace();
            return "BEYONDAMANI";
        }
    }

    public static String encrypt(Context context, String str) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(1, getSecretKey(getMD5(context)));
            return base64Encode(cipher.doFinal(str.getBytes(CHARSET_UTF8)));
        } catch (Exception e) {
            handleException(context, e);
            return null;
        }
    }

    public static String decrypt(Context context, String str) {
        try {
            byte[] bArrBase64Decode = base64Decode(str);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(2, getSecretKey(getMD5(context)));
            return new String(cipher.doFinal(bArrBase64Decode), CHARSET_UTF8);
        } catch (Exception e) {
            handleException(context, e);
            return null;
        }
    }

    public static SecretKeySpec getSecretKey(String str) {
        return new SecretKeySpec(toMakeKey(str, 32, "0").getBytes(CHARSET_UTF8), KEY_ALGORITHM);
    }

    private static String toMakeKey(String str, int i, String str2) {
        int length = str.length();
        if (length >= i) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        for (int i2 = 0; i2 < i - length; i2++) {
            sb.append(str2);
        }
        return sb.toString();
    }

    public static byte[] base64Decode(String str) {
        return Base64.decode(str, 2);
    }

    public static String base64Encode(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    private static void handleException(Context context, Exception exc) {
        FileUtils.getInstance(context).updatePrivacyStatus(false);
        exc.printStackTrace();
        Log.e(TAG, TAG + exc);
    }
}
