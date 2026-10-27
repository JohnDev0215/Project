package w4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: ListCompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements t4.b, a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    List<t4.b> f9733e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    volatile boolean f9734g;

    @Override // w4.a
    public boolean a(t4.b bVar) {
        x4.b.c(bVar, "d is null");
        if (!this.f9734g) {
            synchronized (this) {
                if (!this.f9734g) {
                    List linkedList = this.f9733e;
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                        this.f9733e = linkedList;
                    }
                    linkedList.add(bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    @Override // w4.a
    public boolean b(t4.b bVar) {
        x4.b.c(bVar, "Disposable item is null");
        if (this.f9734g) {
            return false;
        }
        synchronized (this) {
            if (this.f9734g) {
                return false;
            }
            List<t4.b> list = this.f9733e;
            if (list != null && list.remove(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // w4.a
    public boolean c(t4.b bVar) {
        if (!b(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    void d(List<t4.b> list) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = null;
        Iterator<t4.b> it = list.iterator();
        while (it.hasNext()) {
            try {
                it.next().dispose();
            } catch (Throwable th) {
                u4.b.b(th);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        if (arrayList != null) {
            if (arrayList.size() != 1) {
                throw new u4.a(arrayList);
            }
            throw b5.b.a((Throwable) arrayList.get(0));
        }
    }

    @Override // t4.b
    public void dispose() {
        if (this.f9734g) {
            return;
        }
        synchronized (this) {
            if (this.f9734g) {
                return;
            }
            this.f9734g = true;
            List<t4.b> list = this.f9733e;
            this.f9733e = null;
            d(list);
        }
    }

    @Override // t4.b
    public boolean isDisposed() {
        return this.f9734g;
    }
}
