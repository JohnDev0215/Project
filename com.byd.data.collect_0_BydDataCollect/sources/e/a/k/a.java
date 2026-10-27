package e.a.k;

import java.util.ArrayList;

/* JADX INFO: compiled from: CompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements b, e.a.n.a.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    e.a.n.h.c<b> f4858b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f4859c;

    @Override // e.a.n.a.a
    public boolean a(b bVar) {
        e.a.n.b.b.c(bVar, "Disposable item is null");
        if (this.f4859c) {
            return false;
        }
        synchronized (this) {
            if (this.f4859c) {
                return false;
            }
            e.a.n.h.c<b> cVar = this.f4858b;
            if (cVar != null && cVar.e(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // e.a.n.a.a
    public boolean b(b bVar) {
        if (!a(bVar)) {
            return false;
        }
        bVar.d();
        return true;
    }

    @Override // e.a.n.a.a
    public boolean c(b bVar) {
        e.a.n.b.b.c(bVar, "d is null");
        if (!this.f4859c) {
            synchronized (this) {
                if (!this.f4859c) {
                    e.a.n.h.c<b> cVar = this.f4858b;
                    if (cVar == null) {
                        cVar = new e.a.n.h.c<>();
                        this.f4858b = cVar;
                    }
                    cVar.a(bVar);
                    return true;
                }
            }
        }
        bVar.d();
        return false;
    }

    @Override // e.a.k.b
    public void d() {
        if (this.f4859c) {
            return;
        }
        synchronized (this) {
            if (this.f4859c) {
                return;
            }
            this.f4859c = true;
            e.a.n.h.c<b> cVar = this.f4858b;
            this.f4858b = null;
            e(cVar);
        }
    }

    void e(e.a.n.h.c<b> cVar) {
        if (cVar == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Object obj : cVar.b()) {
            if (obj instanceof b) {
                try {
                    ((b) obj).d();
                } catch (Throwable th) {
                    e.a.l.b.b(th);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
            }
        }
        if (arrayList != null) {
            if (arrayList.size() != 1) {
                throw new e.a.l.a(arrayList);
            }
            throw e.a.n.h.b.a((Throwable) arrayList.get(0));
        }
    }

    public boolean f() {
        return this.f4859c;
    }
}
