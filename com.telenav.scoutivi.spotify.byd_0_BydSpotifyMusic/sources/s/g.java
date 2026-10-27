package s;

import s.f;

/* JADX INFO: compiled from: DimensionDependency.java */
/* JADX INFO: loaded from: classes.dex */
class g extends f {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9420m;

    public g(p pVar) {
        super(pVar);
        if (pVar instanceof l) {
            this.f9411e = f.a.HORIZONTAL_DIMENSION;
        } else {
            this.f9411e = f.a.VERTICAL_DIMENSION;
        }
    }

    @Override // s.f
    public void d(int i7) {
        if (this.f9416j) {
            return;
        }
        this.f9416j = true;
        this.f9413g = i7;
        for (d dVar : this.f9417k) {
            dVar.a(dVar);
        }
    }
}
