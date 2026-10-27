package com.amazon.avod.media.contentcache;

import androidx.core.os.EnvironmentCompat;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoSpecification;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class CacheRequest {
    private final boolean mIsDeferrable;
    private final boolean mIsEntitledToWatch;
    private final int mPriority;
    private final String mSource;
    private final VideoOptions mVideoOptions;
    private final VideoSpecification mVideoSpec;

    private CacheRequest(Builder builder) {
        this.mVideoSpec = (VideoSpecification) Preconditions.checkNotNull(builder.mVideoSpec);
        this.mVideoOptions = (VideoOptions) Preconditions.checkNotNull(builder.mVideoOptions);
        this.mSource = (String) Preconditions.checkNotNull(builder.mSource);
        this.mIsEntitledToWatch = builder.mIsEntitledToWatch;
        this.mPriority = builder.mPriority;
        this.mIsDeferrable = builder.mIsDeferrable;
    }

    @Nonnull
    public VideoSpecification getVideoSpecification() {
        return this.mVideoSpec;
    }

    @Nonnull
    public VideoOptions getVideoOptions() {
        return this.mVideoOptions;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public boolean isEntitledToWatch() {
        return this.mIsEntitledToWatch;
    }

    @Nonnull
    public String getSource() {
        return this.mSource;
    }

    public boolean isDeferrable() {
        return this.mIsDeferrable;
    }

    public CacheRequest trimToFirstAudioTrack() {
        return asBuilder().setVideoSpecification(this.mVideoSpec.trimToFirstAudioTrack()).build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CacheRequest)) {
            return false;
        }
        CacheRequest cacheRequest = (CacheRequest) obj;
        return Objects.equal(getVideoSpecification(), cacheRequest.getVideoSpecification()) && Objects.equal(Integer.valueOf(getPriority()), Integer.valueOf(cacheRequest.getPriority())) && Objects.equal(Boolean.valueOf(isEntitledToWatch()), Boolean.valueOf(cacheRequest.isEntitledToWatch())) && Objects.equal(getSource(), cacheRequest.getSource()) && Objects.equal(Boolean.valueOf(isDeferrable()), Boolean.valueOf(cacheRequest.isDeferrable())) && Objects.equal(getVideoOptions(), cacheRequest.getVideoOptions());
    }

    public int hashCode() {
        return Objects.hashCode(getVideoSpecification(), getVideoOptions(), getSource(), Boolean.valueOf(isEntitledToWatch()), Integer.valueOf(getPriority()), Boolean.valueOf(isDeferrable()));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mVideoSpec", getVideoSpecification()).add("mVideoOptions", getVideoOptions()).add("mSource", this.mSource).add("mIsEntitledToWatch", this.mIsEntitledToWatch).add("mPriority", this.mPriority).add("mIsDeferrable", this.mIsDeferrable).toString();
    }

    public Builder asBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(@Nonnull CacheRequest cacheRequest) {
        Preconditions.checkNotNull(cacheRequest, "request");
        return new Builder();
    }

    public static class Builder {
        private boolean mIsDeferrable;
        private boolean mIsEntitledToWatch;
        private int mPriority;
        private String mSource;
        private VideoOptions mVideoOptions;
        private VideoSpecification mVideoSpec;

        private Builder() {
            this.mIsEntitledToWatch = false;
            this.mSource = EnvironmentCompat.MEDIA_UNKNOWN;
            this.mPriority = 0;
            this.mIsDeferrable = false;
        }

        private Builder(@Nonnull CacheRequest cacheRequest) {
            this.mIsEntitledToWatch = false;
            this.mSource = EnvironmentCompat.MEDIA_UNKNOWN;
            this.mPriority = 0;
            this.mIsDeferrable = false;
            this.mVideoSpec = cacheRequest.getVideoSpecification();
            this.mVideoOptions = cacheRequest.getVideoOptions();
            this.mIsEntitledToWatch = cacheRequest.isEntitledToWatch();
            this.mSource = cacheRequest.getSource();
            this.mPriority = cacheRequest.getPriority();
            this.mIsDeferrable = cacheRequest.isDeferrable();
        }

        public Builder setVideoSpecification(VideoSpecification videoSpecification) {
            this.mVideoSpec = videoSpecification;
            return this;
        }

        public Builder setVideoOptions(VideoOptions videoOptions) {
            this.mVideoOptions = videoOptions;
            return this;
        }

        public Builder setIsEntitledToWatch(boolean z) {
            this.mIsEntitledToWatch = z;
            return this;
        }

        public CacheRequest build() {
            return new CacheRequest(this);
        }
    }
}
