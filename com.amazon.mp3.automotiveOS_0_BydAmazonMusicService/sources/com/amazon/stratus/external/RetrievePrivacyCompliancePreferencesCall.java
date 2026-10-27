package com.amazon.stratus.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.stratus.RetrievePrivacyCompliancePreferencesRequest;
import com.amazon.stratus.RetrievePrivacyCompliancePreferencesResponse;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrievePrivacyCompliancePreferencesCall extends CoralCall<RetrievePrivacyCompliancePreferencesRequest, RetrievePrivacyCompliancePreferencesResponse> {
    public RetrievePrivacyCompliancePreferencesCall(URL url, RetrievePrivacyCompliancePreferencesRequest retrievePrivacyCompliancePreferencesRequest, List<RequestInterceptor> list) {
        this(url, retrievePrivacyCompliancePreferencesRequest, list, false);
    }

    public RetrievePrivacyCompliancePreferencesCall(URL url, RetrievePrivacyCompliancePreferencesRequest retrievePrivacyCompliancePreferencesRequest, List<RequestInterceptor> list, boolean z) {
        super(url, retrievePrivacyCompliancePreferencesRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new RetrievePrivacyCompliancePreferencesApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<RetrievePrivacyCompliancePreferencesResponse> getResponseType() {
        return RetrievePrivacyCompliancePreferencesResponse.class;
    }
}
