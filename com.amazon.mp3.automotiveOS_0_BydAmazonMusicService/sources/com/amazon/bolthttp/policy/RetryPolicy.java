package com.amazon.bolthttp.policy;

import com.amazon.bolthttp.policy.AttemptContext;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface RetryPolicy<T extends AttemptContext> {
    void before(@Nonnull T t);

    Exception getReturnedException(@Nonnull T t);

    boolean shouldRetry(@Nonnull T t);
}
