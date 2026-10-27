package h6;

import c6.b0;
import c6.d0;
import c6.r;
import c6.s;
import c6.u;
import c6.x;
import c6.y;
import c6.z;
import h5.q;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import k6.f;
import k6.n;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.m;
import p6.o;
import y5.p;

/* JADX INFO: compiled from: RealConnection.kt */
/* JADX INFO: loaded from: classes.dex */
public final class f extends f.d implements c6.j {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final a f6713t = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Socket f6714c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Socket f6715d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private s f6716e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private y f6717f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private k6.f f6718g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private p6.g f6719h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private p6.f f6720i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f6721j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f6722k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f6723l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f6724m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f6725n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f6726o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final List<Reference<e>> f6727p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private long f6728q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private final h f6729r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final d0 f6730s;

    /* JADX INFO: compiled from: RealConnection.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: RealConnection.kt */
    static final class b extends m implements r5.a<List<? extends Certificate>> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ c6.g f6731e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ s f6732g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ c6.a f6733h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(c6.g gVar, s sVar, c6.a aVar) {
            super(0);
            this.f6731e = gVar;
            this.f6732g = sVar;
            this.f6733h = aVar;
        }

        @Override // r5.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<Certificate> invoke() {
            o6.c cVarD = this.f6731e.d();
            l.c(cVarD);
            return cVarD.a(this.f6732g.d(), this.f6733h.l().h());
        }
    }

    /* JADX INFO: compiled from: RealConnection.kt */
    static final class c extends m implements r5.a<List<? extends X509Certificate>> {
        c() {
            super(0);
        }

        @Override // r5.a
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final List<X509Certificate> invoke() {
            s sVar = f.this.f6716e;
            l.c(sVar);
            List<Certificate> listD = sVar.d();
            ArrayList arrayList = new ArrayList(q.p(listD, 10));
            for (Certificate certificate : listD) {
                Objects.requireNonNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                arrayList.add((X509Certificate) certificate);
            }
            return arrayList;
        }
    }

    public f(h connectionPool, d0 route) {
        l.e(connectionPool, "connectionPool");
        l.e(route, "route");
        this.f6729r = connectionPool;
        this.f6730s = route;
        this.f6726o = 1;
        this.f6727p = new ArrayList();
        this.f6728q = Long.MAX_VALUE;
    }

    private final boolean A(List<d0> list) {
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (d0 d0Var : list) {
                if (d0Var.b().type() == Proxy.Type.DIRECT && this.f6730s.b().type() == Proxy.Type.DIRECT && l.a(this.f6730s.d(), d0Var.d())) {
                    return true;
                }
            }
        }
        return false;
    }

    private final void E(int i7) throws SocketException {
        Socket socket = this.f6715d;
        l.c(socket);
        p6.g gVar = this.f6719h;
        l.c(gVar);
        p6.f fVar = this.f6720i;
        l.c(fVar);
        socket.setSoTimeout(0);
        k6.f fVarA = new f.b(true, g6.e.f6577h).m(socket, this.f6730s.a().l().h(), gVar, fVar).k(this).l(i7).a();
        this.f6718g = fVarA;
        this.f6726o = k6.f.I.a().d();
        k6.f.I0(fVarA, false, null, 3, null);
    }

    private final boolean F(u uVar) {
        s sVar;
        if (d6.b.f5967h && !Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        u uVarL = this.f6730s.a().l();
        if (uVar.l() != uVarL.l()) {
            return false;
        }
        if (l.a(uVar.h(), uVarL.h())) {
            return true;
        }
        if (this.f6722k || (sVar = this.f6716e) == null) {
            return false;
        }
        l.c(sVar);
        return e(uVar, sVar);
    }

    private final boolean e(u uVar, s sVar) {
        List<Certificate> listD = sVar.d();
        if (!listD.isEmpty()) {
            o6.d dVar = o6.d.f8672a;
            String strH = uVar.h();
            Certificate certificate = listD.get(0);
            Objects.requireNonNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            if (dVar.e(strH, (X509Certificate) certificate)) {
                return true;
            }
        }
        return false;
    }

    private final void h(int i7, int i8, c6.e eVar, r rVar) throws IOException {
        Socket socket;
        int i9;
        Proxy proxyB = this.f6730s.b();
        c6.a aVarA = this.f6730s.a();
        Proxy.Type type = proxyB.type();
        if (type != null && ((i9 = g.f6735a[type.ordinal()]) == 1 || i9 == 2)) {
            socket = aVarA.j().createSocket();
            l.c(socket);
        } else {
            socket = new Socket(proxyB);
        }
        this.f6714c = socket;
        rVar.i(eVar, this.f6730s.d(), proxyB);
        socket.setSoTimeout(i8);
        try {
            l6.h.f8004c.g().f(socket, this.f6730s.d(), i7);
            try {
                this.f6719h = o.b(o.f(socket));
                this.f6720i = o.a(o.d(socket));
            } catch (NullPointerException e7) {
                if (l.a(e7.getMessage(), "throw with null exception")) {
                    throw new IOException(e7);
                }
            }
        } catch (ConnectException e8) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f6730s.d());
            connectException.initCause(e8);
            throw connectException;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void i(h6.b bVar) throws Throwable {
        c6.a aVarA = this.f6730s.a();
        SSLSocketFactory sSLSocketFactoryK = aVarA.k();
        SSLSocket sSLSocket = null;
        try {
            l.c(sSLSocketFactoryK);
            Socket socketCreateSocket = sSLSocketFactoryK.createSocket(this.f6714c, aVarA.l().h(), aVarA.l().l(), true);
            if (socketCreateSocket == null) {
                throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.SSLSocket");
            }
            SSLSocket sSLSocket2 = (SSLSocket) socketCreateSocket;
            try {
                c6.l lVarA = bVar.a(sSLSocket2);
                if (lVarA.h()) {
                    l6.h.f8004c.g().e(sSLSocket2, aVarA.l().h(), aVarA.f());
                }
                sSLSocket2.startHandshake();
                SSLSession sslSocketSession = sSLSocket2.getSession();
                s.a aVar = s.f3836e;
                l.d(sslSocketSession, "sslSocketSession");
                s sVarA = aVar.a(sslSocketSession);
                HostnameVerifier hostnameVerifierE = aVarA.e();
                l.c(hostnameVerifierE);
                if (hostnameVerifierE.verify(aVarA.l().h(), sslSocketSession)) {
                    c6.g gVarA = aVarA.a();
                    l.c(gVarA);
                    this.f6716e = new s(sVarA.e(), sVarA.a(), sVarA.c(), new b(gVarA, sVarA, aVarA));
                    gVarA.b(aVarA.l().h(), new c());
                    String strG = lVarA.h() ? l6.h.f8004c.g().g(sSLSocket2) : null;
                    this.f6715d = sSLSocket2;
                    this.f6719h = o.b(o.f(sSLSocket2));
                    this.f6720i = o.a(o.d(sSLSocket2));
                    this.f6717f = strG != null ? y.Companion.a(strG) : y.HTTP_1_1;
                    l6.h.f8004c.g().b(sSLSocket2);
                    return;
                }
                List<Certificate> listD = sVarA.d();
                if (!(!listD.isEmpty())) {
                    throw new SSLPeerUnverifiedException("Hostname " + aVarA.l().h() + " not verified (no certificates)");
                }
                Certificate certificate = listD.get(0);
                if (certificate == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                }
                X509Certificate x509Certificate = (X509Certificate) certificate;
                StringBuilder sb = new StringBuilder();
                sb.append("\n              |Hostname ");
                sb.append(aVarA.l().h());
                sb.append(" not verified:\n              |    certificate: ");
                sb.append(c6.g.f3709d.a(x509Certificate));
                sb.append("\n              |    DN: ");
                Principal subjectDN = x509Certificate.getSubjectDN();
                l.d(subjectDN, "cert.subjectDN");
                sb.append(subjectDN.getName());
                sb.append("\n              |    subjectAltNames: ");
                sb.append(o6.d.f8672a.a(x509Certificate));
                sb.append("\n              ");
                throw new SSLPeerUnverifiedException(y5.i.h(sb.toString(), null, 1, null));
            } catch (Throwable th) {
                th = th;
                sSLSocket = sSLSocket2;
                if (sSLSocket != null) {
                    l6.h.f8004c.g().b(sSLSocket);
                }
                if (sSLSocket != null) {
                    d6.b.j(sSLSocket);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private final void j(int i7, int i8, int i9, c6.e eVar, r rVar) throws IOException {
        z zVarL = l();
        u uVarI = zVarL.i();
        for (int i10 = 0; i10 < 21; i10++) {
            h(i7, i8, eVar, rVar);
            zVarL = k(i8, i9, zVarL, uVarI);
            if (zVarL == null) {
                return;
            }
            Socket socket = this.f6714c;
            if (socket != null) {
                d6.b.j(socket);
            }
            this.f6714c = null;
            this.f6720i = null;
            this.f6719h = null;
            rVar.g(eVar, this.f6730s.d(), this.f6730s.b(), null);
        }
    }

    private final z k(int i7, int i8, z zVar, u uVar) throws IOException {
        String str = "CONNECT " + d6.b.K(uVar, true) + " HTTP/1.1";
        while (true) {
            p6.g gVar = this.f6719h;
            l.c(gVar);
            p6.f fVar = this.f6720i;
            l.c(fVar);
            j6.b bVar = new j6.b(null, this, gVar, fVar);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            gVar.c().g(i7, timeUnit);
            fVar.c().g(i8, timeUnit);
            bVar.A(zVar.e(), str);
            bVar.d();
            b0.a aVarF = bVar.f(false);
            l.c(aVarF);
            b0 b0VarC = aVarF.r(zVar).c();
            bVar.z(b0VarC);
            int I = b0VarC.I();
            if (I == 200) {
                if (gVar.b().J() && fVar.b().J()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (I != 407) {
                throw new IOException("Unexpected response code for CONNECT: " + b0VarC.I());
            }
            z zVarA = this.f6730s.a().h().a(this.f6730s, b0VarC);
            if (zVarA == null) {
                throw new IOException("Failed to authenticate with proxy");
            }
            if (p.o("close", b0.U(b0VarC, "Connection", null, 2, null), true)) {
                return zVarA;
            }
            zVar = zVarA;
        }
    }

    private final z l() {
        z zVarB = new z.a().g(this.f6730s.a().l()).e("CONNECT", null).c("Host", d6.b.K(this.f6730s.a().l(), true)).c("Proxy-Connection", "Keep-Alive").c("User-Agent", "okhttp/4.9.2").b();
        z zVarA = this.f6730s.a().h().a(this.f6730s, new b0.a().r(zVarB).p(y.HTTP_1_1).g(407).m("Preemptive Authenticate").b(d6.b.f5962c).s(-1L).q(-1L).j("Proxy-Authenticate", "OkHttp-Preemptive").c());
        return zVarA != null ? zVarA : zVarB;
    }

    private final void m(h6.b bVar, int i7, c6.e eVar, r rVar) throws Throwable {
        if (this.f6730s.a().k() != null) {
            rVar.B(eVar);
            i(bVar);
            rVar.A(eVar, this.f6716e);
            if (this.f6717f == y.HTTP_2) {
                E(i7);
                return;
            }
            return;
        }
        List<y> listF = this.f6730s.a().f();
        y yVar = y.H2_PRIOR_KNOWLEDGE;
        if (!listF.contains(yVar)) {
            this.f6715d = this.f6714c;
            this.f6717f = y.HTTP_1_1;
        } else {
            this.f6715d = this.f6714c;
            this.f6717f = yVar;
            E(i7);
        }
    }

    public final void B(long j7) {
        this.f6728q = j7;
    }

    public final void C(boolean z6) {
        this.f6721j = z6;
    }

    public Socket D() {
        Socket socket = this.f6715d;
        l.c(socket);
        return socket;
    }

    public final synchronized void G(e call, IOException iOException) {
        l.e(call, "call");
        if (iOException instanceof n) {
            if (((n) iOException).f7483e == k6.b.REFUSED_STREAM) {
                int i7 = this.f6725n + 1;
                this.f6725n = i7;
                if (i7 > 1) {
                    this.f6721j = true;
                    this.f6723l++;
                }
            } else if (((n) iOException).f7483e != k6.b.CANCEL || !call.s()) {
                this.f6721j = true;
                this.f6723l++;
            }
        } else if (!v() || (iOException instanceof k6.a)) {
            this.f6721j = true;
            if (this.f6724m == 0) {
                if (iOException != null) {
                    g(call.j(), this.f6730s, iOException);
                }
                this.f6723l++;
            }
        }
    }

    @Override // k6.f.d
    public synchronized void a(k6.f connection, k6.m settings) {
        l.e(connection, "connection");
        l.e(settings, "settings");
        this.f6726o = settings.d();
    }

    @Override // k6.f.d
    public void b(k6.i stream) {
        l.e(stream, "stream");
        stream.d(k6.b.REFUSED_STREAM, null);
    }

    public final void d() {
        Socket socket = this.f6714c;
        if (socket != null) {
            d6.b.j(socket);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x014c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(int r17, int r18, int r19, int r20, boolean r21, c6.e r22, c6.r r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h6.f.f(int, int, int, int, boolean, c6.e, c6.r):void");
    }

    public final void g(x client, d0 failedRoute, IOException failure) {
        l.e(client, "client");
        l.e(failedRoute, "failedRoute");
        l.e(failure, "failure");
        if (failedRoute.b().type() != Proxy.Type.DIRECT) {
            c6.a aVarA = failedRoute.a();
            aVarA.i().connectFailed(aVarA.l().q(), failedRoute.b().address(), failure);
        }
        client.r().b(failedRoute);
    }

    public final List<Reference<e>> n() {
        return this.f6727p;
    }

    public final long o() {
        return this.f6728q;
    }

    public final boolean p() {
        return this.f6721j;
    }

    public final int q() {
        return this.f6723l;
    }

    public s r() {
        return this.f6716e;
    }

    public final synchronized void s() {
        this.f6724m++;
    }

    public final boolean t(c6.a address, List<d0> list) {
        l.e(address, "address");
        if (d6.b.f5967h && !Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        if (this.f6727p.size() >= this.f6726o || this.f6721j || !this.f6730s.a().d(address)) {
            return false;
        }
        if (l.a(address.l().h(), z().a().l().h())) {
            return true;
        }
        if (this.f6718g == null || list == null || !A(list) || address.e() != o6.d.f8672a || !F(address.l())) {
            return false;
        }
        try {
            c6.g gVarA = address.a();
            l.c(gVarA);
            String strH = address.l().h();
            s sVarR = r();
            l.c(sVarR);
            gVarA.a(strH, sVarR.d());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public String toString() {
        Object objA;
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.f6730s.a().l().h());
        sb.append(':');
        sb.append(this.f6730s.a().l().l());
        sb.append(',');
        sb.append(" proxy=");
        sb.append(this.f6730s.b());
        sb.append(" hostAddress=");
        sb.append(this.f6730s.d());
        sb.append(" cipherSuite=");
        s sVar = this.f6716e;
        if (sVar == null || (objA = sVar.a()) == null) {
            objA = "none";
        }
        sb.append(objA);
        sb.append(" protocol=");
        sb.append(this.f6717f);
        sb.append('}');
        return sb.toString();
    }

    public final boolean u(boolean z6) {
        long j7;
        if (d6.b.f5967h && Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST NOT hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        long jNanoTime = System.nanoTime();
        Socket socket = this.f6714c;
        l.c(socket);
        Socket socket2 = this.f6715d;
        l.c(socket2);
        p6.g gVar = this.f6719h;
        l.c(gVar);
        if (socket.isClosed() || socket2.isClosed() || socket2.isInputShutdown() || socket2.isOutputShutdown()) {
            return false;
        }
        k6.f fVar = this.f6718g;
        if (fVar != null) {
            return fVar.u0(jNanoTime);
        }
        synchronized (this) {
            j7 = jNanoTime - this.f6728q;
        }
        if (j7 < 10000000000L || !z6) {
            return true;
        }
        return d6.b.B(socket2, gVar);
    }

    public final boolean v() {
        return this.f6718g != null;
    }

    public final i6.d w(x client, i6.g chain) throws SocketException {
        l.e(client, "client");
        l.e(chain, "chain");
        Socket socket = this.f6715d;
        l.c(socket);
        p6.g gVar = this.f6719h;
        l.c(gVar);
        p6.f fVar = this.f6720i;
        l.c(fVar);
        k6.f fVar2 = this.f6718g;
        if (fVar2 != null) {
            return new k6.g(client, this, chain, fVar2);
        }
        socket.setSoTimeout(chain.k());
        p6.b0 b0VarC = gVar.c();
        long jH = chain.h();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        b0VarC.g(jH, timeUnit);
        fVar.c().g(chain.j(), timeUnit);
        return new j6.b(client, this, gVar, fVar);
    }

    public final synchronized void x() {
        this.f6722k = true;
    }

    public final synchronized void y() {
        this.f6721j = true;
    }

    public d0 z() {
        return this.f6730s;
    }
}
