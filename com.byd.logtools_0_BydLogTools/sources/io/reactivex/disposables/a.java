package io.reactivex.disposables;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.ArrayList;

/* JADX INFO: compiled from: CompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements b, io.reactivex.internal.disposables.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    io.reactivex.internal.util.a<b> f4983b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f4984c;

    @Override // io.reactivex.internal.disposables.a
    public boolean a(b bVar) {
        e.a.n.a.b.c(bVar, "disposables is null");
        if (this.f4984c) {
            return false;
        }
        synchronized (this) {
            if (this.f4984c) {
                return false;
            }
            io.reactivex.internal.util.a<b> aVar = this.f4983b;
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
        e.a.n.a.b.c(bVar, "disposable is null");
        if (!this.f4984c) {
            synchronized (this) {
                if (!this.f4984c) {
                    io.reactivex.internal.util.a<b> aVar = this.f4983b;
                    if (aVar == null) {
                        aVar = new io.reactivex.internal.util.a<>();
                        this.f4983b = aVar;
                    }
                    aVar.a(bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    void d(io.reactivex.internal.util.a<b> aVar) {
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
    public void dispose() {
        if (this.f4984c) {
            return;
        }
        synchronized (this) {
            if (this.f4984c) {
                return;
            }
            this.f4984c = true;
            io.reactivex.internal.util.a<b> aVar = this.f4983b;
            this.f4983b = null;
            d(aVar);
        }
    }

    public boolean e() {
        return this.f4984c;
    }
}
