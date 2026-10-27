package com.amazon.avod.media.guice;

import android.content.Context;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.drm.playready.PlayReadyLicensingService;
import com.amazon.avod.drm.widevine.WidevineLicensingService;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.internal.PrioritizingDownloadService;
import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.platform.AndroidPlatform;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.platform.power.AndroidLockFactory;
import com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.FirstMatchVideoPresentationFactory;
import com.amazon.avod.media.playback.VideoPresentationFactory;
import com.amazon.avod.media.playback.android.AndroidVideoPresentationFactory;
import com.amazon.avod.media.playback.image.ImageVideoPresentationFactory;
import com.amazon.avod.media.playback.support.CachingPlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.ConfigurablePlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.profiling.PerfTracerMediaProfiler;
import com.amazon.avod.media.service.AVODContentUrlFetcher;
import com.amazon.avod.media.service.PlayReadyAvodLicensingService;
import com.amazon.avod.media.service.WidevineAvodLicensingService;
import com.amazon.avod.media.service.playbackoptimizationservice.DefaultHeuristicsConfigurationSynchronizer;
import com.amazon.avod.playback.config.PlayerLifecycleConfig;
import com.amazon.avod.playback.session.AdEnabledVideoPresentationFactory;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public class MediaModule_Dagger {
    MediaComponent getHeuristicsConfigurationSynchronizerAsMediaComponent(DefaultHeuristicsConfigurationSynchronizer defaultHeuristicsConfigurationSynchronizer) {
        return defaultHeuristicsConfigurationSynchronizer;
    }

    VideoPresentationFactory provideAdEnabledVideoPresentationFactory(AdEnabledVideoPresentationFactory adEnabledVideoPresentationFactory) {
        return adEnabledVideoPresentationFactory;
    }

    VideoPresentationFactory provideAndroidVideoPresentationFactory(AndroidVideoPresentationFactory androidVideoPresentationFactory) {
        return androidVideoPresentationFactory;
    }

    ContentUrlFetcher provideContentUrlFetcher(AVODContentUrlFetcher aVODContentUrlFetcher) {
        return aVODContentUrlFetcher;
    }

    FileSystem provideFileSystem(AndroidPlatform androidPlatform) {
        return androidPlatform;
    }

    VideoPresentationFactory provideImageVideoPresentationFactory(ImageVideoPresentationFactory imageVideoPresentationFactory) {
        return imageVideoPresentationFactory;
    }

    PlayReadyLicensingService providePlayReadyLicensingService(PlayReadyAvodLicensingService playReadyAvodLicensingService) {
        return playReadyAvodLicensingService;
    }

    PowerManagementLockFactory providePowerManagementLockFactory(AndroidLockFactory androidLockFactory) {
        return androidLockFactory;
    }

    VideoPresentationFactory provideVideoPresentationFactory(FirstMatchVideoPresentationFactory firstMatchVideoPresentationFactory) {
        return firstMatchVideoPresentationFactory;
    }

    WidevineLicensingService provideWidevineLicensingService(WidevineAvodLicensingService widevineAvodLicensingService) {
        return widevineAvodLicensingService;
    }

    MediaProfiler provideMediaProfiler() {
        return new PerfTracerMediaProfiler();
    }

    ConfigurablePlaybackSupportEvaluator provideConfigurablePlaybackSupportEvaluator(RendererSchemeController rendererSchemeController) {
        return new ConfigurablePlaybackSupportEvaluator(rendererSchemeController);
    }

    PlaybackSupportEvaluator providePlaybackSupportEvaluator() {
        return CachingPlaybackSupportEvaluator.getInstance();
    }

    DownloadService provideDownloadService() {
        return PrioritizingDownloadService.getInstance();
    }

    ExecutorService provideExecutorService(MediaSystemSharedContext mediaSystemSharedContext) {
        return mediaSystemSharedContext.getExecutorService();
    }

    Context provideAppContext(MediaSystemSharedContext mediaSystemSharedContext) {
        return mediaSystemSharedContext.getAppContext();
    }

    PlayerLifecycleConfig providePlayerLifecycleConfig() {
        return PlayerLifecycleConfig.getInstance();
    }
}
