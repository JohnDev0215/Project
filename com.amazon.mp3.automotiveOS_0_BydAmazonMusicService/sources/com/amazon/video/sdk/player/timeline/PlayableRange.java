package com.amazon.video.sdk.player.timeline;

import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlayableRange.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/PlayableRange;", "", "", "getStart", "()Ljava/lang/Long;", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "getEnd", "end", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface PlayableRange {
    Long getEnd();

    Long getStart();
}
