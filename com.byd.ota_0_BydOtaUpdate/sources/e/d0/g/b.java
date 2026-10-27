package e.d0.g;

import e.a0;
import e.t;
import e.y;
import java.io.IOException;
import java.net.ProtocolException;

/* JADX INFO: compiled from: CallServerInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f3395a;

    public b(boolean z) {
        this.f3395a = z;
    }

    @Override // e.t
    public a0 a(t.a aVar) throws IOException {
        g gVar = (g) aVar;
        c cVarD = gVar.d();
        e.d0.f.f fVarF = gVar.f();
        e.d0.f.c cVar = (e.d0.f.c) gVar.c();
        y yVarB = gVar.b();
        long jCurrentTimeMillis = System.currentTimeMillis();
        cVarD.b(yVarB);
        a0.a aVarF = null;
        if (f.b(yVarB.f()) && yVarB.a() != null) {
            if ("100-continue".equalsIgnoreCase(yVarB.c("Expect"))) {
                cVarD.d();
                aVarF = cVarD.f(true);
            }
            if (aVarF == null) {
                f.d dVarA = f.k.a(cVarD.e(yVarB, yVarB.a().a()));
                yVarB.a().h(dVarA);
                dVarA.close();
            } else if (!cVar.p()) {
                fVarF.j();
            }
        }
        cVarD.a();
        if (aVarF == null) {
            aVarF = cVarD.f(false);
        }
        a0 a0VarC = aVarF.o(yVarB).h(fVarF.d().m()).p(jCurrentTimeMillis).n(System.currentTimeMillis()).c();
        int iS = a0VarC.s();
        a0 a0VarC2 = (this.f3395a && iS == 101) ? a0VarC.M().b(e.d0.c.f3338c).c() : a0VarC.M().b(cVarD.c(a0VarC)).c();
        if ("close".equalsIgnoreCase(a0VarC2.O().c("Connection")) || "close".equalsIgnoreCase(a0VarC2.H("Connection"))) {
            fVarF.j();
        }
        if ((iS != 204 && iS != 205) || a0VarC2.c().v() <= 0) {
            return a0VarC2;
        }
        throw new ProtocolException("HTTP " + iS + " had non-zero Content-Length: " + a0VarC2.c().v());
    }
}
