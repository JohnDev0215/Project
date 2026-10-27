package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public final class ImmutableSortedMap<K, V> extends ImmutableSortedMapFauxverideShim<K, V> implements NavigableMap<K, V> {

    @CheckForNull
    private transient ImmutableSortedMap<K, V> descendingMap;
    private final transient RegularImmutableSortedSet<K> keySet;
    private final transient ImmutableList<V> valueList;
    private static final Comparator<Comparable> NATURAL_ORDER = Ordering.natural();
    private static final ImmutableSortedMap<Comparable, Object> NATURAL_EMPTY_MAP = new ImmutableSortedMap<>(ImmutableSortedSet.emptySet(Ordering.natural()), ImmutableList.of());

    static <K, V> ImmutableSortedMap<K, V> emptyMap(Comparator<? super K> comparator) {
        if (Ordering.natural().equals(comparator)) {
            return of();
        }
        return new ImmutableSortedMap<>(ImmutableSortedSet.emptySet(comparator), ImmutableList.of());
    }

    public static <K, V> ImmutableSortedMap<K, V> of() {
        return (ImmutableSortedMap<K, V>) NATURAL_EMPTY_MAP;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <K, V> ImmutableSortedMap<K, V> of(Comparator<? super K> comparator, K k, V v) {
        return new ImmutableSortedMap<>(new RegularImmutableSortedSet(ImmutableList.of(k), (Comparator) Preconditions.checkNotNull(comparator)), ImmutableList.of(v));
    }

    public static <K extends Comparable<?>, V> Builder<K, V> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    public static class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        private final Comparator<? super K> comparator;
        private transient Object[] keys;
        private transient Object[] values;

        public Builder(Comparator<? super K> comparator) {
            this(comparator, 4);
        }

        private Builder(Comparator<? super K> comparator, int i) {
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
            this.keys = new Object[i];
            this.values = new Object[i];
        }

        private void ensureCapacity(int i) {
            Object[] objArr = this.keys;
            if (i > objArr.length) {
                int iExpandedCapacity = ImmutableCollection.Builder.expandedCapacity(objArr.length, i);
                this.keys = Arrays.copyOf(this.keys, iExpandedCapacity);
                this.values = Arrays.copyOf(this.values, iExpandedCapacity);
            }
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> put(K k, V v) {
            ensureCapacity(this.size + 1);
            CollectPreconditions.checkEntryNotNull(k, v);
            Object[] objArr = this.keys;
            int i = this.size;
            objArr[i] = k;
            this.values[i] = v;
            this.size = i + 1;
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            super.put((Map.Entry) entry);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            super.putAll((Map) map);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            super.putAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public ImmutableSortedMap<K, V> build() {
            return buildOrThrow();
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public ImmutableSortedMap<K, V> buildOrThrow() {
            int i = this.size;
            if (i == 0) {
                return ImmutableSortedMap.emptyMap(this.comparator);
            }
            if (i == 1) {
                Comparator<? super K> comparator = this.comparator;
                Object obj = this.keys[0];
                Objects.requireNonNull(obj);
                Object obj2 = this.values[0];
                Objects.requireNonNull(obj2);
                return ImmutableSortedMap.of(comparator, obj, obj2);
            }
            Object[] objArrCopyOf = Arrays.copyOf(this.keys, i);
            Arrays.sort(objArrCopyOf, this.comparator);
            Object[] objArr = new Object[this.size];
            for (int i2 = 0; i2 < this.size; i2++) {
                if (i2 > 0) {
                    int i3 = i2 - 1;
                    if (this.comparator.compare(objArrCopyOf[i3], objArrCopyOf[i2]) == 0) {
                        String strValueOf = String.valueOf(objArrCopyOf[i3]);
                        String strValueOf2 = String.valueOf(objArrCopyOf[i2]);
                        StringBuilder sb = new StringBuilder(strValueOf.length() + 57 + strValueOf2.length());
                        sb.append("keys required to be distinct but compared as equal: ");
                        sb.append(strValueOf);
                        sb.append(" and ");
                        sb.append(strValueOf2);
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                Object obj3 = this.keys[i2];
                Objects.requireNonNull(obj3);
                int iBinarySearch = Arrays.binarySearch(objArrCopyOf, obj3, this.comparator);
                Object obj4 = this.values[i2];
                Objects.requireNonNull(obj4);
                objArr[iBinarySearch] = obj4;
            }
            return new ImmutableSortedMap<>(new RegularImmutableSortedSet(ImmutableList.asImmutableList(objArrCopyOf), this.comparator), ImmutableList.asImmutableList(objArr));
        }
    }

    ImmutableSortedMap(RegularImmutableSortedSet<K> regularImmutableSortedSet, ImmutableList<V> immutableList) {
        this(regularImmutableSortedSet, immutableList, null);
    }

    ImmutableSortedMap(RegularImmutableSortedSet<K> regularImmutableSortedSet, ImmutableList<V> immutableList, @CheckForNull ImmutableSortedMap<K, V> immutableSortedMap) {
        this.keySet = regularImmutableSortedSet;
        this.valueList = immutableList;
        this.descendingMap = immutableSortedMap;
    }

    @Override // java.util.Map
    public int size() {
        return this.valueList.size();
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    @CheckForNull
    public V get(@CheckForNull Object obj) {
        int iIndexOf = this.keySet.indexOf(obj);
        if (iIndexOf == -1) {
            return null;
        }
        return this.valueList.get(iIndexOf);
    }

    @Override // com.google.common.collect.ImmutableMap
    boolean isPartialView() {
        return this.keySet.isPartialView() || this.valueList.isPartialView();
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return isEmpty() ? ImmutableSet.of() : new ImmutableMapEntrySet<K, V>() { // from class: com.google.common.collect.ImmutableSortedMap.1EntrySet
            @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
            public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                return asList().iterator();
            }

            @Override // com.google.common.collect.ImmutableSet
            ImmutableList<Map.Entry<K, V>> createAsList() {
                return new ImmutableList<Map.Entry<K, V>>() { // from class: com.google.common.collect.ImmutableSortedMap.1EntrySet.1
                    @Override // com.google.common.collect.ImmutableCollection
                    boolean isPartialView() {
                        return true;
                    }

                    @Override // java.util.List
                    public Map.Entry<K, V> get(int i) {
                        return new AbstractMap.SimpleImmutableEntry(ImmutableSortedMap.this.keySet.asList().get(i), ImmutableSortedMap.this.valueList.get(i));
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return ImmutableSortedMap.this.size();
                    }
                };
            }

            @Override // com.google.common.collect.ImmutableMapEntrySet
            ImmutableMap<K, V> map() {
                return ImmutableSortedMap.this;
            }
        };
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSortedSet<K> keySet() {
        return this.keySet;
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<K> createKeySet() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableCollection<V> values() {
        return this.valueList;
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableCollection<V> createValues() {
        throw new AssertionError("should never be called");
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return keySet().comparator();
    }

    @Override // java.util.SortedMap
    public K firstKey() {
        return keySet().first();
    }

    @Override // java.util.SortedMap
    public K lastKey() {
        return keySet().last();
    }

    private ImmutableSortedMap<K, V> getSubMap(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        if (i == i2) {
            return emptyMap(comparator());
        }
        return new ImmutableSortedMap<>(this.keySet.getSubSet(i, i2), this.valueList.subList(i, i2));
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public ImmutableSortedMap<K, V> headMap(K k) {
        return headMap((Object) k, false);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // java.util.NavigableMap
    public ImmutableSortedMap<K, V> headMap(K k, boolean z) {
        return getSubMap(0, this.keySet.headIndex((K) Preconditions.checkNotNull(k), z));
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public ImmutableSortedMap<K, V> subMap(K k, K k2) {
        return subMap((Object) k, true, (Object) k2, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap
    public ImmutableSortedMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(k2);
        Preconditions.checkArgument(comparator().compare(k, k2) <= 0, "expected fromKey <= toKey but %s > %s", k, k2);
        return headMap((Object) k2, z2).tailMap((Object) k, z);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public ImmutableSortedMap<K, V> tailMap(K k) {
        return tailMap((Object) k, true);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // java.util.NavigableMap
    public ImmutableSortedMap<K, V> tailMap(K k, boolean z) {
        return getSubMap(this.keySet.tailIndex((K) Preconditions.checkNotNull(k), z), size());
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    public Map.Entry<K, V> lowerEntry(K k) {
        return headMap((Object) k, false).lastEntry();
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    public K lowerKey(K k) {
        return (K) Maps.keyOrNull(lowerEntry(k));
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    public Map.Entry<K, V> floorEntry(K k) {
        return headMap((Object) k, true).lastEntry();
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    public K floorKey(K k) {
        return (K) Maps.keyOrNull(floorEntry(k));
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    public Map.Entry<K, V> ceilingEntry(K k) {
        return tailMap((Object) k, true).firstEntry();
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    public K ceilingKey(K k) {
        return (K) Maps.keyOrNull(ceilingEntry(k));
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    public Map.Entry<K, V> higherEntry(K k) {
        return tailMap((Object) k, false).firstEntry();
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    public K higherKey(K k) {
        return (K) Maps.keyOrNull(higherEntry(k));
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    public Map.Entry<K, V> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return entrySet().asList().get(0);
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    public Map.Entry<K, V> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return entrySet().asList().get(size() - 1);
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    @Deprecated
    public final Map.Entry<K, V> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableMap
    @CheckForNull
    @Deprecated
    public final Map.Entry<K, V> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableMap
    public ImmutableSortedMap<K, V> descendingMap() {
        ImmutableSortedMap<K, V> immutableSortedMap = this.descendingMap;
        if (immutableSortedMap != null) {
            return immutableSortedMap;
        }
        if (isEmpty()) {
            return emptyMap(Ordering.from(comparator()).reverse());
        }
        return new ImmutableSortedMap<>((RegularImmutableSortedSet) this.keySet.descendingSet(), this.valueList.reverse(), this);
    }

    @Override // java.util.NavigableMap
    public ImmutableSortedSet<K> navigableKeySet() {
        return this.keySet;
    }

    @Override // java.util.NavigableMap
    public ImmutableSortedSet<K> descendingKeySet() {
        return this.keySet.descendingSet();
    }
}
