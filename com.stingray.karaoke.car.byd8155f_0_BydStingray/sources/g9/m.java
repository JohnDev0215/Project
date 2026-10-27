package g9;

import A.C0024l;
import a.AbstractC0904a;
import android.hardware.bydauto.properties.CarSettings;
import c9.C1040a;
import c9.C1049j;
import c9.D;
import c9.G;
import c9.H;
import c9.I;
import c9.J;
import c9.q;
import c9.t;
import c9.w;
import i8.C1417b;
import j9.u;
import j9.v;
import j9.y;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import n7.AbstractC1998m;
import r9.AbstractC2312b;
import r9.C;
import r9.C2322l;

/* JADX INFO: loaded from: classes.dex */
public final class m extends j9.g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final J f15076b;
    public Socket c;
    public Socket d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public t f15077e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public D f15078f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public j9.m f15079g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public r9.D f15080h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C f15081i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f15082j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f15083k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f15084m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f15085n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f15086p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f15087q;

    public m(n nVar, J j3) {
        A7.m.f(nVar, "connectionPool");
        A7.m.f(j3, "route");
        this.f15076b = j3;
        this.o = 1;
        this.f15086p = new ArrayList();
        this.f15087q = Long.MAX_VALUE;
    }

    public static void d(c9.C c, J j3, IOException iOException) {
        A7.m.f(c, "client");
        A7.m.f(j3, "failedRoute");
        A7.m.f(iOException, "failure");
        if (j3.f12529b.type() != Proxy.Type.DIRECT) {
            C1040a c1040a = j3.f12528a;
            c1040a.f12539g.connectFailed(c1040a.f12540h.h(), j3.f12529b.address(), iOException);
        }
        e7.f fVar = c.f12478L;
        synchronized (fVar) {
            ((LinkedHashSet) fVar.f14480m).add(j3);
        }
    }

    @Override // j9.g
    public final synchronized void a(j9.m mVar, y yVar) {
        A7.m.f(mVar, "connection");
        A7.m.f(yVar, CarSettings.UserTableData.TABLE_NAME_SETTINGS);
        this.o = (yVar.f17598a & 16) != 0 ? yVar.f17599b[4] : Integer.MAX_VALUE;
    }

    @Override // j9.g
    public final void b(u uVar) {
        uVar.c(8, null);
    }

    public final void c(int i7, int i10, int i11, boolean z6, j jVar, q qVar) throws Throwable {
        J j3;
        A7.m.f(jVar, "call");
        A7.m.f(qVar, "eventListener");
        if (this.f15078f != null) {
            throw new IllegalStateException("already connected");
        }
        List list = this.f15076b.f12528a.f12542j;
        b bVar = new b(list);
        C1040a c1040a = this.f15076b.f12528a;
        if (c1040a.c == null) {
            if (!list.contains(c9.n.f12590f)) {
                throw new o(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String str = this.f15076b.f12528a.f12540h.d;
            l9.o oVar = l9.o.f18874a;
            if (!l9.o.f18874a.h(str)) {
                throw new o(new UnknownServiceException(android.hardware.bydauto.ac.tsimpl.a.i("CLEARTEXT communication to ", str, " not permitted by network security policy")));
            }
        } else if (c1040a.f12541i.contains(D.H2_PRIOR_KNOWLEDGE)) {
            throw new o(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
        }
        o oVar2 = null;
        do {
            try {
                J j10 = this.f15076b;
                if (j10.f12528a.c != null && j10.f12529b.type() == Proxy.Type.HTTP) {
                    f(i7, i10, i11, jVar, qVar);
                    if (this.c == null) {
                        j3 = this.f15076b;
                        if (j3.f12528a.c == null && j3.f12529b.type() == Proxy.Type.HTTP && this.c == null) {
                            throw new o(new ProtocolException("Too many tunnel connections attempted: 21"));
                        }
                        this.f15087q = System.nanoTime();
                        return;
                    }
                } else {
                    try {
                        e(i7, i10, jVar, qVar);
                    } catch (IOException e10) {
                        e = e10;
                        Socket socket = this.d;
                        if (socket != null) {
                            d9.b.d(socket);
                        }
                        Socket socket2 = this.c;
                        if (socket2 != null) {
                            d9.b.d(socket2);
                        }
                        this.d = null;
                        this.c = null;
                        this.f15080h = null;
                        this.f15081i = null;
                        this.f15077e = null;
                        this.f15078f = null;
                        this.f15079g = null;
                        this.o = 1;
                        J j11 = this.f15076b;
                        qVar.i(jVar, j11.c, j11.f12529b, e);
                        if (oVar2 == null) {
                            oVar2 = new o(e);
                        } else {
                            h6.q.l(oVar2.l, e);
                            oVar2.f15090m = e;
                        }
                        if (!z6) {
                            throw oVar2;
                        }
                        bVar.d = true;
                        if (!bVar.c) {
                            throw oVar2;
                        }
                        if (e instanceof ProtocolException) {
                            throw oVar2;
                        }
                        if (e instanceof InterruptedIOException) {
                            throw oVar2;
                        }
                        if ((e instanceof SSLHandshakeException) && (e.getCause() instanceof CertificateException)) {
                            throw oVar2;
                        }
                        if (e instanceof SSLPeerUnverifiedException) {
                            throw oVar2;
                        }
                    }
                }
                g(bVar, jVar, qVar);
                J j12 = this.f15076b;
                qVar.h(jVar, j12.c, j12.f12529b, this.f15078f);
                j3 = this.f15076b;
                if (j3.f12528a.c == null) {
                }
                this.f15087q = System.nanoTime();
                return;
            } catch (IOException e11) {
                e = e11;
            }
        } while (e instanceof SSLException);
        throw oVar2;
    }

    public final void e(int i7, int i10, j jVar, q qVar) throws IOException {
        Socket socketCreateSocket;
        J j3 = this.f15076b;
        Proxy proxy = j3.f12529b;
        C1040a c1040a = j3.f12528a;
        Proxy.Type type = proxy.type();
        int i11 = type == null ? -1 : k.f15073a[type.ordinal()];
        if (i11 == 1 || i11 == 2) {
            socketCreateSocket = c1040a.f12536b.createSocket();
            A7.m.c(socketCreateSocket);
        } else {
            socketCreateSocket = new Socket(proxy);
        }
        this.c = socketCreateSocket;
        qVar.j(jVar, this.f15076b.c, proxy);
        socketCreateSocket.setSoTimeout(i10);
        try {
            l9.o oVar = l9.o.f18874a;
            l9.o.f18874a.e(socketCreateSocket, this.f15076b.c, i7);
            try {
                this.f15080h = AbstractC2312b.c(AbstractC2312b.k(socketCreateSocket));
                this.f15081i = AbstractC2312b.b(AbstractC2312b.h(socketCreateSocket));
            } catch (NullPointerException e10) {
                if (A7.m.a(e10.getMessage(), "throw with null exception")) {
                    throw new IOException(e10);
                }
            }
        } catch (ConnectException e11) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f15076b.c);
            connectException.initCause(e11);
            throw connectException;
        }
    }

    public final void f(int i7, int i10, int i11, j jVar, q qVar) throws IOException {
        N0.q qVar2 = new N0.q();
        J j3 = this.f15076b;
        qVar2.S(j3.f12528a.f12540h);
        qVar2.J("CONNECT", null);
        C1040a c1040a = j3.f12528a;
        qVar2.G("Host", d9.b.w(c1040a.f12540h, true));
        qVar2.G("Proxy-Connection", "Keep-Alive");
        qVar2.G("User-Agent", "okhttp/4.12.0");
        H0.b bVarR = qVar2.r();
        A7.D d = new A7.D(5, false);
        D d10 = D.HTTP_1_1;
        I i12 = d9.b.c;
        AbstractC0904a.t("Proxy-Authenticate");
        AbstractC0904a.v("OkHttp-Preemptive", "Proxy-Authenticate");
        d.x("Proxy-Authenticate");
        d.i("Proxy-Authenticate", "OkHttp-Preemptive");
        new H(bVarR, d10, "Preemptive Authenticate", 407, null, d.m(), i12, null, null, null, -1L, -1L, null);
        c1040a.f12538f.getClass();
        e(i7, i10, jVar, qVar);
        String str = "CONNECT " + d9.b.w((w) bVarR.f2765m, true) + " HTTP/1.1";
        r9.D d11 = this.f15080h;
        A7.m.c(d11);
        C c = this.f15081i;
        A7.m.c(c);
        C1417b c1417b = new C1417b(null, this, d11, c);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        d11.l.e().g(i10, timeUnit);
        c.l.e().g(i11, timeUnit);
        c1417b.j((c9.u) bVarR.o, str);
        c1417b.c();
        G g7 = c1417b.g(false);
        A7.m.c(g7);
        g7.f12504a = bVarR;
        H hA = g7.a();
        long jK = d9.b.k(hA);
        if (jK != -1) {
            i9.d dVarI = c1417b.i(jK);
            d9.b.u(dVarI, Integer.MAX_VALUE, timeUnit);
            dVarI.close();
        }
        int i13 = hA.o;
        if (i13 != 200) {
            if (i13 != 407) {
                throw new IOException(android.hardware.bydauto.ac.tsimpl.a.e(i13, "Unexpected response code for CONNECT: "));
            }
            c1040a.f12538f.getClass();
            throw new IOException("Failed to authenticate with proxy");
        }
        if (!d11.f21897m.j() || !c.f21895m.j()) {
            throw new IOException("TLS tunnel buffered too many bytes!");
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
    public final void g(b bVar, j jVar, q qVar) throws Throwable {
        C1040a c1040a = this.f15076b.f12528a;
        SSLSocketFactory sSLSocketFactory = c1040a.c;
        D dX = D.HTTP_1_1;
        if (sSLSocketFactory == null) {
            List list = c1040a.f12541i;
            D d = D.H2_PRIOR_KNOWLEDGE;
            if (!list.contains(d)) {
                this.d = this.c;
                this.f15078f = dX;
                return;
            } else {
                this.d = this.c;
                this.f15078f = d;
                l();
                return;
            }
        }
        qVar.C(jVar);
        C1040a c1040a2 = this.f15076b.f12528a;
        SSLSocketFactory sSLSocketFactory2 = c1040a2.c;
        SSLSocket sSLSocket = null;
        String strF = null;
        try {
            A7.m.c(sSLSocketFactory2);
            Socket socket = this.c;
            w wVar = c1040a2.f12540h;
            Socket socketCreateSocket = sSLSocketFactory2.createSocket(socket, wVar.d, wVar.f12621e, true);
            A7.m.d(socketCreateSocket, "null cannot be cast to non-null type javax.net.ssl.SSLSocket");
            SSLSocket sSLSocket2 = (SSLSocket) socketCreateSocket;
            try {
                c9.n nVarA = bVar.a(sSLSocket2);
                if (nVarA.f12592b) {
                    l9.o oVar = l9.o.f18874a;
                    l9.o.f18874a.d(sSLSocket2, c1040a2.f12540h.d, c1040a2.f12541i);
                }
                sSLSocket2.startHandshake();
                SSLSession session = sSLSocket2.getSession();
                A7.m.e(session, "sslSocketSession");
                t tVarA = H7.H.A(session);
                HostnameVerifier hostnameVerifier = c1040a2.d;
                A7.m.c(hostnameVerifier);
                if (hostnameVerifier.verify(c1040a2.f12540h.d, session)) {
                    C1049j c1049j = c1040a2.f12537e;
                    A7.m.c(c1049j);
                    this.f15077e = new t(tVarA.f12609a, tVarA.f12610b, tVarA.c, new C0024l(c1049j, tVarA, c1040a2, 11));
                    A7.m.f(c1040a2.f12540h.d, "hostname");
                    Iterator it = c1049j.f12569a.iterator();
                    if (it.hasNext()) {
                        it.next().getClass();
                        throw new ClassCastException();
                    }
                    if (nVarA.f12592b) {
                        l9.o oVar2 = l9.o.f18874a;
                        strF = l9.o.f18874a.f(sSLSocket2);
                    }
                    this.d = sSLSocket2;
                    this.f15080h = AbstractC2312b.c(AbstractC2312b.k(sSLSocket2));
                    this.f15081i = AbstractC2312b.b(AbstractC2312b.h(sSLSocket2));
                    if (strF != null) {
                        dX = com.bumptech.glide.c.X(strF);
                    }
                    this.f15078f = dX;
                    l9.o oVar3 = l9.o.f18874a;
                    l9.o.f18874a.a(sSLSocket2);
                    qVar.B(jVar, this.f15077e);
                    if (this.f15078f == D.HTTP_2) {
                        l();
                        return;
                    }
                    return;
                }
                List listA = tVarA.a();
                if (listA.isEmpty()) {
                    throw new SSLPeerUnverifiedException("Hostname " + c1040a2.f12540h.d + " not verified (no certificates)");
                }
                Object obj = listA.get(0);
                A7.m.d(obj, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                X509Certificate x509Certificate = (X509Certificate) obj;
                StringBuilder sb = new StringBuilder("\n              |Hostname ");
                sb.append(c1040a2.f12540h.d);
                sb.append(" not verified:\n              |    certificate: ");
                C1049j c1049j2 = C1049j.c;
                StringBuilder sb2 = new StringBuilder("sha256/");
                C2322l c2322l = C2322l.o;
                byte[] encoded = x509Certificate.getPublicKey().getEncoded();
                A7.m.e(encoded, "publicKey.encoded");
                sb2.append(m6.b.G0(encoded).c("SHA-256").a());
                sb.append(sb2.toString());
                sb.append("\n              |    DN: ");
                sb.append(x509Certificate.getSubjectDN().getName());
                sb.append("\n              |    subjectAltNames: ");
                sb.append(AbstractC1998m.n0(p9.c.a(x509Certificate, 7), p9.c.a(x509Certificate, 2)));
                sb.append("\n              ");
                throw new SSLPeerUnverifiedException(Q8.i.r0(sb.toString()));
            } catch (Throwable th) {
                th = th;
                sSLSocket = sSLSocket2;
                if (sSLSocket != null) {
                    l9.o oVar4 = l9.o.f18874a;
                    l9.o.f18874a.a(sSLSocket);
                }
                if (sSLSocket != null) {
                    d9.b.d(sSLSocket);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean h(c9.C1040a r10, java.util.ArrayList r11) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g9.m.h(c9.a, java.util.ArrayList):boolean");
    }

    public final boolean i(boolean z6) {
        long j3;
        byte[] bArr = d9.b.f13645a;
        long jNanoTime = System.nanoTime();
        Socket socket = this.c;
        A7.m.c(socket);
        Socket socket2 = this.d;
        A7.m.c(socket2);
        r9.D d = this.f15080h;
        A7.m.c(d);
        if (socket.isClosed() || socket2.isClosed() || socket2.isInputShutdown() || socket2.isOutputShutdown()) {
            return false;
        }
        j9.m mVar = this.f15079g;
        if (mVar != null) {
            synchronized (mVar) {
                if (mVar.f17551q) {
                    return false;
                }
                if (mVar.f17559y < mVar.f17558x) {
                    if (jNanoTime >= mVar.f17560z) {
                        return false;
                    }
                }
                return true;
            }
        }
        synchronized (this) {
            j3 = jNanoTime - this.f15087q;
        }
        if (j3 < 10000000000L || !z6) {
            return true;
        }
        try {
            int soTimeout = socket2.getSoTimeout();
            try {
                socket2.setSoTimeout(1);
                boolean z10 = !d.b();
                socket2.setSoTimeout(soTimeout);
                return z10;
            } catch (Throwable th) {
                socket2.setSoTimeout(soTimeout);
                throw th;
            }
        } catch (SocketTimeoutException unused) {
            return true;
        } catch (IOException unused2) {
            return false;
        }
    }

    public final h9.c j(c9.C c, F.u uVar) throws SocketException {
        A7.m.f(c, "client");
        Socket socket = this.d;
        A7.m.c(socket);
        r9.D d = this.f15080h;
        A7.m.c(d);
        C c10 = this.f15081i;
        A7.m.c(c10);
        j9.m mVar = this.f15079g;
        if (mVar != null) {
            return new j9.n(c, this, uVar, mVar);
        }
        int i7 = uVar.d;
        socket.setSoTimeout(i7);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        d.l.e().g(i7, timeUnit);
        c10.l.e().g(uVar.f1960e, timeUnit);
        return new C1417b(c, this, d, c10);
    }

    public final synchronized void k() {
        this.f15082j = true;
    }

    public final void l() throws SocketException {
        Socket socket = this.d;
        A7.m.c(socket);
        r9.D d = this.f15080h;
        A7.m.c(d);
        C c = this.f15081i;
        A7.m.c(c);
        socket.setSoTimeout(0);
        f9.c cVar = f9.c.f14651h;
        H0.b bVar = new H0.b(cVar);
        String str = this.f15076b.f12528a.f12540h.d;
        A7.m.f(str, "peerName");
        bVar.f2766n = socket;
        String str2 = d9.b.f13650h + ' ' + str;
        A7.m.f(str2, "<set-?>");
        bVar.o = str2;
        bVar.f2767p = d;
        bVar.f2768q = c;
        bVar.f2769r = this;
        j9.m mVar = new j9.m(bVar);
        this.f15079g = mVar;
        y yVar = j9.m.f17538K;
        this.o = (yVar.f17598a & 16) != 0 ? yVar.f17599b[4] : Integer.MAX_VALUE;
        v vVar = mVar.f17545H;
        synchronized (vVar) {
            try {
                if (vVar.o) {
                    throw new IOException("closed");
                }
                Logger logger = v.f17591q;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(d9.b.i(">> CONNECTION " + j9.e.f17518a.e(), new Object[0]));
                }
                vVar.l.A(j9.e.f17518a);
                vVar.l.flush();
            } finally {
            }
        }
        v vVar2 = mVar.f17545H;
        y yVar2 = mVar.f17539A;
        synchronized (vVar2) {
            try {
                A7.m.f(yVar2, CarSettings.UserTableData.TABLE_NAME_SETTINGS);
                if (vVar2.o) {
                    throw new IOException("closed");
                }
                vVar2.i(0, Integer.bitCount(yVar2.f17598a) * 6, 4, 0);
                int i7 = 0;
                while (i7 < 10) {
                    boolean z6 = true;
                    if (((1 << i7) & yVar2.f17598a) == 0) {
                        z6 = false;
                    }
                    if (z6) {
                        int i10 = i7 != 4 ? i7 != 7 ? i7 : 4 : 3;
                        C c10 = vVar2.l;
                        if (c10.f21896n) {
                            throw new IllegalStateException("closed");
                        }
                        c10.f21895m.b0(i10);
                        c10.b();
                        vVar2.l.g(yVar2.f17599b[i7]);
                    }
                    i7++;
                }
                vVar2.l.flush();
            } finally {
            }
        }
        if (mVar.f17539A.a() != 65535) {
            mVar.f17545H.q(r9 - 65535, 0);
        }
        cVar.e().c(new e9.f(mVar.f17549n, mVar.f17546I, 1), 0L);
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Connection{");
        J j3 = this.f15076b;
        sb.append(j3.f12528a.f12540h.d);
        sb.append(':');
        sb.append(j3.f12528a.f12540h.f12621e);
        sb.append(", proxy=");
        sb.append(j3.f12529b);
        sb.append(" hostAddress=");
        sb.append(j3.c);
        sb.append(" cipherSuite=");
        t tVar = this.f15077e;
        if (tVar == null || (obj = tVar.f12610b) == null) {
            obj = "none";
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.f15078f);
        sb.append('}');
        return sb.toString();
    }
}
