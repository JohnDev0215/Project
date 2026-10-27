package f.b0.g;

import f.r;
import f.w;
import f.y;
import java.io.IOException;
import java.net.ProtocolException;

/* JADX INFO: compiled from: CallServerInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f5074a;

    public b(boolean z) {
        this.f5074a = z;
    }

    @Override // f.r
    public y a(r.a aVar) throws IOException {
        g gVar = (g) aVar;
        c cVarC = gVar.c();
        f.b0.f.f fVarF = gVar.f();
        w wVarB = aVar.b();
        long jCurrentTimeMillis = System.currentTimeMillis();
        cVarC.b(wVarB);
        y.a aVarF = null;
        if (f.b(wVarB.f()) && wVarB.a() != null) {
            if ("100-continue".equalsIgnoreCase(wVarB.c("Expect"))) {
                cVarC.d();
                aVarF = cVarC.f(true);
            }
            if (aVarF == null) {
                g.d dVarA = g.j.a(cVarC.e(wVarB, wVarB.a().contentLength()));
                wVarB.a().writeTo(dVarA);
                dVarA.close();
            }
        }
        cVarC.a();
        if (aVarF == null) {
            aVarF = cVarC.f(false);
        }
        y yVarC = aVarF.o(wVarB).h(fVarF.d().l()).p(jCurrentTimeMillis).n(System.currentTimeMillis()).c();
        int iO = yVarC.O();
        y yVarC2 = (this.f5074a && iO == 101) ? yVarC.U().b(f.b0.c.f5014c).c() : yVarC.U().b(cVarC.c(yVarC)).c();
        if ("close".equalsIgnoreCase(yVarC2.W().c("Connection")) || "close".equalsIgnoreCase(yVarC2.Q("Connection"))) {
            fVarF.j();
        }
        if ((iO != 204 && iO != 205) || yVarC2.w().O() <= 0) {
            return yVarC2;
        }
        throw new ProtocolException("HTTP " + iO + " had non-zero Content-Length: " + yVarC2.w().O());
    }
}
