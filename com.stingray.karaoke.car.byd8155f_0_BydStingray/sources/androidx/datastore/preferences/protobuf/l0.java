package androidx.datastore.preferences.protobuf;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class l0 implements Iterator {
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
