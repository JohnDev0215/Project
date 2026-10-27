package f.b0.f;

import f.a0;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: RouteSelector.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f.a f5054a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f5055b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Proxy f5056c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public InetSocketAddress f5057d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5059f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5061h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<Proxy> f5058e = Collections.emptyList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public List<InetSocketAddress> f5060g = Collections.emptyList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List<a0> f5062i = new ArrayList();

    public e(f.a aVar, d dVar) {
        this.f5054a = aVar;
        this.f5055b = dVar;
        l(aVar.k(), aVar.f());
    }

    public static String b(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        return address == null ? inetSocketAddress.getHostName() : address.getHostAddress();
    }

    public void a(a0 a0Var, IOException iOException) {
        if (a0Var.b().type() != Proxy.Type.DIRECT && this.f5054a.h() != null) {
            this.f5054a.h().connectFailed(this.f5054a.k().D(), a0Var.b().address(), iOException);
        }
        this.f5055b.b(a0Var);
    }

    public boolean c() {
        return d() || f() || e();
    }

    public final boolean d() {
        return this.f5061h < this.f5060g.size();
    }

    public final boolean e() {
        return !this.f5062i.isEmpty();
    }

    public final boolean f() {
        return this.f5059f < this.f5058e.size();
    }

    public a0 g() throws IOException {
        if (!d()) {
            if (!f()) {
                if (e()) {
                    return i();
                }
                throw new NoSuchElementException();
            }
            this.f5056c = j();
        }
        InetSocketAddress inetSocketAddressH = h();
        this.f5057d = inetSocketAddressH;
        a0 a0Var = new a0(this.f5054a, this.f5056c, inetSocketAddressH);
        if (!this.f5055b.c(a0Var)) {
            return a0Var;
        }
        this.f5062i.add(a0Var);
        return g();
    }

    public final InetSocketAddress h() throws IOException {
        if (d()) {
            List<InetSocketAddress> list = this.f5060g;
            int i2 = this.f5061h;
            this.f5061h = i2 + 1;
            return list.get(i2);
        }
        throw new SocketException("No route to " + this.f5054a.k().l() + "; exhausted inet socket addresses: " + this.f5060g);
    }

    public final a0 i() {
        return this.f5062i.remove(0);
    }

    public final Proxy j() throws IOException {
        if (f()) {
            List<Proxy> list = this.f5058e;
            int i2 = this.f5059f;
            this.f5059f = i2 + 1;
            Proxy proxy = list.get(i2);
            k(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f5054a.k().l() + "; exhausted proxy configurations: " + this.f5058e);
    }

    public final void k(Proxy proxy) throws IOException {
        String strL;
        int iX;
        this.f5060g = new ArrayList();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            strL = this.f5054a.k().l();
            iX = this.f5054a.k().x();
        } else {
            SocketAddress socketAddressAddress = proxy.address();
            if (!(socketAddressAddress instanceof InetSocketAddress)) {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass());
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
            strL = b(inetSocketAddress);
            iX = inetSocketAddress.getPort();
        }
        if (iX < 1 || iX > 65535) {
            throw new SocketException("No route to " + strL + ":" + iX + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            this.f5060g.add(InetSocketAddress.createUnresolved(strL, iX));
        } else {
            List<InetAddress> listA = this.f5054a.c().a(strL);
            int size = listA.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f5060g.add(new InetSocketAddress(listA.get(i2), iX));
            }
        }
        this.f5061h = 0;
    }

    public final void l(HttpUrl httpUrl, Proxy proxy) {
        if (proxy != null) {
            this.f5058e = Collections.singletonList(proxy);
        } else {
            List<Proxy> listSelect = this.f5054a.h().select(httpUrl.D());
            this.f5058e = (listSelect == null || listSelect.isEmpty()) ? f.b0.c.o(Proxy.NO_PROXY) : f.b0.c.n(listSelect);
        }
        this.f5059f = 0;
    }
}
