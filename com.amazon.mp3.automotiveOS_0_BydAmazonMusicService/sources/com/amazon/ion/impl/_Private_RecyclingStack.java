package com.amazon.ion.impl;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class _Private_RecyclingStack<T> {
    private final ElementFactory<T> elementFactory;
    private final List<T> elements;
    private int currentIndex = -1;
    private T top = null;

    public interface ElementFactory<T> {
        T newElement();
    }

    public _Private_RecyclingStack(int i, ElementFactory<T> elementFactory) {
        this.elements = new ArrayList(i);
        this.elementFactory = elementFactory;
    }

    public T push() {
        int i = this.currentIndex + 1;
        this.currentIndex = i;
        if (i >= this.elements.size()) {
            T tNewElement = this.elementFactory.newElement();
            this.top = tNewElement;
            this.elements.add(tNewElement);
        } else {
            this.top = this.elements.get(this.currentIndex);
        }
        return this.top;
    }

    public T peek() {
        return this.top;
    }

    public T pop() {
        T t = this.top;
        int i = this.currentIndex - 1;
        this.currentIndex = i;
        if (i >= 0) {
            this.top = this.elements.get(i);
        } else {
            this.top = null;
            this.currentIndex = -1;
        }
        return t;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public int size() {
        return this.currentIndex + 1;
    }
}
