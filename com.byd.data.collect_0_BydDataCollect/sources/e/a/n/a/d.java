package e.a.n.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: ListCompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements e.a.k.b, a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    List<e.a.k.b> f4867b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f4868c;

    @Override // e.a.n.a.a
    public boolean a(e.a.k.b bVar) {
        e.a.n.b.b.c(bVar, "Disposable item is null");
        if (this.f4868c) {
            return false;
        }
        synchronized (this) {
            if (this.f4868c) {
                return false;
            }
            List<e.a.k.b> list = this.f4867b;
            if (list != null && list.remove(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // e.a.n.a.a
    public boolean b(e.a.k.b bVar) {
        if (!a(bVar)) {
            return false;
        }
        bVar.d();
        return true;
    }

    @Override // e.a.n.a.a
    public boolean c(e.a.k.b bVar) {
        e.a.n.b.b.c(bVar, "d is null");
        if (!this.f4868c) {
            synchronized (this) {
                if (!this.f4868c) {
                    List linkedList = this.f4867b;
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                        this.f4867b = linkedList;
                    }
                    linkedList.add(bVar);
                    return true;
                }
            }
        }
        bVar.d();
        return false;
    }

    @Override // e.a.k.b
    public void d() {
        if (this.f4868c) {
            return;
        }
        synchronized (this) {
            if (this.f4868c) {
                return;
            }
            this.f4868c = true;
            List<e.a.k.b> list = this.f4867b;
            this.f4867b = null;
            e(list);
        }
    }

    void e(List<e.a.k.b> list) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = null;
        Iterator<e.a.k.b> it = list.iterator();
        while (it.hasNext()) {
            try {
                it.next().d();
            } catch (Throwable th) {
                e.a.l.b.b(th);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        if (arrayList != null) {
            if (arrayList.size() != 1) {
                throw new e.a.l.a(arrayList);
            }
            throw e.a.n.h.b.a((Throwable) arrayList.get(0));
        }
    }
}
