package com.google.android.projection.protocol;

import android.util.Base64;
import com.google.android.projection.logging.Log;
import com.google.android.projection.protocol.SslTransportFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public final class SslTransportFactory {
    private static final String TAG = "AHU.WPP.SSL";
    private static final String TLS_12_PROTOCOL = "TLSv1.2";
    private static final String X509_CERT_FACTORY = "X.509";
    private static final String X509_CERT_HEADER = "-----BEGIN CERTIFICATE-----\n";
    private final SSLContext sslContext;

    private SslTransportFactory(String rootCert, String clientCert, String clientKey, String orgName, boolean ignoreCertificateDate) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, KeyManagementException, CertificateException, KeyStoreException {
        SSLContext sSLContext = SSLContext.getInstance(TLS_12_PROTOCOL);
        this.sslContext = sSLContext;
        sSLContext.init(new KeyManager[]{new WppKeyManager(rootCert, clientCert, clientKey, orgName)}, new TrustManager[]{new WppTrustManager(rootCert, ignoreCertificateDate, orgName)}, null);
    }

    public Socket createSslSocket(Socket socket) throws IOException {
        InetAddress address = socket.getInetAddress();
        if (address == null) {
            throw new IllegalArgumentException("Provided socket is not connected.");
        }
        SSLSocket sslSocket = (SSLSocket) this.sslContext.getSocketFactory().createSocket(socket, address.getHostAddress(), socket.getPort(), true);
        sslSocket.setUseClientMode(true);
        return sslSocket;
    }

    public static Builder builder(String rootCert, String clientCert, String clientKey) {
        return new Builder(rootCert, clientCert, clientKey);
    }

    public static final class Builder {
        private final String clientCert;
        private final String clientKey;
        private final String rootCert;
        private String orgName = "CarService";
        private boolean ignoreCertificateDate = false;

        public Builder(String rootCert, String clientCert, String clientKey) {
            this.rootCert = rootCert;
            this.clientCert = clientCert;
            this.clientKey = clientKey;
        }

        public Builder setOrgName(String orgName) {
            this.orgName = orgName;
            return this;
        }

        public Builder setIgnoreCertificateDate(boolean ignoreCertificateDate) {
            this.ignoreCertificateDate = ignoreCertificateDate;
            return this;
        }

        public SslTransportFactory build() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, CertificateException, KeyManagementException, KeyStoreException {
            return new SslTransportFactory(this.rootCert, this.clientCert, this.clientKey, this.orgName, this.ignoreCertificateDate);
        }
    }

    private static final class WppKeyManager implements X509KeyManager {
        private static final String PKCS1_FOOTER = "-----END RSA PRIVATE KEY-----\n";
        private static final String PKCS1_HEADER = "-----BEGIN RSA PRIVATE KEY-----\n";
        private static final String PKCS8_FOOTER = "-----END PRIVATE KEY-----\n";
        private static final String PKCS8_HEADER = "-----BEGIN PRIVATE KEY-----\n";
        private static final String RSA_ALGORITHM = "RSA";
        private final X509Certificate clientCert;
        private final PrivateKey clientKey;
        private final String orgName;
        private final X509Certificate rootCert;

        WppKeyManager(String rootCert, String clientCert, String clientKey, String orgName) throws InvalidKeySpecException, NoSuchAlgorithmException, CertificateException {
            this.rootCert = SslTransportFactory.createCert(rootCert);
            this.clientCert = SslTransportFactory.createCert(clientCert);
            this.clientKey = createKey(clientKey);
            this.orgName = orgName;
        }

        @Override // javax.net.ssl.X509KeyManager
        public String[] getClientAliases(String keyType, Principal[] issuers) {
            if (RSA_ALGORITHM.equals(keyType)) {
                return new String[]{this.orgName};
            }
            return null;
        }

        @Override // javax.net.ssl.X509KeyManager
        public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
            if (keyType != null && Arrays.asList(keyType).contains(RSA_ALGORITHM)) {
                return this.orgName;
            }
            return null;
        }

        @Override // javax.net.ssl.X509KeyManager
        public String[] getServerAliases(String keyType, Principal[] issuers) {
            throw new UnsupportedOperationException("SslTransportFactory is for client side auth.");
        }

        @Override // javax.net.ssl.X509KeyManager
        public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
            throw new UnsupportedOperationException("SslTransportFactory is for client side auth.");
        }

        @Override // javax.net.ssl.X509KeyManager
        public X509Certificate[] getCertificateChain(String alias) {
            return new X509Certificate[]{this.clientCert, this.rootCert};
        }

        @Override // javax.net.ssl.X509KeyManager
        public PrivateKey getPrivateKey(String alias) {
            return this.clientKey;
        }

        private static PrivateKey createKey(String key) throws InvalidKeySpecException, NoSuchAlgorithmException {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            if (key.startsWith(PKCS8_HEADER)) {
                return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(key.substring(PKCS8_HEADER.length(), key.length() - PKCS8_FOOTER.length()), 0)));
            }
            if (key.startsWith(PKCS1_HEADER)) {
                return keyFactory.generatePrivate(new X509EncodedKeySpec(Base64.decode(key.substring(PKCS1_HEADER.length(), key.length() - PKCS1_FOOTER.length()), 0)));
            }
            throw new IllegalArgumentException("Provided key material is not in a supported format");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class WppTrustManager implements X509TrustManager {
        private final boolean ignoreCertificateDate;
        private final X509TrustManager trustManager;

        private WppTrustManager(String rootCert, boolean ignoreCertificateDate, String orgName) throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException, KeyManagementException {
            this.ignoreCertificateDate = ignoreCertificateDate;
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry(orgName, SslTransportFactory.createCert(rootCert));
            trustManagerFactory.init(keyStore);
            this.trustManager = (X509TrustManager) Arrays.stream(trustManagerFactory.getTrustManagers()).filter(new Predicate() { // from class: com.google.android.projection.protocol.-$$Lambda$SslTransportFactory$WppTrustManager$cxZ4vrA7GZGNwvsBjEGAgcfAyGM
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return SslTransportFactory.WppTrustManager.lambda$new$0((TrustManager) obj);
                }
            }).findFirst().orElseThrow(new Supplier() { // from class: com.google.android.projection.protocol.-$$Lambda$SslTransportFactory$WppTrustManager$7FSl3muuXI15QOhvI1TBYNoahJ0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SslTransportFactory.WppTrustManager.lambda$new$1();
                }
            });
        }

        static /* synthetic */ boolean lambda$new$0(TrustManager manager) {
            return manager instanceof X509TrustManager;
        }

        static /* synthetic */ KeyManagementException lambda$new$1() {
            return new KeyManagementException("Unable to find X509 TrustManager.");
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            if (this.ignoreCertificateDate) {
                try {
                    this.trustManager.checkClientTrusted(chain, authType);
                    return;
                } catch (CertificateExpiredException | CertificateNotYetValidException e) {
                    Log.e(SslTransportFactory.TAG, "Ignore temporally invalid cert due to ignoreCertificateDate being true", e);
                    return;
                }
            }
            this.trustManager.checkClientTrusted(chain, authType);
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            if (this.ignoreCertificateDate) {
                try {
                    this.trustManager.checkServerTrusted(chain, authType);
                    return;
                } catch (CertificateExpiredException | CertificateNotYetValidException e) {
                    Log.e(SslTransportFactory.TAG, "Ignore temporally invalid cert due to ignoreCertificateDate being true", e);
                    return;
                }
            }
            this.trustManager.checkServerTrusted(chain, authType);
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return this.trustManager.getAcceptedIssuers();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static X509Certificate createCert(String cert) throws CertificateException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance(X509_CERT_FACTORY);
        int certHeaderIdx = cert.indexOf(X509_CERT_HEADER);
        if (certHeaderIdx == -1) {
            throw new CertificateException("Could not find certificate header.");
        }
        return (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(cert.substring(certHeaderIdx).getBytes(StandardCharsets.UTF_8)));
    }
}
