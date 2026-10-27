package cn.hutool.core.map;

import cn.hutool.core.lang.func.Func0;
import cn.hutool.core.util.ReferenceUtil$ReferenceType;
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

/* JADX INFO: loaded from: classes.dex */
public class ReferenceConcurrentMap<K, V> implements ConcurrentMap<K, V>, Iterable<Map.Entry<K, V>>, Serializable {
    private final ReferenceUtil$ReferenceType keyType;
    private final ReferenceQueue<K> lastQueue = new ReferenceQueue<>();
    private BiConsumer<Reference<? extends K>, V> purgeListener;
    final ConcurrentMap<Reference<K>, V> raw;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2932a;

        static {
            int[] iArr = new int[ReferenceUtil$ReferenceType.values().length];
            f2932a = iArr;
            try {
                iArr[ReferenceUtil$ReferenceType.WEAK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2932a[ReferenceUtil$ReferenceType.SOFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static class b<K> extends SoftReference<K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f2933a;

        b(K k, ReferenceQueue<? super K> referenceQueue) {
            super(k, referenceQueue);
            this.f2933a = k.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof b) {
                return cn.hutool.core.util.f.c(((b) obj).get(), get());
            }
            return false;
        }

        public int hashCode() {
            return this.f2933a;
        }
    }

    private static class c<K> extends WeakReference<K> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f2934a;

        c(K k, ReferenceQueue<? super K> referenceQueue) {
            super(k, referenceQueue);
            this.f2934a = k.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof c) {
                return cn.hutool.core.util.f.c(((c) obj).get(), get());
            }
            return false;
        }

        public int hashCode() {
            return this.f2934a;
        }
    }

    public ReferenceConcurrentMap(ConcurrentMap<Reference<K>, V> concurrentMap, ReferenceUtil$ReferenceType referenceUtil$ReferenceType) {
        this.raw = concurrentMap;
        this.keyType = referenceUtil$ReferenceType;
    }

    static /* synthetic */ AbstractMap.SimpleImmutableEntry e(Map.Entry entry) {
        return new AbstractMap.SimpleImmutableEntry(((Reference) entry.getKey()).get(), entry.getValue());
    }

    static /* synthetic */ Object g(Reference reference) {
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    private Reference<K> i(K k, ReferenceQueue<? super K> referenceQueue) {
        int i = a.f2932a[this.keyType.ordinal()];
        if (i == 1) {
            return new c(k, referenceQueue);
        }
        if (i == 2) {
            return new b(k, referenceQueue);
        }
        throw new IllegalArgumentException("Unsupported key type: " + this.keyType);
    }

    private void j() {
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
        j();
        return this.raw.compute(i(k, this.lastQueue), new BiFunction() { // from class: cn.hutool.core.map.e
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return biFunction.apply(k, obj2);
            }
        });
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V computeIfAbsent(final K k, final Function<? super K, ? extends V> function) {
        j();
        return this.raw.computeIfAbsent(i(k, this.lastQueue), new Function() { // from class: cn.hutool.core.map.h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return function.apply(k);
            }
        });
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V computeIfPresent(final K k, final BiFunction<? super K, ? super V, ? extends V> biFunction) {
        j();
        return this.raw.computeIfPresent(i(k, this.lastQueue), new BiFunction() { // from class: cn.hutool.core.map.g
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return biFunction.apply(k, obj2);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        j();
        return this.raw.containsKey(i(obj, null));
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        j();
        return this.raw.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        j();
        return (Set) this.raw.entrySet().stream().map(new Function() { // from class: cn.hutool.core.map.b
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ReferenceConcurrentMap.e((Map.Entry) obj);
            }
        }).collect(Collectors.toSet());
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public void forEach(final BiConsumer<? super K, ? super V> biConsumer) {
        j();
        this.raw.forEach(new BiConsumer() { // from class: cn.hutool.core.map.f
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                biConsumer.accept(((Reference) obj).get(), obj2);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V get(Object obj) {
        j();
        return this.raw.get(i(obj, null));
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
        return new HashSet(c.a.a.b.e.c(this.raw.keySet(), new Function() { // from class: cn.hutool.core.map.c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ReferenceConcurrentMap.g((Reference) obj);
            }
        }));
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V merge(K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction) {
        j();
        return this.raw.merge(i(k, this.lastQueue), v, biFunction);
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        j();
        return this.raw.put(i(k, this.lastQueue), v);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        map.forEach(new BiConsumer() { // from class: cn.hutool.core.map.a
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f2935a.put(obj, obj2);
            }
        });
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V putIfAbsent(K k, V v) {
        j();
        return this.raw.putIfAbsent(i(k, this.lastQueue), v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V remove(Object obj) {
        j();
        return this.raw.remove(i(obj, null));
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V replace(K k, V v) {
        j();
        return this.raw.replace(i(k, this.lastQueue), v);
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public void replaceAll(final BiFunction<? super K, ? super V, ? extends V> biFunction) {
        j();
        this.raw.replaceAll(new BiFunction() { // from class: cn.hutool.core.map.i
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return biFunction.apply(((Reference) obj).get(), obj2);
            }
        });
    }

    public void setPurgeListener(BiConsumer<Reference<? extends K>, V> biConsumer) {
        this.purgeListener = biConsumer;
    }

    @Override // java.util.Map
    public int size() {
        j();
        return this.raw.size();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        j();
        return this.raw.values();
    }

    public V computeIfAbsent(K k, final Func0<? extends V> func0) {
        return computeIfAbsent(k, new Function() { // from class: cn.hutool.core.map.d
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return func0.callWithRuntimeException();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean remove(Object obj, Object obj2) {
        j();
        return this.raw.remove(i(obj, null), obj2);
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean replace(K k, V v, V v2) {
        j();
        return this.raw.replace(i(k, this.lastQueue), v, v2);
    }
}
