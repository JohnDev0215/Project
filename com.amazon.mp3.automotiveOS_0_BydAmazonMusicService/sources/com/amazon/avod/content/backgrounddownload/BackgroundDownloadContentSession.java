package com.amazon.avod.content.backgrounddownload;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionEventListener;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.PlayerBindState;
import com.amazon.avod.content.SmoothStreamingContentSessionEventTranslator;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.audioselection.AudioStreamAndQualityPair;
import com.amazon.avod.content.event.ContentEventSessionEnding;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.ContentMetadataAcquiredEvent;
import com.amazon.avod.content.event.FatalContentEventError;
import com.amazon.avod.content.readytowatch.ReadyToWatch;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.StartAudioBitrateSelectionMode;
import com.amazon.avod.content.smoothstream.StreamSelections;
import com.amazon.avod.content.smoothstream.StreamSelector;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.content.smoothstream.manifest.ProtectionHeaderWithValidation;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirerInterface;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.content.smoothstream.manifest.acquisition.PsshFragmentAcquirer;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamper;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamperFactory;
import com.amazon.avod.content.smoothstream.quality.VideoResolutionFilter;
import com.amazon.avod.content.smoothstream.storage.WriterScopedContentStore;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.content.urlvending.ContentUrlSelectorFactory;
import com.amazon.avod.content.urlvending.ContentUrlSwitchingPolicyFactory;
import com.amazon.avod.content.urlvending.QoeConfig;
import com.amazon.avod.content.urlvending.QoeEvaluator;
import com.amazon.avod.content.urlvending.QoeEvaluatorFactory;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.PlaybackPerformanceReport;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.pmet.ContentStoreType;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.throughput.BandwidthStats;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class BackgroundDownloadContentSession implements ContentSession {
    private final int mAppUid;
    private AudioVideoUrls mAudioVideoUrls;
    private final QualityLevelClamperFactory mClamperFactory;
    private final SmoothStreamingPlaybackConfig mConfig;
    private DownloadTask mContentDownloader;
    private final ContentManagementEventBus mContentEventDispatcher;
    private final ContentUrlSelectorFactory mContentUrlSelectorFactory;
    private final ContentUrlSwitchingPolicyFactory mContentUrlSwitchingPolicyFactory;
    private ContentSessionContext mContext;
    private final DownloadService mDownloadService;
    private final SmoothStreamingContentSessionEventTranslator mEventTranslator;
    private final PlaybackNativeLibrariesLoader mLibraryLoader;
    private final LifecycleProfiler mLifecycleProfiler;
    private Manifest mManifest;
    private final ManifestAcquirerInterface mManifestAcquirer;
    private final ManifestCapturerInterface mManifestCapturer;
    private final MediaQualityConfig mMediaQualityConfig;
    private final NetworkHistoryManager mNetworkHistoryManager;
    private PlayableContent mPlayableContent;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private final PsshFragmentAcquirer mPsshFragmentAcquirer;
    private ReadyToWatch mReadyToWatch;
    private final WriterScopedContentStore mScopedContentStore;
    private StreamSelections mSelectedStreams;
    private File mStoragePath;
    private final StreamSelector mStreamSelector;
    private VideoSpecification mVideoSpecification;
    private final Object mStartStopMutex = new Object();
    private final Stopwatch mManifestAcquiryStopwatch = Stopwatch.createUnstarted(Tickers.androidTicker());
    private ContentUrlSelector mContentUrlSelector = null;
    private boolean mIsCancelled = false;
    private volatile boolean mIsActive = false;
    private DrmScheme mDrmScheme = null;
    private final QoeEvaluator mQoeEvaluator = QoeEvaluatorFactory.INSTANCE.newQoeEvaluator(QoeConfig.INSTANCE);

    @Override // com.amazon.avod.content.ContentSession
    public String getAuditPingUrl(@Nonnull String str) {
        return null;
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public ContentException getLastFatalError() {
        return null;
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean isRestarting() {
        return false;
    }

    @Override // com.amazon.avod.content.ContentSession
    public void notifyPlayerBindStateChange(@Nonnull PlayerBindState playerBindState) {
    }

    public BackgroundDownloadContentSession(@Nonnull NetworkHistoryManager networkHistoryManager, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull ManifestAcquirerInterface manifestAcquirerInterface, @Nonnull PsshFragmentAcquirer psshFragmentAcquirer, @Nonnull StreamSelector streamSelector, @Nonnull WriterScopedContentStore writerScopedContentStore, @Nonnull QualityLevelClamperFactory qualityLevelClamperFactory, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull ContentUrlSelectorFactory contentUrlSelectorFactory, @Nonnull ContentUrlSwitchingPolicyFactory contentUrlSwitchingPolicyFactory, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull LifecycleProfiler lifecycleProfiler, DownloadService downloadService, @Nonnull MediaQualityConfig mediaQualityConfig, @Nonnull SmoothStreamingContentSessionEventTranslator smoothStreamingContentSessionEventTranslator, int i, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        this.mNetworkHistoryManager = (NetworkHistoryManager) Preconditions.checkNotNull(networkHistoryManager, "networkHistoryManager");
        this.mContentEventDispatcher = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "eventBus");
        this.mManifestAcquirer = (ManifestAcquirerInterface) Preconditions.checkNotNull(manifestAcquirerInterface, "manifestAcquirer");
        this.mPsshFragmentAcquirer = (PsshFragmentAcquirer) Preconditions.checkNotNull(psshFragmentAcquirer, "psshFragmentAcquirer");
        this.mStreamSelector = (StreamSelector) Preconditions.checkNotNull(streamSelector, "streamSelector");
        this.mScopedContentStore = (WriterScopedContentStore) Preconditions.checkNotNull(writerScopedContentStore, "contentStore");
        this.mClamperFactory = (QualityLevelClamperFactory) Preconditions.checkNotNull(qualityLevelClamperFactory, "clamperFactory");
        this.mLibraryLoader = (PlaybackNativeLibrariesLoader) Preconditions.checkNotNull(playbackNativeLibrariesLoader, "libraryLoader");
        this.mDownloadService = (DownloadService) Preconditions.checkNotNull(downloadService, "downloadService");
        this.mMediaQualityConfig = (MediaQualityConfig) Preconditions.checkNotNull(mediaQualityConfig, "mediaQualityConfig");
        this.mEventTranslator = (SmoothStreamingContentSessionEventTranslator) Preconditions.checkNotNull(smoothStreamingContentSessionEventTranslator, "smoothStreamingContentSessionEventTranslator");
        this.mContentUrlSelectorFactory = (ContentUrlSelectorFactory) Preconditions.checkNotNull(contentUrlSelectorFactory, "contentUrlSelectorFactory");
        this.mContentUrlSwitchingPolicyFactory = (ContentUrlSwitchingPolicyFactory) Preconditions.checkNotNull(contentUrlSwitchingPolicyFactory, "contentUrlSwitchingPolicyFactory");
        this.mConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "config");
        this.mLifecycleProfiler = (LifecycleProfiler) Preconditions.checkNotNull(lifecycleProfiler, "lifecycleProfiler");
        this.mAppUid = i;
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
        this.mManifestCapturer = (ManifestCapturerInterface) Preconditions.checkNotNull(manifestCapturerInterface, "manifestCapturer");
    }

    public void initialize(@Nonnull VideoSpecification videoSpecification, @Nonnull AudioVideoUrls audioVideoUrls, @Nonnull File file, @Nonnull DrmScheme drmScheme) {
        this.mVideoSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpec");
        this.mStoragePath = (File) Preconditions.checkNotNull(file, "storagePath");
        this.mAudioVideoUrls = (AudioVideoUrls) Preconditions.checkNotNull(audioVideoUrls, "audioVideoUrls");
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
    }

    @Override // com.amazon.avod.content.ContentSession
    public void setListener(ContentSessionEventListener contentSessionEventListener) {
        this.mEventTranslator.setListener(contentSessionEventListener);
    }

    @Override // com.amazon.avod.content.ContentSession
    public void begin() throws MediaException {
        synchronized (this.mStartStopMutex) {
            if (!this.mIsCancelled && !this.mIsActive) {
                waitForNativeLibrariesInitialization();
                this.mEventTranslator.begin();
                try {
                    Preconditions.checkNotNull(this.mAudioVideoUrls, "must call initialize before calling begin");
                    ContentUrlSelector contentUrlSelectorNewContentUrlSelector = this.mContentUrlSelectorFactory.newContentUrlSelector(this.mAudioVideoUrls, this.mContentEventDispatcher, false, false, null);
                    this.mContentUrlSelector = contentUrlSelectorNewContentUrlSelector;
                    contentUrlSelectorNewContentUrlSelector.setContentUrlSwitchingPolicy(this.mContentUrlSwitchingPolicyFactory.newContentUrlSwitchingPolicyForManifest());
                    VideoSpecification videoSpecification = this.mVideoSpecification;
                    ContentUrl currentContentUrl = this.mContentUrlSelector.getCurrentContentUrl();
                    ContentSessionType contentSessionType = ContentSessionType.DOWNLOAD;
                    DLog.logf("Starting Download ContentSession for %s using url %s, session type %s", videoSpecification, currentContentUrl, contentSessionType);
                    PlayableContent playableContentNewPlayableContent = PlayableContent.newPlayableContent(this.mVideoSpecification);
                    this.mPlayableContent = playableContentNewPlayableContent;
                    this.mScopedContentStore.begin(playableContentNewPlayableContent, contentSessionType, this.mContentEventDispatcher, this.mContext);
                    this.mManifest = this.mManifestAcquirer.getManifestFromUrl(this.mContentUrlSelector, this.mAudioVideoUrls.getEncodeId(), this.mStoragePath, this.mPlayableContent, contentSessionType, false, this.mDrmScheme, this.mLifecycleProfiler, null, this.mVideoSpecification.getContentType());
                    initializeModels();
                    this.mContentDownloader.start();
                    setProtectionHeader();
                    this.mIsActive = true;
                } catch (ContentException e) {
                    ContentManagementEventBus contentManagementEventBus = this.mContentEventDispatcher;
                    PlayableContent playableContent = this.mPlayableContent;
                    ContentSessionType contentSessionType2 = ContentSessionType.DOWNLOAD;
                    contentManagementEventBus.postEvent(new FatalContentEventError(playableContent, contentSessionType2, e, getConsumptionId(), contentSessionType2));
                    throw e;
                }
            }
        }
    }

    private void waitForNativeLibrariesInitialization() throws ContentException {
        if (this.mLibraryLoader.waitForInitialization()) {
            return;
        }
        DLog.errorf("Failed to load the playback native libraries!");
        throw new ContentException(ContentException.ContentError.ERROR_LOADING_NATIVE_LIBRARIES, "Failed to load the playback native libraries!");
    }

    @Override // com.amazon.avod.content.ContentSession
    public void refreshManifest() throws MediaException {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    public void end(boolean z) {
        synchronized (this.mStartStopMutex) {
            ContentManagementEventBus contentManagementEventBus = this.mContentEventDispatcher;
            PlayableContent playableContent = this.mPlayableContent;
            ContentSessionType contentSessionType = ContentSessionType.DOWNLOAD;
            contentManagementEventBus.postEvent(new ContentEventSessionEnding(playableContent, contentSessionType));
            if (!this.mIsCancelled && this.mIsActive) {
                this.mIsCancelled = true;
                this.mIsActive = false;
                DownloadTask downloadTask = this.mContentDownloader;
                if (downloadTask != null) {
                    downloadTask.stop();
                }
                this.mEventTranslator.end();
                try {
                    PlayableContent playableContent2 = this.mPlayableContent;
                    if (playableContent2 != null) {
                        this.mScopedContentStore.end(contentSessionType, playableContent2, this.mAudioVideoUrls.getEncodeId());
                    }
                } catch (ContentException e) {
                    DLog.errorf(e.getMessage());
                }
                this.mContentEventDispatcher.shutdown();
                this.mManifestAcquirer.shutdown();
                Manifest manifest = this.mManifest;
                if (manifest != null) {
                    manifest.release();
                }
            }
        }
    }

    private void initializeModels() throws ContentException {
        MediaQuality mediaQuality = this.mVideoSpecification.getMediaQuality();
        StreamSelector streamSelector = this.mStreamSelector;
        Manifest manifest = this.mManifest;
        ContentSessionType contentSessionType = ContentSessionType.DOWNLOAD;
        StreamSelections streamSelectionsSelectStreams = streamSelector.selectStreams(true, manifest, contentSessionType, this.mVideoSpecification.getAudioFormat(), this.mVideoSpecification.getAudioLanguage(), this.mVideoSpecification.getAudioTrackIds(), this.mVideoSpecification.getPreferredAudioTrackIds(), this.mVideoSpecification.getPrimaryAudioTrackId(), this.mNetworkHistoryManager, null, StartAudioBitrateSelectionMode.AUDIO_FORMAT_BASED, this.mStoragePath, this.mAudioVideoUrls.getEncodeId(), this.mPlayableContent, mediaQuality);
        this.mSelectedStreams = streamSelectionsSelectStreams;
        DLog.logf("Selected streams: %s", streamSelectionsSelectStreams);
        StreamIndex videoStream = this.mSelectedStreams.getVideoStream();
        QualityLevelClamper qualityLevelClamperNewServerConfigurableQualityLevelClamper = this.mClamperFactory.newServerConfigurableQualityLevelClamper(this.mManifest);
        this.mContext = ContentSessionContext.create(this.mPlayableContent, this.mVideoSpecification, contentSessionType, this.mManifest, this.mAudioVideoUrls, this.mSelectedStreams, new VideoResolutionFilter(qualityLevelClamperNewServerConfigurableQualityLevelClamper, videoStream).getResolutions(0), this.mConfig, null, this.mManifestAcquiryStopwatch, LiveStreamingPlaybackConfig.getInstance(), this.mStoragePath, this.mNetworkHistoryManager, null, BandwidthStats.newInstance(this.mAppUid), this.mContentUrlSelector, this.mPlaybackEventReporter, this.mClamperFactory, qualityLevelClamperNewServerConfigurableQualityLevelClamper, PlayerBindState.UNATTACHED, this.mQoeEvaluator, ContentStoreType.FILESTORE);
        this.mContentUrlSelector.setContentUrlSwitchingPolicy(this.mContentUrlSwitchingPolicyFactory.newContentUrlSwitchingPolicyForFragment());
        ArrayList arrayListNewArrayList = Lists.newArrayList(new SmoothStreamingURI(this.mSelectedStreams.getVideoStream(), videoStream.getClosestQualityLevelWithResolutionCap(0, this.mMediaQualityConfig.getVideoDownloadBitrateCap(mediaQuality, this.mContext.getMaxResolution(), videoStream.getFourCC()), this.mContext.getMaxResolution()), this.mSelectedStreams.getVideoStream().getNumChunks() - 1));
        for (AudioStreamAndQualityPair audioStreamAndQualityPair : this.mSelectedStreams.getAudioStreamAndQualityPairs()) {
            StreamIndex audioStream = audioStreamAndQualityPair.getAudioStream();
            arrayListNewArrayList.add(new SmoothStreamingURI(audioStream, audioStreamAndQualityPair.getAudioQualityLevel(), audioStream.getNumChunks() - 1));
        }
        try {
            this.mContentDownloader = CompositeDownloader.newDownloadTask(arrayListNewArrayList, this.mDownloadService, this.mStoragePath, this.mContentEventDispatcher, this.mContentUrlSelector, this.mPlayableContent, SingleFileDownloadConfig.getInstance(), this.mScopedContentStore, this.mContext);
            this.mReadyToWatch = new SingleFileDownloaderReadyToWatch(this.mContentDownloader, this.mContext.getDuration(), this.mConfig.getSingleFileReadyToWatchDeltaPercentage());
            long diskSpace = DiskUtils.getDiskSpace(this.mStoragePath.getAbsolutePath());
            long totalSizeBytes = this.mContentDownloader.getTotalSizeBytes() - this.mContentDownloader.getDownloadedSizeBytes();
            long bytes = DataUnit.MEGABYTES.toBytes(r0.getReservedSizeMegaBytes());
            if (totalSizeBytes > 0 && totalSizeBytes + bytes >= diskSpace) {
                throw new ContentException(ContentException.ContentError.DISK_FULL);
            }
        } catch (IOException e) {
            throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage());
        }
    }

    private void setProtectionHeader() throws ContentException {
        if (this.mManifest.isEncrypted()) {
            ProtectionHeader protectionHeader = this.mManifest.getProtectionHeader();
            if (protectionHeader == null) {
                protectionHeader = this.mPsshFragmentAcquirer.getProtectionHeaderFromFragment(-1, this.mContentUrlSelector, this.mContext, true, null);
            }
            if (this.mConfig.shouldValidateProtectionHeaderBeforeUsing()) {
                protectionHeader = ProtectionHeaderWithValidation.fromRawProtectionHeader(protectionHeader);
            }
            try {
                this.mContext.getState().setProtectionHeaderIfUnset(protectionHeader, null);
                this.mContentEventDispatcher.postEvent(new ContentMetadataAcquiredEvent(this.mContext.getContent(), this.mContext.getSessionType(), protectionHeader.getBase64EncodedData()));
            } catch (IOException e) {
                throw new ContentException(ContentException.ContentError.CDN_ERROR, "Error parsing the PSSH: ", e);
            }
        }
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean isActive() {
        return this.mIsActive;
    }

    @Override // com.amazon.avod.content.ContentSession
    public ContentManagementEventBus getEventDispatcher() {
        return this.mContentEventDispatcher;
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean isEncryptedContent() {
        Preconditions.checkState(this.mContext != null, "Must call begin() before isEncryptedContent()!");
        return this.mContext.getManifest().isEncrypted();
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public byte[] getVideoDefaultKeyId() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public byte[] getAudioDefaultKeyId() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    public String getEncodedEncryptionHeader() throws ContentException {
        Preconditions.checkState(this.mContext != null, "Must call begin() before getEncodedEncryptionHeader()!");
        ProtectionHeader videoProtectionHeader = this.mContext.getState().getVideoProtectionHeader();
        if (videoProtectionHeader == null) {
            return null;
        }
        return videoProtectionHeader.getBase64EncodedData();
    }

    @Override // com.amazon.avod.content.ContentSession
    public ContentSessionContext getContext() {
        Preconditions.checkState(this.mContext != null, "Must call begin() before getContext()!");
        return this.mContext;
    }

    @Override // com.amazon.avod.content.ContentSession
    public ReadyToWatch getReadyToWatch() {
        return this.mReadyToWatch;
    }

    @Override // com.amazon.avod.content.ContentSession
    public PlaybackSessionProtocol getProtocol() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    public void setVideoQualityOverride(@Nullable QualityLevel qualityLevel) {
        throw new UnsupportedOperationException("setVideoQualityOverride");
    }

    @Override // com.amazon.avod.content.ContentSession
    public void setPlaybackPerformanceReport(PlaybackPerformanceReport playbackPerformanceReport) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    public void setPlaybackRestrictedToBufferedContent(boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    public long getDownloadedTimeAfterPositionInNanos(long j) {
        Preconditions.checkState(this.mContext != null, "Must call begin() before getDownloadedTimeAfterPositionInNanos()!");
        return (long) ((this.mContext.getDuration().getTotalNanoSeconds() * (this.mContentDownloader.getDownloadedSizeBytes() / this.mContentDownloader.getTotalSizeBytes())) - j);
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean isFullyDownloadedFromNanos(long j) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    public long getRemainingNeededSizeInBytes() {
        Preconditions.checkState(this.mContentDownloader != null, "Must call begin() before getRemainingNeededSizeInBytes()!");
        return this.mContentDownloader.getTotalSizeBytes() - this.mContentDownloader.getDownloadedSizeBytes();
    }

    @Override // com.amazon.avod.content.ContentSession
    public long getTotalNeededSizeInBytes() {
        Preconditions.checkState(this.mContentDownloader != null, "Must call begin() before getTotalNeededSizeInBytes()!");
        return this.mContentDownloader.getTotalSizeBytes();
    }

    @Override // com.amazon.avod.content.ContentSession
    public TimeSpan getLastAvailableTime() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nonnull
    public Set<String> getAvailableAudioLanguages() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    public long getTimeWindowStartMillis() throws ContentException {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    public long getTimeWindowEndMillis() throws ContentException {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public String getCurrentAudioLanguage() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public ContentUrlSelector getContentUrlSelector() {
        return this.mContentUrlSelector;
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean shouldSwitchAudioAdaptationSet() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    public boolean verifyAudioCachedContent(long j) throws ContentException {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nonnull
    public ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList() {
        Preconditions.checkNotNull(this.mAudioVideoUrls, "AudioVideoUrls is null inside ContentSession");
        return this.mAudioVideoUrls.getAudioTrackMetadataList();
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public AudioVideoUrls getAudioVideoUrls() {
        return this.mAudioVideoUrls;
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nullable
    public String getConsumptionId() {
        ContentUrlSelector contentUrlSelector = this.mContentUrlSelector;
        if (contentUrlSelector != null) {
            return contentUrlSelector.getCurrentContentUrl().getSessionId();
        }
        return null;
    }

    @Override // com.amazon.avod.content.ContentSession
    @Nonnull
    public ManifestCapturerInterface getManifestCapturer() {
        return this.mManifestCapturer;
    }
}
