package org.threeten.bp.format;

import java.util.Locale;
import org.threeten.bp.chrono.Chronology;

/* JADX INFO: loaded from: classes4.dex */
abstract class DateTimeFormatStyleProvider {
    public abstract DateTimeFormatter getFormatter(FormatStyle formatStyle, FormatStyle formatStyle2, Chronology chronology, Locale locale);

    DateTimeFormatStyleProvider() {
    }

    static DateTimeFormatStyleProvider getInstance() {
        return new SimpleDateTimeFormatStyleProvider();
    }

    public Locale[] getAvailableLocales() {
        throw new UnsupportedOperationException();
    }
}
