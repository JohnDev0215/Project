package com.amazon.avod.prs;

import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.util.DLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PRSV2ResourceRequest {
    private final ConsumptionType mConsumptionType;
    private final boolean mForValidation;
    private final ResourceParams mResourceParams;
    private String mResourceParamsJson;
    private final PlaybackResourcesResponseListener mResponseListener;
    private final Map<String, String> mSessionContext;
    private final String mTitleId;
    private final VideoMaterialType mVideoMaterialType;

    private PRSV2ResourceRequest(@Nonnull String str, @Nonnull VideoMaterialType videoMaterialType, @Nonnull ConsumptionType consumptionType, @Nonnull ResourceParams resourceParams, @Nonnull Map<String, String> map, @Nullable PlaybackResourcesResponseListener playbackResourcesResponseListener, boolean z) {
        this.mTitleId = (String) Preconditions.checkNotNull(str, "titleId");
        this.mVideoMaterialType = (VideoMaterialType) Preconditions.checkNotNull(videoMaterialType, "videoMaterialType");
        this.mConsumptionType = (ConsumptionType) Preconditions.checkNotNull(consumptionType, "consumptionType");
        ResourceParams resourceParams2 = (ResourceParams) Preconditions.checkNotNull(resourceParams, "resourceParams");
        this.mResourceParams = resourceParams2;
        this.mSessionContext = (Map) Preconditions.checkNotNull(map, "sessionContext");
        this.mResponseListener = playbackResourcesResponseListener;
        this.mForValidation = z;
        try {
            this.mResourceParamsJson = ResourceParamsUtils.formatParamsToJsonString(resourceParams2);
        } catch (JsonProcessingException e) {
            DLog.exceptionf(e, "Can not format resourceParams to Json", new Object[0]);
            this.mResourceParamsJson = this.mResourceParams.toString();
        }
    }

    @Nonnull
    public String getTitleId() {
        return this.mTitleId;
    }

    @Nonnull
    public VideoMaterialType getVideoMaterialType() {
        return this.mVideoMaterialType;
    }

    @Nonnull
    public ConsumptionType getConsumptionType() {
        return this.mConsumptionType;
    }

    @Nonnull
    public ResourceParams getResourceParams() {
        return this.mResourceParams;
    }

    @Nonnull
    public Map<String, String> getSessionContext() {
        return this.mSessionContext;
    }

    @Nullable
    public PlaybackResourcesResponseListener getResponseListener() {
        return this.mResponseListener;
    }

    public boolean isForValidation() {
        return this.mForValidation;
    }

    @Nonnull
    public String toString() {
        return MoreObjects.toStringHelper(this).add("titleId", this.mTitleId).add("videoMaterialType", this.mVideoMaterialType).add("consumptionType", this.mConsumptionType).add("resourceParamsJson", this.mResourceParamsJson).add("sessionContext", this.mSessionContext).add("isForValidation", this.mForValidation).toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.mTitleId, this.mVideoMaterialType, this.mConsumptionType, this.mResourceParamsJson, this.mSessionContext, Boolean.valueOf(this.mForValidation));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PRSV2ResourceRequest)) {
            return false;
        }
        PRSV2ResourceRequest pRSV2ResourceRequest = (PRSV2ResourceRequest) obj;
        if (this.mTitleId.equals(pRSV2ResourceRequest.mTitleId) && this.mVideoMaterialType.equals(pRSV2ResourceRequest.mVideoMaterialType) && this.mConsumptionType.equals(pRSV2ResourceRequest.mConsumptionType) && this.mResourceParamsJson.equals(pRSV2ResourceRequest.mResourceParamsJson) && this.mForValidation == pRSV2ResourceRequest.mForValidation) {
            return this.mSessionContext.equals(pRSV2ResourceRequest.mSessionContext);
        }
        return false;
    }

    public static class Builder {
        private ConsumptionType mConsumptionType;
        private boolean mForValidation;
        private ResourceParams mResourceParams;
        private PlaybackResourcesResponseListener mResponseListener;
        private Map<String, String> mSessionContext;
        private String mTitleId;
        private VideoMaterialType mVideoMaterialType;

        public Builder titleId(String str) {
            this.mTitleId = str;
            return this;
        }

        public Builder videoMaterialType(VideoMaterialType videoMaterialType) {
            this.mVideoMaterialType = videoMaterialType;
            return this;
        }

        public Builder consumptionType(ConsumptionType consumptionType) {
            this.mConsumptionType = consumptionType;
            return this;
        }

        public Builder params(ResourceParams resourceParams) {
            this.mResourceParams = resourceParams;
            return this;
        }

        public Builder sessionContext(Map<String, String> map) {
            this.mSessionContext = map;
            return this;
        }

        public Builder responseListener(PlaybackResourcesResponseListener playbackResourcesResponseListener) {
            this.mResponseListener = playbackResourcesResponseListener;
            return this;
        }

        public Builder forValidation(boolean z) {
            this.mForValidation = z;
            return this;
        }

        public PRSV2ResourceRequest build() {
            return new PRSV2ResourceRequest(this.mTitleId, this.mVideoMaterialType, this.mConsumptionType, this.mResourceParams, this.mSessionContext, this.mResponseListener, this.mForValidation);
        }
    }
}
