package com.amazon.bolthttp.policy;

import android.os.SystemClock;
import com.amazon.bolthttp.Request;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class RequestContext extends BaseAttemptContext {
    private final Request<?> mRequest;

    @Override // com.amazon.bolthttp.policy.BaseAttemptContext, com.amazon.bolthttp.policy.AttemptContext
    public /* bridge */ /* synthetic */ int getAttemptCount() {
        return super.getAttemptCount();
    }

    @Override // com.amazon.bolthttp.policy.BaseAttemptContext, com.amazon.bolthttp.policy.AttemptContext
    public /* bridge */ /* synthetic */ Exception getLastException() {
        return super.getLastException();
    }

    @Override // com.amazon.bolthttp.policy.BaseAttemptContext, com.amazon.bolthttp.policy.AttemptContext
    public /* bridge */ /* synthetic */ long getTotalElapsedTime() {
        return super.getTotalElapsedTime();
    }

    @Override // com.amazon.bolthttp.policy.BaseAttemptContext
    public /* bridge */ /* synthetic */ void onAttemptFailure(@Nonnull Exception exc) {
        super.onAttemptFailure(exc);
    }

    @Override // com.amazon.bolthttp.policy.BaseAttemptContext
    public /* bridge */ /* synthetic */ void onStartNextAttempt() {
        super.onStartNextAttempt();
    }

    public RequestContext(@Nonnull Request<?> request) {
        this.mRequest = (Request) Preconditions.checkNotNull(request, "request");
    }

    @Override // com.amazon.bolthttp.policy.BaseAttemptContext
    protected long getTimeMillis() {
        return SystemClock.elapsedRealtime();
    }
}
