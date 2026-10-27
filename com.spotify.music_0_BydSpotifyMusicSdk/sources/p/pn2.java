package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class pn2 implements Iterator {
    public final Iterator c;

    public pn2(Iterator it) {
        it.getClass();
        this.c = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return ((h61) this).e.apply(this.c.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.c.remove();
    }
}
