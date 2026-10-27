package p;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class lb implements Iterator, Map.Entry {
    public int c;
    public int e = -1;
    public boolean k;
    public final /* synthetic */ nb l;

    public lb(nb nbVar) {
        this.l = nbVar;
        this.c = nbVar.k - 1;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.k) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        int i = this.e;
        nb nbVar = this.l;
        return v03.b(key, nbVar.f(i)) && v03.b(entry.getValue(), nbVar.i(this.e));
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        if (this.k) {
            return this.l.f(this.e);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.k) {
            return this.l.i(this.e);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.e < this.c;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.k) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        int i = this.e;
        nb nbVar = this.l;
        Object objF = nbVar.f(i);
        Object objI = nbVar.i(this.e);
        return (objF == null ? 0 : objF.hashCode()) ^ (objI != null ? objI.hashCode() : 0);
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.e++;
        this.k = true;
        return this;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.k) {
            throw new IllegalStateException();
        }
        this.l.g(this.e);
        this.e--;
        this.c--;
        this.k = false;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (this.k) {
            return this.l.h(this.e, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
