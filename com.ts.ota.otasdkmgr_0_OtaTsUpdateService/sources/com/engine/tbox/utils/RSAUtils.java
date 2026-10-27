package com.engine.tbox.utils;

import com.engine.tbox.core.utils.LogUtils;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes.dex */
public class RSAUtils {
    private static final String ALGORITHM = "RSA";
    private static final int BITES = 1024;
    private static final String END_STRING = "-----END PUBLIC KEY-----";
    private static final String MODE = "NONE";
    private static final String PADDING = "PKCS1Padding";
    private static final String START_STRING = "-----BEGIN PUBLIC KEY-----";
    private static final String TAG = "TBoxRSAUtils";

    public static byte[] rsaEncryptInputWithPublicKey(byte[] key, byte[] input) throws BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] key2;
        if (key == null || input == null) {
            LogUtils.e(TAG, "key:" + Arrays.toString(key) + " input:" + Arrays.toString(input), new Object[0]);
            return null;
        }
        String tmpKey = new String(key, StandardCharsets.UTF_8);
        LogUtils.v(TAG, "tmpKey:" + tmpKey, new Object[0]);
        try {
            key2 = Base64.getMimeDecoder().decode(tmpKey.replace(START_STRING, "").replace(END_STRING, "").trim());
        } catch (IllegalArgumentException exp) {
            exp.printStackTrace();
            key2 = key;
            LogUtils.w(TAG, "use the default key as default", new Object[0]);
        }
        X509EncodedKeySpec spec = new X509EncodedKeySpec(key2);
        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
        PublicKey publicKey = factory.generatePublic(spec);
        Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");
        cipher.init(1, publicKey);
        return cipher.doFinal(input);
    }

    public static byte[] generatorKeyPair(RsaKeyCallback callback) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        LogUtils.v(TAG, "publicKey:" + publicKey, new Object[0]);
        LogUtils.v(TAG, "privateKey:" + privateKey, new Object[0]);
        if (callback != null) {
            callback.generatorKey(publicKey, privateKey);
        }
        LogUtils.v(TAG, "publicKey:" + Arrays.toString(publicKey.getEncoded()), new Object[0]);
        return publicKey.getEncoded();
    }

    public static byte[] decryptWithKey(Key mKey, byte[] input) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");
        cipher.init(2, mKey);
        return cipher.doFinal(input);
    }
}
