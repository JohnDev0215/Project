package com.amazon.identity.auth.device.storage;

import com.amazon.identity.auth.device.api.MAPError;
import java.security.KeyStore;
import javax.crypto.SecretKey;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class KeystoreProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final KeyStore f552a = b();
    public final String b;

    /* JADX INFO: compiled from: DCP */
    public static final class KeystoreProviderException extends Exception {
        private static final long serialVersionUID = -7354549861193710767L;
        private final MAPError mError;
        private final String mErrorMessage;

        public KeystoreProviderException(MAPError mAPError, String str, Throwable th) {
            super(th.getMessage(), th);
            this.mError = mAPError;
            this.mErrorMessage = str;
        }

        public String a() {
            return this.mErrorMessage;
        }
    }

    public KeystoreProvider(String str) throws KeystoreProviderException {
        this.b = str;
    }

    public SecretKey a() throws KeystoreProviderException {
        try {
            return (SecretKey) this.f552a.getKey(this.b, null);
        } catch (Exception e) {
            throw new KeystoreProviderException(MAPError.CommonError.INTERNAL_ERROR, e.getMessage(), e);
        }
    }

    public final KeyStore b() throws KeystoreProviderException {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore;
        } catch (Exception e) {
            throw new KeystoreProviderException(MAPError.CommonError.INTERNAL_ERROR, e.getMessage(), e);
        }
    }
}
