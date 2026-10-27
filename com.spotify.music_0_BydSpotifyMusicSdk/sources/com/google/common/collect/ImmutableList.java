package com.google.common.collect;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import p.ci1;
import p.f60;
import p.oq2;
import p.u4;
import p.y31;

/* JADX INFO: loaded from: classes.dex */
public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {
    public static final y31 e = new y31(RegularImmutableList.m, 0);

    public static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        public Object readResolve() {
            Object[] objArr = this.elements;
            y31 y31Var = ImmutableList.e;
            return objArr.length == 0 ? RegularImmutableList.m : ImmutableList.n((Object[]) objArr.clone());
        }
    }

    public class SubList extends ImmutableList<E> {
        public final transient int k;
        public final transient int l;

        public SubList(int i, int i2) {
            this.k = i;
            this.l = i2;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final Object[] c() {
            return ImmutableList.this.c();
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final int d() {
            return ImmutableList.this.e() + this.k + this.l;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final int e() {
            return ImmutableList.this.e() + this.k;
        }

        @Override // java.util.List
        public final Object get(int i) {
            ci1.k(i, this.l);
            return ImmutableList.this.get(i + this.k);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean h() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            return listIterator(0);
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public final ListIterator listIterator() {
            return listIterator(0);
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
        public final ImmutableList subList(int i, int i2) {
            ci1.m(i, i2, this.l);
            ImmutableList immutableList = ImmutableList.this;
            int i3 = this.k;
            return immutableList.subList(i + i3, i2 + i3);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public final int size() {
            return this.l;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public final /* bridge */ /* synthetic */ ListIterator listIterator(int i) {
            return listIterator(i);
        }
    }

    public static ImmutableList m(int i, Object[] objArr) {
        return i == 0 ? RegularImmutableList.m : new RegularImmutableList(i, objArr);
    }

    public static ImmutableList n(Object... objArr) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (objArr[i] == null) {
                throw new NullPointerException(u4.e("at index ", i));
            }
        }
        return m(objArr.length, objArr);
    }

    public static ImmutableList o(Collection collection) {
        if (!(collection instanceof ImmutableCollection)) {
            return n(collection.toArray());
        }
        ImmutableList immutableListA = ((ImmutableCollection) collection).a();
        if (!immutableListA.h()) {
            return immutableListA;
        }
        Object[] array = immutableListA.toArray();
        return m(array.length, array);
    }

    public static ImmutableList q() {
        return RegularImmutableList.m;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final ImmutableList a() {
        return this;
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int b(Object[] objArr) {
        int size = size();
        for (int i = 0; i < size; i++) {
            objArr[i] = get(i);
        }
        return size;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i = 0; i < size; i++) {
                        if (f60.p(get(i), list.get(i))) {
                        }
                    }
                    return true;
                }
                Iterator<E> it = iterator();
                Iterator<E> it2 = list.iterator();
                while (it.hasNext()) {
                    if (it2.hasNext() && f60.p(it.next(), it2.next())) {
                    }
                }
                return !it2.hasNext();
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = ~(~(get(i2).hashCode() + (i * 31)));
        }
        return i;
    }

    @Override // com.google.common.collect.ImmutableCollection
    /* JADX INFO: renamed from: i */
    public final oq2 iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public final y31 listIterator(int i) {
        ci1.l(i, size());
        return isEmpty() ? e : new y31(this, i);
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: r */
    public ImmutableList subList(int i, int i2) {
        ci1.m(i, i2, size());
        int i3 = i2 - i;
        return i3 == size() ? this : i3 == 0 ? RegularImmutableList.m : new SubList(i, i3);
    }

    @Override // java.util.List
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return listIterator(0);
    }
}
