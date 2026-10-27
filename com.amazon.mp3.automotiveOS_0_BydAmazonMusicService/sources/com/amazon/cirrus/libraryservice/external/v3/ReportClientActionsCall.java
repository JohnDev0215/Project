package com.amazon.cirrus.libraryservice.external.v3;

import com.amazon.cirrus.libraryservice.v3.ReportClientActionsRequest;
import com.amazon.cirrus.libraryservice.v3.ReportClientActionsResponse;
import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.AuthMethod;
import com.amazon.hermes.CoralCall;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class ReportClientActionsCall extends CoralCall<ReportClientActionsRequest, ReportClientActionsResponse> {
    public ReportClientActionsCall(AuthMethod authMethod, URL url, ReportClientActionsRequest reportClientActionsRequest, boolean z) {
        super(authMethod, url, reportClientActionsRequest, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new ReportClientActionsApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<ReportClientActionsResponse> getResponseType() {
        return ReportClientActionsResponse.class;
    }
}
