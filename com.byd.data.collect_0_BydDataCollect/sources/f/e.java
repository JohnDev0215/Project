package f;

import f.x;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: Address.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a */
    final x f5045a;

    /* JADX INFO: renamed from: b */
    final s f5046b;

    /* JADX INFO: renamed from: c */
    final SocketFactory f5047c;

    /* JADX INFO: renamed from: d */
    final f f5048d;

    /* JADX INFO: renamed from: e */
    final List<c0> f5049e;

    /* JADX INFO: renamed from: f */
    final List<o> f5050f;

    /* JADX INFO: renamed from: g */
    final ProxySelector f5051g;

    /* JADX INFO: renamed from: h */
    @Nullable
    final Proxy f5052h;

    /* JADX INFO: renamed from: i */
    @Nullable
    final SSLSocketFactory f5053i;

    @Nullable
    final HostnameVerifier j;

    @Nullable
    final k k;

    public e(String str, int i2, s sVar, SocketFactory socketFactory, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier, @Nullable k kVar, f fVar, @Nullable Proxy proxy, List<c0> list, List<o> list2, ProxySelector proxySelector) {
        this.f5045a = new x.a().s(sSLSocketFactory != null ? "https" : "http").g(str).n(i2).c();
        Objects.requireNonNull(sVar, "dns == null");
        this.f5046b = sVar;
        Objects.requireNonNull(socketFactory, "socketFactory == null");
        this.f5047c = socketFactory;
        Objects.requireNonNull(fVar, "proxyAuthenticator == null");
        this.f5048d = fVar;
        Objects.requireNonNull(list, "protocols == null");
        this.f5049e = f.k0.e.s(list);
        Objects.requireNonNull(list2, "connectionSpecs == null");
        this.f5050f = f.k0.e.s(list2);
        Objects.requireNonNull(proxySelector, "proxySelector == null");
        this.f5051g = proxySelector;
        this.f5052h = proxy;
        this.f5053i = sSLSocketFactory;
        this.j = hostnameVerifier;
        this.k = kVar;
    }

    @Nullable
    public k a() {
        return this.k;
    }

    public List<o> b() {
        return this.f5050f;
    }

    public s c() {
        return this.f5046b;
    }

    boolean d(e eVar) {
        return this.f5046b.equals(eVar.f5046b) && this.f5048d.equals(eVar.f5048d) && this.f5049e.equals(eVar.f5049e) && this.f5050f.equals(eVar.f5050f) && this.f5051g.equals(eVar.f5051g) && Objects.equals(this.f5052h, eVar.f5052h) && Objects.equals(this.f5053i, eVar.f5053i) && Objects.equals(this.j, eVar.j) && Objects.equals(this.k, eVar.k) && l().y() == eVar.l().y();
    }

    @Nullable
    public HostnameVerifier e() {
        return this.j;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof e) {
            e eVar = (e) obj;
            if (this.f5045a.equals(eVar.f5045a) && d(eVar)) {
                return true;
            }
        }
        return false;
    }

    public List<c0> f() {
        return this.f5049e;
    }

    @Nullable
    public Proxy g() {
        return this.f5052h;
    }

    public f h() {
        return this.f5048d;
    }

    public int hashCode() {
        return ((((((((((((((((((527 + this.f5045a.hashCode()) * 31) + this.f5046b.hashCode()) * 31) + this.f5048d.hashCode()) * 31) + this.f5049e.hashCode()) * 31) + this.f5050f.hashCode()) * 31) + this.f5051g.hashCode()) * 31) + Objects.hashCode(this.f5052h)) * 31) + Objects.hashCode(this.f5053i)) * 31) + Objects.hashCode(this.j)) * 31) + Objects.hashCode(this.k);
    }

    public ProxySelector i() {
        return this.f5051g;
    }

    public SocketFactory j() {
        return this.f5047c;
    }

    @Nullable
    public SSLSocketFactory k() {
        return this.f5053i;
    }

    public x l() {
        return this.f5045a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{");
        sb.append(this.f5045a.m());
        sb.append(":");
        sb.append(this.f5045a.y());
        if (this.f5052h != null) {
            sb.append(", proxy=");
            sb.append(this.f5052h);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.f5051g);
        }
        sb.append("}");
        return sb.toString();
    }
}
