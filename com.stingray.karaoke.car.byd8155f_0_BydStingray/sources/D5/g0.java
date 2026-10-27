package d5;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends M {
    public final transient K o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient h0 f13423p;

    public g0(K k10, h0 h0Var) {
        this.o = k10;
        this.f13423p = h0Var;
    }

    @Override // d5.M, d5.AbstractC1075D
    public final I a() {
        return this.f13423p;
    }

    @Override // d5.AbstractC1075D, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.o.get(obj) != null;
    }

    @Override // d5.AbstractC1075D
    public final int d(int i7, Object[] objArr) {
        return this.f13423p.d(i7, objArr);
    }

    @Override // d5.AbstractC1075D
    public final boolean r() {
        return true;
    }

    @Override // d5.AbstractC1075D
    /* JADX INFO: renamed from: s */
    public final s0 iterator() {
        return this.f13423p.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.o.size();
    }
}
