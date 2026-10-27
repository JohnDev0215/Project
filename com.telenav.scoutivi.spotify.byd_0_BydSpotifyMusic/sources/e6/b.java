package e6;

import c6.b0;
import c6.d0;
import c6.h;
import c6.o;
import c6.q;
import c6.u;
import c6.z;
import h5.x;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.l;
import y5.p;

/* JADX INFO: compiled from: JavaNetAuthenticator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b implements c6.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final q f6313d;

    public b(q defaultDns) {
        l.e(defaultDns, "defaultDns");
        this.f6313d = defaultDns;
    }

    private final InetAddress b(Proxy proxy, u uVar, q qVar) {
        Proxy.Type type = proxy.type();
        if (type != null && a.f6312a[type.ordinal()] == 1) {
            return (InetAddress) x.w(qVar.a(uVar.h()));
        }
        SocketAddress socketAddressAddress = proxy.address();
        Objects.requireNonNull(socketAddressAddress, "null cannot be cast to non-null type java.net.InetSocketAddress");
        InetAddress address = ((InetSocketAddress) socketAddressAddress).getAddress();
        l.d(address, "(address() as InetSocketAddress).address");
        return address;
    }

    @Override // c6.b
    public z a(d0 d0Var, b0 response) {
        Proxy proxy;
        q qVarC;
        PasswordAuthentication passwordAuthenticationRequestPasswordAuthentication;
        c6.a aVarA;
        l.e(response, "response");
        List<h> listG = response.G();
        z zVarG0 = response.g0();
        u uVarI = zVarG0.i();
        boolean z6 = response.I() == 407;
        if (d0Var == null || (proxy = d0Var.b()) == null) {
            proxy = Proxy.NO_PROXY;
        }
        for (h hVar : listG) {
            if (p.o("Basic", hVar.c(), true)) {
                if (d0Var == null || (aVarA = d0Var.a()) == null || (qVarC = aVarA.c()) == null) {
                    qVarC = this.f6313d;
                }
                if (z6) {
                    SocketAddress socketAddressAddress = proxy.address();
                    Objects.requireNonNull(socketAddressAddress, "null cannot be cast to non-null type java.net.InetSocketAddress");
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
                    String hostName = inetSocketAddress.getHostName();
                    l.d(proxy, "proxy");
                    passwordAuthenticationRequestPasswordAuthentication = Authenticator.requestPasswordAuthentication(hostName, b(proxy, uVarI, qVarC), inetSocketAddress.getPort(), uVarI.p(), hVar.b(), hVar.c(), uVarI.r(), Authenticator.RequestorType.PROXY);
                } else {
                    String strH = uVarI.h();
                    l.d(proxy, "proxy");
                    passwordAuthenticationRequestPasswordAuthentication = Authenticator.requestPasswordAuthentication(strH, b(proxy, uVarI, qVarC), uVarI.l(), uVarI.p(), hVar.b(), hVar.c(), uVarI.r(), Authenticator.RequestorType.SERVER);
                }
                if (passwordAuthenticationRequestPasswordAuthentication != null) {
                    String str = z6 ? "Proxy-Authorization" : "Authorization";
                    String userName = passwordAuthenticationRequestPasswordAuthentication.getUserName();
                    l.d(userName, "auth.userName");
                    char[] password = passwordAuthenticationRequestPasswordAuthentication.getPassword();
                    l.d(password, "auth.password");
                    return zVarG0.h().c(str, o.a(userName, new String(password), hVar.a())).b();
                }
            }
        }
        return null;
    }

    public /* synthetic */ b(q qVar, int i7, g gVar) {
        this((i7 & 1) != 0 ? q.f3832a : qVar);
    }
}
