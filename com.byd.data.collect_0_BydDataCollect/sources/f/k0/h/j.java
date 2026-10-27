package f.k0.h;

import f.i0;
import f.t;
import f.x;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: RouteSelector.java */
/* JADX INFO: loaded from: classes.dex */
final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f.e f5206a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h f5207b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f.i f5208c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final t f5209d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f5211f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<Proxy> f5210e = Collections.emptyList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<InetSocketAddress> f5212g = Collections.emptyList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final List<i0> f5213h = new ArrayList();

    /* JADX INFO: compiled from: RouteSelector.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<i0> f5214a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f5215b = 0;

        a(List<i0> list) {
            this.f5214a = list;
        }

        public List<i0> a() {
            return new ArrayList(this.f5214a);
        }

        public boolean b() {
            return this.f5215b < this.f5214a.size();
        }

        public i0 c() {
            if (!b()) {
                throw new NoSuchElementException();
            }
            List<i0> list = this.f5214a;
            int i2 = this.f5215b;
            this.f5215b = i2 + 1;
            return list.get(i2);
        }
    }

    j(f.e eVar, h hVar, f.i iVar, t tVar) {
        this.f5206a = eVar;
        this.f5207b = hVar;
        this.f5208c = iVar;
        this.f5209d = tVar;
        g(eVar.l(), eVar.g());
    }

    static String a(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        return address == null ? inetSocketAddress.getHostName() : address.getHostAddress();
    }

    private boolean c() {
        return this.f5211f < this.f5210e.size();
    }

    private Proxy e() throws SocketException, UnknownHostException {
        if (c()) {
            List<Proxy> list = this.f5210e;
            int i2 = this.f5211f;
            this.f5211f = i2 + 1;
            Proxy proxy = list.get(i2);
            f(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f5206a.l().m() + "; exhausted proxy configurations: " + this.f5210e);
    }

    private void f(Proxy proxy) throws SocketException, UnknownHostException {
        String strM;
        int iY;
        this.f5212g = new ArrayList();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            strM = this.f5206a.l().m();
            iY = this.f5206a.l().y();
        } else {
            SocketAddress socketAddressAddress = proxy.address();
            if (!(socketAddressAddress instanceof InetSocketAddress)) {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass());
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
            strM = a(inetSocketAddress);
            iY = inetSocketAddress.getPort();
        }
        if (iY < 1 || iY > 65535) {
            throw new SocketException("No route to " + strM + ":" + iY + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            this.f5212g.add(InetSocketAddress.createUnresolved(strM, iY));
            return;
        }
        this.f5209d.j(this.f5208c, strM);
        List<InetAddress> listB = this.f5206a.c().b(strM);
        if (listB.isEmpty()) {
            throw new UnknownHostException(this.f5206a.c() + " returned no addresses for " + strM);
        }
        this.f5209d.i(this.f5208c, strM, listB);
        int size = listB.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f5212g.add(new InetSocketAddress(listB.get(i2), iY));
        }
    }

    private void g(x xVar, Proxy proxy) {
        if (proxy != null) {
            this.f5210e = Collections.singletonList(proxy);
        } else {
            List<Proxy> listSelect = this.f5206a.i().select(xVar.E());
            this.f5210e = (listSelect == null || listSelect.isEmpty()) ? f.k0.e.t(Proxy.NO_PROXY) : f.k0.e.s(listSelect);
        }
        this.f5211f = 0;
    }

    public boolean b() {
        return c() || !this.f5213h.isEmpty();
    }

    public a d() throws SocketException, UnknownHostException {
        if (!b()) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        while (c()) {
            Proxy proxyE = e();
            int size = this.f5212g.size();
            for (int i2 = 0; i2 < size; i2++) {
                i0 i0Var = new i0(this.f5206a, proxyE, this.f5212g.get(i2));
                if (this.f5207b.c(i0Var)) {
                    this.f5213h.add(i0Var);
                } else {
                    arrayList.add(i0Var);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.addAll(this.f5213h);
            this.f5213h.clear();
        }
        return new a(arrayList);
    }
}
