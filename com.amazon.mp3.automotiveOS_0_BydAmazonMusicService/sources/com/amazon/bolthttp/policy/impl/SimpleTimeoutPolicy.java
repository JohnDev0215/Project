package com.amazon.bolthttp.policy.impl;

import com.amazon.bolthttp.policy.AttemptContext;
import com.amazon.bolthttp.policy.TimeoutPolicy;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleTimeoutPolicy implements TimeoutPolicy<AttemptContext> {
    public static final long DEFAULT_CONNECTIVITY_TIMEOUT_MILLIS;
    public static final long DEFAULT_CONNECT_TIMEOUT_MILLIS;
    public static final long DEFAULT_READ_TIMEOUT_MILLIS;
    private final long mConnectTimeoutMillis;
    private final long mConnectivityTimeoutMillis;
    private final long mReadTimeoutMillis;

    @Override // com.amazon.bolthttp.policy.TimeoutPolicy
    public void before(@Nonnull AttemptContext attemptContext) {
    }

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        DEFAULT_CONNECTIVITY_TIMEOUT_MILLIS = timeUnit.toMillis(5L);
        DEFAULT_CONNECT_TIMEOUT_MILLIS = timeUnit.toMillis(5L);
        DEFAULT_READ_TIMEOUT_MILLIS = timeUnit.toMillis(10L);
    }

    public SimpleTimeoutPolicy() {
        this(DEFAULT_CONNECTIVITY_TIMEOUT_MILLIS, DEFAULT_CONNECT_TIMEOUT_MILLIS, DEFAULT_READ_TIMEOUT_MILLIS);
    }

    public SimpleTimeoutPolicy(long j, long j2, long j3) {
        if (j < 0) {
            throw new IllegalArgumentException("connectivityTimeoutMillis < 0");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("connectTimeoutMillis < 0");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("readTimeoutMillis < 0");
        }
        this.mConnectivityTimeoutMillis = j;
        this.mConnectTimeoutMillis = j2;
        this.mReadTimeoutMillis = j3;
    }

    @Override // com.amazon.bolthttp.policy.TimeoutPolicy
    public long getConnectivityTimeoutMillis(@Nonnull AttemptContext attemptContext) {
        return this.mConnectivityTimeoutMillis;
    }

    @Override // com.amazon.bolthttp.policy.TimeoutPolicy
    public long getConnectTimeoutMillis(@Nonnull AttemptContext attemptContext) {
        return this.mConnectTimeoutMillis;
    }

    @Override // com.amazon.bolthttp.policy.TimeoutPolicy
    public long getReadTimeoutMillis(@Nonnull AttemptContext attemptContext) {
        return this.mReadTimeoutMillis;
    }
}
