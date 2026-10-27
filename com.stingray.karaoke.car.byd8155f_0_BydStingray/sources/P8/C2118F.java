package p8;

import java.util.Iterator;

/* JADX INFO: renamed from: p8.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2118F implements Iterator {
    public Iterator l;

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.l.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return (String) this.l.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
