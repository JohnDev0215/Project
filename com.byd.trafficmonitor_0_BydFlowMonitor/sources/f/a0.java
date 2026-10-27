package f;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Objects;

/* JADX INFO: compiled from: Route.java */
/* JADX INFO: loaded from: classes.dex */
public final class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f5006a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Proxy f5007b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final InetSocketAddress f5008c;

    public a0(a aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        Objects.requireNonNull(aVar, "address == null");
        Objects.requireNonNull(proxy, "proxy == null");
        Objects.requireNonNull(inetSocketAddress, "inetSocketAddress == null");
        this.f5006a = aVar;
        this.f5007b = proxy;
        this.f5008c = inetSocketAddress;
    }

    public a a() {
        return this.f5006a;
    }

    public Proxy b() {
        return this.f5007b;
    }

    public boolean c() {
        return this.f5006a.f5005i != null && this.f5007b.type() == Proxy.Type.HTTP;
    }

    public InetSocketAddress d() {
        return this.f5008c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a0)) {
            return false;
        }
        a0 a0Var = (a0) obj;
        return this.f5006a.equals(a0Var.f5006a) && this.f5007b.equals(a0Var.f5007b) && this.f5008c.equals(a0Var.f5008c);
    }

    public int hashCode() {
        return ((((527 + this.f5006a.hashCode()) * 31) + this.f5007b.hashCode()) * 31) + this.f5008c.hashCode();
    }

    public String toString() {
        return "Route{" + this.f5008c + "}";
    }
}
