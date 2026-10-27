package c.b.b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: JsonArray.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends i implements Iterable<i> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<i> f4168b = new ArrayList();

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof f) && ((f) obj).f4168b.equals(this.f4168b));
    }

    public int hashCode() {
        return this.f4168b.hashCode();
    }

    public void i(i iVar) {
        if (iVar == null) {
            iVar = j.f4169a;
        }
        this.f4168b.add(iVar);
    }

    @Override // java.lang.Iterable
    public Iterator<i> iterator() {
        return this.f4168b.iterator();
    }
}
