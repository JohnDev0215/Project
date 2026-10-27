package io.reactivex.internal.disposables;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: compiled from: ListCompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements io.reactivex.disposables.b, a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    List<io.reactivex.disposables.b> f4989b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    volatile boolean f4990c;

    @Override // io.reactivex.internal.disposables.a
    public boolean a(io.reactivex.disposables.b bVar) {
        e.a.n.a.b.c(bVar, "Disposable item is null");
        if (this.f4990c) {
            return false;
        }
        synchronized (this) {
            if (this.f4990c) {
                return false;
            }
            List<io.reactivex.disposables.b> list = this.f4989b;
            if (list != null && list.remove(bVar)) {
                return true;
            }
            return false;
        }
    }

    @Override // io.reactivex.internal.disposables.a
    public boolean b(io.reactivex.disposables.b bVar) {
        if (!a(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    @Override // io.reactivex.internal.disposables.a
    public boolean c(io.reactivex.disposables.b bVar) {
        e.a.n.a.b.c(bVar, "d is null");
        if (!this.f4990c) {
            synchronized (this) {
                if (!this.f4990c) {
                    List linkedList = this.f4989b;
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                        this.f4989b = linkedList;
                    }
                    linkedList.add(bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    void d(List<io.reactivex.disposables.b> list) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = null;
        Iterator<io.reactivex.disposables.b> it = list.iterator();
        while (it.hasNext()) {
            try {
                it.next().dispose();
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
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

    @Override // io.reactivex.disposables.b
    public void dispose() {
        if (this.f4990c) {
            return;
        }
        synchronized (this) {
            if (this.f4990c) {
                return;
            }
            this.f4990c = true;
            List<io.reactivex.disposables.b> list = this.f4989b;
            this.f4989b = null;
            d(list);
        }
    }
}
