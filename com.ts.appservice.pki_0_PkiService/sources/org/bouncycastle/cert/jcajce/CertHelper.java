package org.bouncycastle.cert.jcajce;

import java.security.cert.CertificateFactory;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
abstract class CertHelper {
    CertHelper() {
    }

    protected abstract CertificateFactory createCertificateFactory(String str);

    public CertificateFactory getCertificateFactory(String str) {
        return createCertificateFactory(str);
    }
}
