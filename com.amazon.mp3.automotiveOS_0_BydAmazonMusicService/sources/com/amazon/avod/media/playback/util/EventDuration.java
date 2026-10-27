package com.amazon.avod.media.playback.util;

import com.amazon.avod.media.TimeSpan;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventDuration.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\fJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0007\u001a\u0004\b\n\u0010\t\"\u0004\b\u000b\u0010\fR\u0011\u0010\u000e\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\r\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/amazon/avod/media/playback/util/EventDuration;", "", "Lcom/amazon/avod/media/TimeSpan;", "endTime", "", "end", "startTime", "Lcom/amazon/avod/media/TimeSpan;", "getStartTime", "()Lcom/amazon/avod/media/TimeSpan;", "getEndTime", "setEndTime", "(Lcom/amazon/avod/media/TimeSpan;)V", "getDuration", "duration", "<init>", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0})
public final class EventDuration {
    private TimeSpan endTime;
    private final TimeSpan startTime;

    public EventDuration(TimeSpan startTime) {
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        this.startTime = startTime;
    }

    public final TimeSpan getStartTime() {
        return this.startTime;
    }

    public final TimeSpan getEndTime() {
        return this.endTime;
    }

    public final TimeSpan getDuration() {
        TimeSpan timeSpan = this.endTime;
        if (timeSpan != null) {
            TimeSpan timeSpanDifference = TimeSpan.difference(this.startTime, timeSpan);
            Intrinsics.checkNotNullExpressionValue(timeSpanDifference, "{\n            TimeSpan.d…tTime, endTime)\n        }");
            return timeSpanDifference;
        }
        TimeSpan timeSpanDifference2 = TimeSpan.difference(this.startTime, TimeSpan.now());
        Intrinsics.checkNotNullExpressionValue(timeSpanDifference2, "{\n            TimeSpan.d…TimeSpan.now())\n        }");
        return timeSpanDifference2;
    }

    public final void end(TimeSpan endTime) {
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        this.endTime = endTime;
    }
}
