package com.amazon.avod.playback.sye.statistics;

import android.os.SystemClock;
import com.amazon.avod.listeners.SetListenerProxy;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.util.SyeConfig;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.ISyeMetrics;
import com.netinsight.sye.syeClient.ISyePlayer;
import com.netinsight.sye.syeClient.playerListeners.IVideoTrackListener;
import com.netinsight.sye.syeClient.video.ISyeVideoStreamInfo;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MetricsNotifier extends SetListenerProxy<MetricsListener> implements IVideoTrackListener {
    private int lastBitrate;
    private long lastUpdatedTimeStamp = SystemClock.elapsedRealtime();
    private VideoResolution lastVideoResolution;

    @Nonnull
    private final ScheduledExecutorService mExecutor;

    @Nonnull
    private final ISyePlayer mPlayer;

    @Nonnull
    private final SyeConfig mSyeConfig;

    public MetricsNotifier(@Nonnull ISyePlayer iSyePlayer, @Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull SyeConfig syeConfig) {
        this.mPlayer = (ISyePlayer) Preconditions.checkNotNull(iSyePlayer, "player");
        this.mExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "executor");
        this.mSyeConfig = (SyeConfig) Preconditions.checkNotNull(syeConfig, "syeConfig");
        notifyMetricsPeriodically();
    }

    private void notifyMetricsPeriodically() {
        this.mExecutor.scheduleAtFixedRate(new Runnable() { // from class: com.amazon.avod.playback.sye.statistics.MetricsNotifier$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.notifyMetrics();
            }
        }, this.mSyeConfig.getStatisticsNotificationIntervalMillis(), this.mSyeConfig.getStatisticsNotificationIntervalMillis(), TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMetrics() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int i = (int) (jElapsedRealtime - this.lastUpdatedTimeStamp);
        this.lastUpdatedTimeStamp = jElapsedRealtime;
        Set<MetricsListener> listeners = getListeners();
        ISyeMetrics metrics = this.mPlayer.getMetrics();
        Iterator<MetricsListener> it = listeners.iterator();
        while (it.hasNext()) {
            it.next().onMetricsAvailable(metrics, this.lastBitrate, i, this.lastVideoResolution);
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IVideoTrackListener
    public void onVideoStreamChange(@Nonnull ISyeVideoStreamInfo iSyeVideoStreamInfo) {
        VideoResolution videoResolution;
        if (this.lastBitrate != 0 || this.lastVideoResolution != null) {
            notifyMetrics();
        }
        if (this.lastBitrate == iSyeVideoStreamInfo.getBitrate() && (videoResolution = this.lastVideoResolution) != null && videoResolution.getWidth() == iSyeVideoStreamInfo.getWidth() && this.lastVideoResolution.getHeight() == iSyeVideoStreamInfo.getHeight()) {
            return;
        }
        this.lastBitrate = iSyeVideoStreamInfo.getBitrate();
        this.lastVideoResolution = new VideoResolution(iSyeVideoStreamInfo.getWidth(), iSyeVideoStreamInfo.getHeight());
    }
}
