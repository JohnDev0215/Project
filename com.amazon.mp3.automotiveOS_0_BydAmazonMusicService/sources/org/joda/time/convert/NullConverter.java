package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.ReadWritablePeriod;

/* JADX INFO: loaded from: classes6.dex */
class NullConverter extends AbstractConverter implements InstantConverter, PeriodConverter {
    static final NullConverter INSTANCE = new NullConverter();

    @Override // org.joda.time.convert.Converter
    public Class<?> getSupportedType() {
        return null;
    }

    protected NullConverter() {
    }

    @Override // org.joda.time.convert.PeriodConverter
    public void setInto(ReadWritablePeriod readWritablePeriod, Object obj, Chronology chronology) {
        readWritablePeriod.setPeriod(null);
    }
}
