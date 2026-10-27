package c.b.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: JsonArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends k implements Iterable<k> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List<k> f2910c = new ArrayList();

    @Override // c.b.a.k
    public String d() {
        if (this.f2910c.size() == 1) {
            return this.f2910c.get(0).d();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof h) && ((h) obj).f2910c.equals(this.f2910c));
    }

    public int hashCode() {
        return this.f2910c.hashCode();
    }

    public void i(k kVar) {
        if (kVar == null) {
            kVar = l.f2911a;
        }
        this.f2910c.add(kVar);
    }

    @Override // java.lang.Iterable
    public Iterator<k> iterator() {
        return this.f2910c.iterator();
    }
}
