package com.amazon.client.metrics.common.transport.internal.android;

import com.amazon.client.metrics.thirdparty.transport.OAuthHelper;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidOAuthHelperImpl implements OAuthHelper {
    private final com.amazon.client.metrics.common.transport.OAuthHelper mCommonOAuthHelper;

    public AndroidOAuthHelperImpl(com.amazon.client.metrics.common.transport.OAuthHelper oAuthHelper) {
        if (oAuthHelper == null) {
            throw new NullPointerException("OAuthHelper may not be null");
        }
        this.mCommonOAuthHelper = oAuthHelper;
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.OAuthHelper
    public String getAccessToken() throws Exception {
        return this.mCommonOAuthHelper.getAccessToken();
    }
}
