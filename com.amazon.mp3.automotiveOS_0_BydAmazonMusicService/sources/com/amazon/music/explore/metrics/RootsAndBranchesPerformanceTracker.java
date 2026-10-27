package com.amazon.music.explore.metrics;

import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.providers.MetricsProvider;

/* JADX INFO: loaded from: classes4.dex */
public class RootsAndBranchesPerformanceTracker {
    private static String FLEX_NAME = "renderingArtistInfluences";
    private final MetricsProvider metricsProvider;
    private long startTimeNs = -1;
    private long brushEndTimeNs = -1;
    private long exploreEndTimeNs = -1;
    private boolean didEmitEvent = false;

    public RootsAndBranchesPerformanceTracker(MetricsProvider metricsProvider) {
        this.metricsProvider = metricsProvider;
    }

    public void startTiming() {
        if (this.startTimeNs == -1) {
            this.startTimeNs = System.nanoTime();
        }
    }

    public void timeBrushResponse() {
        if (this.brushEndTimeNs == -1) {
            this.brushEndTimeNs = System.nanoTime();
        }
        attemptEventEmission();
    }

    public void timeExploreResponse() {
        if (this.exploreEndTimeNs == -1) {
            this.exploreEndTimeNs = System.nanoTime();
        }
        attemptEventEmission();
    }

    private void attemptEventEmission() {
        if (this.didEmitEvent) {
            return;
        }
        long j = this.startTimeNs;
        if (j != -1) {
            long j2 = this.brushEndTimeNs;
            if (j2 != -1) {
                long j3 = this.exploreEndTimeNs;
                if (j3 == -1) {
                    return;
                }
                long j4 = (j3 - j) / 1000000;
                long j5 = (j2 - j) / 1000000;
                this.metricsProvider.sendMetricsEvent(new FlexWrapper.Builder().withName(FLEX_NAME).withNum1(Float.valueOf(j4)).withNum2(Float.valueOf(j5)).withNum3(Float.valueOf(j4 > j5 ? 1 : 0)).build());
                this.didEmitEvent = true;
            }
        }
    }
}
