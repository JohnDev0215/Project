package k1;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Iterator {
    public g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17623m;

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f17623m < this.l.f17619p.size();
    }

    @Override // java.util.Iterator
    public final Object next() {
        d dVar = (d) this.l.f17619p.get(this.f17623m);
        this.f17623m++;
        return dVar;
    }
}
