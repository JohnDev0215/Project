package b.a;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
abstract class d<T> implements Iterator<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1271c;
    private int d;
    private boolean e;

    d(int i) {
        this.f1271c = i;
    }

    protected abstract T a(int i);

    protected abstract void b(int i);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.d < this.f1271c;
    }

    @Override // java.util.Iterator
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T tA = a(this.d);
        this.d++;
        this.e = true;
        return tA;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (!this.e) {
            throw new IllegalStateException();
        }
        int i = this.d - 1;
        this.d = i;
        b(i);
        this.f1271c--;
        this.e = false;
    }
}
