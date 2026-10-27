package com.amazon.musicplayqueueservice.model.client.v2.external.voiceenabled;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicplayqueueservice.client.v2.RateEntityRequest;
import com.amazon.musicplayqueueservice.client.v2.RateEntityResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class RateEntityCall extends CoralCall<RateEntityRequest, RateEntityResponse> {
    public RateEntityCall(URL url, RateEntityRequest rateEntityRequest, RequestInterceptor requestInterceptor) {
        this(url, rateEntityRequest, requestInterceptor, false);
    }

    public RateEntityCall(URL url, RateEntityRequest rateEntityRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, rateEntityRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new RateEntityApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<RateEntityResponse> getResponseType() {
        return RateEntityResponse.class;
    }
}
