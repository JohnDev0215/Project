package X;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import n7.AbstractC1989d;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends AbstractC1989d implements List, Collection, B7.a {
    @Override // n7.AbstractC1986a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // n7.AbstractC1986a, java.util.Collection, java.util.List
    public final boolean containsAll(Collection collection) {
        Collection collection2 = collection;
        if ((collection2 instanceof Collection) && collection2.isEmpty()) {
            return true;
        }
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // n7.AbstractC1989d, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    public abstract c l(int i7, Object obj);

    @Override // n7.AbstractC1989d, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    public abstract c q(Object obj);

    public c r(Collection collection) {
        g gVarS = s();
        gVarS.addAll(collection);
        return gVarS.q();
    }

    public abstract g s();

    @Override // n7.AbstractC1989d, java.util.List
    public final List subList(int i7, int i10) {
        return new W.a(this, i7, i10);
    }

    public abstract c t(b bVar);

    public abstract c u(int i7);

    public abstract c v(int i7, Object obj);
}
