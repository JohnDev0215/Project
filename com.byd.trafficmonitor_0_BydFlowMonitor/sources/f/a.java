package f;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl;
import okhttp3.Protocol;

/* JADX INFO: compiled from: Address.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HttpUrl f4997a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f4998b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final SocketFactory f4999c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b f5000d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<Protocol> f5001e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List<k> f5002f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ProxySelector f5003g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Proxy f5004h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final SSLSocketFactory f5005i;
    public final HostnameVerifier j;
    public final g k;

    public a(String str, int i2, o oVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, g gVar, b bVar, Proxy proxy, List<Protocol> list, List<k> list2, ProxySelector proxySelector) {
        this.f4997a = new HttpUrl.Builder().t(sSLSocketFactory != null ? "https" : "http").g(str).o(i2).a();
        Objects.requireNonNull(oVar, "dns == null");
        this.f4998b = oVar;
        Objects.requireNonNull(socketFactory, "socketFactory == null");
        this.f4999c = socketFactory;
        Objects.requireNonNull(bVar, "proxyAuthenticator == null");
        this.f5000d = bVar;
        Objects.requireNonNull(list, "protocols == null");
        this.f5001e = f.b0.c.n(list);
        Objects.requireNonNull(list2, "connectionSpecs == null");
        this.f5002f = f.b0.c.n(list2);
        Objects.requireNonNull(proxySelector, "proxySelector == null");
        this.f5003g = proxySelector;
        this.f5004h = proxy;
        this.f5005i = sSLSocketFactory;
        this.j = hostnameVerifier;
        this.k = gVar;
    }

    public g a() {
        return this.k;
    }

    public List<k> b() {
        return this.f5002f;
    }

    public o c() {
        return this.f4998b;
    }

    public HostnameVerifier d() {
        return this.j;
    }

    public List<Protocol> e() {
        return this.f5001e;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f4997a.equals(aVar.f4997a) && this.f4998b.equals(aVar.f4998b) && this.f5000d.equals(aVar.f5000d) && this.f5001e.equals(aVar.f5001e) && this.f5002f.equals(aVar.f5002f) && this.f5003g.equals(aVar.f5003g) && f.b0.c.k(this.f5004h, aVar.f5004h) && f.b0.c.k(this.f5005i, aVar.f5005i) && f.b0.c.k(this.j, aVar.j) && f.b0.c.k(this.k, aVar.k);
    }

    public Proxy f() {
        return this.f5004h;
    }

    public b g() {
        return this.f5000d;
    }

    public ProxySelector h() {
        return this.f5003g;
    }

    public int hashCode() {
        int iHashCode = (((((((((((527 + this.f4997a.hashCode()) * 31) + this.f4998b.hashCode()) * 31) + this.f5000d.hashCode()) * 31) + this.f5001e.hashCode()) * 31) + this.f5002f.hashCode()) * 31) + this.f5003g.hashCode()) * 31;
        Proxy proxy = this.f5004h;
        int iHashCode2 = (iHashCode + (proxy != null ? proxy.hashCode() : 0)) * 31;
        SSLSocketFactory sSLSocketFactory = this.f5005i;
        int iHashCode3 = (iHashCode2 + (sSLSocketFactory != null ? sSLSocketFactory.hashCode() : 0)) * 31;
        HostnameVerifier hostnameVerifier = this.j;
        int iHashCode4 = (iHashCode3 + (hostnameVerifier != null ? hostnameVerifier.hashCode() : 0)) * 31;
        g gVar = this.k;
        return iHashCode4 + (gVar != null ? gVar.hashCode() : 0);
    }

    public SocketFactory i() {
        return this.f4999c;
    }

    public SSLSocketFactory j() {
        return this.f5005i;
    }

    public HttpUrl k() {
        return this.f4997a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{");
        sb.append(this.f4997a.l());
        sb.append(":");
        sb.append(this.f4997a.x());
        if (this.f5004h != null) {
            sb.append(", proxy=");
            sb.append(this.f5004h);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.f5003g);
        }
        sb.append("}");
        return sb.toString();
    }
}
