package com.amazon.avod.playbackresourcev2;

import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackresource.PRSException;
import com.amazon.atvplaybackresource.SyeUrlResponse;
import com.amazon.atvsubtitlepresets.GetPresetsOutput;
import com.amazon.avod.content.config.PlaybackResourcesV2Config;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.playbackresource.PlaybackResources;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResourcesV2 {
    private final Optional<AudioVideoUrls> mAudioVideoUrls;
    private final ImmutableMap<Resource, PRSException> mErrorsByResource;
    private final Optional<PlayReadyLicenseV2> mPlayReadyLicense;
    private final Optional<PlaybackData> mPlaybackData;
    private final Optional<SyeUrlResponse> mSyeUrlResponse;
    protected final String mTitleId;
    private final Optional<WidevineLicenseV2> mWidevineLicenseResource;
    private final Optional<XRayFragmentBaseV2> mXrayMetadata;
    private Optional<GetPresetsOutput> mSubtitlePresets = Optional.absent();
    private final PlaybackResourcesV2Config mPlaybackResourcesV2Config = PlaybackResourcesV2Config.getInstance();

    public static class Builder {
        private AudioVideoUrls mAudioVideoUrls;
        private ImmutableMap<Resource, PRSException> mErrorsByResource = ImmutableMap.of();
        private PlayReadyLicenseV2 mPlayReadyLicense;
        private PlaybackData mPlaybackData;
        private SyeUrlResponse mSyeUrlResponse;
        private String mTitleId;
        private WidevineLicenseV2 mWidevineLicenseResource;
        private XRayFragmentBaseV2 mXrayMetadata;

        public Builder withAudioVideoUrls(@Nonnull AudioVideoUrls audioVideoUrls) {
            this.mAudioVideoUrls = audioVideoUrls;
            return this;
        }

        public Builder withPlaybackData(@Nonnull PlaybackData playbackData) {
            this.mPlaybackData = playbackData;
            return this;
        }

        public Builder withPlayReadyLicense(@Nonnull PlayReadyLicenseV2 playReadyLicenseV2) {
            this.mPlayReadyLicense = playReadyLicenseV2;
            return this;
        }

        public Builder withWidevineLicense(@Nonnull WidevineLicenseV2 widevineLicenseV2) {
            this.mWidevineLicenseResource = widevineLicenseV2;
            return this;
        }

        public Builder withSyeUrls(@Nonnull SyeUrlResponse syeUrlResponse) {
            this.mSyeUrlResponse = syeUrlResponse;
            return this;
        }

        public Builder withTitleId(@Nonnull String str) {
            this.mTitleId = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public Builder withXrayMetadata(@Nonnull XRayFragmentBaseV2 xRayFragmentBaseV2) {
            this.mXrayMetadata = (XRayFragmentBaseV2) Preconditions.checkNotNull(xRayFragmentBaseV2);
            return this;
        }

        public Builder withErrorsByResource(@Nonnull ImmutableMap<Resource, PRSException> immutableMap) {
            this.mErrorsByResource = (ImmutableMap) Preconditions.checkNotNull(immutableMap);
            return this;
        }

        public PlaybackResourcesV2 build() {
            return new PlaybackResourcesV2(Optional.fromNullable(this.mAudioVideoUrls), Optional.fromNullable(this.mPlaybackData), Optional.fromNullable(this.mPlayReadyLicense), Optional.fromNullable(this.mWidevineLicenseResource), Optional.fromNullable(this.mSyeUrlResponse), (String) Preconditions.checkNotNull(this.mTitleId), Optional.fromNullable(this.mXrayMetadata), this.mErrorsByResource);
        }
    }

    public PlaybackResourcesV2(@Nonnull Optional<AudioVideoUrls> optional, @Nonnull Optional<PlaybackData> optional2, @Nonnull Optional<PlayReadyLicenseV2> optional3, @Nonnull Optional<WidevineLicenseV2> optional4, @Nonnull Optional<SyeUrlResponse> optional5, @Nonnull String str, @Nonnull Optional<XRayFragmentBaseV2> optional6, @Nonnull ImmutableMap<Resource, PRSException> immutableMap) {
        this.mAudioVideoUrls = (Optional) Preconditions.checkNotNull(optional, "audioVideoUrls");
        this.mPlaybackData = (Optional) Preconditions.checkNotNull(optional2, "playbackData");
        this.mPlayReadyLicense = (Optional) Preconditions.checkNotNull(optional3, "playReadyLicense");
        this.mWidevineLicenseResource = (Optional) Preconditions.checkNotNull(optional4, "widevineLicenseResource");
        this.mSyeUrlResponse = (Optional) Preconditions.checkNotNull(optional5, "syeUrlResponse");
        this.mXrayMetadata = (Optional) Preconditions.checkNotNull(optional6, "xRayMetadata");
        this.mTitleId = (String) Preconditions.checkNotNull(str, "titleId");
        this.mErrorsByResource = (ImmutableMap) Preconditions.checkNotNull(immutableMap, "errorsByResource");
    }

    @Nonnull
    public Optional<AudioVideoUrls> getAudioVideoUrls() {
        return this.mAudioVideoUrls;
    }

    @Nonnull
    public Optional<PlayReadyLicenseV2> getPlayReadyLicense() {
        return this.mPlayReadyLicense;
    }

    @Nonnull
    public Optional<WidevineLicenseV2> getWidevineLicenseResource() {
        return this.mWidevineLicenseResource;
    }

    private void setSubtitlePresets(@Nonnull Optional<GetPresetsOutput> optional) {
        this.mSubtitlePresets = (Optional) Preconditions.checkNotNull(optional, "subtitlePresets");
    }

    @Nonnull
    public ImmutableMap<Resource, PRSException> getErrorsByResource() {
        return this.mErrorsByResource;
    }

    public PlaybackResourcesV2 update(@Nonnull PlaybackResources playbackResources) {
        Preconditions.checkNotNull(playbackResources, "playbackResourcesV1");
        Iterator<Resource> it = this.mPlaybackResourcesV2Config.getPRSV2UnsupportedResourceSet().iterator();
        while (it.hasNext()) {
            if (it.next() == Resource.SubtitlePresets) {
                setSubtitlePresets(playbackResources.getSubtitlePresets());
            }
        }
        return this;
    }
}
