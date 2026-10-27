package e;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl;
import okhttp3.Protocol;

/* JADX INFO: compiled from: Address.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HttpUrl f3289a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f3290b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final SocketFactory f3291c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b f3292d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<Protocol> f3293e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List<k> f3294f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ProxySelector f3295g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @Nullable
    public final Proxy f3296h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @Nullable
    public final SSLSocketFactory f3297i;

    @Nullable
    public final HostnameVerifier j;

    @Nullable
    public final g k;

    public a(String str, int i2, o oVar, SocketFactory socketFactory, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier, @Nullable g gVar, b bVar, @Nullable Proxy proxy, List<Protocol> list, List<k> list2, ProxySelector proxySelector) {
        this.f3289a = new HttpUrl.Builder().v(sSLSocketFactory != null ? "https" : "http").i(str).q(i2).c();
        Objects.requireNonNull(oVar, "dns == null");
        this.f3290b = oVar;
        Objects.requireNonNull(socketFactory, "socketFactory == null");
        this.f3291c = socketFactory;
        Objects.requireNonNull(bVar, "proxyAuthenticator == null");
        this.f3292d = bVar;
        Objects.requireNonNull(list, "protocols == null");
        this.f3293e = e.d0.c.n(list);
        Objects.requireNonNull(list2, "connectionSpecs == null");
        this.f3294f = e.d0.c.n(list2);
        Objects.requireNonNull(proxySelector, "proxySelector == null");
        this.f3295g = proxySelector;
        this.f3296h = proxy;
        this.f3297i = sSLSocketFactory;
        this.j = hostnameVerifier;
        this.k = gVar;
    }

    @Nullable
    public g a() {
        return this.k;
    }

    public List<k> b() {
        return this.f3294f;
    }

    public o c() {
        return this.f3290b;
    }

    public boolean d(a aVar) {
        return this.f3290b.equals(aVar.f3290b) && this.f3292d.equals(aVar.f3292d) && this.f3293e.equals(aVar.f3293e) && this.f3294f.equals(aVar.f3294f) && this.f3295g.equals(aVar.f3295g) && e.d0.c.k(this.f3296h, aVar.f3296h) && e.d0.c.k(this.f3297i, aVar.f3297i) && e.d0.c.k(this.j, aVar.j) && e.d0.c.k(this.k, aVar.k) && l().y() == aVar.l().y();
    }

    @Nullable
    public HostnameVerifier e() {
        return this.j;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (this.f3289a.equals(aVar.f3289a) && d(aVar)) {
                return true;
            }
        }
        return false;
    }

    public List<Protocol> f() {
        return this.f3293e;
    }

    @Nullable
    public Proxy g() {
        return this.f3296h;
    }

    public b h() {
        return this.f3292d;
    }

    public int hashCode() {
        int iHashCode = (((((((((((527 + this.f3289a.hashCode()) * 31) + this.f3290b.hashCode()) * 31) + this.f3292d.hashCode()) * 31) + this.f3293e.hashCode()) * 31) + this.f3294f.hashCode()) * 31) + this.f3295g.hashCode()) * 31;
        Proxy proxy = this.f3296h;
        int iHashCode2 = (iHashCode + (proxy != null ? proxy.hashCode() : 0)) * 31;
        SSLSocketFactory sSLSocketFactory = this.f3297i;
        int iHashCode3 = (iHashCode2 + (sSLSocketFactory != null ? sSLSocketFactory.hashCode() : 0)) * 31;
        HostnameVerifier hostnameVerifier = this.j;
        int iHashCode4 = (iHashCode3 + (hostnameVerifier != null ? hostnameVerifier.hashCode() : 0)) * 31;
        g gVar = this.k;
        return iHashCode4 + (gVar != null ? gVar.hashCode() : 0);
    }

    public ProxySelector i() {
        return this.f3295g;
    }

    public SocketFactory j() {
        return this.f3291c;
    }

    @Nullable
    public SSLSocketFactory k() {
        return this.f3297i;
    }

    public HttpUrl l() {
        return this.f3289a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{");
        sb.append(this.f3289a.l());
        sb.append(":");
        sb.append(this.f3289a.y());
        if (this.f3296h != null) {
            sb.append(", proxy=");
            sb.append(this.f3296h);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.f3295g);
        }
        sb.append("}");
        return sb.toString();
    }
}
