package d.a.m;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.ArrayList;

/* JADX INFO: compiled from: CompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements b, d.a.p.a.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d.a.p.h.b<b> f3166c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile boolean f3167d;

    @Override // d.a.p.a.a
    public boolean a(b bVar) {
        d.a.p.b.b.d(bVar, "Disposable item is null");
        if (this.f3167d) {
            return false;
        }
        synchronized (this) {
            if (this.f3167d) {
                return false;
            }
            d.a.p.h.b<b> bVar2 = this.f3166c;
            if (bVar2 != null && bVar2.e(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // d.a.p.a.a
    public boolean b(b bVar) {
        if (!a(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    @Override // d.a.p.a.a
    public boolean c(b bVar) {
        d.a.p.b.b.d(bVar, "d is null");
        if (!this.f3167d) {
            synchronized (this) {
                if (!this.f3167d) {
                    d.a.p.h.b<b> bVar2 = this.f3166c;
                    if (bVar2 == null) {
                        bVar2 = new d.a.p.h.b<>();
                        this.f3166c = bVar2;
                    }
                    bVar2.a(bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    public void d(d.a.p.h.b<b> bVar) {
        if (bVar == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Object obj : bVar.b()) {
            if (obj instanceof b) {
                try {
                    ((b) obj).dispose();
                } catch (Throwable th) {
                    d.a.n.a.a(th);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
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
        if (this.f3167d) {
            return;
        }
        synchronized (this) {
            if (this.f3167d) {
                return;
            }
            this.f3167d = true;
            d.a.p.h.b<b> bVar = this.f3166c;
            this.f3166c = null;
            d(bVar);
        }
    }

    @Override // d.a.m.b
    public boolean isDisposed() {
        return this.f3167d;
    }
}
