package com.amplifyframework.datastore.syncengine;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.logging.Logger;
import io.reactivex.rxjava3.core.Completable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class RetryStrategy {
    public static final RxRetryStrategy RX_INTERRUPTIBLE_WITH_BACKOFF = new RxCompletableExponentialBackoffStrategy(2, 8, Arrays.asList(InterruptedException.class));
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");

    interface RxRetryStrategy {
        boolean retryHandler(int i, Throwable th);
    }

    private RetryStrategy() {
    }

    static final class RxCompletableExponentialBackoffStrategy implements RxRetryStrategy {
        private final int maxExponent;
        private final List<Class<? extends Throwable>> skipExceptionTypes;
        private final int waitBaseDelay;

        RxCompletableExponentialBackoffStrategy(int i, int i2, List<Class<? extends Throwable>> list) {
            this.waitBaseDelay = i;
            this.maxExponent = i2;
            this.skipExceptionTypes = list;
        }

        @Override // com.amplifyframework.datastore.syncengine.RetryStrategy.RxRetryStrategy
        public boolean retryHandler(int i, Throwable th) {
            RetryStrategy.LOG.verbose("Should retry? attempt number:" + i + " exception type:" + th.getClass());
            if (this.skipExceptionTypes.contains(th.getClass())) {
                return false;
            }
            long jLongValue = Double.valueOf(Math.pow(2.0d, i % this.maxExponent)).longValue();
            RetryStrategy.LOG.debug("Waiting " + jLongValue + " seconds before retrying");
            Completable.timer(TimeUnit.SECONDS.toMillis(jLongValue), TimeUnit.MILLISECONDS).blockingAwait();
            return true;
        }
    }
}
