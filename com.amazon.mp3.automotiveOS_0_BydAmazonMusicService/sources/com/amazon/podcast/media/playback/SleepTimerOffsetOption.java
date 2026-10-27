package com.amazon.podcast.media.playback;

import com.amazon.podcast.R$string;

/* JADX INFO: loaded from: classes5.dex */
public enum SleepTimerOffsetOption {
    ADD_FIVE_MINUTES(5, R$string.podcast_sleep_timer_add_duration_string),
    REMOVE_FIVE_MINUTES(5, R$string.podcast_sleep_timer_subtract_duration_string);

    private final int resourceId;
    private final long value;

    SleepTimerOffsetOption(final long value, final int resourceId) {
        this.resourceId = resourceId;
        this.value = value;
    }

    public int getResourceId() {
        return this.resourceId;
    }

    public long getValueMs() {
        return this.value * 60 * 1000;
    }

    public int getValue() {
        return (int) this.value;
    }
}
