package f.k0.g;

import f.c0;
import f.e0;
import f.g0;
import f.k0.e;
import f.k0.g.c;
import f.k0.i.f;
import f.k0.i.h;
import f.w;
import f.y;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* JADX INFO: compiled from: CacheInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    final d f5142a;

    /* JADX INFO: renamed from: f.k0.g.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CacheInterceptor.java */
    class C0098a implements Source {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f5143b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ BufferedSource f5144c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b f5145d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ BufferedSink f5146e;

        C0098a(BufferedSource bufferedSource, b bVar, BufferedSink bufferedSink) {
            this.f5144c = bufferedSource;
            this.f5145d = bVar;
            this.f5146e = bufferedSink;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (!this.f5143b && !e.o(this, 100, TimeUnit.MILLISECONDS)) {
                this.f5143b = true;
                this.f5145d.a();
            }
            this.f5144c.close();
        }

        @Override // okio.Source
        public Timeout e() {
            return this.f5144c.e();
        }

        @Override // okio.Source
        public long p(Buffer buffer, long j) throws IOException {
            try {
                long jP = this.f5144c.p(buffer, j);
                if (jP != -1) {
                    buffer.U(this.f5146e.b(), buffer.g0() - jP, jP);
                    this.f5146e.n();
                    return jP;
                }
                if (!this.f5143b) {
                    this.f5143b = true;
                    this.f5146e.close();
                }
                return -1L;
            } catch (IOException e2) {
                if (!this.f5143b) {
                    this.f5143b = true;
                    this.f5145d.a();
                }
                throw e2;
            }
        }
    }

    public a(@Nullable d dVar) {
        this.f5142a = dVar;
    }

    private g0 b(b bVar, g0 g0Var) {
        Sink sinkB;
        if (bVar == null || (sinkB = bVar.b()) == null) {
            return g0Var;
        }
        return g0Var.V().b(new h(g0Var.C("Content-Type"), g0Var.a().j(), Okio.c(new C0098a(g0Var.a().S(), bVar, Okio.b(sinkB))))).c();
    }

    private static w c(w wVar, w wVar2) {
        w.a aVar = new w.a();
        int iH = wVar.h();
        for (int i2 = 0; i2 < iH; i2++) {
            String strE = wVar.e(i2);
            String strI = wVar.i(i2);
            if ((!"Warning".equalsIgnoreCase(strE) || !strI.startsWith("1")) && (d(strE) || !e(strE) || wVar2.c(strE) == null)) {
                f.k0.c.f5131a.b(aVar, strE, strI);
            }
        }
        int iH2 = wVar2.h();
        for (int i3 = 0; i3 < iH2; i3++) {
            String strE2 = wVar2.e(i3);
            if (!d(strE2) && e(strE2)) {
                f.k0.c.f5131a.b(aVar, strE2, wVar2.i(i3));
            }
        }
        return aVar.f();
    }

    static boolean d(String str) {
        return "Content-Length".equalsIgnoreCase(str) || "Content-Encoding".equalsIgnoreCase(str) || "Content-Type".equalsIgnoreCase(str);
    }

    static boolean e(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    private static g0 f(g0 g0Var) {
        return (g0Var == null || g0Var.a() == null) ? g0Var : g0Var.V().b(null).c();
    }

    @Override // f.y
    public g0 a(y.a aVar) {
        d dVar = this.f5142a;
        g0 g0VarB = dVar != null ? dVar.b(aVar.a()) : null;
        c cVarC = new c.a(System.currentTimeMillis(), aVar.a(), g0VarB).c();
        e0 e0Var = cVarC.f5148a;
        g0 g0Var = cVarC.f5149b;
        d dVar2 = this.f5142a;
        if (dVar2 != null) {
            dVar2.d(cVarC);
        }
        if (g0VarB != null && g0Var == null) {
            e.f(g0VarB.a());
        }
        if (e0Var == null && g0Var == null) {
            return new g0.a().q(aVar.a()).o(c0.HTTP_1_1).g(504).l("Unsatisfiable Request (only-if-cached)").b(e.f5136d).r(-1L).p(System.currentTimeMillis()).c();
        }
        if (e0Var == null) {
            return g0Var.V().d(f(g0Var)).c();
        }
        try {
            g0 g0VarD = aVar.d(e0Var);
            if (g0VarD == null && g0VarB != null) {
            }
            if (g0Var != null) {
                if (g0VarD.j() == 304) {
                    g0 g0VarC = g0Var.V().j(c(g0Var.S(), g0VarD.S())).r(g0VarD.Z()).p(g0VarD.X()).d(f(g0Var)).m(f(g0VarD)).c();
                    g0VarD.a().close();
                    this.f5142a.c();
                    this.f5142a.a(g0Var, g0VarC);
                    return g0VarC;
                }
                e.f(g0Var.a());
            }
            g0 g0VarC2 = g0VarD.V().d(f(g0Var)).m(f(g0VarD)).c();
            if (this.f5142a != null) {
                if (f.k0.i.e.c(g0VarC2) && c.a(g0VarC2, e0Var)) {
                    return b(this.f5142a.f(g0VarC2), g0VarC2);
                }
                if (f.a(e0Var.f())) {
                    try {
                        this.f5142a.e(e0Var);
                    } catch (IOException unused) {
                    }
                }
            }
            return g0VarC2;
        } finally {
            if (g0VarB != null) {
                e.f(g0VarB.a());
            }
        }
    }
}
