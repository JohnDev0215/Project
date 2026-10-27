package com.netinsight.sye.syeClient;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0018\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\t\u001a\u00020\u00068&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u000b\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0004R\u0016\u0010\r\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0004R\u0016\u0010\u0011\u001a\u00020\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0013\u001a\u00020\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0016\u0010\u0015\u001a\u00020\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010R\u0016\u0010\u0017\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0004R\u0016\u0010\u0019\u001a\u00020\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0010R\u0016\u0010\u001b\u001a\u00020\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0010R\u0016\u0010\u001d\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0004R\u0016\u0010\u001f\u001a\u00020\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0010R\u0016\u0010!\u001a\u00020\u000e8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0010R\u0016\u0010#\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0004R\u0016\u0010%\u001a\u00020\u00028&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0004¨\u0006&"}, d2 = {"Lcom/netinsight/sye/syeClient/ISyeMetrics;", "", "", "getBytesReceived", "()J", "bytesReceived", "", "getEgressEndpoint", "()Ljava/lang/String;", "egressEndpoint", "getPacketsReceived", "packetsReceived", "getVideoFramesReceived", "videoFramesReceived", "", "getAudioDropoutMillis", "()I", "audioDropoutMillis", "getRoundtripMillis", "roundtripMillis", "getVideoDivergenceMillis", "videoDivergenceMillis", "getPacketsRetransmitReceived", "packetsRetransmitReceived", "getVideoFramesDropped", "videoFramesDropped", "getBitrateTotal", "bitrateTotal", "getPacketsLost", "packetsLost", "getVideoFramesDamaged", "videoFramesDamaged", "getVideoDecodeFailures", "videoDecodeFailures", "getThumbnailsReceived", "thumbnailsReceived", "getNotificationsReceived", "notificationsReceived", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyeMetrics {
    int getAudioDropoutMillis();

    int getBitrateTotal();

    long getBytesReceived();

    String getEgressEndpoint();

    long getNotificationsReceived();

    long getPacketsLost();

    long getPacketsReceived();

    long getPacketsRetransmitReceived();

    int getRoundtripMillis();

    long getThumbnailsReceived();

    int getVideoDecodeFailures();

    int getVideoDivergenceMillis();

    int getVideoFramesDamaged();

    int getVideoFramesDropped();

    long getVideoFramesReceived();
}
