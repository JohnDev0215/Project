package android.security.identity;

import java.security.cert.X509Certificate;
import java.util.Collection;

/* JADX INFO: loaded from: classes2.dex */
public abstract class WritableIdentityCredential {
    public abstract Collection<X509Certificate> getCredentialKeyCertificateChain(byte[] bArr);

    public abstract byte[] personalize(PersonalizationData personalizationData);

    protected WritableIdentityCredential() {
    }
}
