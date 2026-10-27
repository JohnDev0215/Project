package defpackage;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: Iterators.java */
/* JADX INFO: loaded from: classes.dex */
public final class d31 extends xi3<Object> {
    public boolean e;
    public final /* synthetic */ Object f;

    public d31(Object obj) {
        this.f = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.e;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.e) {
            throw new NoSuchElementException();
        }
        this.e = true;
        return this.f;
    }
}
