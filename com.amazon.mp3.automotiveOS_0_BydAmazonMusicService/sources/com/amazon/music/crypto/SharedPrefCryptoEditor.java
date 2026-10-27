package com.amazon.music.crypto;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
final class SharedPrefCryptoEditor implements SharedPreferences.Editor {
    private static final Logger LOG = LoggerFactory.getLogger(SharedPrefCrypto.class.getSimpleName());
    private final Crypto mCrypto;
    private final SharedPreferences.Editor mEditor;
    private final SharedPreferences mSharedPreferences;

    public SharedPrefCryptoEditor(SharedPreferences sharedPreferences, Crypto crypto) {
        this.mEditor = ((SharedPreferences) Validate.notNull(sharedPreferences)).edit();
        this.mCrypto = (Crypto) Validate.notNull(crypto);
        this.mSharedPreferences = sharedPreferences;
    }

    private void adjustEncryptionModeIfNeeded() {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            try {
            } catch (Exception e) {
                LOG.error("Failed to add GCM mode on the fly ", (Throwable) e);
            }
            if (this.mCrypto.getCryptoInterceptors().size() == 1 && this.mSharedPreferences.getBoolean("gcm_upgraded", false)) {
                this.mCrypto.addGCMModeOnTheFly();
            }
        }
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putString(String str, String str2) {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            if (str2 != null) {
                try {
                    String strEncrypt = this.mCrypto.encrypt(str);
                    String strEncrypt2 = this.mCrypto.encrypt(str2);
                    removeOriginalKey(str);
                    save(strEncrypt, strEncrypt2);
                } catch (EncryptionException unused) {
                    LOG.warn("could not add string to shared pref");
                }
            } else {
                remove(str);
            }
        }
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            try {
                String strEncrypt = this.mCrypto.encrypt(str);
                HashSet hashSet = new HashSet(set.size());
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    hashSet.add(this.mCrypto.encrypt(it.next()));
                }
                removeOriginalKey(str);
                save(strEncrypt, hashSet);
            } catch (EncryptionException unused) {
                LOG.warn("could not add string set to shared pref");
            }
        }
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putInt(String str, int i) {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            try {
                String strEncrypt = this.mCrypto.encrypt(str);
                String strEncrypt2 = this.mCrypto.encrypt(String.valueOf(i));
                removeOriginalKey(str);
                save(strEncrypt, strEncrypt2);
            } catch (EncryptionException unused) {
                LOG.warn("could not add Integer to shared pref");
            }
        }
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putLong(String str, long j) {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            try {
                String strEncrypt = this.mCrypto.encrypt(str);
                String strEncrypt2 = this.mCrypto.encrypt(String.valueOf(j));
                removeOriginalKey(str);
                save(strEncrypt, strEncrypt2);
            } catch (EncryptionException unused) {
                LOG.warn("could not add long to shared pref");
            }
        }
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putFloat(String str, float f) {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            try {
                String strEncrypt = this.mCrypto.encrypt(str);
                String strEncrypt2 = this.mCrypto.encrypt(String.valueOf(f));
                removeOriginalKey(str);
                save(strEncrypt, strEncrypt2);
            } catch (EncryptionException unused) {
                LOG.warn("could not add float to shared pref");
            }
        }
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putBoolean(String str, boolean z) {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            try {
                String strEncrypt = this.mCrypto.encrypt(str);
                String strEncrypt2 = this.mCrypto.encrypt(String.valueOf(z));
                removeOriginalKey(str);
                save(strEncrypt, strEncrypt2);
            } catch (EncryptionException unused) {
                LOG.warn("could not add boolean to shared pref");
            }
        }
        return this;
    }

    private void removeOriginalKey(String str) {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            List<String> correctRemoveKey = getCorrectRemoveKey(str);
            if (correctRemoveKey != null) {
                Iterator<String> it = correctRemoveKey.iterator();
                while (it.hasNext()) {
                    this.mEditor.remove(it.next());
                }
            }
        }
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor remove(String str) {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            adjustEncryptionModeIfNeeded();
            try {
                Iterator<String> it = getCorrectRemoveKey(str).iterator();
                while (it.hasNext()) {
                    this.mEditor.remove(it.next());
                }
            } catch (Exception unused) {
                LOG.warn("could not remove key from shared pref");
            }
        }
        return this;
    }

    private List<String> getCorrectRemoveKey(String str) {
        try {
            adjustEncryptionModeIfNeeded();
            ArrayList arrayList = new ArrayList();
            for (String str2 : this.mSharedPreferences.getAll().keySet()) {
                if (!str2.equalsIgnoreCase("gcm_upgraded") && str.equalsIgnoreCase(this.mCrypto.decrypt(str2))) {
                    arrayList.add(str2);
                }
            }
            return arrayList;
        } catch (DecryptionException unused) {
            return null;
        }
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor clear() {
        SharedPreferences.Editor editorClear;
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            editorClear = this.mEditor.clear();
        }
        return editorClear;
    }

    @Override // android.content.SharedPreferences.Editor
    public boolean commit() {
        boolean zCommit;
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            zCommit = this.mEditor.commit();
        }
        return zCommit;
    }

    @Override // android.content.SharedPreferences.Editor
    public void apply() {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            this.mEditor.apply();
        }
    }

    private void save(String str, String str2) {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            this.mEditor.putString(str, str2);
            addIsEncryptedEntry(this.mEditor);
        }
    }

    private void save(String str, Set<String> set) {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            this.mEditor.putStringSet(str, set);
            removeOriginalKey("shared_pref_encrypted");
            this.mEditor.putBoolean("shared_pref_encrypted", true);
            addIsEncryptedEntry(this.mEditor);
        }
    }

    private void addIsEncryptedEntry(SharedPreferences.Editor editor) {
        synchronized (SharedPrefCrypto.getEncryptionLock(this.mCrypto.getAlias())) {
            try {
                adjustEncryptionModeIfNeeded();
                String strEncrypt = this.mCrypto.encrypt("shared_pref_encrypted");
                String strEncrypt2 = this.mCrypto.encrypt(String.valueOf(true));
                removeOriginalKey("shared_pref_encrypted");
                editor.putString(strEncrypt, strEncrypt2);
            } catch (EncryptionException unused) {
                LOG.warn("could not set encrypted flag on shared pref");
            }
        }
    }
}
