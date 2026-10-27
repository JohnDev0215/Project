package com.amazon.video.sdk.player;

import java.io.Serializable;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaybackEnvelope.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0007\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/player/PlaybackEnvelope;", "Ljava/io/Serializable;", "", "getEnvelope", "()Ljava/lang/String;", "envelope", "getCorrelationId", "correlationId", "ATVPlaybackFramework_release"}, k = 1, mv = {1, 6, 0})
public interface PlaybackEnvelope extends Serializable {
    String getCorrelationId();

    String getEnvelope();
}
