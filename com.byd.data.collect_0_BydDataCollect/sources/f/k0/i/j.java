package f.k0.i;

import f.b0;
import f.e0;
import f.f0;
import f.g0;
import f.i0;
import f.x;
import f.y;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.annotation.Nullable;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: compiled from: RetryAndFollowUpInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b0 f5245a;

    public j(b0 b0Var) {
        this.f5245a = b0Var;
    }

    private e0 b(g0 g0Var, @Nullable i0 i0Var) throws ProtocolException {
        String strC;
        x xVarC;
        if (g0Var == null) {
            throw new IllegalStateException();
        }
        int iJ = g0Var.j();
        String strF = g0Var.Y().f();
        if (iJ == 307 || iJ == 308) {
            if (!strF.equals("GET") && !strF.equals("HEAD")) {
                return null;
            }
        } else {
            if (iJ == 401) {
                this.f5245a.c().b(i0Var, g0Var);
                return null;
            }
            if (iJ == 503) {
                if ((g0Var.W() == null || g0Var.W().j() != 503) && f(g0Var, Integer.MAX_VALUE) == 0) {
                    return g0Var.Y();
                }
                return null;
            }
            if (iJ == 407) {
                if ((i0Var != null ? i0Var.b() : this.f5245a.x()).type() != Proxy.Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                this.f5245a.z().b(i0Var, g0Var);
                return null;
            }
            if (iJ == 408) {
                if (!this.f5245a.D()) {
                    return null;
                }
                f0 f0VarA = g0Var.Y().a();
                if (f0VarA != null && f0VarA.i()) {
                    return null;
                }
                if ((g0Var.W() == null || g0Var.W().j() != 408) && f(g0Var, 0) <= 0) {
                    return g0Var.Y();
                }
                return null;
            }
            switch (iJ) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        if (!this.f5245a.o() || (strC = g0Var.C("Location")) == null || (xVarC = g0Var.Y().i().C(strC)) == null) {
            return null;
        }
        if (!xVarC.D().equals(g0Var.Y().i().D()) && !this.f5245a.p()) {
            return null;
        }
        e0.a aVarG = g0Var.Y().g();
        if (f.b(strF)) {
            boolean zD = f.d(strF);
            if (f.c(strF)) {
                aVarG.d("GET", null);
            } else {
                aVarG.d(strF, zD ? g0Var.Y().a() : null);
            }
            if (!zD) {
                aVarG.h("Transfer-Encoding");
                aVarG.h("Content-Length");
                aVarG.h("Content-Type");
            }
        }
        if (!f.k0.e.D(g0Var.Y().i(), xVarC)) {
            aVarG.h("Authorization");
        }
        return aVarG.j(xVarC).a();
    }

    private boolean c(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private boolean d(IOException iOException, f.k0.h.k kVar, boolean z, e0 e0Var) {
        if (this.f5245a.D()) {
            return !(z && e(iOException, e0Var)) && c(iOException, z) && kVar.c();
        }
        return false;
    }

    private boolean e(IOException iOException, e0 e0Var) {
        f0 f0VarA = e0Var.a();
        return (f0VarA != null && f0VarA.i()) || (iOException instanceof FileNotFoundException);
    }

    private int f(g0 g0Var, int i2) {
        String strC = g0Var.C("Retry-After");
        if (strC == null) {
            return i2;
        }
        if (strC.matches("\\d+")) {
            return Integer.valueOf(strC).intValue();
        }
        return Integer.MAX_VALUE;
    }

    @Override // f.y
    public g0 a(y.a aVar) throws IOException {
        f.k0.h.d dVarF;
        e0 e0VarB;
        e0 e0VarA = aVar.a();
        g gVar = (g) aVar;
        f.k0.h.k kVarI = gVar.i();
        int i2 = 0;
        g0 g0Var = null;
        while (true) {
            kVarI.m(e0VarA);
            if (kVarI.i()) {
                throw new IOException("Canceled");
            }
            try {
                try {
                    g0 g0VarH = gVar.h(e0VarA, kVarI, null);
                    if (g0Var != null) {
                        g0VarH = g0VarH.V().n(g0Var.V().b(null).c()).c();
                    }
                    g0Var = g0VarH;
                    dVarF = f.k0.c.f5131a.f(g0Var);
                    e0VarB = b(g0Var, dVarF != null ? dVarF.c().r() : null);
                } catch (f.k0.h.i e2) {
                    if (!d(e2.d(), kVarI, false, e0VarA)) {
                        throw e2.b();
                    }
                } catch (IOException e3) {
                    if (!d(e3, kVarI, !(e3 instanceof f.k0.k.a), e0VarA)) {
                        throw e3;
                    }
                }
                if (e0VarB == null) {
                    if (dVarF != null && dVarF.h()) {
                        kVarI.o();
                    }
                    return g0Var;
                }
                f0 f0VarA = e0VarB.a();
                if (f0VarA != null && f0VarA.i()) {
                    return g0Var;
                }
                f.k0.e.f(g0Var.a());
                if (kVarI.h()) {
                    dVarF.e();
                }
                i2++;
                if (i2 > 20) {
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                }
                e0VarA = e0VarB;
            } finally {
                kVarI.f();
            }
        }
    }
}
