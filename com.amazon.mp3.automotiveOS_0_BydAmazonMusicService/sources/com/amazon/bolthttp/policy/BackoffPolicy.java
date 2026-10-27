package com.amazon.bolthttp.policy;

import com.amazon.bolthttp.policy.AttemptContext;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface BackoffPolicy<T extends AttemptContext> {
    void before(T t);

    long getBackoffMillis(@Nonnull T t);
}
