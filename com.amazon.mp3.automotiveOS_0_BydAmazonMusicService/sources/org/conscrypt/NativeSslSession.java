package org.conscrypt;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import org.conscrypt.NativeRef;
import org.conscrypt.SSLUtils;

/* JADX INFO: loaded from: classes7.dex */
abstract class NativeSslSession {
    private static final Logger logger = Logger.getLogger(NativeSslSession.class.getName());

    abstract String getCipherSuite();

    abstract byte[] getId();

    abstract String getPeerHost();

    abstract byte[] getPeerOcspStapledResponse();

    abstract int getPeerPort();

    abstract byte[] getPeerSignedCertificateTimestamp();

    abstract String getProtocol();

    abstract boolean isSingleUse();

    abstract boolean isValid();

    abstract void offerToResume(NativeSsl nativeSsl) throws SSLException;

    abstract byte[] toBytes();

    abstract SSLSession toSSLSession();

    NativeSslSession() {
    }

    static NativeSslSession newInstance(NativeRef.SSL_SESSION ssl_session, ConscryptSession conscryptSession) throws SSLPeerUnverifiedException {
        AbstractSessionContext abstractSessionContext = (AbstractSessionContext) conscryptSession.getSessionContext();
        if (abstractSessionContext instanceof ClientSessionContext) {
            return new Impl(abstractSessionContext, ssl_session, conscryptSession.getPeerHost(), conscryptSession.getPeerPort(), conscryptSession.getPeerCertificates(), getOcspResponse(conscryptSession), conscryptSession.getPeerSignedCertificateTimestamp());
        }
        return new Impl(abstractSessionContext, ssl_session, null, -1, null, null, null);
    }

    private static byte[] getOcspResponse(ConscryptSession conscryptSession) {
        List<byte[]> statusResponses = conscryptSession.getStatusResponses();
        if (statusResponses.size() >= 1) {
            return statusResponses.get(0);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static org.conscrypt.NativeSslSession newInstance(org.conscrypt.AbstractSessionContext r14, byte[] r15, java.lang.String r16, int r17) {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.NativeSslSession.newInstance(org.conscrypt.AbstractSessionContext, byte[], java.lang.String, int):org.conscrypt.NativeSslSession");
    }

    private static final class Impl extends NativeSslSession {
        private final String cipherSuite;
        private final AbstractSessionContext context;
        private final String host;
        private final X509Certificate[] peerCertificates;
        private final byte[] peerOcspStapledResponse;
        private final byte[] peerSignedCertificateTimestamp;
        private final int port;
        private final String protocol;
        private final NativeRef.SSL_SESSION ref;

        private Impl(AbstractSessionContext abstractSessionContext, NativeRef.SSL_SESSION ssl_session, String str, int i, X509Certificate[] x509CertificateArr, byte[] bArr, byte[] bArr2) {
            this.context = abstractSessionContext;
            this.host = str;
            this.port = i;
            this.peerCertificates = x509CertificateArr;
            this.peerOcspStapledResponse = bArr;
            this.peerSignedCertificateTimestamp = bArr2;
            this.protocol = NativeCrypto.SSL_SESSION_get_version(ssl_session.address);
            this.cipherSuite = NativeCrypto.cipherSuiteToJava(NativeCrypto.SSL_SESSION_cipher(ssl_session.address));
            this.ref = ssl_session;
        }

        @Override // org.conscrypt.NativeSslSession
        byte[] getId() {
            return NativeCrypto.SSL_SESSION_session_id(this.ref.address);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long getCreationTime() {
            return NativeCrypto.SSL_SESSION_get_time(this.ref.address);
        }

        @Override // org.conscrypt.NativeSslSession
        boolean isValid() {
            return System.currentTimeMillis() - (Math.max(0L, Math.min((long) this.context.getSessionTimeout(), NativeCrypto.SSL_SESSION_get_timeout(this.ref.address))) * 1000) < getCreationTime();
        }

        @Override // org.conscrypt.NativeSslSession
        boolean isSingleUse() {
            return NativeCrypto.SSL_SESSION_should_be_single_use(this.ref.address);
        }

        @Override // org.conscrypt.NativeSslSession
        void offerToResume(NativeSsl nativeSsl) throws SSLException {
            nativeSsl.offerToResumeSession(this.ref.address);
        }

        @Override // org.conscrypt.NativeSslSession
        String getCipherSuite() {
            return this.cipherSuite;
        }

        @Override // org.conscrypt.NativeSslSession
        String getProtocol() {
            return this.protocol;
        }

        @Override // org.conscrypt.NativeSslSession
        String getPeerHost() {
            return this.host;
        }

        @Override // org.conscrypt.NativeSslSession
        int getPeerPort() {
            return this.port;
        }

        @Override // org.conscrypt.NativeSslSession
        byte[] getPeerOcspStapledResponse() {
            return this.peerOcspStapledResponse;
        }

        @Override // org.conscrypt.NativeSslSession
        byte[] getPeerSignedCertificateTimestamp() {
            return this.peerSignedCertificateTimestamp;
        }

        @Override // org.conscrypt.NativeSslSession
        byte[] toBytes() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeInt(SSLUtils.SessionType.OPEN_SSL_WITH_TLS_SCT.value);
                byte[] bArrI2d_SSL_SESSION = NativeCrypto.i2d_SSL_SESSION(this.ref.address);
                dataOutputStream.writeInt(bArrI2d_SSL_SESSION.length);
                dataOutputStream.write(bArrI2d_SSL_SESSION);
                dataOutputStream.writeInt(this.peerCertificates.length);
                for (X509Certificate x509Certificate : this.peerCertificates) {
                    byte[] encoded = x509Certificate.getEncoded();
                    dataOutputStream.writeInt(encoded.length);
                    dataOutputStream.write(encoded);
                }
                if (this.peerOcspStapledResponse != null) {
                    dataOutputStream.writeInt(1);
                    dataOutputStream.writeInt(this.peerOcspStapledResponse.length);
                    dataOutputStream.write(this.peerOcspStapledResponse);
                } else {
                    dataOutputStream.writeInt(0);
                }
                byte[] bArr = this.peerSignedCertificateTimestamp;
                if (bArr != null) {
                    dataOutputStream.writeInt(bArr.length);
                    dataOutputStream.write(this.peerSignedCertificateTimestamp);
                } else {
                    dataOutputStream.writeInt(0);
                }
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                NativeSslSession.logger.log(Level.WARNING, "Failed to convert saved SSL Session: ", (Throwable) e);
                return null;
            } catch (CertificateEncodingException e2) {
                NativeSslSession.log(e2);
                return null;
            }
        }

        @Override // org.conscrypt.NativeSslSession
        SSLSession toSSLSession() {
            return new SSLSession() { // from class: org.conscrypt.NativeSslSession.Impl.1
                @Override // javax.net.ssl.SSLSession
                public byte[] getId() {
                    return Impl.this.getId();
                }

                @Override // javax.net.ssl.SSLSession
                public String getCipherSuite() {
                    return Impl.this.getCipherSuite();
                }

                @Override // javax.net.ssl.SSLSession
                public String getProtocol() {
                    return Impl.this.getProtocol();
                }

                @Override // javax.net.ssl.SSLSession
                public String getPeerHost() {
                    return Impl.this.getPeerHost();
                }

                @Override // javax.net.ssl.SSLSession
                public int getPeerPort() {
                    return Impl.this.getPeerPort();
                }

                @Override // javax.net.ssl.SSLSession
                public long getCreationTime() {
                    return Impl.this.getCreationTime();
                }

                @Override // javax.net.ssl.SSLSession
                public boolean isValid() {
                    return Impl.this.isValid();
                }

                @Override // javax.net.ssl.SSLSession
                public SSLSessionContext getSessionContext() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public long getLastAccessedTime() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public void invalidate() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public void putValue(String str, Object obj) {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Object getValue(String str) {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public void removeValue(String str) {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public String[] getValueNames() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Certificate[] getLocalCertificates() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public javax.security.cert.X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public Principal getLocalPrincipal() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public int getPacketBufferSize() {
                    throw new UnsupportedOperationException();
                }

                @Override // javax.net.ssl.SSLSession
                public int getApplicationBufferSize() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(Throwable th) {
        logger.log(Level.INFO, "Error inflating SSL session: {0}", th.getMessage() != null ? th.getMessage() : th.getClass().getName());
    }

    private static void checkRemaining(ByteBuffer byteBuffer, int i) throws IOException {
        if (i < 0) {
            throw new IOException("Length is negative: " + i);
        }
        if (i <= byteBuffer.remaining()) {
            return;
        }
        throw new IOException("Length of blob is longer than available: " + i + " > " + byteBuffer.remaining());
    }
}
