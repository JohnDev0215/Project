package okhttp3;

import com.spotify.connectivity.httpwebgate.WebgateHelper;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import p.hf2;
import p.ng2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Address {
    public final Dns a;
    public final SocketFactory b;
    public final SSLSocketFactory c;
    public final HostnameVerifier d;
    public final CertificatePinner e;
    public final Authenticator f;
    public final Proxy g;
    public final ProxySelector h;
    public final HttpUrl i;
    public final List j;
    public final List k;

    public Address(String str, int i, Dns dns, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, CertificatePinner certificatePinner, Authenticator authenticator, Proxy proxy, List list, List list2, ProxySelector proxySelector) {
        v03.j(str, "uriHost");
        v03.j(dns, "dns");
        v03.j(socketFactory, "socketFactory");
        v03.j(authenticator, "proxyAuthenticator");
        v03.j(list, "protocols");
        v03.j(list2, "connectionSpecs");
        v03.j(proxySelector, "proxySelector");
        this.a = dns;
        this.b = socketFactory;
        this.c = sSLSocketFactory;
        this.d = hostnameVerifier;
        this.e = certificatePinner;
        this.f = authenticator;
        this.g = proxy;
        this.h = proxySelector;
        HttpUrl.Builder builder = new HttpUrl.Builder();
        String str2 = sSLSocketFactory != null ? WebgateHelper.DEFAULT_WEBGATE_PROTOCOL : "http";
        if (ng2.b0(str2, "http", true)) {
            builder.a = "http";
        } else {
            if (!ng2.b0(str2, WebgateHelper.DEFAULT_WEBGATE_PROTOCOL, true)) {
                throw new IllegalArgumentException("unexpected scheme: ".concat(str2));
            }
            builder.a = WebgateHelper.DEFAULT_WEBGATE_PROTOCOL;
        }
        String strB = HostnamesKt.b(HttpUrl.Companion.e(HttpUrl.k, str, 0, 0, false, 7));
        if (strB == null) {
            throw new IllegalArgumentException("unexpected host: ".concat(str));
        }
        builder.d = strB;
        if (1 > i || i >= 65536) {
            throw new IllegalArgumentException(u4.e("unexpected port: ", i).toString());
        }
        builder.e = i;
        this.i = builder.a();
        this.j = Util.y(list);
        this.k = Util.y(list2);
    }

    public final boolean a(Address address) {
        v03.j(address, "that");
        return v03.b(this.a, address.a) && v03.b(this.f, address.f) && v03.b(this.j, address.j) && v03.b(this.k, address.k) && v03.b(this.h, address.h) && v03.b(this.g, address.g) && v03.b(this.c, address.c) && v03.b(this.d, address.d) && v03.b(this.e, address.e) && this.i.e == address.i.e;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Address) {
            Address address = (Address) obj;
            if (v03.b(this.i, address.i) && a(address)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.e) + ((Objects.hashCode(this.d) + ((Objects.hashCode(this.c) + ((Objects.hashCode(this.g) + ((this.h.hashCode() + ((this.k.hashCode() + ((this.j.hashCode() + ((this.f.hashCode() + ((this.a.hashCode() + hf2.b(this.i.i, 527, 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("Address{");
        HttpUrl httpUrl = this.i;
        sb.append(httpUrl.d);
        sb.append(':');
        sb.append(httpUrl.e);
        sb.append(", ");
        Proxy proxy = this.g;
        if (proxy != null) {
            str = "proxy=" + proxy;
        } else {
            str = "proxySelector=" + this.h;
        }
        return hf2.d(sb, str, '}');
    }
}
