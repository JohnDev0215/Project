package com.amazon.client.metrics.thirdparty.configuration;

import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class MetricsNetworkConfiguration implements NetworkConfiguration {
    final Set<NetworkType> mNetworkTypes;
    TransportType mTransportType;

    public MetricsNetworkConfiguration(TransportType transportType, Set<NetworkType> set) {
        if (transportType == null) {
            throw new IllegalArgumentException("tranportType is null in configuration");
        }
        if (set == null || set.isEmpty()) {
            throw new IllegalArgumentException("networkTypes is null or empty in configuration");
        }
        this.mTransportType = transportType;
        this.mNetworkTypes = set;
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.NetworkConfiguration
    public TransportType getTransportType() {
        return this.mTransportType;
    }

    @Override // com.amazon.client.metrics.thirdparty.configuration.NetworkConfiguration
    public Set<NetworkType> getNetworkTypes() {
        return this.mNetworkTypes;
    }
}
