package com.amazon.avod.content.urlvending;

import com.amazon.avod.media.framework.config.TimeShiftPolicyConfig;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AudioVideoUrlsBuilder {
    private ImmutableList<AudioTrackMetadata> mAudioTrackMetadataList;
    private ImmutableList<ContentUrl> mContentUrls;
    private String mEncodeId;
    private FailoverManager mFailoverManager;
    private Map<String, String> mPlaybackSettingsOverride;
    private ReturnedTitleRendition mReturnedTitleRendition;
    private String mSessionToken;
    private TimeShiftPolicyConfig mTimeShiftPolicyConfig = TimeShiftPolicyConfig.getInstance();
    private CuepointPlaylistInfo mCuepointPlaylistInfo = null;
    private String mAuxCacheKey = null;

    public AudioVideoUrlsBuilder() {
    }

    public AudioVideoUrlsBuilder(@Nonnull AudioVideoUrls audioVideoUrls) {
        Preconditions.checkNotNull(audioVideoUrls, "audioVideoUrls");
        setContentUrls(audioVideoUrls.getContentUrls());
        setAudioTrackMetadataList(audioVideoUrls.getAudioTrackMetadataList());
        setEncodeId(audioVideoUrls.getEncodeId());
        setPlaybackSettingsOverride(audioVideoUrls.getPlaybackSettingsOverride());
        setFailoverManager(audioVideoUrls.getFailoverManager());
        setReturnedTitleRendition(audioVideoUrls.getReturnedTitleRendition());
        setTimeShiftPolicyConfig(audioVideoUrls.getTimeShiftPolicyConfig());
        setCuepointPlaylistInfo(audioVideoUrls.getCuepointPlaylistInfo());
        setAuxCacheKey(audioVideoUrls.getAuxCacheKey());
        setSessionToken(audioVideoUrls.getSessionToken());
    }

    @Nonnull
    public AudioVideoUrlsBuilder setContentUrls(@Nonnull ImmutableList<ContentUrl> immutableList) {
        this.mContentUrls = (ImmutableList) Preconditions.checkNotNull(immutableList, "contentUrls");
        Preconditions.checkState(!immutableList.isEmpty(), "ContentUrls cannot be empty.");
        return this;
    }

    @Nonnull
    public AudioVideoUrlsBuilder setAudioTrackMetadataList(@Nonnull ImmutableList<AudioTrackMetadata> immutableList) {
        this.mAudioTrackMetadataList = (ImmutableList) Preconditions.checkNotNull(immutableList, "audioTrackMetadataList");
        return this;
    }

    @Nonnull
    public AudioVideoUrlsBuilder setEncodeId(@Nullable String str) {
        this.mEncodeId = str;
        return this;
    }

    @Nonnull
    public AudioVideoUrlsBuilder setPlaybackSettingsOverride(@Nonnull Map<String, String> map) {
        this.mPlaybackSettingsOverride = (Map) Preconditions.checkNotNull(map, "playbackSettingsOverride");
        return this;
    }

    @Nonnull
    public AudioVideoUrlsBuilder setFailoverManager(@Nullable FailoverManager failoverManager) {
        this.mFailoverManager = failoverManager;
        return this;
    }

    @Nonnull
    public AudioVideoUrlsBuilder setReturnedTitleRendition(@Nullable ReturnedTitleRendition returnedTitleRendition) {
        this.mReturnedTitleRendition = returnedTitleRendition;
        return this;
    }

    @Nonnull
    private AudioVideoUrlsBuilder setTimeShiftPolicyConfig(@Nonnull TimeShiftPolicyConfig timeShiftPolicyConfig) {
        this.mTimeShiftPolicyConfig = (TimeShiftPolicyConfig) Preconditions.checkNotNull(timeShiftPolicyConfig, "timeShiftPolicyConfig");
        return this;
    }

    @Nonnull
    public AudioVideoUrlsBuilder setCuepointPlaylistInfo(@Nullable CuepointPlaylistInfo cuepointPlaylistInfo) {
        this.mCuepointPlaylistInfo = cuepointPlaylistInfo;
        return this;
    }

    @Nonnull
    public AudioVideoUrlsBuilder setAuxCacheKey(@Nullable String str) {
        this.mAuxCacheKey = str;
        return this;
    }

    @Nonnull
    public AudioVideoUrlsBuilder setSessionToken(@Nullable String str) {
        this.mSessionToken = str;
        return this;
    }

    @Nonnull
    public AudioVideoUrls build() {
        return new AudioVideoUrls(this.mContentUrls, this.mAudioTrackMetadataList, this.mEncodeId, this.mPlaybackSettingsOverride, this.mFailoverManager, this.mReturnedTitleRendition, this.mTimeShiftPolicyConfig, this.mCuepointPlaylistInfo, this.mAuxCacheKey, this.mSessionToken);
    }
}
