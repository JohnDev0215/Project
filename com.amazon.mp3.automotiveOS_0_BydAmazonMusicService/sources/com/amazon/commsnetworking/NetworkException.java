package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkResponse;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkException extends Exception {

    @NonNull
    private INetworkRequest request;

    @Nullable
    private INetworkResponse response;

    public NetworkException(@NonNull INetworkRequest iNetworkRequest, @NonNull INetworkResponse iNetworkResponse) {
        super("Unexpected code " + iNetworkResponse.getStatusCode());
        this.request = iNetworkRequest;
        this.response = iNetworkResponse;
    }

    public NetworkException(@NonNull INetworkRequest iNetworkRequest, @NonNull Exception exc) {
        super(exc);
        this.request = iNetworkRequest;
    }
}
