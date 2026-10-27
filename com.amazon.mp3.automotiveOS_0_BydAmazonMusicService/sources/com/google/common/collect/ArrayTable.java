package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public final class ArrayTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
    private final V[][] array;
    private final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableList<C> columnList;
    private final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableList<R> rowList;

    @CheckForNull
    private transient ArrayTable<R, C, V>.RowMap rowMap;

    @Override // com.google.common.collect.AbstractTable
    public /* bridge */ /* synthetic */ boolean equals(@CheckForNull Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.AbstractTable
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.AbstractTable
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        return new ArrayTable<>(iterable, iterable2);
    }

    private ArrayTable(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        ImmutableList<R> immutableListCopyOf = ImmutableList.copyOf(iterable);
        this.rowList = immutableListCopyOf;
        ImmutableList<C> immutableListCopyOf2 = ImmutableList.copyOf(iterable2);
        this.columnList = immutableListCopyOf2;
        Preconditions.checkArgument(immutableListCopyOf.isEmpty() == immutableListCopyOf2.isEmpty());
        this.rowKeyToIndex = Maps.indexMap(immutableListCopyOf);
        this.columnKeyToIndex = Maps.indexMap(immutableListCopyOf2);
        this.array = (V[][]) ((Object[][]) Array.newInstance((Class<?>) Object.class, immutableListCopyOf.size(), immutableListCopyOf2.size()));
        eraseAll();
    }

    private static abstract class ArrayMap<K, V> extends Maps.IteratorBasedAbstractMap<K, V> {
        private final ImmutableMap<K, Integer> keyIndex;

        abstract String getKeyRole();

        abstract V getValue(int i);

        abstract V setValue(int i, V v);

        private ArrayMap(ImmutableMap<K, Integer> immutableMap) {
            this.keyIndex = immutableMap;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.keyIndex.keySet();
        }

        K getKey(int i) {
            return this.keyIndex.keySet().asList().get(i);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.keyIndex.size();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return this.keyIndex.isEmpty();
        }

        Map.Entry<K, V> getEntry(final int i) {
            Preconditions.checkElementIndex(i, size());
            return new AbstractMapEntry<K, V>() { // from class: com.google.common.collect.ArrayTable.ArrayMap.1
                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public K getKey() {
                    return (K) ArrayMap.this.getKey(i);
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public V getValue() {
                    return (V) ArrayMap.this.getValue(i);
                }

                @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
                public V setValue(V v) {
                    return (V) ArrayMap.this.setValue(i, v);
                }
            };
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        Iterator<Map.Entry<K, V>> entryIterator() {
            return new AbstractIndexedListIterator<Map.Entry<K, V>>(size()) { // from class: com.google.common.collect.ArrayTable.ArrayMap.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIndexedListIterator
                public Map.Entry<K, V> get(int i) {
                    return ArrayMap.this.getEntry(i);
                }
            };
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@CheckForNull Object obj) {
            return this.keyIndex.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CheckForNull
        public V get(@CheckForNull Object obj) {
            Integer num = this.keyIndex.get(obj);
            if (num == null) {
                return null;
            }
            return getValue(num.intValue());
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CheckForNull
        public V put(K k, V v) {
            Integer num = this.keyIndex.get(k);
            if (num == null) {
                String keyRole = getKeyRole();
                String strValueOf = String.valueOf(k);
                String strValueOf2 = String.valueOf(this.keyIndex.keySet());
                StringBuilder sb = new StringBuilder(String.valueOf(keyRole).length() + 9 + strValueOf.length() + strValueOf2.length());
                sb.append(keyRole);
                sb.append(" ");
                sb.append(strValueOf);
                sb.append(" not in ");
                sb.append(strValueOf2);
                throw new IllegalArgumentException(sb.toString());
            }
            return setValue(num.intValue(), v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CheckForNull
        public V remove(@CheckForNull Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public void clear() {
            throw new UnsupportedOperationException();
        }
    }

    @CheckForNull
    public V at(int i, int i2) {
        Preconditions.checkElementIndex(i, this.rowList.size());
        Preconditions.checkElementIndex(i2, this.columnList.size());
        return this.array[i][i2];
    }

    @CheckForNull
    public V set(int i, int i2, @CheckForNull V v) {
        Preconditions.checkElementIndex(i, this.rowList.size());
        Preconditions.checkElementIndex(i2, this.columnList.size());
        V[] vArr = this.array[i];
        V v2 = vArr[i2];
        vArr[i2] = v;
        return v2;
    }

    @Override // com.google.common.collect.AbstractTable
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public void eraseAll() {
        for (V[] vArr : this.array) {
            Arrays.fill(vArr, (Object) null);
        }
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean contains(@CheckForNull Object obj, @CheckForNull Object obj2) {
        return containsRow(obj) && containsColumn(obj2);
    }

    public boolean containsColumn(@CheckForNull Object obj) {
        return this.columnKeyToIndex.containsKey(obj);
    }

    public boolean containsRow(@CheckForNull Object obj) {
        return this.rowKeyToIndex.containsKey(obj);
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @CheckForNull
    public V get(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Integer num = this.rowKeyToIndex.get(obj);
        Integer num2 = this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return at(num.intValue(), num2.intValue());
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public boolean isEmpty() {
        return this.rowList.isEmpty() || this.columnList.isEmpty();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    @CheckForNull
    public V put(R r, C c, @CheckForNull V v) {
        Preconditions.checkNotNull(r);
        Preconditions.checkNotNull(c);
        Integer num = this.rowKeyToIndex.get(r);
        Preconditions.checkArgument(num != null, "Row %s not in %s", r, this.rowList);
        Integer num2 = this.columnKeyToIndex.get(c);
        Preconditions.checkArgument(num2 != null, "Column %s not in %s", c, this.columnList);
        return set(num.intValue(), num2.intValue(), v);
    }

    @Override // com.google.common.collect.Table
    public int size() {
        return this.rowList.size() * this.columnList.size();
    }

    @Override // com.google.common.collect.AbstractTable, com.google.common.collect.Table
    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    @Override // com.google.common.collect.AbstractTable
    Iterator<Table.Cell<R, C, V>> cellIterator() {
        return new AbstractIndexedListIterator<Table.Cell<R, C, V>>(size()) { // from class: com.google.common.collect.ArrayTable.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIndexedListIterator
            public Table.Cell<R, C, V> get(int i) {
                return ArrayTable.this.getCell(i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Table.Cell<R, C, V> getCell(int i) {
        return new Tables.AbstractCell<R, C, V>(i) { // from class: com.google.common.collect.ArrayTable.2
            final int columnIndex;
            final int rowIndex;
            final /* synthetic */ int val$index;

            {
                this.val$index = i;
                this.rowIndex = i / ArrayTable.this.columnList.size();
                this.columnIndex = i % ArrayTable.this.columnList.size();
            }

            @Override // com.google.common.collect.Table.Cell
            public R getRowKey() {
                return (R) ArrayTable.this.rowList.get(this.rowIndex);
            }

            @Override // com.google.common.collect.Table.Cell
            public C getColumnKey() {
                return (C) ArrayTable.this.columnList.get(this.columnIndex);
            }

            @Override // com.google.common.collect.Table.Cell
            @CheckForNull
            public V getValue() {
                return (V) ArrayTable.this.at(this.rowIndex, this.columnIndex);
            }
        };
    }

    @Override // com.google.common.collect.Table
    public Map<C, V> row(R r) {
        Preconditions.checkNotNull(r);
        Integer num = this.rowKeyToIndex.get(r);
        if (num == null) {
            return Collections.emptyMap();
        }
        return new Row(num.intValue());
    }

    private class Row extends ArrayMap<C, V> {
        final int rowIndex;

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        String getKeyRole() {
            return "Column";
        }

        Row(int i) {
            super(ArrayTable.this.columnKeyToIndex);
            this.rowIndex = i;
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        @CheckForNull
        V getValue(int i) {
            return (V) ArrayTable.this.at(this.rowIndex, i);
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap
        @CheckForNull
        V setValue(int i, @CheckForNull V v) {
            return (V) ArrayTable.this.set(this.rowIndex, i, v);
        }
    }

    @Override // com.google.common.collect.Table
    public Map<R, Map<C, V>> rowMap() {
        ArrayTable<R, C, V>.RowMap rowMap = this.rowMap;
        if (rowMap != null) {
            return rowMap;
        }
        ArrayTable<R, C, V>.RowMap rowMap2 = new RowMap();
        this.rowMap = rowMap2;
        return rowMap2;
    }

    private class RowMap extends ArrayMap<R, Map<C, V>> {
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        String getKeyRole() {
            return "Row";
        }

        private RowMap() {
            super(ArrayTable.this.rowKeyToIndex);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public Map<C, V> getValue(int i) {
            return new Row(i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ArrayTable.ArrayMap
        public Map<C, V> setValue(int i, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ArrayTable.ArrayMap, java.util.AbstractMap, java.util.Map
        @CheckForNull
        public Map<C, V> put(R r, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }
    }
}
