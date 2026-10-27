package com.amazon.avod.playback;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.downloading.ContentAccessor;
import com.amazon.avod.content.readytowatch.ReadyToWatch;
import com.amazon.avod.content.smoothstream.ImageDownloader;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.util.AudioConfig;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.playback.sampling.SampleReadResult;
import com.amazon.avod.playback.sampling.SampleType;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackSessionProtocol {
    boolean canResumeFromTimeInNanos(long j);

    boolean canStartWatchingFromTimeInNanos(long j);

    void dispose();

    AudioConfig getAudioConfig() throws MediaException;

    @Nonnull
    String getContentDebugInfo();

    String getEncodedEncryptionHeader() throws MediaException;

    byte[] getEncryptionInitializationData() throws MediaException;

    long getFirstAvailablePositionInNanos(@Nullable StreamType streamType);

    @Nullable
    ImageDownloader getImageDownloader();

    long getLastAvailablePositionInNanos(@Nullable StreamType streamType);

    long getMaxBackBufferSizeInMillis();

    long getMaxFrontBufferSizeInMillis();

    SampleReadResult getNextSample(SampleType sampleType, SampleHolder sampleHolder) throws MediaException;

    @Nonnull
    VideoConfig getVideoConfig();

    boolean hasDownloadErrors();

    boolean hasUpdatesForContentView();

    void initialize(ContentSessionContext contentSessionContext, ContentAccessor contentAccessor, ReadyToWatch readyToWatch);

    boolean isDownload();

    boolean isEncryptedContent();

    boolean isStreamingSubtitlesSupported();

    void onBufferingStart();

    void onBufferingStop();

    void releaseFragment(SampleType sampleType, SampleHolder sampleHolder);

    TimeSpan seekTo(TimeSpan timeSpan, boolean z) throws ContentException;

    void setPlayPositionInNanos(long j);

    TimeSpan start(TimeSpan timeSpan) throws ContentException;

    void startStream(@Nonnull StreamType streamType, long j);

    void stop();

    void stopStream(@Nonnull StreamType streamType);
}
