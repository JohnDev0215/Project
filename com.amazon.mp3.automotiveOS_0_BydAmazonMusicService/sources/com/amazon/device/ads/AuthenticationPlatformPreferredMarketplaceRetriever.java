package com.amazon.device.ads;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
class AuthenticationPlatformPreferredMarketplaceRetriever implements PreferredMarketplaceRetriever {
    private final AuthenticationPlatformAdapter authenticationPlatformAdapter;

    public AuthenticationPlatformPreferredMarketplaceRetriever(AuthenticationPlatformAdapter authenticationPlatformAdapter) {
        this.authenticationPlatformAdapter = authenticationPlatformAdapter;
    }

    @Override // com.amazon.device.ads.PreferredMarketplaceRetriever
    public String retrievePreferredMarketplace(Context context) {
        return this.authenticationPlatformAdapter.setContext(context).getPreferredMarketplace();
    }
}
