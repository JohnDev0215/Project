package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class j41 implements Iterator, g81 {
    public final /* synthetic */ int c = 0;
    public final Iterator e;
    public int k;

    public j41(Iterator it) {
        v03.j(it, "iterator");
        this.e = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.c;
        Iterator it = this.e;
        switch (i) {
            case 0:
                return it.hasNext();
        }
        while (this.k > 0 && it.hasNext()) {
            it.next();
            this.k--;
        }
        return it.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.c;
        Iterator it = this.e;
        switch (i) {
            case 0:
                int i2 = this.k;
                this.k = i2 + 1;
                if (i2 >= 0) {
                    return new h41(i2, it.next());
                }
                v03.P();
                throw null;
        }
        while (this.k > 0 && it.hasNext()) {
            it.next();
            this.k--;
        }
        return it.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.c) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public j41(jb0 jb0Var) {
        this.e = jb0Var.a.iterator();
        this.k = jb0Var.b;
    }
}
