package com.amazon.avod.content.dash.quality.heuristic.shortbuffer;

import com.amazon.avod.content.dash.quality.heuristic.DownloadStatus;
import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackState;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsSharedInfo;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.util.IntRollingStatisticsAggregator;
import com.amazon.avod.throughput.BandwidthStats;
import com.amazon.avod.util.DLog;
import com.google.common.collect.Lists;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class VideoDownloadController implements HeuristicsDownloadController {
    private final BandwidthStats mBandwidthStats;
    private final HeuristicsCallbacks mCallbacks;
    private final ConfidenceEvaluator mConfidenceEvaluator;
    private final long mDownloadReservedTimeNanos;
    private final long mDownloadTimeoutWhenNotPlayingNanos;
    private final float mFragmentSizeOverhead;
    private final HeuristicsDownloadInfoList mHeuristicsDownloadInfoList;
    private final HeuristicsSharedInfo mHeuristicsSharedInfo;
    private final long mMaxDownloadTimeoutNanos;
    private final IntRollingStatisticsAggregator mMillisecondLatencyAggregator;
    private final long mMinDownloadTimeoutNanos;
    private final int mNumberOfConcurrentDownloadRequests;
    private final int mNumberOfFutureFragmentToCheck;
    private final QualitySelector mQualitySelector;
    private final int mStreamIndex;
    private final int mUnReviewTimeForDownloadToStartMillis;

    VideoDownloadController(int i, HeuristicsCallbacks heuristicsCallbacks, QualitySelector qualitySelector, BandwidthStats bandwidthStats, ConfidenceEvaluator confidenceEvaluator, HeuristicsSharedInfo heuristicsSharedInfo, IntRollingStatisticsAggregator intRollingStatisticsAggregator) {
        this(i, heuristicsCallbacks, qualitySelector, bandwidthStats, confidenceEvaluator, ShortBufferHeuristicsConfig.getInstance(), heuristicsSharedInfo, intRollingStatisticsAggregator);
    }

    VideoDownloadController(int i, HeuristicsCallbacks heuristicsCallbacks, QualitySelector qualitySelector, BandwidthStats bandwidthStats, ConfidenceEvaluator confidenceEvaluator, ShortBufferHeuristicsConfig shortBufferHeuristicsConfig, HeuristicsSharedInfo heuristicsSharedInfo, IntRollingStatisticsAggregator intRollingStatisticsAggregator) {
        this.mHeuristicsDownloadInfoList = new HeuristicsDownloadInfoList();
        this.mStreamIndex = i;
        this.mCallbacks = heuristicsCallbacks;
        this.mQualitySelector = qualitySelector;
        this.mBandwidthStats = bandwidthStats;
        this.mConfidenceEvaluator = confidenceEvaluator;
        this.mHeuristicsSharedInfo = heuristicsSharedInfo;
        this.mNumberOfFutureFragmentToCheck = shortBufferHeuristicsConfig.getNumberOfFutureFragmentsToCheck();
        this.mNumberOfConcurrentDownloadRequests = shortBufferHeuristicsConfig.getVideoConcurrentDownloadRequests();
        this.mMinDownloadTimeoutNanos = shortBufferHeuristicsConfig.getMinVideoDownloadTimeout().getTotalNanoSeconds();
        this.mMaxDownloadTimeoutNanos = shortBufferHeuristicsConfig.getMaxVideoDownloadTimeout().getTotalNanoSeconds();
        this.mDownloadReservedTimeNanos = shortBufferHeuristicsConfig.getVideoDownloadReservedTime().getTotalNanoSeconds();
        this.mFragmentSizeOverhead = shortBufferHeuristicsConfig.getFragmentSizeOverhead();
        this.mUnReviewTimeForDownloadToStartMillis = (int) shortBufferHeuristicsConfig.getUnReviewTime().getTotalMilliseconds();
        this.mDownloadTimeoutWhenNotPlayingNanos = shortBufferHeuristicsConfig.getVideoDownloadTimeoutWhenNotPlaying().getTotalNanoSeconds();
        this.mMillisecondLatencyAggregator = intRollingStatisticsAggregator;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.HeuristicsDownloadController
    public void submitDownloadRequests() {
        int i = 0;
        while (this.mHeuristicsDownloadInfoList.size() < this.mNumberOfConcurrentDownloadRequests && i < this.mNumberOfFutureFragmentToCheck) {
            int iMissingFragment = this.mCallbacks.missingFragment(this.mStreamIndex, i);
            i++;
            if (iMissingFragment < 0) {
                return;
            }
            if (!this.mHeuristicsDownloadInfoList.contains(iMissingFragment)) {
                long jBufferedDuration = this.mCallbacks.bufferedDuration(this.mStreamIndex) * 100;
                long jMin = Math.min(Math.max(jBufferedDuration - this.mDownloadReservedTimeNanos, this.mMinDownloadTimeoutNanos), this.mMaxDownloadTimeoutNanos);
                if (this.mHeuristicsSharedInfo.getPlaybackState() != HeuristicsPlaybackState.Playing && this.mBandwidthStats.getAvgBandwidthBps() > 0) {
                    jMin = Math.max(jMin, this.mDownloadTimeoutWhenNotPlayingNanos);
                }
                long j = jMin;
                int i2 = (int) (jBufferedDuration / 1000000);
                int iSelect = this.mQualitySelector.select(i2, iMissingFragment);
                int iFragmentSize = (int) (((int) this.mCallbacks.fragmentSize(this.mStreamIndex, iMissingFragment, iSelect)) * this.mFragmentSizeOverhead);
                long jStartDownload = this.mCallbacks.startDownload(this.mStreamIndex, iMissingFragment, iSelect, j / 100, this.mBandwidthStats.getAvgBandwidthBps(), FragmentType.DEFAULT);
                if (jStartDownload > -1) {
                    HeuristicsDownloadInfo heuristicsDownloadInfo = new HeuristicsDownloadInfo(iMissingFragment, jStartDownload, iFragmentSize, System.currentTimeMillis(), (int) new TimeSpan(j).getTotalMilliseconds());
                    this.mHeuristicsDownloadInfoList.add(heuristicsDownloadInfo);
                    DLog.logf("Requested to download: %s | front buffer: %s", heuristicsDownloadInfo, Integer.valueOf(i2));
                }
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.HeuristicsDownloadController
    public void reviewDownloadRequests() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList<HeuristicsDownloadInfo> arrayListNewArrayList = Lists.newArrayList();
        int avgBandwidthBps = this.mBandwidthStats.getAvgBandwidthBps();
        for (HeuristicsDownloadInfo heuristicsDownloadInfo : this.mHeuristicsDownloadInfoList) {
            if (avgBandwidthBps > 0) {
                long startedTimeMillis = jCurrentTimeMillis - heuristicsDownloadInfo.getStartedTimeMillis();
                if (startedTimeMillis >= this.mUnReviewTimeForDownloadToStartMillis) {
                    if ((((long) avgBandwidthBps) * (((long) heuristicsDownloadInfo.getTimeAllowedMillis()) - startedTimeMillis)) / 1000 < heuristicsDownloadInfo.getSizeInBytes() - heuristicsDownloadInfo.getBytesDownloaded()) {
                        arrayListNewArrayList.add(heuristicsDownloadInfo);
                    }
                    DLog.devf("Downloading request: %s | bw Bps: %s | spent millis: %s", heuristicsDownloadInfo, Integer.valueOf(avgBandwidthBps), Long.valueOf(startedTimeMillis));
                }
            }
        }
        for (HeuristicsDownloadInfo heuristicsDownloadInfo2 : arrayListNewArrayList) {
            this.mConfidenceEvaluator.downloadFailure();
            this.mCallbacks.cancelDownload(this.mStreamIndex, heuristicsDownloadInfo2.getFragmentIndex(), heuristicsDownloadInfo2.getDownloadHandle());
            this.mHeuristicsDownloadInfoList.remove(heuristicsDownloadInfo2.getDownloadHandle());
            DLog.logf("Cancelled request: %s | bw Bps: %s", heuristicsDownloadInfo2, Integer.valueOf(avgBandwidthBps));
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.HeuristicsDownloadController
    public void reportDownloadProgress(long j, long j2, long j3, DownloadStatus downloadStatus, long j4) {
        if (downloadStatus == DownloadStatus.PartSuccess) {
            HeuristicsDownloadInfo byDownloadHandle = this.mHeuristicsDownloadInfoList.getByDownloadHandle(j);
            if (byDownloadHandle != null) {
                if (byDownloadHandle.getBytesDownloaded() == 0 && j4 > -1) {
                    int sizeInBytes = byDownloadHandle.getSizeInBytes();
                    byDownloadHandle.setSizeInBytes((int) j4);
                    DLog.logf("Updated request size: old value: %s | request %s", Integer.valueOf(sizeInBytes), byDownloadHandle);
                }
                if (byDownloadHandle.getBytesDownloaded() == 0 && j3 > 0) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - byDownloadHandle.getStartedTimeMillis();
                    if (jCurrentTimeMillis > 0 && jCurrentTimeMillis < 2147483647L) {
                        this.mMillisecondLatencyAggregator.addSample((int) jCurrentTimeMillis);
                    }
                }
                byDownloadHandle.setBytesDownloaded((int) j3);
            }
        } else {
            DLog.logf("Removed request: %s | Reason: %s | download handle: %s", this.mHeuristicsDownloadInfoList.remove(j), downloadStatus, Long.valueOf(j));
        }
        if (downloadStatus == DownloadStatus.Success) {
            this.mConfidenceEvaluator.downloadSuccess();
        } else if (downloadStatus == DownloadStatus.Timeout) {
            this.mConfidenceEvaluator.downloadFailure();
        }
    }
}
