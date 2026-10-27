package d5;

import c5.InterfaceC1028e;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes.dex */
public final class o0 extends n0 implements SortedSet {
    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return ((SortedSet) this.l).comparator();
    }

    @Override // java.util.SortedSet
    public final Object first() {
        Iterator it = this.l.iterator();
        it.getClass();
        InterfaceC1028e interfaceC1028e = this.f13445m;
        interfaceC1028e.getClass();
        while (it.hasNext()) {
            Object next = it.next();
            if (interfaceC1028e.apply(next)) {
                return next;
            }
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedSet
    public final SortedSet headSet(Object obj) {
        return new o0(((SortedSet) this.l).headSet(obj), this.f13445m);
    }

    @Override // java.util.SortedSet
    public final Object last() {
        SortedSet sortedSetHeadSet = (SortedSet) this.l;
        while (true) {
            Object objLast = sortedSetHeadSet.last();
            if (this.f13445m.apply(objLast)) {
                return objLast;
            }
            sortedSetHeadSet = sortedSetHeadSet.headSet(objLast);
        }
    }

    @Override // java.util.SortedSet
    public final SortedSet subSet(Object obj, Object obj2) {
        return new o0(((SortedSet) this.l).subSet(obj, obj2), this.f13445m);
    }

    @Override // java.util.SortedSet
    public final SortedSet tailSet(Object obj) {
        return new o0(((SortedSet) this.l).tailSet(obj), this.f13445m);
    }
}
