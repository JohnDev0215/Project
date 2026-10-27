package t9;

import n3.z;
import w9.r;

/* JADX INFO: loaded from: classes.dex */
public final class n extends y9.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w9.p f22571a = new w9.p();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f22572b;
    public boolean c;

    public n(int i7) {
        this.f22572b = i7;
    }

    @Override // y9.a
    public final boolean b(w9.a aVar) {
        if (!this.c) {
            return true;
        }
        w9.a aVar2 = (w9.a) ((z) this.f22571a.f19921b);
        if (!(aVar2 instanceof w9.o)) {
            return true;
        }
        ((w9.o) aVar2).f23381g = false;
        return true;
    }

    @Override // y9.a
    public final w9.a d() {
        return this.f22571a;
    }

    @Override // y9.a
    public final boolean e() {
        return true;
    }

    @Override // y9.a
    public final P2.g g(f fVar) {
        if (fVar.f22531h) {
            if (((z) this.f22571a.c) == null) {
                return null;
            }
            w9.a aVarD = fVar.h().d();
            this.c = (aVarD instanceof r) || (aVarD instanceof w9.p);
            return P2.g.a(fVar.f22528e);
        }
        int i7 = fVar.f22530g;
        int i10 = this.f22572b;
        if (i7 >= i10) {
            return new P2.g(-1, fVar.c + i10, false);
        }
        return null;
    }
}
