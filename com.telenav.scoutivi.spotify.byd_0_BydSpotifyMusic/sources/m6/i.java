package m6;

import c6.y;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import m6.j;
import org.conscrypt.Conscrypt;

/* JADX INFO: compiled from: ConscryptSocketAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class i implements k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f8429b = new b(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final j.a f8428a = new a();

    /* JADX INFO: compiled from: ConscryptSocketAdapter.kt */
    public static final class a implements j.a {
        a() {
        }

        @Override // m6.j.a
        public boolean a(SSLSocket sslSocket) {
            kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
            return l6.d.f7985f.c() && Conscrypt.isConscrypt(sslSocket);
        }

        @Override // m6.j.a
        public k b(SSLSocket sslSocket) {
            kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
            return new i();
        }
    }

    /* JADX INFO: compiled from: ConscryptSocketAdapter.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final j.a a() {
            return i.f8428a;
        }
    }

    @Override // m6.k
    public boolean a(SSLSocket sslSocket) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        return Conscrypt.isConscrypt(sslSocket);
    }

    @Override // m6.k
    public String b(SSLSocket sslSocket) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        if (a(sslSocket)) {
            return Conscrypt.getApplicationProtocol(sslSocket);
        }
        return null;
    }

    @Override // m6.k
    public boolean c() {
        return l6.d.f7985f.c();
    }

    @Override // m6.k
    public void d(SSLSocket sslSocket, String str, List<? extends y> protocols) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        kotlin.jvm.internal.l.e(protocols, "protocols");
        if (a(sslSocket)) {
            Conscrypt.setUseSessionTickets(sslSocket, true);
            Object[] array = l6.h.f8004c.b(protocols).toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            Conscrypt.setApplicationProtocols(sslSocket, (String[]) array);
        }
    }
}
