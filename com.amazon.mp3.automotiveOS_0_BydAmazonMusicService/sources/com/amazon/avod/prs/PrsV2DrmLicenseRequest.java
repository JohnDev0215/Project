package com.amazon.avod.prs;

import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface PrsV2DrmLicenseRequest {
    @Nonnull
    ResourceParams getParams();

    @Nullable
    PlaybackResourcesResponseListener getResponseListener();

    @Nonnull
    Map<String, String> getSessionContext();

    @Nonnull
    String getTitleId();
}
