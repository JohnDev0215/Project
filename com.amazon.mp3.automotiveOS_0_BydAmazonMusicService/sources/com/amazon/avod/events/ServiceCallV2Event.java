package com.amazon.avod.events;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.http.ServiceClient;
import com.amazon.bolthttp.CancelledException;
import com.amazon.bolthttp.policy.impl.Retryability;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ServiceCallV2Event<T> extends AbstractEvent {
    private static final ImmutableSet<Class<? extends Exception>> IGNORABLE_EXCEPTION_TYPES = ImmutableSet.builder().addAll((Iterable) Retryability.AGGRESSIVE_RETRYABLE_NETWORK_EXCEPTIONS).add(CancelledException.class).add(InterruptedException.class).build();
    protected final String mLogTag;
    protected final NetworkConnectionManager mNetworkConnectionManager;
    protected final ServiceClient mServiceClient;

    public ServiceCallV2Event(@Nonnull EventData eventData, @Nonnull EventPolicy eventPolicy) {
        this(eventData, eventPolicy, NetworkConnectionManager.getInstance(), ServiceClient.getInstance());
    }

    ServiceCallV2Event(@Nonnull EventData eventData, @Nonnull EventPolicy eventPolicy, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull ServiceClient serviceClient) {
        super(eventData, eventPolicy);
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mServiceClient = (ServiceClient) Preconditions.checkNotNull(serviceClient, "serviceClient");
        this.mLogTag = getClass().getSimpleName();
    }
}
