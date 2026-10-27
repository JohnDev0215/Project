package com.amazon.avod.content.smoothstream;

import android.content.Context;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.downloading.ContentAccessor;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.exceptions.CorruptDataStreamException;
import com.amazon.avod.content.exceptions.StreamSeekOverException;
import com.amazon.avod.content.image.ImageDownloaderFactory;
import com.amazon.avod.content.readytowatch.ReadyToWatch;
import com.amazon.avod.content.smoothstream.Mp4FragmentParser;
import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.util.AudioConfig;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.sampling.SampleCodecData;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.playback.sampling.SampleReadResult;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.Base64;
import com.amazon.avod.util.DLog;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class AdaptiveStreamingSessionProtocol implements PlaybackSessionProtocol {
    private ContentAccessor mAccessor;
    private final Context mAppContext;
    private SampleStream mAudioStream;
    private StreamIndex mAudioStreamIndex;
    private final SmoothStreamingPlaybackConfig mConfig;
    private final ContentManagementEventBus mContentManagementEventBus;
    private ContentSessionContext mContentSessionContext;
    private ImageDownloader mImageDownloader;
    private StreamIndex mImageStreamIndex;
    private final LiveStreamingPlaybackConfig mLiveConfig;
    private final Mp4FragmentParser mMp4FragmentParser;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private final DefaultQualityConfiguration mQualityConfiguration;
    private ReadyToWatch mReadyToWatch;
    private SampleStreamFactory mStreamFactory;
    private SampleStream mSubtitleStream;
    private SampleStream mVideoStream;
    private StreamIndex mVideoStreamIndex;
    private volatile boolean mIsInitialized = false;
    private final FragmentEventMessageListener mFragmentEventMessageListener = new FragmentEventMessageListener(this, null);
    private final ImageDownloaderFactory mImageDownloaderFactory = ImageDownloaderFactory.getInstance();

    public AdaptiveStreamingSessionProtocol(@Nonnull Context context, Mp4FragmentParser mp4FragmentParser, SampleStreamFactory sampleStreamFactory, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, DefaultQualityConfiguration defaultQualityConfiguration, ContentManagementEventBus contentManagementEventBus, @Nonnull PlaybackEventReporter playbackEventReporter) {
        this.mAppContext = (Context) Preconditions.checkNotNull(context, "appContext");
        this.mQualityConfiguration = defaultQualityConfiguration;
        this.mMp4FragmentParser = mp4FragmentParser;
        this.mStreamFactory = sampleStreamFactory;
        this.mContentManagementEventBus = contentManagementEventBus;
        this.mConfig = smoothStreamingPlaybackConfig;
        this.mLiveConfig = liveStreamingPlaybackConfig;
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public void initialize(ContentSessionContext contentSessionContext, ContentAccessor contentAccessor, ReadyToWatch readyToWatch) {
        this.mContentSessionContext = contentSessionContext;
        this.mVideoStreamIndex = contentSessionContext.getStreamSelections().getVideoStream();
        this.mAudioStreamIndex = contentSessionContext.getStreamSelections().getAudioStream();
        this.mImageStreamIndex = contentSessionContext.getStreamSelections().getImageStreamStream();
        this.mAccessor = contentAccessor;
        this.mReadyToWatch = readyToWatch;
        this.mVideoStream = this.mStreamFactory.newVideoStream(contentSessionContext, contentAccessor);
        this.mAudioStream = this.mStreamFactory.newAudioStream(contentSessionContext, this.mAccessor);
        this.mSubtitleStream = this.mStreamFactory.newSubtitleStream(contentSessionContext, this.mAccessor);
        this.mIsInitialized = true;
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public boolean canResumeFromTimeInNanos(long j) {
        checkInitialized();
        return this.mReadyToWatch.canResumeFromTimeInNanos(j).isReadyToWatch();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public boolean canStartWatchingFromTimeInNanos(long j) {
        checkInitialized();
        return this.mReadyToWatch.canStartWatchingFromTimeInNanos(j).isReadyToWatch();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public long getFirstAvailablePositionInNanos(@Nullable StreamType streamType) {
        checkInitialized();
        return this.mAccessor.getFirstAvailableTimeInNanos(streamType);
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public long getLastAvailablePositionInNanos(@Nullable StreamType streamType) {
        checkInitialized();
        return this.mAccessor.getLastAvailableTimeInNanos(streamType);
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public long getMaxFrontBufferSizeInMillis() {
        checkInitialized();
        return this.mContentSessionContext.getMaxFrontBufferSize().getTotalMilliseconds();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public long getMaxBackBufferSizeInMillis() {
        checkInitialized();
        return this.mContentSessionContext.getMaxBackBufferSize().getTotalMilliseconds();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public void stopStream(@Nonnull StreamType streamType) {
        Preconditions.checkNotNull(streamType, "streamType");
        Preconditions.checkArgument((streamType == StreamType.AUDIO || streamType == StreamType.VIDEO) ? false : true, "streamType should not be audio/video");
        SampleStream sampleStream = this.mSubtitleStream;
        if (sampleStream != null) {
            sampleStream.stop();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public void startStream(@Nonnull StreamType streamType, long j) {
        Preconditions.checkNotNull(streamType, "streamType");
        Preconditions.checkArgument((streamType == StreamType.AUDIO || streamType == StreamType.VIDEO) ? false : true, "streamType should not be audio/video");
        SampleStream sampleStream = this.mSubtitleStream;
        if (sampleStream != null) {
            sampleStream.startAtNanos(j);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public boolean isStreamingSubtitlesSupported() {
        checkInitialized();
        return this.mContentSessionContext.getStreamSelections().getSubtitleStream() != null;
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public boolean hasUpdatesForContentView() {
        checkInitialized();
        return this.mAccessor.hasUpdatesForContentView();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public boolean hasDownloadErrors() {
        checkInitialized();
        return this.mAccessor.hasDownloadErrors();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    @Nonnull
    public String getContentDebugInfo() {
        StringBuilder sb = new StringBuilder();
        if (this.mConfig.isContentDebugInfoEnabled()) {
            appendDirectoryInformation(sb);
            appendSelectedStreamsInformation(sb);
            appendAdaptationSetsInformation(sb);
            sb.append(this.mContentSessionContext.getSpecification());
        }
        String string = sb.toString();
        DLog.warnf("SSSP - DebugInfo: %s", string);
        return string;
    }

    private void appendAdaptationSetsInformation(@Nonnull StringBuilder sb) {
        sb.append("adaptationSets{");
        List<StreamIndex> streams = this.mContentSessionContext.getManifest().getStreams();
        int size = streams.size();
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            arrayListNewArrayList.add(String.format(Locale.US, "%s=%s", Integer.valueOf(i), getFourCCTag(streams.get(i))));
        }
        Joiner.on(", ").appendTo(sb, arrayListNewArrayList);
        sb.append("}");
    }

    private String getFourCCTag(@Nullable StreamIndex streamIndex) {
        return streamIndex == null ? "NOT_FOUND" : String.format(Locale.US, "%s%s", streamIndex.getFourCC(), streamIndex.getAudioFormat());
    }

    private void appendSelectedStreamsInformation(@Nonnull StringBuilder sb) {
        sb.append("selectedStreams{video=");
        sb.append(getFourCCTag(this.mVideoStreamIndex));
        sb.append(", audio=");
        sb.append(getFourCCTag(this.mAudioStreamIndex));
        sb.append("}");
    }

    private void appendDirectoryInformation(@Nonnull StringBuilder sb) {
        ContentSessionContext contentSessionContext = this.mContentSessionContext;
        if (contentSessionContext == null) {
            return;
        }
        File storagePath = contentSessionContext.getStoragePath();
        sb.append("directory{path=");
        if (storagePath != null && storagePath.exists()) {
            sb.append(storagePath.getAbsolutePath());
            sb.append(", files=");
            if (this.mConfig.isDetailedContentDebugInfoEnabled()) {
                getDetailedDirectoryInformation(sb, storagePath);
            } else {
                getSimpleDirectoryInformation(sb, storagePath);
            }
        } else {
            sb.append("NOT_FOUND");
        }
        sb.append("}");
    }

    private void getDetailedDirectoryInformation(@Nonnull StringBuilder sb, @Nonnull File file) {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        for (File file2 : Files.fileTraverser().breadthFirst(file)) {
            int i = 0;
            int iNullSafeSizeOf = 0;
            for (File file3 : Files.fileTraverser().breadthFirst(file2)) {
                i++;
                iNullSafeSizeOf += file3.isDirectory() ? nullSafeSizeOf(file3.listFiles()) : 0;
            }
            arrayListNewArrayList.add(String.format(Locale.US, "%s|%s|%s", file2.getName(), Integer.valueOf(i), Integer.valueOf(iNullSafeSizeOf)));
        }
        Joiner.on(", ").appendTo(sb, arrayListNewArrayList);
    }

    private int nullSafeSizeOf(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    private void getSimpleDirectoryInformation(@Nonnull StringBuilder sb, @Nonnull File file) {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        Iterator<File> it = Files.fileTraverser().breadthFirst(file).iterator();
        while (it.hasNext()) {
            arrayListNewArrayList.add(it.next().getName());
        }
        Joiner.on(", ").appendTo(sb, arrayListNewArrayList);
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public void releaseFragment(SampleType sampleType, SampleHolder sampleHolder) {
        SampleStream streamForType = getStreamForType(sampleType);
        if (streamForType != null) {
            streamForType.releaseFragment();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public SampleReadResult getNextSample(SampleType sampleType, SampleHolder sampleHolder) throws MediaException {
        checkInitialized();
        SampleStream streamForType = getStreamForType(sampleType);
        if (streamForType == null) {
            return SampleReadResult.NO_STREAM_AVAILABLE;
        }
        try {
            return streamForType.readSampleToHolder(sampleHolder);
        } catch (CorruptDataStreamException e) {
            SmoothStreamingURI uri = e.getURI();
            boolean z = this.mAccessor.getDownloadedTimeAfterPositionInNanos(uri.getPresentationTimeInNanos() + uri.getDurationInNanos()) > 0;
            if (this.mContentSessionContext.isDownload() && z) {
                long chunkTimeInNanos = uri.getStream().getChunkTimeInNanos(uri.getChunkIndex());
                long nextSafeVideoTimeStampInNanos = getNextSafeVideoTimeStampInNanos(e.getURI().getStream(), e.getURI().getChunkIndex());
                Locale locale = Locale.US;
                Object[] objArr = new Object[3];
                objArr[0] = e;
                objArr[1] = Long.valueOf(chunkTimeInNanos);
                objArr[2] = nextSafeVideoTimeStampInNanos == -1 ? "End of stream" : String.valueOf(nextSafeVideoTimeStampInNanos);
                String str = String.format(locale, "Downloaded stream corrupt due to %s; Seeking from %s ns to %s ns", objArr);
                if (nextSafeVideoTimeStampInNanos == -1) {
                    return SampleReadResult.NO_MORE_DATA;
                }
                throw new StreamSeekOverException(str, ContentException.ContentError.DISK_ERROR, e.getCause(), uri.isAudio() ? StreamType.AUDIO : StreamType.VIDEO, chunkTimeInNanos, resolveTimeToVideoStreamBoundary(resolveToTimeWindow(nextSafeVideoTimeStampInNanos)));
            }
            throw e.getCause();
        }
    }

    protected long getNextSafeVideoTimeStampInNanos(StreamIndex streamIndex, int i) {
        checkInitialized();
        if (streamIndex.isLastPlayableChunk(i)) {
            return -1L;
        }
        if (streamIndex.isVideo()) {
            return streamIndex.getChunkTimeInNanos(i + 1);
        }
        long chunkTimeInNanos = streamIndex.getChunkTimeInNanos(i + 1);
        int chunkIndexFromNanos = this.mVideoStreamIndex.getChunkIndexFromNanos(chunkTimeInNanos);
        long chunkTimeInNanos2 = this.mVideoStreamIndex.getChunkTimeInNanos(chunkIndexFromNanos);
        while (chunkTimeInNanos2 < chunkTimeInNanos) {
            if (streamIndex.isLastPlayableChunk(chunkIndexFromNanos)) {
                return -1L;
            }
            chunkIndexFromNanos++;
            chunkTimeInNanos2 = this.mVideoStreamIndex.getChunkTimeInNanos(chunkIndexFromNanos);
        }
        return chunkTimeInNanos2;
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public TimeSpan seekTo(TimeSpan timeSpan, boolean z) throws ContentException {
        checkInitialized();
        if (z) {
            long totalNanoSeconds = timeSpan.getTotalNanoSeconds();
            DLog.warnf("Protocol seek-over to exact seek position: %s, translates to videoChunkIndex: %s audioChunkIndex: %s", Long.valueOf(totalNanoSeconds), Integer.valueOf(this.mVideoStreamIndex.getChunkIndexFromNanos(totalNanoSeconds)), Integer.valueOf(this.mAudioStreamIndex.getChunkIndexFromNanos(totalNanoSeconds)));
            this.mVideoStream.seekToNanos(totalNanoSeconds);
            this.mAudioStream.seekToNanos(totalNanoSeconds);
            SampleStream sampleStream = this.mSubtitleStream;
            if (sampleStream != null) {
                sampleStream.seekToNanos(totalNanoSeconds);
            }
            long jResolveTimeToVideoStreamBoundary = resolveTimeToVideoStreamBoundary(totalNanoSeconds);
            setPlayPositionInNanos(jResolveTimeToVideoStreamBoundary);
            return new TimeSpan(jResolveTimeToVideoStreamBoundary);
        }
        long jResolveTimeToVideoStreamBoundary2 = resolveTimeToVideoStreamBoundary(resolveToTimeWindow(timeSpan.getTotalNanoSeconds()));
        setPlayPositionInNanos(jResolveTimeToVideoStreamBoundary2);
        this.mVideoStream.seekToNanos(jResolveTimeToVideoStreamBoundary2);
        this.mAudioStream.seekToNanos(jResolveTimeToVideoStreamBoundary2);
        SampleStream sampleStream2 = this.mSubtitleStream;
        if (sampleStream2 != null) {
            sampleStream2.seekToNanos(jResolveTimeToVideoStreamBoundary2);
        }
        return new TimeSpan(jResolveTimeToVideoStreamBoundary2);
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public boolean isEncryptedContent() {
        checkInitialized();
        return this.mContentSessionContext.getManifest().isEncrypted();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public boolean isDownload() {
        checkInitialized();
        return this.mContentSessionContext.isDownload();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public String getEncodedEncryptionHeader() {
        checkInitialized();
        ProtectionHeader videoProtectionHeader = this.mContentSessionContext.getState().getVideoProtectionHeader();
        if (videoProtectionHeader == null) {
            return null;
        }
        return videoProtectionHeader.getBase64EncodedData();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public byte[] getEncryptionInitializationData() throws ContentException {
        String encodedEncryptionHeader = getEncodedEncryptionHeader();
        if (encodedEncryptionHeader == null) {
            return null;
        }
        try {
            return Base64.decode(encodedEncryptionHeader);
        } catch (IOException e) {
            throw new ContentException(ContentException.ContentError.INVALID_BASE64_DATA, e);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public AudioConfig getAudioConfig() {
        checkInitialized();
        AudioQualityLevel audioQuality = this.mQualityConfiguration.getAudioQuality(this.mContentSessionContext, this.mAudioStreamIndex);
        return new AudioConfig(audioQuality.getFourCC(), audioQuality.getSampleRate(), audioQuality.getNumChannels(), audioQuality.getBitrate());
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    @Nonnull
    public VideoConfig getVideoConfig() {
        checkInitialized();
        Manifest manifest = this.mContentSessionContext.getManifest();
        StreamIndex videoStream = manifest.getVideoStream();
        double displayAspectRatio = videoStream.getDisplayAspectRatio();
        VideoQualityLevel videoQuality = this.mQualityConfiguration.getVideoQuality(this.mContentSessionContext);
        if (Math.abs(displayAspectRatio - (-1.0d)) < 0.01d) {
            displayAspectRatio = ((double) videoQuality.getMaxWidth()) / ((double) videoQuality.getMaxHeight());
        }
        double d = displayAspectRatio;
        VideoResolution videoResolution = new VideoResolution(videoQuality.getMaxWidth(), videoQuality.getMaxHeight(), d);
        QualityLevel[] sortedQualityLevels = videoStream.getSortedQualityLevels(this.mContentSessionContext.getState().getConsumptionHead(videoStream.getIndex()));
        VideoResolution videoResolution2 = new VideoResolution(videoStream.getMaxWidth(), videoStream.getMaxHeight(), d);
        String codecData = videoQuality.getCodecData();
        return new VideoConfig(videoQuality.getFourCC(), videoResolution, videoResolution2, !Strings.isNullOrEmpty(codecData) ? SampleCodecData.fromHexString(codecData) : null, manifest.isHdr(), manifest.isDynamic(), d, videoQuality.getFrameRate(), sortedQualityLevels);
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public TimeSpan start(TimeSpan timeSpan) throws ContentException {
        checkInitialized();
        long jResolveTimeToVideoStreamBoundary = resolveTimeToVideoStreamBoundary(resolveToTimeWindow(timeSpan.getTotalNanoSeconds()));
        setPlayPositionInNanos(jResolveTimeToVideoStreamBoundary);
        this.mVideoStream.startAtNanos(jResolveTimeToVideoStreamBoundary);
        this.mAudioStream.startAtNanos(jResolveTimeToVideoStreamBoundary);
        if (this.mImageStreamIndex != null && this.mImageDownloader == null) {
            ImageDownloaderFactory imageDownloaderFactory = this.mImageDownloaderFactory;
            Context context = this.mAppContext;
            ContentSessionContext contentSessionContext = this.mContentSessionContext;
            this.mImageDownloader = imageDownloaderFactory.createImageDownloader(context, contentSessionContext, contentSessionContext.getSpecification(), this.mImageStreamIndex, this.mPlaybackEventReporter, this.mContentManagementEventBus);
        }
        ImageDownloader imageDownloader = this.mImageDownloader;
        if (imageDownloader != null) {
            imageDownloader.start();
        }
        this.mMp4FragmentParser.addEventMessageListener(this.mFragmentEventMessageListener);
        return new TimeSpan(jResolveTimeToVideoStreamBoundary);
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public void stop() {
        checkInitialized();
        DLog.logf("Stopping SmoothStreamingSession.");
        this.mAudioStream.stop();
        this.mVideoStream.stop();
        SampleStream sampleStream = this.mSubtitleStream;
        if (sampleStream != null) {
            sampleStream.stop();
        }
        this.mMp4FragmentParser.removeEventMessageListener(this.mFragmentEventMessageListener);
        this.mMp4FragmentParser.release();
    }

    private void checkInitialized() {
        if (!this.mIsInitialized) {
            throw new IllegalStateException("AdaptiveStreamingSessionProtocol is uninitialized");
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public void dispose() {
        ImageDownloader imageDownloader = this.mImageDownloader;
        if (imageDownloader != null) {
            imageDownloader.stop();
            this.mImageDownloader.dispose();
        }
        this.mIsInitialized = false;
        this.mStreamFactory = null;
        this.mReadyToWatch = null;
        this.mAccessor = null;
        this.mVideoStream = null;
        this.mAudioStream = null;
        this.mSubtitleStream = null;
        this.mImageStreamIndex = null;
        this.mImageDownloader = null;
        this.mVideoStreamIndex = null;
        this.mContentSessionContext = null;
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public void setPlayPositionInNanos(long j) {
        checkInitialized();
        this.mContentSessionContext.getState().setPlayPositionInNanos(j);
    }

    long resolveTimeToVideoStreamBoundary(long j) throws ContentException {
        Preconditions.checkArgument(j >= 0, "Negative seek position %s", j);
        long jResolveTimeToVideoStreamBoundary = StreamingUtils.resolveTimeToVideoStreamBoundary(j, this.mVideoStreamIndex, this.mAudioStreamIndex);
        DLog.logf("Original seek position %s nanoseconds, calculated position = %s nanoseconds", Long.valueOf(j), Long.valueOf(jResolveTimeToVideoStreamBoundary));
        return jResolveTimeToVideoStreamBoundary;
    }

    long resolveToTimeWindow(long j) throws ContentException {
        Preconditions.checkArgument(j >= 0, "Negative position %s", j);
        return this.mContentSessionContext.getSpecification().isLiveStream() ? this.mContentSessionContext.getState().resolveToMediaTimeWindowBoundary(j, false) : j;
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.smoothstream.AdaptiveStreamingSessionProtocol$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$playback$sampling$SampleType;

        static {
            int[] iArr = new int[SampleType.values().length];
            $SwitchMap$com$amazon$avod$playback$sampling$SampleType = iArr;
            try {
                iArr[SampleType.VIDEO_SAMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$sampling$SampleType[SampleType.AUDIO_SAMPLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$sampling$SampleType[SampleType.SUBTITLE_SAMPLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private SampleStream getStreamForType(SampleType sampleType) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$playback$sampling$SampleType[sampleType.ordinal()];
        if (i == 1) {
            return this.mVideoStream;
        }
        if (i == 2) {
            return this.mAudioStream;
        }
        if (i == 3) {
            return this.mSubtitleStream;
        }
        Preconditions.checkState(false, "cannot get stream type for SampleType: %s", (Object) sampleType);
        return null;
    }

    private class FragmentEventMessageListener implements Mp4FragmentParser.EventMessageListener {
        private FragmentEventMessageListener() {
        }

        /* synthetic */ FragmentEventMessageListener(AdaptiveStreamingSessionProtocol adaptiveStreamingSessionProtocol, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.amazon.avod.content.smoothstream.Mp4FragmentParser.EventMessageListener
        public void onEventMessage(@Nonnull Collection<EventMessage> collection) {
            Iterator<EventMessage> it = collection.iterator();
            while (it.hasNext()) {
                AdaptiveStreamingSessionProtocol.this.mContentManagementEventBus.postEvent(it.next());
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public void onBufferingStart() {
        this.mContentSessionContext.getQoeEvaluator().onBufferingStart();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    public void onBufferingStop() {
        this.mContentSessionContext.getQoeEvaluator().onBufferingStop();
    }

    @Override // com.amazon.avod.playback.PlaybackSessionProtocol
    @Nullable
    public ImageDownloader getImageDownloader() {
        return this.mImageDownloader;
    }
}
