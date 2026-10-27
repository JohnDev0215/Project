package com.amazon.avod.media.service.cache;

import com.amazon.atvplaybackdevice.types.ConsumptionType;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playbackclient.utils.VideoMaterialTypeUtils;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerResourcesCacheRequest {

    @Nullable
    private final String mAudioTrackId;

    @Nonnull
    private final ConsumptionType mConsumptionType;
    private final ContentSessionType mContentSessionType;

    @Nonnull
    private final EPrivacyConsentData mEPrivacyConsent;
    private final boolean mHasPlaybackBeenInvoked;

    @Nullable
    private final PlaybackEnvelope mPlaybackEnvelope;

    @Nullable
    private final String mPlaybackToken;
    private final Map<String, String> mSessionContext;
    private final boolean mShowAds;
    private final String mTitleId;
    private final VideoMaterialType mVideoMaterialType;

    public PlayerResourcesCacheRequest(@Nonnull VideoSpecification videoSpecification, @Nonnull ConsumptionType consumptionType, @Nonnull Map<String, String> map) {
        this(videoSpecification, consumptionType, map, ContentSessionType.CONTENT_CACHE, false);
    }

    public PlayerResourcesCacheRequest(@Nonnull VideoSpecification videoSpecification, @Nonnull ConsumptionType consumptionType, @Nonnull Map<String, String> map, @Nonnull ContentSessionType contentSessionType, boolean z) {
        this(((VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification")).getTitleId(), VideoMaterialTypeUtils.fromPlayersContentType(videoSpecification.getContentType()), videoSpecification.getPrimaryAudioTrackId(), videoSpecification.getPlaybackToken(), consumptionType, videoSpecification.getPlaybackEnvelope(), (Map) Preconditions.checkNotNull(map, "sessionContext"), (ContentSessionType) Preconditions.checkNotNull(contentSessionType, "contentSessionType"), z, videoSpecification.shouldShowAds(), videoSpecification.getEPrivacyConsent());
    }

    public PlayerResourcesCacheRequest(@Nonnull String str, @Nonnull VideoMaterialType videoMaterialType, @Nullable String str2, @Nullable String str3, @Nonnull ConsumptionType consumptionType, @Nullable PlaybackEnvelope playbackEnvelope, @Nonnull Map<String, String> map, @Nonnull ContentSessionType contentSessionType, boolean z, boolean z2, @Nonnull EPrivacyConsentData ePrivacyConsentData) {
        this.mTitleId = (String) Preconditions.checkNotNull(str, "titleId");
        this.mVideoMaterialType = (VideoMaterialType) Preconditions.checkNotNull(videoMaterialType, "videoMaterialType");
        this.mAudioTrackId = str2;
        this.mPlaybackToken = str3;
        this.mPlaybackEnvelope = playbackEnvelope;
        this.mConsumptionType = (ConsumptionType) Preconditions.checkNotNull(consumptionType, "consumptionType");
        this.mSessionContext = (Map) Preconditions.checkNotNull(map, "sessionContext");
        this.mContentSessionType = (ContentSessionType) Preconditions.checkNotNull(contentSessionType, "contentSessionType");
        this.mHasPlaybackBeenInvoked = z;
        this.mShowAds = z2;
        this.mEPrivacyConsent = (EPrivacyConsentData) Preconditions.checkNotNull(ePrivacyConsentData, "ePrivacyConsent");
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
    public Map<String, String> getSessionContext() {
        return this.mSessionContext;
    }

    @Nullable
    public String getAudioTrackId() {
        return this.mAudioTrackId;
    }

    @Nullable
    public String getPlaybackToken() {
        return this.mPlaybackToken;
    }

    @Nonnull
    public ContentSessionType getContentSessionType() {
        return this.mContentSessionType;
    }

    public boolean hasPlaybackBeenInvoked() {
        return this.mHasPlaybackBeenInvoked;
    }

    @Nonnull
    public ConsumptionType getConsumptionType() {
        return this.mConsumptionType;
    }

    public boolean shouldShowAds() {
        return this.mShowAds;
    }

    @Nonnull
    public EPrivacyConsentData getEPrivacyConsent() {
        return this.mEPrivacyConsent;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayerResourcesCacheRequest)) {
            return false;
        }
        PlayerResourcesCacheRequest playerResourcesCacheRequest = (PlayerResourcesCacheRequest) obj;
        if (this.mTitleId.equals(playerResourcesCacheRequest.mTitleId) && this.mVideoMaterialType == playerResourcesCacheRequest.mVideoMaterialType && this.mSessionContext.equals(playerResourcesCacheRequest.mSessionContext) && Objects.equals(this.mPlaybackToken, playerResourcesCacheRequest.mPlaybackToken) && Objects.equals(this.mPlaybackEnvelope, playerResourcesCacheRequest.mPlaybackEnvelope) && Objects.equals(this.mConsumptionType, playerResourcesCacheRequest.mConsumptionType)) {
            return Objects.equals(this.mAudioTrackId, playerResourcesCacheRequest.mAudioTrackId);
        }
        return false;
    }

    public int hashCode() {
        return com.google.common.base.Objects.hashCode(this.mTitleId, this.mVideoMaterialType, this.mSessionContext, this.mPlaybackToken, this.mPlaybackEnvelope, this.mConsumptionType, this.mAudioTrackId);
    }

    @Nonnull
    public String toString() {
        return MoreObjects.toStringHelper(this).add("titleId", this.mTitleId).add("videoMaterialType", this.mVideoMaterialType).add("playbackToken", this.mPlaybackToken).add("playbackEnvelope", this.mPlaybackEnvelope).add("sessionContext", this.mSessionContext).add("contentSessionType", this.mContentSessionType).add("hasPlaybackBeenInvoked", this.mHasPlaybackBeenInvoked).add("audioTrackId", this.mAudioTrackId).add("consumptionType", this.mConsumptionType).toString();
    }
}
