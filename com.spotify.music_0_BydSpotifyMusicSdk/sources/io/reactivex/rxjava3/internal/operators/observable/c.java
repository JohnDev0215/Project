package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class c extends io.reactivex.rxjava3.observers.a implements Iterator {
    public Notification e;
    public final Semaphore k = new Semaphore(0);
    public final AtomicReference l = new AtomicReference();

    @Override // java.util.Iterator
    public final boolean hasNext() {
        Notification notification = this.e;
        if (notification != null && NotificationLite.i(notification.a)) {
            throw io.reactivex.rxjava3.internal.util.c.f(this.e.b());
        }
        if (this.e == null) {
            try {
                this.k.acquire();
                Notification notification2 = (Notification) this.l.getAndSet(null);
                this.e = notification2;
                if (NotificationLite.i(notification2.a)) {
                    throw io.reactivex.rxjava3.internal.util.c.f(notification2.b());
                }
            } catch (InterruptedException e) {
                dispose();
                this.e = Notification.a(e);
                throw io.reactivex.rxjava3.internal.util.c.f(e);
            }
        }
        return this.e.c();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object obj = this.e.a;
        if (obj == null || NotificationLite.i(obj)) {
            obj = null;
        }
        this.e = null;
        return obj;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        RxJavaPlugins.b(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.l.getAndSet((Notification) obj) == null) {
            this.k.release();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Read-only iterator.");
    }
}
