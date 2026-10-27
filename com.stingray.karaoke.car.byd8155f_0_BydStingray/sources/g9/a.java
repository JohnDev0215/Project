package g9;

import F.u;
import c9.C;
import c9.H;
import c9.x;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class a implements x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f15030a = new a();

    @Override // c9.x
    public final H a(u uVar) throws IOException {
        j jVar = (j) uVar.f1962g;
        jVar.getClass();
        synchronized (jVar) {
            try {
                if (!jVar.f15072z) {
                    throw new IllegalStateException("released");
                }
                if (jVar.f15071y) {
                    throw new IllegalStateException("Check failed.");
                }
                if (jVar.f15070x) {
                    throw new IllegalStateException("Check failed.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        f fVar = jVar.f15066t;
        A7.m.c(fVar);
        C c = jVar.l;
        A7.m.f(c, "client");
        try {
            e eVar = new e(jVar, jVar.f15062p, fVar, fVar.a(uVar.c, uVar.d, uVar.f1960e, c.f12482q, !A7.m.a((String) ((H0.b) uVar.f1964i).f2766n, "GET")).j(c, uVar));
            jVar.f15069w = eVar;
            jVar.B = eVar;
            synchronized (jVar) {
                jVar.f15070x = true;
                jVar.f15071y = true;
            }
            if (jVar.f15058A) {
                throw new IOException("Canceled");
            }
            return u.a(uVar, 0, eVar, null, 61).f((H0.b) uVar.f1964i);
        } catch (o e10) {
            fVar.c(e10.f15090m);
            throw e10;
        } catch (IOException e11) {
            fVar.c(e11);
            throw new o(e11);
        }
    }
}
