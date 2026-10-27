package p;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class g41 implements Iterator, g81 {
    public int c;
    public int e;
    public boolean k;

    public g41(int i) {
        this.c = i;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.e < this.c;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object objF;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i = this.e;
        jb jbVar = (jb) this;
        int i2 = jbVar.l;
        Object obj = jbVar.m;
        switch (i2) {
            case 0:
                objF = ((nb) obj).f(i);
                break;
            case 1:
                objF = ((nb) obj).i(i);
                break;
            default:
                objF = ((qb) obj).e[i];
                break;
        }
        this.e++;
        this.k = true;
        return objF;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.k) {
            throw new IllegalStateException("Call next() before removing an element.".toString());
        }
        int i = this.e - 1;
        this.e = i;
        jb jbVar = (jb) this;
        int i2 = jbVar.l;
        Object obj = jbVar.m;
        switch (i2) {
            case 0:
                ((nb) obj).g(i);
                break;
            case 1:
                ((nb) obj).g(i);
                break;
            default:
                ((qb) obj).a(i);
                break;
        }
        this.c--;
        this.k = false;
    }
}
