package a.e.b.k.m;

import a.e.b.k.m.f;

/* JADX INFO: loaded from: classes.dex */
class g extends f {
    public int m;

    public g(m mVar) {
        super(mVar);
        this.e = mVar instanceof j ? f.a.HORIZONTAL_DIMENSION : f.a.VERTICAL_DIMENSION;
    }

    @Override // a.e.b.k.m.f
    public void d(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        for (d dVar : this.k) {
            dVar.a(dVar);
        }
    }
}
