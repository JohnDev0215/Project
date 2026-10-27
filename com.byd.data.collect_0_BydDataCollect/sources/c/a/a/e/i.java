package c.a.a.e;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: ReferenceConcurrentMap.java */
/* JADX INFO: loaded from: classes.dex */
public class i<K, V> implements ConcurrentMap<K, V>, Iterable<Map.Entry<K, V>>, Serializable {
    private final c.a.a.g.j keyType;
    private final ReferenceQueue<K> lastQueue = new ReferenceQueue<>();
    private BiConsumer<Reference<? extends K>, V> purgeListener;
    final ConcurrentMap<Reference<K>, V> raw;

    /* JADX INFO: compiled from: ReferenceConcurrentMap.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3071a;

        static {
            int[] iArr = new int[c.a.a.g.j.values().length];
            f3071a = iArr;
            try {
                iArr[c.a.a.g.j.WEAK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3071a[c.a.a.g.j.SOFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: ReferenceConcurrentMap.java */
    private static class b<K> extends SoftReference<K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f3072a;

        b(K k, ReferenceQueue<? super K> referenceQueue) {
            super(k, referenceQueue);
            this.f3072a = k.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof b) {
                return c.a.a.g.f.b(((b) obj).get(), get());
            }
            return false;
        }

        public int hashCode() {
            return this.f3072a;
        }
    }

    /* JADX INFO: compiled from: ReferenceConcurrentMap.java */
    private static class c<K> extends WeakReference<K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f3073a;

        c(K k, ReferenceQueue<? super K> referenceQueue) {
            super(k, referenceQueue);
            this.f3073a = k.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof c) {
                return c.a.a.g.f.b(((c) obj).get(), get());
            }
            return false;
        }

        public int hashCode() {
            return this.f3073a;
        }
    }

    public i(ConcurrentMap<Reference<K>, V> concurrentMap, c.a.a.g.j jVar) {
        this.raw = concurrentMap;
        this.keyType = jVar;
    }

    static /* synthetic */ AbstractMap.SimpleImmutableEntry e(Map.Entry entry) {
        return new AbstractMap.SimpleImmutableEntry(((Reference) entry.getKey()).get(), entry.getValue());
    }

    static /* synthetic */ Object h(Reference reference) {
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    private Reference<K> j(K k, ReferenceQueue<? super K> referenceQueue) {
        int i2 = a.f3071a[this.keyType.ordinal()];
        if (i2 == 1) {
            return new c(k, referenceQueue);
        }
        if (i2 == 2) {
            return new b(k, referenceQueue);
        }
        throw new IllegalArgumentException("Unsupported key type: " + this.keyType);
    }

    private void k() {
        while (true) {
            Reference<? extends K> referencePoll = this.lastQueue.poll();
            if (referencePoll == null) {
                return;
            }
            V vRemove = this.raw.remove(referencePoll);
            BiConsumer<Reference<? extends K>, V> biConsumer = this.purgeListener;
            if (biConsumer != null) {
                biConsumer.accept(referencePoll, vRemove);
            }
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.raw.clear();
        while (this.lastQueue.poll() != null) {
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V compute(final K k, final BiFunction<? super K, ? super V, ? extends V> biFunction) {
        k();
        return this.raw.compute(j(k, this.lastQueue), new BiFunction() { // from class: c.a.a.e.d
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return biFunction.apply(k, obj2);
            }
        });
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V computeIfAbsent(final K k, final Function<? super K, ? extends V> function) {
        k();
        return this.raw.computeIfAbsent(j(k, this.lastQueue), new Function() { // from class: c.a.a.e.g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return function.apply(k);
            }
        });
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V computeIfPresent(final K k, final BiFunction<? super K, ? super V, ? extends V> biFunction) {
        k();
        return this.raw.computeIfPresent(j(k, this.lastQueue), new BiFunction() { // from class: c.a.a.e.f
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return biFunction.apply(k, obj2);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        k();
        return this.raw.containsKey(j(obj, null));
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        k();
        return this.raw.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        k();
        return (Set) this.raw.entrySet().stream().map(new Function() { // from class: c.a.a.e.b
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return i.e((Map.Entry) obj);
            }
        }).collect(Collectors.toSet());
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public void forEach(final BiConsumer<? super K, ? super V> biConsumer) {
        k();
        this.raw.forEach(new BiConsumer() { // from class: c.a.a.e.e
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                biConsumer.accept(((Reference) obj).get(), obj2);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V get(Object obj) {
        k();
        return this.raw.get(j(obj, null));
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        return entrySet().iterator();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return new HashSet(c.a.a.b.e.c(this.raw.keySet(), new Function() { // from class: c.a.a.e.c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return i.h((Reference) obj);
            }
        }));
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V merge(K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction) {
        k();
        return this.raw.merge(j(k, this.lastQueue), v, biFunction);
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        k();
        return this.raw.put(j(k, this.lastQueue), v);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        map.forEach(new BiConsumer() { // from class: c.a.a.e.a
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f3060a.put(obj, obj2);
            }
        });
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V putIfAbsent(K k, V v) {
        k();
        return this.raw.putIfAbsent(j(k, this.lastQueue), v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V remove(Object obj) {
        k();
        return this.raw.remove(j(obj, null));
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V replace(K k, V v) {
        k();
        return this.raw.replace(j(k, this.lastQueue), v);
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public void replaceAll(final BiFunction<? super K, ? super V, ? extends V> biFunction) {
        k();
        this.raw.replaceAll(new BiFunction() { // from class: c.a.a.e.h
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return biFunction.apply(((Reference) obj).get(), obj2);
            }
        });
    }

    @Override // java.util.Map
    public int size() {
        k();
        return this.raw.size();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        k();
        return this.raw.values();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean remove(Object obj, Object obj2) {
        k();
        return this.raw.remove(j(obj, null), obj2);
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean replace(K k, V v, V v2) {
        k();
        return this.raw.replace(j(k, this.lastQueue), v, v2);
    }
}
