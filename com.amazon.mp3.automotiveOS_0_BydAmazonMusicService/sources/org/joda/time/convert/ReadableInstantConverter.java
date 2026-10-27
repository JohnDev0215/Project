package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableInstant;
import org.joda.time.chrono.ISOChronology;

/* JADX INFO: loaded from: classes6.dex */
class ReadableInstantConverter extends AbstractConverter implements InstantConverter {
    static final ReadableInstantConverter INSTANCE = new ReadableInstantConverter();

    protected ReadableInstantConverter() {
    }

    @Override // org.joda.time.convert.AbstractConverter, org.joda.time.convert.InstantConverter
    public Chronology getChronology(Object obj, DateTimeZone dateTimeZone) {
        Chronology chronology = ((ReadableInstant) obj).getChronology();
        if (chronology == null) {
            return ISOChronology.getInstance(dateTimeZone);
        }
        if (chronology.getZone() == dateTimeZone) {
            return chronology;
        }
        Chronology chronologyWithZone = chronology.withZone(dateTimeZone);
        return chronologyWithZone == null ? ISOChronology.getInstance(dateTimeZone) : chronologyWithZone;
    }

    @Override // org.joda.time.convert.AbstractConverter, org.joda.time.convert.InstantConverter
    public long getInstantMillis(Object obj, Chronology chronology) {
        return ((ReadableInstant) obj).getMillis();
    }

    @Override // org.joda.time.convert.Converter
    public Class<?> getSupportedType() {
        return ReadableInstant.class;
    }
}
