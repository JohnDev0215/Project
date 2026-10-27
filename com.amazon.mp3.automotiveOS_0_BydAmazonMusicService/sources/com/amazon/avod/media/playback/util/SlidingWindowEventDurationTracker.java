package com.amazon.avod.media.playback.util;

import android.annotation.SuppressLint;
import com.amazon.avod.media.TimeSpan;
import com.google.common.collect.Lists;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SlidingWindowEventDurationTracker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0003R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u0007\u001a&\u0012\f\u0012\n \t*\u0004\u0018\u00010\u00060\u0006 \t*\u0012\u0012\f\u0012\n \t*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8G¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/avod/media/playback/util/SlidingWindowEventDurationTracker;", "", "length", "Lcom/amazon/avod/media/TimeSpan;", "(Lcom/amazon/avod/media/TimeSpan;)V", "currentEventDuration", "Lcom/amazon/avod/media/playback/util/EventDuration;", "eventDurations", "Ljava/util/LinkedList;", "kotlin.jvm.PlatformType", "totalDurationInMillis", "", "getTotalDurationInMillis", "()I", "windowLength", "eventEnd", "", "eventEndTime", "eventStart", "eventStartTime", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SlidingWindowEventDurationTracker {
    private EventDuration currentEventDuration;
    private final LinkedList<EventDuration> eventDurations;
    private final TimeSpan windowLength;

    public SlidingWindowEventDurationTracker(TimeSpan length) {
        Intrinsics.checkNotNullParameter(length, "length");
        this.eventDurations = Lists.newLinkedList();
        this.windowLength = length;
    }

    @SuppressLint({"NewApi"})
    public final int getTotalDurationInMillis() {
        int iMin;
        long totalMilliseconds;
        synchronized (this) {
            iMin = 0;
            TimeSpan timeSpanDifference = TimeSpan.difference(TimeSpan.now(), this.windowLength);
            LinkedList<EventDuration> eventDurations = this.eventDurations;
            Intrinsics.checkNotNullExpressionValue(eventDurations, "eventDurations");
            for (EventDuration eventDuration : eventDurations) {
                if (eventDuration.getStartTime().greaterThan(timeSpanDifference)) {
                    totalMilliseconds = eventDuration.getDuration().getTotalMilliseconds();
                } else {
                    TimeSpan endTime = eventDuration.getEndTime();
                    Intrinsics.checkNotNull(endTime);
                    if (endTime.greaterThan(timeSpanDifference)) {
                        totalMilliseconds = TimeSpan.difference(eventDuration.getEndTime(), timeSpanDifference).getTotalMilliseconds();
                    } else {
                        this.eventDurations.remove(eventDuration);
                    }
                }
                iMin += (int) totalMilliseconds;
            }
            EventDuration eventDuration2 = this.currentEventDuration;
            if (eventDuration2 != null) {
                Intrinsics.checkNotNull(eventDuration2);
                iMin += (int) Math.min(eventDuration2.getDuration().getTotalMilliseconds(), this.windowLength.getTotalMilliseconds());
            }
        }
        return iMin;
    }

    public final boolean eventStart(TimeSpan eventStartTime) {
        Intrinsics.checkNotNullParameter(eventStartTime, "eventStartTime");
        if (this.currentEventDuration != null) {
            return false;
        }
        this.currentEventDuration = new EventDuration(eventStartTime);
        return true;
    }

    public final boolean eventEnd(TimeSpan eventEndTime) {
        Intrinsics.checkNotNullParameter(eventEndTime, "eventEndTime");
        synchronized (this) {
            EventDuration eventDuration = this.currentEventDuration;
            if (eventDuration == null) {
                return false;
            }
            Intrinsics.checkNotNull(eventDuration);
            eventDuration.end(eventEndTime);
            this.eventDurations.add(this.currentEventDuration);
            this.currentEventDuration = null;
            return true;
        }
    }
}
