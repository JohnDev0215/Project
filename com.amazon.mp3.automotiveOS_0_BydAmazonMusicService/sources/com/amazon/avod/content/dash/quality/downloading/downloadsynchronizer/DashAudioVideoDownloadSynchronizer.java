package com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer;

import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class DashAudioVideoDownloadSynchronizer implements DownloadSynchronizer {
    private final long mMaxDeltaInNanos;
    private final Object mMutex = new Object();
    private long mLastVideoFragmentDownloadedPresentationTimeInNanos = -1;
    private long mResumeTimeNanos = -1;

    public DashAudioVideoDownloadSynchronizer(long j) {
        Preconditions.checkArgument(j > 0, "delta time should be greater than 0");
        DLog.devf("DashAudioVideoDownloadSynchronizer is initialized with %s as maxDeltaSeconds", Long.valueOf(j));
        this.mMaxDeltaInNanos = TimeSpan.fromSeconds(j).getTotalNanoSeconds();
    }

    @Override // com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer.DownloadSynchronizer
    public void onSeeked(long j) {
        synchronized (this.mMutex) {
            this.mLastVideoFragmentDownloadedPresentationTimeInNanos = -1L;
            this.mResumeTimeNanos = j;
        }
    }

    @Override // com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer.DownloadSynchronizer
    public void onFragmentDownloaded(SmoothStreamingURI smoothStreamingURI) {
        synchronized (this.mMutex) {
            long presentationTimeInNanos = smoothStreamingURI.getPresentationTimeInNanos();
            if (smoothStreamingURI.isVideo() && !smoothStreamingURI.isInitFragment() && presentationTimeInNanos > this.mLastVideoFragmentDownloadedPresentationTimeInNanos) {
                this.mLastVideoFragmentDownloadedPresentationTimeInNanos = presentationTimeInNanos;
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer.DownloadSynchronizer
    public boolean shouldDownload(SmoothStreamingURI smoothStreamingURI) {
        synchronized (this.mMutex) {
            boolean z = true;
            if (smoothStreamingURI.isAudio() && !smoothStreamingURI.isInitFragment()) {
                long presentationTimeInNanos = smoothStreamingURI.getPresentationTimeInNanos();
                long j = this.mLastVideoFragmentDownloadedPresentationTimeInNanos;
                if (j == -1) {
                    j = this.mResumeTimeNanos;
                }
                if (j == -1) {
                    return true;
                }
                if (presentationTimeInNanos - j >= this.mMaxDeltaInNanos) {
                    z = false;
                }
                return z;
            }
            return true;
        }
    }
}
