package J5;

import A7.D;
import A7.m;
import F.u;
import N0.q;
import Q8.o;
import android.content.Context;
import c9.C;
import c9.C1041b;
import c9.H;
import c9.J;
import c9.v;
import c9.w;
import c9.x;
import com.stingray.kca.model.LoginInfo;
import g1.AbstractC1274a;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: loaded from: classes.dex */
public final class i implements x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3106a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f3107b;

    public i(h hVar) {
        this.f3107b = hVar;
    }

    public static q b(H0.b bVar, String str) {
        q qVarL = bVar.L();
        qVarL.o("Authorization", "Bearer " + str);
        String languageTag = Locale.getDefault().toLanguageTag();
        m.e(languageTag, "toLanguageTag(...)");
        qVarL.o("Accept-Language", languageTag);
        return qVarL;
    }

    public static int e(H h10, int i7) {
        String strG = H.g(h10, "Retry-After");
        if (strG == null) {
            return i7;
        }
        Pattern patternCompile = Pattern.compile("\\d+");
        m.e(patternCompile, "compile(...)");
        if (!patternCompile.matcher(strG).matches()) {
            return Integer.MAX_VALUE;
        }
        Integer numValueOf = Integer.valueOf(strG);
        m.e(numValueOf, "valueOf(header)");
        return numValueOf.intValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:167:0x034d  */
    @Override // c9.x
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final c9.H a(F.u r32) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 1012
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: J5.i.a(F.u):c9.H");
    }

    public H0.b c(H h10, g9.e eVar) throws ProtocolException {
        String strG;
        v vVar;
        g9.m mVar;
        J j3 = (eVar == null || (mVar = (g9.m) eVar.f15045f) == null) ? null : mVar.f15076b;
        int i7 = h10.o;
        String str = (String) h10.l.f2766n;
        if (i7 != 307 && i7 != 308) {
            if (i7 == 401) {
                ((C) this.f3107b).f12483r.getClass();
                return null;
            }
            if (i7 == 421) {
                if (eVar == null || m.a(((g9.f) eVar.d).f15047b.f12540h.d, ((g9.m) eVar.f15045f).f15076b.f12528a.f12540h.d)) {
                    return null;
                }
                g9.m mVar2 = (g9.m) eVar.f15045f;
                synchronized (mVar2) {
                    mVar2.f15083k = true;
                }
                return h10.l;
            }
            if (i7 == 503) {
                H h11 = h10.f12521u;
                if ((h11 == null || h11.o != 503) && e(h10, Integer.MAX_VALUE) == 0) {
                    return h10.l;
                }
                return null;
            }
            if (i7 == 407) {
                m.c(j3);
                if (j3.f12529b.type() != Proxy.Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                ((C) this.f3107b).f12490y.getClass();
                return null;
            }
            if (i7 == 408) {
                if (!((C) this.f3107b).f12482q) {
                    return null;
                }
                H h12 = h10.f12521u;
                if ((h12 == null || h12.o != 408) && e(h10, 0) <= 0) {
                    return h10.l;
                }
                return null;
            }
            switch (i7) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        C c = (C) this.f3107b;
        if (!c.f12484s || (strG = H.g(h10, "Location")) == null) {
            return null;
        }
        H0.b bVar = h10.l;
        w wVar = (w) bVar.f2765m;
        wVar.getClass();
        try {
            vVar = new v();
            vVar.f(wVar, strG);
        } catch (IllegalArgumentException unused) {
            vVar = null;
        }
        w wVarC = vVar != null ? vVar.c() : null;
        if (wVarC == null) {
            return null;
        }
        if (!m.a(wVarC.f12619a, ((w) bVar.f2765m).f12619a) && !c.f12485t) {
            return null;
        }
        q qVarL = bVar.L();
        if (h6.j.o(str)) {
            boolean zEquals = str.equals("PROPFIND");
            int i10 = h10.o;
            boolean z6 = zEquals || i10 == 308 || i10 == 307;
            if (str.equals("PROPFIND") || i10 == 308 || i10 == 307) {
                qVarL.J(str, z6 ? (AbstractC1274a) bVar.f2767p : null);
            } else {
                qVarL.J("GET", null);
            }
            if (!z6) {
                ((D) qVarL.o).x("Transfer-Encoding");
                ((D) qVarL.o).x("Content-Length");
                ((D) qVarL.o).x("Content-Type");
            }
        }
        if (!d9.b.a((w) bVar.f2765m, wVarC)) {
            ((D) qVarL.o).x("Authorization");
        }
        qVarL.f4399m = wVarC;
        return qVarL.r();
    }

    public boolean d(IOException iOException, g9.j jVar, H0.b bVar, boolean z6) {
        v vVar;
        boolean zE;
        g9.m mVar;
        if (!((C) this.f3107b).f12482q) {
            return false;
        }
        if ((z6 && (iOException instanceof FileNotFoundException)) || (iOException instanceof ProtocolException) || (!(iOException instanceof InterruptedIOException) ? !(((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) : (iOException instanceof SocketTimeoutException) && !z6)) {
            return false;
        }
        g9.f fVar = jVar.f15066t;
        m.c(fVar);
        int i7 = fVar.f15050g;
        if (i7 == 0 && fVar.f15051h == 0 && fVar.f15052i == 0) {
            zE = false;
        } else if (fVar.f15053j != null) {
            zE = true;
        } else {
            J j3 = null;
            if (i7 <= 1 && fVar.f15051h <= 1 && fVar.f15052i <= 0 && (mVar = fVar.c.f15067u) != null) {
                synchronized (mVar) {
                    if (mVar.l == 0 && d9.b.a(mVar.f15076b.f12528a.f12540h, fVar.f15047b.f12540h)) {
                        j3 = mVar.f15076b;
                    }
                }
            }
            if (j3 != null) {
                fVar.f15053j = j3;
            } else {
                E4.b bVar2 = fVar.f15048e;
                if ((bVar2 == null || !bVar2.e()) && (vVar = fVar.f15049f) != null) {
                    zE = vVar.e();
                }
            }
            zE = true;
        }
        return zE;
    }

    public H f(u uVar, boolean z6) throws d {
        H0.b bVar = (H0.b) uVar.f1964i;
        String path = ((w) bVar.f2765m).h().getPath();
        m.e(path, "getPath(...)");
        if (o.z0(path, "/api/login", false)) {
            return uVar.f(bVar);
        }
        h hVar = (h) this.f3107b;
        m.c(hVar);
        LoginInfo loginInfoA = hVar.a();
        if (loginInfoA != null) {
            String str = loginInfoA.f13163a;
            m.c(str);
            H hF = uVar.f(b(bVar, str).r());
            if (hF.j() || hF.o != 401) {
                return hF;
            }
            hVar.d(str, false);
            if (!z6) {
                B3.m mVar = hF.f12518r;
                String.valueOf(mVar);
                throw new d(mVar != null ? mVar.y() : hF.f12515n);
            }
            hF.close();
        }
        hVar.b();
        if (hVar.a() != null) {
            return f(uVar, false);
        }
        throw new d("Cannot get LoginInfo");
    }

    public i(C1041b c1041b) {
        m.f(c1041b, "cookieJar");
        this.f3107b = c1041b;
    }

    public i(Context context) {
        m.f(context, "context");
        this.f3107b = context;
    }

    public i(C c) {
        m.f(c, "client");
        this.f3107b = c;
    }
}
