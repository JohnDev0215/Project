package f.b0.g;

import f.a0;
import f.r;
import f.u;
import f.w;
import f.y;
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
public final class j implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f5088a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f5089b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f.b0.f.f f5090c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f5091d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile boolean f5092e;

    public j(u uVar, boolean z) {
        this.f5088a = uVar;
        this.f5089b = z;
    }

    @Override // f.r
    public y a(r.a aVar) throws IOException {
        w wVarB = aVar.b();
        this.f5090c = new f.b0.f.f(this.f5088a.d(), c(wVarB.h()), this.f5091d);
        int i2 = 0;
        y yVar = null;
        while (!this.f5092e) {
            try {
                try {
                    y yVarD = ((g) aVar).d(wVarB, this.f5090c, null, null);
                    if (yVar != null) {
                        yVarD = yVarD.U().l(yVar.U().b(null).c()).c();
                    }
                    yVar = yVarD;
                    wVarB = d(yVar);
                } catch (IOException e2) {
                    if (!g(e2, !(e2 instanceof ConnectionShutdownException), wVarB)) {
                        throw e2;
                    }
                } catch (RouteException e3) {
                    if (!g(e3.getLastConnectException(), false, wVarB)) {
                        throw e3.getLastConnectException();
                    }
                }
                if (wVarB == null) {
                    if (!this.f5089b) {
                        this.f5090c.k();
                    }
                    return yVar;
                }
                f.b0.c.c(yVar.w());
                i2++;
                if (i2 > 20) {
                    this.f5090c.k();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                }
                wVarB.a();
                if (!h(yVar, wVarB.h())) {
                    this.f5090c.k();
                    this.f5090c = new f.b0.f.f(this.f5088a.d(), c(wVarB.h()), this.f5091d);
                } else if (this.f5090c.c() != null) {
                    throw new IllegalStateException("Closing the body of " + yVar + " didn't close its backing stream. Bad interceptor?");
                }
            } catch (Throwable th) {
                this.f5090c.o(null);
                this.f5090c.k();
                throw th;
            }
        }
        this.f5090c.k();
        throw new IOException("Canceled");
    }

    public void b() {
        this.f5092e = true;
        f.b0.f.f fVar = this.f5090c;
        if (fVar != null) {
            fVar.b();
        }
    }

    public final f.a c(HttpUrl httpUrl) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifierK;
        f.g gVarB;
        if (httpUrl.m()) {
            SSLSocketFactory sSLSocketFactoryX = this.f5088a.x();
            hostnameVerifierK = this.f5088a.k();
            sSLSocketFactory = sSLSocketFactoryX;
            gVarB = this.f5088a.b();
        } else {
            sSLSocketFactory = null;
            hostnameVerifierK = null;
            gVarB = null;
        }
        return new f.a(httpUrl.l(), httpUrl.x(), this.f5088a.h(), this.f5088a.v(), sSLSocketFactory, hostnameVerifierK, gVarB, this.f5088a.r(), this.f5088a.q(), this.f5088a.p(), this.f5088a.e(), this.f5088a.s());
    }

    public final w d(y yVar) throws IOException {
        String strQ;
        HttpUrl httpUrlB;
        if (yVar == null) {
            throw new IllegalStateException();
        }
        f.b0.f.c cVarD = this.f5090c.d();
        a0 a0VarA = cVarD != null ? cVarD.a() : null;
        int iO = yVar.O();
        String strF = yVar.W().f();
        if (iO == 307 || iO == 308) {
            if (!strF.equals("GET") && !strF.equals("HEAD")) {
                return null;
            }
        } else {
            if (iO == 401) {
                return this.f5088a.a().a(a0VarA, yVar);
            }
            if (iO == 407) {
                if ((a0VarA != null ? a0VarA.b() : this.f5088a.q()).type() == Proxy.Type.HTTP) {
                    return this.f5088a.r().a(a0VarA, yVar);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            if (iO == 408) {
                yVar.W().a();
                return yVar.W();
            }
            switch (iO) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        if (!this.f5088a.i() || (strQ = yVar.Q("Location")) == null || (httpUrlB = yVar.W().h().B(strQ)) == null) {
            return null;
        }
        if (!httpUrlB.C().equals(yVar.W().h().C()) && !this.f5088a.j()) {
            return null;
        }
        w.a aVarG = yVar.W().g();
        if (f.b(strF)) {
            boolean zD = f.d(strF);
            if (f.c(strF)) {
                aVarG.f("GET", null);
            } else {
                aVarG.f(strF, zD ? yVar.W().a() : null);
            }
            if (!zD) {
                aVarG.h("Transfer-Encoding");
                aVarG.h("Content-Length");
                aVarG.h("Content-Type");
            }
        }
        if (!h(yVar, httpUrlB)) {
            aVarG.h("Authorization");
        }
        return aVarG.j(httpUrlB).b();
    }

    public boolean e() {
        return this.f5092e;
    }

    public final boolean f(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    public final boolean g(IOException iOException, boolean z, w wVar) {
        this.f5090c.o(iOException);
        if (!this.f5088a.u()) {
            return false;
        }
        if (z) {
            wVar.a();
        }
        return f(iOException, z) && this.f5090c.h();
    }

    public final boolean h(y yVar, HttpUrl httpUrl) {
        HttpUrl httpUrlH = yVar.W().h();
        return httpUrlH.l().equals(httpUrl.l()) && httpUrlH.x() == httpUrl.x() && httpUrlH.C().equals(httpUrl.C());
    }

    public void i(Object obj) {
        this.f5091d = obj;
    }
}
