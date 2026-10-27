package com.amazon.avod.content.smoothstream.streamstate;

import com.amazon.avod.content.ContentSessionConfiguration;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.downloading.ContentViewPersistence;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicyFactory;
import com.amazon.avod.content.smoothstream.streamstate.retentionpolicy.RetentionPolicyFactory;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class StreamStateFactory {
    private final CheckpointPolicyFactory mCheckpointPolicyFactory;
    private final SmoothStreamingPlaybackConfig mConfig;
    private final SmoothStreamingContentStore mContentStore;
    private final DefaultQualityConfiguration mQualityDefaults;
    private final RetentionPolicyFactory mRetentionPolicyFactory;

    public StreamStateFactory(DefaultQualityConfiguration defaultQualityConfiguration, ExecutorService executorService, SmoothStreamingContentStore smoothStreamingContentStore, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, ContentSessionConfiguration contentSessionConfiguration, @Nonnull DownloadService downloadService) {
        this(smoothStreamingContentStore, new CheckpointPolicyFactory(smoothStreamingContentStore, executorService, smoothStreamingPlaybackConfig, defaultQualityConfiguration), new RetentionPolicyFactory(contentSessionConfiguration, (DownloadService) Preconditions.checkNotNull(downloadService, "downloadService")), defaultQualityConfiguration, smoothStreamingPlaybackConfig);
    }

    StreamStateFactory(SmoothStreamingContentStore smoothStreamingContentStore, CheckpointPolicyFactory checkpointPolicyFactory, RetentionPolicyFactory retentionPolicyFactory, DefaultQualityConfiguration defaultQualityConfiguration, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mContentStore = smoothStreamingContentStore;
        this.mCheckpointPolicyFactory = checkpointPolicyFactory;
        this.mRetentionPolicyFactory = retentionPolicyFactory;
        this.mQualityDefaults = defaultQualityConfiguration;
        this.mConfig = smoothStreamingPlaybackConfig;
    }

    public StreamStateImpl newStreamState(ContentSessionContext contentSessionContext, StreamIndex streamIndex, ContentViewPersistence contentViewPersistence) {
        StreamStateImpl streamStateImpl = new StreamStateImpl(this.mContentStore, this.mConfig);
        streamStateImpl.initialize(contentSessionContext, streamIndex, this.mRetentionPolicyFactory.newRetentionPolicy(contentSessionContext), this.mCheckpointPolicyFactory.newCheckpointPolicy(contentSessionContext, streamIndex, contentViewPersistence));
        return streamStateImpl;
    }

    public ContentSizeEstimator newSizeEstimator(ContentSessionContext contentSessionContext, StreamIndex streamIndex, StreamReaderState streamReaderState) {
        int fragmentOverheadBytes = this.mConfig.getFragmentOverheadBytes(streamIndex.getType());
        return new ContentSizeEstimator(streamReaderState, streamIndex.getStreamDurationInNanos(), streamIndex.getType() == StreamType.SUBTITLES ? 0 : this.mQualityDefaults.getDefaultQualityLevel(contentSessionContext, streamIndex).getBitrate(), streamIndex.getNumChunks(), fragmentOverheadBytes, contentSessionContext.getSpecification().isLiveStream());
    }
}
