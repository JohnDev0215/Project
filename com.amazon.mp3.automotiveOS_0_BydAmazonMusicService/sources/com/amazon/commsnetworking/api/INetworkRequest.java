package com.amazon.commsnetworking.api;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Request;

/* JADX INFO: loaded from: classes2.dex */
public interface INetworkRequest {
    Request buildRequest();

    boolean canRetry(INetworkResponse iNetworkResponse);

    int getAttemptCount();

    void prepareForRetry(INetworkResponse iNetworkResponse);

    void setCall(@NonNull Call call);

    INetworkRequest withHeader(@NonNull String str, @NonNull String str2);

    INetworkRequest withParameter(@NonNull String str, @NonNull String str2);

    INetworkRequest withPayload(@NonNull String str, @NonNull String str2);
}
