package l6;

import c6.y;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.internal.l;
import org.conscrypt.Conscrypt;
import org.conscrypt.ConscryptHostnameVerifier;

/* JADX INFO: compiled from: ConscryptPlatform.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d extends h {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final boolean f7984e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f7985f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Provider f7986d;

    /* JADX INFO: compiled from: ConscryptPlatform.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final boolean a(int i7, int i8, int i9) {
            Conscrypt.Version version = Conscrypt.version();
            return version.major() != i7 ? version.major() > i7 : version.minor() != i8 ? version.minor() > i8 : version.patch() >= i9;
        }

        public final d b() {
            kotlin.jvm.internal.g gVar = null;
            if (c()) {
                return new d(gVar);
            }
            return null;
        }

        public final boolean c() {
            return d.f7984e;
        }
    }

    /* JADX INFO: compiled from: ConscryptPlatform.kt */
    public static final class b implements ConscryptHostnameVerifier {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f7987a = new b();

        private b() {
        }
    }

    static {
        a aVar = new a(null);
        f7985f = aVar;
        boolean z6 = false;
        try {
            Class.forName("org.conscrypt.Conscrypt$Version", false, aVar.getClass().getClassLoader());
            if (Conscrypt.isAvailable()) {
                if (aVar.a(2, 1, 0)) {
                    z6 = true;
                }
            }
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
        }
        f7984e = z6;
    }

    private d() {
        Provider providerNewProvider = Conscrypt.newProvider();
        l.d(providerNewProvider, "Conscrypt.newProvider()");
        this.f7986d = providerNewProvider;
    }

    @Override // l6.h
    public void e(SSLSocket sslSocket, String str, List<y> protocols) {
        l.e(sslSocket, "sslSocket");
        l.e(protocols, "protocols");
        if (!Conscrypt.isConscrypt(sslSocket)) {
            super.e(sslSocket, str, protocols);
            return;
        }
        Conscrypt.setUseSessionTickets(sslSocket, true);
        Object[] array = h.f8004c.b(protocols).toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
        Conscrypt.setApplicationProtocols(sslSocket, (String[]) array);
    }

    @Override // l6.h
    public String g(SSLSocket sslSocket) {
        l.e(sslSocket, "sslSocket");
        return Conscrypt.isConscrypt(sslSocket) ? Conscrypt.getApplicationProtocol(sslSocket) : super.g(sslSocket);
    }

    @Override // l6.h
    public SSLContext m() throws NoSuchAlgorithmException {
        SSLContext sSLContext = SSLContext.getInstance("TLS", this.f7986d);
        l.d(sSLContext, "SSLContext.getInstance(\"TLS\", provider)");
        return sSLContext;
    }

    @Override // l6.h
    public SSLSocketFactory n(X509TrustManager trustManager) throws NoSuchAlgorithmException, KeyManagementException {
        l.e(trustManager, "trustManager");
        SSLContext sSLContextM = m();
        sSLContextM.init(null, new TrustManager[]{trustManager}, null);
        SSLSocketFactory socketFactory = sSLContextM.getSocketFactory();
        l.d(socketFactory, "newSSLContext().apply {\n…null)\n    }.socketFactory");
        return socketFactory;
    }

    @Override // l6.h
    public X509TrustManager o() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        l.d(trustManagerFactory, "TrustManagerFactory.getI…(null as KeyStore?)\n    }");
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        l.c(trustManagers);
        if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
            TrustManager trustManager = trustManagers[0];
            Objects.requireNonNull(trustManager, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
            X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
            Conscrypt.setHostnameVerifier(x509TrustManager, b.f7987a);
            return x509TrustManager;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected default trust managers: ");
        String string = Arrays.toString(trustManagers);
        l.d(string, "java.util.Arrays.toString(this)");
        sb.append(string);
        throw new IllegalStateException(sb.toString().toString());
    }

    public /* synthetic */ d(kotlin.jvm.internal.g gVar) {
        this();
    }
}
