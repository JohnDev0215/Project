package com.amazon.avod.playback.session;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager;
import com.amazon.avod.media.ads.internal.state.AsyncAdPlanRetriever;
import com.amazon.avod.media.contentcache.CacheRecord;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.qos.metadata.DeliveryType;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.userdownload.PauseToken;
import com.google.common.base.Supplier;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackSessionContext {
    @Nullable
    AdEnabledPlaybackManager getAdEnabledPlaybackManager();

    @Nonnull
    AdPlan getAdPlan();

    @Nullable
    AsyncAdPlanRetriever getAsyncAdPlanRetriever();

    @Nullable
    AudioVideoUrls getAudioVideoUrls();

    @Nullable
    CacheRecord getCacheRecord();

    @Nullable
    ContentSession getContentSession();

    @Nullable
    ContentSessionType getContentSessionType();

    @Nullable
    PlaybackDataSource getDataSource();

    @Nullable
    DeliveryType getDeliveryType();

    @Nullable
    PVDownloadPlayerShim getDownload();

    @Nullable
    PauseToken getDownloadsPausedToken();

    @Nonnull
    DrmScheme getDrmScheme();

    @Nullable
    String getOfflineKeyId();

    @Nullable
    VideoRenderingSettings getOutputSettings();

    @Nullable
    VideoPlaybackEngine getPlaybackEngine();

    @Nonnull
    RendererSchemeType getRendererSchemeType();

    @Nullable
    File getStoragePath();

    @Nonnull
    CountDownLatch getTerminationLatch();

    @Nonnull
    VideoOptions getVideoOptions();

    @Nullable
    VideoSpecification getVideoSpec();

    boolean isDashSession();

    boolean isPlaybackStarted();

    boolean isPrimaryPlayerPrepared();

    void notifyPlaybackStarted();

    void notifyPrimaryPlayerPrepared();

    void setAdEnabledPlaybackManager(@Nonnull AdEnabledPlaybackManager adEnabledPlaybackManager);

    void setAdPlan(@Nonnull AdPlan adPlan);

    void setAsyncAdPlanRetriever(@Nonnull AsyncAdPlanRetriever asyncAdPlanRetriever);

    void setAudioVideoUrls(@Nullable AudioVideoUrls audioVideoUrls);

    void setCacheRecordSupplier(@Nonnull Supplier<CacheRecord> supplier);

    void setContentSession(@Nonnull ContentSession contentSession);

    void setContentSessionType(@Nonnull ContentSessionType contentSessionType);

    void setDataSource(@Nonnull PlaybackDataSource playbackDataSource);

    void setDeliveryType(@Nonnull DeliveryType deliveryType);

    void setDownload(@Nullable PVDownloadPlayerShim pVDownloadPlayerShim);

    void setDownloadsPausedToken(@Nonnull PauseToken pauseToken);

    void setDrmScheme(@Nonnull DrmScheme drmScheme);

    void setOfflineKeyId(@Nullable String str);

    void setOutputSettings(@Nonnull VideoRenderingSettings videoRenderingSettings);

    void setPlaybackEngine(@Nonnull VideoPlaybackEngine videoPlaybackEngine);

    void setRendererSchemeType(@Nonnull RendererSchemeType rendererSchemeType);

    void setStoragePath(@Nullable File file);

    void setVideoSpec(@Nonnull VideoSpecification videoSpecification);
}
