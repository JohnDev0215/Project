package com.amazon.avod.prs;

import com.amazon.avod.playbackresourcev2.PlaybackResourcesV2Wrapper;
import com.amazon.bolthttp.Request;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class GetDrmLicenseResourcesRequestProvider {
    @Nonnull
    abstract Request<PlaybackResourcesV2Wrapper> buildRequest(@Nonnull PrsV2DrmLicenseRequest prsV2DrmLicenseRequest);
}
