package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

/* JADX INFO: loaded from: classes6.dex */
public interface InstantConverter extends Converter {
    Chronology getChronology(Object obj, DateTimeZone dateTimeZone);

    long getInstantMillis(Object obj, Chronology chronology);
}
