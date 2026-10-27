package com.amazon.avod.content.dash.quality.heuristic;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.downloading.CompositeDownloadTask;
import com.amazon.avod.content.smoothstream.downloading.DownloadTaskAdapter;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CompositeHeuristicsCallbacks extends CompositeDownloadTask implements HeuristicsCallbacks {
    private ImmutableMap<Integer, HeuristicsCallbacks> mCompositeFragmentDownloader;
    private HeuristicsCallbacks mFirstCallbacks = null;
    private final Heuristics mHeuristics;

    public CompositeHeuristicsCallbacks(@Nonnull Heuristics heuristics) {
        this.mHeuristics = (Heuristics) Preconditions.checkNotNull(heuristics, "heuristics");
    }

    public void setDownloadTaskAdapters(@Nonnull ImmutableList<DownloadTaskAdapter> immutableList, @Nonnull ImmutableMap<Integer, HeuristicsCallbacks> immutableMap) throws ContentException {
        super.setDownloadTasks(immutableList);
        ImmutableMap<Integer, HeuristicsCallbacks> immutableMap2 = (ImmutableMap) Preconditions.checkNotNull(immutableMap, "callbacks");
        this.mCompositeFragmentDownloader = immutableMap2;
        this.mFirstCallbacks = immutableMap2.values().iterator().next();
        UnmodifiableIterator<Integer> it = this.mCompositeFragmentDownloader.keySet().iterator();
        while (it.hasNext()) {
            this.mHeuristics.setStreamIndex(it.next().intValue(), 0);
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public boolean cancelDownload(int i, int i2, long j) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.cancelDownload(i, i2, j);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return false;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long startDownload(int i, int i2, int i3, long j, long j2, FragmentType fragmentType) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.startDownload(i, i2, i3, j, j2, fragmentType);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -2L;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long bufferedDuration(int i) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.bufferedDuration(i);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return 0L;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long maxAvailableBufferDuration(int i) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.maxAvailableBufferDuration(i);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return 0L;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long availableBufferSize(int i) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.availableBufferSize(i);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return 0L;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int missingFragment(int i, int i2) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.missingFragment(i, i2);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -1;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int highestQualityObtained(int i, int i2) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.highestQualityObtained(i, i2);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -1;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int consumptionHead(int i) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.consumptionHead(i);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -1;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int streamBehavior(int i) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.streamBehavior(i);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -1;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long fragmentStartTime(int i, int i2) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.fragmentStartTime(i, i2);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -1L;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long fragmentDurationTime(int i, int i2) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.fragmentDurationTime(i, i2);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -1L;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int fragmentQualityCount(int i, int i2) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.fragmentQualityCount(i, i2);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -1;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int fragmentBitrate(int i, int i2, int i3) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.fragmentBitrate(i, i2, i3);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -1;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long fragmentSize(int i, int i2, int i3) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.fragmentSize(i, i2, i3);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -1L;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int fragmentCount(int i) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.fragmentCount(i);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -1;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int averageBandwidthBps() {
        HeuristicsCallbacks heuristicsCallbacks = this.mFirstCallbacks;
        if (heuristicsCallbacks == null) {
            DLog.warnf("No callback found. Callbacks might not be initialized");
            return -1;
        }
        return heuristicsCallbacks.averageBandwidthBps();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int streamType(int i) {
        HeuristicsCallbacks heuristicsCallbacks = this.mCompositeFragmentDownloader.get(Integer.valueOf(i));
        if (heuristicsCallbacks != null) {
            return heuristicsCallbacks.streamType(i);
        }
        DLog.warnf("Unexpected streamIndex: %s", Integer.valueOf(i));
        return -1;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public void overridePlaybackConfigsCallback(@Nonnull Map<String, String> map) {
        HeuristicsCallbacks heuristicsCallbacks = this.mFirstCallbacks;
        if (heuristicsCallbacks == null) {
            DLog.warnf("No callback found. Callbacks might not be initialized");
        } else {
            heuristicsCallbacks.overridePlaybackConfigsCallback(map);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.CompositeDownloadTask
    public void start() {
        super.start();
        try {
            this.mHeuristics.resume();
        } catch (ContentException e) {
            DLog.exceptionf(e, "Failed to start the heuristics engine", new Object[0]);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.CompositeDownloadTask
    public void cancel() {
        try {
            this.mHeuristics.pause();
            this.mHeuristics.release();
        } catch (ContentException e) {
            DLog.exceptionf(e, "Failed to stop the heuristics engine", new Object[0]);
        }
        super.cancel();
    }
}
