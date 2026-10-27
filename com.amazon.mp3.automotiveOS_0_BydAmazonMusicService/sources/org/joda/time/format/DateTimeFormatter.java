package org.joda.time.format;

import java.io.IOException;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

/* JADX INFO: loaded from: classes6.dex */
public class DateTimeFormatter {
    private final Chronology iChrono;
    private final int iDefaultYear;
    private final Locale iLocale;
    private final boolean iOffsetParsed;
    private final InternalParser iParser;
    private final Integer iPivotYear;
    private final InternalPrinter iPrinter;
    private final DateTimeZone iZone;

    DateTimeFormatter(InternalPrinter internalPrinter, InternalParser internalParser) {
        this.iPrinter = internalPrinter;
        this.iParser = internalParser;
        this.iLocale = null;
        this.iOffsetParsed = false;
        this.iChrono = null;
        this.iZone = null;
        this.iPivotYear = null;
        this.iDefaultYear = 2000;
    }

    private DateTimeFormatter(InternalPrinter internalPrinter, InternalParser internalParser, Locale locale, boolean z, Chronology chronology, DateTimeZone dateTimeZone, Integer num, int i) {
        this.iPrinter = internalPrinter;
        this.iParser = internalParser;
        this.iLocale = locale;
        this.iOffsetParsed = z;
        this.iChrono = chronology;
        this.iZone = dateTimeZone;
        this.iPivotYear = num;
        this.iDefaultYear = i;
    }

    InternalPrinter getPrinter0() {
        return this.iPrinter;
    }

    public DateTimeParser getParser() {
        return InternalParserDateTimeParser.of(this.iParser);
    }

    InternalParser getParser0() {
        return this.iParser;
    }

    public DateTimeFormatter withLocale(Locale locale) {
        return (locale == getLocale() || (locale != null && locale.equals(getLocale()))) ? this : new DateTimeFormatter(this.iPrinter, this.iParser, locale, this.iOffsetParsed, this.iChrono, this.iZone, this.iPivotYear, this.iDefaultYear);
    }

    public Locale getLocale() {
        return this.iLocale;
    }

    public DateTimeFormatter withOffsetParsed() {
        return this.iOffsetParsed ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, true, this.iChrono, null, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeFormatter withChronology(Chronology chronology) {
        return this.iChrono == chronology ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, this.iOffsetParsed, chronology, this.iZone, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeFormatter withZoneUTC() {
        return withZone(DateTimeZone.UTC);
    }

    public DateTimeFormatter withZone(DateTimeZone dateTimeZone) {
        return this.iZone == dateTimeZone ? this : new DateTimeFormatter(this.iPrinter, this.iParser, this.iLocale, false, this.iChrono, dateTimeZone, this.iPivotYear, this.iDefaultYear);
    }

    public DateTimeZone getZone() {
        return this.iZone;
    }

    public int getDefaultYear() {
        return this.iDefaultYear;
    }

    public void printTo(Appendable appendable, ReadableInstant readableInstant) throws IOException {
        printTo(appendable, DateTimeUtils.getInstantMillis(readableInstant), DateTimeUtils.getInstantChronology(readableInstant));
    }

    public void printTo(StringBuffer stringBuffer, long j) {
        try {
            printTo((Appendable) stringBuffer, j);
        } catch (IOException unused) {
        }
    }

    public void printTo(Appendable appendable, long j) throws IOException {
        printTo(appendable, j, null);
    }

    public void printTo(Appendable appendable, ReadablePartial readablePartial) throws IOException {
        InternalPrinter internalPrinterRequirePrinter = requirePrinter();
        if (readablePartial == null) {
            throw new IllegalArgumentException("The partial must not be null");
        }
        internalPrinterRequirePrinter.printTo(appendable, readablePartial, this.iLocale);
    }

    public String print(ReadableInstant readableInstant) {
        StringBuilder sb = new StringBuilder(requirePrinter().estimatePrintedLength());
        try {
            printTo(sb, readableInstant);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    public String print(ReadablePartial readablePartial) {
        StringBuilder sb = new StringBuilder(requirePrinter().estimatePrintedLength());
        try {
            printTo(sb, readablePartial);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    private void printTo(Appendable appendable, long j, Chronology chronology) throws IOException {
        InternalPrinter internalPrinterRequirePrinter = requirePrinter();
        Chronology chronologySelectChronology = selectChronology(chronology);
        DateTimeZone zone = chronologySelectChronology.getZone();
        int offset = zone.getOffset(j);
        long j2 = offset;
        long j3 = j + j2;
        if ((j ^ j3) < 0 && (j2 ^ j) >= 0) {
            zone = DateTimeZone.UTC;
            offset = 0;
            j3 = j;
        }
        internalPrinterRequirePrinter.printTo(appendable, j3, chronologySelectChronology.withUTC(), offset, zone, this.iLocale);
    }

    private InternalPrinter requirePrinter() {
        InternalPrinter internalPrinter = this.iPrinter;
        if (internalPrinter != null) {
            return internalPrinter;
        }
        throw new UnsupportedOperationException("Printing not supported");
    }

    public long parseMillis(String str) {
        return new DateTimeParserBucket(0L, selectChronology(this.iChrono), this.iLocale, this.iPivotYear, this.iDefaultYear).doParseMillis(requireParser(), str);
    }

    public LocalDate parseLocalDate(String str) {
        return parseLocalDateTime(str).toLocalDate();
    }

    public LocalTime parseLocalTime(String str) {
        return parseLocalDateTime(str).toLocalTime();
    }

    public LocalDateTime parseLocalDateTime(String str) {
        InternalParser internalParserRequireParser = requireParser();
        Chronology chronologyWithUTC = selectChronology(null).withUTC();
        DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0L, chronologyWithUTC, this.iLocale, this.iPivotYear, this.iDefaultYear);
        int into = internalParserRequireParser.parseInto(dateTimeParserBucket, str, 0);
        if (into < 0) {
            into = ~into;
        } else if (into >= str.length()) {
            long jComputeMillis = dateTimeParserBucket.computeMillis(true, str);
            if (dateTimeParserBucket.getOffsetInteger() != null) {
                chronologyWithUTC = chronologyWithUTC.withZone(DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffsetInteger().intValue()));
            } else if (dateTimeParserBucket.getZone() != null) {
                chronologyWithUTC = chronologyWithUTC.withZone(dateTimeParserBucket.getZone());
            }
            return new LocalDateTime(jComputeMillis, chronologyWithUTC);
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(str, into));
    }

    public DateTime parseDateTime(String str) {
        InternalParser internalParserRequireParser = requireParser();
        Chronology chronologySelectChronology = selectChronology(null);
        DateTimeParserBucket dateTimeParserBucket = new DateTimeParserBucket(0L, chronologySelectChronology, this.iLocale, this.iPivotYear, this.iDefaultYear);
        int into = internalParserRequireParser.parseInto(dateTimeParserBucket, str, 0);
        if (into < 0) {
            into = ~into;
        } else if (into >= str.length()) {
            long jComputeMillis = dateTimeParserBucket.computeMillis(true, str);
            if (this.iOffsetParsed && dateTimeParserBucket.getOffsetInteger() != null) {
                chronologySelectChronology = chronologySelectChronology.withZone(DateTimeZone.forOffsetMillis(dateTimeParserBucket.getOffsetInteger().intValue()));
            } else if (dateTimeParserBucket.getZone() != null) {
                chronologySelectChronology = chronologySelectChronology.withZone(dateTimeParserBucket.getZone());
            }
            DateTime dateTime = new DateTime(jComputeMillis, chronologySelectChronology);
            DateTimeZone dateTimeZone = this.iZone;
            return dateTimeZone != null ? dateTime.withZone(dateTimeZone) : dateTime;
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(str, into));
    }

    private InternalParser requireParser() {
        InternalParser internalParser = this.iParser;
        if (internalParser != null) {
            return internalParser;
        }
        throw new UnsupportedOperationException("Parsing not supported");
    }

    private Chronology selectChronology(Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        Chronology chronology3 = this.iChrono;
        if (chronology3 != null) {
            chronology2 = chronology3;
        }
        DateTimeZone dateTimeZone = this.iZone;
        return dateTimeZone != null ? chronology2.withZone(dateTimeZone) : chronology2;
    }
}
