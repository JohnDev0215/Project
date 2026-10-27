package d5;

/* JADX INFO: loaded from: classes.dex */
public final class q0 extends M {
    public final transient Object o;

    public q0(Object obj) {
        obj.getClass();
        this.o = obj;
    }

    @Override // d5.M, d5.AbstractC1075D
    public final I a() {
        return I.B(this.o);
    }

    @Override // d5.AbstractC1075D, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.o.equals(obj);
    }

    @Override // d5.AbstractC1075D
    public final int d(int i7, Object[] objArr) {
        objArr[i7] = this.o;
        return i7 + 1;
    }

    @Override // d5.M, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.o.hashCode();
    }

    @Override // d5.AbstractC1075D
    public final boolean r() {
        return false;
    }

    @Override // d5.AbstractC1075D
    /* JADX INFO: renamed from: s */
    public final s0 iterator() {
        return new P(this.o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return "[" + this.o.toString() + ']';
    }
}
