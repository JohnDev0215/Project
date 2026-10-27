package com.amazonaws.mobileconnectors.iot;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.conscrypt.OpenSSLProvider;

/* JADX INFO: loaded from: classes5.dex */
final class AWSIotSslUtility {
    protected static final String[] ALPN_EXTENSION = {"x-amzn-mqtt-ca"};
    private static final String TLS_V_1_2 = "TLSv1.2";

    private AWSIotSslUtility() {
    }

    public static SSLSocketFactory getSocketFactoryWithKeyStore(KeyStore keyStore, int i) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, KeyManagementException, NoSuchProviderException {
        SSLContext sSLContext;
        if (i == 443) {
            Security.addProvider(new OpenSSLProvider());
            sSLContext = SSLContext.getInstance(TLS_V_1_2, "Conscrypt");
        } else {
            sSLContext = SSLContext.getInstance(TLS_V_1_2);
        }
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, AWSIotKeystoreHelper.AWS_IOT_INTERNAL_KEYSTORE_PASSWORD.toCharArray());
        sSLContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
        return new AWSIotTLSSocketFactory(sSLContext.getSocketFactory());
    }

    public static SSLSocketFactory getSocketFactoryWithKeyStoreAndProxy(KeyStore keyStore, int i, String str, int i2) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, KeyManagementException, NoSuchProviderException {
        return new AWSIotProxiedSocketFactory(getSocketFactoryWithKeyStore(keyStore, i), str, i2);
    }
}
