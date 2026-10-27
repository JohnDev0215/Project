package f6;

import c6.b0;
import c6.c;
import c6.c0;
import c6.e;
import c6.r;
import c6.t;
import c6.v;
import c6.y;
import c6.z;
import f6.b;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.l;
import y5.p;

/* JADX INFO: compiled from: CacheInterceptor.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0075a f6407a = new C0075a(null);

    /* JADX INFO: renamed from: f6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CacheInterceptor.kt */
    public static final class C0075a {
        private C0075a() {
        }

        public /* synthetic */ C0075a(g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final t c(t tVar, t tVar2) {
            t.a aVar = new t.a();
            int size = tVar.size();
            for (int i7 = 0; i7 < size; i7++) {
                String strD = tVar.d(i7);
                String strF = tVar.f(i7);
                if ((!p.o("Warning", strD, true) || !p.A(strF, "1", false, 2, null)) && (d(strD) || !e(strD) || tVar2.c(strD) == null)) {
                    aVar.c(strD, strF);
                }
            }
            int size2 = tVar2.size();
            for (int i8 = 0; i8 < size2; i8++) {
                String strD2 = tVar2.d(i8);
                if (!d(strD2) && e(strD2)) {
                    aVar.c(strD2, tVar2.f(i8));
                }
            }
            return aVar.d();
        }

        private final boolean d(String str) {
            return p.o("Content-Length", str, true) || p.o("Content-Encoding", str, true) || p.o("Content-Type", str, true);
        }

        private final boolean e(String str) {
            return (p.o("Connection", str, true) || p.o("Keep-Alive", str, true) || p.o("Proxy-Authenticate", str, true) || p.o("Proxy-Authorization", str, true) || p.o("TE", str, true) || p.o("Trailers", str, true) || p.o("Transfer-Encoding", str, true) || p.o("Upgrade", str, true)) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final b0 f(b0 b0Var) {
            return (b0Var != null ? b0Var.m() : null) != null ? b0Var.c0().b(null).c() : b0Var;
        }
    }

    public a(c cVar) {
    }

    @Override // c6.v
    public b0 a(v.a chain) {
        r rVarL;
        l.e(chain, "chain");
        e eVarCall = chain.call();
        b bVarB = new b.C0076b(System.currentTimeMillis(), chain.a(), null).b();
        z zVarB = bVarB.b();
        b0 b0VarA = bVarB.a();
        h6.e eVar = (h6.e) (!(eVarCall instanceof h6.e) ? null : eVarCall);
        if (eVar == null || (rVarL = eVar.l()) == null) {
            rVarL = r.f3834a;
        }
        if (zVarB == null && b0VarA == null) {
            b0 b0VarC = new b0.a().r(chain.a()).p(y.HTTP_1_1).g(504).m("Unsatisfiable Request (only-if-cached)").b(d6.b.f5962c).s(-1L).q(System.currentTimeMillis()).c();
            rVarL.z(eVarCall, b0VarC);
            return b0VarC;
        }
        if (zVarB == null) {
            l.c(b0VarA);
            b0 b0VarC2 = b0VarA.c0().d(f6407a.f(b0VarA)).c();
            rVarL.b(eVarCall, b0VarC2);
            return b0VarC2;
        }
        if (b0VarA != null) {
            rVarL.a(eVarCall, b0VarA);
        }
        b0 b0VarB = chain.b(zVarB);
        if (b0VarA != null) {
            if (b0VarB != null && b0VarB.I() == 304) {
                b0.a aVarC0 = b0VarA.c0();
                C0075a c0075a = f6407a;
                aVarC0.k(c0075a.c(b0VarA.V(), b0VarB.V())).s(b0VarB.h0()).q(b0VarB.f0()).d(c0075a.f(b0VarA)).n(c0075a.f(b0VarB)).c();
                c0 c0VarM = b0VarB.m();
                l.c(c0VarM);
                c0VarM.close();
                l.c(null);
                throw null;
            }
            c0 c0VarM2 = b0VarA.m();
            if (c0VarM2 != null) {
                d6.b.i(c0VarM2);
            }
        }
        l.c(b0VarB);
        b0.a aVarC02 = b0VarB.c0();
        C0075a c0075a2 = f6407a;
        return aVarC02.d(c0075a2.f(b0VarA)).n(c0075a2.f(b0VarB)).c();
    }
}
