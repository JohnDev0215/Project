package p;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class h51 implements Iterator, g81 {
    public final int c;
    public final int e;
    public boolean k;
    public int l;

    public h51(int i, int i2, int i3) {
        this.c = i3;
        this.e = i2;
        boolean z = true;
        if (i3 <= 0 ? i < i2 : i > i2) {
            z = false;
        }
        this.k = z;
        this.l = z ? i : i2;
    }

    @Override // java.util.Iterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final /* bridge */ /* synthetic */ Object next() {
        return Integer.valueOf(b());
    }

    public final int b() {
        int i = this.l;
        if (i != this.e) {
            this.l = this.c + i;
        } else {
            if (!this.k) {
                throw new NoSuchElementException();
            }
            this.k = false;
        }
        return i;
    }

    public final void c() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.k;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ void remove() {
        c();
        throw null;
    }
}
