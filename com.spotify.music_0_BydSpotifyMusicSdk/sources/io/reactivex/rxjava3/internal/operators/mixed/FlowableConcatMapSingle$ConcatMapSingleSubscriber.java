package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.ci1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
final class FlowableConcatMapSingle$ConcatMapSingleSubscriber<T, R> extends ConcatMapXMainSubscriber<T> implements tg2 {
    private static final long serialVersionUID = -9140123220065488293L;
    int consumed;
    final rg2 downstream;
    long emitted;
    final ConcatMapSingleObserver<R> inner;
    R item;
    final o mapper;
    final AtomicLong requested;
    volatile int state;

    public static final class ConcatMapSingleObserver<R> extends AtomicReference<Disposable> implements SingleObserver<R> {
        private static final long serialVersionUID = -3051469169682093892L;
        final FlowableConcatMapSingle$ConcatMapSingleSubscriber<?, R> parent;

        public ConcatMapSingleObserver(FlowableConcatMapSingle$ConcatMapSingleSubscriber flowableConcatMapSingle$ConcatMapSingleSubscriber) {
            this.parent = flowableConcatMapSingle$ConcatMapSingleSubscriber;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onError(Throwable th) {
            FlowableConcatMapSingle$ConcatMapSingleSubscriber<?, R> flowableConcatMapSingle$ConcatMapSingleSubscriber = this.parent;
            if (flowableConcatMapSingle$ConcatMapSingleSubscriber.errors.a(th)) {
                if (flowableConcatMapSingle$ConcatMapSingleSubscriber.errorMode != ErrorMode.k) {
                    flowableConcatMapSingle$ConcatMapSingleSubscriber.upstream.cancel();
                }
                flowableConcatMapSingle$ConcatMapSingleSubscriber.state = 0;
                flowableConcatMapSingle$ConcatMapSingleSubscriber.f();
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onSubscribe(Disposable disposable) {
            DisposableHelper.c(this, disposable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.core.SingleObserver
        public final void onSuccess(Object obj) {
            FlowableConcatMapSingle$ConcatMapSingleSubscriber<?, R> flowableConcatMapSingle$ConcatMapSingleSubscriber = this.parent;
            flowableConcatMapSingle$ConcatMapSingleSubscriber.item = obj;
            flowableConcatMapSingle$ConcatMapSingleSubscriber.state = 2;
            flowableConcatMapSingle$ConcatMapSingleSubscriber.f();
        }
    }

    public FlowableConcatMapSingle$ConcatMapSingleSubscriber(rg2 rg2Var, o oVar, int i, ErrorMode errorMode) {
        super(i, errorMode);
        this.downstream = rg2Var;
        this.mapper = oVar;
        this.requested = new AtomicLong();
        this.inner = new ConcatMapSingleObserver<>(this);
    }

    @Override // p.tg2
    public final void a(long j) {
        ci1.d(this.requested, j);
        f();
    }

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
    public final void b() {
        this.item = null;
    }

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
    public final void c() {
        ConcatMapSingleObserver<R> concatMapSingleObserver = this.inner;
        concatMapSingleObserver.getClass();
        DisposableHelper.a(concatMapSingleObserver);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
    
        r3.clear();
        r17.item = null;
        r4.e(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0042, code lost:
    
        return;
     */
    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f() {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.mixed.FlowableConcatMapSingle$ConcatMapSingleSubscriber.f():void");
    }

    @Override // io.reactivex.rxjava3.internal.operators.mixed.ConcatMapXMainSubscriber
    public final void g() {
        this.downstream.onSubscribe(this);
    }
}
