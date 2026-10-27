package com.amazon.avod.media.service;

import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.prs.AdditionalParams;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerResourcesAndParams implements AdditionalParams {
    private final AdvertisingIdCollector mAdvertisingIdCollector;
    private final Optional<String> mAudioTrackId;
    private final ConsumptionType mConsumptionType;
    private final ContentType mContentType;
    private final ImmutableSet<Resource> mDesiredResources;
    private final EPrivacyConsentData mEPrivacyConsent;
    private final boolean mForceDash;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private final PlaybackSupportEvaluator mPlaybackSupportEvaluator;
    private final String mPlaybackToken;
    private final Map<String, String> mPostBodyParamsMap;
    private final Map<String, String> mQueryStringMap;
    private final RendererSchemeController mRendererSchemeController;
    private final RendererSchemeType mRendererSchemeType;
    private final boolean mShowAds;
    private final String mTitleId;
    private final Optional<String> mUserWatchSessionId;
    private final Optional<String> mVersion;

    @Override // com.amazon.avod.prs.AdditionalParams
    public boolean hasQueryStringParams() {
        return true;
    }

    public PlayerResourcesAndParams(@Nonnull String str, @Nonnull ContentType contentType, @Nullable String str2, @Nullable String str3, @Nonnull ImmutableSet<Resource> immutableSet, boolean z, @Nonnull EPrivacyConsentData ePrivacyConsentData) {
        this(str, Optional.fromNullable(str2), ConsumptionType.Streaming, contentType, Optional.absent(), Optional.absent(), null, false, PlayerResourcesExternalParamHolder.getPlaybackSupportEvaluator(), null, str3, ePrivacyConsentData, PlayerResourcesExternalParamHolder.getAdvertisingIdCollector(), immutableSet, PlayerResourcesExternalParamHolder.getRendererSchemeController(), z);
    }

    public PlayerResourcesAndParams(@Nonnull String str, @Nonnull Optional<String> optional, @Nonnull ConsumptionType consumptionType, @Nonnull ContentType contentType, @Nonnull Optional<String> optional2, @Nonnull Optional<String> optional3, @Nullable PlaybackEventReporter playbackEventReporter, boolean z, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nullable RendererSchemeType rendererSchemeType, @Nullable String str2, @Nonnull EPrivacyConsentData ePrivacyConsentData, @Nonnull ImmutableSet<Resource> immutableSet, boolean z2) {
        this(str, optional, consumptionType, contentType, optional2, optional3, playbackEventReporter, z, playbackSupportEvaluator, rendererSchemeType, str2, ePrivacyConsentData, PlayerResourcesExternalParamHolder.getAdvertisingIdCollector(), immutableSet, PlayerResourcesExternalParamHolder.getRendererSchemeController(), z2);
    }

    PlayerResourcesAndParams(@Nonnull String str, @Nullable Optional<String> optional, @Nonnull ConsumptionType consumptionType, @Nonnull ContentType contentType, @Nullable Optional<String> optional2, @Nullable Optional<String> optional3, @Nullable PlaybackEventReporter playbackEventReporter, boolean z, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nullable RendererSchemeType rendererSchemeType, @Nullable String str2, @Nonnull EPrivacyConsentData ePrivacyConsentData, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull ImmutableSet<Resource> immutableSet, @Nonnull RendererSchemeController rendererSchemeController, boolean z2) {
        this.mTitleId = (String) Preconditions.checkNotNull(str, "titleId");
        this.mAudioTrackId = optional;
        this.mConsumptionType = (ConsumptionType) Preconditions.checkNotNull(consumptionType, "consumptionType");
        this.mContentType = (ContentType) Preconditions.checkNotNull(contentType, "contentType");
        this.mUserWatchSessionId = optional2;
        this.mVersion = optional3;
        this.mPlaybackEventReporter = playbackEventReporter;
        this.mForceDash = z;
        this.mPlaybackSupportEvaluator = (PlaybackSupportEvaluator) Preconditions.checkNotNull(playbackSupportEvaluator, "playbackSupportEvaluator");
        this.mRendererSchemeType = rendererSchemeType;
        this.mPlaybackToken = str2;
        this.mEPrivacyConsent = (EPrivacyConsentData) Preconditions.checkNotNull(ePrivacyConsentData, "ePrivacyConsent");
        this.mPostBodyParamsMap = buildPostBodyParams();
        this.mAdvertisingIdCollector = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector");
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
        this.mDesiredResources = (ImmutableSet) Preconditions.checkNotNull(immutableSet, "desiredResources");
        this.mShowAds = z2;
        this.mQueryStringMap = buildQueryStringParams();
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    public boolean hasPostBodyParams() {
        return !Strings.isNullOrEmpty(this.mPlaybackToken);
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    @Nonnull
    public Map<String, String> getPostBodyParams() {
        return this.mPostBodyParamsMap;
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    @Nonnull
    public Map<String, String> getQueryStringParams() {
        return this.mQueryStringMap;
    }

    @Nonnull
    public Set<Resource> getDesiredResourcesSet() {
        return this.mDesiredResources;
    }

    private Map<String, String> buildQueryStringParams() {
        ImmutableMap.Builder builder;
        ImmutableMap.Builder builder2 = ImmutableMap.builder();
        RendererSchemeType schemeType = this.mRendererSchemeController.getRendererScheme(this.mRendererSchemeType, null).getSchemeType();
        if (this.mDesiredResources.contains(Resource.PlaybackUrls)) {
            builder = builder2;
            builder.putAll(new PlaybackUrlsInformation(this.mTitleId, this.mAudioTrackId, this.mConsumptionType, this.mContentType, this.mUserWatchSessionId, this.mVersion, this.mAdvertisingIdCollector, this.mPlaybackEventReporter, this.mForceDash, this.mPlaybackSupportEvaluator, schemeType, this.mPlaybackToken, this.mShowAds, this.mEPrivacyConsent).getQueryStringParams());
        } else {
            builder = builder2;
        }
        if (this.mDesiredResources.contains(Resource.SubtitleUrls)) {
            builder.put("subtitleFormat", "TTMLv2");
        }
        return builder.build();
    }

    private Map<String, String> buildPostBodyParams() {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        if (!Strings.isNullOrEmpty(this.mPlaybackToken)) {
            builder.put("playbackToken", this.mPlaybackToken);
        }
        return builder.build();
    }
}
