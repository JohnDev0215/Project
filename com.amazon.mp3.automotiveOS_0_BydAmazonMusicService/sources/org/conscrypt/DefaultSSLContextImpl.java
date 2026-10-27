package org.conscrypt;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;

/* JADX INFO: loaded from: classes7.dex */
public class DefaultSSLContextImpl extends OpenSSLContextImpl {
    private static KeyManager[] KEY_MANAGERS;
    private static TrustManager[] TRUST_MANAGERS;

    private DefaultSSLContextImpl(String[] strArr) throws GeneralSecurityException, IOException {
        super(strArr, true);
    }

    KeyManager[] getKeyManagers() throws Throwable {
        BufferedInputStream bufferedInputStream;
        KeyManager[] keyManagerArr = KEY_MANAGERS;
        if (keyManagerArr != null) {
            return keyManagerArr;
        }
        String property = System.getProperty(SSLSocketFactoryFactory.SYSKEYSTORE);
        BufferedInputStream bufferedInputStream2 = null;
        if (property == null) {
            return null;
        }
        String property2 = System.getProperty(SSLSocketFactoryFactory.SYSKEYSTOREPWD);
        char[] charArray = property2 == null ? null : property2.toCharArray();
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(property));
        } catch (Throwable th) {
            th = th;
        }
        try {
            keyStore.load(bufferedInputStream, charArray);
            bufferedInputStream.close();
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, charArray);
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
            KEY_MANAGERS = keyManagers;
            return keyManagers;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            throw th;
        }
    }

    TrustManager[] getTrustManagers() throws Throwable {
        BufferedInputStream bufferedInputStream;
        TrustManager[] trustManagerArr = TRUST_MANAGERS;
        if (trustManagerArr != null) {
            return trustManagerArr;
        }
        String property = System.getProperty(SSLSocketFactoryFactory.SYSTRUSTSTORE);
        BufferedInputStream bufferedInputStream2 = null;
        if (property == null) {
            return null;
        }
        String property2 = System.getProperty(SSLSocketFactoryFactory.SYSTRUSTSTOREPWD);
        char[] charArray = property2 == null ? null : property2.toCharArray();
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(property));
        } catch (Throwable th) {
            th = th;
        }
        try {
            keyStore.load(bufferedInputStream, charArray);
            bufferedInputStream.close();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            TRUST_MANAGERS = trustManagers;
            return trustManagers;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            throw th;
        }
    }

    @Override // org.conscrypt.OpenSSLContextImpl, javax.net.ssl.SSLContextSpi
    public void engineInit(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, SecureRandom secureRandom) throws KeyManagementException {
        throw new KeyManagementException("Do not init() the default SSLContext ");
    }

    public static final class TLSv13 extends DefaultSSLContextImpl {
        public TLSv13() throws GeneralSecurityException, IOException {
            super(NativeCrypto.TLSV13_PROTOCOLS);
        }
    }

    public static final class TLSv12 extends DefaultSSLContextImpl {
        public TLSv12() throws GeneralSecurityException, IOException {
            super(NativeCrypto.TLSV12_PROTOCOLS);
        }
    }
}
