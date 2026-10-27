package i6;

import c6.a0;
import c6.b0;
import c6.c0;
import c6.v;
import c6.z;
import java.io.IOException;
import java.net.ProtocolException;
import kotlin.jvm.internal.l;
import p6.o;
import y5.p;

/* JADX INFO: compiled from: CallServerInterceptor.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f7045a;

    public b(boolean z6) {
        this.f7045a = z6;
    }

    @Override // c6.v
    public b0 a(v.a chain) throws IOException {
        b0.a aVarP;
        boolean z6;
        l.e(chain, "chain");
        g gVar = (g) chain;
        h6.c cVarG = gVar.g();
        l.c(cVarG);
        z zVarI = gVar.i();
        a0 a0VarA = zVarI.a();
        long jCurrentTimeMillis = System.currentTimeMillis();
        cVarG.t(zVarI);
        if (!f.a(zVarI.g()) || a0VarA == null) {
            cVarG.n();
            aVarP = null;
            z6 = true;
        } else {
            if (p.o("100-continue", zVarI.d("Expect"), true)) {
                cVarG.f();
                aVarP = cVarG.p(true);
                cVarG.r();
                z6 = false;
            } else {
                aVarP = null;
                z6 = true;
            }
            if (aVarP != null) {
                cVarG.n();
                if (!cVarG.h().v()) {
                    cVarG.m();
                }
            } else if (a0VarA.c()) {
                cVarG.f();
                a0VarA.e(o.a(cVarG.c(zVarI, true)));
            } else {
                p6.f fVarA = o.a(cVarG.c(zVarI, false));
                a0VarA.e(fVarA);
                fVarA.close();
            }
        }
        if (a0VarA == null || !a0VarA.c()) {
            cVarG.e();
        }
        if (aVarP == null) {
            aVarP = cVarG.p(false);
            l.c(aVarP);
            if (z6) {
                cVarG.r();
                z6 = false;
            }
        }
        b0 b0VarC = aVarP.r(zVarI).i(cVarG.h().r()).s(jCurrentTimeMillis).q(System.currentTimeMillis()).c();
        int I = b0VarC.I();
        if (I == 100) {
            b0.a aVarP2 = cVarG.p(false);
            l.c(aVarP2);
            if (z6) {
                cVarG.r();
            }
            b0VarC = aVarP2.r(zVarI).i(cVarG.h().r()).s(jCurrentTimeMillis).q(System.currentTimeMillis()).c();
            I = b0VarC.I();
        }
        cVarG.q(b0VarC);
        b0 b0VarC2 = (this.f7045a && I == 101) ? b0VarC.c0().b(d6.b.f5962c).c() : b0VarC.c0().b(cVarG.o(b0VarC)).c();
        if (p.o("close", b0VarC2.g0().d("Connection"), true) || p.o("close", b0.U(b0VarC2, "Connection", null, 2, null), true)) {
            cVarG.m();
        }
        if (I == 204 || I == 205) {
            c0 c0VarM = b0VarC2.m();
            if ((c0VarM != null ? c0VarM.z() : -1L) > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("HTTP ");
                sb.append(I);
                sb.append(" had non-zero Content-Length: ");
                c0 c0VarM2 = b0VarC2.m();
                sb.append(c0VarM2 != null ? Long.valueOf(c0VarM2.z()) : null);
                throw new ProtocolException(sb.toString());
            }
        }
        return b0VarC2;
    }
}
