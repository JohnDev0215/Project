package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.google.common.base.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PlaybackErrorEvent extends PlaybackEvent {
    private final int mHttpErrorCode;
    final String mNote;
    private final MediaException mPlaybackException;

    public abstract boolean isRetriable();

    PlaybackErrorEvent(TimeSpan timeSpan, MediaException mediaException, @Nullable String str, int i) {
        this(timeSpan, mediaException, str, i, null);
    }

    PlaybackErrorEvent(TimeSpan timeSpan, MediaException mediaException, @Nullable String str, int i, @Nullable String str2) {
        super(timeSpan, str2);
        this.mPlaybackException = mediaException;
        this.mNote = str;
        this.mHttpErrorCode = i;
    }

    public MediaException getPlaybackException() {
        return this.mPlaybackException;
    }

    @Nullable
    public String getNote() {
        return this.mNote;
    }

    public int getHttpErrorCode() {
        return this.mHttpErrorCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaybackErrorEvent)) {
            return false;
        }
        PlaybackErrorEvent playbackErrorEvent = (PlaybackErrorEvent) obj;
        if (this.mHttpErrorCode != playbackErrorEvent.mHttpErrorCode || !this.mPlaybackException.equals(playbackErrorEvent.mPlaybackException)) {
            return false;
        }
        String str = this.mNote;
        return str != null ? str.equals(playbackErrorEvent.mNote) : (playbackErrorEvent.mNote != null || getConsumptionId() == null) ? playbackErrorEvent.getConsumptionId() == null : getConsumptionId().equals(playbackErrorEvent.getConsumptionId());
    }

    public int hashCode() {
        return Objects.hashCode(this.mPlaybackException, this.mNote, Integer.valueOf(this.mHttpErrorCode), getConsumptionId());
    }
}
