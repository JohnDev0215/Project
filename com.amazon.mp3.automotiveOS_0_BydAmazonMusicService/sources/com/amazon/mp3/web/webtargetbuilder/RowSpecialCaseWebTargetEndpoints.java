package com.amazon.mp3.web.webtargetbuilder;

import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndpointsProvider;

/* JADX INFO: loaded from: classes4.dex */
public class RowSpecialCaseWebTargetEndpoints implements WebTargetEndpoints {
    @Override // com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints
    public String getDefaultWebEndpoint() {
        return Environment.FTU.get().toURL();
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints
    public String getApexRowMusicWebEndpoint() {
        return getRowEndpoint();
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.WebTargetEndpoints
    public String getRowPrimeOnlyWebEndpoint() {
        return getRowEndpoint();
    }

    private String getRowEndpoint() {
        return EndpointsProvider.get().getApexRowMusicEndpoint();
    }
}
