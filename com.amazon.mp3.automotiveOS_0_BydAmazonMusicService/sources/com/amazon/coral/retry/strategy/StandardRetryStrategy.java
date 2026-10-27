package com.amazon.coral.retry.strategy;

import com.amazon.coral.retry.CallListener;
import com.amazon.coral.retry.RetryContext;
import com.amazon.coral.retry.RetryStrategy;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes2.dex */
final class StandardRetryStrategy<CONTEXT> implements RetryStrategy<CONTEXT>, CallListener {
    private final CallListener callListener;
    private final RetryStrategy<CONTEXT> delegate;
    private final BiConsumer<Exception, Integer> retryEventConsumer;
    private final BiConsumer<Exception, Integer> stopEventConsumer;
    private final CallListener wrappedListener;

    StandardRetryStrategy(CallListener callListener, CallListener callListener2, RetryStrategy<CONTEXT> retryStrategy, BiConsumer<Exception, Integer> biConsumer, BiConsumer<Exception, Integer> biConsumer2) {
        this.delegate = retryStrategy;
        this.callListener = callListener;
        this.wrappedListener = callListener2;
        this.retryEventConsumer = biConsumer;
        this.stopEventConsumer = biConsumer2;
    }

    @Override // com.amazon.coral.retry.CallListener
    public void onSuccess(RetryContext retryContext) {
        this.callListener.onSuccess(retryContext);
        CallListener callListener = this.wrappedListener;
        if (callListener != null) {
            callListener.onSuccess(retryContext);
        }
    }

    @Override // com.amazon.coral.retry.CallListener
    public void onAttemptBegin(RetryContext retryContext) {
        this.callListener.onAttemptBegin(retryContext);
        CallListener callListener = this.wrappedListener;
        if (callListener != null) {
            callListener.onAttemptBegin(retryContext);
        }
    }

    @Override // com.amazon.coral.retry.RetryStrategy
    public long nextDelayMillis(long j, int i, Exception exc, CONTEXT context) {
        long jNextDelayMillis = this.delegate.nextDelayMillis(j, i, exc, context);
        try {
            if (jNextDelayMillis == -1) {
                BiConsumer<Exception, Integer> biConsumer = this.stopEventConsumer;
                if (biConsumer != null) {
                    biConsumer.accept(exc, Integer.valueOf(i));
                }
            } else {
                BiConsumer<Exception, Integer> biConsumer2 = this.retryEventConsumer;
                if (biConsumer2 != null) {
                    biConsumer2.accept(exc, Integer.valueOf(i));
                }
            }
        } catch (Throwable unused) {
        }
        return jNextDelayMillis;
    }
}
