package p8;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: p8.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2113A implements Iterator {
    @Override // java.util.Iterator
    public final boolean hasNext() {
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
