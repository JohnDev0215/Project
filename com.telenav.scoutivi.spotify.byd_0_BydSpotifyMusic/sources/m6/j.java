package m6;

import c6.y;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: DeferredSocketAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class j implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private k f8430a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f8431b;

    /* JADX INFO: compiled from: DeferredSocketAdapter.kt */
    public interface a {
        boolean a(SSLSocket sSLSocket);

        k b(SSLSocket sSLSocket);
    }

    public j(a socketAdapterFactory) {
        kotlin.jvm.internal.l.e(socketAdapterFactory, "socketAdapterFactory");
        this.f8431b = socketAdapterFactory;
    }

    private final synchronized k e(SSLSocket sSLSocket) {
        if (this.f8430a == null && this.f8431b.a(sSLSocket)) {
            this.f8430a = this.f8431b.b(sSLSocket);
        }
        return this.f8430a;
    }

    @Override // m6.k
    public boolean a(SSLSocket sslSocket) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        return this.f8431b.a(sslSocket);
    }

    @Override // m6.k
    public String b(SSLSocket sslSocket) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        k kVarE = e(sslSocket);
        if (kVarE != null) {
            return kVarE.b(sslSocket);
        }
        return null;
    }

    @Override // m6.k
    public boolean c() {
        return true;
    }

    @Override // m6.k
    public void d(SSLSocket sslSocket, String str, List<? extends y> protocols) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        kotlin.jvm.internal.l.e(protocols, "protocols");
        k kVarE = e(sslSocket);
        if (kVarE != null) {
            kVarE.d(sslSocket, str, protocols);
        }
    }
}
