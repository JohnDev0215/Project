package E;

import C0.b0;
import D.C0130l;
import G.I;
import T.C0754m0;
import T.C0761q;

/* JADX INFO: loaded from: classes.dex */
public final class l implements I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D f1361a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f1362b;
    public final C0147d c;
    public final B5.u d;

    public l(D d, k kVar, C0147d c0147d, B5.u uVar) {
        this.f1361a = d;
        this.f1362b = kVar;
        this.c = c0147d;
        this.d = uVar;
    }

    @Override // G.I
    public final int a(Object obj) {
        return this.d.a(obj);
    }

    @Override // G.I
    public final int b() {
        return this.f1362b.j().f735m;
    }

    @Override // G.I
    public final Object c(int i7) {
        Object objO = this.d.o(i7);
        return objO == null ? this.f1362b.k(i7) : objO;
    }

    @Override // G.I
    public final Object d(int i7) {
        return this.f1362b.i(i7);
    }

    @Override // G.I
    public final void e(int i7, Object obj, C0761q c0761q, int i10) {
        int i11;
        c0761q.S(-462424778);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.d(i7) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.h(obj) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.f(this) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            G.E.b(obj, i7, this.f1361a.f1315r, b0.g.b(-824725566, new C0130l(i7, 1, this), c0761q), c0761q, ((i11 >> 3) & 14) | 3072 | ((i11 << 3) & 112));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new b0(this, i7, obj, i10, 1);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        return A7.m.a(this.f1362b, ((l) obj).f1362b);
    }

    public final int hashCode() {
        return this.f1362b.hashCode();
    }
}
