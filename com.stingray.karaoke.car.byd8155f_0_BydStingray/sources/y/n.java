package Y;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class n implements Iterator, B7.a {
    public Object[] l = m.f10600e.d;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f10603m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f10604n;

    public final void b(Object[] objArr, int i7, int i10) {
        this.l = objArr;
        this.f10603m = i7;
        this.f10604n = i10;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f10604n < this.f10603m;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
