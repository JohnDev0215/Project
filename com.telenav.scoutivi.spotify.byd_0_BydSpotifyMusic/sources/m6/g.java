package m6;

import c6.y;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import m6.j;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;

/* JADX INFO: compiled from: BouncyCastleSocketAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g implements k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f8423b = new b(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final j.a f8422a = new a();

    /* JADX INFO: compiled from: BouncyCastleSocketAdapter.kt */
    public static final class a implements j.a {
        a() {
        }

        @Override // m6.j.a
        public boolean a(SSLSocket sslSocket) {
            kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
            return l6.c.f7982f.b() && (sslSocket instanceof BCSSLSocket);
        }

        @Override // m6.j.a
        public k b(SSLSocket sslSocket) {
            kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
            return new g();
        }
    }

    /* JADX INFO: compiled from: BouncyCastleSocketAdapter.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final j.a a() {
            return g.f8422a;
        }
    }

    @Override // m6.k
    public boolean a(SSLSocket sslSocket) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        return sslSocket instanceof BCSSLSocket;
    }

    @Override // m6.k
    public String b(SSLSocket sslSocket) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        String applicationProtocol = ((BCSSLSocket) sslSocket).getApplicationProtocol();
        if (applicationProtocol == null || (applicationProtocol.hashCode() == 0 && applicationProtocol.equals(""))) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // m6.k
    public boolean c() {
        return l6.c.f7982f.b();
    }

    @Override // m6.k
    public void d(SSLSocket sslSocket, String str, List<? extends y> protocols) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        kotlin.jvm.internal.l.e(protocols, "protocols");
        if (a(sslSocket)) {
            BCSSLSocket bCSSLSocket = (BCSSLSocket) sslSocket;
            BCSSLParameters sslParameters = bCSSLSocket.getParameters();
            kotlin.jvm.internal.l.d(sslParameters, "sslParameters");
            Object[] array = l6.h.f8004c.b(protocols).toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            sslParameters.setApplicationProtocols((String[]) array);
            bCSSLSocket.setParameters(sslParameters);
        }
    }
}
