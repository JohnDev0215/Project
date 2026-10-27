package com.amazon.avod.content.dash.quality.heuristic.shortbuffer;

import com.amazon.avod.content.dash.quality.heuristic.DownloadStatus;
import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.throughput.BandwidthStats;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class DummyDownloadController implements HeuristicsDownloadController {
    private final BandwidthStats mBandwidthStats;
    private final HeuristicsCallbacks mCallbacks;
    private final int mConcurrentDownloadRequests;
    private final ShortBufferHeuristicsConfig mConfig;
    private final HeuristicsDownloadInfoList mHeuristicsDownloadInfoList;
    private final int mNumberOfFutureFragmentsToCheck;
    private final int mStreamIndex;
    private final long mTimeoutNanos;

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.HeuristicsDownloadController
    public void reviewDownloadRequests() {
    }

    DummyDownloadController(int i, HeuristicsCallbacks heuristicsCallbacks, BandwidthStats bandwidthStats) {
        this(i, heuristicsCallbacks, bandwidthStats, ShortBufferHeuristicsConfig.getInstance());
    }

    DummyDownloadController(int i, HeuristicsCallbacks heuristicsCallbacks, BandwidthStats bandwidthStats, ShortBufferHeuristicsConfig shortBufferHeuristicsConfig) {
        this.mHeuristicsDownloadInfoList = new HeuristicsDownloadInfoList();
        this.mStreamIndex = i;
        this.mCallbacks = heuristicsCallbacks;
        this.mBandwidthStats = bandwidthStats;
        this.mConfig = shortBufferHeuristicsConfig;
        this.mConcurrentDownloadRequests = shortBufferHeuristicsConfig.getAudioSubtitleConcurrentDownloadRequests();
        this.mNumberOfFutureFragmentsToCheck = shortBufferHeuristicsConfig.getNumberOfFutureFragmentsToCheck();
        this.mTimeoutNanos = shortBufferHeuristicsConfig.getAudioSubtitleDownloadTimeout().getTotalNanoSeconds();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.HeuristicsDownloadController
    public void submitDownloadRequests() {
        int i = 0;
        while (this.mHeuristicsDownloadInfoList.size() < this.mConcurrentDownloadRequests && i < this.mNumberOfFutureFragmentsToCheck) {
            int iMissingFragment = this.mCallbacks.missingFragment(this.mStreamIndex, i);
            i++;
            if (iMissingFragment < 0) {
                return;
            }
            if (!this.mHeuristicsDownloadInfoList.contains(iMissingFragment)) {
                int iFragmentSize = (int) this.mCallbacks.fragmentSize(this.mStreamIndex, iMissingFragment, 0);
                long jStartDownload = this.mCallbacks.startDownload(this.mStreamIndex, iMissingFragment, 0, this.mTimeoutNanos / 100, this.mBandwidthStats.getAvgBandwidthBps(), FragmentType.DEFAULT);
                if (jStartDownload > -1) {
                    this.mHeuristicsDownloadInfoList.add(new HeuristicsDownloadInfo(iMissingFragment, jStartDownload, iFragmentSize, System.currentTimeMillis(), (int) new TimeSpan(this.mTimeoutNanos).getTotalMilliseconds()));
                }
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.shortbuffer.HeuristicsDownloadController
    public void reportDownloadProgress(long j, long j2, long j3, DownloadStatus downloadStatus, long j4) {
        if (downloadStatus != DownloadStatus.PartSuccess) {
            this.mHeuristicsDownloadInfoList.remove(j);
        }
    }
}
