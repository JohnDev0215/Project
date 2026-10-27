package com.amazon.avod.content.smoothstream.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.downloading.DownloadTask;
import com.amazon.avod.content.smoothstream.FragmentStreamRequestResult;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.streamstate.ContentSizeEstimator;
import com.amazon.avod.content.smoothstream.streamstate.StreamReaderState;
import com.amazon.avod.content.smoothstream.streamstate.StreamStateObserver;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadTaskAdapter implements DownloadTask {
    private static final TimeSpan MAX_WAIT_TIME = TimeSpan.fromSeconds(1.0d);
    private final ContentSessionContext mContext;
    private TaskState mCurrentState;
    private final TimeSpan mMaxWaitTime;
    private final Object mMonitor;
    private final StreamStateObserver mObserver;
    private final ContentSizeEstimator mSizeEstimator;
    private final ConcurrentFragmentDownloader mStreamDownloader;
    private final StreamIndex mStreamIndex;
    private final StreamReaderState mStreamState;

    private enum TaskState {
        NOT_STARTED,
        PAUSED,
        RUNNING,
        SHUTDOWN
    }

    public DownloadTaskAdapter(ContentSessionContext contentSessionContext, StreamIndex streamIndex, ContentSizeEstimator contentSizeEstimator, StreamReaderState streamReaderState, ConcurrentFragmentDownloader concurrentFragmentDownloader) {
        this(contentSessionContext, streamIndex, contentSizeEstimator, streamReaderState, concurrentFragmentDownloader, MAX_WAIT_TIME);
    }

    public DownloadTaskAdapter(ContentSessionContext contentSessionContext, StreamIndex streamIndex, ContentSizeEstimator contentSizeEstimator, StreamReaderState streamReaderState, ConcurrentFragmentDownloader concurrentFragmentDownloader, TimeSpan timeSpan) {
        this.mMonitor = new Object();
        this.mCurrentState = TaskState.NOT_STARTED;
        StreamStateObserver streamStateObserver = new StreamStateObserver() { // from class: com.amazon.avod.content.smoothstream.downloading.DownloadTaskAdapter.1
            @Override // com.amazon.avod.content.smoothstream.streamstate.StreamStateObserver
            public void onStreamPositionChanged() {
            }

            @Override // com.amazon.avod.content.smoothstream.streamstate.StreamStateObserver
            public void onStreamStateChanged() {
                synchronized (DownloadTaskAdapter.this.mMonitor) {
                    DownloadTaskAdapter.this.mMonitor.notifyAll();
                }
            }
        };
        this.mObserver = streamStateObserver;
        this.mContext = contentSessionContext;
        this.mStreamIndex = streamIndex;
        this.mSizeEstimator = contentSizeEstimator;
        this.mStreamState = streamReaderState;
        streamReaderState.registerObserver(streamStateObserver);
        this.mStreamDownloader = concurrentFragmentDownloader;
        this.mMaxWaitTime = timeSpan;
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getDownloadedTimeAfterPositionInNanos(long j) {
        return this.mSizeEstimator.getDownloadedTimeAfterPositionInNanos(j);
    }

    @Override // com.amazon.avod.content.LocalContentState
    public boolean isFullyDownloadedFromNanos(long j) {
        return this.mSizeEstimator.isFullyDownloadedFromNanos(j);
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getRemainingNeededSizeInBytes() {
        return this.mSizeEstimator.getRemainingNeededSizeInBytes();
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getTotalNeededSizeInBytes() {
        return this.mSizeEstimator.getTotalNeededSizeInBytes();
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getFirstAvailableTimeInNanos() {
        return this.mSizeEstimator.getFirstAvailableTimeInNanos();
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getLastAvailableTimeInNanos() {
        return this.mSizeEstimator.getLastAvailableTimeInNanos();
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    @Nullable
    public FragmentStreamRequestResult requestFragment(@Nonnull SmoothStreamingURI smoothStreamingURI) throws ContentException {
        Preconditions.checkArgument(canServiceRequest(smoothStreamingURI), "Can't service this request!");
        while (isActive()) {
            FragmentStreamRequestResult fragment = this.mStreamState.getFragment(smoothStreamingURI);
            if (fragment != null) {
                return fragment;
            }
            synchronized (this.mMonitor) {
                try {
                    try {
                        this.mMonitor.wait(this.mMaxWaitTime.getTotalMilliseconds());
                    } catch (InterruptedException unused) {
                        return null;
                    }
                } finally {
                }
            }
        }
        return null;
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public void notifyFragmentCorrupt(SmoothStreamingURI smoothStreamingURI) {
        Preconditions.checkArgument(canServiceRequest(smoothStreamingURI), "Can't service this request!");
        this.mStreamState.markAsErrored(smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public synchronized void start() {
        TaskState taskState = this.mCurrentState;
        TaskState taskState2 = TaskState.RUNNING;
        if (taskState == taskState2) {
            return;
        }
        this.mCurrentState = taskState2;
        this.mStreamDownloader.start();
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public synchronized void cancel() {
        if (this.mCurrentState == TaskState.RUNNING) {
            this.mStreamDownloader.stop();
        }
        this.mCurrentState = TaskState.SHUTDOWN;
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public void seek(long j) {
        this.mStreamState.onSeek(j);
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public StreamType getStreamType() {
        return this.mStreamIndex.getType();
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public synchronized void onContentContextChanged() {
        if (isActive()) {
            boolean z = !this.mContext.getState().isPlaybackRestrictedToBufferedContent();
            TaskState taskState = this.mCurrentState;
            TaskState taskState2 = TaskState.RUNNING;
            if (taskState == taskState2 && !z) {
                this.mCurrentState = TaskState.PAUSED;
                this.mStreamDownloader.stop();
            } else if (taskState == TaskState.PAUSED && z) {
                this.mCurrentState = taskState2;
                this.mStreamDownloader.start();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized boolean isActive() {
        /*
            r2 = this;
            monitor-enter(r2)
            com.amazon.avod.content.smoothstream.downloading.DownloadTaskAdapter$TaskState r0 = r2.mCurrentState     // Catch: java.lang.Throwable -> L11
            com.amazon.avod.content.smoothstream.downloading.DownloadTaskAdapter$TaskState r1 = com.amazon.avod.content.smoothstream.downloading.DownloadTaskAdapter.TaskState.RUNNING     // Catch: java.lang.Throwable -> L11
            if (r0 == r1) goto Le
            com.amazon.avod.content.smoothstream.downloading.DownloadTaskAdapter$TaskState r1 = com.amazon.avod.content.smoothstream.downloading.DownloadTaskAdapter.TaskState.PAUSED     // Catch: java.lang.Throwable -> L11
            if (r0 != r1) goto Lc
            goto Le
        Lc:
            r0 = 0
            goto Lf
        Le:
            r0 = 1
        Lf:
            monitor-exit(r2)
            return r0
        L11:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.smoothstream.downloading.DownloadTaskAdapter.isActive():boolean");
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public boolean canServiceRequest(SmoothStreamingURI smoothStreamingURI) {
        return smoothStreamingURI.getStream().getType() == this.mStreamIndex.getType();
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public int getAverageVideoBandwidthBps() {
        if (this.mStreamIndex.isVideo()) {
            return this.mStreamDownloader.getBandwidthBps();
        }
        return 0;
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public void restrictToQuality(@Nonnull QualityLevel qualityLevel, long j) {
        if (this.mStreamIndex.isVideo()) {
            this.mStreamState.restrictToQuality(qualityLevel, j);
        }
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public void releaseFragment(FragmentStreamRequestResult fragmentStreamRequestResult) {
        this.mStreamState.releaseFragment(fragmentStreamRequestResult);
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public void dispose() {
        this.mStreamDownloader.dispose();
    }

    @Override // com.amazon.avod.content.LocalContentState
    public boolean hasUpdatesForContentView() {
        return this.mStreamDownloader.hasUpdatesForContentView();
    }

    @Override // com.amazon.avod.content.LocalContentState
    public boolean hasDownloadErrors() {
        return this.mStreamDownloader.hasDownloadErrors();
    }

    @Override // com.amazon.avod.content.downloading.DownloadTask
    public void notifyLivePointUpdated(long j) {
        this.mStreamState.notifyLivePointUpdated(j);
    }
}
