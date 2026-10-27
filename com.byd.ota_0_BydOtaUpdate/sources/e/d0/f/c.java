package e.d0.f;

import e.a0;
import e.c0;
import e.d0.i.e;
import e.d0.i.g;
import e.i;
import e.j;
import e.k;
import e.r;
import e.w;
import e.y;
import f.q;
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
import javax.annotation.Nullable;
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
    public final j f3366b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c0 f3367c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Socket f3368d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Socket f3369e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public r f3370f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Protocol f3371g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public e.d0.i.e f3372h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f.e f3373i;
    public f.d j;
    public boolean k;
    public int l;
    public int m = 1;
    public final List<Reference<f>> n = new ArrayList();
    public long o = Long.MAX_VALUE;

    public c(j jVar, c0 c0Var) {
        this.f3366b = jVar;
        this.f3367c = c0Var;
    }

    @Override // e.i
    public Protocol a() {
        return this.f3371g;
    }

    @Override // e.i
    public c0 b() {
        return this.f3367c;
    }

    @Override // e.d0.i.e.i
    public void c(e.d0.i.e eVar) {
        synchronized (this.f3366b) {
            this.m = eVar.H();
        }
    }

    @Override // e.d0.i.e.i
    public void d(g gVar) throws IOException {
        gVar.d(ErrorCode.REFUSED_STREAM);
    }

    public void e() {
        e.d0.c.d(this.f3368d);
    }

    public void f(int i2, int i3, int i4, boolean z) throws Throwable {
        if (this.f3371g != null) {
            throw new IllegalStateException("already connected");
        }
        List<k> listB = this.f3367c.a().b();
        b bVar = new b(listB);
        if (this.f3367c.a().k() == null) {
            if (!listB.contains(k.f3596d)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String strL = this.f3367c.a().l().l();
            if (!e.d0.j.e.h().k(strL)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + strL + " not permitted by network security policy"));
            }
        }
        RouteException routeException = null;
        do {
            try {
                if (this.f3367c.c()) {
                    i(i2, i3, i4);
                } else {
                    g(i2, i3);
                }
                l(bVar);
                if (this.f3372h != null) {
                    synchronized (this.f3366b) {
                        this.m = this.f3372h.H();
                    }
                    return;
                }
                return;
            } catch (IOException e2) {
                e.d0.c.d(this.f3369e);
                e.d0.c.d(this.f3368d);
                this.f3369e = null;
                this.f3368d = null;
                this.f3373i = null;
                this.j = null;
                this.f3370f = null;
                this.f3371g = null;
                this.f3372h = null;
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

    public final void g(int i2, int i3) throws IOException {
        Proxy proxyB = this.f3367c.b();
        Socket socketCreateSocket = (proxyB.type() == Proxy.Type.DIRECT || proxyB.type() == Proxy.Type.HTTP) ? this.f3367c.a().j().createSocket() : new Socket(proxyB);
        this.f3368d = socketCreateSocket;
        socketCreateSocket.setSoTimeout(i3);
        try {
            e.d0.j.e.h().f(this.f3368d, this.f3367c.d(), i2);
            try {
                this.f3373i = f.k.b(f.k.i(this.f3368d));
                this.j = f.k.a(f.k.e(this.f3368d));
            } catch (NullPointerException e2) {
                if ("throw with null exception".equals(e2.getMessage())) {
                    throw new IOException(e2);
                }
            }
        } catch (ConnectException e3) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f3367c.d());
            connectException.initCause(e3);
            throw connectException;
        }
    }

    public final void h(b bVar) throws Throwable {
        SSLSocket sSLSocket;
        e.a aVarA = this.f3367c.a();
        SSLSocket sSLSocket2 = null;
        try {
            try {
                sSLSocket = (SSLSocket) aVarA.k().createSocket(this.f3368d, aVarA.l().l(), aVarA.l().y(), true);
            } catch (AssertionError e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            k kVarA = bVar.a(sSLSocket);
            if (kVarA.f()) {
                e.d0.j.e.h().e(sSLSocket, aVarA.l().l(), aVarA.f());
            }
            sSLSocket.startHandshake();
            r rVarB = r.b(sSLSocket.getSession());
            if (aVarA.e().verify(aVarA.l().l(), sSLSocket.getSession())) {
                aVarA.a().a(aVarA.l().l(), rVarB.c());
                String strI = kVarA.f() ? e.d0.j.e.h().i(sSLSocket) : null;
                this.f3369e = sSLSocket;
                this.f3373i = f.k.b(f.k.i(sSLSocket));
                this.j = f.k.a(f.k.e(this.f3369e));
                this.f3370f = rVarB;
                this.f3371g = strI != null ? Protocol.get(strI) : Protocol.HTTP_1_1;
                e.d0.j.e.h().a(sSLSocket);
                return;
            }
            X509Certificate x509Certificate = (X509Certificate) rVarB.c().get(0);
            throw new SSLPeerUnverifiedException("Hostname " + aVarA.l().l() + " not verified:\n    certificate: " + e.g.c(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + e.d0.l.d.a(x509Certificate));
        } catch (AssertionError e3) {
            e = e3;
            if (!e.d0.c.s(e)) {
                throw e;
            }
            throw new IOException(e);
        } catch (Throwable th2) {
            th = th2;
            sSLSocket2 = sSLSocket;
            if (sSLSocket2 != null) {
                e.d0.j.e.h().a(sSLSocket2);
            }
            e.d0.c.d(sSLSocket2);
            throw th;
        }
    }

    public final void i(int i2, int i3, int i4) throws IOException {
        y yVarK = k();
        HttpUrl httpUrlH = yVarK.h();
        int i5 = 0;
        while (true) {
            i5++;
            if (i5 > 21) {
                throw new ProtocolException("Too many tunnel connections attempted: 21");
            }
            g(i2, i3);
            yVarK = j(i3, i4, yVarK, httpUrlH);
            if (yVarK == null) {
                return;
            }
            e.d0.c.d(this.f3368d);
            this.f3368d = null;
            this.j = null;
            this.f3373i = null;
        }
    }

    public final y j(int i2, int i3, y yVar, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + e.d0.c.m(httpUrl, true) + " HTTP/1.1";
        while (true) {
            e.d0.h.a aVar = new e.d0.h.a(null, null, this.f3373i, this.j);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            this.f3373i.e().g(i2, timeUnit);
            this.j.e().g(i3, timeUnit);
            aVar.o(yVar.d(), str);
            aVar.a();
            a0 a0VarC = aVar.f(false).o(yVar).c();
            long jB = e.d0.g.e.b(a0VarC);
            if (jB == -1) {
                jB = 0;
            }
            q qVarL = aVar.l(jB);
            e.d0.c.u(qVarL, Integer.MAX_VALUE, timeUnit);
            qVarL.close();
            int iS = a0VarC.s();
            if (iS == 200) {
                if (this.f3373i.a().z() && this.j.a().z()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (iS != 407) {
                throw new IOException("Unexpected response code for CONNECT: " + a0VarC.s());
            }
            y yVarA = this.f3367c.a().h().a(this.f3367c, a0VarC);
            if (yVarA == null) {
                throw new IOException("Failed to authenticate with proxy");
            }
            if ("close".equalsIgnoreCase(a0VarC.H("Connection"))) {
                return yVarA;
            }
            yVar = yVarA;
        }
    }

    public final y k() {
        return new y.a().h(this.f3367c.a().l()).d("Host", e.d0.c.m(this.f3367c.a().l(), true)).d("Proxy-Connection", "Keep-Alive").d("User-Agent", e.d0.d.a()).b();
    }

    public final void l(b bVar) throws Throwable {
        if (this.f3367c.a().k() == null) {
            this.f3371g = Protocol.HTTP_1_1;
            this.f3369e = this.f3368d;
            return;
        }
        h(bVar);
        if (this.f3371g == Protocol.HTTP_2) {
            this.f3369e.setSoTimeout(0);
            e.d0.i.e eVarA = new e.h(true).c(this.f3369e, this.f3367c.a().l().l(), this.f3373i, this.j).b(this).a();
            this.f3372h = eVarA;
            eVarA.S();
        }
    }

    public r m() {
        return this.f3370f;
    }

    public boolean n(e.a aVar, @Nullable c0 c0Var) {
        if (this.n.size() >= this.m || this.k || !e.d0.a.f3334a.g(this.f3367c.a(), aVar)) {
            return false;
        }
        if (aVar.l().l().equals(b().a().l().l())) {
            return true;
        }
        if (this.f3372h == null || c0Var == null || c0Var.b().type() != Proxy.Type.DIRECT || this.f3367c.b().type() != Proxy.Type.DIRECT || !this.f3367c.d().equals(c0Var.d()) || c0Var.a().e() != e.d0.l.d.f3564a || !s(aVar.l())) {
            return false;
        }
        try {
            aVar.a().a(aVar.l().l(), m().c());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean o(boolean z) {
        if (this.f3369e.isClosed() || this.f3369e.isInputShutdown() || this.f3369e.isOutputShutdown()) {
            return false;
        }
        if (this.f3372h != null) {
            return !r0.v();
        }
        if (z) {
            try {
                int soTimeout = this.f3369e.getSoTimeout();
                try {
                    this.f3369e.setSoTimeout(1);
                    return !this.f3373i.z();
                } finally {
                    this.f3369e.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    public boolean p() {
        return this.f3372h != null;
    }

    public e.d0.g.c q(w wVar, f fVar) throws SocketException {
        if (this.f3372h != null) {
            return new e.d0.i.d(wVar, fVar, this.f3372h);
        }
        this.f3369e.setSoTimeout(wVar.z());
        f.r rVarE = this.f3373i.e();
        long jZ = wVar.z();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        rVarE.g(jZ, timeUnit);
        this.j.e().g(wVar.F(), timeUnit);
        return new e.d0.h.a(wVar, fVar, this.f3373i, this.j);
    }

    public Socket r() {
        return this.f3369e;
    }

    public boolean s(HttpUrl httpUrl) {
        if (httpUrl.y() != this.f3367c.a().l().y()) {
            return false;
        }
        if (httpUrl.l().equals(this.f3367c.a().l().l())) {
            return true;
        }
        return this.f3370f != null && e.d0.l.d.f3564a.c(httpUrl.l(), (X509Certificate) this.f3370f.c().get(0));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.f3367c.a().l().l());
        sb.append(":");
        sb.append(this.f3367c.a().l().y());
        sb.append(", proxy=");
        sb.append(this.f3367c.b());
        sb.append(" hostAddress=");
        sb.append(this.f3367c.d());
        sb.append(" cipherSuite=");
        r rVar = this.f3370f;
        sb.append(rVar != null ? rVar.a() : "none");
        sb.append(" protocol=");
        sb.append(this.f3371g);
        sb.append('}');
        return sb.toString();
    }
}
