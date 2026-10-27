package com.amazon.mp3.web.webtargetbuilder;

import com.amazon.mp3.environment.url.EndpointsProvider;

/* JADX INFO: loaded from: classes4.dex */
public class AccountWebTargetEndpoints implements WebTargetEndpoints {
    @Override // com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints
    public String getDefaultWebEndpoint() {
        return EndpointsProvider.get().getAccountWebViewEndpoint();
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints
    public String getApexRowMusicWebEndpoint() {
        return EndpointsProvider.get().getAccountWebViewEndpoint();
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints
    public String getRowPrimeOnlyWebEndpoint() {
        return EndpointsProvider.get().getAccountWebViewEndpoint();
    }
}
