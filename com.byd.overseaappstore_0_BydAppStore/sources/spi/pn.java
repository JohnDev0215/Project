package spi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;
import org.jetbrains.annotations.NotNull;
import spi.ag;
import spi.hf;
import spi.im;
import spi.ln;
import spi.tn;

/* JADX INFO: loaded from: classes2.dex */
public final class pn implements ag {
    public final dk a;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new a(null);
    }

    public pn(@NotNull dk client) {
        Intrinsics.checkNotNullParameter(client, "client");
        this.a = client;
    }

    @Override // spi.ag
    @NotNull
    public final ln a(@NotNull ag.a chain) throws Throwable {
        List list;
        ln lnVar;
        int i;
        y8 y8Var;
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifier;
        m3 m3Var;
        Intrinsics.checkNotNullParameter(chain, "chain");
        bm bmVar = (bm) chain;
        im imVar = bmVar.f;
        vl vlVar = bmVar.b;
        boolean z = true;
        List listEmptyList = CollectionsKt.emptyList();
        ln lnVar2 = null;
        int i2 = 0;
        im request = imVar;
        boolean z2 = true;
        while (true) {
            Objects.requireNonNull(vlVar);
            Intrinsics.checkNotNullParameter(request, "request");
            if (!(vlVar.i == null ? z : false)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            synchronized (vlVar) {
                if (!(vlVar.k ^ z)) {
                    throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()".toString());
                }
                if (!(vlVar.j ^ z)) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                Unit unit = Unit.INSTANCE;
            }
            if (z2) {
                am amVar = vlVar.a;
                hf hfVar = request.b;
                if (hfVar.a) {
                    dk dkVar = vlVar.p;
                    SSLSocketFactory sSLSocketFactory2 = dkVar.q;
                    if (sSLSocketFactory2 == null) {
                        throw new IllegalStateException("CLEARTEXT-only client");
                    }
                    HostnameVerifier hostnameVerifier2 = dkVar.u;
                    m3Var = dkVar.v;
                    sSLSocketFactory = sSLSocketFactory2;
                    hostnameVerifier = hostnameVerifier2;
                } else {
                    sSLSocketFactory = null;
                    hostnameVerifier = null;
                    m3Var = null;
                }
                String str = hfVar.e;
                int i3 = hfVar.f;
                dk dkVar2 = vlVar.p;
                list = listEmptyList;
                i = i2;
                lnVar = lnVar2;
                vlVar.f = new a9(amVar, new t(str, i3, dkVar2.l, dkVar2.p, sSLSocketFactory, hostnameVerifier, m3Var, dkVar2.o, dkVar2.m, dkVar2.t, dkVar2.s, dkVar2.n), vlVar, vlVar.b);
            } else {
                list = listEmptyList;
                lnVar = lnVar2;
                i = i2;
            }
            try {
                if (vlVar.m) {
                    throw new IOException("Canceled");
                }
                try {
                    try {
                        ln lnVarB = bmVar.b(request);
                        if (lnVar != null) {
                            ln.a aVar = new ln.a(lnVarB);
                            ln.a aVar2 = new ln.a(lnVar);
                            aVar2.g = null;
                            ln lnVarA = aVar2.a();
                            if (!(lnVarA.h == null)) {
                                throw new IllegalArgumentException("priorResponse.body != null".toString());
                            }
                            aVar.j = lnVarA;
                            lnVarB = aVar.a();
                        }
                        lnVar2 = lnVarB;
                        y8Var = vlVar.i;
                    } catch (RouteException e) {
                        List list2 = list;
                        ln lnVar3 = lnVar;
                        if (!c(e.getLastConnectException(), vlVar, request, false)) {
                            IOException firstConnectException = e.getFirstConnectException();
                            uu.A(firstConnectException, list2);
                            throw firstConnectException;
                        }
                        listEmptyList = CollectionsKt.plus((Collection<? extends IOException>) list2, e.getFirstConnectException());
                        z = true;
                        vlVar.f(true);
                        z2 = false;
                        i2 = i;
                        lnVar2 = lnVar3;
                    }
                } catch (IOException e2) {
                    ln lnVar4 = lnVar;
                    List list3 = list;
                    if (!c(e2, vlVar, request, !(e2 instanceof ConnectionShutdownException))) {
                        uu.A(e2, list3);
                        throw e2;
                    }
                    listEmptyList = CollectionsKt.plus((Collection<? extends IOException>) list3, e2);
                    z = true;
                    vlVar.f(true);
                    lnVar2 = lnVar4;
                    i2 = i;
                    z2 = false;
                }
                try {
                    request = b(lnVar2, y8Var);
                    if (request == null) {
                        if (y8Var != null && y8Var.a) {
                            if (!(!vlVar.h)) {
                                throw new IllegalStateException("Check failed.".toString());
                            }
                            vlVar.h = true;
                            vlVar.c.i();
                        }
                        vlVar.f(false);
                        return lnVar2;
                    }
                    mm mmVar = request.e;
                    if (mmVar != null && mmVar.isOneShot()) {
                        vlVar.f(false);
                        return lnVar2;
                    }
                    mn mnVar = lnVar2.h;
                    if (mnVar != null) {
                        uu.c(mnVar);
                    }
                    i2 = i + 1;
                    if (i2 > 20) {
                        throw new ProtocolException("Too many follow-up requests: " + i2);
                    }
                    vlVar.f(true);
                    listEmptyList = list;
                    z2 = true;
                    z = true;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            vlVar.f(true);
            throw th;
        }
    }

    public final im b(ln lnVar, y8 y8Var) throws IOException {
        String link;
        wl wlVar;
        mm mmVar = null;
        rn rnVar = (y8Var == null || (wlVar = y8Var.b) == null) ? null : wlVar.q;
        int i = lnVar.e;
        im imVar = lnVar.b;
        String method = imVar.c;
        if (i != 307 && i != 308) {
            if (i == 401) {
                return this.a.g.a(rnVar, lnVar);
            }
            if (i == 421) {
                mm mmVar2 = imVar.e;
                if ((mmVar2 != null && mmVar2.isOneShot()) || y8Var == null || !(!Intrinsics.areEqual(y8Var.e.h.a.e, y8Var.b.q.a.a.e))) {
                    return null;
                }
                wl wlVar2 = y8Var.b;
                synchronized (wlVar2) {
                    wlVar2.j = true;
                }
                return lnVar.b;
            }
            if (i == 503) {
                ln lnVar2 = lnVar.k;
                if ((lnVar2 == null || lnVar2.e != 503) && d(lnVar, Integer.MAX_VALUE) == 0) {
                    return lnVar.b;
                }
                return null;
            }
            if (i == 407) {
                Intrinsics.checkNotNull(rnVar);
                if (rnVar.b.type() == Proxy.Type.HTTP) {
                    return this.a.o.a(rnVar, lnVar);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            if (i == 408) {
                if (!this.a.f) {
                    return null;
                }
                mm mmVar3 = imVar.e;
                if (mmVar3 != null && mmVar3.isOneShot()) {
                    return null;
                }
                ln lnVar3 = lnVar.k;
                if ((lnVar3 == null || lnVar3.e != 408) && d(lnVar, 0) <= 0) {
                    return lnVar.b;
                }
                return null;
            }
            switch (i) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        if (!this.a.h || (link = ln.B(lnVar, "Location")) == null) {
            return null;
        }
        hf hfVar = lnVar.b.b;
        Objects.requireNonNull(hfVar);
        Intrinsics.checkNotNullParameter(link, "link");
        hf.a aVarG = hfVar.g(link);
        hf url = aVarG != null ? aVarG.a() : null;
        if (url == null) {
            return null;
        }
        if (!Intrinsics.areEqual(url.b, lnVar.b.b.b) && !this.a.i) {
            return null;
        }
        im imVar2 = lnVar.b;
        Objects.requireNonNull(imVar2);
        im.a aVar = new im.a(imVar2);
        if (gf.b(method)) {
            int i2 = lnVar.e;
            Objects.requireNonNull(gf.a);
            Intrinsics.checkNotNullParameter(method, "method");
            boolean z = Intrinsics.areEqual(method, "PROPFIND") || i2 == 308 || i2 == 307;
            Intrinsics.checkNotNullParameter(method, "method");
            if ((!Intrinsics.areEqual(method, "PROPFIND")) && i2 != 308 && i2 != 307) {
                method = "GET";
            } else if (z) {
                mmVar = lnVar.b.e;
            }
            aVar.d(method, mmVar);
            if (!z) {
                aVar.e("Transfer-Encoding");
                aVar.e("Content-Length");
                aVar.e("Content-Type");
            }
        }
        if (!uu.a(lnVar.b.b, url)) {
            aVar.e("Authorization");
        }
        Intrinsics.checkNotNullParameter(url, "url");
        aVar.a = url;
        return aVar.a();
    }

    public final boolean c(IOException iOException, vl vlVar, im imVar, boolean z) {
        boolean zA;
        tn tnVar;
        wl wlVar;
        if (!this.a.f) {
            return false;
        }
        if (z) {
            mm mmVar = imVar.e;
            if ((mmVar != null && mmVar.isOneShot()) || (iOException instanceof FileNotFoundException)) {
                return false;
            }
        }
        if (!(!(iOException instanceof ProtocolException) && (!(iOException instanceof InterruptedIOException) ? ((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException) : !((iOException instanceof SocketTimeoutException) && !z)))) {
            return false;
        }
        a9 a9Var = vlVar.f;
        Intrinsics.checkNotNull(a9Var);
        int i = a9Var.c;
        if (i == 0 && a9Var.d == 0 && a9Var.e == 0) {
            zA = false;
        } else if (a9Var.f != null) {
            zA = true;
        } else {
            rn rnVar = null;
            if (i <= 1 && a9Var.d <= 1 && a9Var.e <= 0 && (wlVar = a9Var.i.g) != null) {
                synchronized (wlVar) {
                    if (wlVar.k == 0 && uu.a(wlVar.q.a.a, a9Var.h.a)) {
                        rnVar = wlVar.q;
                    }
                }
            }
            if (rnVar != null) {
                a9Var.f = rnVar;
            } else {
                tn.b bVar = a9Var.a;
                if ((bVar == null || !bVar.a()) && (tnVar = a9Var.b) != null) {
                    zA = tnVar.a();
                }
            }
            zA = true;
        }
        return zA;
    }

    public final int d(ln lnVar, int i) {
        String strB = ln.B(lnVar, "Retry-After");
        if (strB == null) {
            return i;
        }
        if (!new Regex("\\d+").matches(strB)) {
            return Integer.MAX_VALUE;
        }
        Integer numValueOf = Integer.valueOf(strB);
        Intrinsics.checkNotNullExpressionValue(numValueOf, "Integer.valueOf(header)");
        return numValueOf.intValue();
    }
}
