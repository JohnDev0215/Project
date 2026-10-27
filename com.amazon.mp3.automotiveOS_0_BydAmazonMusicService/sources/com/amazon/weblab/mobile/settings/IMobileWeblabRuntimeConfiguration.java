package com.amazon.weblab.mobile.settings;

/* JADX INFO: loaded from: classes5.dex */
public interface IMobileWeblabRuntimeConfiguration {
    MobileWeblabCachePolicyType getCachePolicy();

    String getDirectory();

    MobileWeblabServiceEndpoint getEndpoint();

    int getMaxDegreeOfParallelism();

    int getNetworkRetries();

    Interval getTtl();

    boolean isCleanUpAtInitEnabled();

    boolean isUpdateAtInitEnabled();
}
