package e;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Route.java */
/* JADX INFO: loaded from: classes.dex */
public final class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f3314a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Proxy f3315b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final InetSocketAddress f3316c;

    public c0(a aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        Objects.requireNonNull(aVar, "address == null");
        Objects.requireNonNull(proxy, "proxy == null");
        Objects.requireNonNull(inetSocketAddress, "inetSocketAddress == null");
        this.f3314a = aVar;
        this.f3315b = proxy;
        this.f3316c = inetSocketAddress;
    }

    public a a() {
        return this.f3314a;
    }

    public Proxy b() {
        return this.f3315b;
    }

    public boolean c() {
        return this.f3314a.f3297i != null && this.f3315b.type() == Proxy.Type.HTTP;
    }

    public InetSocketAddress d() {
        return this.f3316c;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof c0) {
            c0 c0Var = (c0) obj;
            if (c0Var.f3314a.equals(this.f3314a) && c0Var.f3315b.equals(this.f3315b) && c0Var.f3316c.equals(this.f3316c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.f3314a.hashCode()) * 31) + this.f3315b.hashCode()) * 31) + this.f3316c.hashCode();
    }

    public String toString() {
        return "Route{" + this.f3316c + "}";
    }
}
