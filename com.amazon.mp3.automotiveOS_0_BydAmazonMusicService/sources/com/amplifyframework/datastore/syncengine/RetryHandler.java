package com.amplifyframework.datastore.syncengine;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.utils.ErrorInspector;
import com.amplifyframework.logging.Logger;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class RetryHandler {
    private static final int JITTER_FACTOR_VALUE = 100;
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private static final int MAX_ATTEMPTS_VALUE = 3;
    private static final int MAX_DELAY_S_VALUE = 300;
    private static final int MAX_EXPONENT_VALUE = 8;
    private final int jitterFactor;
    private final int maxAttempts;
    private final int maxDelayS;
    private final int maxExponent;

    public RetryHandler(int i, int i2, int i3, int i4) {
        this.maxExponent = i;
        this.jitterFactor = i2;
        this.maxAttempts = i3;
        this.maxDelayS = i4;
    }

    public RetryHandler() {
        this.maxExponent = 8;
        this.jitterFactor = 100;
        this.maxAttempts = 3;
        this.maxDelayS = 300;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$retry$0(Single single, List list, SingleEmitter singleEmitter) throws Throwable {
        call(single, singleEmitter, 0L, this.maxAttempts, list);
    }

    public <T> Single<T> retry(final Single<T> single, final List<Class<? extends Throwable>> list) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.RetryHandler$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                this.f$0.lambda$retry$0(single, list, singleEmitter);
            }
        });
    }

    private <T> void call(final Single<T> single, final SingleEmitter<T> singleEmitter, Long l, final int i, final List<Class<? extends Throwable>> list) {
        Single<T> singleDelaySubscription = single.delaySubscription(l.longValue(), TimeUnit.SECONDS);
        singleEmitter.getClass();
        singleDelaySubscription.subscribe(new RetryHandler$$ExternalSyntheticLambda1(singleEmitter), new Consumer() { // from class: com.amplifyframework.datastore.syncengine.RetryHandler$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$call$1(singleEmitter, i, list, single, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$call$1(SingleEmitter singleEmitter, int i, List list, Single single, Throwable th) throws Throwable {
        if (!singleEmitter.isDisposed()) {
            LOG.verbose("Retry attempts left " + i + ". exception type:" + th.getClass());
            if (i == 0 || ErrorInspector.contains(th, (List<Class<? extends Throwable>>) list)) {
                singleEmitter.onError(th);
                return;
            } else {
                call(single, singleEmitter, Long.valueOf(jitteredDelaySec(i)), i - 1, list);
                return;
            }
        }
        LOG.verbose("The subscribing channel is disposed.");
    }

    long jitteredDelaySec(int i) {
        int i2 = this.maxAttempts;
        double dMin = Math.min(this.maxDelayS, Math.pow(2.0d, (i2 - (i2 - i)) % this.maxExponent) + (((double) this.jitterFactor) * Math.random()));
        LOG.debug("Wait time is " + dMin + " seconds before retrying");
        return (long) dMin;
    }
}
