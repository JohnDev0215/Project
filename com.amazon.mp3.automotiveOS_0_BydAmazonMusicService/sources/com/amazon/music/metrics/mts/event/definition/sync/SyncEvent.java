package com.amazon.music.metrics.mts.event.definition.sync;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.EventTimeConverter;
import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;
import com.amazon.music.metrics.util.RangeValidator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
abstract class SyncEvent extends MTSEvent {
    private static final long TWELVE_HOURS_IN_MILLISECONDS = TimeUnit.HOURS.toMillis(12);

    protected SyncEvent(String str, long j) throws CirrusInvalidDataException {
        super(str);
        addEventAttributes(str, j);
    }

    private void addEventAttributes(String str, long j) throws CirrusInvalidDataException {
        validateTime(str, j);
        addAttribute("durationSeconds", new EventTimeConverter().convertToRoundedSeconds(j));
    }

    private void validateTime(String str, long j) throws CirrusInvalidDataException {
        if (!withinValidTime(j)) {
            throw new CirrusInvalidDataException(createErrorString(str, j));
        }
    }

    private String createErrorString(String str, long j) {
        return String.format(Locale.US, "%s milliseconds is outside of valid range: %d", str, Long.valueOf(j));
    }

    private boolean withinValidTime(long j) {
        return new RangeValidator().withinRange(j, 0L, TWELVE_HOURS_IN_MILLISECONDS);
    }
}
