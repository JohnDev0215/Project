package com.amazon.musicplayqueueservice.model.client.v2.external.voiceenabled;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicplayqueueservice.client.v2.queue.AbstractCreateQueueRequest;
import com.amazon.musicplayqueueservice.client.v2.queue.CreateQueueResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class CreateQueueCall extends CoralCall<AbstractCreateQueueRequest, CreateQueueResponse> {
    public CreateQueueCall(URL url, AbstractCreateQueueRequest abstractCreateQueueRequest, RequestInterceptor requestInterceptor) {
        this(url, abstractCreateQueueRequest, requestInterceptor, false);
    }

    public CreateQueueCall(URL url, AbstractCreateQueueRequest abstractCreateQueueRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, abstractCreateQueueRequest, requestInterceptor, z);
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
