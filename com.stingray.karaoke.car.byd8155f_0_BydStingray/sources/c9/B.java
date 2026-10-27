package c9;

import N1.C0420a;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public final class B {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public e7.f f12443A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public B8.t f12444a = new B8.t(11);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0420a f12445b = new C0420a(26);
    public final ArrayList c = new ArrayList();
    public final ArrayList d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public G2.a f12446e = new G2.a(q.f12603a, 6);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f12447f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C1041b f12448g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f12449h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f12450i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public C1041b f12451j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public C1045f f12452k;
    public C1041b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ProxySelector f12453m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C1041b f12454n;
    public SocketFactory o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public SSLSocketFactory f12455p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public X509TrustManager f12456q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f12457r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f12458s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public p9.c f12459t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C1049j f12460u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public h6.i f12461v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f12462w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f12463x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f12464y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public long f12465z;

    public B() {
        C1041b c1041b = C1041b.f12543a;
        this.f12448g = c1041b;
        this.f12449h = true;
        this.f12450i = true;
        this.f12451j = C1041b.f12544b;
        this.l = C1041b.c;
        this.f12454n = c1041b;
        SocketFactory socketFactory = SocketFactory.getDefault();
        A7.m.e(socketFactory, "getDefault()");
        this.o = socketFactory;
        this.f12457r = C.f12467N;
        this.f12458s = C.f12466M;
        this.f12459t = p9.c.f21008a;
        this.f12460u = C1049j.c;
        this.f12462w = 10000;
        this.f12463x = 10000;
        this.f12464y = 10000;
        this.f12465z = 1024L;
    }
}
