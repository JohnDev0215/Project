package org.joda.time.format;

import java.util.Locale;
import org.joda.time.ReadablePeriod;

/* JADX INFO: loaded from: classes6.dex */
public interface PeriodPrinter {
    int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale);

    int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale);

    void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale);
}
