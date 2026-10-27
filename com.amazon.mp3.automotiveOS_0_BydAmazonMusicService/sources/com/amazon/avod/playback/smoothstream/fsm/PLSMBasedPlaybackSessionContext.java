package com.amazon.avod.playback.smoothstream.fsm;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.AdEnabledPlaybackManager;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.ads.internal.state.AsyncAdPlanRetriever;
import com.amazon.avod.media.contentcache.CacheRecord;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.qos.metadata.DeliveryType;
import com.amazon.avod.userdownload.PVDownloadPlayerShim;
import com.amazon.avod.userdownload.PauseToken;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
@Deprecated
public class PLSMBasedPlaybackSessionContext implements PlaybackSessionContext {
    private AsyncAdPlanRetriever mAsyncAdPlanRetriever;
    private AudioVideoUrls mAudioVideoUrls;
    private Supplier<CacheRecord> mCacheRecordSupplier;
    private ContentSessionType mContentSessionType;
    private PlaybackDataSource mDataSource;
    private DeliveryType mDeliveryType;
    private DrmScheme mDrmScheme;
    private VideoRenderingSettings mOutputSettings;
    private VideoPlaybackEngine mPlaybackEngine;
    private RendererSchemeType mRendererSchemeType;
    private ContentSession mSession;
    private PVDownloadPlayerShim mUserDownload;
    private final VideoOptions mVideoOptions;
    private VideoSpecification mVideoSpec;
    private boolean mPlaybackStarted = false;
    private PauseToken mDownloadsPausedToken = null;
    private File mStoragePath = null;
    private boolean mIsPrimaryPlayerPrepared = false;
    private final boolean mIsSSAIEnabled = AdsConfig.getInstance().isSSAIEnabled();
    private String mOfflineKeyId = null;
    private final CountDownLatch mTerminationLatch = new CountDownLatch(1);

    public PLSMBasedPlaybackSessionContext(@Nonnull VideoOptions videoOptions, @Nonnull RendererSchemeController rendererSchemeController) {
        this.mVideoOptions = (VideoOptions) Preconditions.checkNotNull(videoOptions, "videoOptions");
        Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
        RendererScheme rendererScheme = rendererSchemeController.getRendererScheme(null, null);
        this.mRendererSchemeType = (RendererSchemeType) Preconditions.checkNotNull(rendererScheme.getSchemeType(), "rendererSchemeType");
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(rendererScheme.getDrmFramework().getCurrentDrmScheme(), "drmScheme");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public VideoSpecification getVideoSpec() {
        return this.mVideoSpec;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setVideoSpec(@Nonnull VideoSpecification videoSpecification) {
        this.mVideoSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpec");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public ContentSession getContentSession() {
        return this.mSession;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setContentSession(@Nonnull ContentSession contentSession) {
        this.mSession = (ContentSession) Preconditions.checkNotNull(contentSession, "session");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public VideoPlaybackEngine getPlaybackEngine() {
        return this.mPlaybackEngine;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setPlaybackEngine(@Nonnull VideoPlaybackEngine videoPlaybackEngine) {
        this.mPlaybackEngine = (VideoPlaybackEngine) Preconditions.checkNotNull(videoPlaybackEngine, "engine");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public VideoRenderingSettings getOutputSettings() {
        return this.mOutputSettings;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setOutputSettings(@Nonnull VideoRenderingSettings videoRenderingSettings) {
        this.mOutputSettings = (VideoRenderingSettings) Preconditions.checkNotNull(videoRenderingSettings, "settings");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public PlaybackDataSource getDataSource() {
        return this.mDataSource;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setDataSource(@Nonnull PlaybackDataSource playbackDataSource) {
        this.mDataSource = (PlaybackDataSource) Preconditions.checkNotNull(playbackDataSource, "dataSource");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public PVDownloadPlayerShim getDownload() {
        return this.mUserDownload;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setDownload(@Nullable PVDownloadPlayerShim pVDownloadPlayerShim) {
        this.mUserDownload = pVDownloadPlayerShim;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public PauseToken getDownloadsPausedToken() {
        return this.mDownloadsPausedToken;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setDownloadsPausedToken(@Nonnull PauseToken pauseToken) {
        this.mDownloadsPausedToken = (PauseToken) Preconditions.checkNotNull(pauseToken, "pauseToken");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setOfflineKeyId(@Nullable String str) {
        this.mOfflineKeyId = str;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public String getOfflineKeyId() {
        return this.mOfflineKeyId;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setDrmScheme(@Nonnull DrmScheme drmScheme) {
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nonnull
    public DrmScheme getDrmScheme() {
        return this.mDrmScheme;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setRendererSchemeType(@Nonnull RendererSchemeType rendererSchemeType) {
        this.mRendererSchemeType = (RendererSchemeType) Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nonnull
    public RendererSchemeType getRendererSchemeType() {
        return this.mRendererSchemeType;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public File getStoragePath() {
        return this.mStoragePath;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setStoragePath(@Nullable File file) {
        this.mStoragePath = file;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public ContentSessionType getContentSessionType() {
        return this.mContentSessionType;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setContentSessionType(@Nonnull ContentSessionType contentSessionType) {
        this.mContentSessionType = (ContentSessionType) Preconditions.checkNotNull(contentSessionType, "contentSessionType");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public DeliveryType getDeliveryType() {
        return this.mDeliveryType;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setDeliveryType(@Nonnull DeliveryType deliveryType) {
        this.mDeliveryType = (DeliveryType) Preconditions.checkNotNull(deliveryType, "deliveryType");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public AudioVideoUrls getAudioVideoUrls() {
        return this.mAudioVideoUrls;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setAudioVideoUrls(@Nullable AudioVideoUrls audioVideoUrls) {
        this.mAudioVideoUrls = audioVideoUrls;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public boolean isPlaybackStarted() {
        return this.mPlaybackStarted;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void notifyPlaybackStarted() {
        this.mPlaybackStarted = true;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public boolean isDashSession() {
        AudioVideoUrls audioVideoUrls = this.mAudioVideoUrls;
        if (audioVideoUrls == null || audioVideoUrls.getContentUrls().isEmpty()) {
            return false;
        }
        return ContentUrl.isDashUrl(this.mAudioVideoUrls.getContentUrls().get(0));
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void notifyPrimaryPlayerPrepared() {
        this.mIsPrimaryPlayerPrepared = true;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public boolean isPrimaryPlayerPrepared() {
        return this.mIsPrimaryPlayerPrepared;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setAdPlan(@Nonnull AdPlan adPlan) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nonnull
    public AdPlan getAdPlan() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nonnull
    public VideoOptions getVideoOptions() {
        return this.mVideoOptions;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setAdEnabledPlaybackManager(@Nonnull AdEnabledPlaybackManager adEnabledPlaybackManager) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public AdEnabledPlaybackManager getAdEnabledPlaybackManager() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nonnull
    public CountDownLatch getTerminationLatch() {
        return this.mTerminationLatch;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setAsyncAdPlanRetriever(@Nonnull AsyncAdPlanRetriever asyncAdPlanRetriever) {
        if (this.mIsSSAIEnabled) {
            this.mAsyncAdPlanRetriever = asyncAdPlanRetriever;
        }
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public AsyncAdPlanRetriever getAsyncAdPlanRetriever() {
        return this.mAsyncAdPlanRetriever;
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    public void setCacheRecordSupplier(@Nonnull Supplier<CacheRecord> supplier) {
        this.mCacheRecordSupplier = (Supplier) Preconditions.checkNotNull(supplier, "cacheRecordSupplier");
    }

    @Override // com.amazon.avod.playback.session.PlaybackSessionContext
    @Nullable
    public CacheRecord getCacheRecord() {
        Supplier<CacheRecord> supplier = this.mCacheRecordSupplier;
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }
}
