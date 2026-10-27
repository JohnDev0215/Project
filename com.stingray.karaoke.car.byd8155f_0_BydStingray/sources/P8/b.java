package P8;

import java.util.Iterator;
import n7.AbstractC1999n;
import n7.x;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Iterator, B7.a {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Iterator f5459m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5460n;

    public b(Iterator it) {
        A7.m.f(it, "iterator");
        this.f5459m = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        Iterator it;
        switch (this.l) {
            case 0:
                break;
            default:
                return this.f5459m.hasNext();
        }
        while (true) {
            int i7 = this.f5460n;
            it = this.f5459m;
            if (i7 > 0 && it.hasNext()) {
                it.next();
                this.f5460n--;
            }
        }
        return it.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Iterator it;
        switch (this.l) {
            case 0:
                break;
            default:
                int i7 = this.f5460n;
                this.f5460n = i7 + 1;
                if (i7 >= 0) {
                    return new x(i7, this.f5459m.next());
                }
                AbstractC1999n.I();
                throw null;
        }
        while (true) {
            int i10 = this.f5460n;
            it = this.f5459m;
            if (i10 > 0 && it.hasNext()) {
                it.next();
                this.f5460n--;
            }
        }
        return it.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public b(c cVar) {
        this.f5459m = cVar.f5461a.iterator();
        this.f5460n = cVar.f5462b;
    }
}
