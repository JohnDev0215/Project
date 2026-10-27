package com.amazon.music.push.citadel.util;

import com.amazon.coral.retry.strategy.ExponentialBackoffAndJitterBuilder;
import com.amazon.music.push.citadel.exception.CitadelServerException;
import com.amazon.music.push.citadel.exception.CitadelUpdateOutdatedException;

/* JADX INFO: loaded from: classes4.dex */
public class CitadelRetryStrategyProvider {
    public static ExponentialBackoffAndJitterBuilder getDefaultStateUpdateStrategy() {
        return new ExponentialBackoffAndJitterBuilder().retryOn(CitadelServerException.class).throwOn(CitadelUpdateOutdatedException.class).withInitialIntervalMillis(100.0d).withExponentialFactor(2.0d).withRandomizationFactor(0.1d).withMaxAttempts(5);
    }

    public static ExponentialBackoffAndJitterBuilder getDefaultActionUpdateStrategy() {
        return new ExponentialBackoffAndJitterBuilder().retryOn(CitadelServerException.class).withInitialIntervalMillis(100.0d).withExponentialFactor(2.0d).withRandomizationFactor(0.1d).withMaxAttempts(5);
    }
}
