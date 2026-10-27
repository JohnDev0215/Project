package l6;

import c6.x;
import c6.y;
import h5.q;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: Platform.kt */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile h f8002a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Logger f8003b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f8004c;

    /* JADX INFO: compiled from: Platform.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        private final h d() {
            m6.c.f8412c.b();
            h hVarA = l6.a.f7973f.a();
            if (hVarA != null) {
                return hVarA;
            }
            h hVarA2 = b.f7976g.a();
            l.c(hVarA2);
            return hVarA2;
        }

        private final h e() {
            g gVarA;
            c cVarA;
            d dVarB;
            if (j() && (dVarB = d.f7985f.b()) != null) {
                return dVarB;
            }
            if (i() && (cVarA = c.f7982f.a()) != null) {
                return cVarA;
            }
            if (k() && (gVarA = g.f8000f.a()) != null) {
                return gVarA;
            }
            f fVarA = f.f7998e.a();
            if (fVarA != null) {
                return fVarA;
            }
            h hVarA = e.f7988i.a();
            return hVarA != null ? hVarA : new h();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final h f() {
            return h() ? d() : e();
        }

        private final boolean i() {
            Provider provider = Security.getProviders()[0];
            l.d(provider, "Security.getProviders()[0]");
            return l.a("BC", provider.getName());
        }

        private final boolean j() {
            Provider provider = Security.getProviders()[0];
            l.d(provider, "Security.getProviders()[0]");
            return l.a("Conscrypt", provider.getName());
        }

        private final boolean k() {
            Provider provider = Security.getProviders()[0];
            l.d(provider, "Security.getProviders()[0]");
            return l.a("OpenJSSE", provider.getName());
        }

        public final List<String> b(List<? extends y> protocols) {
            l.e(protocols, "protocols");
            ArrayList arrayList = new ArrayList();
            for (Object obj : protocols) {
                if (((y) obj) != y.HTTP_1_0) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList(q.p(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((y) it.next()).toString());
            }
            return arrayList2;
        }

        public final byte[] c(List<? extends y> protocols) {
            l.e(protocols, "protocols");
            p6.e eVar = new p6.e();
            for (String str : b(protocols)) {
                eVar.M(str.length());
                eVar.H(str);
            }
            return eVar.a0();
        }

        public final h g() {
            return h.f8002a;
        }

        public final boolean h() {
            return l.a("Dalvik", System.getProperty("java.vm.name"));
        }
    }

    static {
        a aVar = new a(null);
        f8004c = aVar;
        f8002a = aVar.f();
        f8003b = Logger.getLogger(x.class.getName());
    }

    public static /* synthetic */ void k(h hVar, String str, int i7, Throwable th, int i8, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
        }
        if ((i8 & 2) != 0) {
            i7 = 4;
        }
        if ((i8 & 4) != 0) {
            th = null;
        }
        hVar.j(str, i7, th);
    }

    public void b(SSLSocket sslSocket) {
        l.e(sslSocket, "sslSocket");
    }

    public o6.c c(X509TrustManager trustManager) {
        l.e(trustManager, "trustManager");
        return new o6.a(d(trustManager));
    }

    public o6.e d(X509TrustManager trustManager) {
        l.e(trustManager, "trustManager");
        X509Certificate[] acceptedIssuers = trustManager.getAcceptedIssuers();
        l.d(acceptedIssuers, "trustManager.acceptedIssuers");
        return new o6.b((X509Certificate[]) Arrays.copyOf(acceptedIssuers, acceptedIssuers.length));
    }

    public void e(SSLSocket sslSocket, String str, List<y> protocols) {
        l.e(sslSocket, "sslSocket");
        l.e(protocols, "protocols");
    }

    public void f(Socket socket, InetSocketAddress address, int i7) throws IOException {
        l.e(socket, "socket");
        l.e(address, "address");
        socket.connect(address, i7);
    }

    public String g(SSLSocket sslSocket) {
        l.e(sslSocket, "sslSocket");
        return null;
    }

    public Object h(String closer) {
        l.e(closer, "closer");
        if (f8003b.isLoggable(Level.FINE)) {
            return new Throwable(closer);
        }
        return null;
    }

    public boolean i(String hostname) {
        l.e(hostname, "hostname");
        return true;
    }

    public void j(String message, int i7, Throwable th) {
        l.e(message, "message");
        f8003b.log(i7 == 5 ? Level.WARNING : Level.INFO, message, th);
    }

    public void l(String message, Object obj) {
        l.e(message, "message");
        if (obj == null) {
            message = message + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        j(message, 5, (Throwable) obj);
    }

    public SSLContext m() throws NoSuchAlgorithmException {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        l.d(sSLContext, "SSLContext.getInstance(\"TLS\")");
        return sSLContext;
    }

    public SSLSocketFactory n(X509TrustManager trustManager) {
        l.e(trustManager, "trustManager");
        try {
            SSLContext sSLContextM = m();
            sSLContextM.init(null, new TrustManager[]{trustManager}, null);
            SSLSocketFactory socketFactory = sSLContextM.getSocketFactory();
            l.d(socketFactory, "newSSLContext().apply {\n…ll)\n      }.socketFactory");
            return socketFactory;
        } catch (GeneralSecurityException e7) {
            throw new AssertionError("No System TLS: " + e7, e7);
        }
    }

    public X509TrustManager o() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        factory.init((KeyStore) null);
        l.d(factory, "factory");
        TrustManager[] trustManagers = factory.getTrustManagers();
        l.c(trustManagers);
        if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
            TrustManager trustManager = trustManagers[0];
            Objects.requireNonNull(trustManager, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
            return (X509TrustManager) trustManager;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected default trust managers: ");
        String string = Arrays.toString(trustManagers);
        l.d(string, "java.util.Arrays.toString(this)");
        sb.append(string);
        throw new IllegalStateException(sb.toString().toString());
    }

    public String toString() {
        String simpleName = getClass().getSimpleName();
        l.d(simpleName, "javaClass.simpleName");
        return simpleName;
    }
}
