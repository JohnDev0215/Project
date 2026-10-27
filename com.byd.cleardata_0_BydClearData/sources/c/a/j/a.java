package c.a.j;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class a implements b, c.a.m.a.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c.a.m.h.b<b> f1809b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f1810c;

    @Override // c.a.m.a.a
    public boolean a(b bVar) {
        c.a.m.b.b.c(bVar, "Disposable item is null");
        if (this.f1810c) {
            return false;
        }
        synchronized (this) {
            if (this.f1810c) {
                return false;
            }
            c.a.m.h.b<b> bVar2 = this.f1809b;
            if (bVar2 != null && bVar2.e(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // c.a.m.a.a
    public boolean b(b bVar) {
        if (!a(bVar)) {
            return false;
        }
        bVar.e();
        return true;
    }

    @Override // c.a.m.a.a
    public boolean c(b bVar) {
        c.a.m.b.b.c(bVar, "d is null");
        if (!this.f1810c) {
            synchronized (this) {
                if (!this.f1810c) {
                    c.a.m.h.b<b> bVar2 = this.f1809b;
                    if (bVar2 == null) {
                        bVar2 = new c.a.m.h.b<>();
                        this.f1809b = bVar2;
                    }
                    bVar2.a(bVar);
                    return true;
                }
            }
        }
        bVar.e();
        return false;
    }

    public void d() {
        if (this.f1810c) {
            return;
        }
        synchronized (this) {
            if (this.f1810c) {
                return;
            }
            c.a.m.h.b<b> bVar = this.f1809b;
            this.f1809b = null;
            f(bVar);
        }
    }

    @Override // c.a.j.b
    public void e() {
        if (this.f1810c) {
            return;
        }
        synchronized (this) {
            if (this.f1810c) {
                return;
            }
            this.f1810c = true;
            c.a.m.h.b<b> bVar = this.f1809b;
            this.f1809b = null;
            f(bVar);
        }
    }

    void f(c.a.m.h.b<b> bVar) {
        if (bVar == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Object obj : bVar.b()) {
            if (obj instanceof b) {
                try {
                    ((b) obj).e();
                } catch (Throwable th) {
                    c.a.k.b.b(th);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
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
    public boolean g() {
        return this.f1810c;
    }
}
