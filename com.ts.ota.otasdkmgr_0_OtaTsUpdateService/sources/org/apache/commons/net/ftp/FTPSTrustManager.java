package org.apache.commons.net.ftp;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.net.util.NetConstants;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FTPSTrustManager implements X509TrustManager {
    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] certificates, String authType) {
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
        for (X509Certificate certificate : certificates) {
            certificate.checkValidity();
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return NetConstants.EMPTY_X509_CERTIFICATE_ARRAY;
    }
}
