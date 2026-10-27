package io.sentry;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/* JADX INFO: renamed from: io.sentry.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1463f extends AbstractCollection implements Queue, Serializable {
    public final transient Object[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient int f16531m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public transient int f16532n = 0;
    public transient boolean o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f16533p;

    public C1463f(int i7) {
        if (i7 <= 0) {
            throw new IllegalArgumentException("The size must be greater than 0");
        }
        Object[] objArr = new Object[i7];
        this.l = objArr;
        this.f16533p = objArr.length;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Queue
    public final boolean add(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Attempted to add null object to queue");
        }
        int size = size();
        int i7 = this.f16533p;
        if (size == i7) {
            remove();
        }
        int i10 = this.f16532n;
        int i11 = i10 + 1;
        this.f16532n = i11;
        this.l[i10] = obj;
        if (i11 >= i7) {
            this.f16532n = 0;
        }
        if (this.f16532n == this.f16531m) {
            this.o = true;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.o = false;
        this.f16531m = 0;
        this.f16532n = 0;
        Arrays.fill(this.l, (Object) null);
    }

    @Override // java.util.Queue
    public final Object element() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return peek();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new C1460e(this);
    }

    @Override // java.util.Queue
    public final boolean offer(Object obj) {
        add(obj);
        return true;
    }

    @Override // java.util.Queue
    public final Object peek() {
        if (isEmpty()) {
            return null;
        }
        return this.l[this.f16531m];
    }

    @Override // java.util.Queue
    public final Object poll() {
        if (isEmpty()) {
            return null;
        }
        return remove();
    }

    @Override // java.util.Queue
    public final Object remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        int i7 = this.f16531m;
        Object[] objArr = this.l;
        Object obj = objArr[i7];
        if (obj != null) {
            int i10 = i7 + 1;
            this.f16531m = i10;
            objArr[i7] = null;
            if (i10 >= this.f16533p) {
                this.f16531m = 0;
            }
            this.o = false;
        }
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        int i7 = this.f16532n;
        int i10 = this.f16531m;
        int i11 = this.f16533p;
        if (i7 < i10) {
            return (i11 - i10) + i7;
        }
        if (i7 != i10) {
            return i7 - i10;
        }
        if (this.o) {
            return i11;
        }
        return 0;
    }
}
