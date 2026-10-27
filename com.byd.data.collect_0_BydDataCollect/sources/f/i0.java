package f;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Route.java */
/* JADX INFO: loaded from: classes.dex */
public final class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final e f5111a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Proxy f5112b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final InetSocketAddress f5113c;

    public i0(e eVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        Objects.requireNonNull(eVar, "address == null");
        Objects.requireNonNull(proxy, "proxy == null");
        Objects.requireNonNull(inetSocketAddress, "inetSocketAddress == null");
        this.f5111a = eVar;
        this.f5112b = proxy;
        this.f5113c = inetSocketAddress;
    }

    public e a() {
        return this.f5111a;
    }

    public Proxy b() {
        return this.f5112b;
    }

    public boolean c() {
        return this.f5111a.f5053i != null && this.f5112b.type() == Proxy.Type.HTTP;
    }

    public InetSocketAddress d() {
        return this.f5113c;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof i0) {
            i0 i0Var = (i0) obj;
            if (i0Var.f5111a.equals(this.f5111a) && i0Var.f5112b.equals(this.f5112b) && i0Var.f5113c.equals(this.f5113c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.f5111a.hashCode()) * 31) + this.f5112b.hashCode()) * 31) + this.f5113c.hashCode();
    }

    public String toString() {
        return "Route{" + this.f5113c + "}";
    }
}
