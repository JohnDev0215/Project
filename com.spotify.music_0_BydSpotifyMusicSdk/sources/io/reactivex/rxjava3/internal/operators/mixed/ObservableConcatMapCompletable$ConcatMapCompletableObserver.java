package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableConcatMapCompletable$ConcatMapCompletableObserver<T> extends ConcatMapXMainObserver<T> {
    private static final long serialVersionUID = 3610901111000061034L;
    volatile boolean active;
    final CompletableObserver downstream;
    final ConcatMapInnerObserver inner;
    final o mapper;

    public static final class ConcatMapInnerObserver extends AtomicReference<Disposable> implements CompletableObserver {
        private static final long serialVersionUID = 5638352172918776687L;
        final ObservableConcatMapCompletable$ConcatMapCompletableObserver<?> parent;

        public ConcatMapInnerObserver(ObservableConcatMapCompletable$ConcatMapCompletableObserver observableConcatMapCompletable$ConcatMapCompletableObserver) {
            this.parent = observableConcatMapCompletable$ConcatMapCompletableObserver;
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onComplete() {
            ObservableConcatMapCompletable$ConcatMapCompletableObserver<?> observableConcatMapCompletable$ConcatMapCompletableObserver = this.parent;
            observableConcatMapCompletable$ConcatMapCompletableObserver.active = false;
            observableConcatMapCompletable$ConcatMapCompletableObserver.c();
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onError(Throwable th) {
            ObservableConcatMapCompletable$ConcatMapCompletableObserver<?> observableConcatMapCompletable$ConcatMapCompletableObserver = this.parent;
            if (observableConcatMapCompletable$ConcatMapCompletableObserver.errors.a(th)) {
                if (observableConcatMapCompletable$ConcatMapCompletableObserver.errorMode != ErrorMode.k) {
                    observableConcatMapCompletable$ConcatMapCompletableObserver.upstream.dispose();
                }
                observableConcatMapCompletable$ConcatMapCompletableObserver.active = false;
                observableConcatMapCompletable$ConcatMapCompletableObserver.c();
            }
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.c(this, disposable);
        }
    }

    public ObservableConcatMapCompletable$ConcatMapCompletableObserver(CompletableObserver completableObserver, o oVar, ErrorMode errorMode, int i) {
        super(i, errorMode);
        this.downstream = completableObserver;
        this.mapper = oVar;
        this.inner = new ConcatMapInnerObserver(this);
    }

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
    public final void b() {
        ConcatMapInnerObserver concatMapInnerObserver = this.inner;
        concatMapInnerObserver.getClass();
        DisposableHelper.a(concatMapInnerObserver);
    }

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
    public final void c() {
        CompletableSource completableSource;
        boolean z;
        if (getAndIncrement() != 0) {
            return;
        }
        AtomicThrowable atomicThrowable = this.errors;
        ErrorMode errorMode = this.errorMode;
        io.reactivex.rxjava3.operators.g gVar = this.queue;
        while (!this.disposed) {
            if (atomicThrowable.get() != null && (errorMode == ErrorMode.c || (errorMode == ErrorMode.e && !this.active))) {
                this.disposed = true;
                gVar.clear();
                atomicThrowable.c(this.downstream);
                return;
            }
            if (!this.active) {
                boolean z2 = this.done;
                try {
                    Object objPoll = gVar.poll();
                    if (objPoll != null) {
                        Object objApply = this.mapper.apply(objPoll);
                        Objects.requireNonNull(objApply, "The mapper returned a null CompletableSource");
                        completableSource = (CompletableSource) objApply;
                        z = false;
                    } else {
                        completableSource = null;
                        z = true;
                    }
                    if (z2 && z) {
                        this.disposed = true;
                        atomicThrowable.c(this.downstream);
                        return;
                    } else if (!z) {
                        this.active = true;
                        completableSource.subscribe(this.inner);
                    }
                } catch (Throwable th) {
                    he1.R(th);
                    this.disposed = true;
                    gVar.clear();
                    this.upstream.dispose();
                    atomicThrowable.a(th);
                    atomicThrowable.c(this.downstream);
                    return;
                }
            }
            if (decrementAndGet() == 0) {
                return;
            }
        }
        gVar.clear();
    }

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
    public final void d() {
        this.downstream.onSubscribe(this);
    }
}
