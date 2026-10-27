package io.reactivex.disposables;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.ArrayList;

/* JADX INFO: compiled from: CompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements b, io.reactivex.internal.disposables.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    io.reactivex.internal.util.a<b> f4547b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f4548c;

    @Override // io.reactivex.internal.disposables.a
    public boolean a(b bVar) {
        d.a.n.a.b.c(bVar, "Disposable item is null");
        if (this.f4548c) {
            return false;
        }
        synchronized (this) {
            if (this.f4548c) {
                return false;
            }
            io.reactivex.internal.util.a<b> aVar = this.f4547b;
            if (aVar != null && aVar.e(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // io.reactivex.internal.disposables.a
    public boolean b(b bVar) {
        if (!a(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    @Override // io.reactivex.internal.disposables.a
    public boolean c(b bVar) {
        d.a.n.a.b.c(bVar, "d is null");
        if (!this.f4548c) {
            synchronized (this) {
                if (!this.f4548c) {
                    io.reactivex.internal.util.a<b> aVar = this.f4547b;
                    if (aVar == null) {
                        aVar = new io.reactivex.internal.util.a<>();
                        this.f4547b = aVar;
                    }
                    aVar.a(bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    public void d() {
        if (this.f4548c) {
            return;
        }
        synchronized (this) {
            if (this.f4548c) {
                return;
            }
            io.reactivex.internal.util.a<b> aVar = this.f4547b;
            this.f4547b = null;
            e(aVar);
        }
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        if (this.f4548c) {
            return;
        }
        synchronized (this) {
            if (this.f4548c) {
                return;
            }
            this.f4548c = true;
            io.reactivex.internal.util.a<b> aVar = this.f4547b;
            this.f4547b = null;
            e(aVar);
        }
    }

    void e(io.reactivex.internal.util.a<b> aVar) {
        if (aVar == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Object obj : aVar.b()) {
            if (obj instanceof b) {
                try {
                    ((b) obj).dispose();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
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

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.f4548c;
    }
}
