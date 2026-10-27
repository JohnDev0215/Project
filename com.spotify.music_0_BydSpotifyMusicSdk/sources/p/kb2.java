package p;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public final class kb2 implements Iterator, c00, g81 {
    public int c;
    public Object e;
    public c00 k;

    public final RuntimeException a() {
        int i = this.c;
        if (i == 4) {
            return new NoSuchElementException();
        }
        if (i == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.c);
    }

    @Override // p.c00
    public final y10 getContext() {
        return EmptyCoroutineContext.c;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i;
        while (true) {
            i = this.c;
            if (i != 0) {
                break;
            }
            this.c = 5;
            c00 c00Var = this.k;
            v03.h(c00Var);
            this.k = null;
            c00Var.k(lq2.a);
        }
        if (i == 1) {
            v03.h(null);
            throw null;
        }
        if (i == 2 || i == 3) {
            return true;
        }
        if (i == 4) {
            return false;
        }
        throw a();
    }

    @Override // p.c00
    public final void k(Object obj) {
        kotlin.a.d(obj);
        this.c = 4;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.c;
        if (i == 0 || i == 1) {
            if (hasNext()) {
                return next();
            }
            throw new NoSuchElementException();
        }
        if (i == 2) {
            this.c = 1;
            v03.h(null);
            throw null;
        }
        if (i != 3) {
            throw a();
        }
        this.c = 0;
        Object obj = this.e;
        this.e = null;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
