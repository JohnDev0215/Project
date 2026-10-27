package org.threeten.bp.format;

import cerence.ark.assistant.library.JsonKey;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;

/* JADX INFO: loaded from: classes4.dex */
final class DateTimeParseContext {
    private boolean caseSensitive;
    private Locale locale;
    private Chronology overrideChronology;
    private ZoneId overrideZone;
    private final ArrayList<Parsed> parsed;
    private boolean strict;
    private DecimalStyle symbols;

    DateTimeParseContext(DateTimeFormatter dateTimeFormatter) {
        this.caseSensitive = true;
        this.strict = true;
        ArrayList<Parsed> arrayList = new ArrayList<>();
        this.parsed = arrayList;
        this.locale = dateTimeFormatter.getLocale();
        this.symbols = dateTimeFormatter.getDecimalStyle();
        this.overrideChronology = dateTimeFormatter.getChronology();
        this.overrideZone = dateTimeFormatter.getZone();
        arrayList.add(new Parsed());
    }

    DateTimeParseContext(Locale locale, DecimalStyle decimalStyle, Chronology chronology) {
        this.caseSensitive = true;
        this.strict = true;
        ArrayList<Parsed> arrayList = new ArrayList<>();
        this.parsed = arrayList;
        this.locale = locale;
        this.symbols = decimalStyle;
        this.overrideChronology = chronology;
        this.overrideZone = null;
        arrayList.add(new Parsed());
    }

    DateTimeParseContext(DateTimeParseContext dateTimeParseContext) {
        this.caseSensitive = true;
        this.strict = true;
        ArrayList<Parsed> arrayList = new ArrayList<>();
        this.parsed = arrayList;
        this.locale = dateTimeParseContext.locale;
        this.symbols = dateTimeParseContext.symbols;
        this.overrideChronology = dateTimeParseContext.overrideChronology;
        this.overrideZone = dateTimeParseContext.overrideZone;
        this.caseSensitive = dateTimeParseContext.caseSensitive;
        this.strict = dateTimeParseContext.strict;
        arrayList.add(new Parsed());
    }

    DateTimeParseContext copy() {
        return new DateTimeParseContext(this);
    }

    Locale getLocale() {
        return this.locale;
    }

    DecimalStyle getSymbols() {
        return this.symbols;
    }

    Chronology getEffectiveChronology() {
        Chronology chronology = currentParsed().chrono;
        if (chronology != null) {
            return chronology;
        }
        Chronology chronology2 = this.overrideChronology;
        return chronology2 == null ? IsoChronology.INSTANCE : chronology2;
    }

    boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    void setCaseSensitive(boolean z) {
        this.caseSensitive = z;
    }

    boolean subSequenceEquals(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3) {
        if (i + i3 > charSequence.length() || i2 + i3 > charSequence2.length()) {
            return false;
        }
        if (isCaseSensitive()) {
            for (int i4 = 0; i4 < i3; i4++) {
                if (charSequence.charAt(i + i4) != charSequence2.charAt(i2 + i4)) {
                    return false;
                }
            }
            return true;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            char cCharAt = charSequence.charAt(i + i5);
            char cCharAt2 = charSequence2.charAt(i2 + i5);
            if (cCharAt != cCharAt2 && Character.toUpperCase(cCharAt) != Character.toUpperCase(cCharAt2) && Character.toLowerCase(cCharAt) != Character.toLowerCase(cCharAt2)) {
                return false;
            }
        }
        return true;
    }

    boolean charEquals(char c, char c2) {
        if (isCaseSensitive()) {
            return c == c2;
        }
        return charEqualsIgnoreCase(c, c2);
    }

    static boolean charEqualsIgnoreCase(char c, char c2) {
        return c == c2 || Character.toUpperCase(c) == Character.toUpperCase(c2) || Character.toLowerCase(c) == Character.toLowerCase(c2);
    }

    boolean isStrict() {
        return this.strict;
    }

    void setStrict(boolean z) {
        this.strict = z;
    }

    void startOptional() {
        this.parsed.add(currentParsed().copy());
    }

    void endOptional(boolean z) {
        if (z) {
            this.parsed.remove(r2.size() - 2);
        } else {
            this.parsed.remove(r2.size() - 1);
        }
    }

    private Parsed currentParsed() {
        return this.parsed.get(r0.size() - 1);
    }

    Long getParsed(TemporalField temporalField) {
        return currentParsed().fieldValues.get(temporalField);
    }

    int setParsedField(TemporalField temporalField, long j, int i, int i2) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Long lPut = currentParsed().fieldValues.put(temporalField, Long.valueOf(j));
        return (lPut == null || lPut.longValue() == j) ? i2 : ~i;
    }

    void setParsed(Chronology chronology) {
        Jdk8Methods.requireNonNull(chronology, "chrono");
        Parsed parsedCurrentParsed = currentParsed();
        parsedCurrentParsed.chrono = chronology;
        if (parsedCurrentParsed.callbacks != null) {
            ArrayList<Object[]> arrayList = new ArrayList(parsedCurrentParsed.callbacks);
            parsedCurrentParsed.callbacks.clear();
            for (Object[] objArr : arrayList) {
                ((DateTimeFormatterBuilder.ReducedPrinterParser) objArr[0]).setValue(this, ((Long) objArr[1]).longValue(), ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue());
            }
        }
    }

    void addChronologyChangedParser(DateTimeFormatterBuilder.ReducedPrinterParser reducedPrinterParser, long j, int i, int i2) {
        Parsed parsedCurrentParsed = currentParsed();
        if (parsedCurrentParsed.callbacks == null) {
            parsedCurrentParsed.callbacks = new ArrayList(2);
        }
        parsedCurrentParsed.callbacks.add(new Object[]{reducedPrinterParser, Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(i2)});
    }

    void setParsed(ZoneId zoneId) {
        Jdk8Methods.requireNonNull(zoneId, JsonKey.NOTIFICATION_ZONE);
        currentParsed().zone = zoneId;
    }

    void setParsedLeapSecond() {
        currentParsed().leapSecond = true;
    }

    Parsed toParsed() {
        return currentParsed();
    }

    public String toString() {
        return currentParsed().toString();
    }

    final class Parsed extends DefaultInterfaceTemporalAccessor {
        List<Object[]> callbacks;
        Chronology chrono;
        Period excessDays;
        final Map<TemporalField, Long> fieldValues;
        boolean leapSecond;
        ZoneId zone;

        private Parsed() {
            this.chrono = null;
            this.zone = null;
            this.fieldValues = new HashMap();
            this.excessDays = Period.ZERO;
        }

        protected Parsed copy() {
            Parsed parsed = DateTimeParseContext.this.new Parsed();
            parsed.chrono = this.chrono;
            parsed.zone = this.zone;
            parsed.fieldValues.putAll(this.fieldValues);
            parsed.leapSecond = this.leapSecond;
            return parsed;
        }

        public String toString() {
            return this.fieldValues.toString() + ListUtils.DEFAULT_JOIN_SEPARATOR + this.chrono + ListUtils.DEFAULT_JOIN_SEPARATOR + this.zone;
        }

        @Override // org.threeten.bp.temporal.TemporalAccessor
        public boolean isSupported(TemporalField temporalField) {
            return this.fieldValues.containsKey(temporalField);
        }

        @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
        public int get(TemporalField temporalField) {
            if (!this.fieldValues.containsKey(temporalField)) {
                throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            }
            return Jdk8Methods.safeToInt(this.fieldValues.get(temporalField).longValue());
        }

        @Override // org.threeten.bp.temporal.TemporalAccessor
        public long getLong(TemporalField temporalField) {
            if (!this.fieldValues.containsKey(temporalField)) {
                throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            }
            return this.fieldValues.get(temporalField).longValue();
        }

        @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
        public <R> R query(TemporalQuery<R> temporalQuery) {
            if (temporalQuery == TemporalQueries.chronology()) {
                return (R) this.chrono;
            }
            if (temporalQuery == TemporalQueries.zoneId() || temporalQuery == TemporalQueries.zone()) {
                return (R) this.zone;
            }
            return (R) super.query(temporalQuery);
        }

        DateTimeBuilder toBuilder() {
            DateTimeBuilder dateTimeBuilder = new DateTimeBuilder();
            dateTimeBuilder.fieldValues.putAll(this.fieldValues);
            dateTimeBuilder.chrono = DateTimeParseContext.this.getEffectiveChronology();
            ZoneId zoneId = this.zone;
            if (zoneId == null) {
                dateTimeBuilder.zone = DateTimeParseContext.this.overrideZone;
            } else {
                dateTimeBuilder.zone = zoneId;
            }
            dateTimeBuilder.leapSecond = this.leapSecond;
            dateTimeBuilder.excessDays = this.excessDays;
            return dateTimeBuilder;
        }
    }

    void setLocale(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        this.locale = locale;
    }
}
