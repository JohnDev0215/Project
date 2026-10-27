package com.amazon.bolthttp.policy;

import com.amazon.bolthttp.internal.util.TimeUtils;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class NetworkContext extends BaseAttemptContext {
    private final RequestContext mRequestContext;

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

    public NetworkContext(@Nonnull RequestContext requestContext) {
        this.mRequestContext = (RequestContext) Preconditions.checkNotNull(requestContext, "requestContext");
    }

    @Nonnull
    public RequestContext getRequestContext() {
        return this.mRequestContext;
    }

    @Override // com.amazon.bolthttp.policy.BaseAttemptContext
    protected long getTimeMillis() {
        return TimeUtils.getTimeMillis();
    }
}
