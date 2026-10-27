package u2;

import h2.AbstractC1343A;
import io.sentry.C1458d0;
import java.io.IOException;
import l5.C1778f;

/* JADX INFO: loaded from: classes.dex */
public final class h0 implements a0 {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22836m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j0 f22837n;

    public h0(j0 j0Var) {
        this.f22837n = j0Var;
    }

    public final void a() {
        if (this.f22836m) {
            return;
        }
        j0 j0Var = this.f22837n;
        B5.u uVar = j0Var.f22847p;
        int i7 = 26;
        uVar.k(new C1458d0(uVar, i7, new C2521y(1, e2.L.g(j0Var.f22852u.f14316n), j0Var.f22852u, AbstractC1343A.T(0L), -9223372036854775807L)));
        this.f22836m = true;
    }

    @Override // u2.a0
    public final boolean k() {
        return this.f22837n.f22854w;
    }

    @Override // u2.a0
    public final int p(C1778f c1778f, m2.g gVar, int i7) {
        a();
        j0 j0Var = this.f22837n;
        boolean z6 = j0Var.f22854w;
        if (z6 && j0Var.f22855x == null) {
            this.l = 2;
        }
        int i10 = this.l;
        if (i10 == 2) {
            gVar.b(4);
            return -4;
        }
        if ((i7 & 2) != 0 || i10 == 0) {
            c1778f.f18639n = j0Var.f22852u;
            this.l = 1;
            return -5;
        }
        if (!z6) {
            return -3;
        }
        j0Var.f22855x.getClass();
        gVar.b(1);
        gVar.f18957r = 0L;
        if ((i7 & 4) == 0) {
            gVar.j(j0Var.f22856y);
            gVar.f18955p.put(j0Var.f22855x, 0, j0Var.f22856y);
        }
        if ((i7 & 1) == 0) {
            this.l = 2;
        }
        return -4;
    }

    @Override // u2.a0
    public final void r() throws IOException {
        IOException iOException;
        j0 j0Var = this.f22837n;
        if (j0Var.f22853v) {
            return;
        }
        x2.n nVar = j0Var.f22851t;
        IOException iOException2 = nVar.c;
        if (iOException2 != null) {
            throw iOException2;
        }
        x2.k kVar = nVar.f23758b;
        if (kVar != null && (iOException = kVar.o) != null && kVar.f23751p > kVar.l) {
            throw iOException;
        }
    }

    @Override // u2.a0
    public final int s(long j3) {
        a();
        if (j3 <= 0 || this.l == 2) {
            return 0;
        }
        this.l = 2;
        return 1;
    }
}
