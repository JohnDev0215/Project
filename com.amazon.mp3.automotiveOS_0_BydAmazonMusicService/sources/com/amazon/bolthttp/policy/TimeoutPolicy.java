package com.amazon.bolthttp.policy;

import com.amazon.bolthttp.policy.AttemptContext;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface TimeoutPolicy<T extends AttemptContext> {
    void before(@Nonnull T t);

    long getConnectTimeoutMillis(@Nonnull T t);

    long getConnectivityTimeoutMillis(@Nonnull T t);

    long getReadTimeoutMillis(@Nonnull T t);
}
