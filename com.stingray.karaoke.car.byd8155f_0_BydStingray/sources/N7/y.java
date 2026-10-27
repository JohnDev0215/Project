package n7;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class y implements Iterator, B7.a {
    public abstract int b();

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return Integer.valueOf(b());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
