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
final class FlowableConcatMapCompletable$ConcatMapCompletableObserver<T> extends ConcatMapXMainSubscriber<T> implements Disposable {
    private static final long serialVersionUID = 3610901111000061034L;
    volatile boolean active;
    int consumed;
    final CompletableObserver downstream;
    final ConcatMapInnerObserver inner;
    final o mapper;

    public static final class ConcatMapInnerObserver extends AtomicReference<Disposable> implements CompletableObserver {
        private static final long serialVersionUID = 5638352172918776687L;
        final FlowableConcatMapCompletable$ConcatMapCompletableObserver<?> parent;

        public ConcatMapInnerObserver(FlowableConcatMapCompletable$ConcatMapCompletableObserver flowableConcatMapCompletable$ConcatMapCompletableObserver) {
            this.parent = flowableConcatMapCompletable$ConcatMapCompletableObserver;
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onComplete() {
            FlowableConcatMapCompletable$ConcatMapCompletableObserver<?> flowableConcatMapCompletable$ConcatMapCompletableObserver = this.parent;
            flowableConcatMapCompletable$ConcatMapCompletableObserver.active = false;
            flowableConcatMapCompletable$ConcatMapCompletableObserver.f();
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onError(Throwable th) {
            FlowableConcatMapCompletable$ConcatMapCompletableObserver<?> flowableConcatMapCompletable$ConcatMapCompletableObserver = this.parent;
            if (flowableConcatMapCompletable$ConcatMapCompletableObserver.errors.a(th)) {
                if (flowableConcatMapCompletable$ConcatMapCompletableObserver.errorMode != ErrorMode.c) {
                    flowableConcatMapCompletable$ConcatMapCompletableObserver.active = false;
                    flowableConcatMapCompletable$ConcatMapCompletableObserver.f();
                    return;
                }
                flowableConcatMapCompletable$ConcatMapCompletableObserver.upstream.cancel();
                flowableConcatMapCompletable$ConcatMapCompletableObserver.errors.c(flowableConcatMapCompletable$ConcatMapCompletableObserver.downstream);
                if (flowableConcatMapCompletable$ConcatMapCompletableObserver.getAndIncrement() == 0) {
                    flowableConcatMapCompletable$ConcatMapCompletableObserver.queue.clear();
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.c(this, disposable);
        }
    }

    public FlowableConcatMapCompletable$ConcatMapCompletableObserver(CompletableObserver completableObserver, o oVar, ErrorMode errorMode, int i) {
        super(i, errorMode);
        this.downstream = completableObserver;
        this.mapper = oVar;
        this.inner = new ConcatMapInnerObserver(this);
    }

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
    public final void c() {
        ConcatMapInnerObserver concatMapInnerObserver = this.inner;
        concatMapInnerObserver.getClass();
        DisposableHelper.a(concatMapInnerObserver);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        h();
    }

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
    public final void f() {
        if (getAndIncrement() != 0) {
            return;
        }
        ErrorMode errorMode = this.errorMode;
        io.reactivex.rxjava3.operators.g gVar = this.queue;
        AtomicThrowable atomicThrowable = this.errors;
        boolean z = this.syncFused;
        while (!this.cancelled) {
            if (atomicThrowable.get() != null && (errorMode == ErrorMode.c || (errorMode == ErrorMode.e && !this.active))) {
                gVar.clear();
                atomicThrowable.c(this.downstream);
                return;
            }
            if (!this.active) {
                boolean z2 = this.done;
                try {
                    Object objPoll = gVar.poll();
                    boolean z3 = objPoll == null;
                    if (z2 && z3) {
                        atomicThrowable.c(this.downstream);
                        return;
                    }
                    if (!z3) {
                        int i = this.prefetch;
                        int i2 = i - (i >> 1);
                        if (!z) {
                            int i3 = this.consumed + 1;
                            if (i3 == i2) {
                                this.consumed = 0;
                                this.upstream.a(i2);
                            } else {
                                this.consumed = i3;
                            }
                        }
                        try {
                            Object objApply = this.mapper.apply(objPoll);
                            Objects.requireNonNull(objApply, "The mapper returned a null CompletableSource");
                            CompletableSource completableSource = (CompletableSource) objApply;
                            this.active = true;
                            completableSource.subscribe(this.inner);
                        } catch (Throwable th) {
                            he1.R(th);
                            gVar.clear();
                            this.upstream.cancel();
                            atomicThrowable.a(th);
                            atomicThrowable.c(this.downstream);
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    he1.R(th2);
                    this.upstream.cancel();
                    atomicThrowable.a(th2);
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

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
    public final void g() {
        this.downstream.onSubscribe(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }
}
