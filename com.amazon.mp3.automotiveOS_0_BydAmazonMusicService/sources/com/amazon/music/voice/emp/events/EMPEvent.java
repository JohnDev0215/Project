package com.amazon.music.voice.emp.events;

import PlaybackMetricsInterface.v1_0.PlaybackSignalType;
import kotlin.Metadata;

/* JADX INFO: compiled from: EMPEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/voice/emp/events/EMPEvent;", "", "eventName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getEventName", "()Ljava/lang/String;", PlaybackSignalType.PLAYBACK_STARTED, PlaybackSignalType.PLAYBACK_STOPPED, "PLAYBACK_SESSION_STARTED", "PLAYBACK_SESSION_ENDED", "TRACK_CHANGED", "PLAYBACK_NEXT", "PLAYBACK_PREVIOUS", "PLAY_MODE_CHANGED", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum EMPEvent {
    PLAYBACK_STARTED("PlaybackStarted"),
    PLAYBACK_STOPPED("PlaybackStopped"),
    PLAYBACK_SESSION_STARTED("PlaybackSessionStarted"),
    PLAYBACK_SESSION_ENDED("PlaybackSessionEnded"),
    TRACK_CHANGED("TrackChanged"),
    PLAYBACK_NEXT("PlaybackNext"),
    PLAYBACK_PREVIOUS("PlaybackPrevious"),
    PLAY_MODE_CHANGED("PlayModeChanged");

    private final String eventName;

    EMPEvent(String str) {
        this.eventName = str;
    }

    public final String getEventName() {
        return this.eventName;
    }
}
