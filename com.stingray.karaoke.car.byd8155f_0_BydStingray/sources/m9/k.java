package m9;

import java.util.List;
import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;

/* JADX INFO: loaded from: classes.dex */
public final class k implements n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f19349a = new j();

    @Override // m9.n
    public final boolean a(SSLSocket sSLSocket) {
        return Conscrypt.isConscrypt(sSLSocket);
    }

    @Override // m9.n
    public final String b(SSLSocket sSLSocket) {
        if (a(sSLSocket)) {
            return Conscrypt.getApplicationProtocol(sSLSocket);
        }
        return null;
    }

    @Override // m9.n
    public final boolean c() {
        boolean z6 = l9.h.d;
        return l9.h.d;
    }

    @Override // m9.n
    public final void d(SSLSocket sSLSocket, String str, List list) {
        A7.m.f(list, "protocols");
        if (a(sSLSocket)) {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
            l9.o oVar = l9.o.f18874a;
            Conscrypt.setApplicationProtocols(sSLSocket, (String[]) l9.n.G(list).toArray(new String[0]));
        }
    }
}
