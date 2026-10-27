package d5;

import c5.InterfaceC1027d;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class T extends AbstractSequentialList implements Serializable {
    public final AbstractCollection l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC1027d f13399m;

    /* JADX WARN: Multi-variable type inference failed */
    public T(InterfaceC1027d interfaceC1027d, List list) {
        list.getClass();
        this.l = (AbstractCollection) list;
        this.f13399m = interfaceC1027d;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.AbstractCollection, java.util.List] */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.l.isEmpty();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.AbstractCollection, java.util.List] */
    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i7) {
        return new Q(this, this.l.listIterator(i7), 1);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.AbstractCollection, java.util.List] */
    @Override // java.util.AbstractList
    public final void removeRange(int i7, int i10) {
        this.l.subList(i7, i10).clear();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.AbstractCollection, java.util.List] */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.l.size();
    }
}
