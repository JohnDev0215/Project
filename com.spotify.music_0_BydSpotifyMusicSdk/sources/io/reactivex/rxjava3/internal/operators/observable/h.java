package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class h implements Iterator {
    public final i c;
    public final ObservableSource e;
    public Object k;
    public boolean l = true;
    public boolean m = true;
    public Throwable n;
    public boolean o;

    public h(ObservableSource observableSource, i iVar) {
        this.e = observableSource;
        this.c = iVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        Throwable th = this.n;
        if (th != null) {
            throw io.reactivex.rxjava3.internal.util.c.f(th);
        }
        if (!this.l) {
            return false;
        }
        if (this.m) {
            boolean z = this.o;
            i iVar = this.c;
            if (!z) {
                this.o = true;
                iVar.k.set(1);
                new o0(this.e, 4).subscribe(iVar);
            }
            try {
                iVar.k.set(1);
                Notification notification = (Notification) iVar.e.take();
                boolean zC = notification.c();
                Object obj = notification.a;
                if (!zC) {
                    this.l = false;
                    if (obj == null) {
                        return false;
                    }
                    Throwable thB = notification.b();
                    this.n = thB;
                    throw io.reactivex.rxjava3.internal.util.c.f(thB);
                }
                this.m = false;
                if (obj == null || NotificationLite.i(obj)) {
                    obj = null;
                }
                this.k = obj;
            } catch (InterruptedException e) {
                iVar.dispose();
                this.n = e;
                throw io.reactivex.rxjava3.internal.util.c.f(e);
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Throwable th = this.n;
        if (th != null) {
            throw io.reactivex.rxjava3.internal.util.c.f(th);
        }
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        this.m = true;
        return this.k;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Read only iterator");
    }
}
