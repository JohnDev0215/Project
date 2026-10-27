package com.amazon.avod.media.service;

import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
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
public class LicenseRequestResourcesAndParams implements AdditionalParams {
    private final String mBase64EncodedChallenge;
    private final ConsumptionType mConsumptionType;
    private final ContentType mContentType;
    private final Resource mDesiredResource;
    private final boolean mIsDash;
    private final boolean mIsLicenseRefresh;
    private final boolean mIsSecondLicenseRequest;
    private final String mKeyId;
    private final PlaybackSupportEvaluator mPlaybackSupportEvaluator;
    private final String mPlaybackToken;
    private final Map<String, String> mPostBodyParamsMap = buildPostBodyParams();
    private final Map<String, String> mQueryStringMap = buildQueryStringParams();
    private final RendererSchemeType mRendererSchemeType;
    private final String mTitleId;

    @Override // com.amazon.avod.prs.AdditionalParams
    public boolean hasPostBodyParams() {
        return true;
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    public boolean hasQueryStringParams() {
        return true;
    }

    public LicenseRequestResourcesAndParams(@Nonnull String str, @Nonnull ConsumptionType consumptionType, @Nonnull ContentType contentType, @Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull RendererSchemeType rendererSchemeType, @Nonnull String str2, @Nonnull Resource resource, @Nullable String str3, @Nullable String str4, boolean z, boolean z2, boolean z3) {
        this.mTitleId = (String) Preconditions.checkNotNull(str, "titleId");
        this.mConsumptionType = (ConsumptionType) Preconditions.checkNotNull(consumptionType, "consumptionType");
        this.mContentType = (ContentType) Preconditions.checkNotNull(contentType, "contentType");
        this.mPlaybackSupportEvaluator = (PlaybackSupportEvaluator) Preconditions.checkNotNull(playbackSupportEvaluator, "playbackSupportEvaluator");
        this.mRendererSchemeType = (RendererSchemeType) Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        this.mBase64EncodedChallenge = (String) Preconditions.checkNotNull(str2, "base64EncodedChallenge");
        this.mDesiredResource = (Resource) Preconditions.checkNotNull(resource, "desiredResources");
        this.mPlaybackToken = str3;
        this.mKeyId = str4;
        this.mIsDash = z;
        this.mIsLicenseRefresh = z2;
        this.mIsSecondLicenseRequest = z3;
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

    private Map<String, String> buildQueryStringParams() {
        String str;
        ImmutableMap.Builder builder = ImmutableMap.builder();
        builder.putAll(new PlaybackUrlsInformation(this.mTitleId, (Optional<String>) Optional.absent(), this.mConsumptionType, this.mContentType, (Optional<String>) Optional.absent(), (Optional<String>) Optional.absent(), (AdvertisingIdCollector) null, (PlaybackEventReporter) null, this.mPlaybackSupportEvaluator, this.mRendererSchemeType, this.mPlaybackToken, EPrivacyConsentData.EMPTY, this.mIsLicenseRefresh, this.mIsDash).getQueryStringParams());
        if (this.mDesiredResource == Resource.PlayReadyLicense && (str = this.mKeyId) != null) {
            builder.put("playReadyKeyId", str);
        }
        builder.put("isSecondLicenseRequest", Boolean.toString(this.mIsSecondLicenseRequest));
        return builder.build();
    }

    private Map<String, String> buildPostBodyParams() {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        if (!Strings.isNullOrEmpty(this.mPlaybackToken)) {
            builder.put("playbackToken", this.mPlaybackToken);
        }
        if (this.mDesiredResource == Resource.PlayReadyLicense) {
            builder.put("playReadyChallenge", this.mBase64EncodedChallenge);
        }
        if (this.mDesiredResource == Resource.Widevine2License) {
            builder.put("widevine2Challenge", this.mBase64EncodedChallenge);
        }
        return builder.build();
    }

    @Nonnull
    public Set<Resource> getDesiredResourcesSet() {
        return ImmutableSet.of(this.mDesiredResource);
    }
}
