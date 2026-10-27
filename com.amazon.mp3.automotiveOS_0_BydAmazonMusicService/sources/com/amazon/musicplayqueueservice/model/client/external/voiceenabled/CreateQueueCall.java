package com.amazon.musicplayqueueservice.model.client.external.voiceenabled;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.CreateQueueResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class CreateQueueCall extends CoralCall<CreateQueueRequest, CreateQueueResponse> {
    public CreateQueueCall(URL url, CreateQueueRequest createQueueRequest, RequestInterceptor requestInterceptor) {
        this(url, createQueueRequest, requestInterceptor, false);
    }

    public CreateQueueCall(URL url, CreateQueueRequest createQueueRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, createQueueRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new CreateQueueApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<CreateQueueResponse> getResponseType() {
        return CreateQueueResponse.class;
    }
}
