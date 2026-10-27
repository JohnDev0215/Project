package f.k0.i;

import f.e0;
import f.g0;
import f.y;
import java.io.IOException;
import java.net.ProtocolException;
import okio.BufferedSink;
import okio.Okio;

/* JADX INFO: compiled from: CallServerInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f5227a;

    public b(boolean z) {
        this.f5227a = z;
    }

    @Override // f.y
    public g0 a(y.a aVar) throws IOException {
        boolean z;
        g gVar = (g) aVar;
        f.k0.h.d dVarG = gVar.g();
        e0 e0VarA = gVar.a();
        long jCurrentTimeMillis = System.currentTimeMillis();
        dVarG.p(e0VarA);
        g0.a aVarL = null;
        if (!f.b(e0VarA.f()) || e0VarA.a() == null) {
            dVarG.j();
            z = false;
        } else {
            if ("100-continue".equalsIgnoreCase(e0VarA.c("Expect"))) {
                dVarG.g();
                dVarG.n();
                aVarL = dVarG.l(true);
                z = true;
            } else {
                z = false;
            }
            if (aVarL != null) {
                dVarG.j();
                if (!dVarG.c().o()) {
                    dVarG.i();
                }
            } else if (e0VarA.a().h()) {
                dVarG.g();
                e0VarA.a().j(Okio.b(dVarG.d(e0VarA, true)));
            } else {
                BufferedSink bufferedSinkB = Okio.b(dVarG.d(e0VarA, false));
                e0VarA.a().j(bufferedSinkB);
                bufferedSinkB.close();
            }
        }
        if (e0VarA.a() == null || !e0VarA.a().h()) {
            dVarG.f();
        }
        if (!z) {
            dVarG.n();
        }
        if (aVarL == null) {
            aVarL = dVarG.l(false);
        }
        g0 g0VarC = aVarL.q(e0VarA).h(dVarG.c().l()).r(jCurrentTimeMillis).p(System.currentTimeMillis()).c();
        int iJ = g0VarC.j();
        if (iJ == 100) {
            g0VarC = dVarG.l(false).q(e0VarA).h(dVarG.c().l()).r(jCurrentTimeMillis).p(System.currentTimeMillis()).c();
            iJ = g0VarC.j();
        }
        dVarG.m(g0VarC);
        g0 g0VarC2 = (this.f5227a && iJ == 101) ? g0VarC.V().b(f.k0.e.f5136d).c() : g0VarC.V().b(dVarG.k(g0VarC)).c();
        if ("close".equalsIgnoreCase(g0VarC2.Y().c("Connection")) || "close".equalsIgnoreCase(g0VarC2.C("Connection"))) {
            dVarG.i();
        }
        if ((iJ != 204 && iJ != 205) || g0VarC2.a().j() <= 0) {
            return g0VarC2;
        }
        throw new ProtocolException("HTTP " + iJ + " had non-zero Content-Length: " + g0VarC2.a().j());
    }
}
