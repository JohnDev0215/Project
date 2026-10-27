package org.threeten.bp.format;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.threeten.bp.temporal.TemporalField;

/* JADX INFO: loaded from: classes4.dex */
abstract class DateTimeTextProvider {
    public abstract String getText(TemporalField temporalField, long j, TextStyle textStyle, Locale locale);

    public abstract Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField temporalField, TextStyle textStyle, Locale locale);

    DateTimeTextProvider() {
    }

    static DateTimeTextProvider getInstance() {
        return new SimpleDateTimeTextProvider();
    }

    public Locale[] getAvailableLocales() {
        throw new UnsupportedOperationException();
    }
}
