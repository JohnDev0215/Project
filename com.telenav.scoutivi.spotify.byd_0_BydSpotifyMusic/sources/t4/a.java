package t4;

import java.util.ArrayList;

/* JADX INFO: compiled from: CompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements b, w4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    b5.c<b> f9557e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    volatile boolean f9558g;

    @Override // w4.a
    public boolean a(b bVar) {
        x4.b.c(bVar, "d is null");
        if (!this.f9558g) {
            synchronized (this) {
                if (!this.f9558g) {
                    b5.c<b> cVar = this.f9557e;
                    if (cVar == null) {
                        cVar = new b5.c<>();
                        this.f9557e = cVar;
                    }
                    cVar.a(bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    @Override // w4.a
    public boolean b(b bVar) {
        x4.b.c(bVar, "Disposable item is null");
        if (this.f9558g) {
            return false;
        }
        synchronized (this) {
            if (this.f9558g) {
                return false;
            }
            b5.c<b> cVar = this.f9557e;
            if (cVar != null && cVar.e(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // w4.a
    public boolean c(b bVar) {
        if (!b(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    void d(b5.c<b> cVar) {
        if (cVar == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Object obj : cVar.b()) {
            if (obj instanceof b) {
                try {
                    ((b) obj).dispose();
                } catch (Throwable th) {
                    u4.b.b(th);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
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
        if (this.f9558g) {
            return;
        }
        synchronized (this) {
            if (this.f9558g) {
                return;
            }
            this.f9558g = true;
            b5.c<b> cVar = this.f9557e;
            this.f9557e = null;
            d(cVar);
        }
    }

    @Override // t4.b
    public boolean isDisposed() {
        return this.f9558g;
    }
}
