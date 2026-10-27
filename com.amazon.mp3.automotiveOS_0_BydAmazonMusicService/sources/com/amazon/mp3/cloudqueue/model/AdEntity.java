package com.amazon.mp3.cloudqueue.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.mp3.cloudqueue.SynchronizedPlayableEntity;
import com.amazon.musicplayqueueservice.client.v2.track.AdMetadata;
import com.amazon.musicplayqueueservice.client.v2.track.ClientMetricsInfo;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class AdEntity extends SynchronizedPlayableEntity {
    private final AdMetadata mAdMetadata;

    @Nullable
    public String getAdAudioUrl() {
        if (this.mAdMetadata.getAudioDetailOptions().size() > 0) {
            return this.mAdMetadata.getAudioDetailOptions().get(0).getStreamingUrl();
        }
        return null;
    }

    @Nullable
    public String getAdImageUrl() {
        if (this.mAdMetadata.getVisualDetailOptions().size() > 0) {
            return this.mAdMetadata.getVisualDetailOptions().get(0).getImageUrl();
        }
        return null;
    }

    @Nullable
    public String getAdClickUrl() {
        if (this.mAdMetadata.getVisualDetailOptions().size() > 0) {
            return this.mAdMetadata.getVisualDetailOptions().get(0).getClickThroughURL();
        }
        return null;
    }

    @Nullable
    public String getProductName() {
        return this.mAdMetadata.getProductName();
    }

    @Nullable
    public String getAdName() {
        return this.mAdMetadata.getAdvertiserName();
    }

    public String getAdId() {
        return getAdAudioUrl();
    }

    public AdEntity(AdMetadata adMetadata) {
        this.mAdMetadata = adMetadata;
        processActionMap(adMetadata.getCapabilities());
    }

    @NonNull
    public String getBlockRef() {
        ClientMetricsInfo clientMetricsInfo = this.mAdMetadata.getClientMetricsInfo();
        return clientMetricsInfo != null ? clientMetricsInfo.getContentBlockRef() : "";
    }

    public Map<String, String> getMetricsContext() {
        return this.mAdMetadata.getMetricsContext();
    }

    public long getDurationInSeconds() {
        return (long) this.mAdMetadata.getDurationInSeconds();
    }
}
