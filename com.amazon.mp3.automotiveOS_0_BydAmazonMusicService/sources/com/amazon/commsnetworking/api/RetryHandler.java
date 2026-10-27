package com.amazon.commsnetworking.api;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public interface RetryHandler {
    void prepareRetryRequest(@NonNull INetworkRequest iNetworkRequest, @NonNull INetworkResponse iNetworkResponse);

    boolean shouldRetry(@NonNull INetworkResponse iNetworkResponse);
}
