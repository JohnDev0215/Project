package com.amazon.minerva.client.common.internal.transport;

import com.amazon.minerva.client.thirdparty.transport.OAuthProvider;

/* JADX INFO: loaded from: classes3.dex */
public class OAuthProviderAdapter implements OAuthProvider {
    private com.amazon.minerva.client.common.transport.OAuthProvider mOAuthProvider;

    public OAuthProviderAdapter(com.amazon.minerva.client.common.transport.OAuthProvider oAuthProvider) {
        this.mOAuthProvider = oAuthProvider;
    }

    @Override // com.amazon.minerva.client.thirdparty.transport.OAuthProvider
    public String getAccessToken() throws Exception {
        return this.mOAuthProvider.getAccessToken();
    }
}
