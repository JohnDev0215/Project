package b.c.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: JsonArray.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f extends i implements Iterable<i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<i> f2197a = new ArrayList();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof f) && ((f) obj).f2197a.equals(this.f2197a));
    }

    public int hashCode() {
        return this.f2197a.hashCode();
    }

    public void i(i iVar) {
        if (iVar == null) {
            iVar = j.f2198a;
        }
        this.f2197a.add(iVar);
    }

    @Override // java.lang.Iterable
    public Iterator<i> iterator() {
        return this.f2197a.iterator();
    }
}
