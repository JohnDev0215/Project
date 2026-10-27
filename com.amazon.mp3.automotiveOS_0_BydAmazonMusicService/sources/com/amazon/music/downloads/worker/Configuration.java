package com.amazon.music.downloads.worker;

/* JADX INFO: loaded from: classes4.dex */
public interface Configuration {

    public interface NetworkRetryPolicyProvider {
        NetworkRetryPolicy createNewInstance();
    }

    String getAppVersion();

    int getConnectionTimeout();

    NetworkRetryPolicyProvider getNetworkRetryPolicyProvider();

    int getParallelDownloadsCount();
}
