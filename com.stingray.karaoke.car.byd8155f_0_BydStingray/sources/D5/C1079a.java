package d5;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: renamed from: d5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1079a implements Iterator {
    public final Iterator l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f13403m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Collection f13404n = null;
    public Iterator o = O.l;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Y f13405p;

    public C1079a(Y y10) {
        this.f13405p = y10;
        this.l = y10.o.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.l.hasNext() || this.o.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.o.hasNext()) {
            Map.Entry entry = (Map.Entry) this.l.next();
            this.f13403m = entry.getKey();
            Collection collection = (Collection) entry.getValue();
            this.f13404n = collection;
            this.o = collection.iterator();
        }
        return this.o.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.o.remove();
        Collection collection = this.f13404n;
        Objects.requireNonNull(collection);
        if (collection.isEmpty()) {
            this.l.remove();
        }
        Y y10 = this.f13405p;
        y10.f13401p--;
    }
}
