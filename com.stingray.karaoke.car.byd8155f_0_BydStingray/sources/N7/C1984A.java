package n7;

import e0.C1157D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: renamed from: n7.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1984A extends AbstractC1991f {
    public final ArrayList l;

    public C1984A(ArrayList arrayList) {
        this.l = arrayList;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i7, Object obj) {
        this.l.add(AbstractC1998m.R(i7, this), obj);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.l.clear();
    }

    @Override // n7.AbstractC1991f
    public final int d() {
        return this.l.size();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        return this.l.get(AbstractC1998m.Q(i7, this));
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new C1157D(this, 0);
    }

    @Override // n7.AbstractC1991f
    public final Object l(int i7) {
        return this.l.remove(AbstractC1998m.Q(i7, this));
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return new C1157D(this, 0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i7, Object obj) {
        return this.l.set(AbstractC1998m.Q(i7, this), obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i7) {
        return new C1157D(this, i7);
    }
}
