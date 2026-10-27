package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ArrayIterator.kt */
/* JADX INFO: loaded from: classes.dex */
final class a<T> implements Iterator<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4073b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final T[] f4074c;

    public a(T[] tArr) {
        h.d(tArr, "array");
        this.f4074c = tArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f4073b < this.f4074c.length;
    }

    @Override // java.util.Iterator
    public T next() {
        try {
            T[] tArr = this.f4074c;
            int i = this.f4073b;
            this.f4073b = i + 1;
            return tArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f4073b--;
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
