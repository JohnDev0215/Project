package io.reactivex.rxjava3.internal.disposables;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.b;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.util.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Disposable, b {
    public LinkedList c;
    public volatile boolean e;

    @Override // io.reactivex.rxjava3.disposables.b
    public final boolean a(Disposable disposable) {
        if (!b(disposable)) {
            return false;
        }
        disposable.dispose();
        return true;
    }

    @Override // io.reactivex.rxjava3.disposables.b
    public final boolean b(Disposable disposable) {
        Objects.requireNonNull(disposable, "Disposable item is null");
        if (this.e) {
            return false;
        }
        synchronized (this) {
            try {
                if (this.e) {
                    return false;
                }
                LinkedList linkedList = this.c;
                if (linkedList != null && linkedList.remove(disposable)) {
                    return true;
                }
                return false;
            } finally {
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.b
    public final boolean c(Disposable disposable) {
        if (!this.e) {
            synchronized (this) {
                try {
                    if (!this.e) {
                        LinkedList linkedList = this.c;
                        if (linkedList == null) {
                            linkedList = new LinkedList();
                            this.c = linkedList;
                        }
                        linkedList.add(disposable);
                        return true;
                    }
                } finally {
                }
            }
        }
        disposable.dispose();
        return false;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.e) {
            return;
        }
        synchronized (this) {
            try {
                if (this.e) {
                    return;
                }
                this.e = true;
                LinkedList linkedList = this.c;
                ArrayList arrayList = null;
                this.c = null;
                if (linkedList == null) {
                    return;
                }
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    try {
                        ((Disposable) it.next()).dispose();
                    } catch (Throwable th) {
                        he1.R(th);
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
                    throw c.f((Throwable) arrayList.get(0));
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e;
    }
}
