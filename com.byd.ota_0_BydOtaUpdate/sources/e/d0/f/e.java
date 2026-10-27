package e.d0.f;

import e.c0;
import java.io.IOException;
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
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: RouteSelector.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e.a f3375a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f3376b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Proxy f3377c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public InetSocketAddress f3378d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3380f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3382h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<Proxy> f3379e = Collections.emptyList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public List<InetSocketAddress> f3381g = Collections.emptyList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List<c0> f3383i = new ArrayList();

    public e(e.a aVar, d dVar) {
        this.f3375a = aVar;
        this.f3376b = dVar;
        l(aVar.l(), aVar.g());
    }

    public static String b(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        return address == null ? inetSocketAddress.getHostName() : address.getHostAddress();
    }

    public void a(c0 c0Var, IOException iOException) {
        if (c0Var.b().type() != Proxy.Type.DIRECT && this.f3375a.i() != null) {
            this.f3375a.i().connectFailed(this.f3375a.l().E(), c0Var.b().address(), iOException);
        }
        this.f3376b.b(c0Var);
    }

    public boolean c() {
        return d() || f() || e();
    }

    public final boolean d() {
        return this.f3382h < this.f3381g.size();
    }

    public final boolean e() {
        return !this.f3383i.isEmpty();
    }

    public final boolean f() {
        return this.f3380f < this.f3379e.size();
    }

    public c0 g() throws IOException {
        if (!d()) {
            if (!f()) {
                if (e()) {
                    return i();
                }
                throw new NoSuchElementException();
            }
            this.f3377c = j();
        }
        InetSocketAddress inetSocketAddressH = h();
        this.f3378d = inetSocketAddressH;
        c0 c0Var = new c0(this.f3375a, this.f3377c, inetSocketAddressH);
        if (!this.f3376b.c(c0Var)) {
            return c0Var;
        }
        this.f3383i.add(c0Var);
        return g();
    }

    public final InetSocketAddress h() throws IOException {
        if (d()) {
            List<InetSocketAddress> list = this.f3381g;
            int i2 = this.f3382h;
            this.f3382h = i2 + 1;
            return list.get(i2);
        }
        throw new SocketException("No route to " + this.f3375a.l().l() + "; exhausted inet socket addresses: " + this.f3381g);
    }

    public final c0 i() {
        return this.f3383i.remove(0);
    }

    public final Proxy j() throws IOException {
        if (f()) {
            List<Proxy> list = this.f3379e;
            int i2 = this.f3380f;
            this.f3380f = i2 + 1;
            Proxy proxy = list.get(i2);
            k(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f3375a.l().l() + "; exhausted proxy configurations: " + this.f3379e);
    }

    public final void k(Proxy proxy) throws IOException {
        String strL;
        int iY;
        this.f3381g = new ArrayList();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            strL = this.f3375a.l().l();
            iY = this.f3375a.l().y();
        } else {
            SocketAddress socketAddressAddress = proxy.address();
            if (!(socketAddressAddress instanceof InetSocketAddress)) {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass());
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
            strL = b(inetSocketAddress);
            iY = inetSocketAddress.getPort();
        }
        if (iY < 1 || iY > 65535) {
            throw new SocketException("No route to " + strL + ":" + iY + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            this.f3381g.add(InetSocketAddress.createUnresolved(strL, iY));
        } else {
            List<InetAddress> listA = this.f3375a.c().a(strL);
            if (listA.isEmpty()) {
                throw new UnknownHostException(this.f3375a.c() + " returned no addresses for " + strL);
            }
            int size = listA.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f3381g.add(new InetSocketAddress(listA.get(i2), iY));
            }
        }
        this.f3382h = 0;
    }

    public final void l(HttpUrl httpUrl, Proxy proxy) {
        if (proxy != null) {
            this.f3379e = Collections.singletonList(proxy);
        } else {
            List<Proxy> listSelect = this.f3375a.i().select(httpUrl.E());
            this.f3379e = (listSelect == null || listSelect.isEmpty()) ? e.d0.c.o(Proxy.NO_PROXY) : e.d0.c.n(listSelect);
        }
        this.f3380f = 0;
    }
}
