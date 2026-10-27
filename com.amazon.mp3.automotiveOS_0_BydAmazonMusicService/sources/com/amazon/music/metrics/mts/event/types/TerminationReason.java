package com.amazon.music.metrics.mts.event.types;

import androidx.core.os.EnvironmentCompat;
import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum TerminationReason implements MetricValueType {
    ERROR("error"),
    TRACK_FINISHED("trackFinished"),
    SYSTEM_STOP("systemStop"),
    USER_NEXT("userNext"),
    USER_PREV("userPrev"),
    USER_STOP("userStop"),
    VOICE_PREV("voicePrev"),
    VOICE_NEXT("voiceNext"),
    CONCURRENCY("concurrency"),
    TRACK_SCRUB("trackScrub"),
    LYRICS_SCRUB("lyricsScrub"),
    CONTENT_FINISHED("contentFinished"),
    CONTENT_SCRUB("contentScrub"),
    BLUETOOTH_STOP("bluetoothStop"),
    EXPLICIT_LANGUAGE_FILTER_ENABLED("explicitLanguageFilterEnabled"),
    EXPLICIT_LANGUAGE_FILTER_DISABLED("explicitLanguageFilterDisabled"),
    CASTING("casting"),
    USER_DISCONNECT("userDisconnect"),
    IMPLICIT_DISCONNECT("implicitDisconnect"),
    CASTING_REMOTE_CONTROL("castingRemoteControl"),
    EXTERNAL_DEVICE_CONNECTED("externalDeviceConnected"),
    SERVICE_UPDATE_TIMEOUT("serviceUpdateTimeout"),
    NETWORK_ERROR("networkError"),
    CLIENT_THING_SHADOW_UPDATED_AS_DISCONNECTED("clientThingShadowUpdatedAsDisconnected"),
    PLAYBACK_INACTIVITY("playbackInactivity"),
    REMOTELY_DISCONNECTED("remotelyDisconnected"),
    UNSUPPORTED_CONTENT("unsupportedContent"),
    CREATE_QUEUE_ERROR("createQueueError"),
    SLEEP_TIMER_INACTIVITY("sleepTimerInactivty"),
    ALARM_CLOCK_THRESHOLD_REACHED("alarmClockThresholdReached"),
    GHOST_LISTENING_THRESHOLD_REACHED("ghostListeningThresholdReached"),
    TOGGLE_VIDEO_AUDIO("togAudio"),
    TOGGLE_AUDIO_VIDEO("togMusicVideo"),
    CLOSE_PLAYER("closePlayer"),
    ACTION_BUTTON_CLICKED("actionButtonClicked"),
    ARTIST_IMAGE_CLICKED("artistImageClicked"),
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);

    private final String mReason;

    TerminationReason(String str) {
        this.mReason = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mReason;
    }
}
