package defpackage;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: UnmodifiableLazyStringList.java */
/* JADX INFO: loaded from: classes.dex */
public final class yi3 extends AbstractList<String> implements q61, RandomAccess {
    public final q61 e;

    /* JADX INFO: compiled from: UnmodifiableLazyStringList.java */
    public class a implements ListIterator<String> {
        public final ListIterator<String> e;

        public a(yi3 yi3Var, int i) {
            this.e = yi3Var.e.listIterator(i);
        }

        @Override // java.util.ListIterator
        public final void add(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            return this.e.hasNext();
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return this.e.hasPrevious();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final Object next() {
            return this.e.next();
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            return this.e.nextIndex();
        }

        @Override // java.util.ListIterator
        public final String previous() {
            return this.e.previous();
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            return this.e.previousIndex();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public final void set(String str) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: UnmodifiableLazyStringList.java */
    public class b implements Iterator<String> {
        public final Iterator<String> e;

        public b(yi3 yi3Var) {
            this.e = yi3Var.e.iterator();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.e.hasNext();
        }

        @Override // java.util.Iterator
        public final String next() {
            return this.e.next();
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public yi3(q61 q61Var) {
        this.e = q61Var;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return (String) this.e.get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new b(this);
    }

    @Override // defpackage.q61
    public final List<?> k() {
        return this.e.k();
    }

    @Override // defpackage.q61
    public final void l(lk lkVar) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new a(this, i);
    }

    @Override // defpackage.q61
    public final Object n(int i) {
        return this.e.n(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.e.size();
    }

    @Override // defpackage.q61
    public final q61 m() {
        return this;
    }
}
