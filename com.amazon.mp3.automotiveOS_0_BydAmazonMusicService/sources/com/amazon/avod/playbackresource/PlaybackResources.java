package com.amazon.avod.playbackresource;

import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackresource.PRSException;
import com.amazon.atvplaybackresource.PlayReadyLicense;
import com.amazon.atvplaybackresource.PlaybackSettings;
import com.amazon.atvplaybackresource.ResponseTitleRendition;
import com.amazon.atvplaybackresource.SyeUrlResponse;
import com.amazon.atvplaybackresource.types.EntitlementType;
import com.amazon.atvplaybackresource.widevine.WidevineLicenseResource;
import com.amazon.atvsubtitlepresets.GetPresetsOutput;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.core.Subtitle;
import com.amazon.avod.xray.XRayFragmentBase;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResources {
    protected final Optional<AudioVideoUrls> mAudioVideoUrls;
    protected final Optional<CuepointPlaylist> mCuepointPlaylist;
    protected final Optional<EntitlementType> mEntitlementType;
    protected final Optional<PRSException> mError;
    protected final ImmutableMap<Resource, PRSException> mErrorsByResource;
    protected final ImmutableList<Subtitle> mForcedNarratives;
    protected final Optional<SyeUrlResponse> mGetSyeUrlResponse;
    protected final boolean mIsEntitled;
    protected final Optional<PlayReadyLicense> mPlayReadyLicense;
    protected final Optional<PlaybackSettings> mPlaybackSettings;
    protected final Optional<ResponseTitleRendition> mResponseTitleRendition;
    protected final Optional<GetPresetsOutput> mSubtitlePresets;
    protected final ImmutableList<Subtitle> mSubtitles;
    protected final ImmutableMultimap<String, String> mTrickplayUrls;
    protected final Optional<WidevineLicenseResource> mWidevineLicenseResource;
    protected final Optional<XRayFragmentBase> mXRayMetadata;

    PlaybackResources(@Nonnull Optional<EntitlementType> optional, @Nonnull Optional<PRSException> optional2, @Nonnull ImmutableList<Subtitle> immutableList, @Nonnull ImmutableList<Subtitle> immutableList2, @Nonnull ImmutableMultimap<String, String> immutableMultimap, boolean z, @Nonnull Optional<GetPresetsOutput> optional3, @Nonnull Optional<CuepointPlaylist> optional4, @Nonnull Optional<AudioVideoUrls> optional5, @Nonnull Optional<PlayReadyLicense> optional6, @Nonnull Optional<WidevineLicenseResource> optional7, @Nonnull Optional<SyeUrlResponse> optional8, @Nonnull Optional<PlaybackSettings> optional9, @Nonnull ImmutableMap<Resource, PRSException> immutableMap, @Nonnull Optional<ResponseTitleRendition> optional10, @Nonnull Optional<XRayFragmentBase> optional11) {
        this.mEntitlementType = (Optional) Preconditions.checkNotNull(optional, "entitlementType");
        this.mError = (Optional) Preconditions.checkNotNull(optional2, "error");
        this.mSubtitles = (ImmutableList) Preconditions.checkNotNull(immutableList, "subtitles");
        this.mForcedNarratives = (ImmutableList) Preconditions.checkNotNull(immutableList2, "forcedNarratives");
        this.mTrickplayUrls = (ImmutableMultimap) Preconditions.checkNotNull(immutableMultimap, "trickplayUrls");
        this.mIsEntitled = z;
        this.mSubtitlePresets = (Optional) Preconditions.checkNotNull(optional3, "subtitlePresets");
        this.mCuepointPlaylist = (Optional) Preconditions.checkNotNull(optional4, "cuepointPlaylist");
        this.mAudioVideoUrls = (Optional) Preconditions.checkNotNull(optional5, "audioVideoUrls");
        this.mPlayReadyLicense = (Optional) Preconditions.checkNotNull(optional6, "playReadyLicense");
        this.mWidevineLicenseResource = (Optional) Preconditions.checkNotNull(optional7, "widevineLicenseResource");
        this.mGetSyeUrlResponse = (Optional) Preconditions.checkNotNull(optional8, "syeUrls");
        this.mPlaybackSettings = (Optional) Preconditions.checkNotNull(optional9, "playbackSettings");
        this.mErrorsByResource = (ImmutableMap) Preconditions.checkNotNull(immutableMap, "errorsByResource");
        this.mResponseTitleRendition = (Optional) Preconditions.checkNotNull(optional10, "responseTitleRendition");
        this.mXRayMetadata = (Optional) Preconditions.checkNotNull(optional11, "xRayMetadata");
    }

    @Nonnull
    public Optional<EntitlementType> getEntitlementType() {
        return this.mEntitlementType;
    }

    @Deprecated
    public boolean hasError() {
        return this.mError.isPresent();
    }

    @Nonnull
    @Deprecated
    public Optional<PRSException> getError() {
        return this.mError;
    }

    @Nonnull
    public ImmutableMultimap<String, String> getTrickplayUrls() {
        return this.mTrickplayUrls;
    }

    @Nonnull
    public ImmutableList<Subtitle> getForcedNarratives() {
        return this.mForcedNarratives;
    }

    @Nonnull
    public ImmutableList<Subtitle> getSubtitles() {
        return this.mSubtitles;
    }

    public boolean isEntitled() {
        return this.mIsEntitled;
    }

    @Nonnull
    public Optional<GetPresetsOutput> getSubtitlePresets() {
        return this.mSubtitlePresets;
    }

    @Nonnull
    public Optional<CuepointPlaylist> getCuepointPlaylist() {
        return this.mCuepointPlaylist;
    }

    @Nonnull
    public Optional<AudioVideoUrls> getAudioVideoUrls() {
        return this.mAudioVideoUrls;
    }

    @Nonnull
    public Optional<PlayReadyLicense> getPlayReadyLicense() {
        return this.mPlayReadyLicense;
    }

    @Nonnull
    public Optional<WidevineLicenseResource> getWidevineLicenseResource() {
        return this.mWidevineLicenseResource;
    }

    @Nonnull
    public Optional<SyeUrlResponse> getSyeUrls() {
        return this.mGetSyeUrlResponse;
    }

    @Nonnull
    public ImmutableMap<Resource, PRSException> getErrorsByResource() {
        return this.mErrorsByResource;
    }

    @Nonnull
    public Optional<ResponseTitleRendition> getResponseTitleRendition() {
        return this.mResponseTitleRendition;
    }
}
