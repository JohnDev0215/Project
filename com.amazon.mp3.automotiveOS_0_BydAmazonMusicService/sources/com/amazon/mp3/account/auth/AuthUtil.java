package com.amazon.mp3.account.auth;

import android.text.TextUtils;
import android.util.Base64;
import com.amazon.mp3.util.Log;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/* JADX INFO: loaded from: classes3.dex */
public class AuthUtil {
    private static final String TAG = "AuthUtil";

    private enum KeyFormat {
        PKCS8,
        PKCS1,
        INVALID
    }

    public static PrivateKey parseKey(String key) throws InvalidKeySpecException {
        String strConvertPEMToBase64DER;
        KeyFormat keyFormat;
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        String strTrim = key.trim();
        if (isPEMEncoded(strTrim)) {
            strConvertPEMToBase64DER = convertPEMToBase64DER(strTrim);
            keyFormat = getKeyFormat(strTrim);
        } else {
            strConvertPEMToBase64DER = strTrim;
            keyFormat = KeyFormat.PKCS8;
        }
        if (keyFormat != KeyFormat.PKCS8) {
            throw new RuntimeException("Only PKCS8 Private key is supported");
        }
        try {
            return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(strConvertPEMToBase64DER, 0)));
        } catch (NoSuchAlgorithmException e) {
            Log.error(TAG, "Error in parseKey(): " + e.getLocalizedMessage());
            return null;
        }
    }

    private static boolean isPEMEncoded(String key) {
        return key.charAt(0) == '-' && key.charAt(key.length() - 1) == '-';
    }

    private static String convertPEMToBase64DER(String pemKey) {
        return pemKey.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", "").trim();
    }

    private static KeyFormat getKeyFormat(String pemKey) {
        if (pemKey.contains("-----BEGIN PRIVATE KEY-----")) {
            return KeyFormat.PKCS8;
        }
        if (pemKey.contains("-----BEGIN RSA PRIVATE KEY-----")) {
            return KeyFormat.PKCS1;
        }
        return KeyFormat.INVALID;
    }
}
