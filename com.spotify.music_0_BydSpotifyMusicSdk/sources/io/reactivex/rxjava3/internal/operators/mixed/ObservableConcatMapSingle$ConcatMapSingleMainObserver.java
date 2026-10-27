package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableConcatMapSingle$ConcatMapSingleMainObserver<T, R> extends ConcatMapXMainObserver<T> {
    private static final long serialVersionUID = -9140123220065488293L;
    final Observer<? super R> downstream;
    final ConcatMapSingleObserver<R> inner;
    R item;
    final o mapper;
    volatile int state;

    public static final class ConcatMapSingleObserver<R> extends AtomicReference<Disposable> implements SingleObserver<R> {
        private static final long serialVersionUID = -3051469169682093892L;
        final ObservableConcatMapSingle$ConcatMapSingleMainObserver<?, R> parent;

        public ConcatMapSingleObserver(ObservableConcatMapSingle$ConcatMapSingleMainObserver observableConcatMapSingle$ConcatMapSingleMainObserver) {
            this.parent = observableConcatMapSingle$ConcatMapSingleMainObserver;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onError(Throwable th) {
            ObservableConcatMapSingle$ConcatMapSingleMainObserver<?, R> observableConcatMapSingle$ConcatMapSingleMainObserver = this.parent;
            if (observableConcatMapSingle$ConcatMapSingleMainObserver.errors.a(th)) {
                if (observableConcatMapSingle$ConcatMapSingleMainObserver.errorMode != ErrorMode.k) {
                    observableConcatMapSingle$ConcatMapSingleMainObserver.upstream.dispose();
                }
                observableConcatMapSingle$ConcatMapSingleMainObserver.state = 0;
                observableConcatMapSingle$ConcatMapSingleMainObserver.c();
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.c(this, disposable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onSuccess(Object obj) {
            ObservableConcatMapSingle$ConcatMapSingleMainObserver<?, R> observableConcatMapSingle$ConcatMapSingleMainObserver = this.parent;
            observableConcatMapSingle$ConcatMapSingleMainObserver.item = obj;
            observableConcatMapSingle$ConcatMapSingleMainObserver.state = 2;
            observableConcatMapSingle$ConcatMapSingleMainObserver.c();
        }
    }

    public ObservableConcatMapSingle$ConcatMapSingleMainObserver(Observer observer, o oVar, int i, ErrorMode errorMode) {
        super(i, errorMode);
        this.downstream = observer;
        this.mapper = oVar;
        this.inner = new ConcatMapSingleObserver<>(this);
    }

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
    public final void a() {
        this.item = null;
    }

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
    public final void b() {
        ConcatMapSingleObserver<R> concatMapSingleObserver = this.inner;
        concatMapSingleObserver.getClass();
        DisposableHelper.a(concatMapSingleObserver);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        r2.clear();
        r10.item = null;
        r3.d(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
    
        return;
     */
    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c() {
        /*
            r10 = this;
            int r0 = r10.getAndIncrement()
            if (r0 == 0) goto L7
            return
        L7:
            io.reactivex.rxjava3.core.Observer<? super R> r0 = r10.downstream
            io.reactivex.rxjava3.internal.util.ErrorMode r1 = r10.errorMode
            io.reactivex.rxjava3.operators.g r2 = r10.queue
            io.reactivex.rxjava3.internal.util.AtomicThrowable r3 = r10.errors
            r4 = 1
            r5 = r4
        L11:
            boolean r6 = r10.disposed
            r7 = 0
            if (r6 == 0) goto L1d
            r2.clear()
            r10.item = r7
            goto L97
        L1d:
            int r6 = r10.state
            java.lang.Object r8 = r3.get()
            if (r8 == 0) goto L38
            io.reactivex.rxjava3.internal.util.ErrorMode r8 = io.reactivex.rxjava3.internal.util.ErrorMode.c
            if (r1 == r8) goto L2f
            io.reactivex.rxjava3.internal.util.ErrorMode r8 = io.reactivex.rxjava3.internal.util.ErrorMode.e
            if (r1 != r8) goto L38
            if (r6 != 0) goto L38
        L2f:
            r2.clear()
            r10.item = r7
            r3.d(r0)
            return
        L38:
            r8 = 0
            if (r6 != 0) goto L89
            boolean r6 = r10.done
            java.lang.Object r7 = r2.poll()     // Catch: java.lang.Throwable -> L77
            if (r7 != 0) goto L44
            r8 = r4
        L44:
            if (r6 == 0) goto L4c
            if (r8 == 0) goto L4c
            r3.d(r0)
            return
        L4c:
            if (r8 == 0) goto L4f
            goto L97
        L4f:
            io.reactivex.rxjava3.functions.o r6 = r10.mapper     // Catch: java.lang.Throwable -> L64
            java.lang.Object r6 = r6.apply(r7)     // Catch: java.lang.Throwable -> L64
            java.lang.String r7 = "The mapper returned a null SingleSource"
            java.util.Objects.requireNonNull(r6, r7)     // Catch: java.lang.Throwable -> L64
            io.reactivex.rxjava3.core.SingleSource r6 = (io.reactivex.rxjava3.core.SingleSource) r6     // Catch: java.lang.Throwable -> L64
            r10.state = r4
            io.reactivex.rxjava3.internal.operators.mixed.ObservableConcatMapSingle$ConcatMapSingleMainObserver$ConcatMapSingleObserver<R> r7 = r10.inner
            r6.subscribe(r7)
            goto L97
        L64:
            r1 = move-exception
            p.he1.R(r1)
            io.reactivex.rxjava3.disposables.Disposable r4 = r10.upstream
            r4.dispose()
            r2.clear()
            r3.a(r1)
            r3.d(r0)
            return
        L77:
            r1 = move-exception
            p.he1.R(r1)
            r10.disposed = r4
            io.reactivex.rxjava3.disposables.Disposable r2 = r10.upstream
            r2.dispose()
            r3.a(r1)
            r3.d(r0)
            return
        L89:
            r9 = 2
            if (r6 != r9) goto L97
            R r6 = r10.item
            r10.item = r7
            r0.onNext(r6)
            r10.state = r8
            goto L11
        L97:
            int r5 = -r5
            int r5 = r10.addAndGet(r5)
            if (r5 != 0) goto L11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.mixed.ObservableConcatMapSingle$ConcatMapSingleMainObserver.c():void");
    }

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainObserver
    public final void d() {
        this.downstream.onSubscribe(this);
    }
}
