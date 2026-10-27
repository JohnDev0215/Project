package c.b.h.a;

import a.b.k.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class d implements c.b.e.b, a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public List<c.b.e.b> f2229a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile boolean f2230b;

    @Override // c.b.h.a.a
    public boolean a(c.b.e.b bVar) {
        c.b.h.b.b.a(bVar, "d is null");
        if (!this.f2230b) {
            synchronized (this) {
                if (!this.f2230b) {
                    List linkedList = this.f2229a;
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                        this.f2229a = linkedList;
                    }
                    linkedList.add(bVar);
                    return true;
                }
            }
        }
        bVar.d();
        return false;
    }

    @Override // c.b.h.a.a
    public boolean b(c.b.e.b bVar) {
        c.b.h.b.b.a(bVar, "Disposable item is null");
        if (this.f2230b) {
            return false;
        }
        synchronized (this) {
            if (this.f2230b) {
                return false;
            }
            List<c.b.e.b> list = this.f2229a;
            if (list != null && list.remove(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // c.b.h.a.a
    public boolean c(c.b.e.b bVar) {
        if (!b(bVar)) {
            return false;
        }
        bVar.d();
        return true;
    }

    @Override // c.b.e.b
    public void d() {
        if (this.f2230b) {
            return;
        }
        synchronized (this) {
            if (this.f2230b) {
                return;
            }
            this.f2230b = true;
            List<c.b.e.b> list = this.f2229a;
            ArrayList arrayList = null;
            this.f2229a = null;
            if (list == null) {
                return;
            }
            Iterator<c.b.e.b> it = list.iterator();
            while (it.hasNext()) {
                try {
                    it.next().d();
                } catch (Throwable th) {
                    l.i.C0(th);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
            }
            if (arrayList != null) {
                if (arrayList.size() != 1) {
                    throw new c.b.f.a(arrayList);
                }
                throw c.b.h.f.b.a((Throwable) arrayList.get(0));
            }
        }
    }
}
