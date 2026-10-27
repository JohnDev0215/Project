package c9;

import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: renamed from: c9.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1040a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1041b f12535a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SocketFactory f12536b;
    public final SSLSocketFactory c;
    public final HostnameVerifier d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C1049j f12537e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C1041b f12538f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ProxySelector f12539g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final w f12540h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f12541i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final List f12542j;

    public C1040a(String str, int i7, C1041b c1041b, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, C1049j c1049j, C1041b c1041b2, List list, List list2, ProxySelector proxySelector) {
        A7.m.f(str, "uriHost");
        A7.m.f(c1041b, "dns");
        A7.m.f(socketFactory, "socketFactory");
        A7.m.f(c1041b2, "proxyAuthenticator");
        A7.m.f(list, "protocols");
        A7.m.f(list2, "connectionSpecs");
        A7.m.f(proxySelector, "proxySelector");
        this.f12535a = c1041b;
        this.f12536b = socketFactory;
        this.c = sSLSocketFactory;
        this.d = hostnameVerifier;
        this.f12537e = c1049j;
        this.f12538f = c1041b2;
        this.f12539g = proxySelector;
        v vVar = new v();
        String str2 = sSLSocketFactory != null ? "https" : "http";
        if (str2.equalsIgnoreCase("http")) {
            vVar.d = "http";
        } else {
            if (!str2.equalsIgnoreCase("https")) {
                throw new IllegalArgumentException("unexpected scheme: ".concat(str2));
            }
            vVar.d = "https";
        }
        String strM = com.bumptech.glide.d.M(C1041b.e(str, 0, 0, false, 7));
        if (strM == null) {
            throw new IllegalArgumentException("unexpected host: ".concat(str));
        }
        vVar.f12615g = strM;
        if (1 > i7 || i7 >= 65536) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "unexpected port: ").toString());
        }
        vVar.f12612b = i7;
        this.f12540h = vVar.c();
        this.f12541i = d9.b.x(list);
        this.f12542j = d9.b.x(list2);
    }

    public final boolean a(C1040a c1040a) {
        A7.m.f(c1040a, "that");
        return A7.m.a(this.f12535a, c1040a.f12535a) && A7.m.a(this.f12538f, c1040a.f12538f) && A7.m.a(this.f12541i, c1040a.f12541i) && A7.m.a(this.f12542j, c1040a.f12542j) && A7.m.a(this.f12539g, c1040a.f12539g) && A7.m.a(null, null) && A7.m.a(this.c, c1040a.c) && A7.m.a(this.d, c1040a.d) && A7.m.a(this.f12537e, c1040a.f12537e) && this.f12540h.f12621e == c1040a.f12540h.f12621e;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1040a) {
            C1040a c1040a = (C1040a) obj;
            if (A7.m.a(this.f12540h, c1040a.f12540h) && a(c1040a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f12537e) + ((Objects.hashCode(this.d) + ((Objects.hashCode(this.c) + ((this.f12539g.hashCode() + ((this.f12542j.hashCode() + ((this.f12541i.hashCode() + ((this.f12538f.hashCode() + ((this.f12535a.hashCode() + M5.b.c(this.f12540h.f12624h, 527, 31)) * 31)) * 31)) * 31)) * 31)) * 961)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        w wVar = this.f12540h;
        sb.append(wVar.d);
        sb.append(':');
        sb.append(wVar.f12621e);
        sb.append(", ");
        sb.append("proxySelector=" + this.f12539g);
        sb.append('}');
        return sb.toString();
    }
}
