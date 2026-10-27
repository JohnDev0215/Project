package com.amazon.avod.prs;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackResourcesFactory {
    @Nonnull
    public static GetPlaybackResources createNonCachingInstance(@Nonnull GetPlaybackResourcesRequestProvider getPlaybackResourcesRequestProvider) {
        return new GetPlaybackResourcesFromNetwork(getPlaybackResourcesRequestProvider);
    }

    @Nonnull
    public static GetPlaybackResourcesV2 createNonCachingInstance(@Nonnull GetPlaybackResourcesV2RequestProvider getPlaybackResourcesV2RequestProvider) {
        return new GetPlaybackResourcesV2(getPlaybackResourcesV2RequestProvider);
    }

    @Nonnull
    public static GetCombinedPlaybackResources createNonCachingInstance(@Nonnull GetPlaybackResourcesRequestProvider getPlaybackResourcesRequestProvider, @Nonnull GetPlaybackResourcesV2RequestProvider getPlaybackResourcesV2RequestProvider) {
        return new GetCombinedPlaybackResources(getPlaybackResourcesRequestProvider, getPlaybackResourcesV2RequestProvider);
    }

    @Nonnull
    public static GetDrmLicenseResources createPlayReadyInstance(@Nonnull GetPlayReadyLicensePlayerRequest getPlayReadyLicensePlayerRequest) {
        return new GetDrmLicenseResources(getPlayReadyLicensePlayerRequest);
    }

    @Nonnull
    public static GetDrmLicenseResources createWidevineInstance(@Nonnull GetWidevineLicensePlayerRequest getWidevineLicensePlayerRequest) {
        return new GetDrmLicenseResources(getWidevineLicensePlayerRequest);
    }
}
