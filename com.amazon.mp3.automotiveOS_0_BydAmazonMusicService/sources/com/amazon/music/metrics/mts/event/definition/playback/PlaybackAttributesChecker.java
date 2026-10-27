package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;
import com.amazon.music.metrics.util.RangeValidator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackAttributesChecker {
    static final long ONE_HOUR_IN_MILLISECONDS = TimeUnit.HOURS.toMillis(1);

    public static void validatePlaybackDelay(long j) throws CirrusInvalidDataException {
        if (!isPlaybackDelayValid(j)) {
            throw new CirrusInvalidDataException(getPlaybackDelayErrorString(j));
        }
    }

    public static boolean isPlaybackDelayValid(long j) {
        return new RangeValidator().withinRange(j, 0L, ONE_HOUR_IN_MILLISECONDS);
    }

    public static String getPlaybackDelayErrorString(long j) {
        return String.format("playbackTerminated streamingInitialDelayMilliseconds is outside of valid range: %d", Long.valueOf(j));
    }
}
