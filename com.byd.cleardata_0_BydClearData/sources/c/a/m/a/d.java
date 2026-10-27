package c.a.m.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class d implements c.a.j.b, a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    List<c.a.j.b> f1821b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f1822c;

    @Override // c.a.m.a.a
    public boolean a(c.a.j.b bVar) {
        c.a.m.b.b.c(bVar, "Disposable item is null");
        if (this.f1822c) {
            return false;
        }
        synchronized (this) {
            if (this.f1822c) {
                return false;
            }
            List<c.a.j.b> list = this.f1821b;
            if (list != null && list.remove(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // c.a.m.a.a
    public boolean b(c.a.j.b bVar) {
        if (!a(bVar)) {
            return false;
        }
        bVar.e();
        return true;
    }

    @Override // c.a.m.a.a
    public boolean c(c.a.j.b bVar) {
        c.a.m.b.b.c(bVar, "d is null");
        if (!this.f1822c) {
            synchronized (this) {
                if (!this.f1822c) {
                    List linkedList = this.f1821b;
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                        this.f1821b = linkedList;
                    }
                    linkedList.add(bVar);
                    return true;
                }
            }
        }
        bVar.e();
        return false;
    }

    void d(List<c.a.j.b> list) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = null;
        Iterator<c.a.j.b> it = list.iterator();
        while (it.hasNext()) {
            try {
                it.next().e();
            } catch (Throwable th) {
                c.a.k.b.b(th);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        if (arrayList != null) {
            if (arrayList.size() != 1) {
                throw new c.a.k.a(arrayList);
            }
            throw c.a.m.h.a.a((Throwable) arrayList.get(0));
        }
    }

    @Override // c.a.j.b
    public void e() {
        if (this.f1822c) {
            return;
        }
        synchronized (this) {
            if (this.f1822c) {
                return;
            }
            this.f1822c = true;
            List<c.a.j.b> list = this.f1821b;
            this.f1821b = null;
            d(list);
        }
    }

    @Override // c.a.j.b
    public boolean g() {
        return this.f1822c;
    }
}
