package c9;

import N1.C0420a;
import java.net.ProxySelector;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public final class C implements Cloneable, InterfaceC1047h, L {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final List f12466M = d9.b.l(D.HTTP_2, D.HTTP_1_1);

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final List f12467N = d9.b.l(n.f12589e, n.f12590f);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final SSLSocketFactory f12468A;
    public final X509TrustManager B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final List f12469C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final List f12470D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final p9.c f12471E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final C1049j f12472F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final h6.i f12473G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final int f12474H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final int f12475I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final int f12476J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final long f12477K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final e7.f f12478L;
    public final B8.t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0420a f12479m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final List f12480n;
    public final List o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final G2.a f12481p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f12482q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C1041b f12483r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f12484s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f12485t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C1041b f12486u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C1045f f12487v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C1041b f12488w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final ProxySelector f12489x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C1041b f12490y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final SocketFactory f12491z;

    public C(B b10) throws NoSuchAlgorithmException, KeyStoreException {
        A7.m.f(b10, "builder");
        io.sentry.okhttp.d dVar = new io.sentry.okhttp.d(b10.f12446e);
        byte[] bArr = d9.b.f13645a;
        b10.f12446e = new G2.a(dVar, 6);
        this.l = b10.f12444a;
        this.f12479m = b10.f12445b;
        this.f12480n = d9.b.x(b10.c);
        this.o = d9.b.x(b10.d);
        this.f12481p = b10.f12446e;
        this.f12482q = b10.f12447f;
        this.f12483r = b10.f12448g;
        this.f12484s = b10.f12449h;
        this.f12485t = b10.f12450i;
        this.f12486u = b10.f12451j;
        this.f12487v = b10.f12452k;
        this.f12488w = b10.l;
        ProxySelector proxySelector = b10.f12453m;
        proxySelector = proxySelector == null ? ProxySelector.getDefault() : proxySelector;
        this.f12489x = proxySelector == null ? n9.a.f20172a : proxySelector;
        this.f12490y = b10.f12454n;
        this.f12491z = b10.o;
        List list = b10.f12457r;
        this.f12469C = list;
        this.f12470D = b10.f12458s;
        this.f12471E = b10.f12459t;
        this.f12474H = b10.f12462w;
        this.f12475I = b10.f12463x;
        this.f12476J = b10.f12464y;
        this.f12477K = b10.f12465z;
        e7.f fVar = b10.f12443A;
        this.f12478L = fVar == null ? new e7.f(4, (byte) 0) : fVar;
        if (list == null || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((n) it.next()).f12591a) {
                    SSLSocketFactory sSLSocketFactory = b10.f12455p;
                    if (sSLSocketFactory != null) {
                        this.f12468A = sSLSocketFactory;
                        h6.i iVar = b10.f12461v;
                        A7.m.c(iVar);
                        this.f12473G = iVar;
                        X509TrustManager x509TrustManager = b10.f12456q;
                        A7.m.c(x509TrustManager);
                        this.B = x509TrustManager;
                        C1049j c1049j = b10.f12460u;
                        c1049j.getClass();
                        this.f12472F = A7.m.a(c1049j.f12570b, iVar) ? c1049j : new C1049j(c1049j.f12569a, iVar);
                    } else {
                        l9.o oVar = l9.o.f18874a;
                        X509TrustManager x509TrustManagerM = l9.o.f18874a.m();
                        this.B = x509TrustManagerM;
                        this.f12468A = l9.o.f18874a.l(x509TrustManagerM);
                        h6.i iVarB = l9.o.f18874a.b(x509TrustManagerM);
                        this.f12473G = iVarB;
                        C1049j c1049j2 = b10.f12460u;
                        c1049j2.getClass();
                        this.f12472F = A7.m.a(c1049j2.f12570b, iVarB) ? c1049j2 : new C1049j(c1049j2.f12569a, iVarB);
                    }
                }
            }
            this.f12468A = null;
            this.f12473G = null;
            this.B = null;
            this.f12472F = C1049j.c;
        } else {
            this.f12468A = null;
            this.f12473G = null;
            this.B = null;
            this.f12472F = C1049j.c;
        }
        List list2 = this.f12480n;
        A7.m.d(list2, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (list2.contains(null)) {
            throw new IllegalStateException(("Null interceptor: " + list2).toString());
        }
        List list3 = this.o;
        A7.m.d(list3, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if (list3.contains(null)) {
            throw new IllegalStateException(("Null network interceptor: " + list3).toString());
        }
        X509TrustManager x509TrustManager2 = this.B;
        h6.i iVar2 = this.f12473G;
        SSLSocketFactory sSLSocketFactory2 = this.f12468A;
        List list4 = this.f12469C;
        if (list4 == null || !list4.isEmpty()) {
            Iterator it2 = list4.iterator();
            while (it2.hasNext()) {
                if (((n) it2.next()).f12591a) {
                    if (sSLSocketFactory2 == null) {
                        throw new IllegalStateException("sslSocketFactory == null");
                    }
                    if (iVar2 == null) {
                        throw new IllegalStateException("certificateChainCleaner == null");
                    }
                    if (x509TrustManager2 == null) {
                        throw new IllegalStateException("x509TrustManager == null");
                    }
                    return;
                }
            }
        }
        if (sSLSocketFactory2 != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (iVar2 != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (x509TrustManager2 != null) {
            throw new IllegalStateException("Check failed.");
        }
        if (!A7.m.a(this.f12472F, C1049j.c)) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final g9.j a(H0.b bVar) {
        A7.m.f(bVar, "request");
        return new g9.j(this, bVar, false);
    }

    public final Object clone() {
        return super.clone();
    }
}
