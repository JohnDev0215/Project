package org.mockito.internal.matchers.text;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
class ArrayIterator implements Iterator<Object> {
    private final Object array;
    private int currentIndex = 0;

    public ArrayIterator(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Expected array instance but got null");
        }
        if (!obj.getClass().isArray()) {
            throw new IllegalArgumentException("Expected array but got object of type: " + obj.getClass() + ", the object: " + obj.toString());
        }
        this.array = obj;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.currentIndex < Array.getLength(this.array);
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object obj = this.array;
        int i = this.currentIndex;
        this.currentIndex = i + 1;
        return Array.get(obj, i);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("cannot remove items from an array");
    }
}
