package f.b0.f;

import f.a0;
import f.b0.i.e;
import f.b0.i.g;
import f.i;
import f.j;
import f.k;
import f.p;
import f.u;
import f.w;
import f.y;
import g.q;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: compiled from: RealConnection.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends e.i implements i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f5045b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a0 f5046c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Socket f5047d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Socket f5048e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public p f5049f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Protocol f5050g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public f.b0.i.e f5051h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public g.e f5052i;
    public g.d j;
    public boolean k;
    public int l;
    public int m = 1;
    public final List<Reference<f>> n = new ArrayList();
    public long o = Long.MAX_VALUE;

    public c(j jVar, a0 a0Var) {
        this.f5045b = jVar;
        this.f5046c = a0Var;
    }

    @Override // f.i
    public a0 a() {
        return this.f5046c;
    }

    @Override // f.b0.i.e.i
    public void b(f.b0.i.e eVar) {
        synchronized (this.f5045b) {
            this.m = eVar.Q();
        }
    }

    @Override // f.b0.i.e.i
    public void c(g gVar) throws IOException {
        gVar.d(ErrorCode.REFUSED_STREAM);
    }

    public void d() {
        f.b0.c.d(this.f5047d);
    }

    public void e(int i2, int i3, int i4, boolean z) throws Throwable {
        if (this.f5050g != null) {
            throw new IllegalStateException("already connected");
        }
        List<k> listB = this.f5046c.a().b();
        b bVar = new b(listB);
        if (this.f5046c.a().j() == null) {
            if (!listB.contains(k.f5342d)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String strL = this.f5046c.a().k().l();
            if (!f.b0.j.e.h().k(strL)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + strL + " not permitted by network security policy"));
            }
        }
        RouteException routeException = null;
        do {
            try {
                if (this.f5046c.c()) {
                    h(i2, i3, i4);
                } else {
                    f(i2, i3);
                }
                k(bVar);
                if (this.f5051h != null) {
                    synchronized (this.f5045b) {
                        this.m = this.f5051h.Q();
                    }
                    return;
                }
                return;
            } catch (IOException e2) {
                f.b0.c.d(this.f5048e);
                f.b0.c.d(this.f5047d);
                this.f5048e = null;
                this.f5047d = null;
                this.f5052i = null;
                this.j = null;
                this.f5049f = null;
                this.f5050g = null;
                this.f5051h = null;
                if (routeException == null) {
                    routeException = new RouteException(e2);
                } else {
                    routeException.addConnectException(e2);
                }
                if (!z) {
                    throw routeException;
                }
            }
        } while (bVar.b(e2));
        throw routeException;
    }

    public final void f(int i2, int i3) throws IOException {
        Proxy proxyB = this.f5046c.b();
        Socket socketCreateSocket = (proxyB.type() == Proxy.Type.DIRECT || proxyB.type() == Proxy.Type.HTTP) ? this.f5046c.a().i().createSocket() : new Socket(proxyB);
        this.f5047d = socketCreateSocket;
        socketCreateSocket.setSoTimeout(i3);
        try {
            f.b0.j.e.h().f(this.f5047d, this.f5046c.d(), i2);
            this.f5052i = g.j.b(g.j.i(this.f5047d));
            this.j = g.j.a(g.j.e(this.f5047d));
        } catch (ConnectException e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f5046c.d());
            connectException.initCause(e2);
            throw connectException;
        }
    }

    public final void g(b bVar) throws Throwable {
        f.a aVarA = this.f5046c.a();
        SSLSocket sSLSocket = null;
        try {
            try {
                SSLSocket sSLSocket2 = (SSLSocket) aVarA.j().createSocket(this.f5047d, aVarA.k().l(), aVarA.k().x(), true);
                try {
                    k kVarA = bVar.a(sSLSocket2);
                    if (kVarA.g()) {
                        f.b0.j.e.h().e(sSLSocket2, aVarA.k().l(), aVarA.e());
                    }
                    sSLSocket2.startHandshake();
                    p pVarB = p.b(sSLSocket2.getSession());
                    if (aVarA.d().verify(aVarA.k().l(), sSLSocket2.getSession())) {
                        aVarA.a().a(aVarA.k().l(), pVarB.c());
                        String strI = kVarA.g() ? f.b0.j.e.h().i(sSLSocket2) : null;
                        this.f5048e = sSLSocket2;
                        this.f5052i = g.j.b(g.j.i(sSLSocket2));
                        this.j = g.j.a(g.j.e(this.f5048e));
                        this.f5049f = pVarB;
                        this.f5050g = strI != null ? Protocol.get(strI) : Protocol.HTTP_1_1;
                        f.b0.j.e.h().a(sSLSocket2);
                        return;
                    }
                    X509Certificate x509Certificate = (X509Certificate) pVarB.c().get(0);
                    throw new SSLPeerUnverifiedException("Hostname " + aVarA.k().l() + " not verified:\n    certificate: " + f.g.c(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + f.b0.k.d.a(x509Certificate));
                } catch (AssertionError e2) {
                    e = e2;
                    if (!f.b0.c.t(e)) {
                        throw e;
                    }
                    throw new IOException(e);
                } catch (Throwable th) {
                    th = th;
                    sSLSocket = sSLSocket2;
                    if (sSLSocket != null) {
                        f.b0.j.e.h().a(sSLSocket);
                    }
                    f.b0.c.d(sSLSocket);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (AssertionError e3) {
            e = e3;
        }
    }

    public final void h(int i2, int i3, int i4) throws IOException {
        w wVarJ = j();
        HttpUrl httpUrlH = wVarJ.h();
        int i5 = 0;
        while (true) {
            i5++;
            if (i5 > 21) {
                throw new ProtocolException("Too many tunnel connections attempted: 21");
            }
            f(i2, i3);
            wVarJ = i(i3, i4, wVarJ, httpUrlH);
            if (wVarJ == null) {
                return;
            }
            f.b0.c.d(this.f5047d);
            this.f5047d = null;
            this.j = null;
            this.f5052i = null;
        }
    }

    public final w i(int i2, int i3, w wVar, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + f.b0.c.m(httpUrl, true) + " HTTP/1.1";
        while (true) {
            f.b0.h.a aVar = new f.b0.h.a(null, null, this.f5052i, this.j);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            this.f5052i.b().g(i2, timeUnit);
            this.j.b().g(i3, timeUnit);
            aVar.o(wVar.d(), str);
            aVar.a();
            y yVarC = aVar.f(false).o(wVar).c();
            long jB = f.b0.g.e.b(yVarC);
            if (jB == -1) {
                jB = 0;
            }
            g.p pVarL = aVar.l(jB);
            f.b0.c.u(pVarL, Integer.MAX_VALUE, timeUnit);
            pVarL.close();
            int iO = yVarC.O();
            if (iO == 200) {
                if (this.f5052i.a().D() && this.j.a().D()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (iO != 407) {
                throw new IOException("Unexpected response code for CONNECT: " + yVarC.O());
            }
            w wVarA = this.f5046c.a().g().a(this.f5046c, yVarC);
            if (wVarA == null) {
                throw new IOException("Failed to authenticate with proxy");
            }
            if ("close".equalsIgnoreCase(yVarC.Q("Connection"))) {
                return wVarA;
            }
            wVar = wVarA;
        }
    }

    public final w j() {
        return new w.a().j(this.f5046c.a().k()).d("Host", f.b0.c.m(this.f5046c.a().k(), true)).d("Proxy-Connection", "Keep-Alive").d("User-Agent", f.b0.d.a()).b();
    }

    public final void k(b bVar) throws Throwable {
        if (this.f5046c.a().j() == null) {
            this.f5050g = Protocol.HTTP_1_1;
            this.f5048e = this.f5047d;
            return;
        }
        g(bVar);
        if (this.f5050g == Protocol.HTTP_2) {
            this.f5048e.setSoTimeout(0);
            f.b0.i.e eVarA = new e.h(true).c(this.f5048e, this.f5046c.a().k().l(), this.f5052i, this.j).b(this).a();
            this.f5051h = eVarA;
            eVarA.b0();
        }
    }

    public p l() {
        return this.f5049f;
    }

    public boolean m(f.a aVar) {
        return this.n.size() < this.m && aVar.equals(a().a()) && !this.k;
    }

    public boolean n(boolean z) {
        if (this.f5048e.isClosed() || this.f5048e.isInputShutdown() || this.f5048e.isOutputShutdown()) {
            return false;
        }
        if (this.f5051h != null) {
            return !r0.P();
        }
        if (z) {
            try {
                int soTimeout = this.f5048e.getSoTimeout();
                try {
                    this.f5048e.setSoTimeout(1);
                    return !this.f5052i.D();
                } finally {
                    this.f5048e.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    public boolean o() {
        return this.f5051h != null;
    }

    public f.b0.g.c p(u uVar, f fVar) throws SocketException {
        if (this.f5051h != null) {
            return new f.b0.i.d(uVar, fVar, this.f5051h);
        }
        this.f5048e.setSoTimeout(uVar.t());
        q qVarB = this.f5052i.b();
        long jT = uVar.t();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        qVarB.g(jT, timeUnit);
        this.j.b().g(uVar.B(), timeUnit);
        return new f.b0.h.a(uVar, fVar, this.f5052i, this.j);
    }

    public Socket q() {
        return this.f5048e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.f5046c.a().k().l());
        sb.append(":");
        sb.append(this.f5046c.a().k().x());
        sb.append(", proxy=");
        sb.append(this.f5046c.b());
        sb.append(" hostAddress=");
        sb.append(this.f5046c.d());
        sb.append(" cipherSuite=");
        p pVar = this.f5049f;
        sb.append(pVar != null ? pVar.a() : "none");
        sb.append(" protocol=");
        sb.append(this.f5050g);
        sb.append('}');
        return sb.toString();
    }
}
