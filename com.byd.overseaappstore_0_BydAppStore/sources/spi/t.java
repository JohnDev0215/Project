package spi;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.hf;

/* JADX INFO: loaded from: classes2.dex */
public final class t {

    @NotNull
    public final hf a;

    @NotNull
    public final List<Protocol> b;

    @NotNull
    public final List<d4> c;

    @NotNull
    public final s6 d;

    @NotNull
    public final SocketFactory e;

    @Nullable
    public final SSLSocketFactory f;

    @Nullable
    public final HostnameVerifier g;

    @Nullable
    public final m3 h;

    @NotNull
    public final u0 i;

    @Nullable
    public final Proxy j;

    @NotNull
    public final ProxySelector k;

    public t(@NotNull String host, int i, @NotNull s6 dns, @NotNull SocketFactory socketFactory, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier, @Nullable m3 m3Var, @NotNull u0 proxyAuthenticator, @Nullable Proxy proxy, @NotNull List<? extends Protocol> protocols, @NotNull List<d4> connectionSpecs, @NotNull ProxySelector proxySelector) {
        Intrinsics.checkNotNullParameter(host, "uriHost");
        Intrinsics.checkNotNullParameter(dns, "dns");
        Intrinsics.checkNotNullParameter(socketFactory, "socketFactory");
        Intrinsics.checkNotNullParameter(proxyAuthenticator, "proxyAuthenticator");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        Intrinsics.checkNotNullParameter(connectionSpecs, "connectionSpecs");
        Intrinsics.checkNotNullParameter(proxySelector, "proxySelector");
        this.d = dns;
        this.e = socketFactory;
        this.f = sSLSocketFactory;
        this.g = hostnameVerifier;
        this.h = m3Var;
        this.i = proxyAuthenticator;
        this.j = proxy;
        this.k = proxySelector;
        hf.a aVar = new hf.a();
        String str = "https";
        String scheme = sSLSocketFactory != null ? "https" : "http";
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        if (StringsKt.h(scheme, "http")) {
            str = "http";
        } else if (!StringsKt.h(scheme, "https")) {
            throw new IllegalArgumentException(d.f("unexpected scheme: ", scheme));
        }
        aVar.a = str;
        Intrinsics.checkNotNullParameter(host, "host");
        String strG = c.g(hf.b.d(hf.l, host, 0, 0, false, 7));
        if (strG == null) {
            throw new IllegalArgumentException(d.f("unexpected host: ", host));
        }
        aVar.d = strG;
        if (!(1 <= i && 65535 >= i)) {
            throw new IllegalArgumentException(e.c("unexpected port: ", i).toString());
        }
        aVar.e = i;
        this.a = aVar.a();
        this.b = uu.x(protocols);
        this.c = uu.x(connectionSpecs);
    }

    public final boolean a(@NotNull t that) {
        Intrinsics.checkNotNullParameter(that, "that");
        return Intrinsics.areEqual(this.d, that.d) && Intrinsics.areEqual(this.i, that.i) && Intrinsics.areEqual(this.b, that.b) && Intrinsics.areEqual(this.c, that.c) && Intrinsics.areEqual(this.k, that.k) && Intrinsics.areEqual(this.j, that.j) && Intrinsics.areEqual(this.f, that.f) && Intrinsics.areEqual(this.g, that.g) && Intrinsics.areEqual(this.h, that.h) && this.a.f == that.a.f;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof t) {
            t tVar = (t) obj;
            if (Intrinsics.areEqual(this.a, tVar.a) && a(tVar)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h) + ((Objects.hashCode(this.g) + ((Objects.hashCode(this.f) + ((Objects.hashCode(this.j) + ((this.k.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + ((this.i.hashCode() + ((this.d.hashCode() + ((this.a.hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    @NotNull
    public final String toString() {
        StringBuilder sbK;
        Object obj;
        StringBuilder sbK2 = e.k("Address{");
        sbK2.append(this.a.e);
        sbK2.append(':');
        sbK2.append(this.a.f);
        sbK2.append(", ");
        if (this.j != null) {
            sbK = e.k("proxy=");
            obj = this.j;
        } else {
            sbK = e.k("proxySelector=");
            obj = this.k;
        }
        sbK.append(obj);
        sbK2.append(sbK.toString());
        sbK2.append("}");
        return sbK2.toString();
    }
}
