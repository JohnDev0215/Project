package t9;

/* JADX INFO: loaded from: classes.dex */
public final class m extends y9.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w9.o f22569a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f22570b;
    public int c;

    public m(w9.o oVar) {
        this.f22569a = oVar;
    }

    @Override // y9.a
    public final boolean b(w9.a aVar) {
        if (!(aVar instanceof w9.p)) {
            return false;
        }
        if (this.f22570b && this.c == 1) {
            this.f22569a.f23381g = false;
            this.f22570b = false;
        }
        return true;
    }

    @Override // y9.a
    public final w9.a d() {
        return this.f22569a;
    }

    @Override // y9.a
    public final boolean e() {
        return true;
    }

    @Override // y9.a
    public final P2.g g(f fVar) {
        if (fVar.f22531h) {
            this.f22570b = true;
            this.c = 0;
        } else if (this.f22570b) {
            this.c++;
        }
        return P2.g.a(fVar.f22527b);
    }
}
