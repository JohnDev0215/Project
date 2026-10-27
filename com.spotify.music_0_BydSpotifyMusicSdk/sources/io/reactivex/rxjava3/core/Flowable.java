package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.operators.flowable.b;
import io.reactivex.rxjava3.internal.operators.flowable.e;
import io.reactivex.rxjava3.internal.operators.flowable.i0;
import io.reactivex.rxjava3.internal.operators.flowable.l;
import io.reactivex.rxjava3.internal.operators.flowable.o0;
import io.reactivex.rxjava3.internal.operators.flowable.p;
import io.reactivex.rxjava3.internal.operators.flowable.p0;
import io.reactivex.rxjava3.internal.operators.flowable.q;
import io.reactivex.rxjava3.internal.operators.flowable.s;
import io.reactivex.rxjava3.internal.operators.flowable.v;
import io.reactivex.rxjava3.internal.operators.flowable.x;
import io.reactivex.rxjava3.internal.operators.mixed.c;
import io.reactivex.rxjava3.internal.operators.observable.r0;
import io.reactivex.rxjava3.internal.subscribers.DisposableAutoReleaseSubscriber;
import io.reactivex.rxjava3.internal.subscribers.LambdaSubscriber;
import io.reactivex.rxjava3.internal.subscribers.StrictSubscriber;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.az1;
import p.he1;
import p.rg2;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public abstract class Flowable<T> implements az1 {
    public static final int c = Math.max(1, Integer.getInteger("rx3.buffer-size", 128).intValue());

    public static Flowable a(Flowable flowable, Flowable flowable2) {
        Objects.requireNonNull(flowable, "source1 is null");
        Objects.requireNonNull(flowable2, "source2 is null");
        return new b(new az1[]{flowable, flowable2});
    }

    public static Flowable h(Object... objArr) {
        Objects.requireNonNull(objArr, "items is null");
        if (objArr.length == 0) {
            return l.e;
        }
        int i = 0;
        if (objArr.length != 1) {
            return new s(i, objArr);
        }
        Object obj = objArr[0];
        Objects.requireNonNull(obj, "item is null");
        return new x(obj);
    }

    public static v i(Iterable iterable) {
        Objects.requireNonNull(iterable, "source is null");
        return new v(iterable);
    }

    public static Flowable j(az1 az1Var) {
        if (az1Var instanceof Flowable) {
            return (Flowable) az1Var;
        }
        Objects.requireNonNull(az1Var, "publisher is null");
        return new s(3, az1Var);
    }

    public final e b(o oVar, int i, int i2) {
        k.c(i, "maxConcurrency");
        k.c(i2, "prefetch");
        return new e(this, oVar, i, i2, ErrorMode.c);
    }

    public final e c(o oVar, boolean z, int i, int i2) {
        k.c(i, "maxConcurrency");
        k.c(i2, "prefetch");
        return new e(this, oVar, i, i2, z ? ErrorMode.k : ErrorMode.e);
    }

    public final c e(boolean z, int i) {
        k.c(i, "prefetch");
        return new c(this, z ? ErrorMode.k : ErrorMode.e, i, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Flowable f(o oVar) {
        int i = c;
        k.c(i, "maxConcurrency");
        k.c(i, "bufferSize");
        if (!(this instanceof io.reactivex.rxjava3.operators.e)) {
            return new p(this, oVar, i, i);
        }
        Object obj = ((io.reactivex.rxjava3.operators.e) this).get();
        return obj == null ? l.e : new p0(oVar, obj);
    }

    public final q g(boolean z, int i) {
        k.c(i, "maxConcurrency");
        return new q(this, z, i);
    }

    public final Flowable k(long j) {
        if (j >= 0) {
            return j == 0 ? l.e : new i0(this, j, 0);
        }
        throw new IllegalArgumentException(u4.g("times >= 0 required but it was ", j));
    }

    public final o0 l(long j, io.reactivex.rxjava3.functions.p pVar) {
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("times >= 0 required but it was ", j));
        }
        Objects.requireNonNull(pVar, "predicate is null");
        return new o0(this, j, pVar);
    }

    public abstract void m(rg2 rg2Var);

    public final Observable n() {
        return new r0(4, this);
    }

    public final Disposable subscribe() {
        return subscribe(k.d, k.f, k.c);
    }

    public final Disposable subscribe(g gVar) {
        return subscribe(gVar, k.f, k.c);
    }

    public final Disposable subscribe(g gVar, g gVar2) {
        return subscribe(gVar, gVar2, k.c);
    }

    public final Disposable subscribe(g gVar, g gVar2, io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(gVar, "onNext is null");
        Objects.requireNonNull(gVar2, "onError is null");
        Objects.requireNonNull(aVar, "onComplete is null");
        LambdaSubscriber lambdaSubscriber = new LambdaSubscriber(gVar, gVar2, aVar);
        subscribe((FlowableSubscriber) lambdaSubscriber);
        return lambdaSubscriber;
    }

    public final Disposable subscribe(g gVar, g gVar2, io.reactivex.rxjava3.functions.a aVar, io.reactivex.rxjava3.disposables.b bVar) {
        Objects.requireNonNull(gVar, "onNext is null");
        Objects.requireNonNull(gVar2, "onError is null");
        Objects.requireNonNull(aVar, "onComplete is null");
        Objects.requireNonNull(bVar, "container is null");
        DisposableAutoReleaseSubscriber disposableAutoReleaseSubscriber = new DisposableAutoReleaseSubscriber(gVar, gVar2, aVar, bVar);
        bVar.c(disposableAutoReleaseSubscriber);
        subscribe((FlowableSubscriber) disposableAutoReleaseSubscriber);
        return disposableAutoReleaseSubscriber;
    }

    @Override // p.az1
    public final void subscribe(rg2 rg2Var) {
        if (rg2Var instanceof FlowableSubscriber) {
            subscribe((FlowableSubscriber) rg2Var);
        } else {
            Objects.requireNonNull(rg2Var, "subscriber is null");
            subscribe((FlowableSubscriber) new StrictSubscriber(rg2Var));
        }
    }

    public final void subscribe(FlowableSubscriber<? super T> flowableSubscriber) {
        Objects.requireNonNull(flowableSubscriber, "subscriber is null");
        try {
            io.reactivex.rxjava3.functions.c cVar = RxJavaPlugins.b;
            if (cVar != null) {
                try {
                    flowableSubscriber = (FlowableSubscriber<? super T>) ((rg2) cVar.k(this, flowableSubscriber));
                } catch (Throwable th) {
                    throw io.reactivex.rxjava3.internal.util.c.f(th);
                }
            }
            Objects.requireNonNull(flowableSubscriber, "The RxJavaPlugins.onSubscribe hook returned a null FlowableSubscriber. Please check the handler provided to RxJavaPlugins.setOnFlowableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            m(flowableSubscriber);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th2) {
            he1.R(th2);
            RxJavaPlugins.b(th2);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th2);
            throw nullPointerException;
        }
    }
}
