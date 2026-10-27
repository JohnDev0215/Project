package com.amazon.alexa.voice.core.internal.util;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class ArrayIterator<T> implements Iterator<T> {
    private final T[] array;
    private int index;

    public ArrayIterator(T[] tArr) {
        this.array = tArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override // java.util.Iterator
    public T next() {
        T[] tArr = this.array;
        int i = this.index;
        this.index = i + 1;
        return tArr[i];
    }
}
