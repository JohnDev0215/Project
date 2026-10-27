package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class qn2 implements Iterator, g81 {
    public final Iterator c;
    public final /* synthetic */ rn2 e;

    public qn2(rn2 rn2Var) {
        this.e = rn2Var;
        this.c = rn2Var.a.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.e.b.invoke(this.c.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
