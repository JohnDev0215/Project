package org.chromium.content.browser.crypto;

import android.os.AsyncTask;
import android.os.Bundle;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.chromium.base.Log;
import org.chromium.base.ObserverList;
import org.chromium.base.SecureRandomInitializer;
import org.chromium.base.ThreadUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CipherFactory {
    static final String BUNDLE_IV = "org.chromium.content.browser.crypto.CipherFactory.IV";
    static final String BUNDLE_KEY = "org.chromium.content.browser.crypto.CipherFactory.KEY";
    static final int NUM_BYTES = 16;
    private static final String TAG = "cr.CipherFactory";
    private CipherData mData;
    private FutureTask<CipherData> mDataGenerator;
    private final Object mDataLock;
    private final ObserverList<CipherDataObserver> mObservers;
    private ByteArrayGenerator mRandomNumberProvider;

    public interface CipherDataObserver {
        void onCipherDataGenerated();
    }

    private static class CipherData {
        public final byte[] iv;
        public final Key key;

        public CipherData(Key key, byte[] bArr) {
            this.key = key;
            this.iv = bArr;
        }
    }

    private static class LazyHolder {
        private static CipherFactory sInstance = new CipherFactory();

        private LazyHolder() {
        }
    }

    public static CipherFactory getInstance() {
        return LazyHolder.sInstance;
    }

    public Cipher getCipher(int i) {
        CipherData cipherData = getCipherData(true);
        if (cipherData != null) {
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(i, cipherData.key, new IvParameterSpec(cipherData.iv));
                return cipher;
            } catch (GeneralSecurityException unused) {
            }
        }
        Log.e(TAG, "Error in creating cipher instance.", new Object[0]);
        return null;
    }

    public boolean hasCipher() {
        boolean z;
        synchronized (this.mDataLock) {
            z = this.mData != null;
        }
        return z;
    }

    CipherData getCipherData(boolean z) {
        if (this.mData == null && z) {
            triggerKeyGeneration();
            try {
                CipherData cipherData = this.mDataGenerator.get();
                synchronized (this.mDataLock) {
                    if (this.mData == null) {
                        this.mData = cipherData;
                        ThreadUtils.postOnUiThread(new Runnable() { // from class: org.chromium.content.browser.crypto.CipherFactory.1
                            @Override // java.lang.Runnable
                            public void run() {
                                CipherFactory.this.notifyCipherDataGenerated();
                            }
                        });
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e2) {
                throw new RuntimeException(e2);
            }
        }
        return this.mData;
    }

    private Callable<CipherData> createGeneratorCallable() {
        return new Callable<CipherData>() { // from class: org.chromium.content.browser.crypto.CipherFactory.2
            @Override // java.util.concurrent.Callable
            public CipherData call() throws Throwable {
                try {
                    byte[] bytes = CipherFactory.this.mRandomNumberProvider.getBytes(16);
                    try {
                        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                        SecureRandomInitializer.initialize(secureRandom);
                        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                        keyGenerator.init(128, secureRandom);
                        return new CipherData(keyGenerator.generateKey(), bytes);
                    } catch (IOException unused) {
                        Log.e(CipherFactory.TAG, "Couldn't get generator data.", new Object[0]);
                        return null;
                    } catch (GeneralSecurityException unused2) {
                        Log.e(CipherFactory.TAG, "Couldn't get generator instances.", new Object[0]);
                        return null;
                    }
                } catch (IOException unused3) {
                    Log.e(CipherFactory.TAG, "Couldn't get generator data.", new Object[0]);
                    return null;
                } catch (GeneralSecurityException unused4) {
                    Log.e(CipherFactory.TAG, "Couldn't get generator data.", new Object[0]);
                    return null;
                }
            }
        };
    }

    public void triggerKeyGeneration() {
        if (this.mData != null) {
            return;
        }
        synchronized (this.mDataLock) {
            if (this.mDataGenerator == null) {
                this.mDataGenerator = new FutureTask<>(createGeneratorCallable());
                AsyncTask.THREAD_POOL_EXECUTOR.execute(this.mDataGenerator);
            }
        }
    }

    public void saveToBundle(Bundle bundle) {
        byte[] encoded;
        CipherData cipherData = getCipherData(false);
        if (cipherData == null || (encoded = cipherData.key.getEncoded()) == null || cipherData.iv == null) {
            return;
        }
        bundle.putByteArray(BUNDLE_KEY, encoded);
        bundle.putByteArray(BUNDLE_IV, cipherData.iv);
    }

    public boolean restoreFromBundle(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        byte[] byteArray = bundle.getByteArray(BUNDLE_KEY);
        byte[] byteArray2 = bundle.getByteArray(BUNDLE_IV);
        if (byteArray != null && byteArray2 != null) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(byteArray, "AES");
                synchronized (this.mDataLock) {
                    CipherData cipherData = this.mData;
                    if (cipherData == null) {
                        this.mData = new CipherData(secretKeySpec, byteArray2);
                        return true;
                    }
                    if (cipherData.key.equals(secretKeySpec) && Arrays.equals(this.mData.iv, byteArray2)) {
                        return true;
                    }
                    Log.e(TAG, "Attempted to restore different cipher data.", new Object[0]);
                }
            } catch (IllegalArgumentException unused) {
                Log.e(TAG, "Error in restoring the key from the bundle.", new Object[0]);
            }
        }
        return false;
    }

    void setRandomNumberProviderForTests(ByteArrayGenerator byteArrayGenerator) {
        this.mRandomNumberProvider = byteArrayGenerator;
    }

    public void addCipherDataObserver(CipherDataObserver cipherDataObserver) {
        this.mObservers.addObserver(cipherDataObserver);
    }

    public void removeCipherDataObserver(CipherDataObserver cipherDataObserver) {
        this.mObservers.removeObserver(cipherDataObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCipherDataGenerated() {
        Iterator<CipherDataObserver> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().onCipherDataGenerated();
        }
    }

    private CipherFactory() {
        this.mDataLock = new Object();
        this.mRandomNumberProvider = new ByteArrayGenerator();
        this.mObservers = new ObserverList<>();
    }
}
