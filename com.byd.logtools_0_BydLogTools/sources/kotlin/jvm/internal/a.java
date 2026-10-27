package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ArrayIterator.kt */
/* JADX INFO: loaded from: classes.dex */
final class a<T> implements Iterator<T>, kotlin.jvm.internal.n.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f5111b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final T[] f5112c;

    public a(T[] tArr) {
        i.c(tArr, "array");
        this.f5112c = tArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f5111b < this.f5112c.length;
    }

    @Override // java.util.Iterator
    public T next() {
        try {
            T[] tArr = this.f5112c;
            int i = this.f5111b;
            this.f5111b = i + 1;
            return tArr[i];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f5111b--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
