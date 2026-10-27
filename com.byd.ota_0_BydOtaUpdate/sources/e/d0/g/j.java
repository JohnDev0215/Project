package e.d0.g;

import e.a0;
import e.c0;
import e.t;
import e.w;
import e.y;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;

/* JADX INFO: compiled from: RetryAndFollowUpInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w f3408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f3409b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e.d0.f.f f3410c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f3411d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile boolean f3412e;

    public j(w wVar, boolean z) {
        this.f3408a = wVar;
        this.f3409b = z;
    }

    @Override // e.t
    public a0 a(t.a aVar) throws IOException {
        y yVarB = aVar.b();
        this.f3410c = new e.d0.f.f(this.f3408a.g(), c(yVarB.h()), this.f3411d);
        int i2 = 0;
        a0 a0Var = null;
        while (!this.f3412e) {
            try {
                try {
                    a0 a0VarE = ((g) aVar).e(yVarB, this.f3410c, null, null);
                    if (a0Var != null) {
                        a0VarE = a0VarE.M().l(a0Var.M().b(null).c()).c();
                    }
                    a0Var = a0VarE;
                    yVarB = d(a0Var);
                } catch (IOException e2) {
                    if (!g(e2, !(e2 instanceof ConnectionShutdownException), yVarB)) {
                        throw e2;
                    }
                } catch (RouteException e3) {
                    if (!g(e3.getLastConnectException(), false, yVarB)) {
                        throw e3.getLastConnectException();
                    }
                }
                if (yVarB == null) {
                    if (!this.f3409b) {
                        this.f3410c.k();
                    }
                    return a0Var;
                }
                e.d0.c.c(a0Var.c());
                i2++;
                if (i2 > 20) {
                    this.f3410c.k();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                }
                yVarB.a();
                if (!h(a0Var, yVarB.h())) {
                    this.f3410c.k();
                    this.f3410c = new e.d0.f.f(this.f3408a.g(), c(yVarB.h()), this.f3411d);
                } else if (this.f3410c.c() != null) {
                    throw new IllegalStateException("Closing the body of " + a0Var + " didn't close its backing stream. Bad interceptor?");
                }
            } catch (Throwable th) {
                this.f3410c.o(null);
                this.f3410c.k();
                throw th;
            }
        }
        this.f3410c.k();
        throw new IOException("Canceled");
    }

    public void b() {
        this.f3412e = true;
        e.d0.f.f fVar = this.f3410c;
        if (fVar != null) {
            fVar.b();
        }
    }

    public final e.a c(HttpUrl httpUrl) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifierO;
        e.g gVarE;
        if (httpUrl.m()) {
            SSLSocketFactory sSLSocketFactoryC = this.f3408a.C();
            hostnameVerifierO = this.f3408a.o();
            sSLSocketFactory = sSLSocketFactoryC;
            gVarE = this.f3408a.e();
        } else {
            sSLSocketFactory = null;
            hostnameVerifierO = null;
            gVarE = null;
        }
        return new e.a(httpUrl.l(), httpUrl.y(), this.f3408a.k(), this.f3408a.B(), sSLSocketFactory, hostnameVerifierO, gVarE, this.f3408a.x(), this.f3408a.w(), this.f3408a.u(), this.f3408a.h(), this.f3408a.y());
    }

    public final y d(a0 a0Var) throws IOException {
        String strH;
        HttpUrl httpUrlC;
        if (a0Var == null) {
            throw new IllegalStateException();
        }
        e.d0.f.c cVarD = this.f3410c.d();
        c0 c0VarB = cVarD != null ? cVarD.b() : null;
        int iS = a0Var.s();
        String strF = a0Var.O().f();
        if (iS == 307 || iS == 308) {
            if (!strF.equals("GET") && !strF.equals("HEAD")) {
                return null;
            }
        } else {
            if (iS == 401) {
                return this.f3408a.b().a(c0VarB, a0Var);
            }
            if (iS == 407) {
                if ((c0VarB != null ? c0VarB.b() : this.f3408a.w()).type() == Proxy.Type.HTTP) {
                    return this.f3408a.x().a(c0VarB, a0Var);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            if (iS == 408) {
                a0Var.O().a();
                return a0Var.O();
            }
            switch (iS) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        if (!this.f3408a.m() || (strH = a0Var.H("Location")) == null || (httpUrlC = a0Var.O().h().C(strH)) == null) {
            return null;
        }
        if (!httpUrlC.D().equals(a0Var.O().h().D()) && !this.f3408a.n()) {
            return null;
        }
        y.a aVarG = a0Var.O().g();
        if (f.b(strF)) {
            boolean zD = f.d(strF);
            if (f.c(strF)) {
                aVarG.f("GET", null);
            } else {
                aVarG.f(strF, zD ? a0Var.O().a() : null);
            }
            if (!zD) {
                aVarG.g("Transfer-Encoding");
                aVarG.g("Content-Length");
                aVarG.g("Content-Type");
            }
        }
        if (!h(a0Var, httpUrlC)) {
            aVarG.g("Authorization");
        }
        return aVarG.h(httpUrlC).b();
    }

    public boolean e() {
        return this.f3412e;
    }

    public final boolean f(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    public final boolean g(IOException iOException, boolean z, y yVar) {
        this.f3410c.o(iOException);
        if (!this.f3408a.A()) {
            return false;
        }
        if (z) {
            yVar.a();
        }
        return f(iOException, z) && this.f3410c.h();
    }

    public final boolean h(a0 a0Var, HttpUrl httpUrl) {
        HttpUrl httpUrlH = a0Var.O().h();
        return httpUrlH.l().equals(httpUrl.l()) && httpUrlH.y() == httpUrl.y() && httpUrlH.D().equals(httpUrl.D());
    }

    public void i(Object obj) {
        this.f3411d = obj;
    }
}
