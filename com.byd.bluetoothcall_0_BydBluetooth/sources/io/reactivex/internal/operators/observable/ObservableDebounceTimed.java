package io.reactivex.internal.operators.observable;

import d.a.h;
import d.a.i;
import d.a.j;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableDebounceTimed<T> extends io.reactivex.internal.operators.observable.a<T, T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final long f4272c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final TimeUnit f4273d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final j f4274e;

    static final class DebounceEmitter<T> extends AtomicReference<io.reactivex.disposables.b> implements Runnable, io.reactivex.disposables.b {
        private static final long serialVersionUID = 6812032969491025141L;
        final long idx;
        final AtomicBoolean once = new AtomicBoolean();
        final a<T> parent;
        final T value;

        DebounceEmitter(T t, long j, a<T> aVar) {
            this.value = t;
            this.idx = j;
            this.parent = aVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.a(this.idx, this.value, this);
            }
        }

        public void setResource(io.reactivex.disposables.b bVar) {
            DisposableHelper.replace(this, bVar);
        }
    }

    static final class a<T> implements i<T>, io.reactivex.disposables.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final i<? super T> f4275b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final long f4276c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final TimeUnit f4277d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final j.b f4278e;
        io.reactivex.disposables.b f;
        io.reactivex.disposables.b g;
        volatile long h;
        boolean i;

        a(i<? super T> iVar, long j, TimeUnit timeUnit, j.b bVar) {
            this.f4275b = iVar;
            this.f4276c = j;
            this.f4277d = timeUnit;
            this.f4278e = bVar;
        }

        void a(long j, T t, DebounceEmitter<T> debounceEmitter) {
            if (j == this.h) {
                this.f4275b.onNext(t);
                debounceEmitter.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f.dispose();
            this.f4278e.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4278e.isDisposed();
        }

        @Override // d.a.i
        public void onComplete() {
            if (this.i) {
                return;
            }
            this.i = true;
            io.reactivex.disposables.b bVar = this.g;
            if (bVar != null) {
                bVar.dispose();
            }
            DebounceEmitter debounceEmitter = (DebounceEmitter) bVar;
            if (debounceEmitter != null) {
                debounceEmitter.run();
            }
            this.f4275b.onComplete();
            this.f4278e.dispose();
        }

        @Override // d.a.i
        public void onError(Throwable th) {
            if (this.i) {
                d.a.p.a.l(th);
                return;
            }
            io.reactivex.disposables.b bVar = this.g;
            if (bVar != null) {
                bVar.dispose();
            }
            this.i = true;
            this.f4275b.onError(th);
            this.f4278e.dispose();
        }

        @Override // d.a.i
        public void onNext(T t) {
            if (this.i) {
                return;
            }
            long j = this.h + 1;
            this.h = j;
            io.reactivex.disposables.b bVar = this.g;
            if (bVar != null) {
                bVar.dispose();
            }
            DebounceEmitter debounceEmitter = new DebounceEmitter(t, j, this);
            this.g = debounceEmitter;
            debounceEmitter.setResource(this.f4278e.c(debounceEmitter, this.f4276c, this.f4277d));
        }

        @Override // d.a.i
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.f, bVar)) {
                this.f = bVar;
                this.f4275b.onSubscribe(this);
            }
        }
    }

    public ObservableDebounceTimed(h<T> hVar, long j, TimeUnit timeUnit, j jVar) {
        super(hVar);
        this.f4272c = j;
        this.f4273d = timeUnit;
        this.f4274e = jVar;
    }

    @Override // d.a.e
    public void o(i<? super T> iVar) {
        this.f4292b.a(new a(new d.a.o.a(iVar), this.f4272c, this.f4273d, this.f4274e.a()));
    }
}
