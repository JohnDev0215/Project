package org.joda.time.convert;

import java.util.Date;
import org.joda.time.Chronology;

/* JADX INFO: loaded from: classes6.dex */
final class DateConverter extends AbstractConverter implements InstantConverter {
    static final DateConverter INSTANCE = new DateConverter();

    protected DateConverter() {
    }

    @Override // org.joda.time.convert.AbstractConverter, org.joda.time.convert.InstantConverter
    public long getInstantMillis(Object obj, Chronology chronology) {
        return ((Date) obj).getTime();
    }

    @Override // org.joda.time.convert.Converter
    public Class<?> getSupportedType() {
        return Date.class;
    }
}
