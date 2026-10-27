package b.e.b.k.n;

import b.e.b.k.n.f;

/* JADX INFO: compiled from: DimensionDependency.java */
/* JADX INFO: loaded from: classes.dex */
class g extends f {
    public int m;

    public g(m mVar) {
        super(mVar);
        if (mVar instanceof j) {
            this.f2490e = f.a.HORIZONTAL_DIMENSION;
        } else {
            this.f2490e = f.a.VERTICAL_DIMENSION;
        }
    }

    @Override // b.e.b.k.n.f
    public void d(int i2) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.f2492g = i2;
        for (d dVar : this.k) {
            dVar.a(dVar);
        }
    }
}
