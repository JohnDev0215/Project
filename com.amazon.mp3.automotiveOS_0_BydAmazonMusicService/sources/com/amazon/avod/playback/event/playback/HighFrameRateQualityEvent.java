package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HighFrameRateQualityEvent.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/avod/playback/event/playback/HighFrameRateQualityEvent;", "Lcom/amazon/avod/playback/event/PlaybackEvent;", "eventTime", "Lcom/amazon/avod/media/TimeSpan;", MediaTrack.ROLE_DESCRIPTION, "", "(Lcom/amazon/avod/media/TimeSpan;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class HighFrameRateQualityEvent extends PlaybackEvent {
    private final String description;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HighFrameRateQualityEvent(TimeSpan eventTime, String description) {
        super(eventTime);
        Intrinsics.checkNotNullParameter(eventTime, "eventTime");
        Intrinsics.checkNotNullParameter(description, "description");
        this.description = description;
    }

    public final String getDescription() {
        return this.description;
    }
}
