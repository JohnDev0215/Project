package com.amazon.avod.prs;

import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyLicenseRequest implements PrsV2DrmLicenseRequest {
    private final ResourceParams mParams;
    private final PlaybackResourcesResponseListener mResponseListener;
    private final Map<String, String> mSessionContext;
    private final String mTitleId;

    private PlayReadyLicenseRequest(@Nonnull Builder builder) {
        Preconditions.checkNotNull(builder, "builder");
        this.mParams = builder.mParams;
        this.mSessionContext = builder.mSessionContext;
        this.mResponseListener = builder.mResponseListener;
        this.mTitleId = builder.mTitleId;
    }

    @Override // com.amazon.avod.prs.PrsV2DrmLicenseRequest
    @Nonnull
    public ResourceParams getParams() {
        return this.mParams;
    }

    @Override // com.amazon.avod.prs.PrsV2DrmLicenseRequest
    @Nonnull
    public Map<String, String> getSessionContext() {
        return this.mSessionContext;
    }

    @Override // com.amazon.avod.prs.PrsV2DrmLicenseRequest
    @Nullable
    public PlaybackResourcesResponseListener getResponseListener() {
        return this.mResponseListener;
    }

    @Override // com.amazon.avod.prs.PrsV2DrmLicenseRequest
    @Nonnull
    public String getTitleId() {
        return this.mTitleId;
    }

    public static class Builder {
        private ResourceParams mParams;
        private PlaybackResourcesResponseListener mResponseListener;
        private Map<String, String> mSessionContext;
        private String mTitleId;

        public Builder params(@Nonnull ResourceParams resourceParams) {
            this.mParams = (ResourceParams) Preconditions.checkNotNull(resourceParams, "params");
            return this;
        }

        public Builder sessionContext(@Nonnull Map<String, String> map) {
            this.mSessionContext = (Map) Preconditions.checkNotNull(map, "sessionContext");
            return this;
        }

        public Builder responseListener(@Nullable PlaybackResourcesResponseListener playbackResourcesResponseListener) {
            this.mResponseListener = playbackResourcesResponseListener;
            return this;
        }

        public Builder titleId(@Nonnull String str) {
            this.mTitleId = (String) Preconditions.checkNotNull(str, "titleId");
            return this;
        }

        @Nonnull
        public PlayReadyLicenseRequest build() {
            return new PlayReadyLicenseRequest(this);
        }
    }
}
