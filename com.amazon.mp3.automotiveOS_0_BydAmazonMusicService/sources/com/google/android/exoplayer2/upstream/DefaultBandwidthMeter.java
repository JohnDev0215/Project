package com.google.android.exoplayer2.upstream;

import android.os.Handler;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.SlidingPercentile;

/* JADX INFO: loaded from: classes6.dex */
public class DefaultBandwidthMeter implements TransferListener<Object> {
    private long bitrateEstimate;
    private final Clock clock;
    private final Handler eventHandler;
    private final BandwidthMeter$EventListener eventListener;
    private long sampleBytesTransferred;
    private long sampleStartTimeMs;
    private final SlidingPercentile slidingPercentile;
    private int streamCount;
    private long totalBytesTransferred;
    private long totalElapsedTimeMs;

    public DefaultBandwidthMeter() {
        this(null, null);
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter$EventListener bandwidthMeter$EventListener) {
        this(handler, bandwidthMeter$EventListener, 2000);
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter$EventListener bandwidthMeter$EventListener, int i) {
        this(handler, bandwidthMeter$EventListener, i, Clock.DEFAULT);
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter$EventListener bandwidthMeter$EventListener, int i, Clock clock) {
        this.eventHandler = handler;
        this.eventListener = bandwidthMeter$EventListener;
        this.slidingPercentile = new SlidingPercentile(i);
        this.clock = clock;
        this.bitrateEstimate = -1L;
    }

    @Override // com.google.android.exoplayer2.upstream.TransferListener
    public synchronized void onTransferStart(Object obj, DataSpec dataSpec) {
        if (this.streamCount == 0) {
            this.sampleStartTimeMs = this.clock.elapsedRealtime();
        }
        this.streamCount++;
    }

    @Override // com.google.android.exoplayer2.upstream.TransferListener
    public synchronized void onBytesTransferred(Object obj, int i) {
        this.sampleBytesTransferred += (long) i;
    }

    @Override // com.google.android.exoplayer2.upstream.TransferListener
    public synchronized void onTransferEnd(Object obj) {
        Assertions.checkState(this.streamCount > 0);
        long jElapsedRealtime = this.clock.elapsedRealtime();
        int i = (int) (jElapsedRealtime - this.sampleStartTimeMs);
        this.totalElapsedTimeMs += i;
        long j = this.totalBytesTransferred;
        long j2 = this.sampleBytesTransferred;
        this.totalBytesTransferred = j + j2;
        if (i > 0) {
            this.slidingPercentile.addSample((int) Math.sqrt(j2), (8000 * j2) / r7);
            if (this.totalElapsedTimeMs >= 2000 || this.totalBytesTransferred >= 524288) {
                float percentile = this.slidingPercentile.getPercentile(0.5f);
                this.bitrateEstimate = Float.isNaN(percentile) ? -1L : (long) percentile;
            }
        }
        notifyBandwidthSample(i, this.sampleBytesTransferred, this.bitrateEstimate);
        int i2 = this.streamCount - 1;
        this.streamCount = i2;
        if (i2 > 0) {
            this.sampleStartTimeMs = jElapsedRealtime;
        }
        this.sampleBytesTransferred = 0L;
    }

    private void notifyBandwidthSample(final int i, final long j, final long j2) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer2.upstream.DefaultBandwidthMeter.1
            @Override // java.lang.Runnable
            public void run() {
                DefaultBandwidthMeter.this.eventListener.onBandwidthSample(i, j, j2);
            }
        });
    }
}
