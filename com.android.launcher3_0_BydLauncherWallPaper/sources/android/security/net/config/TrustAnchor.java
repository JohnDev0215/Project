package android.security.net.config;

import java.security.cert.X509Certificate;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class TrustAnchor {
    public final X509Certificate certificate;
    public final boolean overridesPins;

    public TrustAnchor(X509Certificate x509Certificate, boolean z) {
        Objects.requireNonNull(x509Certificate, "certificate");
        this.certificate = x509Certificate;
        this.overridesPins = z;
    }
}
