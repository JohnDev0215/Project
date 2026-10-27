package p;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class ku0 implements Iterator, g81 {
    public final Iterator c;
    public int e = -1;
    public Object k;
    public final /* synthetic */ lu0 l;

    public ku0(lu0 lu0Var) {
        this.l = lu0Var;
        this.c = lu0Var.a.iterator();
    }

    public final void a() {
        Object next;
        lu0 lu0Var;
        do {
            Iterator it = this.c;
            if (!it.hasNext()) {
                this.e = 0;
                return;
            } else {
                next = it.next();
                lu0Var = this.l;
            }
        } while (((Boolean) lu0Var.c.invoke(next)).booleanValue() != lu0Var.b);
        this.k = next;
        this.e = 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.e == -1) {
            a();
        }
        return this.e == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.e == -1) {
            a();
        }
        if (this.e == 0) {
            throw new NoSuchElementException();
        }
        Object obj = this.k;
        this.k = null;
        this.e = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
