package e.d0.e;

import com.byd.ota.master.VehicleVersionInfo;
import e.a0;
import e.d0.e.c;
import e.d0.g.f;
import e.d0.g.h;
import e.s;
import e.t;
import e.y;
import f.e;
import f.k;
import f.p;
import f.q;
import f.r;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;

/* JADX INFO: compiled from: CacheInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f3347a;

    /* JADX INFO: renamed from: e.d0.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CacheInterceptor.java */
    public class C0080a implements q {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f3348c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ e f3349d;
        public final /* synthetic */ b s;
        public final /* synthetic */ f.d x;

        public C0080a(e eVar, b bVar, f.d dVar) {
            this.f3349d = eVar;
            this.s = bVar;
            this.x = dVar;
        }

        @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.f3348c && !e.d0.c.i(this, 100, TimeUnit.MILLISECONDS)) {
                this.f3348c = true;
                this.s.a();
            }
            this.f3349d.close();
        }

        @Override // f.q
        public r e() {
            return this.f3349d.e();
        }

        @Override // f.q
        public long n(f.c cVar, long j) throws IOException {
            try {
                long jN = this.f3349d.n(cVar, j);
                if (jN != -1) {
                    cVar.K(this.x.a(), cVar.Y() - jN, jN);
                    this.x.l();
                    return jN;
                }
                if (!this.f3348c) {
                    this.f3348c = true;
                    this.x.close();
                }
                return -1L;
            } catch (IOException e2) {
                if (!this.f3348c) {
                    this.f3348c = true;
                    this.s.a();
                }
                throw e2;
            }
        }
    }

    public a(d dVar) {
        this.f3347a = dVar;
    }

    public static s c(s sVar, s sVar2) {
        s.a aVar = new s.a();
        int iF = sVar.f();
        for (int i2 = 0; i2 < iF; i2++) {
            String strC = sVar.c(i2);
            String strG = sVar.g(i2);
            if ((!"Warning".equalsIgnoreCase(strC) || !strG.startsWith(VehicleVersionInfo.FORCE_UPGRADE)) && (!d(strC) || sVar2.a(strC) == null)) {
                e.d0.a.f3334a.b(aVar, strC, strG);
            }
        }
        int iF2 = sVar2.f();
        for (int i3 = 0; i3 < iF2; i3++) {
            String strC2 = sVar2.c(i3);
            if (!"Content-Length".equalsIgnoreCase(strC2) && d(strC2)) {
                e.d0.a.f3334a.b(aVar, strC2, sVar2.g(i3));
            }
        }
        return aVar.d();
    }

    public static boolean d(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    public static a0 e(a0 a0Var) {
        return (a0Var == null || a0Var.c() == null) ? a0Var : a0Var.M().b(null).c();
    }

    @Override // e.t
    public a0 a(t.a aVar) throws IOException {
        d dVar = this.f3347a;
        a0 a0VarB = dVar != null ? dVar.b(aVar.b()) : null;
        c cVarC = new c.a(System.currentTimeMillis(), aVar.b(), a0VarB).c();
        y yVar = cVarC.f3350a;
        a0 a0Var = cVarC.f3351b;
        d dVar2 = this.f3347a;
        if (dVar2 != null) {
            dVar2.d(cVarC);
        }
        if (a0VarB != null && a0Var == null) {
            e.d0.c.c(a0VarB.c());
        }
        if (yVar == null && a0Var == null) {
            return new a0.a().o(aVar.b()).m(Protocol.HTTP_1_1).g(504).j("Unsatisfiable Request (only-if-cached)").b(e.d0.c.f3338c).p(-1L).n(System.currentTimeMillis()).c();
        }
        if (yVar == null) {
            return a0Var.M().d(e(a0Var)).c();
        }
        try {
            a0 a0VarA = aVar.a(yVar);
            if (a0VarA == null && a0VarB != null) {
            }
            if (a0Var != null) {
                if (a0VarA.s() == 304) {
                    a0 a0VarC = a0Var.M().i(c(a0Var.J(), a0VarA.J())).p(a0VarA.P()).n(a0VarA.N()).d(e(a0Var)).k(e(a0VarA)).c();
                    a0VarA.c().close();
                    this.f3347a.c();
                    this.f3347a.a(a0Var, a0VarC);
                    return a0VarC;
                }
                e.d0.c.c(a0Var.c());
            }
            a0 a0VarC2 = a0VarA.M().d(e(a0Var)).k(e(a0VarA)).c();
            if (this.f3347a != null) {
                if (e.d0.g.e.c(a0VarC2) && c.a(a0VarC2, yVar)) {
                    return b(this.f3347a.f(a0VarC2), a0VarC2);
                }
                if (f.a(yVar.f())) {
                    try {
                        this.f3347a.e(yVar);
                    } catch (IOException unused) {
                    }
                }
            }
            return a0VarC2;
        } finally {
            if (a0VarB != null) {
                e.d0.c.c(a0VarB.c());
            }
        }
    }

    public final a0 b(b bVar, a0 a0Var) throws IOException {
        p pVarB;
        return (bVar == null || (pVarB = bVar.b()) == null) ? a0Var : a0Var.M().b(new h(a0Var.J(), k.b(new C0080a(a0Var.c().K(), bVar, k.a(pVarB))))).c();
    }
}
