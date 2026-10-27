package com.amazon.commscore.api.identity;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CommsCoreIdentity {
    private String commsId;
    private String directedId;
    private String homegroupId;
    private MarketplaceInfo marketplaceInfo;

    @Nullable
    public String getDirectedId() {
        return this.directedId;
    }

    @Nullable
    public String getHomegroupId() {
        return this.homegroupId;
    }

    @Nullable
    public String getCommsId() {
        return this.commsId;
    }

    @Nullable
    public MarketplaceInfo getMarketplaceInfo() {
        return this.marketplaceInfo;
    }
}
