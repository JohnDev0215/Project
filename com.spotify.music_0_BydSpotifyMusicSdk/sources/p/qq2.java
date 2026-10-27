package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class qq2 implements Iterator {
    public final Iterator c;

    public qq2(rq2 rq2Var) {
        this.c = rq2Var.c.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return (String) this.c.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
