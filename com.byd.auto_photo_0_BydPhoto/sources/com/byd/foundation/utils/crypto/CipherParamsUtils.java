package com.byd.foundation.utils.crypto;

import android.security.keystore.KeyGenParameterSpec;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class CipherParamsUtils {
    public static final int IV_BIT_LENGTH = 12;
    public static final String KEY_ALGORITHM_AES = "AES";

    public static byte[] generateIv() {
        byte[] bArr = new byte[12];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    @NonNull
    @RequiresApi(api = 23)
    public static Key generateKey(String str) throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        return keyStore.containsAlias(str) ? keyStore.getKey(str, null) : generateKeyInner(str);
    }

    public static Key generateKey(@NonNull byte[] bArr) {
        if (bArr.length == 16 || bArr.length == 24 || bArr.length == 32) {
            return new SecretKeySpec(bArr, KEY_ALGORITHM_AES);
        }
        throw new IllegalArgumentException("The key length must be 16/24/32, but key length is " + bArr.length);
    }

    @RequiresApi(api = 23)
    private static Key generateKeyInner(String str) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM_AES, "AndroidKeyStore");
        keyGenerator.init(new KeyGenParameterSpec.Builder(str, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setRandomizedEncryptionRequired(false).build());
        return keyGenerator.generateKey();
    }
}
