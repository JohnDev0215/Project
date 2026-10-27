package d.a.p.a;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: ListCompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements d.a.m.b, a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<d.a.m.b> f3168c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile boolean f3169d;

    public b() {
    }

    @Override // d.a.p.a.a
    public boolean a(d.a.m.b bVar) {
        d.a.p.b.b.d(bVar, "Disposable item is null");
        if (this.f3169d) {
            return false;
        }
        synchronized (this) {
            if (this.f3169d) {
                return false;
            }
            List<d.a.m.b> list = this.f3168c;
            if (list != null && list.remove(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // d.a.p.a.a
    public boolean b(d.a.m.b bVar) {
        if (!a(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    @Override // d.a.p.a.a
    public boolean c(d.a.m.b bVar) {
        d.a.p.b.b.d(bVar, "d is null");
        if (!this.f3169d) {
            synchronized (this) {
                if (!this.f3169d) {
                    List linkedList = this.f3168c;
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                        this.f3168c = linkedList;
                    }
                    linkedList.add(bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    public void d(List<d.a.m.b> list) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = null;
        Iterator<d.a.m.b> it = list.iterator();
        while (it.hasNext()) {
            try {
                it.next().dispose();
            } catch (Throwable th) {
                d.a.n.a.a(th);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        if (arrayList != null) {
            if (arrayList.size() != 1) {
                throw new CompositeException(arrayList);
            }
            throw ExceptionHelper.c((Throwable) arrayList.get(0));
        }
    }

    @Override // d.a.m.b
    public void dispose() {
        if (this.f3169d) {
            return;
        }
        synchronized (this) {
            if (this.f3169d) {
                return;
            }
            this.f3169d = true;
            List<d.a.m.b> list = this.f3168c;
            this.f3168c = null;
            d(list);
        }
    }

    @Override // d.a.m.b
    public boolean isDisposed() {
        return this.f3169d;
    }

    public b(d.a.m.b... bVarArr) {
        d.a.p.b.b.d(bVarArr, "resources is null");
        this.f3168c = new LinkedList();
        for (d.a.m.b bVar : bVarArr) {
            d.a.p.b.b.d(bVar, "Disposable item is null");
            this.f3168c.add(bVar);
        }
    }
}
