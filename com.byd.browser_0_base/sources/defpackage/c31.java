package defpackage;

import java.util.Iterator;

/* JADX INFO: compiled from: Iterators.java */
/* JADX INFO: loaded from: classes.dex */
public final class c31 extends xi3<Object> {
    public final /* synthetic */ Iterator e;

    public c31(Iterator it) {
        this.e = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.e.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.e.next();
    }
}
