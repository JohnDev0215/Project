package com.amazon.stratus.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.stratus.RetrieveCustomerHomeRequest;
import com.amazon.stratus.RetrieveCustomerHomeResponse;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveCustomerHomeCall extends CoralCall<RetrieveCustomerHomeRequest, RetrieveCustomerHomeResponse> {
    public RetrieveCustomerHomeCall(URL url, RetrieveCustomerHomeRequest retrieveCustomerHomeRequest, List<RequestInterceptor> list) {
        this(url, retrieveCustomerHomeRequest, list, false);
    }

    public RetrieveCustomerHomeCall(URL url, RetrieveCustomerHomeRequest retrieveCustomerHomeRequest, List<RequestInterceptor> list, boolean z) {
        super(url, retrieveCustomerHomeRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new RetrieveCustomerHomeApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<RetrieveCustomerHomeResponse> getResponseType() {
        return RetrieveCustomerHomeResponse.class;
    }
}
