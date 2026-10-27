package com.amazon.avod.prs;

import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2Wrapper;
import com.amazon.bolthttp.Request;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface GetPlaybackResourcesV2RequestProvider {
    @Nonnull
    Request<PlaybackResourcesV2Wrapper> buildRequest(@Nonnull PRSV2ResourceRequest pRSV2ResourceRequest);
}
