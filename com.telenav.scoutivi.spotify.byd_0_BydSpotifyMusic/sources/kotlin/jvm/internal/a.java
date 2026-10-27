package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ArrayIterator.kt */
/* JADX INFO: loaded from: classes.dex */
final class a<T> implements Iterator<T>, s5.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final T[] f7492e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f7493g;

    public a(T[] array) {
        l.e(array, "array");
        this.f7492e = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f7493g < this.f7492e.length;
    }

    @Override // java.util.Iterator
    public T next() {
        try {
            T[] tArr = this.f7492e;
            int i7 = this.f7493g;
            this.f7493g = i7 + 1;
            return tArr[i7];
        } catch (ArrayIndexOutOfBoundsException e7) {
            this.f7493g--;
            throw new NoSuchElementException(e7.getMessage());
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
