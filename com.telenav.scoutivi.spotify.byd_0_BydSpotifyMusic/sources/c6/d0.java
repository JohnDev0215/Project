package c6;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* JADX INFO: compiled from: Route.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f3703a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Proxy f3704b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final InetSocketAddress f3705c;

    public d0(a address, Proxy proxy, InetSocketAddress socketAddress) {
        kotlin.jvm.internal.l.e(address, "address");
        kotlin.jvm.internal.l.e(proxy, "proxy");
        kotlin.jvm.internal.l.e(socketAddress, "socketAddress");
        this.f3703a = address;
        this.f3704b = proxy;
        this.f3705c = socketAddress;
    }

    public final a a() {
        return this.f3703a;
    }

    public final Proxy b() {
        return this.f3704b;
    }

    public final boolean c() {
        return this.f3703a.k() != null && this.f3704b.type() == Proxy.Type.HTTP;
    }

    public final InetSocketAddress d() {
        return this.f3705c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof d0) {
            d0 d0Var = (d0) obj;
            if (kotlin.jvm.internal.l.a(d0Var.f3703a, this.f3703a) && kotlin.jvm.internal.l.a(d0Var.f3704b, this.f3704b) && kotlin.jvm.internal.l.a(d0Var.f3705c, this.f3705c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.f3703a.hashCode()) * 31) + this.f3704b.hashCode()) * 31) + this.f3705c.hashCode();
    }

    public String toString() {
        return "Route{" + this.f3705c + '}';
    }
}
