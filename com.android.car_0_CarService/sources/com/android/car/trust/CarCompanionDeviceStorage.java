package com.android.car.trust;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import android.util.Log;
import com.android.car.R;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;

/* JADX INFO: loaded from: classes3.dex */
class CarCompanionDeviceStorage {
    private static final String CIPHER_TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_AUTHENTICATION_TAG_LENGTH = 128;
    private static final String IV_SPEC_SEPARATOR = ";";
    private static final String KEYSTORE_PROVIDER = "AndroidKeyStore";
    private static final String KEY_ALIAS = "Ukey2Key";
    private static final String PREF_ENCRYPTION_KEY_PREFIX = "CTABM_encryption_key";
    private static final String TAG = CarCompanionDeviceStorage.class.getSimpleName();
    private static final String UNIQUE_ID_KEY = "CTABM_unique_id";
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private UUID mUniqueId;

    CarCompanionDeviceStorage(Context context) {
        this.mContext = context;
    }

    SharedPreferences getSharedPrefs() {
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        Context context = this.mContext;
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(context.getString(R.string.token_handle_shared_preferences), 0);
        this.mSharedPreferences = sharedPreferences2;
        return sharedPreferences2;
    }

    int getUserHandleByTokenHandle(long handle) {
        return getSharedPrefs().getInt(String.valueOf(handle), -1);
    }

    byte[] getEncryptionKey(String deviceId) {
        SharedPreferences prefs = getSharedPrefs();
        String key = createSharedPrefKey(deviceId);
        if (!prefs.contains(key)) {
            return null;
        }
        String[] values = prefs.getString(key, null).split(IV_SPEC_SEPARATOR);
        if (values.length != 2) {
            return null;
        }
        byte[] encryptedKey = Base64.decode(values[0], 0);
        byte[] ivSpec = Base64.decode(values[1], 0);
        return decryptWithKeyStore(KEY_ALIAS, encryptedKey, ivSpec);
    }

    boolean saveEncryptionKey(String deviceId, byte[] encryptionKey) {
        String encryptedKey = encryptWithKeyStore(KEY_ALIAS, encryptionKey);
        if (encryptedKey == null) {
            return false;
        }
        if (getSharedPrefs().contains(createSharedPrefKey(deviceId))) {
            clearEncryptionKey(deviceId);
        }
        return getSharedPrefs().edit().putString(createSharedPrefKey(deviceId), encryptedKey).commit();
    }

    void clearEncryptionKey(String deviceId) {
        if (deviceId == null) {
            return;
        }
        getSharedPrefs().edit().remove(createSharedPrefKey(deviceId)).commit();
    }

    String encryptWithKeyStore(String keyAlias, byte[] value) {
        if (value == null) {
            return null;
        }
        Key key = getKeyStoreKey(keyAlias);
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            cipher.init(1, key);
            StringBuffer stringBuffer = new StringBuffer(Base64.encodeToString(cipher.doFinal(value), 0));
            stringBuffer.append(IV_SPEC_SEPARATOR);
            stringBuffer.append(Base64.encodeToString(cipher.getIV(), 0));
            return stringBuffer.toString();
        } catch (IllegalStateException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            Log.e(TAG, "Unable to encrypt value with key " + keyAlias, e);
            return null;
        }
    }

    byte[] decryptWithKeyStore(String keyAlias, byte[] value, byte[] ivSpec) {
        if (value == null) {
            return null;
        }
        try {
            Key key = getKeyStoreKey(keyAlias);
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            cipher.init(2, key, new GCMParameterSpec(128, ivSpec));
            return cipher.doFinal(value);
        } catch (IllegalStateException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            Log.e(TAG, "Unable to decrypt value with key " + keyAlias, e);
            return null;
        }
    }

    private Key getKeyStoreKey(String keyAlias) {
        try {
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER);
            keyStore.load(null);
            if (!keyStore.containsAlias(keyAlias)) {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", KEYSTORE_PROVIDER);
                keyGenerator.init(new KeyGenParameterSpec.Builder(keyAlias, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").build());
                keyGenerator.generateKey();
            }
            return keyStore.getKey(keyAlias, null);
        } catch (IOException | InvalidAlgorithmParameterException | KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | UnrecoverableKeyException | CertificateException e) {
            Log.e(TAG, "Unable to retrieve key " + keyAlias + " from KeyStore.", e);
            throw new IllegalStateException(e);
        }
    }

    UUID getUniqueId() {
        UUID uuid = this.mUniqueId;
        if (uuid != null) {
            return uuid;
        }
        SharedPreferences prefs = getSharedPrefs();
        if (prefs.contains(UNIQUE_ID_KEY)) {
            this.mUniqueId = UUID.fromString(prefs.getString(UNIQUE_ID_KEY, null));
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Found existing trusted unique id: " + prefs.getString(UNIQUE_ID_KEY, ""));
            }
        } else {
            this.mUniqueId = UUID.randomUUID();
            if (!prefs.edit().putString(UNIQUE_ID_KEY, this.mUniqueId.toString()).commit()) {
                this.mUniqueId = null;
            } else if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Generated new trusted unique id: " + prefs.getString(UNIQUE_ID_KEY, ""));
            }
        }
        return this.mUniqueId;
    }

    private String createSharedPrefKey(String deviceId) {
        return PREF_ENCRYPTION_KEY_PREFIX + deviceId;
    }
}
