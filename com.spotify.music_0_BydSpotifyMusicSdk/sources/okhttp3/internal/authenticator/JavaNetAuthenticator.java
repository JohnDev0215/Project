package okhttp3.internal.authenticator;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import okhttp3.Authenticator;
import okhttp3.Dns;
import okhttp3.HttpUrl;
import p.ou;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class JavaNetAuthenticator implements Authenticator {
    public final Dns b;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Proxy.Type.values().length];
            try {
                iArr[Proxy.Type.DIRECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            a = iArr;
        }
    }

    public JavaNetAuthenticator() {
        this(0);
    }

    public static InetAddress a(Proxy proxy, HttpUrl httpUrl, Dns dns) {
        Proxy.Type type = proxy.type();
        if (type != null && WhenMappings.a[type.ordinal()] == 1) {
            return (InetAddress) ou.c0(dns.a(httpUrl.d));
        }
        SocketAddress socketAddressAddress = proxy.address();
        v03.g(socketAddressAddress, "null cannot be cast to non-null type java.net.InetSocketAddress");
        InetAddress address = ((InetSocketAddress) socketAddressAddress).getAddress();
        v03.i(address, "address() as InetSocketAddress).address");
        return address;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007f  */
    @Override // okhttp3.Authenticator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final okhttp3.Request s(okhttp3.Route r22, okhttp3.Response r23) {
        /*
            Method dump skipped, instruction units count: 424
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.authenticator.JavaNetAuthenticator.s(okhttp3.Route, okhttp3.Response):okhttp3.Request");
    }

    public JavaNetAuthenticator(int i) {
        Dns dns = Dns.a;
        v03.j(dns, "defaultDns");
        this.b = dns;
    }
}
