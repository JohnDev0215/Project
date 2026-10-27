package com.android.launcher3.util;

import android.util.SparseArray;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class IntSparseArrayMap<E> extends SparseArray<E> implements Iterable<E> {
    public boolean containsKey(int key) {
        return indexOfKey(key) >= 0;
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

    @Override // android.util.SparseArray
    /* JADX INFO: renamed from: clone */
    public IntSparseArrayMap<E> mo43clone() {
        return (IntSparseArrayMap) super.mo43clone();
    }

    @Override // java.lang.Iterable
    public Iterator<E> iterator() {
        return new ValueIterator();
    }

    class ValueIterator implements Iterator<E> {
        private int mNextIndex = 0;

        ValueIterator() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.mNextIndex < IntSparseArrayMap.this.size();
        }

        @Override // java.util.Iterator
        public E next() {
            IntSparseArrayMap intSparseArrayMap = IntSparseArrayMap.this;
            int i = this.mNextIndex;
            this.mNextIndex = i + 1;
            return intSparseArrayMap.valueAt(i);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
