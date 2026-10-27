package f.k0.n;

import f.k0.l.f;
import java.security.cert.Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: CertificateChainCleaner.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static c b(X509TrustManager x509TrustManager) {
        return f.j().c(x509TrustManager);
    }

    public abstract List<Certificate> a(List<Certificate> list, String str);
}
