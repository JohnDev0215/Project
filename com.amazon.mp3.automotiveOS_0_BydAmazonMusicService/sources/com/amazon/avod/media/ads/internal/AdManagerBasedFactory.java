package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.VideoPresentationFactory;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class AdManagerBasedFactory {
    private final AdsConfig mAdsConfig;
    private final AndroidVideoPlayerV2Cache mAndroidVideoPlayerV2Cache;
    private final ExecutorService mExecutor;
    private final AdClipMediaFileChooser mMediaFileChooser;

    @Nonnull
    private final QOSCommunicationService mQOSCommunicationService;
    private final AdUriProxy mUriProxy;
    private final VideoPresentationFactory mVideoPresentationFactory;

    AdManagerBasedFactory(VideoPresentationFactory videoPresentationFactory, ExecutorService executorService, AdUriProxy adUriProxy, AdsConfig adsConfig, AdClipMediaFileChooser adClipMediaFileChooser, @Nonnull QOSCommunicationService qOSCommunicationService, @Nonnull AndroidVideoPlayerV2Cache androidVideoPlayerV2Cache) {
        this.mVideoPresentationFactory = (VideoPresentationFactory) Preconditions.checkNotNull(videoPresentationFactory);
        this.mExecutor = executorService;
        this.mUriProxy = adUriProxy;
        this.mAdsConfig = adsConfig;
        this.mMediaFileChooser = adClipMediaFileChooser;
        this.mQOSCommunicationService = (QOSCommunicationService) Preconditions.checkNotNull(qOSCommunicationService, "QOSCommunicationService");
        this.mAndroidVideoPlayerV2Cache = (AndroidVideoPlayerV2Cache) Preconditions.checkNotNull(androidVideoPlayerV2Cache, "exoPlayerCache");
    }

    AdManagerBasedAdPlan newAdPlan(@Nullable String str, boolean z) {
        return this.mAdsConfig.isSSAIEnabled() ? new ServerInsertedAdManagerBasedAdPlan(this, this.mExecutor, str, z, this.mQOSCommunicationService) : new AdManagerBasedAdPlan(this, this.mExecutor, str, z, this.mQOSCommunicationService);
    }

    AdManagerBasedAdBreak newAdBreak() {
        return new AdManagerBasedAdBreak(this, this.mExecutor, this.mMediaFileChooser, this.mQOSCommunicationService);
    }

    AdManagerBasedAdClip newAdClip() {
        return new AdManagerBasedAdClip(this.mVideoPresentationFactory, this.mExecutor, this.mUriProxy, this.mAdsConfig, this.mMediaFileChooser, this.mQOSCommunicationService, this.mAndroidVideoPlayerV2Cache);
    }
}
