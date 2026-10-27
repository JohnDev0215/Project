package i6;

import c6.a0;
import c6.b0;
import c6.c0;
import c6.d0;
import c6.u;
import c6.v;
import c6.x;
import c6.z;
import h5.p;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: RetryAndFollowUpInterceptor.kt */
/* JADX INFO: loaded from: classes.dex */
public final class j implements v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f7065b = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final x f7066a;

    /* JADX INFO: compiled from: RetryAndFollowUpInterceptor.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public j(x client) {
        l.e(client, "client");
        this.f7066a = client;
    }

    private final z b(b0 b0Var, String str) {
        String strU;
        u uVarO;
        if (!this.f7066a.p() || (strU = b0.U(b0Var, "Location", null, 2, null)) == null || (uVarO = b0Var.g0().i().o(strU)) == null) {
            return null;
        }
        if (!l.a(uVarO.p(), b0Var.g0().i().p()) && !this.f7066a.q()) {
            return null;
        }
        z.a aVarH = b0Var.g0().h();
        if (f.a(str)) {
            int I = b0Var.I();
            f fVar = f.f7051a;
            boolean z6 = fVar.c(str) || I == 308 || I == 307;
            if (!fVar.b(str) || I == 308 || I == 307) {
                aVarH.e(str, z6 ? b0Var.g0().a() : null);
            } else {
                aVarH.e("GET", null);
            }
            if (!z6) {
                aVarH.f("Transfer-Encoding");
                aVarH.f("Content-Length");
                aVarH.f("Content-Type");
            }
        }
        if (!d6.b.g(b0Var.g0().i(), uVarO)) {
            aVarH.f("Authorization");
        }
        return aVarH.g(uVarO).b();
    }

    private final z c(b0 b0Var, h6.c cVar) throws ProtocolException {
        h6.f fVarH;
        d0 d0VarZ = (cVar == null || (fVarH = cVar.h()) == null) ? null : fVarH.z();
        int I = b0Var.I();
        String strG = b0Var.g0().g();
        if (I != 307 && I != 308) {
            if (I == 401) {
                return this.f7066a.d().a(d0VarZ, b0Var);
            }
            if (I == 421) {
                a0 a0VarA = b0Var.g0().a();
                if ((a0VarA != null && a0VarA.d()) || cVar == null || !cVar.k()) {
                    return null;
                }
                cVar.h().x();
                return b0Var.g0();
            }
            if (I == 503) {
                b0 b0VarD0 = b0Var.d0();
                if ((b0VarD0 == null || b0VarD0.I() != 503) && g(b0Var, Integer.MAX_VALUE) == 0) {
                    return b0Var.g0();
                }
                return null;
            }
            if (I == 407) {
                l.c(d0VarZ);
                if (d0VarZ.b().type() == Proxy.Type.HTTP) {
                    return this.f7066a.y().a(d0VarZ, b0Var);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            if (I == 408) {
                if (!this.f7066a.B()) {
                    return null;
                }
                a0 a0VarA2 = b0Var.g0().a();
                if (a0VarA2 != null && a0VarA2.d()) {
                    return null;
                }
                b0 b0VarD02 = b0Var.d0();
                if ((b0VarD02 == null || b0VarD02.I() != 408) && g(b0Var, 0) <= 0) {
                    return b0Var.g0();
                }
                return null;
            }
            switch (I) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        return b(b0Var, strG);
    }

    private final boolean d(IOException iOException, boolean z6) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z6 : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private final boolean e(IOException iOException, h6.e eVar, z zVar, boolean z6) {
        if (this.f7066a.B()) {
            return !(z6 && f(iOException, zVar)) && d(iOException, z6) && eVar.x();
        }
        return false;
    }

    private final boolean f(IOException iOException, z zVar) {
        a0 a0VarA = zVar.a();
        return (a0VarA != null && a0VarA.d()) || (iOException instanceof FileNotFoundException);
    }

    private final int g(b0 b0Var, int i7) {
        String strU = b0.U(b0Var, "Retry-After", null, 2, null);
        if (strU == null) {
            return i7;
        }
        if (!new y5.f("\\d+").a(strU)) {
            return Integer.MAX_VALUE;
        }
        Integer numValueOf = Integer.valueOf(strU);
        l.d(numValueOf, "Integer.valueOf(header)");
        return numValueOf.intValue();
    }

    @Override // c6.v
    public b0 a(v.a chain) {
        h6.c cVarO;
        z zVarC;
        l.e(chain, "chain");
        g gVar = (g) chain;
        z zVarI = gVar.i();
        h6.e eVarE = gVar.e();
        List listH = p.h();
        b0 b0Var = null;
        boolean z6 = true;
        int i7 = 0;
        while (true) {
            eVarE.h(zVarI, z6);
            try {
                if (eVarE.s()) {
                    throw new IOException("Canceled");
                }
                try {
                    b0 b0VarB = gVar.b(zVarI);
                    if (b0Var != null) {
                        b0VarB = b0VarB.c0().o(b0Var.c0().b(null).c()).c();
                    }
                    b0Var = b0VarB;
                    cVarO = eVarE.o();
                    zVarC = c(b0Var, cVarO);
                } catch (h6.j e7) {
                    if (!e(e7.c(), eVarE, zVarI, false)) {
                        throw d6.b.S(e7.b(), listH);
                    }
                    listH = h5.x.E(listH, e7.b());
                    eVarE.i(true);
                    z6 = false;
                } catch (IOException e8) {
                    if (!e(e8, eVarE, zVarI, !(e8 instanceof k6.a))) {
                        throw d6.b.S(e8, listH);
                    }
                    listH = h5.x.E(listH, e8);
                    eVarE.i(true);
                    z6 = false;
                }
                if (zVarC == null) {
                    if (cVarO != null && cVarO.l()) {
                        eVarE.z();
                    }
                    eVarE.i(false);
                    return b0Var;
                }
                a0 a0VarA = zVarC.a();
                if (a0VarA != null && a0VarA.d()) {
                    eVarE.i(false);
                    return b0Var;
                }
                c0 c0VarM = b0Var.m();
                if (c0VarM != null) {
                    d6.b.i(c0VarM);
                }
                i7++;
                if (i7 > 20) {
                    throw new ProtocolException("Too many follow-up requests: " + i7);
                }
                eVarE.i(true);
                zVarI = zVarC;
                z6 = true;
            } catch (Throwable th) {
                eVarE.i(true);
                throw th;
            }
        }
    }
}
