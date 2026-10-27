package com.google.common.collect;

import defpackage.AbstractC0203p;
import defpackage.ja2;
import defpackage.xi3;
import defpackage.z7;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: ImmutableList.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b<E> extends com.google.common.collect.a<E> implements List<E>, RandomAccess {
    public static final a f = new a(ja2.i, 0);

    /* JADX INFO: compiled from: ImmutableList.java */
    public static class a<E> extends AbstractC0203p<E> {
        public final b<E> g;

        public a(b<E> bVar, int i) {
            super(bVar.size(), i);
            this.g = bVar;
        }
    }

    /* JADX INFO: renamed from: com.google.common.collect.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ImmutableList.java */
    public static class C0039b implements Serializable {
        private static final long serialVersionUID = 0;
        public final Object[] e;

        public C0039b(Object[] objArr) {
            this.e = objArr;
        }

        public Object readResolve() {
            return b.q(this.e);
        }
    }

    /* JADX INFO: compiled from: ImmutableList.java */
    public class c extends b<E> {
        public final transient int g;
        public final transient int h;

        public c(int i, int i2) {
            this.g = i;
            this.h = i2;
        }

        @Override // com.google.common.collect.a
        public final Object[] b() {
            return b.this.b();
        }

        @Override // com.google.common.collect.a
        public final int c() {
            return b.this.d() + this.g + this.h;
        }

        @Override // com.google.common.collect.a
        public final int d() {
            return b.this.d() + this.g;
        }

        @Override // java.util.List
        public final E get(int i) {
            return b.this.get(i + this.g);
        }

        @Override // com.google.common.collect.b, com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            return listIterator(0);
        }

        @Override // com.google.common.collect.b, java.util.List
        public final ListIterator listIterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public final int size() {
            return this.h;
        }

        @Override // com.google.common.collect.b, java.util.List
        /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
        public final b<E> subList(int i, int i2) {
            int i3 = this.g;
            return b.this.subList(i + i3, i2 + i3);
        }

        @Override // com.google.common.collect.b, java.util.List
        public final /* bridge */ /* synthetic */ ListIterator listIterator(int i) {
            return listIterator(i);
        }
    }

    public static ja2 p(int i, Object[] objArr) {
        return i == 0 ? ja2.i : new ja2(i, objArr);
    }

    public static ja2 q(Object[] objArr) {
        if (objArr.length == 0) {
            return ja2.i;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        int length = objArr2.length;
        for (int i = 0; i < length; i++) {
            if (objArr2[i] == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index ");
                sb.append(i);
                throw new NullPointerException(sb.toString());
            }
        }
        return p(objArr2.length, objArr2);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public static ja2 t(Object obj) {
        Object[] objArr = {obj};
        for (int i = 0; i < 1; i++) {
            if (objArr[i] == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index ");
                sb.append(i);
                throw new NullPointerException(sb.toString());
            }
        }
        return p(1, objArr);
    }

    @Override // com.google.common.collect.a
    public int a(int i, Object[] objArr) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection
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
                        if (z7.e(get(i), list.get(i))) {
                        }
                    }
                    return true;
                }
                Iterator<E> it = iterator();
                Iterator<E> it2 = list.iterator();
                while (it.hasNext()) {
                    if (it2.hasNext() && z7.e(it.next(), it2.next())) {
                    }
                }
                return !it2.hasNext();
            }
        }
        return false;
    }

    @Override // com.google.common.collect.a
    /* JADX INFO: renamed from: f */
    public final xi3<E> iterator() {
        return listIterator(0);
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

    @Override // com.google.common.collect.a, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
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
    @Deprecated
    public final E remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public final a listIterator(int i) {
        size();
        return isEmpty() ? f : new a(this, i);
    }

    @Override // java.util.List
    @Deprecated
    public final E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: u */
    public b<E> subList(int i, int i2) {
        size();
        int i3 = i2 - i;
        return i3 == size() ? this : i3 == 0 ? ja2.i : new c(i, i3);
    }

    @Override // com.google.common.collect.a
    public Object writeReplace() {
        return new C0039b(toArray());
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return listIterator(0);
    }
}
