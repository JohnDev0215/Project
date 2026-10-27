package com.amazon.avod.content.urlvending;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.util.IntRollingStatisticsAggregator;
import com.amazon.avod.media.playback.util.SlidingWindowEventDurationTracker;
import com.amazon.avod.media.playback.util.SlidingWindowEventTracker;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QoeAggregator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 M2\u00020\u0001:\u0001MB\u0017\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010J\u001a\u00020I¢\u0006\u0004\bK\u0010LJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0002H\u0002J \u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016J\b\u0010\u0010\u001a\u00020\u0004H\u0016R\u001a\u0010\u0012\u001a\u00020\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u0018R\u0014\u0010\u001c\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u0018R\u0014\u0010\u001d\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u0018R\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010\u0018R\u0014\u0010%\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010\u0018R\u0014\u0010&\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010\u0018R\u0014\u0010'\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010\u0018R\u0014\u0010(\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010\u0018R\"\u0010)\u001a\u00020\b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0014\u00100\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010,R\u0014\u00102\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u0010,R\u0014\u00104\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010,R\u0014\u00106\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u0010,R\u0014\u00108\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u0010,R\u0014\u0010:\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010,R\u0014\u0010<\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010,R\u0014\u0010>\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010,R\u0014\u0010@\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010,R\u0014\u0010B\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bA\u0010,R\u0014\u0010D\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010,R\u0014\u0010F\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bE\u0010,R\u0014\u0010H\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010,¨\u0006N"}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeAggregator;", "Lcom/amazon/avod/content/urlvending/QoeAggregatorInterface;", "", "bandwidthBitsPerSecond", "", "updateBandwidthRelatedInfo", "timeToTheFirstByteInNanos", "updateLatencyRelatedInfo", "", "bitrate", "handleFragmentDownloadedEvent", "latencyInNanos", "handleManifestDownloadedEvent", "bufferFullnessInNanos", "handleBufferFullnessChangedEvent", "onBufferingStart", "onBufferingStop", "", "networkName", "Ljava/lang/String;", "getNetworkName", "()Ljava/lang/String;", "Lcom/amazon/avod/media/framework/util/IntRollingStatisticsAggregator;", "avgBandwidthBpsAggregator", "Lcom/amazon/avod/media/framework/util/IntRollingStatisticsAggregator;", "avgLatencyMillisAggregator", "bandwidthChangeBpsAggregator", "latencyChangeMillisAggregator", "bitrateBpsAggregator", "frontBufferSizeMillisAggregator", "Lcom/amazon/avod/media/playback/util/SlidingWindowEventTracker;", "bufferingEventCountTracker", "Lcom/amazon/avod/media/playback/util/SlidingWindowEventTracker;", "Lcom/amazon/avod/media/playback/util/SlidingWindowEventDurationTracker;", "bufferingEventDurationTracker", "Lcom/amazon/avod/media/playback/util/SlidingWindowEventDurationTracker;", "avgManifestLatencyMillisAggregator", "manifestLatencyChangeMillisAggregator", "latencyMillisAggregator", "bandwidthBpsAggregator", "manifestLatencyMillisAggregator", "sampleCount", "I", "getSampleCount", "()I", "setSampleCount", "(I)V", "getBitrateBps", "bitrateBps", "getFrontBufferSizeMillis", "frontBufferSizeMillis", "getAvgBandwidthBps", "avgBandwidthBps", "getBandwidthChangeBps", "bandwidthChangeBps", "getAvgLatencyMillis", "avgLatencyMillis", "getLatencyChangeMillis", "latencyChangeMillis", "getTimeSpentBufferingMillis", "timeSpentBufferingMillis", "getNumOfBufferingEvent", "numOfBufferingEvent", "getAvgManifestLatencyMillis", "avgManifestLatencyMillis", "getManifestLatencyChangeMillis", "manifestLatencyChangeMillis", "getLatencyMillis", "latencyMillis", "getBandwidthBps", "bandwidthBps", "getManifestLatencyMillis", "manifestLatencyMillis", "Lcom/amazon/avod/content/urlvending/QoeConfigInterface;", "qoeConfig", "<init>", "(Ljava/lang/String;Lcom/amazon/avod/content/urlvending/QoeConfigInterface;)V", "Companion", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0})
public class QoeAggregator implements QoeAggregatorInterface {
    private final IntRollingStatisticsAggregator avgBandwidthBpsAggregator;
    private final IntRollingStatisticsAggregator avgLatencyMillisAggregator;
    private final IntRollingStatisticsAggregator avgManifestLatencyMillisAggregator;
    private final IntRollingStatisticsAggregator bandwidthBpsAggregator;
    private final IntRollingStatisticsAggregator bandwidthChangeBpsAggregator;
    private final IntRollingStatisticsAggregator bitrateBpsAggregator;
    private final SlidingWindowEventTracker bufferingEventCountTracker;
    private final SlidingWindowEventDurationTracker bufferingEventDurationTracker;
    private final IntRollingStatisticsAggregator frontBufferSizeMillisAggregator;
    private final IntRollingStatisticsAggregator latencyChangeMillisAggregator;
    private final IntRollingStatisticsAggregator latencyMillisAggregator;
    private final IntRollingStatisticsAggregator manifestLatencyChangeMillisAggregator;
    private final IntRollingStatisticsAggregator manifestLatencyMillisAggregator;
    private final String networkName;
    private int sampleCount;

    public QoeAggregator(String networkName, QoeConfigInterface qoeConfig) {
        Intrinsics.checkNotNullParameter(networkName, "networkName");
        Intrinsics.checkNotNullParameter(qoeConfig, "qoeConfig");
        this.networkName = networkName;
        this.avgBandwidthBpsAggregator = new IntRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForAvgBandwidth());
        this.avgLatencyMillisAggregator = new IntRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForAvgLatency());
        this.bandwidthChangeBpsAggregator = new IntRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForBandwidthChangeSmoothing());
        this.latencyChangeMillisAggregator = new IntRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForLatencyChangeSmoothing());
        this.bitrateBpsAggregator = new IntRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForBitrateSmoothing());
        this.frontBufferSizeMillisAggregator = new IntRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForFrontBufferSizeSmoothing());
        this.bufferingEventCountTracker = new SlidingWindowEventTracker(qoeConfig.getWindowLengthForTrackingBufferingEvent(), 1000);
        this.bufferingEventDurationTracker = new SlidingWindowEventDurationTracker(qoeConfig.getWindowLengthForTrackingBufferingEvent());
        this.avgManifestLatencyMillisAggregator = new IntRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForManifestAvgLatency());
        this.manifestLatencyChangeMillisAggregator = new IntRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForManifestLatencyChangeSmoothing());
        this.latencyMillisAggregator = new IntRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForLatencySmoothing());
        this.bandwidthBpsAggregator = new IntRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForBandwidthSmoothing());
        this.manifestLatencyMillisAggregator = new IntRollingStatisticsAggregator(qoeConfig.getNumOfSamplesForManifestLatencySmoothing());
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getSampleCount() {
        return this.sampleCount;
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public void setSampleCount(int i) {
        this.sampleCount = i;
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getBitrateBps() {
        return this.bitrateBpsAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getFrontBufferSizeMillis() {
        return this.frontBufferSizeMillisAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getAvgBandwidthBps() {
        return this.avgBandwidthBpsAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getBandwidthChangeBps() {
        return this.bandwidthChangeBpsAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getAvgLatencyMillis() {
        return this.avgLatencyMillisAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getLatencyChangeMillis() {
        return this.latencyChangeMillisAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getTimeSpentBufferingMillis() {
        return this.bufferingEventDurationTracker.getTotalDurationInMillis();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getNumOfBufferingEvent() {
        return this.bufferingEventCountTracker.getEventCount();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getAvgManifestLatencyMillis() {
        return this.avgManifestLatencyMillisAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getManifestLatencyChangeMillis() {
        return this.manifestLatencyChangeMillisAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getLatencyMillis() {
        return this.latencyMillisAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getBandwidthBps() {
        return this.bandwidthBpsAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public int getManifestLatencyMillis() {
        return this.manifestLatencyMillisAggregator.getAverage();
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public void handleFragmentDownloadedEvent(long bandwidthBitsPerSecond, long timeToTheFirstByteInNanos, int bitrate) {
        updateBandwidthRelatedInfo(bandwidthBitsPerSecond);
        updateLatencyRelatedInfo(timeToTheFirstByteInNanos);
        this.bitrateBpsAggregator.addSample(bitrate);
        setSampleCount(getSampleCount() + 1);
    }

    private final void updateBandwidthRelatedInfo(long bandwidthBitsPerSecond) {
        int i = (int) bandwidthBitsPerSecond;
        this.avgBandwidthBpsAggregator.addSample(i);
        this.bandwidthChangeBpsAggregator.addSample(i - getAvgBandwidthBps());
        this.bandwidthBpsAggregator.addSample(i);
    }

    private final void updateLatencyRelatedInfo(long timeToTheFirstByteInNanos) {
        int millis = (int) TimeUnit.NANOSECONDS.toMillis(timeToTheFirstByteInNanos);
        this.avgLatencyMillisAggregator.addSample(millis);
        this.latencyChangeMillisAggregator.addSample(millis - getAvgLatencyMillis());
        this.latencyMillisAggregator.addSample(millis);
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public void handleManifestDownloadedEvent(long latencyInNanos) {
        int millis = (int) TimeUnit.NANOSECONDS.toMillis(latencyInNanos);
        this.avgManifestLatencyMillisAggregator.addSample(millis);
        this.manifestLatencyChangeMillisAggregator.addSample(millis - getAvgManifestLatencyMillis());
        this.manifestLatencyMillisAggregator.addSample(millis);
        setSampleCount(getSampleCount() + 1);
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public void handleBufferFullnessChangedEvent(long bufferFullnessInNanos) {
        this.frontBufferSizeMillisAggregator.addSample((int) TimeUnit.NANOSECONDS.toMillis(bufferFullnessInNanos));
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public void onBufferingStart() {
        this.bufferingEventCountTracker.recordEvent(TimeSpan.now());
        SlidingWindowEventDurationTracker slidingWindowEventDurationTracker = this.bufferingEventDurationTracker;
        TimeSpan timeSpanNow = TimeSpan.now();
        Intrinsics.checkNotNullExpressionValue(timeSpanNow, "now()");
        slidingWindowEventDurationTracker.eventStart(timeSpanNow);
    }

    @Override // com.amazon.avod.content.urlvending.QoeAggregatorInterface
    public void onBufferingStop() {
        SlidingWindowEventDurationTracker slidingWindowEventDurationTracker = this.bufferingEventDurationTracker;
        TimeSpan timeSpanNow = TimeSpan.now();
        Intrinsics.checkNotNullExpressionValue(timeSpanNow, "now()");
        slidingWindowEventDurationTracker.eventEnd(timeSpanNow);
    }
}
