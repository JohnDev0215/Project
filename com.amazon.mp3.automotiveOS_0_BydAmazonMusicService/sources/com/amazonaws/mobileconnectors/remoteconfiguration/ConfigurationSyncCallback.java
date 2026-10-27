package com.amazonaws.mobileconnectors.remoteconfiguration;

/* JADX INFO: loaded from: classes5.dex */
public interface ConfigurationSyncCallback {
    void onConfigurationModified(Configuration configuration);

    void onConfigurationUnmodified(Configuration configuration);

    void onFailure(Exception exc);

    void onThrottle(long j);
}
