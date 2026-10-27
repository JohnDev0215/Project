package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Route {
    public final Address a;
    public final Proxy b;
    public final InetSocketAddress c;

    public Route(Address address, Proxy proxy, InetSocketAddress inetSocketAddress) {
        v03.j(address, "address");
        v03.j(inetSocketAddress, "socketAddress");
        this.a = address;
        this.b = proxy;
        this.c = inetSocketAddress;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Route) {
            Route route = (Route) obj;
            if (v03.b(route.a, this.a) && v03.b(route.b, this.b) && v03.b(route.c, this.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + ((this.a.hashCode() + 527) * 31)) * 31);
    }

    public final String toString() {
        return "Route{" + this.c + '}';
    }
}
