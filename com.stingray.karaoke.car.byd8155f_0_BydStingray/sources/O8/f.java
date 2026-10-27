package O8;

import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Iterator {
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f5190m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g f5191n;

    public f(g gVar) {
        this.f5191n = gVar;
        this.f5190m = ((AbstractList) gVar).modCount;
    }

    public final void a() {
        g gVar = this.f5191n;
        int i7 = ((AbstractList) gVar).modCount;
        int i10 = this.f5190m;
        if (i7 == i10) {
            return;
        }
        throw new ConcurrentModificationException("ModCount: " + ((AbstractList) gVar).modCount + "; expected: " + i10);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.l;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.l) {
            throw new NoSuchElementException();
        }
        this.l = true;
        a();
        return this.f5191n.f5192m;
    }

    @Override // java.util.Iterator
    public final void remove() {
        a();
        this.f5191n.clear();
    }
}
