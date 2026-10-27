package com.amazon.stratus.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.stratus.AttemptAccountCreationRequest;
import com.amazon.stratus.AttemptAccountCreationResponse;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AttemptAccountCreationCall extends CoralCall<AttemptAccountCreationRequest, AttemptAccountCreationResponse> {
    public AttemptAccountCreationCall(URL url, AttemptAccountCreationRequest attemptAccountCreationRequest, List<RequestInterceptor> list) {
        this(url, attemptAccountCreationRequest, list, false);
    }

    public AttemptAccountCreationCall(URL url, AttemptAccountCreationRequest attemptAccountCreationRequest, List<RequestInterceptor> list, boolean z) {
        super(url, attemptAccountCreationRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new AttemptAccountCreationApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<AttemptAccountCreationResponse> getResponseType() {
        return AttemptAccountCreationResponse.class;
    }
}
