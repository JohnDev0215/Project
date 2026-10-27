package com.amazon.coral.retry.strategy;

import com.amazon.coral.retry.RetryStrategy;
import com.amazon.coral.retry.sequence.GeometricSequence;
import com.amazon.coral.retry.sequence.LengthBoundedSequence;
import com.amazon.coral.retry.sequence.RandomizedPositiveSequence;
import com.amazon.coral.retry.transiency.AllowTransiencyStrategy;
import com.amazon.coral.retry.transiency.DenyTransiencyStrategy;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class ExponentialBackoffAndJitterBuilder {
    private ExponentialBackoffAndJitterBuilderConfig config = new ExponentialBackoffAndJitterBuilderConfig();

    public RetryStrategy<Void> newStrategy() {
        Preconditions.checkArgument(this.config.getMaxAttempts() >= 1, "At least one attempt (the first one) must be made.");
        return new RetryStrategyChain(Arrays.asList(new TransiencyRetryStrategy(new DenyTransiencyStrategy(this.config.getUnrecoverableThrowables())), new TransiencyRetryStrategy(new AllowTransiencyStrategy(this.config.getRecoverableThrowables(), this.config.getRecoverableCauseThrowables())), new TimeoutRetryStrategy(this.config.getMaxWait(), new RandomizedPositiveSequence(this.config.getRandomizationFactor(), new LengthBoundedSequence(this.config.getMaxAttempts() - 1, -1.0d, new GeometricSequence(this.config.getInitialIntervalMillis(), this.config.getGeometricRatio()))))), retrofitBuilder().build());
    }

    StandardRetryStrategyBuilder retrofitBuilder() {
        return new StandardRetryStrategyBuilder().withMaxRetryAttempts(this.config.getMaxAttempts() - 1).withRetrofitRetryDelayMillis((long) Math.ceil(this.config.getInitialIntervalMillis())).withRetrofitMaxElapsedTimeMillis(this.config.getMaxWait()).withGeometricRatio(this.config.getGeometricRatio()).withRandomizationFactor(this.config.getRandomizationFactor()).retryOn(this.config.getRecoverableThrowables()).retryOnCause(this.config.getRecoverableCauseThrowables()).throwOn(this.config.getUnrecoverableThrowables()).withExponentialBackoffAndJitter(true).withRetrofitting(true);
    }

    public ExponentialBackoffAndJitterBuilder retryOn(Class<? extends Throwable> cls) {
        this.config.addRecoverableThrowable(cls);
        return this;
    }

    public ExponentialBackoffAndJitterBuilder throwOn(Class<? extends Throwable> cls) {
        this.config.addUnrecoverableThrowable(cls);
        return this;
    }

    public ExponentialBackoffAndJitterBuilder withInitialIntervalMillis(double d) {
        this.config.setInitialIntervalMillis(d);
        return this;
    }

    public ExponentialBackoffAndJitterBuilder withExponentialFactor(double d) {
        this.config.setGeometricRatio(d);
        return this;
    }

    public ExponentialBackoffAndJitterBuilder withMaxAttempts(int i) {
        this.config.setMaxAttempts(i);
        return this;
    }

    public ExponentialBackoffAndJitterBuilder withRandomizationFactor(double d) {
        this.config.setRandomizationFactor(d);
        return this;
    }
}
