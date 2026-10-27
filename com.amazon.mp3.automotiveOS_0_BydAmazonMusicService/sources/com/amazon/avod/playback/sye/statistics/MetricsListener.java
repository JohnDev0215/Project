package com.amazon.avod.playback.sye.statistics;

import com.amazon.avod.media.VideoResolution;
import com.netinsight.sye.syeClient.ISyeMetrics;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface MetricsListener {
    void onMetricsAvailable(ISyeMetrics iSyeMetrics, int i, int i2, @Nullable VideoResolution videoResolution);
}
