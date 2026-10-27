package io.reactivex.rxjava3.internal.observers;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import p.f60;
import p.mj2;

/* JADX INFO: loaded from: classes.dex */
public abstract class k extends mj2 implements Observer {
    public final Observer e;
    public final io.reactivex.rxjava3.operators.f k;
    public volatile boolean l;
    public volatile boolean m;

    public k(io.reactivex.rxjava3.observers.d dVar, io.reactivex.rxjava3.internal.queue.a aVar) {
        super(5);
        this.e = dVar;
        this.k = aVar;
    }

    public abstract void d(Observer observer, Object obj);

    public final boolean f() {
        return ((AtomicInteger) this.c).getAndIncrement() == 0;
    }

    public final void g(Collection collection, Disposable disposable) {
        int i = ((AtomicInteger) this.c).get();
        Observer observer = this.e;
        io.reactivex.rxjava3.operators.f fVar = this.k;
        if (i == 0 && ((AtomicInteger) this.c).compareAndSet(0, 1)) {
            d(observer, collection);
            if (((AtomicInteger) this.c).addAndGet(-1) == 0) {
                return;
            }
        } else {
            fVar.offer(collection);
            if (!f()) {
                return;
            }
        }
        f60.o(fVar, observer, disposable, this);
    }

    public final void h(Collection collection, Disposable disposable) {
        int i = ((AtomicInteger) this.c).get();
        Observer observer = this.e;
        io.reactivex.rxjava3.operators.f fVar = this.k;
        if (i != 0 || !((AtomicInteger) this.c).compareAndSet(0, 1)) {
            fVar.offer(collection);
            if (!f()) {
                return;
            }
        } else if (fVar.isEmpty()) {
            d(observer, collection);
            if (((AtomicInteger) this.c).addAndGet(-1) == 0) {
                return;
            }
        } else {
            fVar.offer(collection);
        }
        f60.o(fVar, observer, disposable, this);
    }
}
