package c9;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* JADX INFO: loaded from: classes.dex */
public final class J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1040a f12528a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Proxy f12529b;
    public final InetSocketAddress c;

    public J(C1040a c1040a, Proxy proxy, InetSocketAddress inetSocketAddress) {
        A7.m.f(inetSocketAddress, "socketAddress");
        this.f12528a = c1040a;
        this.f12529b = proxy;
        this.c = inetSocketAddress;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof J) {
            J j3 = (J) obj;
            if (A7.m.a(j3.f12528a, this.f12528a) && A7.m.a(j3.f12529b, this.f12529b) && A7.m.a(j3.c, this.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.f12529b.hashCode() + ((this.f12528a.hashCode() + 527) * 31)) * 31);
    }

    public final String toString() {
        return "Route{" + this.c + '}';
    }
}
