package com.amazon.avod.content.downloading;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.event.ContentEventSecondaryDownloadEligibilityStateChanged;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class StreamingContentSessionDownloadAvailabilityController implements DownloadAvailabilityController {
    private final ContentManagementEventBus mEventDispatcher;
    private final Heuristics mHeuristics;
    private final HeuristicsPlaybackConfig mHeuristicsConfig;
    private final Object mMutex;
    private int mSecondaryDownloadCancellationCount;
    private boolean mSecondaryDownloadsAllowed;
    private final ContentSessionContext mSessionContext;
    private final boolean mShouldListenForPrimaryDownloadEvents;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig;

    public StreamingContentSessionDownloadAvailabilityController(@Nonnull Heuristics heuristics, @Nonnull ContentSessionContext contentSessionContext, @Nonnull ContentManagementEventBus contentManagementEventBus) {
        this((Heuristics) Preconditions.checkNotNull(heuristics, "heuristics"), contentSessionContext, contentManagementEventBus, heuristics.getHeuristicsPlaybackConfig(), SmoothStreamingPlaybackConfig.INSTANCE);
    }

    StreamingContentSessionDownloadAvailabilityController(@Nonnull Heuristics heuristics, @Nonnull ContentSessionContext contentSessionContext, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull HeuristicsPlaybackConfig heuristicsPlaybackConfig, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mMutex = new Object();
        this.mHeuristics = (Heuristics) Preconditions.checkNotNull(heuristics, "heuristics");
        this.mSessionContext = (ContentSessionContext) Preconditions.checkNotNull(contentSessionContext, "sessionContext");
        this.mEventDispatcher = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "eventDispatcher");
        this.mHeuristicsConfig = (HeuristicsPlaybackConfig) Preconditions.checkNotNull(heuristicsPlaybackConfig, "HeuristicsPlaybackConfig");
        SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig2 = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "smoothStreamingPlaybackConfig");
        this.mSmoothStreamingPlaybackConfig = smoothStreamingPlaybackConfig2;
        this.mShouldListenForPrimaryDownloadEvents = smoothStreamingPlaybackConfig2.shouldListenForPrimaryDownloadEvents();
    }

    @Override // com.amazon.avod.content.downloading.DownloadAvailabilityController
    public void onPrimaryDownloadStateChange(boolean z) {
        if (this.mShouldListenForPrimaryDownloadEvents) {
            synchronized (this.mMutex) {
                this.mSecondaryDownloadsAllowed = !z;
            }
        }
    }

    @Override // com.amazon.avod.content.downloading.DownloadAvailabilityController
    public boolean onBufferSizeChange(@Nonnull DownloadService downloadService, long j, boolean z) {
        boolean z2;
        synchronized (this.mMutex) {
            Preconditions.checkNotNull(downloadService, "downloadService");
            int maxAuxiliaryRequestsCancellation = this.mHeuristicsConfig.getMaxAuxiliaryRequestsCancellation();
            if (this.mSecondaryDownloadCancellationCount >= maxAuxiliaryRequestsCancellation) {
                this.mSecondaryDownloadsAllowed = false;
                return false;
            }
            long bandwidthAverageBytesPerSecond = this.mHeuristics.getBandwidthAverageBytesPerSecond();
            if (this.mSecondaryDownloadsAllowed) {
                z2 = DataUnit.BYTES.toKiloBits((float) bandwidthAverageBytesPerSecond) >= ((float) this.mHeuristicsConfig.getMinBandwidthToMaintainAuxiliaryDownloadKbps()) && j > TimeUnit.SECONDS.toNanos((long) this.mHeuristicsConfig.getMinBufferFullnessToMaintainAuxiliaryDownloadSeconds());
            } else {
                if (DataUnit.BYTES.toKiloBits(bandwidthAverageBytesPerSecond) >= this.mHeuristicsConfig.getMinBandwidthToStartAuxiliaryDownloadKbps() && j > TimeUnit.SECONDS.toNanos(this.mHeuristicsConfig.getMinBufferFullnessToStartAuxiliaryDownloadSeconds())) {
                }
            }
            DLog.devf("AuxiliaryDownloadsAllowed %s AverageBW(Bps) %d", Boolean.valueOf(z2), Long.valueOf(bandwidthAverageBytesPerSecond));
            if (this.mSecondaryDownloadsAllowed ^ z2) {
                this.mSecondaryDownloadsAllowed = z2;
                if (!z2 && !z) {
                    this.mSecondaryDownloadCancellationCount++;
                }
                DLog.devf("Secondary downloads allowed state changed to %s, number of cancellations in this session %s", Boolean.valueOf(z2), Integer.valueOf(this.mSecondaryDownloadCancellationCount));
                this.mEventDispatcher.postEvent(new ContentEventSecondaryDownloadEligibilityStateChanged(this.mSecondaryDownloadsAllowed, z, this.mSecondaryDownloadCancellationCount >= maxAuxiliaryRequestsCancellation, downloadService.enableSecondaryDownloads(this.mSecondaryDownloadsAllowed, z), this.mSessionContext.getContent(), this.mSessionContext.getSessionType()));
            }
            return this.mSecondaryDownloadsAllowed;
        }
    }
}
