package com.netinsight.sye.syeClient.metrics;

import com.netinsight.sye.syeClient.ISyeMetrics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b%\u0018\u0000 -2\u00020\u0001:\u0001.B\u0011\b\u0002\u0012\u0006\u0010*\u001a\u00020\u0002¢\u0006\u0004\b+\u0010,J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004R\u001c\u0010\u0006\u001a\u00020\u00058\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\u00020\n8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u00020\n8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000eR\u001c\u0010\u0011\u001a\u00020\u00058\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0007\u001a\u0004\b\u0012\u0010\tR\u001c\u0010\u0013\u001a\u00020\u00058\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0013\u0010\u0007\u001a\u0004\b\u0014\u0010\tR\u001c\u0010\u0015\u001a\u00020\n8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\f\u001a\u0004\b\u0016\u0010\u000eR\u001c\u0010\u0017\u001a\u00020\u00058\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0007\u001a\u0004\b\u0018\u0010\tR\u001c\u0010\u0019\u001a\u00020\u00058\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u0007\u001a\u0004\b\u001a\u0010\tR\u001c\u0010\u001b\u001a\u00020\u00058\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u0007\u001a\u0004\b\u001c\u0010\tR\u001c\u0010\u001d\u001a\u00020\n8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\f\u001a\u0004\b\u001e\u0010\u000eR\u001c\u0010\u001f\u001a\u00020\u00058\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001f\u0010\u0007\u001a\u0004\b \u0010\tR\u001c\u0010!\u001a\u00020\n8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b!\u0010\f\u001a\u0004\b\"\u0010\u000eR\u001c\u0010#\u001a\u00020\n8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b#\u0010\f\u001a\u0004\b$\u0010\u000eR\u001c\u0010%\u001a\u00020\n8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b%\u0010\f\u001a\u0004\b&\u0010\u000eR\u001c\u0010'\u001a\u00020\u00028\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010\u0004¨\u0006/"}, d2 = {"Lcom/netinsight/sye/syeClient/metrics/SyeMetrics;", "Lcom/netinsight/sye/syeClient/ISyeMetrics;", "", "toString", "()Ljava/lang/String;", "", SyeMetrics.PACKETS_RETRANSMIT_RECEIVED, "J", "getPacketsRetransmitReceived", "()J", "", SyeMetrics.VIDEO_FRAMES_DROPPED, "I", "getVideoFramesDropped", "()I", SyeMetrics.VIDEO_DIVERGENCE_MILLIS, "getVideoDivergenceMillis", SyeMetrics.BYTES_RECEIVED, "getBytesReceived", SyeMetrics.VIDEO_FRAME_RECEIVED, "getVideoFramesReceived", SyeMetrics.BITRATE_TOTAL, "getBitrateTotal", SyeMetrics.THUMBNAILS_RECEIVED, "getThumbnailsReceived", SyeMetrics.PACKETS_RECEIVED, "getPacketsReceived", SyeMetrics.NOTIFICATIONS_RECEIVED, "getNotificationsReceived", SyeMetrics.AUDIO_DROPOUT_MILLIS, "getAudioDropoutMillis", SyeMetrics.PACKETS_LOST, "getPacketsLost", SyeMetrics.VIDEO_DECODE_FAILURES, "getVideoDecodeFailures", SyeMetrics.VIDEO_FRAMES_DAMAGED, "getVideoFramesDamaged", SyeMetrics.ROUND_TRIP_MILLIS, "getRoundtripMillis", SyeMetrics.EGRESS_ENDPOINT, "Ljava/lang/String;", "getEgressEndpoint", "jsonString", "<init>", "(Ljava/lang/String;)V", "Companion", "a", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeMetrics implements ISyeMetrics {
    private static final String AUDIO_DROPOUT_MILLIS = "audioDropoutMillis";
    private static final String BITRATE_TOTAL = "bitrateTotal";
    private static final String BYTES_RECEIVED = "bytesReceived";
    private static final String EGRESS_ENDPOINT = "egressEndpoint";
    private static final String NOTIFICATIONS_RECEIVED = "notificationsReceived";
    private static final String PACKETS_LOST = "packetsLost";
    private static final String PACKETS_RECEIVED = "packetsReceived";
    private static final String PACKETS_RETRANSMIT_RECEIVED = "packetsRetransmitReceived";
    private static final String ROUND_TRIP_MILLIS = "roundtripMillis";
    private static final String THUMBNAILS_RECEIVED = "thumbnailsReceived";
    private static final String VIDEO_DECODE_FAILURES = "videoDecodeFailures";
    private static final String VIDEO_DIVERGENCE_MILLIS = "videoDivergenceMillis";
    private static final String VIDEO_FRAMES_DAMAGED = "videoFramesDamaged";
    private static final String VIDEO_FRAMES_DROPPED = "videoFramesDropped";
    private static final String VIDEO_FRAME_RECEIVED = "videoFramesReceived";
    private final int audioDropoutMillis;
    private final int bitrateTotal;
    private final long bytesReceived;
    private final String egressEndpoint;
    private final long notificationsReceived;
    private final long packetsLost;
    private final long packetsReceived;
    private final long packetsRetransmitReceived;
    private final int roundtripMillis;
    private final long thumbnailsReceived;
    private final int videoDecodeFailures;
    private final int videoDivergenceMillis;
    private final int videoFramesDamaged;
    private final int videoFramesDropped;
    private final long videoFramesReceived;

    private SyeMetrics(String str) {
        JSONObject jSONObject = new JSONObject(str);
        String string = jSONObject.getString(EGRESS_ENDPOINT);
        Intrinsics.checkExpressionValueIsNotNull(string, "jsonObject.getString(EGRESS_ENDPOINT)");
        this.egressEndpoint = string;
        this.roundtripMillis = jSONObject.getInt(ROUND_TRIP_MILLIS);
        this.bitrateTotal = jSONObject.getInt(BITRATE_TOTAL);
        this.bytesReceived = jSONObject.getLong(BYTES_RECEIVED);
        this.packetsReceived = jSONObject.getLong(PACKETS_RECEIVED);
        this.packetsRetransmitReceived = jSONObject.getLong(PACKETS_RETRANSMIT_RECEIVED);
        this.packetsLost = jSONObject.getLong(PACKETS_LOST);
        this.audioDropoutMillis = jSONObject.getInt(AUDIO_DROPOUT_MILLIS);
        this.videoFramesDropped = jSONObject.getInt(VIDEO_FRAMES_DROPPED);
        this.videoFramesDamaged = jSONObject.getInt(VIDEO_FRAMES_DAMAGED);
        this.videoDecodeFailures = jSONObject.getInt(VIDEO_DECODE_FAILURES);
        this.videoDivergenceMillis = jSONObject.getInt(VIDEO_DIVERGENCE_MILLIS);
        this.videoFramesReceived = jSONObject.getLong(VIDEO_FRAME_RECEIVED);
        this.thumbnailsReceived = jSONObject.getLong(THUMBNAILS_RECEIVED);
        this.notificationsReceived = jSONObject.getLong(NOTIFICATIONS_RECEIVED);
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public int getAudioDropoutMillis() {
        return this.audioDropoutMillis;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public int getBitrateTotal() {
        return this.bitrateTotal;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public long getBytesReceived() {
        return this.bytesReceived;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public String getEgressEndpoint() {
        return this.egressEndpoint;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public long getNotificationsReceived() {
        return this.notificationsReceived;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public long getPacketsLost() {
        return this.packetsLost;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public long getPacketsReceived() {
        return this.packetsReceived;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public long getPacketsRetransmitReceived() {
        return this.packetsRetransmitReceived;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public int getRoundtripMillis() {
        return this.roundtripMillis;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public long getThumbnailsReceived() {
        return this.thumbnailsReceived;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public int getVideoDecodeFailures() {
        return this.videoDecodeFailures;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public int getVideoDivergenceMillis() {
        return this.videoDivergenceMillis;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public int getVideoFramesDamaged() {
        return this.videoFramesDamaged;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public int getVideoFramesDropped() {
        return this.videoFramesDropped;
    }

    @Override // com.netinsight.sye.syeClient.ISyeMetrics
    public long getVideoFramesReceived() {
        return this.videoFramesReceived;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(EGRESS_ENDPOINT, getEgressEndpoint());
        jSONObject.put(ROUND_TRIP_MILLIS, getRoundtripMillis());
        jSONObject.put(BITRATE_TOTAL, getBitrateTotal());
        jSONObject.put(BYTES_RECEIVED, getBytesReceived());
        jSONObject.put(PACKETS_RECEIVED, getPacketsReceived());
        jSONObject.put(PACKETS_RETRANSMIT_RECEIVED, getPacketsRetransmitReceived());
        jSONObject.put(PACKETS_LOST, getPacketsLost());
        jSONObject.put(AUDIO_DROPOUT_MILLIS, getAudioDropoutMillis());
        jSONObject.put(VIDEO_FRAMES_DROPPED, getVideoFramesDropped());
        jSONObject.put(VIDEO_FRAMES_DAMAGED, getVideoFramesDamaged());
        jSONObject.put(VIDEO_DECODE_FAILURES, getVideoDecodeFailures());
        jSONObject.put(VIDEO_DIVERGENCE_MILLIS, getVideoDivergenceMillis());
        jSONObject.put(VIDEO_FRAME_RECEIVED, getVideoFramesReceived());
        jSONObject.put(THUMBNAILS_RECEIVED, getThumbnailsReceived());
        jSONObject.put(NOTIFICATIONS_RECEIVED, getNotificationsReceived());
        String string = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "jsonObject.toString()");
        return string;
    }
}
