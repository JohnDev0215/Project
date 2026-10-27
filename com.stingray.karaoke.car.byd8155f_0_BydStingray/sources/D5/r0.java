package d5;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class r0 implements Iterator {
    public final Iterator l;

    public r0(Iterator it) {
        it.getClass();
        this.l = it;
    }

    public abstract Object a(Object obj);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.l.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return a(this.l.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.l.remove();
    }
}
