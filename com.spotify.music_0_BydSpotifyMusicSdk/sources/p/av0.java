package p;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class av0 implements Iterator, g81 {
    public final Iterator c;
    public Iterator e;
    public final /* synthetic */ bv0 k;

    public av0(bv0 bv0Var) {
        this.k = bv0Var;
        this.c = bv0Var.a.iterator();
    }

    public final boolean a() {
        Iterator it = this.e;
        if (it != null && !it.hasNext()) {
            this.e = null;
        }
        while (true) {
            if (this.e != null) {
                break;
            }
            Iterator it2 = this.c;
            if (!it2.hasNext()) {
                return false;
            }
            Object next = it2.next();
            bv0 bv0Var = this.k;
            Iterator it3 = (Iterator) bv0Var.c.invoke(bv0Var.b.invoke(next));
            if (it3.hasNext()) {
                this.e = it3;
                break;
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return a();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!a()) {
            throw new NoSuchElementException();
        }
        Iterator it = this.e;
        v03.h(it);
        return it.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
