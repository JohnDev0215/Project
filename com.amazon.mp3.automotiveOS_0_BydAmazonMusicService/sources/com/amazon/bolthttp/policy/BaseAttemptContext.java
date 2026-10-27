package com.amazon.bolthttp.policy;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
abstract class BaseAttemptContext implements AttemptContext {
    private final Map<Class<?>, Map<String, Object>> mPolicyState = new HashMap();
    private final long mStartTimeMillis = getTimeMillis();
    private int mAttemptCount = 0;
    private Exception mLastException = null;

    protected abstract long getTimeMillis();

    BaseAttemptContext() {
    }

    @Override // com.amazon.bolthttp.policy.AttemptContext
    public int getAttemptCount() {
        return this.mAttemptCount;
    }

    @Override // com.amazon.bolthttp.policy.AttemptContext
    public long getTotalElapsedTime() {
        return getTimeMillis() - this.mStartTimeMillis;
    }

    @Override // com.amazon.bolthttp.policy.AttemptContext
    public Exception getLastException() {
        return this.mLastException;
    }

    public void onStartNextAttempt() {
        this.mAttemptCount++;
    }

    public void onAttemptFailure(@Nonnull Exception exc) {
        this.mLastException = (Exception) Preconditions.checkNotNull(exc, "exception");
    }
}
