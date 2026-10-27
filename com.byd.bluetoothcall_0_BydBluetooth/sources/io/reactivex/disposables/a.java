package io.reactivex.disposables;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.ArrayList;

/* JADX INFO: compiled from: CompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements b, io.reactivex.internal.disposables.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    io.reactivex.internal.util.b<b> f4259b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f4260c;

    @Override // io.reactivex.internal.disposables.a
    public boolean a(b bVar) {
        d.a.n.a.b.d(bVar, "Disposable item is null");
        if (this.f4260c) {
            return false;
        }
        synchronized (this) {
            if (this.f4260c) {
                return false;
            }
            io.reactivex.internal.util.b<b> bVar2 = this.f4259b;
            if (bVar2 != null && bVar2.e(bVar)) {
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
        d.a.n.a.b.d(bVar, "d is null");
        if (!this.f4260c) {
            synchronized (this) {
                if (!this.f4260c) {
                    io.reactivex.internal.util.b<b> bVar2 = this.f4259b;
                    if (bVar2 == null) {
                        bVar2 = new io.reactivex.internal.util.b<>();
                        this.f4259b = bVar2;
                    }
                    bVar2.a(bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    void d(io.reactivex.internal.util.b<b> bVar) {
        if (bVar == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Object obj : bVar.b()) {
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
    public void dispose() {
        if (this.f4260c) {
            return;
        }
        synchronized (this) {
            if (this.f4260c) {
                return;
            }
            this.f4260c = true;
            io.reactivex.internal.util.b<b> bVar = this.f4259b;
            this.f4259b = null;
            d(bVar);
        }
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.f4260c;
    }
}
