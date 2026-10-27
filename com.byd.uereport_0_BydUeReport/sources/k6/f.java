package k6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: JsonArray.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends i implements Iterable<i> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<i> f5921d = new ArrayList();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof f) && ((f) obj).f5921d.equals(this.f5921d));
    }

    public int hashCode() {
        return this.f5921d.hashCode();
    }

    public void i(i iVar) {
        if (iVar == null) {
            iVar = j.f5922a;
        }
        this.f5921d.add(iVar);
    }

    @Override // java.lang.Iterable
    public Iterator<i> iterator() {
        return this.f5921d.iterator();
    }
}
