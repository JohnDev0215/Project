package org.threeten.bp.format;

import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.ValueRange;

/* JADX INFO: loaded from: classes4.dex */
final class DateTimePrintContext {
    private Locale locale;
    private int optional;
    private DecimalStyle symbols;
    private TemporalAccessor temporal;

    DateTimePrintContext(TemporalAccessor temporalAccessor, DateTimeFormatter dateTimeFormatter) {
        this.temporal = adjust(temporalAccessor, dateTimeFormatter);
        this.locale = dateTimeFormatter.getLocale();
        this.symbols = dateTimeFormatter.getDecimalStyle();
    }

    DateTimePrintContext(TemporalAccessor temporalAccessor, Locale locale, DecimalStyle decimalStyle) {
        this.temporal = temporalAccessor;
        this.locale = locale;
        this.symbols = decimalStyle;
    }

    private static TemporalAccessor adjust(final TemporalAccessor temporalAccessor, DateTimeFormatter dateTimeFormatter) {
        Chronology chronology = dateTimeFormatter.getChronology();
        ZoneId zone = dateTimeFormatter.getZone();
        if (chronology == null && zone == null) {
            return temporalAccessor;
        }
        Chronology chronology2 = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        final ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zoneId());
        final ChronoLocalDate chronoLocalDateDate = null;
        if (Jdk8Methods.equals(chronology2, chronology)) {
            chronology = null;
        }
        if (Jdk8Methods.equals(zoneId, zone)) {
            zone = null;
        }
        if (chronology == null && zone == null) {
            return temporalAccessor;
        }
        final Chronology chronology3 = chronology != null ? chronology : chronology2;
        if (zone != null) {
            zoneId = zone;
        }
        if (zone != null) {
            if (temporalAccessor.isSupported(ChronoField.INSTANT_SECONDS)) {
                if (chronology3 == null) {
                    chronology3 = IsoChronology.INSTANCE;
                }
                return chronology3.zonedDateTime(Instant.from(temporalAccessor), zone);
            }
            ZoneId zoneIdNormalized = zone.normalized();
            ZoneOffset zoneOffset = (ZoneOffset) temporalAccessor.query(TemporalQueries.offset());
            if ((zoneIdNormalized instanceof ZoneOffset) && zoneOffset != null && !zoneIdNormalized.equals(zoneOffset)) {
                throw new DateTimeException("Invalid override zone for temporal: " + zone + StringUtils.SPACE + temporalAccessor);
            }
        }
        if (chronology != null) {
            if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                chronoLocalDateDate = chronology3.date(temporalAccessor);
            } else if (chronology != IsoChronology.INSTANCE || chronology2 != null) {
                for (ChronoField chronoField : ChronoField.values()) {
                    if (chronoField.isDateBased() && temporalAccessor.isSupported(chronoField)) {
                        throw new DateTimeException("Invalid override chronology for temporal: " + chronology + StringUtils.SPACE + temporalAccessor);
                    }
                }
            }
        }
        return new DefaultInterfaceTemporalAccessor() { // from class: org.threeten.bp.format.DateTimePrintContext.1
            @Override // org.threeten.bp.temporal.TemporalAccessor
            public boolean isSupported(TemporalField temporalField) {
                if (chronoLocalDateDate != null && temporalField.isDateBased()) {
                    return chronoLocalDateDate.isSupported(temporalField);
                }
                return temporalAccessor.isSupported(temporalField);
            }

            @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
            public ValueRange range(TemporalField temporalField) {
                if (chronoLocalDateDate != null && temporalField.isDateBased()) {
                    return chronoLocalDateDate.range(temporalField);
                }
                return temporalAccessor.range(temporalField);
            }

            @Override // org.threeten.bp.temporal.TemporalAccessor
            public long getLong(TemporalField temporalField) {
                if (chronoLocalDateDate != null && temporalField.isDateBased()) {
                    return chronoLocalDateDate.getLong(temporalField);
                }
                return temporalAccessor.getLong(temporalField);
            }

            @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
            public <R> R query(TemporalQuery<R> temporalQuery) {
                if (temporalQuery == TemporalQueries.chronology()) {
                    return (R) chronology3;
                }
                if (temporalQuery == TemporalQueries.zoneId()) {
                    return (R) zoneId;
                }
                if (temporalQuery == TemporalQueries.precision()) {
                    return (R) temporalAccessor.query(temporalQuery);
                }
                return temporalQuery.queryFrom(this);
            }
        };
    }

    TemporalAccessor getTemporal() {
        return this.temporal;
    }

    Locale getLocale() {
        return this.locale;
    }

    DecimalStyle getSymbols() {
        return this.symbols;
    }

    void startOptional() {
        this.optional++;
    }

    void endOptional() {
        this.optional--;
    }

    <R> R getValue(TemporalQuery<R> temporalQuery) {
        R r = (R) this.temporal.query(temporalQuery);
        if (r == null && this.optional == 0) {
            throw new DateTimeException("Unable to extract value: " + this.temporal.getClass());
        }
        return r;
    }

    Long getValue(TemporalField temporalField) {
        try {
            return Long.valueOf(this.temporal.getLong(temporalField));
        } catch (DateTimeException e) {
            if (this.optional > 0) {
                return null;
            }
            throw e;
        }
    }

    public String toString() {
        return this.temporal.toString();
    }

    void setDateTime(TemporalAccessor temporalAccessor) {
        Jdk8Methods.requireNonNull(temporalAccessor, "temporal");
        this.temporal = temporalAccessor;
    }

    void setLocale(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        this.locale = locale;
    }
}
