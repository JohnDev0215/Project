package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import java.util.Objects;
import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: classes3.dex */
public final class FlowableOnBackpressureReduce<T> extends AbstractFlowableWithUpstream<T, T> {
    final BiFunction<T, T, T> reducer;

    public FlowableOnBackpressureReduce(Flowable<T> source, BiFunction<T, T, T> reducer) {
        super(source);
        this.reducer = reducer;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe((FlowableSubscriber) new BackpressureReduceSubscriber(s, this.reducer));
    }

    static final class BackpressureReduceSubscriber<T> extends AbstractBackpressureThrottlingSubscriber<T, T> {
        private static final long serialVersionUID = 821363947659780367L;
        final BiFunction<T, T, T> reducer;

        BackpressureReduceSubscriber(Subscriber<? super T> downstream, BiFunction<T, T, T> reducer) {
            super(downstream);
            this.reducer = reducer;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // io.reactivex.rxjava3.internal.operators.flowable.AbstractBackpressureThrottlingSubscriber, org.reactivestreams.Subscriber
        public void onNext(T t) {
            Object andSet = this.current.get();
            if (andSet != null) {
                andSet = this.current.getAndSet(null);
            }
            if (andSet == null) {
                this.current.lazySet((R) t);
            } else {
                try {
                    this.current.lazySet((R) Objects.requireNonNull(this.reducer.apply((T) andSet, t), "The reducer returned a null value"));
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.upstream.cancel();
                    onError(th);
                    return;
                }
            }
            drain();
        }
    }
}
