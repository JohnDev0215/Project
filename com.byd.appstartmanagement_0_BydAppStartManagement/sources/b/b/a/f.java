package b.b.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: JsonArray.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends i implements Iterable<i> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<i> f386b = new ArrayList();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof f) && ((f) obj).f386b.equals(this.f386b));
    }

    public int hashCode() {
        return this.f386b.hashCode();
    }

    public void i(i iVar) {
        if (iVar == null) {
            iVar = j.f387a;
        }
        this.f386b.add(iVar);
    }

    @Override // java.lang.Iterable
    public Iterator<i> iterator() {
        return this.f386b.iterator();
    }
}
