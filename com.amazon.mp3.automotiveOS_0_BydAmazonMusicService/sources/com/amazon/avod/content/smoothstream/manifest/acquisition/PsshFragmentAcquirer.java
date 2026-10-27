package com.amazon.avod.content.smoothstream.manifest.acquisition;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.Mp4FragmentParser;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.downloading.SmoothStreamingDownloadExceptionUtils;
import com.amazon.avod.content.smoothstream.downloading.SmoothStreamingRequestPrioritizationUtils;
import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.content.urlvending.DownloadType;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.memory.SlowGrowingDirectBuffer;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.playback.drm.DrmScheme;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PsshFragmentAcquirer {
    private final SmoothStreamingPlaybackConfig mConfig;
    private final SmoothStreamingContentStore mContentStore;
    private final RetryingObjectAcquirer<ProtectionHeader> mDelegate;
    private final DrmScheme mDrmScheme;
    private final SmoothStreamingDownloadExceptionUtils mExceptionUtils;
    private final MediaProfiler mMediaProfiler;
    private final Mp4FragmentParser mParser;
    private final SmoothStreamingRequestPrioritizationUtils mPrioritizationUtils;

    public PsshFragmentAcquirer(@Nonnull DownloadService downloadService, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull MediaProfiler mediaProfiler, @Nonnull SmoothStreamingContentStore smoothStreamingContentStore, @Nonnull Mp4FragmentParser mp4FragmentParser, @Nullable DrmScheme drmScheme) {
        RetryingObjectAcquirer retryingObjectAcquirer = new RetryingObjectAcquirer(downloadService, contentManagementEventBus);
        SmoothStreamingDownloadExceptionUtils smoothStreamingDownloadExceptionUtils = new SmoothStreamingDownloadExceptionUtils();
        SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig = SmoothStreamingPlaybackConfig.INSTANCE;
        this(mediaProfiler, retryingObjectAcquirer, smoothStreamingContentStore, smoothStreamingDownloadExceptionUtils, mp4FragmentParser, smoothStreamingPlaybackConfig, new SmoothStreamingRequestPrioritizationUtils(smoothStreamingPlaybackConfig), drmScheme);
    }

    PsshFragmentAcquirer(@Nonnull MediaProfiler mediaProfiler, @Nonnull RetryingObjectAcquirer<ProtectionHeader> retryingObjectAcquirer, @Nonnull SmoothStreamingContentStore smoothStreamingContentStore, @Nonnull SmoothStreamingDownloadExceptionUtils smoothStreamingDownloadExceptionUtils, @Nonnull Mp4FragmentParser mp4FragmentParser, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull SmoothStreamingRequestPrioritizationUtils smoothStreamingRequestPrioritizationUtils, @Nullable DrmScheme drmScheme) {
        this.mMediaProfiler = (MediaProfiler) Preconditions.checkNotNull(mediaProfiler, "mediaProfiler");
        this.mDelegate = (RetryingObjectAcquirer) Preconditions.checkNotNull(retryingObjectAcquirer, "delegate");
        this.mContentStore = (SmoothStreamingContentStore) Preconditions.checkNotNull(smoothStreamingContentStore, "contentStore");
        this.mExceptionUtils = (SmoothStreamingDownloadExceptionUtils) Preconditions.checkNotNull(smoothStreamingDownloadExceptionUtils, "exceptionUtils");
        this.mParser = (Mp4FragmentParser) Preconditions.checkNotNull(mp4FragmentParser, "parser");
        this.mConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "config");
        this.mPrioritizationUtils = (SmoothStreamingRequestPrioritizationUtils) Preconditions.checkNotNull(smoothStreamingRequestPrioritizationUtils, "prioritizationUtils");
        this.mDrmScheme = drmScheme;
    }

    public ProtectionHeader getProtectionHeaderFromFragment(int i, ContentUrlSelector contentUrlSelector, ContentSessionContext contentSessionContext, boolean z, String str) throws ContentException {
        return getProtectionHeaderFromFragment(i, i, contentUrlSelector, contentSessionContext, z, str);
    }

    public ProtectionHeader getProtectionHeaderFromFragment(int i, int i2, ContentUrlSelector contentUrlSelector, final ContentSessionContext contentSessionContext, boolean z, String str) throws ContentException {
        StreamIndex audioStream;
        if (z) {
            audioStream = contentSessionContext.getStreamSelections().getVideoStream();
        } else {
            audioStream = contentSessionContext.getStreamSelections().getAudioStream();
        }
        StreamIndex streamIndex = audioStream;
        final SmoothStreamingURI smoothStreamingURI = new SmoothStreamingURI(streamIndex, streamIndex.getSortedQualityLevels(i)[0], i, i2, true);
        RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks<ProtectionHeader> retryingObjectAcquirerCallbacks = new RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks<ProtectionHeader>() { // from class: com.amazon.avod.content.smoothstream.manifest.acquisition.PsshFragmentAcquirer.1
            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public /* bridge */ /* synthetic */ ProtectionHeader readLocalObject(@Nullable Map map) throws ContentException {
                return readLocalObject((Map<String, List<String>>) map);
            }

            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public boolean localObjectExists() {
                return PsshFragmentAcquirer.this.mContentStore.isAnyFragmentAvailable(contentSessionContext, smoothStreamingURI);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public ProtectionHeader readLocalObject(@Nullable Map<String, List<String>> map) throws ContentException {
                PsshFragmentAcquirer.this.mMediaProfiler.start("rtwLoadPSSHFragmentFromDisk");
                try {
                    ByteBuffer byteBufferLoadFragment = PsshFragmentAcquirer.this.mContentStore.loadFragment(contentSessionContext, PsshFragmentAcquirer.this.mContentStore.getBestQualityAvailable(contentSessionContext, smoothStreamingURI));
                    PsshFragmentAcquirer.this.mMediaProfiler.stop("rtwLoadPSSHFragmentFromDisk");
                    int iCapacity = byteBufferLoadFragment.capacity();
                    byte[] bArr = new byte[iCapacity];
                    byteBufferLoadFragment.rewind();
                    byteBufferLoadFragment.get(bArr, 0, iCapacity);
                    PsshFragmentAcquirer.this.mMediaProfiler.start("rtwParsePSSHFragment");
                    try {
                        return PsshFragmentAcquirer.this.mParser.parse(bArr, 0, iCapacity, smoothStreamingURI.isAudio(), new SlowGrowingDirectBuffer(1048576)).getPssh(PsshFragmentAcquirer.this.mDrmScheme == DrmScheme.PLAYREADY);
                    } finally {
                        PsshFragmentAcquirer.this.mMediaProfiler.stop("rtwParsePSSHFragment");
                        PsshFragmentAcquirer.this.mContentStore.releaseBuffer(contentSessionContext.getSessionType(), byteBufferLoadFragment);
                    }
                } catch (Throwable th) {
                    PsshFragmentAcquirer.this.mMediaProfiler.stop("rtwLoadPSSHFragmentFromDisk");
                    throw th;
                }
            }

            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public void deleteLocalObject() {
                PsshFragmentAcquirer.this.mContentStore.deleteErroredFragment(contentSessionContext, smoothStreamingURI);
            }

            @Override // com.amazon.avod.media.downloadservice.SaveCallback
            public void save(InputStream inputStream, int i3) throws ContentException {
                ContentUrlSelector contentUrlSelector2 = contentSessionContext.getContentUrlSelector();
                PsshFragmentAcquirer.this.mContentStore.storeFragment(contentSessionContext, smoothStreamingURI, inputStream, i3, contentUrlSelector2 == null ? null : contentUrlSelector2.getCurrentContentUrl().getUrl());
            }

            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public String getObjectUrl(ContentUrl contentUrl) {
                return smoothStreamingURI.getAbsoluteUrl(contentUrl.getUrl());
            }

            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public Map<String, String> getObjectHeaders() {
                return smoothStreamingURI.getDownloadHeaders();
            }

            @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.RetryingObjectAcquirerCallbacks
            public ContentException translateException(Exception exc, @Nullable DownloadStatistics downloadStatistics) {
                return PsshFragmentAcquirer.this.mExceptionUtils.translateExceptionForFragment(exc, null, downloadStatistics);
            }
        };
        this.mMediaProfiler.start("rtwAcquirePSSHFragment");
        try {
            return this.mDelegate.acquireObject(contentUrlSelector, contentSessionContext.getContent(), contentSessionContext.getSessionType(), this.mConfig.getNumberOfPsshFragmentDownloadTries(), this.mConfig.getStreamingRetryDelayBackoff(), null, retryingObjectAcquirerCallbacks, this.mPrioritizationUtils.computeFragmentPriority(contentSessionContext.getEffectiveSessionType(), smoothStreamingURI, 0L), true, true, false, false, this.mConfig.getPsshFragmentDownloadTimeout(), false, str, DownloadType.FRAGMENT, contentSessionContext.getSpecification().getContentType(), this.mConfig.isPsshFragmentResponseCacheEnabled(), true, false);
        } finally {
            this.mMediaProfiler.stop("rtwAcquirePSSHFragment");
        }
    }
}
