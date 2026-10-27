package com.amazon.music.crypto;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class SharedPrefCrypto implements SharedPreferences {
    private static final Logger LOG = LoggerFactory.getLogger(SharedPrefCrypto.class.getSimpleName());
    private static ConcurrentHashMap<String, Object> encryptionLocks = new ConcurrentHashMap<>();
    private final Crypto mCrypto;
    private SharedPreferences.OnSharedPreferenceChangeListener mListener;
    private final List<SharedPreferences.OnSharedPreferenceChangeListener> mListeners = new ArrayList();
    private final Object mLock = new Object();
    private final SharedPreferences mSharedPref;

    public static SharedPreferences encrypt(Crypto crypto, SharedPreferences sharedPreferences) {
        return new SharedPrefCrypto(crypto, sharedPreferences);
    }

    public static Object getEncryptionLock(String str) {
        if (!encryptionLocks.contains(str)) {
            encryptionLocks.put(str, new Object());
        }
        return encryptionLocks.get(str);
    }

    private SharedPrefCrypto(Crypto crypto, SharedPreferences sharedPreferences) {
        synchronized (Crypto.class) {
            getEncryptionLock(crypto.getAlias());
            synchronized (getEncryptionLock(crypto.getAlias())) {
                this.mCrypto = (Crypto) Validate.notNull(crypto);
                this.mSharedPref = (SharedPreferences) Validate.notNull(sharedPreferences);
                init();
            }
        }
    }

    private boolean hasEncryptedByIndex(String str, boolean z, int i) {
        try {
            String string = this.mSharedPref.getString(findCorrectEncryptKey(str, i), null);
            return string == null ? z : Boolean.parseBoolean(this.mCrypto.getCryptoInterceptors().get(i).decrypt(string));
        } catch (Exception unused) {
            LOG.error("could not get boolean from shared pref");
            return z;
        }
    }

    private void init() {
        int encryptionAlgorithmIndex = this.mCrypto.getEncryptionAlgorithmIndex();
        int i = 0;
        while (true) {
            if (i >= this.mCrypto.getCryptoInterceptors().size()) {
                i = -1;
                break;
            } else if (hasEncryptedByIndex("shared_pref_encrypted", false, i)) {
                break;
            } else {
                i++;
            }
        }
        this.mCrypto.setEncryptionAlgorithmIndex(encryptionAlgorithmIndex);
        if (i == -1) {
            doEncryption();
            return;
        }
        if (i != this.mCrypto.getEncryptionAlgorithmIndex()) {
            LOG.error("start migrate " + this.mCrypto.getAlias());
            doEncryptionMigrate(i);
        }
    }

    private void doEncryptionMigrate(int i) {
        try {
            Map<String, ?> all = this.mSharedPref.getAll();
            SharedPreferences.Editor editorEdit = this.mSharedPref.edit();
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (!"gcm_upgraded".equals(key)) {
                    editorEdit.putString(this.mCrypto.encrypt(this.mCrypto.getCryptoInterceptors().get(i).decrypt(key)), this.mCrypto.encrypt(this.mCrypto.getCryptoInterceptors().get(i).decrypt(value.toString())));
                    editorEdit.remove(key);
                }
            }
            editorEdit.putString(this.mCrypto.encrypt("shared_pref_encrypted"), this.mCrypto.encrypt("true"));
            if (i <= this.mCrypto.getEncryptionAlgorithmIndex()) {
                LOG.info("Successfully upgrade to GCM");
                editorEdit.putBoolean("gcm_upgraded", true);
            } else {
                LOG.info("Successfully downgrade to ECB");
                editorEdit.putBoolean("gcm_upgraded", false);
            }
            editorEdit.remove(this.mCrypto.getCryptoInterceptors().get(i).encrypt("shared_pref_encrypted"));
            editorEdit.apply();
            this.mCrypto.tryReinitECB();
        } catch (Exception e) {
            if (i <= this.mCrypto.getEncryptionAlgorithmIndex()) {
                LOG.error("Failed to do upgrade to GCM", (Throwable) e);
            } else {
                LOG.error("Failed to downgrade to ECB", (Throwable) e);
            }
        }
    }

    private void doEncryption() {
        Map<String, ?> all = this.mSharedPref.getAll();
        SharedPreferences.Editor editorEdit = this.mSharedPref.edit();
        SharedPreferences.Editor editorEdit2 = edit();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Set) {
                editorEdit2.putStringSet(key, (Set) value);
            } else if (value instanceof Integer) {
                editorEdit2.putInt(key, ((Integer) value).intValue());
            } else if (value instanceof Float) {
                editorEdit2.putFloat(key, ((Float) value).floatValue());
            } else if (value instanceof String) {
                editorEdit2.putString(key, (String) value);
            } else if (value instanceof Long) {
                editorEdit2.putLong(key, ((Long) value).longValue());
            } else if (value instanceof Boolean) {
                editorEdit2.putBoolean(key, ((Boolean) value).booleanValue());
            }
            editorEdit.remove(key);
        }
        editorEdit.apply();
        editorEdit2.putBoolean("shared_pref_encrypted", true);
        editorEdit2.apply();
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        boolean zContains;
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            try {
                try {
                    zContains = this.mSharedPref.contains(this.mCrypto.encrypt(str));
                } catch (EncryptionException e) {
                    LOG.error("Failed to encrypt key", (Throwable) e);
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zContains;
    }

    private void adjustEncryptionModeIfNeeded() {
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            try {
            } catch (Exception e) {
                LOG.error("Failed to add GCM mode on the fly ", (Throwable) e);
            }
            if (this.mCrypto.getEncryptionAlgorithmIndex() == 0 && this.mSharedPref.getBoolean("gcm_upgraded", false)) {
                this.mCrypto.addGCMModeOnTheFly();
            }
        }
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        SharedPrefCryptoEditor sharedPrefCryptoEditor;
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            sharedPrefCryptoEditor = new SharedPrefCryptoEditor(this.mSharedPref, this.mCrypto);
        }
        return sharedPrefCryptoEditor;
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        synchronized (this.mLock) {
            if (!this.mListeners.contains(onSharedPreferenceChangeListener)) {
                this.mListeners.add(onSharedPreferenceChangeListener);
                if (this.mListener == null) {
                    setupListener();
                }
            }
        }
    }

    private void setupListener() {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.amazon.music.crypto.SharedPrefCrypto.1
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                try {
                    String strDecrypt = SharedPrefCrypto.this.mCrypto.decrypt(str);
                    Iterator it = SharedPrefCrypto.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((SharedPreferences.OnSharedPreferenceChangeListener) it.next()).onSharedPreferenceChanged(sharedPreferences, strDecrypt);
                    }
                } catch (DecryptionException e) {
                    SharedPrefCrypto.LOG.warn("Failed to decrypt key", (Throwable) e);
                }
            }
        };
        this.mListener = onSharedPreferenceChangeListener;
        this.mSharedPref.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        synchronized (this.mLock) {
            this.mListeners.remove(onSharedPreferenceChangeListener);
            if (this.mListeners.isEmpty()) {
                this.mSharedPref.unregisterOnSharedPreferenceChangeListener(this.mListener);
                this.mListener = null;
            }
        }
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            String encryptedValue = getEncryptedValue(str);
            if (encryptedValue == null) {
                return z;
            }
            try {
                return Boolean.parseBoolean(this.mCrypto.decrypt(encryptedValue));
            } catch (DecryptionException unused) {
                LOG.warn("could not get boolean from shared pref");
                return z;
            }
        }
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            try {
                this.mCrypto.tryReinitECB();
            } catch (Exception unused) {
                LOG.error("fail to reinit ECB");
            }
            String encryptedValue = getEncryptedValue(str);
            if (encryptedValue == null) {
                LOG.error("fail to get encrypted value for  " + str + " from " + this.mCrypto.getAlias());
                return str2;
            }
            try {
                return this.mCrypto.decrypt(encryptedValue);
            } catch (DecryptionException unused2) {
                Logger logger = LOG;
                logger.warn("could not get string from shared pref");
                logger.error("could not get " + str + " from " + this.mCrypto.getAlias());
                return str2;
            }
        }
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f) {
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            String encryptedValue = getEncryptedValue(str);
            if (encryptedValue == null) {
                return f;
            }
            try {
                return Float.parseFloat(this.mCrypto.decrypt(encryptedValue));
            } catch (DecryptionException unused) {
                LOG.warn("could not get float from shared pref");
                return f;
            }
        }
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i) {
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            String encryptedValue = getEncryptedValue(str);
            if (encryptedValue == null) {
                return i;
            }
            try {
                return Integer.parseInt(this.mCrypto.decrypt(encryptedValue));
            } catch (DecryptionException unused) {
                LOG.warn("could not get integer from shared pref");
                return i;
            }
        }
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j) {
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            String encryptedValue = getEncryptedValue(str);
            if (encryptedValue == null) {
                return j;
            }
            try {
                return Long.parseLong(this.mCrypto.decrypt(encryptedValue));
            } catch (DecryptionException unused) {
                LOG.warn("could not get long from shared pref");
                return j;
            }
        }
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            Set<String> encryptedSet = getEncryptedSet(str);
            if (encryptedSet == null) {
                return set;
            }
            HashSet hashSet = new HashSet(encryptedSet.size());
            try {
                Iterator<String> it = encryptedSet.iterator();
                while (it.hasNext()) {
                    hashSet.add(this.mCrypto.decrypt(it.next()));
                }
                return hashSet;
            } catch (DecryptionException unused) {
                LOG.warn("could not get string set from shared pref");
                return set;
            }
        }
    }

    private String getEncryptedValue(String str) {
        String string;
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            string = this.mSharedPref.getString(findCorrectEncryptKey(str, this.mCrypto.getEncryptionAlgorithmIndex()), null);
        }
        return string;
    }

    private String findCorrectEncryptKey(String str, int i) {
        String str2;
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            Iterator<String> it = this.mSharedPref.getAll().keySet().iterator();
            while (true) {
                str2 = null;
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (!next.equalsIgnoreCase("gcm_upgraded")) {
                    try {
                        if (str.equalsIgnoreCase(this.mCrypto.getCryptoInterceptors().get(i).decrypt(next))) {
                            str2 = next;
                            break;
                        }
                    } catch (Exception unused) {
                        return null;
                    }
                }
            }
        }
        return str2;
    }

    private Set<String> getEncryptedSet(String str) {
        Set<String> stringSet;
        synchronized (getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            try {
                stringSet = this.mSharedPref.getStringSet(findCorrectEncryptKey(str, this.mCrypto.getEncryptionAlgorithmIndex()), null);
            } catch (Exception unused) {
                return null;
            }
        }
        return stringSet;
    }
}
