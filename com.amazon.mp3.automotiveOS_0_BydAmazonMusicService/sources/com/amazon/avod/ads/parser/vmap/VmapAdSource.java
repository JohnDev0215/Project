package com.amazon.avod.ads.parser.vmap;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VmapAdSource {
    private final VmapAdTagURI mAdTagURI;
    private final Boolean mAllowMultipleAds;
    private final VmapCustomAdData mCustomAdData;
    private final Boolean mFollowRedirects;
    private final String mId;
    private final VmapVastAdData mVastData;

    public VmapAdSource(@Nullable String str, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable VmapVastAdData vmapVastAdData, @Nullable VmapAdTagURI vmapAdTagURI, @Nullable VmapCustomAdData vmapCustomAdData) {
        this.mId = str;
        this.mAllowMultipleAds = bool;
        this.mFollowRedirects = bool2;
        this.mVastData = vmapVastAdData;
        this.mAdTagURI = vmapAdTagURI;
        this.mCustomAdData = vmapCustomAdData;
    }

    @Nullable
    public VmapVastAdData getVastData() {
        return this.mVastData;
    }

    @Nullable
    public VmapAdTagURI getAdTagURI() {
        return this.mAdTagURI;
    }
}
