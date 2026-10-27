package f.k0.h;

import f.b0;
import f.c0;
import f.e0;
import f.g0;
import f.i0;
import f.k0.k.f;
import f.k0.k.n;
import f.m;
import f.o;
import f.t;
import f.v;
import f.x;
import f.y;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Timeout;

/* JADX INFO: compiled from: RealConnection.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends f.j implements m {

    /* JADX INFO: renamed from: b */
    public final g f5190b;

    /* JADX INFO: renamed from: c */
    private final i0 f5191c;

    /* JADX INFO: renamed from: d */
    private Socket f5192d;

    /* JADX INFO: renamed from: e */
    private Socket f5193e;

    /* JADX INFO: renamed from: f */
    private v f5194f;

    /* JADX INFO: renamed from: g */
    private c0 f5195g;

    /* JADX INFO: renamed from: h */
    private f.k0.k.f f5196h;

    /* JADX INFO: renamed from: i */
    private BufferedSource f5197i;
    private BufferedSink j;
    boolean k;
    int l;
    int m;
    private int n;
    private int o = 1;
    final List<Reference<k>> p = new ArrayList();
    long q = Long.MAX_VALUE;

    public f(g gVar, i0 i0Var) {
        this.f5190b = gVar;
        this.f5191c = i0Var;
    }

    private void f(int i2, int i3, f.i iVar, t tVar) throws IOException {
        Proxy proxyB = this.f5191c.b();
        this.f5192d = (proxyB.type() == Proxy.Type.DIRECT || proxyB.type() == Proxy.Type.HTTP) ? this.f5191c.a().j().createSocket() : new Socket(proxyB);
        tVar.f(iVar, this.f5191c.d(), proxyB);
        this.f5192d.setSoTimeout(i3);
        try {
            f.k0.l.f.j().h(this.f5192d, this.f5191c.d(), i2);
            try {
                this.f5197i = Okio.c(Okio.l(this.f5192d));
                this.j = Okio.b(Okio.h(this.f5192d));
            } catch (NullPointerException e2) {
                if ("throw with null exception".equals(e2.getMessage())) {
                    throw new IOException(e2);
                }
            }
        } catch (ConnectException e3) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.f5191c.d());
            connectException.initCause(e3);
            throw connectException;
        }
    }

    private void g(c cVar) throws Throwable {
        f.e eVarA = this.f5191c.a();
        SSLSocket sSLSocket = null;
        try {
            try {
                SSLSocket sSLSocket2 = (SSLSocket) eVarA.k().createSocket(this.f5192d, eVarA.l().m(), eVarA.l().y(), true);
                try {
                    o oVarA = cVar.a(sSLSocket2);
                    if (oVarA.f()) {
                        f.k0.l.f.j().g(sSLSocket2, eVarA.l().m(), eVarA.f());
                    }
                    sSLSocket2.startHandshake();
                    SSLSession session = sSLSocket2.getSession();
                    v vVarB = v.b(session);
                    if (eVarA.e().verify(eVarA.l().m(), session)) {
                        eVarA.a().a(eVarA.l().m(), vVarB.d());
                        String strL = oVarA.f() ? f.k0.l.f.j().l(sSLSocket2) : null;
                        this.f5193e = sSLSocket2;
                        this.f5197i = Okio.c(Okio.l(sSLSocket2));
                        this.j = Okio.b(Okio.h(this.f5193e));
                        this.f5194f = vVarB;
                        this.f5195g = strL != null ? c0.a(strL) : c0.HTTP_1_1;
                        f.k0.l.f.j().a(sSLSocket2);
                        return;
                    }
                    List<Certificate> listD = vVarB.d();
                    if (listD.isEmpty()) {
                        throw new SSLPeerUnverifiedException("Hostname " + eVarA.l().m() + " not verified (no certificates)");
                    }
                    X509Certificate x509Certificate = (X509Certificate) listD.get(0);
                    throw new SSLPeerUnverifiedException("Hostname " + eVarA.l().m() + " not verified:\n    certificate: " + f.k.c(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + f.k0.n.d.a(x509Certificate));
                } catch (AssertionError e2) {
                    e = e2;
                    if (!f.k0.e.z(e)) {
                        throw e;
                    }
                    throw new IOException(e);
                } catch (Throwable th) {
                    th = th;
                    sSLSocket = sSLSocket2;
                    if (sSLSocket != null) {
                        f.k0.l.f.j().a(sSLSocket);
                    }
                    f.k0.e.g(sSLSocket);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (AssertionError e3) {
            e = e3;
        }
    }

    private void h(int i2, int i3, int i4, f.i iVar, t tVar) throws IOException {
        e0 e0VarJ = j();
        x xVarI = e0VarJ.i();
        for (int i5 = 0; i5 < 21; i5++) {
            f(i2, i3, iVar, tVar);
            e0VarJ = i(i3, i4, e0VarJ, xVarI);
            if (e0VarJ == null) {
                return;
            }
            f.k0.e.g(this.f5192d);
            this.f5192d = null;
            this.j = null;
            this.f5197i = null;
            tVar.d(iVar, this.f5191c.d(), this.f5191c.b(), null);
        }
    }

    private e0 i(int i2, int i3, e0 e0Var, x xVar) throws IOException {
        String str = "CONNECT " + f.k0.e.r(xVar, true) + " HTTP/1.1";
        f.k0.j.a aVar = new f.k0.j.a(null, null, this.f5197i, this.j);
        Timeout timeoutE = this.f5197i.e();
        long j = i2;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        timeoutE.h(j, timeUnit);
        this.j.e().h(i3, timeUnit);
        aVar.B(e0Var.d(), str);
        aVar.a();
        g0 g0VarC = aVar.h(false).q(e0Var).c();
        aVar.A(g0VarC);
        int iJ = g0VarC.j();
        if (iJ == 200) {
            if (this.f5197i.G().I() && this.j.b().I()) {
                return null;
            }
            throw new IOException("TLS tunnel buffered too many bytes!");
        }
        if (iJ == 407) {
            this.f5191c.a().h().b(this.f5191c, g0VarC);
            throw new IOException("Failed to authenticate with proxy");
        }
        throw new IOException("Unexpected response code for CONNECT: " + g0VarC.j());
    }

    private e0 j() {
        e0 e0VarA = new e0.a().j(this.f5191c.a().l()).d("CONNECT", null).b("Host", f.k0.e.r(this.f5191c.a().l(), true)).b("Proxy-Connection", "Keep-Alive").b("User-Agent", f.k0.f.a()).a();
        this.f5191c.a().h().b(this.f5191c, new g0.a().q(e0VarA).o(c0.HTTP_1_1).g(407).l("Preemptive Authenticate").b(f.k0.e.f5136d).r(-1L).p(-1L).i("Proxy-Authenticate", "OkHttp-Preemptive").c());
        return e0VarA;
    }

    private void k(c cVar, int i2, f.i iVar, t tVar) throws Throwable {
        if (this.f5191c.a().k() != null) {
            tVar.x(iVar);
            g(cVar);
            tVar.w(iVar, this.f5194f);
            if (this.f5195g == c0.HTTP_2) {
                u(i2);
                return;
            }
            return;
        }
        List<c0> listF = this.f5191c.a().f();
        c0 c0Var = c0.H2_PRIOR_KNOWLEDGE;
        if (!listF.contains(c0Var)) {
            this.f5193e = this.f5192d;
            this.f5195g = c0.HTTP_1_1;
        } else {
            this.f5193e = this.f5192d;
            this.f5195g = c0Var;
            u(i2);
        }
    }

    private boolean s(List<i0> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            i0 i0Var = list.get(i2);
            if (i0Var.b().type() == Proxy.Type.DIRECT && this.f5191c.b().type() == Proxy.Type.DIRECT && this.f5191c.d().equals(i0Var.d())) {
                return true;
            }
        }
        return false;
    }

    private void u(int i2) throws SocketException {
        this.f5193e.setSoTimeout(0);
        f.k0.k.f fVarA = new f.h(true).d(this.f5193e, this.f5191c.a().l().m(), this.f5197i, this.j).b(this).c(i2).a();
        this.f5196h = fVarA;
        fVarA.n0();
    }

    @Override // f.m
    public c0 a() {
        return this.f5195g;
    }

    @Override // f.k0.k.f.j
    public void b(f.k0.k.f fVar) {
        synchronized (this.f5190b) {
            this.o = fVar.b0();
        }
    }

    @Override // f.k0.k.f.j
    public void c(f.k0.k.i iVar) {
        iVar.d(f.k0.k.b.REFUSED_STREAM, null);
    }

    public void d() {
        f.k0.e.g(this.f5192d);
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x00f4 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0142 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(int r17, int r18, int r19, int r20, boolean r21, f.i r22, f.t r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f.k0.h.f.e(int, int, int, int, boolean, f.i, f.t):void");
    }

    public v l() {
        return this.f5194f;
    }

    boolean m(f.e eVar, @Nullable List<i0> list) {
        if (this.p.size() >= this.o || this.k || !f.k0.c.f5131a.e(this.f5191c.a(), eVar)) {
            return false;
        }
        if (eVar.l().m().equals(r().a().l().m())) {
            return true;
        }
        if (this.f5196h == null || list == null || !s(list) || eVar.e() != f.k0.n.d.f5450a || !v(eVar.l())) {
            return false;
        }
        try {
            eVar.a().a(eVar.l().m(), l().d());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean n(boolean z) {
        if (this.f5193e.isClosed() || this.f5193e.isInputShutdown() || this.f5193e.isOutputShutdown()) {
            return false;
        }
        f.k0.k.f fVar = this.f5196h;
        if (fVar != null) {
            return fVar.a0(System.nanoTime());
        }
        if (z) {
            try {
                int soTimeout = this.f5193e.getSoTimeout();
                try {
                    this.f5193e.setSoTimeout(1);
                    return !this.f5197i.I();
                } finally {
                    this.f5193e.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    public boolean o() {
        return this.f5196h != null;
    }

    f.k0.i.c p(b0 b0Var, y.a aVar) throws SocketException {
        if (this.f5196h != null) {
            return new f.k0.k.g(b0Var, this, aVar, this.f5196h);
        }
        this.f5193e.setSoTimeout(aVar.f());
        Timeout timeoutE = this.f5197i.e();
        long jF = aVar.f();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        timeoutE.h(jF, timeUnit);
        this.j.e().h(aVar.c(), timeUnit);
        return new f.k0.j.a(b0Var, this, this.f5197i, this.j);
    }

    public void q() {
        synchronized (this.f5190b) {
            this.k = true;
        }
    }

    public i0 r() {
        return this.f5191c;
    }

    public Socket t() {
        return this.f5193e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.f5191c.a().l().m());
        sb.append(":");
        sb.append(this.f5191c.a().l().y());
        sb.append(", proxy=");
        sb.append(this.f5191c.b());
        sb.append(" hostAddress=");
        sb.append(this.f5191c.d());
        sb.append(" cipherSuite=");
        v vVar = this.f5194f;
        sb.append(vVar != null ? vVar.a() : "none");
        sb.append(" protocol=");
        sb.append(this.f5195g);
        sb.append('}');
        return sb.toString();
    }

    public boolean v(x xVar) {
        if (xVar.y() != this.f5191c.a().l().y()) {
            return false;
        }
        if (xVar.m().equals(this.f5191c.a().l().m())) {
            return true;
        }
        return this.f5194f != null && f.k0.n.d.f5450a.c(xVar.m(), (X509Certificate) this.f5194f.d().get(0));
    }

    void w(@Nullable IOException iOException) {
        synchronized (this.f5190b) {
            if (iOException instanceof n) {
                f.k0.k.b bVar = ((n) iOException).errorCode;
                if (bVar == f.k0.k.b.REFUSED_STREAM) {
                    int i2 = this.n + 1;
                    this.n = i2;
                    if (i2 > 1) {
                        this.k = true;
                        this.l++;
                    }
                } else if (bVar != f.k0.k.b.CANCEL) {
                    this.k = true;
                    this.l++;
                }
            } else if (!o() || (iOException instanceof f.k0.k.a)) {
                this.k = true;
                if (this.m == 0) {
                    if (iOException != null) {
                        this.f5190b.b(this.f5191c, iOException);
                    }
                    this.l++;
                }
            }
        }
    }
}
