package com.amazon.avod.media.service;

import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerResourcesExternalParamHolder {
    private static AdvertisingIdCollector mAdvertisingIdCollector;
    private static final CountDownLatch mInitializationLatch = new CountDownLatch(1);
    private static PlaybackSupportEvaluator mPlaybackSupportEvaluator;
    private static RendererSchemeController mRendererSchemeController;

    public static void initialize(@Nonnull PlaybackSupportEvaluator playbackSupportEvaluator, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull RendererSchemeController rendererSchemeController) {
        mPlaybackSupportEvaluator = (PlaybackSupportEvaluator) Preconditions.checkNotNull(playbackSupportEvaluator, "playbackSupportEvaluator");
        mAdvertisingIdCollector = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector");
        mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
        mInitializationLatch.countDown();
    }

    @Nonnull
    public static PlaybackSupportEvaluator getPlaybackSupportEvaluator() {
        waitOnInitialization();
        return mPlaybackSupportEvaluator;
    }

    @Nonnull
    public static AdvertisingIdCollector getAdvertisingIdCollector() {
        waitOnInitialization();
        return mAdvertisingIdCollector;
    }

    @Nonnull
    public static RendererSchemeController getRendererSchemeController() {
        waitOnInitialization();
        return mRendererSchemeController;
    }

    private static void waitOnInitialization() {
        try {
            mInitializationLatch.await();
        } catch (InterruptedException e) {
            DLog.warnf("PlayerResourcesExternalParamHolder, interrupted waiting for initialization latch %s", e);
        }
    }
}
