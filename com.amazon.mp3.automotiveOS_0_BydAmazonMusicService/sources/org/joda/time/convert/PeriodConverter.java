package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;

/* JADX INFO: loaded from: classes6.dex */
public interface PeriodConverter extends Converter {
    PeriodType getPeriodType(Object obj);

    void setInto(ReadWritablePeriod readWritablePeriod, Object obj, Chronology chronology);
}
