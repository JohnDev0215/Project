package com.amazon.bolthttp.policy;

import javax.annotation.Nonnegative;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface AttemptContext {
    @Nonnegative
    int getAttemptCount();

    @Nullable
    Exception getLastException();

    @Nonnegative
    long getTotalElapsedTime();
}
