package com.amazon.client.metrics.thirdparty.configuration;

import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public interface NetworkConfiguration {
    Set<NetworkType> getNetworkTypes();

    TransportType getTransportType();
}
