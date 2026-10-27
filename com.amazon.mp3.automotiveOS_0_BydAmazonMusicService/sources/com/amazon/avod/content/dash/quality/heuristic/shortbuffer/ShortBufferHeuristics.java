package com.amazon.avod.content.dash.quality.heuristic.shortbuffer;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.dash.quality.heuristic.DownloadConfig;
import com.amazon.avod.content.dash.quality.heuristic.DownloadStatus;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicAlgorithm;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackState;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsSharedInfo;
import com.amazon.avod.content.dash.quality.heuristic.StreamBehavior;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.urlvending.QoeConfigInterface;
import com.amazon.avod.media.framework.util.IntRollingStatisticsAggregator;
import com.amazon.avod.throughput.BandwidthStats;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.nio.ByteBuffer;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
@Deprecated
public class ShortBufferHeuristics implements Heuristics {
    private BandwidthStats mBandwidthStats;
    private HeuristicsCallbacks mCallbacks;
    private final DownloadConfig mDownloadConfig;
    private final Map<Integer, HeuristicsDownloadController> mDownloadControllers;
    private final HeuristicsPlaybackConfig mHeuristicsPlaybackConfig;
    private final HeuristicsSharedInfo mHeuristicsSharedInfo;
    private boolean mIsInitialized;
    private boolean mIsRunning;
    private Manifest mManifest;
    private final IntRollingStatisticsAggregator mMillisecondLatencyAggregator;
    private final Object mMutex;
    private final QoeConfigInterface mQoeConfig;
    private final String mSettingsId;

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public ByteBuffer getState() throws ContentException {
        return null;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public float getVersionInfo() {
        return 1.0f;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void setStreamIndex(int i, int i2) throws ContentException {
    }

    public ShortBufferHeuristics(DownloadConfig downloadConfig, HeuristicsPlaybackConfig heuristicsPlaybackConfig, QoeConfigInterface qoeConfigInterface, String str) {
        this(heuristicsPlaybackConfig, downloadConfig, qoeConfigInterface, str, new IntRollingStatisticsAggregator(ShortBufferHeuristicsConfig.getInstance().getNumberOfLatencySamples()));
    }

    ShortBufferHeuristics(HeuristicsPlaybackConfig heuristicsPlaybackConfig, DownloadConfig downloadConfig, QoeConfigInterface qoeConfigInterface, String str, IntRollingStatisticsAggregator intRollingStatisticsAggregator) {
        this.mHeuristicsSharedInfo = new HeuristicsSharedInfo();
        this.mDownloadControllers = Maps.newConcurrentMap();
        this.mMutex = new Object();
        this.mHeuristicsPlaybackConfig = heuristicsPlaybackConfig;
        this.mDownloadConfig = downloadConfig;
        this.mQoeConfig = qoeConfigInterface;
        this.mSettingsId = str;
        this.mMillisecondLatencyAggregator = intRollingStatisticsAggregator;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void initialize(@Nonnull HeuristicsCallbacks heuristicsCallbacks, ContentSessionContext contentSessionContext) throws ContentException {
        synchronized (this.mMutex) {
            this.mCallbacks = heuristicsCallbacks;
            this.mManifest = contentSessionContext.getManifest();
            this.mBandwidthStats = contentSessionContext.getBandwidthStats();
            this.mIsInitialized = true;
            this.mIsRunning = true;
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void release() throws ContentException {
        synchronized (this.mMutex) {
            this.mIsRunning = false;
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void setStreamHandle(int i, long j) throws ContentException {
        HeuristicsDownloadController videoDownloadController;
        synchronized (this.mMutex) {
            ConfidenceEvaluator confidenceEvaluator = new ConfidenceEvaluator();
            int i2 = AnonymousClass1.$SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[this.mManifest.getStreams().get(i).getType().ordinal()];
            if (i2 == 1) {
                videoDownloadController = new VideoDownloadController(i, this.mCallbacks, new QualitySelector(i, this.mBandwidthStats, this.mCallbacks, confidenceEvaluator, this.mHeuristicsSharedInfo), this.mBandwidthStats, confidenceEvaluator, this.mHeuristicsSharedInfo, this.mMillisecondLatencyAggregator);
            } else {
                if (i2 != 2 && i2 != 3) {
                    throw new UnsupportedOperationException("Please add logic for new stream type!");
                }
                videoDownloadController = new DummyDownloadController(i, this.mCallbacks, this.mBandwidthStats);
            }
            this.mDownloadControllers.put(Integer.valueOf(i), videoDownloadController);
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.dash.quality.heuristic.shortbuffer.ShortBufferHeuristics$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType;

        static {
            int[] iArr = new int[StreamType.values().length];
            $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType = iArr;
            try {
                iArr[StreamType.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[StreamType.AUDIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[StreamType.SUBTITLES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void pause() throws ContentException {
        synchronized (this.mMutex) {
            this.mIsRunning = false;
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void pause(int i) throws ContentException {
        synchronized (this.mMutex) {
            this.mIsRunning = false;
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void resume() throws ContentException {
        synchronized (this.mMutex) {
            this.mIsRunning = true;
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void resume(int i) throws ContentException {
        synchronized (this.mMutex) {
            this.mIsRunning = true;
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void bufferStateChanged() throws ContentException {
        synchronized (this.mMutex) {
            if (this.mIsRunning) {
                for (HeuristicsDownloadController heuristicsDownloadController : this.mDownloadControllers.values()) {
                    heuristicsDownloadController.reviewDownloadRequests();
                    heuristicsDownloadController.submitDownloadRequests();
                }
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void reportDownloadProgress(int i, int i2, long j, long j2, long j3, DownloadStatus downloadStatus, long j4) throws ContentException {
        synchronized (this.mMutex) {
            if (this.mIsRunning) {
                HeuristicsDownloadController heuristicsDownloadController = this.mDownloadControllers.get(Integer.valueOf(i));
                Preconditions.checkNotNull(heuristicsDownloadController, "invalid stream index");
                heuristicsDownloadController.reportDownloadProgress(j, j2, j3, downloadStatus, j4);
                heuristicsDownloadController.reviewDownloadRequests();
                heuristicsDownloadController.submitDownloadRequests();
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public long getLatencyAverageMillis() {
        return this.mMillisecondLatencyAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public long getBandwidthAverageBytesPerSecond() {
        long avgBandwidthBps;
        synchronized (this.mMutex) {
            avgBandwidthBps = this.mBandwidthStats != null ? r1.getAvgBandwidthBps() : 0L;
        }
        return avgBandwidthBps;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public long getLatencyStandardDeviationMillis() {
        return this.mMillisecondLatencyAggregator.getStandardDeviation();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public long getBandwidthStandardDeviationBytesPerSecond() {
        return this.mBandwidthStats.getStdBandwidthBps();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public long getBandwidthPredictionErrorAvgBytesPerSecond() {
        if (this.mBandwidthStats != null) {
            return r0.getAvgBandwidthPredictionErrorBps();
        }
        return 0L;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nonnull
    public String getSettingsId() {
        return this.mSettingsId;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nullable
    public HeuristicsPlaybackConfig getHeuristicsPlaybackConfig() {
        return this.mHeuristicsPlaybackConfig;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nonnull
    public DownloadConfig getDownloadConfig() {
        return this.mDownloadConfig;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nonnull
    public QoeConfigInterface getQoeConfig() {
        return this.mQoeConfig;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    public void onPlaybackStateChange(HeuristicsPlaybackState heuristicsPlaybackState) {
        synchronized (this.mMutex) {
            this.mHeuristicsSharedInfo.setPlaybackState(heuristicsPlaybackState);
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nonnull
    public StreamBehavior getStreamBehavior() {
        return StreamBehavior.DEFAULT;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.Heuristics
    @Nonnull
    public HeuristicAlgorithm getAlgorithm() {
        return HeuristicAlgorithm.LEGACY_SBH;
    }
}
