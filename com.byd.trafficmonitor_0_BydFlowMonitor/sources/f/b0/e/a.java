package f.b0.e;

import f.b0.e.c;
import f.b0.g.f;
import f.b0.g.h;
import f.q;
import f.r;
import f.w;
import f.y;
import g.e;
import g.j;
import g.o;
import g.p;
import g.q;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;

/* JADX INFO: compiled from: CacheInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f5023a;

    /* JADX INFO: renamed from: f.b0.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CacheInterceptor.java */
    public class C0100a implements p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f5024b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ e f5025c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b f5026d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ g.d f5027e;

        public C0100a(e eVar, b bVar, g.d dVar) {
            this.f5025c = eVar;
            this.f5026d = bVar;
            this.f5027e = dVar;
        }

        @Override // g.p
        public q b() {
            return this.f5025c.b();
        }

        @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.f5024b && !f.b0.c.i(this, 100, TimeUnit.MILLISECONDS)) {
                this.f5024b = true;
                this.f5026d.a();
            }
            this.f5025c.close();
        }

        @Override // g.p
        public long m(g.c cVar, long j) throws IOException {
            try {
                long jM = this.f5025c.m(cVar, j);
                if (jM != -1) {
                    cVar.R(this.f5027e.a(), cVar.f0() - jM, jM);
                    this.f5027e.k();
                    return jM;
                }
                if (!this.f5024b) {
                    this.f5024b = true;
                    this.f5027e.close();
                }
                return -1L;
            } catch (IOException e2) {
                if (!this.f5024b) {
                    this.f5024b = true;
                    this.f5026d.a();
                }
                throw e2;
            }
        }
    }

    public a(d dVar) {
        this.f5023a = dVar;
    }

    public static f.q c(f.q qVar, f.q qVar2) {
        q.a aVar = new q.a();
        int iF = qVar.f();
        for (int i2 = 0; i2 < iF; i2++) {
            String strC = qVar.c(i2);
            String strG = qVar.g(i2);
            if ((!"Warning".equalsIgnoreCase(strC) || !strG.startsWith("1")) && (!d(strC) || qVar2.a(strC) == null)) {
                f.b0.a.f5010a.b(aVar, strC, strG);
            }
        }
        int iF2 = qVar2.f();
        for (int i3 = 0; i3 < iF2; i3++) {
            String strC2 = qVar2.c(i3);
            if (!"Content-Length".equalsIgnoreCase(strC2) && d(strC2)) {
                f.b0.a.f5010a.b(aVar, strC2, qVar2.g(i3));
            }
        }
        return aVar.d();
    }

    public static boolean d(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    public static y f(y yVar) {
        return (yVar == null || yVar.w() == null) ? yVar : yVar.U().b(null).c();
    }

    @Override // f.r
    public y a(r.a aVar) throws IOException {
        d dVar = this.f5023a;
        y yVarB = dVar != null ? dVar.b(aVar.b()) : null;
        c cVarC = new c.a(System.currentTimeMillis(), aVar.b(), yVarB).c();
        w wVar = cVarC.f5029a;
        y yVar = cVarC.f5030b;
        d dVar2 = this.f5023a;
        if (dVar2 != null) {
            dVar2.d(cVarC);
        }
        if (yVarB != null && yVar == null) {
            f.b0.c.c(yVarB.w());
        }
        if (wVar == null && yVar == null) {
            return new y.a().o(aVar.b()).m(Protocol.HTTP_1_1).g(504).j("Unsatisfiable Request (only-if-cached)").b(f.b0.c.f5014c).p(-1L).n(System.currentTimeMillis()).c();
        }
        if (wVar == null) {
            return yVar.U().d(f(yVar)).c();
        }
        try {
            y yVarA = aVar.a(wVar);
            if (yVarA == null && yVarB != null) {
            }
            if (yVar != null) {
                if (yVarA.O() == 304) {
                    y yVarC = yVar.U().i(c(yVar.S(), yVarA.S())).p(yVarA.X()).n(yVarA.V()).d(f(yVar)).k(f(yVarA)).c();
                    yVarA.w().close();
                    this.f5023a.c();
                    this.f5023a.a(yVar, yVarC);
                    return yVarC;
                }
                f.b0.c.c(yVar.w());
            }
            y yVarC2 = yVarA.U().d(f(yVar)).k(f(yVarA)).c();
            return f.b0.g.e.c(yVarC2) ? b(e(yVarC2, yVarA.W(), this.f5023a), yVarC2) : yVarC2;
        } finally {
            if (yVarB != null) {
                f.b0.c.c(yVarB.w());
            }
        }
    }

    public final y b(b bVar, y yVar) throws IOException {
        o oVarB;
        return (bVar == null || (oVarB = bVar.b()) == null) ? yVar : yVar.U().b(new h(yVar.S(), j.b(new C0100a(yVar.w().S(), bVar, j.a(oVarB))))).c();
    }

    public final b e(y yVar, w wVar, d dVar) throws IOException {
        if (dVar == null) {
            return null;
        }
        if (c.a(yVar, wVar)) {
            return dVar.f(yVar);
        }
        if (f.a(wVar.f())) {
            try {
                dVar.e(wVar);
            } catch (IOException unused) {
            }
        }
        return null;
    }
}
