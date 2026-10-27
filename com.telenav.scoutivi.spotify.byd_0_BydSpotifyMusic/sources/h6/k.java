package h6;

import c6.d0;
import c6.r;
import c6.u;
import h5.o;
import h5.p;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.m;

/* JADX INFO: compiled from: RouteSelector.kt */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a f6746i = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<? extends Proxy> f6747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f6748b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<? extends InetSocketAddress> f6749c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<d0> f6750d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c6.a f6751e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final i f6752f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final c6.e f6753g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final r f6754h;

    /* JADX INFO: compiled from: RouteSelector.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final String a(InetSocketAddress socketHost) {
            l.e(socketHost, "$this$socketHost");
            InetAddress address = socketHost.getAddress();
            if (address != null) {
                String hostAddress = address.getHostAddress();
                l.d(hostAddress, "address.hostAddress");
                return hostAddress;
            }
            String hostName = socketHost.getHostName();
            l.d(hostName, "hostName");
            return hostName;
        }
    }

    /* JADX INFO: compiled from: RouteSelector.kt */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f6755a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<d0> f6756b;

        public b(List<d0> routes) {
            l.e(routes, "routes");
            this.f6756b = routes;
        }

        public final List<d0> a() {
            return this.f6756b;
        }

        public final boolean b() {
            return this.f6755a < this.f6756b.size();
        }

        public final d0 c() {
            if (!b()) {
                throw new NoSuchElementException();
            }
            List<d0> list = this.f6756b;
            int i7 = this.f6755a;
            this.f6755a = i7 + 1;
            return list.get(i7);
        }
    }

    /* JADX INFO: compiled from: RouteSelector.kt */
    static final class c extends m implements r5.a<List<? extends Proxy>> {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Proxy f6758g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ u f6759h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Proxy proxy, u uVar) {
            super(0);
            this.f6758g = proxy;
            this.f6759h = uVar;
        }

        @Override // r5.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<Proxy> invoke() {
            Proxy proxy = this.f6758g;
            if (proxy != null) {
                return o.d(proxy);
            }
            URI uriQ = this.f6759h.q();
            if (uriQ.getHost() == null) {
                return d6.b.s(Proxy.NO_PROXY);
            }
            List<Proxy> listSelect = k.this.f6751e.i().select(uriQ);
            return listSelect == null || listSelect.isEmpty() ? d6.b.s(Proxy.NO_PROXY) : d6.b.M(listSelect);
        }
    }

    public k(c6.a address, i routeDatabase, c6.e call, r eventListener) {
        l.e(address, "address");
        l.e(routeDatabase, "routeDatabase");
        l.e(call, "call");
        l.e(eventListener, "eventListener");
        this.f6751e = address;
        this.f6752f = routeDatabase;
        this.f6753g = call;
        this.f6754h = eventListener;
        this.f6747a = p.h();
        this.f6749c = p.h();
        this.f6750d = new ArrayList();
        g(address.l(), address.g());
    }

    private final boolean c() {
        return this.f6748b < this.f6747a.size();
    }

    private final Proxy e() throws SocketException, UnknownHostException {
        if (c()) {
            List<? extends Proxy> list = this.f6747a;
            int i7 = this.f6748b;
            this.f6748b = i7 + 1;
            Proxy proxy = list.get(i7);
            f(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f6751e.l().h() + "; exhausted proxy configurations: " + this.f6747a);
    }

    private final void f(Proxy proxy) throws SocketException, UnknownHostException {
        String strH;
        int iL;
        ArrayList arrayList = new ArrayList();
        this.f6749c = arrayList;
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            strH = this.f6751e.l().h();
            iL = this.f6751e.l().l();
        } else {
            SocketAddress socketAddressAddress = proxy.address();
            if (!(socketAddressAddress instanceof InetSocketAddress)) {
                throw new IllegalArgumentException(("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass()).toString());
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
            strH = f6746i.a(inetSocketAddress);
            iL = inetSocketAddress.getPort();
        }
        if (1 > iL || 65535 < iL) {
            throw new SocketException("No route to " + strH + ':' + iL + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            arrayList.add(InetSocketAddress.createUnresolved(strH, iL));
            return;
        }
        this.f6754h.m(this.f6753g, strH);
        List<InetAddress> listA = this.f6751e.c().a(strH);
        if (listA.isEmpty()) {
            throw new UnknownHostException(this.f6751e.c() + " returned no addresses for " + strH);
        }
        this.f6754h.l(this.f6753g, strH, listA);
        Iterator<InetAddress> it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(new InetSocketAddress(it.next(), iL));
        }
    }

    private final void g(u uVar, Proxy proxy) {
        c cVar = new c(proxy, uVar);
        this.f6754h.o(this.f6753g, uVar);
        List<Proxy> listInvoke = cVar.invoke();
        this.f6747a = listInvoke;
        this.f6748b = 0;
        this.f6754h.n(this.f6753g, uVar, listInvoke);
    }

    public final boolean b() {
        return c() || (this.f6750d.isEmpty() ^ true);
    }

    public final b d() {
        if (!b()) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        while (c()) {
            Proxy proxyE = e();
            Iterator<? extends InetSocketAddress> it = this.f6749c.iterator();
            while (it.hasNext()) {
                d0 d0Var = new d0(this.f6751e, proxyE, it.next());
                if (this.f6752f.c(d0Var)) {
                    this.f6750d.add(d0Var);
                } else {
                    arrayList.add(d0Var);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            h5.u.r(arrayList, this.f6750d);
            this.f6750d.clear();
        }
        return new b(arrayList);
    }
}
