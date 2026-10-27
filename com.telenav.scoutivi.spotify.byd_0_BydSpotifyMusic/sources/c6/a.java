package c6;

import c6.u;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: Address.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final u f3629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<y> f3630b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<l> f3631c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final q f3632d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final SocketFactory f3633e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final SSLSocketFactory f3634f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final HostnameVerifier f3635g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final g f3636h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final b f3637i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Proxy f3638j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final ProxySelector f3639k;

    public a(String uriHost, int i7, q dns, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, g gVar, b proxyAuthenticator, Proxy proxy, List<? extends y> protocols, List<l> connectionSpecs, ProxySelector proxySelector) {
        kotlin.jvm.internal.l.e(uriHost, "uriHost");
        kotlin.jvm.internal.l.e(dns, "dns");
        kotlin.jvm.internal.l.e(socketFactory, "socketFactory");
        kotlin.jvm.internal.l.e(proxyAuthenticator, "proxyAuthenticator");
        kotlin.jvm.internal.l.e(protocols, "protocols");
        kotlin.jvm.internal.l.e(connectionSpecs, "connectionSpecs");
        kotlin.jvm.internal.l.e(proxySelector, "proxySelector");
        this.f3632d = dns;
        this.f3633e = socketFactory;
        this.f3634f = sSLSocketFactory;
        this.f3635g = hostnameVerifier;
        this.f3636h = gVar;
        this.f3637i = proxyAuthenticator;
        this.f3638j = proxy;
        this.f3639k = proxySelector;
        this.f3629a = new u.a().o(sSLSocketFactory != null ? "https" : "http").e(uriHost).k(i7).a();
        this.f3630b = d6.b.M(protocols);
        this.f3631c = d6.b.M(connectionSpecs);
    }

    public final g a() {
        return this.f3636h;
    }

    public final List<l> b() {
        return this.f3631c;
    }

    public final q c() {
        return this.f3632d;
    }

    public final boolean d(a that) {
        kotlin.jvm.internal.l.e(that, "that");
        return kotlin.jvm.internal.l.a(this.f3632d, that.f3632d) && kotlin.jvm.internal.l.a(this.f3637i, that.f3637i) && kotlin.jvm.internal.l.a(this.f3630b, that.f3630b) && kotlin.jvm.internal.l.a(this.f3631c, that.f3631c) && kotlin.jvm.internal.l.a(this.f3639k, that.f3639k) && kotlin.jvm.internal.l.a(this.f3638j, that.f3638j) && kotlin.jvm.internal.l.a(this.f3634f, that.f3634f) && kotlin.jvm.internal.l.a(this.f3635g, that.f3635g) && kotlin.jvm.internal.l.a(this.f3636h, that.f3636h) && this.f3629a.l() == that.f3629a.l();
    }

    public final HostnameVerifier e() {
        return this.f3635g;
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (kotlin.jvm.internal.l.a(this.f3629a, aVar.f3629a) && d(aVar)) {
                return true;
            }
        }
        return false;
    }

    public final List<y> f() {
        return this.f3630b;
    }

    public final Proxy g() {
        return this.f3638j;
    }

    public final b h() {
        return this.f3637i;
    }

    public int hashCode() {
        return ((((((((((((((((((527 + this.f3629a.hashCode()) * 31) + this.f3632d.hashCode()) * 31) + this.f3637i.hashCode()) * 31) + this.f3630b.hashCode()) * 31) + this.f3631c.hashCode()) * 31) + this.f3639k.hashCode()) * 31) + Objects.hashCode(this.f3638j)) * 31) + Objects.hashCode(this.f3634f)) * 31) + Objects.hashCode(this.f3635g)) * 31) + Objects.hashCode(this.f3636h);
    }

    public final ProxySelector i() {
        return this.f3639k;
    }

    public final SocketFactory j() {
        return this.f3633e;
    }

    public final SSLSocketFactory k() {
        return this.f3634f;
    }

    public final u l() {
        return this.f3629a;
    }

    public String toString() {
        StringBuilder sb;
        Object obj;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Address{");
        sb2.append(this.f3629a.h());
        sb2.append(':');
        sb2.append(this.f3629a.l());
        sb2.append(", ");
        if (this.f3638j != null) {
            sb = new StringBuilder();
            sb.append("proxy=");
            obj = this.f3638j;
        } else {
            sb = new StringBuilder();
            sb.append("proxySelector=");
            obj = this.f3639k;
        }
        sb.append(obj);
        sb2.append(sb.toString());
        sb2.append("}");
        return sb2.toString();
    }
}
