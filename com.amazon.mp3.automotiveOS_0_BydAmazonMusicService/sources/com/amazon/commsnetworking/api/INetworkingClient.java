package com.amazon.commsnetworking.api;

import androidx.annotation.NonNull;
import com.amazon.commsnetworking.NetworkException;
import com.amazon.commsnetworking.auth.AuthenticationProvider;

/* JADX INFO: loaded from: classes2.dex */
public interface INetworkingClient {

    public interface Builder {
        INetworkingClient build();

        Builder withAuthProvider(@NonNull AuthenticationProvider authenticationProvider);

        Builder withHost(@NonNull String str);

        Builder withReadTimeout(int i);

        Builder withWriteTimeout(int i);
    }

    INetworkResponse execute(@NonNull INetworkRequest iNetworkRequest) throws NetworkException;

    INetworkRequest get(@NonNull String str, @NonNull String str2);

    @NonNull
    AuthenticationProvider getAuthProvider();

    int getReadTimeout();

    int getWriteTimeout();

    INetworkRequest patch(@NonNull String str, @NonNull String str2);

    INetworkRequest post(@NonNull String str, @NonNull String str2);
}
