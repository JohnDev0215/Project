package o6;

import java.security.cert.Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.l;
import l6.h;

/* JADX INFO: compiled from: CertificateChainCleaner.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8671a = new a(null);

    /* JADX INFO: compiled from: CertificateChainCleaner.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final c a(X509TrustManager trustManager) {
            l.e(trustManager, "trustManager");
            return h.f8004c.g().c(trustManager);
        }
    }

    public abstract List<Certificate> a(List<? extends Certificate> list, String str);
}
