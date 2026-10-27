package R7;

import F8.U;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class l implements h {
    public final h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final U f7540m;

    public l(h hVar, U u4) {
        this.l = hVar;
        this.f7540m = u4;
    }

    @Override // R7.h
    public final b e(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        if (((Boolean) this.f7540m.b(c2058c)).booleanValue()) {
            return this.l.e(c2058c);
        }
        return null;
    }

    @Override // R7.h
    public final boolean isEmpty() {
        h hVar = this.l;
        if ((hVar instanceof Collection) && ((Collection) hVar).isEmpty()) {
            return false;
        }
        Iterator it = hVar.iterator();
        while (it.hasNext()) {
            C2058c c2058cA = ((b) it.next()).a();
            if (c2058cA != null && ((Boolean) this.f7540m.b(c2058cA)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.l) {
            C2058c c2058cA = ((b) obj).a();
            if (c2058cA != null && ((Boolean) this.f7540m.b(c2058cA)).booleanValue()) {
                arrayList.add(obj);
            }
        }
        return arrayList.iterator();
    }

    @Override // R7.h
    public final boolean k(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        if (((Boolean) this.f7540m.b(c2058c)).booleanValue()) {
            return this.l.k(c2058c);
        }
        return false;
    }
}
