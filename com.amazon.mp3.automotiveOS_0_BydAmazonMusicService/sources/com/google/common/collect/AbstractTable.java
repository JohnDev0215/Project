package com.google.common.collect;

import com.google.common.collect.Table;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
abstract class AbstractTable<R, C, V> implements Table<R, C, V> {

    @CheckForNull
    private transient Set<Table.Cell<R, C, V>> cellSet;

    abstract Iterator<Table.Cell<R, C, V>> cellIterator();

    AbstractTable() {
    }

    @Override // com.google.common.collect.Table
    public boolean contains(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Map map = (Map) Maps.safeGet(rowMap(), obj);
        return map != null && Maps.safeContainsKey(map, obj2);
    }

    @Override // com.google.common.collect.Table
    @CheckForNull
    public V get(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Map map = (Map) Maps.safeGet(rowMap(), obj);
        if (map == null) {
            return null;
        }
        return (V) Maps.safeGet(map, obj2);
    }

    @Override // com.google.common.collect.Table
    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        Iterators.clear(cellSet().iterator());
    }

    @Override // com.google.common.collect.Table
    @CheckForNull
    public V put(R r, C c, V v) {
        return row(r).put(c, v);
    }

    @Override // com.google.common.collect.Table
    public Set<Table.Cell<R, C, V>> cellSet() {
        Set<Table.Cell<R, C, V>> set = this.cellSet;
        if (set != null) {
            return set;
        }
        Set<Table.Cell<R, C, V>> setCreateCellSet = createCellSet();
        this.cellSet = setCreateCellSet;
        return setCreateCellSet;
    }

    Set<Table.Cell<R, C, V>> createCellSet() {
        return new CellSet();
    }

    class CellSet extends AbstractSet<Table.Cell<R, C, V>> {
        CellSet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            if (!(obj instanceof Table.Cell)) {
                return false;
            }
            Table.Cell cell = (Table.Cell) obj;
            Map map = (Map) Maps.safeGet(AbstractTable.this.rowMap(), cell.getRowKey());
            return map != null && Collections2.safeContains(map.entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue()));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            if (!(obj instanceof Table.Cell)) {
                return false;
            }
            Table.Cell cell = (Table.Cell) obj;
            Map map = (Map) Maps.safeGet(AbstractTable.this.rowMap(), cell.getRowKey());
            return map != null && Collections2.safeRemove(map.entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue()));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            AbstractTable.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Table.Cell<R, C, V>> iterator() {
            return AbstractTable.this.cellIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AbstractTable.this.size();
        }
    }

    public boolean equals(@CheckForNull Object obj) {
        return Tables.equalsImpl(this, obj);
    }

    public int hashCode() {
        return cellSet().hashCode();
    }

    public String toString() {
        return rowMap().toString();
    }
}
