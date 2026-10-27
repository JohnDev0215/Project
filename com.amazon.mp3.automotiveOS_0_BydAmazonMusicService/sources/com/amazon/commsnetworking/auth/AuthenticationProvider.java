package com.amazon.commsnetworking.auth;

import androidx.annotation.NonNull;
import com.amazon.commsnetworking.api.INetworkRequest;

/* JADX INFO: loaded from: classes2.dex */
public interface AuthenticationProvider {
    void authenticateRequest(@NonNull INetworkRequest iNetworkRequest);
}
