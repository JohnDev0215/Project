package m9;

import java.util.List;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;

/* JADX INFO: loaded from: classes.dex */
public final class h implements n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f19346a = new g();

    @Override // m9.n
    public final boolean a(SSLSocket sSLSocket) {
        return false;
    }

    @Override // m9.n
    public final String b(SSLSocket sSLSocket) {
        String applicationProtocol = ((BCSSLSocket) sSLSocket).getApplicationProtocol();
        if (applicationProtocol == null ? true : A7.m.a(applicationProtocol, "")) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // m9.n
    public final boolean c() {
        boolean z6 = l9.e.d;
        return l9.e.d;
    }

    @Override // m9.n
    public final void d(SSLSocket sSLSocket, String str, List list) {
        A7.m.f(list, "protocols");
        if (a(sSLSocket)) {
            BCSSLSocket bCSSLSocket = (BCSSLSocket) sSLSocket;
            BCSSLParameters parameters = bCSSLSocket.getParameters();
            l9.o oVar = l9.o.f18874a;
            parameters.setApplicationProtocols((String[]) l9.n.G(list).toArray(new String[0]));
            bCSSLSocket.setParameters(parameters);
        }
    }
}
