package com.amazon.musicplayqueueservice.model.client.external.voiceenabled;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicplayqueueservice.client.common.TransferQueueRequest;
import com.amazon.musicplayqueueservice.client.common.TransferQueueResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class TransferQueueCall extends CoralCall<TransferQueueRequest, TransferQueueResponse> {
    public TransferQueueCall(URL url, TransferQueueRequest transferQueueRequest, RequestInterceptor requestInterceptor) {
        this(url, transferQueueRequest, requestInterceptor, false);
    }

    public TransferQueueCall(URL url, TransferQueueRequest transferQueueRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, transferQueueRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new TransferQueueApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<TransferQueueResponse> getResponseType() {
        return TransferQueueResponse.class;
    }
}
