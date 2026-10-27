package com.engine.tbox.utils;

import android.content.Context;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.data.ReservationCharging;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class AESUtils {
    private static final int AES_LENGTH = 32;
    public static final String ALGORITHM = "AES";
    public static final String ALISA = "T-BOX";
    public static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    public static final int ECB_256_LENGTH = 256;
    private static final int KEY_ID = 191;
    public static final String KEY_STORE_FILE_NAME = "tbox.keystore";
    public static final String KEY_STORE_NAME = "BKS";
    private static final int MASK_BITE1 = 15;
    private static final int MASK_BYTE = 255;
    public static final String MODE = "CBC";
    private static final int MOVE_BITE = 4;
    public static final String PADDING = "PKCS7Padding";
    private static final String TAG = "TBoxAESUtils";
    public static final String TBOX_STRING = "NEUSOFT-TBOX-BYD";
    private static final boolean USE_HSM = true;
    private PrivateKeyStore keyStore = new PrivateKeyStore();
    private Context mContext;
    private Cipher mDecryptCip;
    private Cipher mEncryCip;
    private byte[] mIv;
    private IvParameterSpec mIvSpec;
    private Key mKey;
    private static final char[] SOTRE_KEY = {'t', 'b', 'o', 'x'};
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    protected static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public AESUtils(Context context) {
        this.mContext = context;
    }

    public void prepare(String ivs) {
        if (ivs != null) {
            this.mIv = ivs.getBytes(StandardCharsets.UTF_8);
        } else {
            this.mIv = "NEUSOFT-TBOX-BYD".getBytes(StandardCharsets.UTF_8);
        }
        this.mIvSpec = new IvParameterSpec(this.mIv);
        if (this.mKey == null) {
            this.mKey = getSecretKeyFromKeyStore();
        }
        if (this.mIv != null) {
            if (this.mEncryCip == null || this.mDecryptCip == null) {
                try {
                    this.mEncryCip = Cipher.getInstance("AES/CBC/PKCS7Padding");
                    this.mDecryptCip = Cipher.getInstance("AES/CBC/PKCS7Padding");
                    this.mEncryCip.init(1, this.mKey, this.mIvSpec);
                    this.mDecryptCip.init(2, this.mKey, this.mIvSpec);
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException exp) {
                    exp.printStackTrace();
                    this.mEncryCip = null;
                    this.mDecryptCip = null;
                }
            }
        }
    }

    public boolean resetSecurityKey(String ivs) {
        try {
            this.mKey = null;
            this.keyStore.deleteKey();
            this.mEncryCip = null;
            this.mDecryptCip = null;
            prepare(ivs);
            return true;
        } catch (SecurityException exp) {
            exp.printStackTrace();
            return false;
        }
    }

    private Key getSecretKeyFromKeyStore() {
        LogUtils.i(TAG, "getSecretKeyFromKeyStore", new Object[0]);
        byte[] bytes = this.keyStore.readDataFromHsm(KEY_ID);
        if (bytes == null) {
            return generateRandomAesKey();
        }
        Key key = new SecretKeySpec(bytes, ALGORITHM);
        return key;
    }

    private Key generateRandomAesKey() {
        LogUtils.i(TAG, "generateRandomAesKey", new Object[0]);
        byte[] randomBytes = new byte[32];
        SECURE_RANDOM.nextBytes(randomBytes);
        Key key = new SecretKeySpec(randomBytes, ALGORITHM);
        this.keyStore.saveDataToHsm(KEY_ID, randomBytes);
        return key;
    }

    private Key getSecretKey() {
        FileInputStream fis = null;
        try {
            try {
                KeyStore keyStore = KeyStore.getInstance(KEY_STORE_NAME);
                FileInputStream fis2 = this.mContext.openFileInput(KEY_STORE_FILE_NAME);
                keyStore.load(fis2, SOTRE_KEY);
                Key key = keyStore.getKey(ALISA, SOTRE_KEY);
                if (key == null) {
                    key = createKey();
                } else {
                    LogUtils.v(TAG, "getSecretKey from keyStore:" + dataToHexString(key.getEncoded()), new Object[0]);
                }
                if (fis2 != null) {
                    try {
                        fis2.close();
                    } catch (IOException exp) {
                        exp.printStackTrace();
                    }
                }
                return key;
            } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException exp2) {
                exp2.printStackTrace();
                if (0 != 0) {
                    try {
                        fis.close();
                    } catch (IOException exp3) {
                        exp3.printStackTrace();
                    }
                }
                return createKey();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    fis.close();
                } catch (IOException exp4) {
                    exp4.printStackTrace();
                }
            }
            throw th;
        }
    }

    private Key generateAesKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
            kg.init(ECB_256_LENGTH);
            Key key = kg.generateKey();
            LogUtils.v(TAG, "generateAesKey Key:" + dataToHexString(key.getEncoded()), new Object[0]);
            return key;
        } catch (NoSuchAlgorithmException exp) {
            exp.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.security.Key createKey() {
        /*
            r9 = this;
            java.lang.String r0 = "createKey close the fos for KEY_STORE_FILE_NAME:tbox.keystore"
            java.lang.String r1 = "TBoxAESUtils"
            r2 = 0
            r3 = 0
            r4 = 0
            java.lang.String r5 = "BKS"
            java.security.KeyStore r5 = java.security.KeyStore.getInstance(r5)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d
            r5.load(r3, r3)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d
            java.security.Key r6 = r9.generateAesKey()     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d
            if (r6 != 0) goto L27
        L17:
            if (r2 == 0) goto L26
            java.lang.Object[] r4 = new java.lang.Object[r4]
            com.engine.tbox.core.utils.LogUtils.d(r1, r0, r4)
            r2.close()     // Catch: java.io.IOException -> L22
            goto L26
        L22:
            r0 = move-exception
            r0.printStackTrace()
        L26:
            return r3
        L27:
            java.lang.String r7 = "T-BOX"
            char[] r8 = com.engine.tbox.utils.AESUtils.SOTRE_KEY     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            r5.setKeyEntry(r7, r6, r8, r3)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            android.content.Context r7 = r9.mContext     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            java.lang.String r8 = "tbox.keystore"
            java.io.FileOutputStream r7 = r7.openFileOutput(r8, r4)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            r2 = r7
            char[] r7 = com.engine.tbox.utils.AESUtils.SOTRE_KEY     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            r5.store(r2, r7)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            r7.<init>()     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            java.lang.String r8 = "store the private key:"
            r7.append(r8)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            byte[] r8 = r6.getEncoded()     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            java.lang.String r8 = dataToHexString(r8)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            r7.append(r8)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            com.engine.tbox.core.utils.LogUtils.v(r1, r7, r8)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d java.lang.Throwable -> L6d
            if (r2 == 0) goto L6a
            java.lang.Object[] r3 = new java.lang.Object[r4]
            com.engine.tbox.core.utils.LogUtils.d(r1, r0, r3)
            r2.close()     // Catch: java.io.IOException -> L66
            goto L6a
        L66:
            r0 = move-exception
            r0.printStackTrace()
        L6a:
            return r6
        L6b:
            r3 = move-exception
            goto L82
        L6d:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L6b
            if (r2 == 0) goto L81
            java.lang.Object[] r4 = new java.lang.Object[r4]
            com.engine.tbox.core.utils.LogUtils.d(r1, r0, r4)
            r2.close()     // Catch: java.io.IOException -> L7c
        L7b:
            goto L81
        L7c:
            r0 = move-exception
            r0.printStackTrace()
            goto L7b
        L81:
            return r3
        L82:
            if (r2 == 0) goto L91
            java.lang.Object[] r4 = new java.lang.Object[r4]
            com.engine.tbox.core.utils.LogUtils.d(r1, r0, r4)
            r2.close()     // Catch: java.io.IOException -> L8d
            goto L91
        L8d:
            r0 = move-exception
            r0.printStackTrace()
        L91:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.engine.tbox.utils.AESUtils.createKey():java.security.Key");
    }

    private void deleteKey() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            try {
                try {
                    KeyStore keyStore = KeyStore.getInstance(KEY_STORE_NAME);
                    fis = this.mContext.openFileInput(KEY_STORE_FILE_NAME);
                    keyStore.load(fis, SOTRE_KEY);
                    keyStore.deleteEntry(ALISA);
                    fos = this.mContext.openFileOutput(KEY_STORE_FILE_NAME, 0);
                    keyStore.store(fos, SOTRE_KEY);
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException exp) {
                            exp.printStackTrace();
                        }
                    }
                } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException exp2) {
                    exp2.printStackTrace();
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException exp3) {
                            exp3.printStackTrace();
                        }
                    }
                    if (fos == null) {
                        return;
                    }
                    LogUtils.d(TAG, "deleteKey close the fos for KEY_STORE_FILE_NAME:tbox.keystore", new Object[0]);
                    fos.close();
                }
                if (fos != null) {
                    LogUtils.d(TAG, "deleteKey close the fos for KEY_STORE_FILE_NAME:tbox.keystore", new Object[0]);
                    fos.close();
                }
            } finally {
            }
        } catch (IOException exp4) {
            exp4.printStackTrace();
        }
    }

    public byte[] encryptByAesCbcPkcs7(String ivs, byte[] input) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException {
        prepare(ivs);
        if (this.mEncryCip == null) {
            System.err.println("cryptByAesCbcPksi7 ivs" + ivs + " input:" + dataToHexString(input));
            return null;
        }
        LogUtils.v(TAG, "encryptByAesCbcPkcs7 use Key:" + dataToHexString(this.mKey.getEncoded()) + " mEncryCip:" + this.mEncryCip + " hashcode:" + this.mEncryCip.hashCode(), new Object[0]);
        return this.mEncryCip.doFinal(input);
    }

    public byte[] decryptByAesCbcPKcs7(String ivs, byte[] input) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException {
        prepare(ivs);
        if (this.mDecryptCip == null) {
            System.err.println("cryptByAesCbcPksi7 ivs" + ivs + " input:" + dataToHexString(input));
            return null;
        }
        LogUtils.v(TAG, "key:" + this.mKey + " value:" + dataToHexString(this.mKey.getEncoded()) + " mDecryptCip:" + this.mDecryptCip + " hashcode:" + this.mDecryptCip.hashCode(), new Object[0]);
        return this.mDecryptCip.doFinal(input);
    }

    public Key getKey() {
        return this.mKey;
    }

    public void setKey(Key key) {
        this.mKey = key;
    }

    public static String dataToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int value = bytes[i] & ReservationCharging.DEFAULT_ELECTROVALENCE;
            char[] cArr = HEX_ARRAY;
            hexChars[i * 2] = cArr[value >>> 4];
            hexChars[(i * 2) + 1] = cArr[value & 15];
        }
        return new String(hexChars);
    }
}
