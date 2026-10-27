package com.amazon.video.sdk.player.timeline;

import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayableRange.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B)\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/amazon/video/sdk/player/timeline/PlayableRangeData;", "Lcom/amazon/video/sdk/player/timeline/PlayableRange;", "Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "unit", "Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "getUnit", "()Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "Ljava/lang/Long;", "getStart", "()Ljava/lang/Long;", "end", "getEnd", "<init>", "(Lcom/amazon/video/sdk/player/timeline/TimeUnit;Ljava/lang/Long;Ljava/lang/Long;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class PlayableRangeData implements PlayableRange {
    private final Long end;
    private final Long start;
    private final TimeUnit unit;

    public PlayableRangeData() {
        this(null, null, null, 7, null);
    }

    public PlayableRangeData(TimeUnit unit, Long l, Long l2) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.unit = unit;
        this.start = l;
        this.end = l2;
    }

    public /* synthetic */ PlayableRangeData(TimeUnit timeUnit, Long l, Long l2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? TimeUnit.CONTENT_MILLIS : timeUnit, (i & 2) != 0 ? null : l, (i & 4) != 0 ? null : l2);
    }

    @Override // com.amazon.video.sdk.player.timeline.PlayableRange
    public Long getStart() {
        return this.start;
    }

    @Override // com.amazon.video.sdk.player.timeline.PlayableRange
    public Long getEnd() {
        return this.end;
    }
}
