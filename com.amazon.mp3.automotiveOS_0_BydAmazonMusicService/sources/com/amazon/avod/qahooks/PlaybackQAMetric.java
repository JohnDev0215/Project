package com.amazon.avod.qahooks;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.qahooks.QALog;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum PlaybackQAMetric implements QALog.QALoggableMetric {
    API_VERSION("api version"),
    ASIN("asin"),
    TITLE_ID("title id"),
    PROGRESS(NotificationCompat.CATEGORY_PROGRESS),
    OLD_QUALITY("old quality"),
    NEW_QUALITY("new quality"),
    OLD_ADAPTATION_SET("old adaptation set"),
    NEW_ADAPTATION_SET("new adaptation set"),
    FOURCC("fourCC code"),
    CAUSE("cause"),
    ERROR_CODE("error code"),
    ERROR_MESSAGE("error message"),
    ERROR_ACTIVITY("error activity"),
    BITRATE("bitrate"),
    SEEK_LOCATION("seek location"),
    BANDWIDTH("bandwidth"),
    BANDWIDTH_STDEV("bandwidth standard deviation"),
    EXPIRATION("expiration"),
    SUCCESS("success"),
    DURATION("duration"),
    RESOLUTION_HEIGHT("resolution height"),
    RESOLUTION_WIDTH("resolution width"),
    AUDIO_FORMAT("audio format"),
    RENDERER_SCHEME("renderer scheme"),
    BUFFER_EVENT_TYPE("buffer event type"),
    ENABLED("enabled"),
    FROM_AUDIO_TRACK("from audio track"),
    TO_AUDIO_TRACK("to audio track"),
    SUBTITLE_LANGUAGE("subtitle language"),
    OLD_NETWORK_CONNECTION_TYPE("old network connection type"),
    OLD_NETWORK_CONNECTION_CLASS("old network connection class"),
    OLD_NETWORK_CONNECTION_STATE("old network connection state"),
    NETWORK_CONNECTION_TYPE("network connection type"),
    NETWORK_CONNECTION_CLASS("network connection class"),
    NETWORK_CONNECTION_STATE("network connection state"),
    NETWORK_CONNECTION_STRENGTH("network connection strength"),
    DIALOG_TYPE("dialog type"),
    NOTE("note"),
    REF_MARKER("ref_marker"),
    METRIC("metric"),
    OLD_STATE("old state"),
    NEW_STATE("new state"),
    SUBTITLE_LANGUAGES("subtitle_languages"),
    OWNING_APPLICATION("owning application"),
    STORAGE_LOCATION("storage location"),
    AUDIT_PING_URL("audit_ping_url");

    private final String mIdentifier;

    @Override // com.amazon.avod.qahooks.QALog.QALoggableMetric
    @Nonnull
    public QALog.QALoggableMetric getQAMetric() {
        return this;
    }

    PlaybackQAMetric(String str) {
        this.mIdentifier = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mIdentifier;
    }
}
