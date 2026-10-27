package io.reactivex.internal.operators.observable;

import d.a.f;
import d.a.g;
import d.a.h;
import d.a.i;
import d.a.m.b;
import d.a.o.d;
import d.a.o.e;
import d.a.t.c;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.observers.ToNotificationObserver;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableRedo<T> extends d.a.p.e.b.a<T, T> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e<? super g<f<Object>>, ? extends h<?>> f5905d;
    public final boolean s;

    public static final class RedoObserver<T> extends AtomicBoolean implements i<T> {
        private static final long serialVersionUID = -1151903143112844287L;
        public final i<? super T> actual;
        public final boolean retryMode;
        public final h<? extends T> source;
        public final c<f<Object>> subject;
        public final AtomicInteger wip = new AtomicInteger();
        public final SequentialDisposable arbiter = new SequentialDisposable();

        public RedoObserver(i<? super T> iVar, c<f<Object>> cVar, h<? extends T> hVar, boolean z) {
            this.actual = iVar;
            this.subject = cVar;
            this.source = hVar;
            this.retryMode = z;
            lazySet(true);
        }

        public void handle(f<Object> fVar) {
            int iAddAndGet = 1;
            if (compareAndSet(true, false)) {
                if (fVar.e()) {
                    this.arbiter.dispose();
                    this.actual.onError(fVar.d());
                    return;
                }
                if (!fVar.f()) {
                    this.arbiter.dispose();
                    this.actual.onComplete();
                } else if (this.wip.getAndIncrement() == 0) {
                    while (!this.arbiter.isDisposed()) {
                        this.source.a(this);
                        iAddAndGet = this.wip.addAndGet(-iAddAndGet);
                        if (iAddAndGet == 0) {
                            return;
                        }
                    }
                }
            }
        }

        @Override // d.a.i
        public void onComplete() {
            if (compareAndSet(false, true)) {
                if (this.retryMode) {
                    this.subject.onComplete();
                } else {
                    this.subject.onNext(f.a());
                }
            }
        }

        @Override // d.a.i
        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                if (this.retryMode) {
                    this.subject.onNext(f.b(th));
                } else {
                    this.subject.onError(th);
                }
            }
        }

        @Override // d.a.i
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // d.a.i
        public void onSubscribe(b bVar) {
            this.arbiter.replace(bVar);
        }
    }

    public class a implements d<f<Object>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RedoObserver f5906a;

        public a(RedoObserver redoObserver) {
            this.f5906a = redoObserver;
        }

        @Override // d.a.o.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(f<Object> fVar) {
            this.f5906a.handle(fVar);
        }
    }

    public ObservableRedo(h<T> hVar, e<? super g<f<Object>>, ? extends h<?>> eVar, boolean z) {
        super(hVar);
        this.f5905d = eVar;
        this.s = z;
    }

    @Override // d.a.g
    public void t(i<? super T> iVar) {
        c<T> cVarX = d.a.t.a.z().x();
        RedoObserver redoObserver = new RedoObserver(iVar, cVarX, this.f3190c, this.s);
        ToNotificationObserver toNotificationObserver = new ToNotificationObserver(new a(redoObserver));
        iVar.onSubscribe(new d.a.p.a.b(redoObserver.arbiter, toNotificationObserver));
        try {
            ((h) d.a.p.b.b.d(this.f5905d.apply(cVarX), "The function returned a null ObservableSource")).a(toNotificationObserver);
            redoObserver.handle(f.c(0));
        } catch (Throwable th) {
            d.a.n.a.a(th);
            iVar.onError(th);
        }
    }
}
