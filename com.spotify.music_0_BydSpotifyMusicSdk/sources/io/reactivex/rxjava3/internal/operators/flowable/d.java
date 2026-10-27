package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import p.az1;
import p.du;
import p.he1;
import p.rg2;
import p.ux2;

/* JADX INFO: loaded from: classes.dex */
public final class d extends a {
    public final io.reactivex.rxjava3.functions.o k;
    public final int l;
    public final ErrorMode m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(z zVar, du duVar) {
        super(zVar);
        ErrorMode errorMode = ErrorMode.c;
        this.k = duVar;
        this.l = 2;
        this.m = errorMode;
    }

    public static <T, R> rg2 subscribe(final rg2 rg2Var, final io.reactivex.rxjava3.functions.o oVar, final int i, ErrorMode errorMode) {
        int iOrdinal = errorMode.ordinal();
        final boolean z = true;
        if (iOrdinal != 1) {
            return iOrdinal != 2 ? new FlowableConcatMap$BaseConcatMapSubscriber<T, R>(rg2Var, oVar, i) { // from class: io.reactivex.rxjava3.internal.operators.flowable.FlowableConcatMap$ConcatMapImmediate
                private static final long serialVersionUID = 7898995095634264146L;
                final rg2 downstream;
                final AtomicInteger wip;

                {
                    super(oVar, i);
                    this.downstream = rg2Var;
                    this.wip = new AtomicInteger();
                }

                @Override // p.tg2
                public final void a(long j) {
                    this.inner.a(j);
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.c
                public final void b(Throwable th) {
                    this.upstream.cancel();
                    rg2 rg2Var2 = this.downstream;
                    AtomicThrowable atomicThrowable = this.errors;
                    if (atomicThrowable.a(th) && getAndIncrement() == 0) {
                        atomicThrowable.e(rg2Var2);
                    }
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.c
                public final void c(Object obj) {
                    ux2.w(this.downstream, obj, this, this.errors);
                }

                @Override // p.tg2
                public final void cancel() {
                    if (this.cancelled) {
                        return;
                    }
                    this.cancelled = true;
                    this.inner.cancel();
                    this.upstream.cancel();
                    this.errors.b();
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableConcatMap$BaseConcatMapSubscriber
                public final void e() {
                    if (this.wip.getAndIncrement() == 0) {
                        while (!this.cancelled) {
                            if (!this.active) {
                                boolean z2 = this.done;
                                try {
                                    Object objPoll = this.queue.poll();
                                    boolean z3 = objPoll == null;
                                    if (z2 && z3) {
                                        this.downstream.onComplete();
                                        return;
                                    }
                                    if (!z3) {
                                        try {
                                            Object objApply = this.mapper.apply(objPoll);
                                            Objects.requireNonNull(objApply, "The mapper returned a null Publisher");
                                            az1 az1Var = (az1) objApply;
                                            if (this.sourceMode != 1) {
                                                int i2 = this.consumed + 1;
                                                if (i2 == this.limit) {
                                                    this.consumed = 0;
                                                    this.upstream.a(i2);
                                                } else {
                                                    this.consumed = i2;
                                                }
                                            }
                                            if (az1Var instanceof io.reactivex.rxjava3.functions.q) {
                                                try {
                                                    Object obj = ((io.reactivex.rxjava3.functions.q) az1Var).get();
                                                    if (obj == null) {
                                                        continue;
                                                    } else if (!this.inner.e()) {
                                                        this.active = true;
                                                        this.inner.g(new FlowableConcatMap$SimpleScalarSubscription(obj, this.inner));
                                                    } else if (!ux2.w(this.downstream, obj, this, this.errors)) {
                                                        return;
                                                    }
                                                } catch (Throwable th) {
                                                    he1.R(th);
                                                    this.upstream.cancel();
                                                    this.errors.a(th);
                                                    this.errors.e(this.downstream);
                                                    return;
                                                }
                                            } else {
                                                this.active = true;
                                                az1Var.subscribe(this.inner);
                                            }
                                        } catch (Throwable th2) {
                                            he1.R(th2);
                                            this.upstream.cancel();
                                            this.errors.a(th2);
                                            this.errors.e(this.downstream);
                                            return;
                                        }
                                    }
                                } catch (Throwable th3) {
                                    he1.R(th3);
                                    this.upstream.cancel();
                                    this.errors.a(th3);
                                    this.errors.e(this.downstream);
                                    return;
                                }
                            }
                            if (this.wip.decrementAndGet() == 0) {
                                return;
                            }
                        }
                    }
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableConcatMap$BaseConcatMapSubscriber
                public final void f() {
                    this.downstream.onSubscribe(this);
                }

                @Override // p.rg2
                public final void onError(Throwable th) {
                    this.inner.cancel();
                    rg2 rg2Var2 = this.downstream;
                    AtomicThrowable atomicThrowable = this.errors;
                    if (atomicThrowable.a(th) && getAndIncrement() == 0) {
                        atomicThrowable.e(rg2Var2);
                    }
                }
            } : new FlowableConcatMap$BaseConcatMapSubscriber<T, R>(i, oVar, rg2Var, z) { // from class: io.reactivex.rxjava3.internal.operators.flowable.FlowableConcatMap$ConcatMapDelayed
                private static final long serialVersionUID = -2945777694260521066L;
                final rg2 downstream;
                final boolean veryEnd;

                {
                    this.downstream = rg2Var;
                    this.veryEnd = z;
                }

                @Override // p.tg2
                public final void a(long j) {
                    this.inner.a(j);
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.c
                public final void b(Throwable th) {
                    if (this.errors.a(th)) {
                        if (!this.veryEnd) {
                            this.upstream.cancel();
                            this.done = true;
                        }
                        this.active = false;
                        e();
                    }
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.c
                public final void c(Object obj) {
                    this.downstream.onNext(obj);
                }

                @Override // p.tg2
                public final void cancel() {
                    if (this.cancelled) {
                        return;
                    }
                    this.cancelled = true;
                    this.inner.cancel();
                    this.upstream.cancel();
                    this.errors.b();
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableConcatMap$BaseConcatMapSubscriber
                public final void e() {
                    Object obj;
                    if (getAndIncrement() == 0) {
                        while (!this.cancelled) {
                            if (!this.active) {
                                boolean z2 = this.done;
                                if (z2 && !this.veryEnd && this.errors.get() != null) {
                                    this.errors.e(this.downstream);
                                    return;
                                }
                                try {
                                    Object objPoll = this.queue.poll();
                                    boolean z3 = objPoll == null;
                                    if (z2 && z3) {
                                        this.errors.e(this.downstream);
                                        return;
                                    }
                                    if (!z3) {
                                        try {
                                            Object objApply = this.mapper.apply(objPoll);
                                            Objects.requireNonNull(objApply, "The mapper returned a null Publisher");
                                            az1 az1Var = (az1) objApply;
                                            if (this.sourceMode != 1) {
                                                int i2 = this.consumed + 1;
                                                if (i2 == this.limit) {
                                                    this.consumed = 0;
                                                    this.upstream.a(i2);
                                                } else {
                                                    this.consumed = i2;
                                                }
                                            }
                                            if (az1Var instanceof io.reactivex.rxjava3.functions.q) {
                                                try {
                                                    obj = ((io.reactivex.rxjava3.functions.q) az1Var).get();
                                                } catch (Throwable th) {
                                                    he1.R(th);
                                                    this.errors.a(th);
                                                    if (!this.veryEnd) {
                                                        this.upstream.cancel();
                                                        this.errors.e(this.downstream);
                                                        return;
                                                    }
                                                    obj = null;
                                                }
                                                if (obj == null) {
                                                    continue;
                                                } else if (this.inner.e()) {
                                                    this.downstream.onNext(obj);
                                                } else {
                                                    this.active = true;
                                                    this.inner.g(new FlowableConcatMap$SimpleScalarSubscription(obj, this.inner));
                                                }
                                            } else {
                                                this.active = true;
                                                az1Var.subscribe(this.inner);
                                            }
                                        } catch (Throwable th2) {
                                            he1.R(th2);
                                            this.upstream.cancel();
                                            this.errors.a(th2);
                                            this.errors.e(this.downstream);
                                            return;
                                        }
                                    }
                                } catch (Throwable th3) {
                                    he1.R(th3);
                                    this.upstream.cancel();
                                    this.errors.a(th3);
                                    this.errors.e(this.downstream);
                                    return;
                                }
                            }
                            if (decrementAndGet() == 0) {
                                return;
                            }
                        }
                    }
                }

                @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableConcatMap$BaseConcatMapSubscriber
                public final void f() {
                    this.downstream.onSubscribe(this);
                }

                @Override // p.rg2
                public final void onError(Throwable th) {
                    if (this.errors.a(th)) {
                        this.done = true;
                        e();
                    }
                }
            };
        }
        final boolean z2 = false;
        return new FlowableConcatMap$BaseConcatMapSubscriber<T, R>(i, oVar, rg2Var, z2) { // from class: io.reactivex.rxjava3.internal.operators.flowable.FlowableConcatMap$ConcatMapDelayed
            private static final long serialVersionUID = -2945777694260521066L;
            final rg2 downstream;
            final boolean veryEnd;

            {
                this.downstream = rg2Var;
                this.veryEnd = z2;
            }

            @Override // p.tg2
            public final void a(long j) {
                this.inner.a(j);
            }

            @Override // io.reactivex.rxjava3.internal.operators.flowable.c
            public final void b(Throwable th) {
                if (this.errors.a(th)) {
                    if (!this.veryEnd) {
                        this.upstream.cancel();
                        this.done = true;
                    }
                    this.active = false;
                    e();
                }
            }

            @Override // io.reactivex.rxjava3.internal.operators.flowable.c
            public final void c(Object obj) {
                this.downstream.onNext(obj);
            }

            @Override // p.tg2
            public final void cancel() {
                if (this.cancelled) {
                    return;
                }
                this.cancelled = true;
                this.inner.cancel();
                this.upstream.cancel();
                this.errors.b();
            }

            @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableConcatMap$BaseConcatMapSubscriber
            public final void e() {
                Object obj;
                if (getAndIncrement() == 0) {
                    while (!this.cancelled) {
                        if (!this.active) {
                            boolean z22 = this.done;
                            if (z22 && !this.veryEnd && this.errors.get() != null) {
                                this.errors.e(this.downstream);
                                return;
                            }
                            try {
                                Object objPoll = this.queue.poll();
                                boolean z3 = objPoll == null;
                                if (z22 && z3) {
                                    this.errors.e(this.downstream);
                                    return;
                                }
                                if (!z3) {
                                    try {
                                        Object objApply = this.mapper.apply(objPoll);
                                        Objects.requireNonNull(objApply, "The mapper returned a null Publisher");
                                        az1 az1Var = (az1) objApply;
                                        if (this.sourceMode != 1) {
                                            int i2 = this.consumed + 1;
                                            if (i2 == this.limit) {
                                                this.consumed = 0;
                                                this.upstream.a(i2);
                                            } else {
                                                this.consumed = i2;
                                            }
                                        }
                                        if (az1Var instanceof io.reactivex.rxjava3.functions.q) {
                                            try {
                                                obj = ((io.reactivex.rxjava3.functions.q) az1Var).get();
                                            } catch (Throwable th) {
                                                he1.R(th);
                                                this.errors.a(th);
                                                if (!this.veryEnd) {
                                                    this.upstream.cancel();
                                                    this.errors.e(this.downstream);
                                                    return;
                                                }
                                                obj = null;
                                            }
                                            if (obj == null) {
                                                continue;
                                            } else if (this.inner.e()) {
                                                this.downstream.onNext(obj);
                                            } else {
                                                this.active = true;
                                                this.inner.g(new FlowableConcatMap$SimpleScalarSubscription(obj, this.inner));
                                            }
                                        } else {
                                            this.active = true;
                                            az1Var.subscribe(this.inner);
                                        }
                                    } catch (Throwable th2) {
                                        he1.R(th2);
                                        this.upstream.cancel();
                                        this.errors.a(th2);
                                        this.errors.e(this.downstream);
                                        return;
                                    }
                                }
                            } catch (Throwable th3) {
                                he1.R(th3);
                                this.upstream.cancel();
                                this.errors.a(th3);
                                this.errors.e(this.downstream);
                                return;
                            }
                        }
                        if (decrementAndGet() == 0) {
                            return;
                        }
                    }
                }
            }

            @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableConcatMap$BaseConcatMapSubscriber
            public final void f() {
                this.downstream.onSubscribe(this);
            }

            @Override // p.rg2
            public final void onError(Throwable th) {
                if (this.errors.a(th)) {
                    this.done = true;
                    e();
                }
            }
        };
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        Flowable flowable = this.e;
        io.reactivex.rxjava3.functions.o oVar = this.k;
        if (ux2.A(flowable, rg2Var, oVar)) {
            return;
        }
        flowable.subscribe(subscribe(rg2Var, oVar, this.l, this.m));
    }
}
