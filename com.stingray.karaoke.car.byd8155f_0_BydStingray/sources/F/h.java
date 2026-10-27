package F;

import C0.b0;
import D.C0130l;
import G.E;
import G.I;
import T.C0754m0;
import T.C0761q;

/* JADX INFO: loaded from: classes.dex */
public final class h implements I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y f1881a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f1882b;
    public final B5.u c;

    public h(y yVar, g gVar, B5.u uVar) {
        this.f1881a = yVar;
        this.f1882b = gVar;
        this.c = uVar;
    }

    @Override // G.I
    public final int a(Object obj) {
        return this.c.a(obj);
    }

    @Override // G.I
    public final int b() {
        return this.f1882b.j().f735m;
    }

    @Override // G.I
    public final Object c(int i7) {
        Object objO = this.c.o(i7);
        return objO == null ? this.f1882b.k(i7) : objO;
    }

    @Override // G.I
    public final Object d(int i7) {
        return this.f1882b.i(i7);
    }

    @Override // G.I
    public final void e(int i7, Object obj, C0761q c0761q, int i10) {
        int i11;
        c0761q.S(1493551140);
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
            E.b(obj, i7, this.f1881a.o, b0.g.b(726189336, new C0130l(i7, 2, this), c0761q), c0761q, ((i11 >> 3) & 14) | 3072 | ((i11 << 3) & 112));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new b0(this, i7, obj, i10, 2);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        return A7.m.a(this.f1882b, ((h) obj).f1882b);
    }

    public final int hashCode() {
        return this.f1882b.hashCode();
    }
}
