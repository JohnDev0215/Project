package com.amazon.coral.retry.strategy;

import com.amazon.coral.retry.CallListener;
import com.amazon.coral.retry.RetryContext;
import com.amazon.coral.retry.RetryStrategy;
import com.amazon.coral.retry.sequence.ConstantSequence;
import com.amazon.coral.retry.sequence.GeometricSequence;
import com.amazon.coral.retry.sequence.LengthBoundedSequence;
import com.amazon.coral.retry.sequence.RandomizedPositiveSequence;
import com.amazon.coral.retry.sequence.Sequence;
import com.amazon.coral.retry.transiency.AllowTransiencyStrategy;
import com.amazon.coral.retry.transiency.DenyTransiencyStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

/* JADX INFO: loaded from: classes2.dex */
public final class StandardRetryStrategyBuilder {
    private static final double DEFAULT_RANDOMIZATION_FACTOR = Math.nextAfter(1.0d, 0.0d);
    private Collection<Class<? extends Throwable>> retryOn = new ArrayList();
    private Collection<Class<? extends Throwable>> retryOnCause = new ArrayList();
    private Collection<Class<? extends Throwable>> throwOn = new ArrayList();
    private int quotaRefillRate = 1000;
    private int maxTokenCount = 10;
    private int maxElapsedTimeMillis = Integer.MAX_VALUE;
    private int retryDelayMillis = 0;
    private int maxRetryAttempts = 1;
    private double geometricRatio = 2.0d;
    private double randomizationFactor = DEFAULT_RANDOMIZATION_FACTOR;
    private boolean exponentialBackoffAndJitter = false;
    private boolean exponentialParamSet = false;
    private boolean retrofitting = false;
    private boolean customShadowMode = false;
    private boolean retrofitParamSet = false;
    private boolean publicParamSet = false;
    private boolean antiWrapParamSet = false;
    private BiPredicate<Exception, Integer> retryPermittedFunction = null;
    private ToIntBiFunction<Exception, Integer> retryDelayFunction = null;
    private BiConsumer<Exception, Integer> retryEventConsumer = null;
    private BiConsumer<Exception, Integer> stopEventConsumer = null;
    private RetryStrategy<?> wrappedPrimary = null;
    private boolean strictMode = false;
    private long retrofitRetryDelayMillis = 0;
    private long retrofitMaxElapsedTimeMillis = 2147483647L;
    private long retrofitQuotaRefillRate = 1000;

    public StandardRetryStrategyBuilder retryOn(Collection<Class<? extends Throwable>> collection) {
        this.retryOn.addAll(collection);
        this.antiWrapParamSet = true;
        return this;
    }

    public StandardRetryStrategyBuilder retryOnCause(Collection<Class<? extends Throwable>> collection) {
        this.retryOnCause.addAll(collection);
        this.antiWrapParamSet = true;
        return this;
    }

    public StandardRetryStrategyBuilder throwOn(Collection<Class<? extends Throwable>> collection) {
        this.throwOn.addAll(collection);
        this.antiWrapParamSet = true;
        return this;
    }

    StandardRetryStrategyBuilder withRetrofitMaxElapsedTimeMillis(long j) {
        this.retrofitMaxElapsedTimeMillis = j;
        this.retrofitParamSet = true;
        this.antiWrapParamSet = true;
        return this;
    }

    StandardRetryStrategyBuilder withRetrofitRetryDelayMillis(long j) {
        this.retrofitRetryDelayMillis = j;
        this.retrofitParamSet = true;
        this.antiWrapParamSet = true;
        return this;
    }

    public StandardRetryStrategyBuilder withMaxRetryAttempts(int i) {
        this.maxRetryAttempts = i;
        this.antiWrapParamSet = true;
        return this;
    }

    public StandardRetryStrategyBuilder withGeometricRatio(double d) {
        this.geometricRatio = d;
        this.exponentialParamSet = true;
        this.antiWrapParamSet = true;
        return this;
    }

    public StandardRetryStrategyBuilder withRandomizationFactor(double d) {
        this.randomizationFactor = d;
        this.exponentialParamSet = true;
        this.antiWrapParamSet = true;
        return this;
    }

    public StandardRetryStrategyBuilder withExponentialBackoffAndJitter(boolean z) {
        this.exponentialBackoffAndJitter = z;
        this.antiWrapParamSet = true;
        return this;
    }

    StandardRetryStrategyBuilder withRetrofitting(boolean z) {
        this.retrofitting = z;
        return this;
    }

    Sequence delaySequence() {
        if (this.exponentialBackoffAndJitter) {
            return new RandomizedPositiveSequence(this.randomizationFactor, new LengthBoundedSequence(this.maxRetryAttempts, -1.0d, new GeometricSequence(this.retrofitting ? this.retrofitRetryDelayMillis : this.retryDelayMillis, this.geometricRatio)));
        }
        return new LengthBoundedSequence(this.maxRetryAttempts, -1.0d, new ConstantSequence(this.retrofitting ? this.retrofitRetryDelayMillis : this.retryDelayMillis));
    }

    List<TransiencyRetryStrategy<RetryContext>> getTransiencyRetryStrategies() {
        return Arrays.asList(new TransiencyRetryStrategy(new DenyTransiencyStrategy(this.throwOn)), new TransiencyRetryStrategy(new AllowTransiencyStrategy(this.retryOn, this.retryOnCause)));
    }

    private void validateArgs() {
        if (this.exponentialParamSet && !this.exponentialBackoffAndJitter) {
            throw new IllegalArgumentException("Parameters related to exponential backoff and jitter were set, but the feature itself was not enabled");
        }
        boolean z = this.retrofitting;
        if (z && this.publicParamSet) {
            throw new IllegalArgumentException("Incorrect method used while retrofitting, use withRetrofit* methods for setting legacy long values such as retryDelayMillis.");
        }
        if (!z && this.retrofitParamSet) {
            throw new IllegalArgumentException("withRetrofit* methods used incorrectly while not retrofitting, use int-accepting methods for legacy long values such as retryDelayMillis.");
        }
        if (z && this.retrofitQuotaRefillRate < 200) {
            this.retrofitQuotaRefillRate = 200L;
        }
        if (!z && this.quotaRefillRate < 200) {
            throw new IllegalArgumentException("Quota refill rate was " + this.quotaRefillRate + " but must be at least 200");
        }
        boolean z2 = this.customShadowMode;
        if (z2 && !z) {
            throw new IllegalArgumentException("customShadowMode cannot be used unless retrofitting");
        }
        if (z2 && this.wrappedPrimary != null) {
            throw new IllegalArgumentException("customShadowMode cannot be used with wrappedPrimary");
        }
        if (z2 && this.antiWrapParamSet) {
            throw new IllegalArgumentException("customShadowMode cannot be used with additional exception or delay configuration");
        }
        RetryStrategy<?> retryStrategy = this.wrappedPrimary;
        if (retryStrategy != null && this.antiWrapParamSet) {
            throw new IllegalArgumentException("withWrappedPrimary cannot be used with additional exception or delay configuration");
        }
        if (!z && retryStrategy == null && this.retryOn.isEmpty() && this.retryOnCause.isEmpty()) {
            throw new IllegalArgumentException("No retryable exceptions configured");
        }
        int i = this.maxTokenCount;
        if (i <= 0 || i > 100) {
            throw new IllegalArgumentException("Max token count was " + this.maxTokenCount + " but must be greater than 0 and less than or equal to 100");
        }
        if (this.maxElapsedTimeMillis < 0) {
            throw new IllegalArgumentException("Negative value for maxElapsedTimeMillis");
        }
        if (this.retrofitMaxElapsedTimeMillis < 0) {
            throw new IllegalArgumentException("Negative value for maxElapsedTimeMillis while retrofitting");
        }
        if (this.retryDelayMillis < 0) {
            throw new IllegalArgumentException("Negative value for retryDelayMillis");
        }
        if (this.retrofitRetryDelayMillis < 0) {
            throw new IllegalArgumentException("Negative value for retryDelayMillis while retrofitting");
        }
        double d = this.randomizationFactor;
        if (d < 0.0d || d >= 1.0d) {
            throw new IllegalArgumentException("randomizationFactor must be within the range [0.0, 1.0)");
        }
        if (this.geometricRatio < 0.0d) {
            throw new IllegalArgumentException("Negative value for geometricRatio");
        }
    }

    public RetryStrategy<RetryContext> build() {
        validateArgs();
        StandardRetryStrategyCore standardRetryStrategyCore = new StandardRetryStrategyCore(this.retrofitting ? this.retrofitQuotaRefillRate : this.quotaRefillRate, this.maxTokenCount, this.strictMode);
        ArrayList arrayList = new ArrayList();
        RetryStrategy<?> retryStrategy = this.wrappedPrimary;
        CallListener callListener = retryStrategy instanceof CallListener ? (CallListener) retryStrategy : null;
        if (retryStrategy != null) {
            arrayList.add(retryStrategy);
        } else if (!this.customShadowMode) {
            arrayList.addAll(getTransiencyRetryStrategies());
            arrayList.add(new TimeoutRetryStrategy(this.retrofitting ? this.retrofitMaxElapsedTimeMillis : this.maxElapsedTimeMillis, delaySequence()));
        }
        arrayList.add(standardRetryStrategyCore);
        if (this.retryPermittedFunction != null) {
            arrayList.add(new RetryStrategy<RetryContext>() { // from class: com.amazon.coral.retry.strategy.StandardRetryStrategyBuilder.1
                @Override // com.amazon.coral.retry.RetryStrategy
                public long nextDelayMillis(long j, int i, Exception exc, RetryContext retryContext) {
                    try {
                        return StandardRetryStrategyBuilder.this.retryPermittedFunction.test(exc, Integer.valueOf(i)) ? 0L : -1L;
                    } catch (Throwable unused) {
                        return 0L;
                    }
                }
            });
        }
        if (this.retryDelayFunction != null) {
            arrayList.add(new RetryStrategy<RetryContext>() { // from class: com.amazon.coral.retry.strategy.StandardRetryStrategyBuilder.2
                @Override // com.amazon.coral.retry.RetryStrategy
                public long nextDelayMillis(long j, int i, Exception exc, RetryContext retryContext) {
                    try {
                        return StandardRetryStrategyBuilder.this.retryDelayFunction.applyAsInt(exc, Integer.valueOf(i));
                    } catch (Throwable unused) {
                        return 0L;
                    }
                }
            });
        }
        return new StandardRetryStrategy(standardRetryStrategyCore, callListener, new RetryStrategyChain(arrayList), this.retryEventConsumer, this.stopEventConsumer);
    }
}
