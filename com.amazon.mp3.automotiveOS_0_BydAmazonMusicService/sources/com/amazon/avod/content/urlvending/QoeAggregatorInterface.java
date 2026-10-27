package com.amazon.avod.content.urlvending;

import kotlin.Metadata;

/* JADX INFO: compiled from: QoeAggregatorInterface.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b'\bf\u0018\u00002\u00020\u0001J \u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H&J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0002H&J\b\u0010\r\u001a\u00020\u0007H&J\b\u0010\u000e\u001a\u00020\u0007H&R\u001c\u0010\u0013\u001a\u00020\u00058&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010R\u0014\u0010\u0017\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0014\u0010\u0019\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0010R\u0014\u0010\u001b\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0010R\u0014\u0010\u001d\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0010R\u0014\u0010\u001f\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0010R\u0014\u0010!\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0010R\u0014\u0010#\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0010R\u0014\u0010%\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0010R\u0014\u0010'\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0010R\u0014\u0010)\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0010R\u0014\u0010+\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u0010R\u0014\u0010-\u001a\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u0010¨\u0006."}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeAggregatorInterface;", "", "", "bandwidthBitsPerSecond", "timeToTheFirstByteInNanos", "", "bitrate", "", "handleFragmentDownloadedEvent", "latencyInNanos", "handleManifestDownloadedEvent", "bufferFullnessInNanos", "handleBufferFullnessChangedEvent", "onBufferingStart", "onBufferingStop", "getSampleCount", "()I", "setSampleCount", "(I)V", "sampleCount", "getBitrateBps", "bitrateBps", "getFrontBufferSizeMillis", "frontBufferSizeMillis", "getAvgBandwidthBps", "avgBandwidthBps", "getBandwidthChangeBps", "bandwidthChangeBps", "getAvgLatencyMillis", "avgLatencyMillis", "getLatencyChangeMillis", "latencyChangeMillis", "getTimeSpentBufferingMillis", "timeSpentBufferingMillis", "getNumOfBufferingEvent", "numOfBufferingEvent", "getAvgManifestLatencyMillis", "avgManifestLatencyMillis", "getManifestLatencyChangeMillis", "manifestLatencyChangeMillis", "getLatencyMillis", "latencyMillis", "getBandwidthBps", "bandwidthBps", "getManifestLatencyMillis", "manifestLatencyMillis", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0})
public interface QoeAggregatorInterface {
    int getAvgBandwidthBps();

    int getAvgLatencyMillis();

    int getAvgManifestLatencyMillis();

    int getBandwidthBps();

    int getBandwidthChangeBps();

    int getBitrateBps();

    int getFrontBufferSizeMillis();

    int getLatencyChangeMillis();

    int getLatencyMillis();

    int getManifestLatencyChangeMillis();

    int getManifestLatencyMillis();

    int getNumOfBufferingEvent();

    int getSampleCount();

    int getTimeSpentBufferingMillis();

    void handleBufferFullnessChangedEvent(long bufferFullnessInNanos);

    void handleFragmentDownloadedEvent(long bandwidthBitsPerSecond, long timeToTheFirstByteInNanos, int bitrate);

    void handleManifestDownloadedEvent(long latencyInNanos);

    void onBufferingStart();

    void onBufferingStop();

    void setSampleCount(int i);
}
