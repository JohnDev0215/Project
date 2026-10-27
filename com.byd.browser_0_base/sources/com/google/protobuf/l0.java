package com.google.protobuf;

import defpackage.wj1;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: MapField.java */
/* JADX INFO: loaded from: classes.dex */
public final class l0<K, V> implements wj1 {
    public volatile boolean a;
    public volatile int b;
    public c<K, V> c;
    public ArrayList d;
    public final a<K, V> e;

    /* JADX INFO: compiled from: MapField.java */
    public interface a<K, V> {
    }

    /* JADX INFO: compiled from: MapField.java */
    public static class b<K, V> implements a<K, V> {
        public b(j0<K, V> j0Var) {
        }
    }

    /* JADX INFO: compiled from: MapField.java */
    public static class c<K, V> implements Map<K, V> {
        public final wj1 e;
        public final Map<K, V> f;

        /* JADX INFO: compiled from: MapField.java */
        public static class a<E> implements Collection<E> {
            public final wj1 e;
            public final Collection<E> f;

            public a(wj1 wj1Var, Collection<E> collection) {
                this.e = wj1Var;
                this.f = collection;
            }

            @Override // java.util.Collection
            public final boolean add(E e) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection
            public final boolean addAll(Collection<? extends E> collection) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Collection
            public final void clear() {
                ((l0) this.e).b();
                this.f.clear();
            }

            @Override // java.util.Collection
            public final boolean contains(Object obj) {
                return this.f.contains(obj);
            }

            @Override // java.util.Collection
            public final boolean containsAll(Collection<?> collection) {
                return this.f.containsAll(collection);
            }

            @Override // java.util.Collection
            public final boolean equals(Object obj) {
                return this.f.equals(obj);
            }

            @Override // java.util.Collection
            public final int hashCode() {
                return this.f.hashCode();
            }

            @Override // java.util.Collection
            public final boolean isEmpty() {
                return this.f.isEmpty();
            }

            @Override // java.util.Collection, java.lang.Iterable
            public final Iterator<E> iterator() {
                return new b(this.e, this.f.iterator());
            }

            @Override // java.util.Collection
            public final boolean remove(Object obj) {
                ((l0) this.e).b();
                return this.f.remove(obj);
            }

            @Override // java.util.Collection
            public final boolean removeAll(Collection<?> collection) {
                ((l0) this.e).b();
                return this.f.removeAll(collection);
            }

            @Override // java.util.Collection
            public final boolean retainAll(Collection<?> collection) {
                ((l0) this.e).b();
                return this.f.retainAll(collection);
            }

            @Override // java.util.Collection
            public final int size() {
                return this.f.size();
            }

            @Override // java.util.Collection
            public final Object[] toArray() {
                return this.f.toArray();
            }

            public final String toString() {
                return this.f.toString();
            }

            @Override // java.util.Collection
            public final <T> T[] toArray(T[] tArr) {
                return (T[]) this.f.toArray(tArr);
            }
        }

        /* JADX INFO: compiled from: MapField.java */
        public static class b<E> implements Iterator<E> {
            public final wj1 e;
            public final Iterator<E> f;

            public b(wj1 wj1Var, Iterator<E> it) {
                this.e = wj1Var;
                this.f = it;
            }

            public final boolean equals(Object obj) {
                return this.f.equals(obj);
            }

            @Override // java.util.Iterator
            public final boolean hasNext() {
                return this.f.hasNext();
            }

            public final int hashCode() {
                return this.f.hashCode();
            }

            @Override // java.util.Iterator
            public final E next() {
                return this.f.next();
            }

            @Override // java.util.Iterator
            public final void remove() {
                ((l0) this.e).b();
                this.f.remove();
            }

            public final String toString() {
                return this.f.toString();
            }
        }

        /* JADX INFO: renamed from: com.google.protobuf.l0$c$c, reason: collision with other inner class name */
        /* JADX INFO: compiled from: MapField.java */
        public static class C0046c<E> implements Set<E> {
            public final wj1 e;
            public final Set<E> f;

            public C0046c(wj1 wj1Var, Set<E> set) {
                this.e = wj1Var;
                this.f = set;
            }

            @Override // java.util.Set, java.util.Collection
            public final boolean add(E e) {
                ((l0) this.e).b();
                return this.f.add(e);
            }

            @Override // java.util.Set, java.util.Collection
            public final boolean addAll(Collection<? extends E> collection) {
                ((l0) this.e).b();
                return this.f.addAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public final void clear() {
                ((l0) this.e).b();
                this.f.clear();
            }

            @Override // java.util.Set, java.util.Collection
            public final boolean contains(Object obj) {
                return this.f.contains(obj);
            }

            @Override // java.util.Set, java.util.Collection
            public final boolean containsAll(Collection<?> collection) {
                return this.f.containsAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public final boolean equals(Object obj) {
                return this.f.equals(obj);
            }

            @Override // java.util.Set, java.util.Collection
            public final int hashCode() {
                return this.f.hashCode();
            }

            @Override // java.util.Set, java.util.Collection
            public final boolean isEmpty() {
                return this.f.isEmpty();
            }

            @Override // java.util.Set, java.util.Collection, java.lang.Iterable
            public final Iterator<E> iterator() {
                return new b(this.e, this.f.iterator());
            }

            @Override // java.util.Set, java.util.Collection
            public final boolean remove(Object obj) {
                ((l0) this.e).b();
                return this.f.remove(obj);
            }

            @Override // java.util.Set, java.util.Collection
            public final boolean removeAll(Collection<?> collection) {
                ((l0) this.e).b();
                return this.f.removeAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public final boolean retainAll(Collection<?> collection) {
                ((l0) this.e).b();
                return this.f.retainAll(collection);
            }

            @Override // java.util.Set, java.util.Collection
            public final int size() {
                return this.f.size();
            }

            @Override // java.util.Set, java.util.Collection
            public final Object[] toArray() {
                return this.f.toArray();
            }

            public final String toString() {
                return this.f.toString();
            }

            @Override // java.util.Set, java.util.Collection
            public final <T> T[] toArray(T[] tArr) {
                return (T[]) this.f.toArray(tArr);
            }
        }

        public c(wj1 wj1Var, LinkedHashMap linkedHashMap) {
            this.e = wj1Var;
            this.f = linkedHashMap;
        }

        @Override // java.util.Map
        public final void clear() {
            ((l0) this.e).b();
            this.f.clear();
        }

        @Override // java.util.Map
        public final boolean containsKey(Object obj) {
            return this.f.containsKey(obj);
        }

        @Override // java.util.Map
        public final boolean containsValue(Object obj) {
            return this.f.containsValue(obj);
        }

        @Override // java.util.Map
        public final Set<Map.Entry<K, V>> entrySet() {
            return new C0046c(this.e, this.f.entrySet());
        }

        @Override // java.util.Map
        public final boolean equals(Object obj) {
            return this.f.equals(obj);
        }

        @Override // java.util.Map
        public final V get(Object obj) {
            return this.f.get(obj);
        }

        @Override // java.util.Map
        public final int hashCode() {
            return this.f.hashCode();
        }

        @Override // java.util.Map
        public final boolean isEmpty() {
            return this.f.isEmpty();
        }

        @Override // java.util.Map
        public final Set<K> keySet() {
            return new C0046c(this.e, this.f.keySet());
        }

        @Override // java.util.Map
        public final V put(K k, V v) {
            ((l0) this.e).b();
            Charset charset = c0.a;
            k.getClass();
            v.getClass();
            return this.f.put(k, v);
        }

        @Override // java.util.Map
        public final void putAll(Map<? extends K, ? extends V> map) {
            ((l0) this.e).b();
            for (K k : map.keySet()) {
                Charset charset = c0.a;
                k.getClass();
                map.get(k).getClass();
            }
            this.f.putAll(map);
        }

        @Override // java.util.Map
        public final V remove(Object obj) {
            ((l0) this.e).b();
            return this.f.remove(obj);
        }

        @Override // java.util.Map
        public final int size() {
            return this.f.size();
        }

        public final String toString() {
            return this.f.toString();
        }

        @Override // java.util.Map
        public final Collection<V> values() {
            return new a(this.e, this.f.values());
        }
    }

    public l0() {
        throw null;
    }

    public l0(a aVar, LinkedHashMap linkedHashMap) {
        this.e = aVar;
        this.a = true;
        this.b = 1;
        this.c = new c<>(this, linkedHashMap);
        this.d = null;
    }

    public final c<K, V> a(List<q0> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (q0 q0Var : list) {
            ((b) this.e).getClass();
            j0 j0Var = (j0) q0Var;
            linkedHashMap.put(j0Var.f, j0Var.g);
        }
        return new c<>(this, linkedHashMap);
    }

    public final void b() {
        if (!this.a) {
            throw new UnsupportedOperationException();
        }
    }

    public final Map<K, V> c() {
        if (this.b == 2) {
            synchronized (this) {
                if (this.b == 2) {
                    this.c = a(this.d);
                    this.b = 3;
                }
            }
        }
        return Collections.unmodifiableMap(this.c);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof l0) {
            return m0.l(c(), ((l0) obj).c());
        }
        return false;
    }

    public final int hashCode() {
        return m0.b(c());
    }
}
