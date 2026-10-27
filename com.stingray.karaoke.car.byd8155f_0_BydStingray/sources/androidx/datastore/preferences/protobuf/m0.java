package androidx.datastore.preferences.protobuf;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class m0 extends AbstractList implements B, RandomAccess {
    public final A l;

    public m0(A a10) {
        this.l = a10;
    }

    @Override // androidx.datastore.preferences.protobuf.B
    public final B b() {
        return this;
    }

    @Override // androidx.datastore.preferences.protobuf.B
    public final List c() {
        return Collections.unmodifiableList(this.l.f11780m);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        return (String) this.l.get(i7);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        l0 l0Var = new l0();
        l0Var.l = this.l.iterator();
        return l0Var;
    }

    @Override // androidx.datastore.preferences.protobuf.B
    public final void j(C0944g c0944g) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i7) {
        k0 k0Var = new k0();
        k0Var.l = this.l.listIterator(i7);
        return k0Var;
    }

    @Override // androidx.datastore.preferences.protobuf.B
    public final Object n(int i7) {
        return this.l.f11780m.get(i7);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.l.size();
    }
}
