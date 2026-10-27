package com.amazon.bolthttp.internal.util;

import com.amazon.bolthttp.BoltConfig;
import com.amazon.bolthttp.NetworkStrategy;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.RequestStrategy;
import com.amazon.bolthttp.policy.NetworkContext;
import com.amazon.bolthttp.policy.RequestContext;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class StrategyHelper<T> {
    private final NetworkStrategy mNetworkStrategy;
    private final Request<T> mRequest;
    private RequestContext mRequestContext;
    private final RequestStrategy mRequestStrategy;

    public StrategyHelper(@Nonnull Request<T> request, @Nonnull BoltConfig boltConfig) {
        this.mRequest = (Request) Preconditions.checkNotNull(request, "request");
        Preconditions.checkNotNull(boltConfig, "config");
        RequestStrategy requestStrategy = request.getRequestStrategy();
        NetworkStrategy networkStrategy = request.getNetworkStrategy();
        this.mRequestStrategy = requestStrategy == null ? boltConfig.getRequestStrategy() : requestStrategy;
        this.mNetworkStrategy = networkStrategy == null ? boltConfig.getNetworkStrategy() : networkStrategy;
    }

    public RequestStrategy getRequestStrategy() {
        return this.mRequestStrategy;
    }

    public NetworkStrategy getNetworkStrategy() {
        return this.mNetworkStrategy;
    }

    public void initializeRequestContextAndPolicies() {
        this.mRequestContext = new RequestContext(this.mRequest);
        this.mRequestStrategy.getRetryPolicy().before(this.mRequestContext);
        this.mRequestStrategy.getBackoffPolicy().before(this.mRequestContext);
    }

    public NetworkContext initializeNetworkContextAndPolicies() {
        NetworkContext networkContext = new NetworkContext(this.mRequestContext);
        this.mNetworkStrategy.getRetryPolicy().before(networkContext);
        this.mNetworkStrategy.getBackoffPolicy().before(networkContext);
        this.mNetworkStrategy.getTimeoutPolicy().before(networkContext);
        return networkContext;
    }

    public RequestContext getRequestContext() {
        return this.mRequestContext;
    }
}
