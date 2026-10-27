package p;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class m1 implements Iterator, g81 {
    public final /* synthetic */ int c = 0;
    public int e;
    public final Object k;

    public m1(Object[] objArr) {
        v03.j(objArr, "array");
        this.k = objArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.c;
        Object obj = this.k;
        switch (i) {
            case 0:
                if (this.e < ((p1) obj).size()) {
                }
                break;
            default:
                if (this.e < ((Object[]) obj).length) {
                }
                break;
        }
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.c;
        Object obj = this.k;
        switch (i) {
            case 0:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i2 = this.e;
                this.e = i2 + 1;
                return ((p1) obj).get(i2);
            default:
                try {
                    int i3 = this.e;
                    this.e = i3 + 1;
                    return ((Object[]) obj)[i3];
                } catch (ArrayIndexOutOfBoundsException e) {
                    this.e--;
                    throw new NoSuchElementException(e.getMessage());
                }
        }
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

    public m1(p1 p1Var) {
        this.k = p1Var;
    }
}
