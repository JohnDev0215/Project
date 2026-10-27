package com.amazon.commsnetworking.api;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface INetworkResponse extends AutoCloseable {
    @Nullable
    String getBody();

    int getStatusCode();

    boolean isSuccessful();
}
