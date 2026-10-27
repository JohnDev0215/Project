package com.amazon.musicplayqueueservice.model.client.external.voiceenabled;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicplayqueueservice.client.common.LoginRequest;
import com.amazon.musicplayqueueservice.client.common.LoginResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class LoginCall extends CoralCall<LoginRequest, LoginResponse> {
    public LoginCall(URL url, LoginRequest loginRequest, RequestInterceptor requestInterceptor) {
        this(url, loginRequest, requestInterceptor, false);
    }

    public LoginCall(URL url, LoginRequest loginRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, loginRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new LoginApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<LoginResponse> getResponseType() {
        return LoginResponse.class;
    }
}
