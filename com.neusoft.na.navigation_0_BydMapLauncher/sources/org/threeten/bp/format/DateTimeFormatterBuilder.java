package org.threeten.bp.format;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import com.bydux.api.common.tools.util.ListUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import kotlin.time.DurationKt;
import okhttp3.internal.http2.Http2Connection;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.slf4j.Marker;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.format.SimpleDateTimeTextProvider;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.IsoFields;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.ValueRange;
import org.threeten.bp.temporal.WeekFields;
import org.threeten.bp.zone.ZoneRulesProvider;

/* JADX INFO: loaded from: classes4.dex */
public final class DateTimeFormatterBuilder {
    private static final Map<Character, TemporalField> FIELD_MAP;
    static final Comparator<String> LENGTH_SORT;
    private static final TemporalQuery<ZoneId> QUERY_REGION_ONLY = new TemporalQuery<ZoneId>() { // from class: org.threeten.bp.format.DateTimeFormatterBuilder.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        public ZoneId queryFrom(TemporalAccessor temporalAccessor) {
            ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zoneId());
            if (zoneId == null || (zoneId instanceof ZoneOffset)) {
                return null;
            }
            return zoneId;
        }
    };
    private DateTimeFormatterBuilder active;
    private final boolean optional;
    private char padNextChar;
    private int padNextWidth;
    private final DateTimeFormatterBuilder parent;
    private final List<DateTimePrinterParser> printerParsers;
    private int valueParserIndex;

    interface DateTimePrinterParser {
        int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i);

        boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb);
    }

    static {
        HashMap map = new HashMap();
        FIELD_MAP = map;
        map.put('G', ChronoField.ERA);
        map.put('y', ChronoField.YEAR_OF_ERA);
        map.put('u', ChronoField.YEAR);
        map.put('Q', IsoFields.QUARTER_OF_YEAR);
        map.put('q', IsoFields.QUARTER_OF_YEAR);
        map.put('M', ChronoField.MONTH_OF_YEAR);
        map.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT), ChronoField.MONTH_OF_YEAR);
        map.put('D', ChronoField.DAY_OF_YEAR);
        map.put('d', ChronoField.DAY_OF_MONTH);
        map.put('F', ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        map.put('E', ChronoField.DAY_OF_WEEK);
        map.put('c', ChronoField.DAY_OF_WEEK);
        map.put('e', ChronoField.DAY_OF_WEEK);
        map.put('a', ChronoField.AMPM_OF_DAY);
        map.put('H', ChronoField.HOUR_OF_DAY);
        map.put('k', ChronoField.CLOCK_HOUR_OF_DAY);
        map.put('K', ChronoField.HOUR_OF_AMPM);
        map.put('h', ChronoField.CLOCK_HOUR_OF_AMPM);
        map.put('m', ChronoField.MINUTE_OF_HOUR);
        map.put('s', ChronoField.SECOND_OF_MINUTE);
        map.put('S', ChronoField.NANO_OF_SECOND);
        map.put('A', ChronoField.MILLI_OF_DAY);
        map.put('n', ChronoField.NANO_OF_SECOND);
        map.put('N', ChronoField.NANO_OF_DAY);
        LENGTH_SORT = new Comparator<String>() { // from class: org.threeten.bp.format.DateTimeFormatterBuilder.3
            @Override // java.util.Comparator
            public int compare(String str, String str2) {
                return str.length() == str2.length() ? str.compareTo(str2) : str.length() - str2.length();
            }
        };
    }

    public static String getLocalizedDateTimePattern(FormatStyle formatStyle, FormatStyle formatStyle2, Chronology chronology, Locale locale) {
        DateFormat timeInstance;
        Jdk8Methods.requireNonNull(locale, "locale");
        Jdk8Methods.requireNonNull(chronology, "chrono");
        if (formatStyle == null && formatStyle2 == null) {
            throw new IllegalArgumentException("Either dateStyle or timeStyle must be non-null");
        }
        if (formatStyle == null) {
            timeInstance = DateFormat.getTimeInstance(formatStyle2.ordinal(), locale);
        } else if (formatStyle2 != null) {
            timeInstance = DateFormat.getDateTimeInstance(formatStyle.ordinal(), formatStyle2.ordinal(), locale);
        } else {
            timeInstance = DateFormat.getDateInstance(formatStyle.ordinal(), locale);
        }
        if (timeInstance instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) timeInstance).toPattern();
        }
        throw new IllegalArgumentException("Unable to determine pattern");
    }

    public DateTimeFormatterBuilder() {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = null;
        this.optional = false;
    }

    private DateTimeFormatterBuilder(DateTimeFormatterBuilder dateTimeFormatterBuilder, boolean z) {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = dateTimeFormatterBuilder;
        this.optional = z;
    }

    public DateTimeFormatterBuilder parseCaseSensitive() {
        appendInternal(SettingsParser.SENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseCaseInsensitive() {
        appendInternal(SettingsParser.INSENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseStrict() {
        appendInternal(SettingsParser.STRICT);
        return this;
    }

    public DateTimeFormatterBuilder parseLenient() {
        appendInternal(SettingsParser.LENIENT);
        return this;
    }

    public DateTimeFormatterBuilder parseDefaulting(TemporalField temporalField, long j) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        appendInternal(new DefaultingParser(temporalField, j));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        appendValue(new NumberPrinterParser(temporalField, 1, 19, SignStyle.NORMAL));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField, int i) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        if (i < 1 || i > 19) {
            throw new IllegalArgumentException("The width must be from 1 to 19 inclusive but was " + i);
        }
        appendValue(new NumberPrinterParser(temporalField, i, i, SignStyle.NOT_NEGATIVE));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField, int i, int i2, SignStyle signStyle) {
        if (i == i2 && signStyle == SignStyle.NOT_NEGATIVE) {
            return appendValue(temporalField, i2);
        }
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(signStyle, "signStyle");
        if (i < 1 || i > 19) {
            throw new IllegalArgumentException("The minimum width must be from 1 to 19 inclusive but was " + i);
        }
        if (i2 < 1 || i2 > 19) {
            throw new IllegalArgumentException("The maximum width must be from 1 to 19 inclusive but was " + i2);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("The maximum width must exceed or equal the minimum width but " + i2 + " < " + i);
        }
        appendValue(new NumberPrinterParser(temporalField, i, i2, signStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendValueReduced(TemporalField temporalField, int i, int i2, int i3) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        appendValue(new ReducedPrinterParser(temporalField, i, i2, i3, null));
        return this;
    }

    public DateTimeFormatterBuilder appendValueReduced(TemporalField temporalField, int i, int i2, ChronoLocalDate chronoLocalDate) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(chronoLocalDate, "baseDate");
        appendValue(new ReducedPrinterParser(temporalField, i, i2, 0, chronoLocalDate));
        return this;
    }

    private DateTimeFormatterBuilder appendValue(NumberPrinterParser numberPrinterParser) {
        NumberPrinterParser numberPrinterParserWithFixedWidth;
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        int i = dateTimeFormatterBuilder.valueParserIndex;
        if (i >= 0 && (dateTimeFormatterBuilder.printerParsers.get(i) instanceof NumberPrinterParser)) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
            int i2 = dateTimeFormatterBuilder2.valueParserIndex;
            NumberPrinterParser numberPrinterParser2 = (NumberPrinterParser) dateTimeFormatterBuilder2.printerParsers.get(i2);
            if (numberPrinterParser.minWidth == numberPrinterParser.maxWidth && numberPrinterParser.signStyle == SignStyle.NOT_NEGATIVE) {
                numberPrinterParserWithFixedWidth = numberPrinterParser2.withSubsequentWidth(numberPrinterParser.maxWidth);
                appendInternal(numberPrinterParser.withFixedWidth());
                this.active.valueParserIndex = i2;
            } else {
                numberPrinterParserWithFixedWidth = numberPrinterParser2.withFixedWidth();
                this.active.valueParserIndex = appendInternal(numberPrinterParser);
            }
            this.active.printerParsers.set(i2, numberPrinterParserWithFixedWidth);
        } else {
            this.active.valueParserIndex = appendInternal(numberPrinterParser);
        }
        return this;
    }

    public DateTimeFormatterBuilder appendFraction(TemporalField temporalField, int i, int i2, boolean z) {
        appendInternal(new FractionPrinterParser(temporalField, i, i2, z));
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField temporalField) {
        return appendText(temporalField, TextStyle.FULL);
    }

    public DateTimeFormatterBuilder appendText(TemporalField temporalField, TextStyle textStyle) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(textStyle, "textStyle");
        appendInternal(new TextPrinterParser(temporalField, textStyle, DateTimeTextProvider.getInstance()));
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField temporalField, Map<Long, String> map) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(map, "textLookup");
        final SimpleDateTimeTextProvider.LocaleStore localeStore = new SimpleDateTimeTextProvider.LocaleStore(Collections.singletonMap(TextStyle.FULL, new LinkedHashMap(map)));
        appendInternal(new TextPrinterParser(temporalField, TextStyle.FULL, new DateTimeTextProvider() { // from class: org.threeten.bp.format.DateTimeFormatterBuilder.2
            @Override // org.threeten.bp.format.DateTimeTextProvider
            public String getText(TemporalField temporalField2, long j, TextStyle textStyle, Locale locale) {
                return localeStore.getText(j, textStyle);
            }

            @Override // org.threeten.bp.format.DateTimeTextProvider
            public Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField temporalField2, TextStyle textStyle, Locale locale) {
                return localeStore.getTextIterator(textStyle);
            }
        }));
        return this;
    }

    public DateTimeFormatterBuilder appendInstant() {
        appendInternal(new InstantPrinterParser(-2));
        return this;
    }

    public DateTimeFormatterBuilder appendInstant(int i) {
        if (i < -1 || i > 9) {
            throw new IllegalArgumentException("Invalid fractional digits: " + i);
        }
        appendInternal(new InstantPrinterParser(i));
        return this;
    }

    public DateTimeFormatterBuilder appendOffsetId() {
        appendInternal(OffsetIdPrinterParser.INSTANCE_ID);
        return this;
    }

    public DateTimeFormatterBuilder appendOffset(String str, String str2) {
        appendInternal(new OffsetIdPrinterParser(str2, str));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalizedOffset(TextStyle textStyle) {
        Jdk8Methods.requireNonNull(textStyle, "style");
        if (textStyle != TextStyle.FULL && textStyle != TextStyle.SHORT) {
            throw new IllegalArgumentException("Style must be either full or short");
        }
        appendInternal(new LocalizedOffsetPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneId() {
        appendInternal(new ZoneIdPrinterParser(TemporalQueries.zoneId(), "ZoneId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneRegionId() {
        appendInternal(new ZoneIdPrinterParser(QUERY_REGION_ONLY, "ZoneRegionId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneOrOffsetId() {
        appendInternal(new ZoneIdPrinterParser(TemporalQueries.zone(), "ZoneOrOffsetId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle) {
        appendInternal(new ZoneTextPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle, Set<ZoneId> set) {
        Jdk8Methods.requireNonNull(set, "preferredZones");
        appendInternal(new ZoneTextPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendChronologyId() {
        appendInternal(new ChronoPrinterParser(null));
        return this;
    }

    public DateTimeFormatterBuilder appendChronologyText(TextStyle textStyle) {
        Jdk8Methods.requireNonNull(textStyle, "textStyle");
        appendInternal(new ChronoPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalized(FormatStyle formatStyle, FormatStyle formatStyle2) {
        if (formatStyle == null && formatStyle2 == null) {
            throw new IllegalArgumentException("Either the date or time style must be non-null");
        }
        appendInternal(new LocalizedPrinterParser(formatStyle, formatStyle2));
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(char c) {
        appendInternal(new CharLiteralPrinterParser(c));
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(String str) {
        Jdk8Methods.requireNonNull(str, "literal");
        if (str.length() > 0) {
            if (str.length() == 1) {
                appendInternal(new CharLiteralPrinterParser(str.charAt(0)));
            } else {
                appendInternal(new StringLiteralPrinterParser(str));
            }
        }
        return this;
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        appendInternal(dateTimeFormatter.toPrinterParser(false));
        return this;
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        appendInternal(dateTimeFormatter.toPrinterParser(true));
        return this;
    }

    public DateTimeFormatterBuilder appendPattern(String str) {
        Jdk8Methods.requireNonNull(str, BYDAutoMultimediaDevice.PARAM_PATTERN);
        parsePattern(str);
        return this;
    }

    private void parsePattern(String str) {
        int i;
        int i2 = 0;
        while (i2 < str.length()) {
            char cCharAt = str.charAt(i2);
            if ((cCharAt >= 'A' && cCharAt <= 'Z') || (cCharAt >= 'a' && cCharAt <= 'z')) {
                int i3 = i2 + 1;
                while (i3 < str.length() && str.charAt(i3) == cCharAt) {
                    i3++;
                }
                int i4 = i3 - i2;
                if (cCharAt == 'p') {
                    if (i3 >= str.length() || (((cCharAt = str.charAt(i3)) < 'A' || cCharAt > 'Z') && (cCharAt < 'a' || cCharAt > 'z'))) {
                        i = i4;
                        i4 = 0;
                    } else {
                        int i5 = i3 + 1;
                        while (i5 < str.length() && str.charAt(i5) == cCharAt) {
                            i5++;
                        }
                        i = i5 - i3;
                        i3 = i5;
                    }
                    if (i4 == 0) {
                        throw new IllegalArgumentException("Pad letter 'p' must be followed by valid pad pattern: " + str);
                    }
                    padNext(i4);
                    i4 = i;
                }
                TemporalField temporalField = FIELD_MAP.get(Character.valueOf(cCharAt));
                if (temporalField != null) {
                    parseField(cCharAt, i4, temporalField);
                } else if (cCharAt == 'z') {
                    if (i4 > 4) {
                        throw new IllegalArgumentException("Too many pattern letters: " + cCharAt);
                    }
                    if (i4 == 4) {
                        appendZoneText(TextStyle.FULL);
                    } else {
                        appendZoneText(TextStyle.SHORT);
                    }
                } else if (cCharAt != 'V') {
                    String str2 = "+0000";
                    if (cCharAt == 'Z') {
                        if (i4 < 4) {
                            appendOffset("+HHMM", "+0000");
                        } else if (i4 == 4) {
                            appendLocalizedOffset(TextStyle.FULL);
                        } else if (i4 == 5) {
                            appendOffset("+HH:MM:ss", "Z");
                        } else {
                            throw new IllegalArgumentException("Too many pattern letters: " + cCharAt);
                        }
                    } else if (cCharAt == 'O') {
                        if (i4 == 1) {
                            appendLocalizedOffset(TextStyle.SHORT);
                        } else if (i4 == 4) {
                            appendLocalizedOffset(TextStyle.FULL);
                        } else {
                            throw new IllegalArgumentException("Pattern letter count must be 1 or 4: " + cCharAt);
                        }
                    } else if (cCharAt == 'X') {
                        if (i4 > 5) {
                            throw new IllegalArgumentException("Too many pattern letters: " + cCharAt);
                        }
                        appendOffset(OffsetIdPrinterParser.PATTERNS[i4 + (i4 == 1 ? 0 : 1)], "Z");
                    } else if (cCharAt == 'x') {
                        if (i4 > 5) {
                            throw new IllegalArgumentException("Too many pattern letters: " + cCharAt);
                        }
                        if (i4 == 1) {
                            str2 = "+00";
                        } else if (i4 % 2 != 0) {
                            str2 = "+00:00";
                        }
                        appendOffset(OffsetIdPrinterParser.PATTERNS[i4 + (i4 == 1 ? 0 : 1)], str2);
                    } else if (cCharAt == 'W') {
                        if (i4 > 1) {
                            throw new IllegalArgumentException("Too many pattern letters: " + cCharAt);
                        }
                        appendInternal(new WeekFieldsPrinterParser('W', i4));
                    } else if (cCharAt == 'w') {
                        if (i4 > 2) {
                            throw new IllegalArgumentException("Too many pattern letters: " + cCharAt);
                        }
                        appendInternal(new WeekFieldsPrinterParser('w', i4));
                    } else if (cCharAt == 'Y') {
                        appendInternal(new WeekFieldsPrinterParser('Y', i4));
                    } else {
                        throw new IllegalArgumentException("Unknown pattern letter: " + cCharAt);
                    }
                } else {
                    if (i4 != 2) {
                        throw new IllegalArgumentException("Pattern letter count must be 2: " + cCharAt);
                    }
                    appendZoneId();
                }
                i2 = i3 - 1;
            } else if (cCharAt == '\'') {
                int i6 = i2 + 1;
                int i7 = i6;
                while (i7 < str.length()) {
                    if (str.charAt(i7) == '\'') {
                        int i8 = i7 + 1;
                        if (i8 >= str.length() || str.charAt(i8) != '\'') {
                            break;
                        } else {
                            i7 = i8;
                        }
                    }
                    i7++;
                }
                if (i7 >= str.length()) {
                    throw new IllegalArgumentException("Pattern ends with an incomplete string literal: " + str);
                }
                String strSubstring = str.substring(i6, i7);
                if (strSubstring.length() == 0) {
                    appendLiteral('\'');
                } else {
                    appendLiteral(strSubstring.replace("''", "'"));
                }
                i2 = i7;
            } else if (cCharAt == '[') {
                optionalStart();
            } else if (cCharAt == ']') {
                if (this.active.parent == null) {
                    throw new IllegalArgumentException("Pattern invalid as it contains ] without previous [");
                }
                optionalEnd();
            } else {
                if (cCharAt == '{' || cCharAt == '}' || cCharAt == '#') {
                    throw new IllegalArgumentException("Pattern includes reserved character: '" + cCharAt + "'");
                }
                appendLiteral(cCharAt);
            }
            i2++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:91:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseField(char r8, int r9, org.threeten.bp.temporal.TemporalField r10) {
        /*
            Method dump skipped, instruction units count: 566
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatterBuilder.parseField(char, int, org.threeten.bp.temporal.TemporalField):void");
    }

    public DateTimeFormatterBuilder padNext(int i) {
        return padNext(i, ' ');
    }

    public DateTimeFormatterBuilder padNext(int i, char c) {
        if (i < 1) {
            throw new IllegalArgumentException("The pad width must be at least one but was " + i);
        }
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        dateTimeFormatterBuilder.padNextWidth = i;
        dateTimeFormatterBuilder.padNextChar = c;
        dateTimeFormatterBuilder.valueParserIndex = -1;
        return this;
    }

    public DateTimeFormatterBuilder optionalStart() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        dateTimeFormatterBuilder.valueParserIndex = -1;
        this.active = new DateTimeFormatterBuilder(dateTimeFormatterBuilder, true);
        return this;
    }

    public DateTimeFormatterBuilder optionalEnd() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        if (dateTimeFormatterBuilder.parent == null) {
            throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
        }
        if (dateTimeFormatterBuilder.printerParsers.size() > 0) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
            CompositePrinterParser compositePrinterParser = new CompositePrinterParser(dateTimeFormatterBuilder2.printerParsers, dateTimeFormatterBuilder2.optional);
            this.active = this.active.parent;
            appendInternal(compositePrinterParser);
        } else {
            this.active = this.active.parent;
        }
        return this;
    }

    private int appendInternal(DateTimePrinterParser dateTimePrinterParser) {
        Jdk8Methods.requireNonNull(dateTimePrinterParser, "pp");
        if (this.active.padNextWidth > 0) {
            if (dateTimePrinterParser != null) {
                DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
                dateTimePrinterParser = new PadPrinterParserDecorator(dateTimePrinterParser, dateTimeFormatterBuilder.padNextWidth, dateTimeFormatterBuilder.padNextChar);
            }
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
            dateTimeFormatterBuilder2.padNextWidth = 0;
            dateTimeFormatterBuilder2.padNextChar = (char) 0;
        }
        this.active.printerParsers.add(dateTimePrinterParser);
        this.active.valueParserIndex = -1;
        return r4.printerParsers.size() - 1;
    }

    public DateTimeFormatter toFormatter() {
        return toFormatter(Locale.getDefault());
    }

    public DateTimeFormatter toFormatter(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        while (this.active.parent != null) {
            optionalEnd();
        }
        return new DateTimeFormatter(new CompositePrinterParser(this.printerParsers, false), locale, DecimalStyle.STANDARD, ResolverStyle.SMART, null, null, null);
    }

    DateTimeFormatter toFormatter(ResolverStyle resolverStyle) {
        return toFormatter().withResolverStyle(resolverStyle);
    }

    static final class CompositePrinterParser implements DateTimePrinterParser {
        private final boolean optional;
        private final DateTimePrinterParser[] printerParsers;

        CompositePrinterParser(List<DateTimePrinterParser> list, boolean z) {
            this((DateTimePrinterParser[]) list.toArray(new DateTimePrinterParser[list.size()]), z);
        }

        CompositePrinterParser(DateTimePrinterParser[] dateTimePrinterParserArr, boolean z) {
            this.printerParsers = dateTimePrinterParserArr;
            this.optional = z;
        }

        public CompositePrinterParser withOptional(boolean z) {
            return z == this.optional ? this : new CompositePrinterParser(this.printerParsers, z);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            int length = sb.length();
            if (this.optional) {
                dateTimePrintContext.startOptional();
            }
            try {
                for (DateTimePrinterParser dateTimePrinterParser : this.printerParsers) {
                    if (!dateTimePrinterParser.print(dateTimePrintContext, sb)) {
                        sb.setLength(length);
                        return true;
                    }
                }
                if (this.optional) {
                    dateTimePrintContext.endOptional();
                }
                return true;
            } finally {
                if (this.optional) {
                    dateTimePrintContext.endOptional();
                }
            }
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (this.optional) {
                dateTimeParseContext.startOptional();
                int i2 = i;
                for (DateTimePrinterParser dateTimePrinterParser : this.printerParsers) {
                    i2 = dateTimePrinterParser.parse(dateTimeParseContext, charSequence, i2);
                    if (i2 < 0) {
                        dateTimeParseContext.endOptional(false);
                        return i;
                    }
                }
                dateTimeParseContext.endOptional(true);
                return i2;
            }
            for (DateTimePrinterParser dateTimePrinterParser2 : this.printerParsers) {
                i = dateTimePrinterParser2.parse(dateTimeParseContext, charSequence, i);
                if (i < 0) {
                    break;
                }
            }
            return i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.printerParsers != null) {
                sb.append(this.optional ? "[" : "(");
                for (DateTimePrinterParser dateTimePrinterParser : this.printerParsers) {
                    sb.append(dateTimePrinterParser);
                }
                sb.append(this.optional ? "]" : ")");
            }
            return sb.toString();
        }
    }

    static final class PadPrinterParserDecorator implements DateTimePrinterParser {
        private final char padChar;
        private final int padWidth;
        private final DateTimePrinterParser printerParser;

        PadPrinterParserDecorator(DateTimePrinterParser dateTimePrinterParser, int i, char c) {
            this.printerParser = dateTimePrinterParser;
            this.padWidth = i;
            this.padChar = c;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            int length = sb.length();
            if (!this.printerParser.print(dateTimePrintContext, sb)) {
                return false;
            }
            int length2 = sb.length() - length;
            if (length2 > this.padWidth) {
                throw new DateTimeException("Cannot print as output of " + length2 + " characters exceeds pad width of " + this.padWidth);
            }
            for (int i = 0; i < this.padWidth - length2; i++) {
                sb.insert(length, this.padChar);
            }
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            boolean zIsStrict = dateTimeParseContext.isStrict();
            boolean zIsCaseSensitive = dateTimeParseContext.isCaseSensitive();
            if (i > charSequence.length()) {
                throw new IndexOutOfBoundsException();
            }
            if (i == charSequence.length()) {
                return ~i;
            }
            int length = this.padWidth + i;
            if (length > charSequence.length()) {
                if (zIsStrict) {
                    return ~i;
                }
                length = charSequence.length();
            }
            int i2 = i;
            while (i2 < length) {
                char cCharAt = charSequence.charAt(i2);
                char c = this.padChar;
                if (!zIsCaseSensitive) {
                    if (!dateTimeParseContext.charEquals(cCharAt, c)) {
                        break;
                    }
                    i2++;
                } else {
                    if (cCharAt != c) {
                        break;
                    }
                    i2++;
                }
            }
            int i3 = this.printerParser.parse(dateTimeParseContext, charSequence.subSequence(0, length), i2);
            return (i3 == length || !zIsStrict) ? i3 : ~(i + i2);
        }

        public String toString() {
            return "Pad(" + this.printerParser + ListUtils.DEFAULT_JOIN_SEPARATOR + this.padWidth + (this.padChar == ' ' ? ")" : ",'" + this.padChar + "')");
        }
    }

    enum SettingsParser implements DateTimePrinterParser {
        SENSITIVE,
        INSENSITIVE,
        STRICT,
        LENIENT;

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int iOrdinal = ordinal();
            if (iOrdinal == 0) {
                dateTimeParseContext.setCaseSensitive(true);
            } else if (iOrdinal == 1) {
                dateTimeParseContext.setCaseSensitive(false);
            } else if (iOrdinal == 2) {
                dateTimeParseContext.setStrict(true);
            } else if (iOrdinal == 3) {
                dateTimeParseContext.setStrict(false);
            }
            return i;
        }

        @Override // java.lang.Enum
        public String toString() {
            int iOrdinal = ordinal();
            if (iOrdinal == 0) {
                return "ParseCaseSensitive(true)";
            }
            if (iOrdinal == 1) {
                return "ParseCaseSensitive(false)";
            }
            if (iOrdinal == 2) {
                return "ParseStrict(true)";
            }
            if (iOrdinal == 3) {
                return "ParseStrict(false)";
            }
            throw new IllegalStateException("Unreachable");
        }
    }

    static class DefaultingParser implements DateTimePrinterParser {
        private final TemporalField field;
        private final long value;

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return true;
        }

        DefaultingParser(TemporalField temporalField, long j) {
            this.field = temporalField;
            this.value = j;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (dateTimeParseContext.getParsed(this.field) == null) {
                dateTimeParseContext.setParsedField(this.field, this.value, i, i);
            }
            return i;
        }
    }

    static final class CharLiteralPrinterParser implements DateTimePrinterParser {
        private final char literal;

        CharLiteralPrinterParser(char c) {
            this.literal = c;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            sb.append(this.literal);
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (i == charSequence.length()) {
                return ~i;
            }
            return !dateTimeParseContext.charEquals(this.literal, charSequence.charAt(i)) ? ~i : i + 1;
        }

        public String toString() {
            return this.literal == '\'' ? "''" : "'" + this.literal + "'";
        }
    }

    static final class StringLiteralPrinterParser implements DateTimePrinterParser {
        private final String literal;

        StringLiteralPrinterParser(String str) {
            this.literal = str;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            sb.append(this.literal);
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (i > charSequence.length() || i < 0) {
                throw new IndexOutOfBoundsException();
            }
            String str = this.literal;
            return !dateTimeParseContext.subSequenceEquals(charSequence, i, str, 0, str.length()) ? ~i : i + this.literal.length();
        }

        public String toString() {
            return "'" + this.literal.replace("'", "''") + "'";
        }
    }

    static class NumberPrinterParser implements DateTimePrinterParser {
        static final int[] EXCEED_POINTS = {0, 10, 100, 1000, 10000, 100000, DurationKt.NANOS_IN_MILLIS, 10000000, 100000000, Http2Connection.DEGRADED_PONG_TIMEOUT_NS};
        final TemporalField field;
        final int maxWidth;
        final int minWidth;
        final SignStyle signStyle;
        final int subsequentWidth;

        long getValue(DateTimePrintContext dateTimePrintContext, long j) {
            return j;
        }

        NumberPrinterParser(TemporalField temporalField, int i, int i2, SignStyle signStyle) {
            this.field = temporalField;
            this.minWidth = i;
            this.maxWidth = i2;
            this.signStyle = signStyle;
            this.subsequentWidth = 0;
        }

        private NumberPrinterParser(TemporalField temporalField, int i, int i2, SignStyle signStyle, int i3) {
            this.field = temporalField;
            this.minWidth = i;
            this.maxWidth = i2;
            this.signStyle = signStyle;
            this.subsequentWidth = i3;
        }

        NumberPrinterParser withFixedWidth() {
            return this.subsequentWidth == -1 ? this : new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, -1);
        }

        NumberPrinterParser withSubsequentWidth(int i) {
            return new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, this.subsequentWidth + i);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            long value2 = getValue(dateTimePrintContext, value.longValue());
            DecimalStyle symbols = dateTimePrintContext.getSymbols();
            String string = value2 == Long.MIN_VALUE ? "9223372036854775808" : Long.toString(Math.abs(value2));
            if (string.length() > this.maxWidth) {
                throw new DateTimeException("Field " + this.field + " cannot be printed as the value " + value2 + " exceeds the maximum print width of " + this.maxWidth);
            }
            String strConvertNumberToI18N = symbols.convertNumberToI18N(string);
            if (value2 >= 0) {
                int i = AnonymousClass4.$SwitchMap$org$threeten$bp$format$SignStyle[this.signStyle.ordinal()];
                if (i == 1) {
                    if (this.minWidth < 19 && value2 >= EXCEED_POINTS[r4]) {
                        sb.append(symbols.getPositiveSign());
                    }
                } else if (i == 2) {
                    sb.append(symbols.getPositiveSign());
                }
            } else {
                int i2 = AnonymousClass4.$SwitchMap$org$threeten$bp$format$SignStyle[this.signStyle.ordinal()];
                if (i2 == 1 || i2 == 2 || i2 == 3) {
                    sb.append(symbols.getNegativeSign());
                } else if (i2 == 4) {
                    throw new DateTimeException("Field " + this.field + " cannot be printed as the value " + value2 + " cannot be negative according to the SignStyle");
                }
            }
            for (int i3 = 0; i3 < this.minWidth - strConvertNumberToI18N.length(); i3++) {
                sb.append(symbols.getZeroDigit());
            }
            sb.append(strConvertNumberToI18N);
            return true;
        }

        boolean isFixedWidth(DateTimeParseContext dateTimeParseContext) {
            int i = this.subsequentWidth;
            return i == -1 || (i > 0 && this.minWidth == this.maxWidth && this.signStyle == SignStyle.NOT_NEGATIVE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:101:0x014f, code lost:
        
            if (r0 > r19.minWidth) goto L108;
         */
        /* JADX WARN: Code restructure failed: missing block: B:103:0x0154, code lost:
        
            return ~(r7 - 1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:105:0x0157, code lost:
        
            if (r0 <= r19.minWidth) goto L108;
         */
        /* JADX WARN: Code restructure failed: missing block: B:107:0x015a, code lost:
        
            return ~r7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:108:0x015b, code lost:
        
            r2 = r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:109:0x015c, code lost:
        
            if (r13 == null) goto L115;
         */
        /* JADX WARN: Code restructure failed: missing block: B:111:0x0164, code lost:
        
            if (r13.bitLength() <= 63) goto L113;
         */
        /* JADX WARN: Code restructure failed: missing block: B:112:0x0166, code lost:
        
            r13 = r13.divide(java.math.BigInteger.TEN);
            r5 = r5 - 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:114:0x017b, code lost:
        
            return setValue(r20, r13.longValue(), r7, r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:116:0x0185, code lost:
        
            return setValue(r20, r2, r7, r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x010a, code lost:
        
            r5 = r10;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x010f, code lost:
        
            if (r0 == false) goto L94;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x0111, code lost:
        
            if (r13 == null) goto L87;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0119, code lost:
        
            if (r13.equals(java.math.BigInteger.ZERO) == false) goto L86;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x011f, code lost:
        
            if (r20.isStrict() == false) goto L86;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x0124, code lost:
        
            return ~(r7 - 1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x0125, code lost:
        
            r13 = r13.negate();
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x012f, code lost:
        
            if (r14 != 0) goto L93;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x0135, code lost:
        
            if (r20.isStrict() == false) goto L93;
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x0139, code lost:
        
            return ~(r7 - 1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x013a, code lost:
        
            r2 = -r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x0141, code lost:
        
            if (r19.signStyle != org.threeten.bp.format.SignStyle.EXCEEDS_PAD) goto L108;
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:0x0147, code lost:
        
            if (r20.isStrict() == false) goto L108;
         */
        /* JADX WARN: Code restructure failed: missing block: B:98:0x0149, code lost:
        
            r0 = r5 - r7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:99:0x014b, code lost:
        
            if (r2 == false) goto L104;
         */
        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int parse(org.threeten.bp.format.DateTimeParseContext r20, java.lang.CharSequence r21, int r22) {
            /*
                Method dump skipped, instruction units count: 390
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser.parse(org.threeten.bp.format.DateTimeParseContext, java.lang.CharSequence, int):int");
        }

        int setValue(DateTimeParseContext dateTimeParseContext, long j, int i, int i2) {
            return dateTimeParseContext.setParsedField(this.field, j, i, i2);
        }

        public String toString() {
            if (this.minWidth == 1 && this.maxWidth == 19 && this.signStyle == SignStyle.NORMAL) {
                return "Value(" + this.field + ")";
            }
            if (this.minWidth == this.maxWidth && this.signStyle == SignStyle.NOT_NEGATIVE) {
                return "Value(" + this.field + ListUtils.DEFAULT_JOIN_SEPARATOR + this.minWidth + ")";
            }
            return "Value(" + this.field + ListUtils.DEFAULT_JOIN_SEPARATOR + this.minWidth + ListUtils.DEFAULT_JOIN_SEPARATOR + this.maxWidth + ListUtils.DEFAULT_JOIN_SEPARATOR + this.signStyle + ")";
        }
    }

    /* JADX INFO: renamed from: org.threeten.bp.format.DateTimeFormatterBuilder$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$format$SignStyle;

        static {
            int[] iArr = new int[SignStyle.values().length];
            $SwitchMap$org$threeten$bp$format$SignStyle = iArr;
            try {
                iArr[SignStyle.EXCEEDS_PAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$format$SignStyle[SignStyle.ALWAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$threeten$bp$format$SignStyle[SignStyle.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$threeten$bp$format$SignStyle[SignStyle.NOT_NEGATIVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static final class ReducedPrinterParser extends NumberPrinterParser {
        static final LocalDate BASE_DATE = LocalDate.of(2000, 1, 1);
        private final ChronoLocalDate baseDate;
        private final int baseValue;

        ReducedPrinterParser(TemporalField temporalField, int i, int i2, int i3, ChronoLocalDate chronoLocalDate) {
            super(temporalField, i, i2, SignStyle.NOT_NEGATIVE);
            if (i < 1 || i > 10) {
                throw new IllegalArgumentException("The width must be from 1 to 10 inclusive but was " + i);
            }
            if (i2 < 1 || i2 > 10) {
                throw new IllegalArgumentException("The maxWidth must be from 1 to 10 inclusive but was " + i2);
            }
            if (i2 < i) {
                throw new IllegalArgumentException("The maxWidth must be greater than the width");
            }
            if (chronoLocalDate == null) {
                long j = i3;
                if (!temporalField.range().isValidValue(j)) {
                    throw new IllegalArgumentException("The base value must be within the range of the field");
                }
                if (j + ((long) EXCEED_POINTS[i]) > 2147483647L) {
                    throw new DateTimeException("Unable to add printer-parser as the range exceeds the capacity of an int");
                }
            }
            this.baseValue = i3;
            this.baseDate = chronoLocalDate;
        }

        private ReducedPrinterParser(TemporalField temporalField, int i, int i2, int i3, ChronoLocalDate chronoLocalDate, int i4) {
            super(temporalField, i, i2, SignStyle.NOT_NEGATIVE, i4);
            this.baseValue = i3;
            this.baseDate = chronoLocalDate;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        long getValue(DateTimePrintContext dateTimePrintContext, long j) {
            long jAbs = Math.abs(j);
            int i = this.baseValue;
            if (this.baseDate != null) {
                i = Chronology.from(dateTimePrintContext.getTemporal()).date(this.baseDate).get(this.field);
            }
            if (j >= i && j < i + EXCEED_POINTS[this.minWidth]) {
                return jAbs % ((long) EXCEED_POINTS[this.minWidth]);
            }
            return jAbs % ((long) EXCEED_POINTS[this.maxWidth]);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        int setValue(DateTimeParseContext dateTimeParseContext, long j, int i, int i2) {
            int i3 = this.baseValue;
            if (this.baseDate != null) {
                i3 = dateTimeParseContext.getEffectiveChronology().date(this.baseDate).get(this.field);
                dateTimeParseContext.addChronologyChangedParser(this, j, i, i2);
            }
            if (i2 - i == this.minWidth && j >= 0) {
                long j2 = EXCEED_POINTS[this.minWidth];
                long j3 = i3;
                long j4 = j3 - (j3 % j2);
                j = i3 > 0 ? j4 + j : j4 - j;
                if (j < j3) {
                    j += j2;
                }
            }
            return dateTimeParseContext.setParsedField(this.field, j, i, i2);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        NumberPrinterParser withFixedWidth() {
            return this.subsequentWidth == -1 ? this : new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, -1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        public ReducedPrinterParser withSubsequentWidth(int i) {
            return new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, this.subsequentWidth + i);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        boolean isFixedWidth(DateTimeParseContext dateTimeParseContext) {
            if (dateTimeParseContext.isStrict()) {
                return super.isFixedWidth(dateTimeParseContext);
            }
            return false;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser
        public String toString() {
            StringBuilder sbAppend = new StringBuilder("ReducedValue(").append(this.field).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append(this.minWidth).append(ListUtils.DEFAULT_JOIN_SEPARATOR).append(this.maxWidth).append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            Object objValueOf = this.baseDate;
            if (objValueOf == null) {
                objValueOf = Integer.valueOf(this.baseValue);
            }
            return sbAppend.append(objValueOf).append(")").toString();
        }
    }

    static final class FractionPrinterParser implements DateTimePrinterParser {
        private final boolean decimalPoint;
        private final TemporalField field;
        private final int maxWidth;
        private final int minWidth;

        FractionPrinterParser(TemporalField temporalField, int i, int i2, boolean z) {
            Jdk8Methods.requireNonNull(temporalField, "field");
            if (!temporalField.range().isFixed()) {
                throw new IllegalArgumentException("Field must have a fixed set of values: " + temporalField);
            }
            if (i < 0 || i > 9) {
                throw new IllegalArgumentException("Minimum width must be from 0 to 9 inclusive but was " + i);
            }
            if (i2 < 1 || i2 > 9) {
                throw new IllegalArgumentException("Maximum width must be from 1 to 9 inclusive but was " + i2);
            }
            if (i2 < i) {
                throw new IllegalArgumentException("Maximum width must exceed or equal the minimum width but " + i2 + " < " + i);
            }
            this.field = temporalField;
            this.minWidth = i;
            this.maxWidth = i2;
            this.decimalPoint = z;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            DecimalStyle symbols = dateTimePrintContext.getSymbols();
            BigDecimal bigDecimalConvertToFraction = convertToFraction(value.longValue());
            if (bigDecimalConvertToFraction.scale() == 0) {
                if (this.minWidth <= 0) {
                    return true;
                }
                if (this.decimalPoint) {
                    sb.append(symbols.getDecimalSeparator());
                }
                for (int i = 0; i < this.minWidth; i++) {
                    sb.append(symbols.getZeroDigit());
                }
                return true;
            }
            String strConvertNumberToI18N = symbols.convertNumberToI18N(bigDecimalConvertToFraction.setScale(Math.min(Math.max(bigDecimalConvertToFraction.scale(), this.minWidth), this.maxWidth), RoundingMode.FLOOR).toPlainString().substring(2));
            if (this.decimalPoint) {
                sb.append(symbols.getDecimalSeparator());
            }
            sb.append(strConvertNumberToI18N);
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            int i3 = 0;
            int i4 = dateTimeParseContext.isStrict() ? this.minWidth : 0;
            int i5 = dateTimeParseContext.isStrict() ? this.maxWidth : 9;
            int length = charSequence.length();
            if (i == length) {
                return i4 > 0 ? ~i : i;
            }
            if (this.decimalPoint) {
                if (charSequence.charAt(i) != dateTimeParseContext.getSymbols().getDecimalSeparator()) {
                    return i4 > 0 ? ~i : i;
                }
                i++;
            }
            int i6 = i;
            int i7 = i4 + i6;
            if (i7 > length) {
                return ~i6;
            }
            int iMin = Math.min(i5 + i6, length);
            int i8 = i6;
            while (true) {
                if (i8 >= iMin) {
                    i2 = i8;
                    break;
                }
                int i9 = i8 + 1;
                int iConvertToDigit = dateTimeParseContext.getSymbols().convertToDigit(charSequence.charAt(i8));
                if (iConvertToDigit >= 0) {
                    i3 = (i3 * 10) + iConvertToDigit;
                    i8 = i9;
                } else {
                    if (i9 < i7) {
                        return ~i6;
                    }
                    i2 = i9 - 1;
                }
            }
            return dateTimeParseContext.setParsedField(this.field, convertFromFraction(new BigDecimal(i3).movePointLeft(i2 - i6)), i6, i2);
        }

        private BigDecimal convertToFraction(long j) {
            ValueRange valueRangeRange = this.field.range();
            valueRangeRange.checkValidValue(j, this.field);
            BigDecimal bigDecimalValueOf = BigDecimal.valueOf(valueRangeRange.getMinimum());
            BigDecimal bigDecimalDivide = BigDecimal.valueOf(j).subtract(bigDecimalValueOf).divide(BigDecimal.valueOf(valueRangeRange.getMaximum()).subtract(bigDecimalValueOf).add(BigDecimal.ONE), 9, RoundingMode.FLOOR);
            return bigDecimalDivide.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : bigDecimalDivide.stripTrailingZeros();
        }

        private long convertFromFraction(BigDecimal bigDecimal) {
            ValueRange valueRangeRange = this.field.range();
            BigDecimal bigDecimalValueOf = BigDecimal.valueOf(valueRangeRange.getMinimum());
            return bigDecimal.multiply(BigDecimal.valueOf(valueRangeRange.getMaximum()).subtract(bigDecimalValueOf).add(BigDecimal.ONE)).setScale(0, RoundingMode.FLOOR).add(bigDecimalValueOf).longValueExact();
        }

        public String toString() {
            return "Fraction(" + this.field + ListUtils.DEFAULT_JOIN_SEPARATOR + this.minWidth + ListUtils.DEFAULT_JOIN_SEPARATOR + this.maxWidth + (this.decimalPoint ? ",DecimalPoint" : "") + ")";
        }
    }

    static final class TextPrinterParser implements DateTimePrinterParser {
        private final TemporalField field;
        private volatile NumberPrinterParser numberPrinterParser;
        private final DateTimeTextProvider provider;
        private final TextStyle textStyle;

        TextPrinterParser(TemporalField temporalField, TextStyle textStyle, DateTimeTextProvider dateTimeTextProvider) {
            this.field = temporalField;
            this.textStyle = textStyle;
            this.provider = dateTimeTextProvider;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            String text = this.provider.getText(this.field, value.longValue(), this.textStyle, dateTimePrintContext.getLocale());
            if (text == null) {
                return numberPrinterParser().print(dateTimePrintContext, sb);
            }
            sb.append(text);
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int length = charSequence.length();
            if (i < 0 || i > length) {
                throw new IndexOutOfBoundsException();
            }
            Iterator<Map.Entry<String, Long>> textIterator = this.provider.getTextIterator(this.field, dateTimeParseContext.isStrict() ? this.textStyle : null, dateTimeParseContext.getLocale());
            if (textIterator != null) {
                while (textIterator.hasNext()) {
                    Map.Entry<String, Long> next = textIterator.next();
                    String key = next.getKey();
                    if (dateTimeParseContext.subSequenceEquals(key, 0, charSequence, i, key.length())) {
                        return dateTimeParseContext.setParsedField(this.field, next.getValue().longValue(), i, i + key.length());
                    }
                }
                if (dateTimeParseContext.isStrict()) {
                    return ~i;
                }
            }
            return numberPrinterParser().parse(dateTimeParseContext, charSequence, i);
        }

        private NumberPrinterParser numberPrinterParser() {
            if (this.numberPrinterParser == null) {
                this.numberPrinterParser = new NumberPrinterParser(this.field, 1, 19, SignStyle.NORMAL);
            }
            return this.numberPrinterParser;
        }

        public String toString() {
            if (this.textStyle == TextStyle.FULL) {
                return "Text(" + this.field + ")";
            }
            return "Text(" + this.field + ListUtils.DEFAULT_JOIN_SEPARATOR + this.textStyle + ")";
        }
    }

    static final class InstantPrinterParser implements DateTimePrinterParser {
        private static final long SECONDS_0000_TO_1970 = 62167219200L;
        private static final long SECONDS_PER_10000_YEARS = 315569520000L;
        private final int fractionalDigits;

        public String toString() {
            return "Instant()";
        }

        InstantPrinterParser(int i) {
            this.fractionalDigits = i;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(ChronoField.INSTANT_SECONDS);
            Long lValueOf = 0L;
            if (dateTimePrintContext.getTemporal().isSupported(ChronoField.NANO_OF_SECOND)) {
                lValueOf = Long.valueOf(dateTimePrintContext.getTemporal().getLong(ChronoField.NANO_OF_SECOND));
            }
            int i = 0;
            if (value == null) {
                return false;
            }
            long jLongValue = value.longValue();
            int iCheckValidIntValue = ChronoField.NANO_OF_SECOND.checkValidIntValue(lValueOf.longValue());
            if (jLongValue >= -62167219200L) {
                long j = (jLongValue - SECONDS_PER_10000_YEARS) + SECONDS_0000_TO_1970;
                long jFloorDiv = Jdk8Methods.floorDiv(j, SECONDS_PER_10000_YEARS) + 1;
                LocalDateTime localDateTimeOfEpochSecond = LocalDateTime.ofEpochSecond(Jdk8Methods.floorMod(j, SECONDS_PER_10000_YEARS) - SECONDS_0000_TO_1970, 0, ZoneOffset.UTC);
                if (jFloorDiv > 0) {
                    sb.append('+').append(jFloorDiv);
                }
                sb.append(localDateTimeOfEpochSecond);
                if (localDateTimeOfEpochSecond.getSecond() == 0) {
                    sb.append(":00");
                }
            } else {
                long j2 = jLongValue + SECONDS_0000_TO_1970;
                long j3 = j2 / SECONDS_PER_10000_YEARS;
                long j4 = j2 % SECONDS_PER_10000_YEARS;
                LocalDateTime localDateTimeOfEpochSecond2 = LocalDateTime.ofEpochSecond(j4 - SECONDS_0000_TO_1970, 0, ZoneOffset.UTC);
                int length = sb.length();
                sb.append(localDateTimeOfEpochSecond2);
                if (localDateTimeOfEpochSecond2.getSecond() == 0) {
                    sb.append(":00");
                }
                if (j3 < 0) {
                    if (localDateTimeOfEpochSecond2.getYear() == -10000) {
                        sb.replace(length, length + 2, Long.toString(j3 - 1));
                    } else if (j4 == 0) {
                        sb.insert(length, j3);
                    } else {
                        sb.insert(length + 1, Math.abs(j3));
                    }
                }
            }
            int i2 = this.fractionalDigits;
            if (i2 == -2) {
                if (iCheckValidIntValue != 0) {
                    sb.append('.');
                    if (iCheckValidIntValue % DurationKt.NANOS_IN_MILLIS == 0) {
                        sb.append(Integer.toString((iCheckValidIntValue / DurationKt.NANOS_IN_MILLIS) + 1000).substring(1));
                    } else if (iCheckValidIntValue % 1000 == 0) {
                        sb.append(Integer.toString((iCheckValidIntValue / 1000) + DurationKt.NANOS_IN_MILLIS).substring(1));
                    } else {
                        sb.append(Integer.toString(iCheckValidIntValue + Http2Connection.DEGRADED_PONG_TIMEOUT_NS).substring(1));
                    }
                }
            } else if (i2 > 0 || (i2 == -1 && iCheckValidIntValue > 0)) {
                sb.append('.');
                int i3 = 100000000;
                while (true) {
                    int i4 = this.fractionalDigits;
                    if ((i4 != -1 || iCheckValidIntValue <= 0) && i >= i4) {
                        break;
                    }
                    int i5 = iCheckValidIntValue / i3;
                    sb.append((char) (i5 + 48));
                    iCheckValidIntValue -= i5 * i3;
                    i3 /= 10;
                    i++;
                }
            }
            sb.append(Matrix.MATRIX_TYPE_ZERO);
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            DateTimeParseContext dateTimeParseContextCopy = dateTimeParseContext.copy();
            int i3 = this.fractionalDigits;
            int i4 = 0;
            int i5 = i3 < 0 ? 0 : i3;
            if (i3 < 0) {
                i3 = 9;
            }
            int i6 = new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral('T').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2).appendLiteral(':').appendValue(ChronoField.SECOND_OF_MINUTE, 2).appendFraction(ChronoField.NANO_OF_SECOND, i5, i3, true).appendLiteral(Matrix.MATRIX_TYPE_ZERO).toFormatter().toPrinterParser(false).parse(dateTimeParseContextCopy, charSequence, i);
            if (i6 < 0) {
                return i6;
            }
            long jLongValue = dateTimeParseContextCopy.getParsed(ChronoField.YEAR).longValue();
            int iIntValue = dateTimeParseContextCopy.getParsed(ChronoField.MONTH_OF_YEAR).intValue();
            int iIntValue2 = dateTimeParseContextCopy.getParsed(ChronoField.DAY_OF_MONTH).intValue();
            int iIntValue3 = dateTimeParseContextCopy.getParsed(ChronoField.HOUR_OF_DAY).intValue();
            int iIntValue4 = dateTimeParseContextCopy.getParsed(ChronoField.MINUTE_OF_HOUR).intValue();
            Long parsed = dateTimeParseContextCopy.getParsed(ChronoField.SECOND_OF_MINUTE);
            Long parsed2 = dateTimeParseContextCopy.getParsed(ChronoField.NANO_OF_SECOND);
            int iIntValue5 = parsed != null ? parsed.intValue() : 0;
            int iIntValue6 = parsed2 != null ? parsed2.intValue() : 0;
            int i7 = ((int) jLongValue) % 10000;
            try {
                if (iIntValue3 != 24 || iIntValue4 != 0 || iIntValue5 != 0 || iIntValue6 != 0) {
                    if (iIntValue3 == 23 && iIntValue4 == 59 && iIntValue5 == 60) {
                        dateTimeParseContext.setParsedLeapSecond();
                        i2 = 59;
                    }
                    return dateTimeParseContext.setParsedField(ChronoField.NANO_OF_SECOND, iIntValue6, i, dateTimeParseContext.setParsedField(ChronoField.INSTANT_SECONDS, LocalDateTime.of(i7, iIntValue, iIntValue2, iIntValue3, iIntValue4, i2, 0).plusDays(i4).toEpochSecond(ZoneOffset.UTC) + Jdk8Methods.safeMultiply(jLongValue / 10000, SECONDS_PER_10000_YEARS), i, i6));
                }
                iIntValue3 = 0;
                i4 = 1;
                return dateTimeParseContext.setParsedField(ChronoField.NANO_OF_SECOND, iIntValue6, i, dateTimeParseContext.setParsedField(ChronoField.INSTANT_SECONDS, LocalDateTime.of(i7, iIntValue, iIntValue2, iIntValue3, iIntValue4, i2, 0).plusDays(i4).toEpochSecond(ZoneOffset.UTC) + Jdk8Methods.safeMultiply(jLongValue / 10000, SECONDS_PER_10000_YEARS), i, i6));
            } catch (RuntimeException unused) {
                return ~i;
            }
            i2 = iIntValue5;
        }
    }

    static final class OffsetIdPrinterParser implements DateTimePrinterParser {
        private final String noOffsetText;
        private final int type;
        static final String[] PATTERNS = {"+HH", "+HHmm", "+HH:mm", "+HHMM", "+HH:MM", "+HHMMss", "+HH:MM:ss", "+HHMMSS", "+HH:MM:SS"};
        static final OffsetIdPrinterParser INSTANCE_ID = new OffsetIdPrinterParser("Z", "+HH:MM:ss");

        OffsetIdPrinterParser(String str, String str2) {
            Jdk8Methods.requireNonNull(str, "noOffsetText");
            Jdk8Methods.requireNonNull(str2, BYDAutoMultimediaDevice.PARAM_PATTERN);
            this.noOffsetText = str;
            this.type = checkPattern(str2);
        }

        private int checkPattern(String str) {
            int i = 0;
            while (true) {
                String[] strArr = PATTERNS;
                if (i < strArr.length) {
                    if (strArr[i].equals(str)) {
                        return i;
                    }
                    i++;
                } else {
                    throw new IllegalArgumentException("Invalid zone offset pattern: " + str);
                }
            }
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(ChronoField.OFFSET_SECONDS);
            if (value == null) {
                return false;
            }
            int iSafeToInt = Jdk8Methods.safeToInt(value.longValue());
            if (iSafeToInt == 0) {
                sb.append(this.noOffsetText);
            } else {
                int iAbs = Math.abs((iSafeToInt / 3600) % 100);
                int iAbs2 = Math.abs((iSafeToInt / 60) % 60);
                int iAbs3 = Math.abs(iSafeToInt % 60);
                int length = sb.length();
                sb.append(iSafeToInt < 0 ? "-" : Marker.ANY_NON_NULL_MARKER).append((char) ((iAbs / 10) + 48)).append((char) ((iAbs % 10) + 48));
                int i = this.type;
                if (i >= 3 || (i >= 1 && iAbs2 > 0)) {
                    sb.append(i % 2 == 0 ? ":" : "").append((char) ((iAbs2 / 10) + 48)).append((char) ((iAbs2 % 10) + 48));
                    iAbs += iAbs2;
                    int i2 = this.type;
                    if (i2 >= 7 || (i2 >= 5 && iAbs3 > 0)) {
                        sb.append(i2 % 2 != 0 ? "" : ":").append((char) ((iAbs3 / 10) + 48)).append((char) ((iAbs3 % 10) + 48));
                        iAbs += iAbs3;
                    }
                }
                if (iAbs == 0) {
                    sb.setLength(length);
                    sb.append(this.noOffsetText);
                }
            }
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x007d  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0080  */
        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int parse(org.threeten.bp.format.DateTimeParseContext r16, java.lang.CharSequence r17, int r18) {
            /*
                r15 = this;
                r0 = r15
                r7 = r17
                r8 = r18
                int r1 = r17.length()
                java.lang.String r2 = r0.noOffsetText
                int r9 = r2.length()
                if (r9 != 0) goto L22
                if (r8 != r1) goto L45
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                r1 = r16
                r5 = r18
                r6 = r18
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            L22:
                if (r8 != r1) goto L26
                int r1 = ~r8
                return r1
            L26:
                java.lang.String r4 = r0.noOffsetText
                r5 = 0
                r1 = r16
                r2 = r17
                r3 = r18
                r6 = r9
                boolean r1 = r1.subSequenceEquals(r2, r3, r4, r5, r6)
                if (r1 == 0) goto L45
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                int r6 = r8 + r9
                r1 = r16
                r5 = r18
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            L45:
                char r1 = r17.charAt(r18)
                r2 = 43
                r3 = 45
                if (r1 == r2) goto L51
                if (r1 != r3) goto La3
            L51:
                r2 = 1
                if (r1 != r3) goto L56
                r1 = -1
                goto L57
            L56:
                r1 = r2
            L57:
                r3 = 4
                int[] r3 = new int[r3]
                int r4 = r8 + 1
                r5 = 0
                r3[r5] = r4
                boolean r4 = r15.parseNumber(r3, r2, r7, r2)
                r6 = 2
                r10 = 3
                if (r4 != 0) goto L7d
                int r4 = r0.type
                if (r4 < r10) goto L6d
                r4 = r2
                goto L6e
            L6d:
                r4 = r5
            L6e:
                boolean r4 = r15.parseNumber(r3, r6, r7, r4)
                if (r4 != 0) goto L7d
                boolean r4 = r15.parseNumber(r3, r10, r7, r5)
                if (r4 == 0) goto L7b
                goto L7d
            L7b:
                r4 = r5
                goto L7e
            L7d:
                r4 = r2
            L7e:
                if (r4 != 0) goto La3
                long r11 = (long) r1
                r1 = r3[r2]
                long r1 = (long) r1
                r13 = 3600(0xe10, double:1.7786E-320)
                long r1 = r1 * r13
                r4 = r3[r6]
                long r6 = (long) r4
                r13 = 60
                long r6 = r6 * r13
                long r1 = r1 + r6
                r4 = r3[r10]
                long r6 = (long) r4
                long r1 = r1 + r6
                long r6 = r11 * r1
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r9 = r3[r5]
                r1 = r16
                r3 = r6
                r5 = r18
                r6 = r9
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            La3:
                if (r9 != 0) goto Lb4
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                int r6 = r8 + r9
                r1 = r16
                r5 = r18
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            Lb4:
                int r1 = ~r8
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatterBuilder.OffsetIdPrinterParser.parse(org.threeten.bp.format.DateTimeParseContext, java.lang.CharSequence, int):int");
        }

        private boolean parseNumber(int[] iArr, int i, CharSequence charSequence, boolean z) {
            int i2;
            int i3 = this.type;
            if ((i3 + 3) / 2 < i) {
                return false;
            }
            int i4 = iArr[0];
            if (i3 % 2 == 0 && i > 1) {
                int i5 = i4 + 1;
                if (i5 > charSequence.length() || charSequence.charAt(i4) != ':') {
                    return z;
                }
                i4 = i5;
            }
            if (i4 + 2 > charSequence.length()) {
                return z;
            }
            int i6 = i4 + 1;
            char cCharAt = charSequence.charAt(i4);
            int i7 = i6 + 1;
            char cCharAt2 = charSequence.charAt(i6);
            if (cCharAt < '0' || cCharAt > '9' || cCharAt2 < '0' || cCharAt2 > '9' || (i2 = ((cCharAt - '0') * 10) + (cCharAt2 - '0')) < 0 || i2 > 59) {
                return z;
            }
            iArr[i] = i2;
            iArr[0] = i7;
            return false;
        }

        public String toString() {
            return "Offset(" + PATTERNS[this.type] + ",'" + this.noOffsetText.replace("'", "''") + "')";
        }
    }

    static final class LocalizedOffsetPrinterParser implements DateTimePrinterParser {
        private final TextStyle style;

        public LocalizedOffsetPrinterParser(TextStyle textStyle) {
            this.style = textStyle;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(ChronoField.OFFSET_SECONDS);
            if (value == null) {
                return false;
            }
            sb.append("GMT");
            if (this.style == TextStyle.FULL) {
                return new OffsetIdPrinterParser("", "+HH:MM:ss").print(dateTimePrintContext, sb);
            }
            int iSafeToInt = Jdk8Methods.safeToInt(value.longValue());
            if (iSafeToInt == 0) {
                return true;
            }
            int iAbs = Math.abs((iSafeToInt / 3600) % 100);
            int iAbs2 = Math.abs((iSafeToInt / 60) % 60);
            int iAbs3 = Math.abs(iSafeToInt % 60);
            sb.append(iSafeToInt < 0 ? "-" : Marker.ANY_NON_NULL_MARKER).append(iAbs);
            if (iAbs2 <= 0 && iAbs3 <= 0) {
                return true;
            }
            sb.append(":").append((char) ((iAbs2 / 10) + 48)).append((char) ((iAbs2 % 10) + 48));
            if (iAbs3 <= 0) {
                return true;
            }
            sb.append(":").append((char) ((iAbs3 / 10) + 48)).append((char) ((iAbs3 % 10) + 48));
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            char cCharAt;
            if (!dateTimeParseContext.subSequenceEquals(charSequence, i, "GMT", 0, 3)) {
                return ~i;
            }
            int i2 = i + 3;
            if (this.style == TextStyle.FULL) {
                return new OffsetIdPrinterParser("", "+HH:MM:ss").parse(dateTimeParseContext, charSequence, i2);
            }
            int length = charSequence.length();
            if (i2 == length) {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, 0L, i2, i2);
            }
            char cCharAt2 = charSequence.charAt(i2);
            if (cCharAt2 != '+' && cCharAt2 != '-') {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, 0L, i2, i2);
            }
            int i3 = cCharAt2 == '-' ? -1 : 1;
            if (i2 == length) {
                return ~i2;
            }
            int i4 = i2 + 1;
            char cCharAt3 = charSequence.charAt(i4);
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                return ~i4;
            }
            int i5 = i4 + 1;
            int i6 = cCharAt3 - '0';
            if (i5 != length && (cCharAt = charSequence.charAt(i5)) >= '0' && cCharAt <= '9') {
                i6 = (i6 * 10) + (cCharAt - '0');
                if (i6 > 23) {
                    return ~i5;
                }
                i5++;
            }
            int i7 = i5;
            if (i7 == length || charSequence.charAt(i7) != ':') {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, i3 * 3600 * i6, i7, i7);
            }
            int i8 = i7 + 1;
            int i9 = length - 2;
            if (i8 > i9) {
                return ~i8;
            }
            char cCharAt4 = charSequence.charAt(i8);
            if (cCharAt4 < '0' || cCharAt4 > '9') {
                return ~i8;
            }
            int i10 = i8 + 1;
            int i11 = cCharAt4 - '0';
            char cCharAt5 = charSequence.charAt(i10);
            if (cCharAt5 < '0' || cCharAt5 > '9') {
                return ~i10;
            }
            int i12 = i10 + 1;
            if ((i11 * 10) + (cCharAt5 - '0') > 59) {
                return ~i12;
            }
            if (i12 == length || charSequence.charAt(i12) != ':') {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, i3 * ((i6 * 3600) + (r11 * 60)), i12, i12);
            }
            int i13 = i12 + 1;
            if (i13 > i9) {
                return ~i13;
            }
            char cCharAt6 = charSequence.charAt(i13);
            if (cCharAt6 < '0' || cCharAt6 > '9') {
                return ~i13;
            }
            int i14 = i13 + 1;
            int i15 = cCharAt6 - '0';
            char cCharAt7 = charSequence.charAt(i14);
            if (cCharAt7 < '0' || cCharAt7 > '9') {
                return ~i14;
            }
            int i16 = i14 + 1;
            return (i15 * 10) + (cCharAt7 - '0') > 59 ? ~i16 : dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, i3 * ((i6 * 3600) + (r11 * 60) + r0), i16, i16);
        }
    }

    static final class ZoneTextPrinterParser implements DateTimePrinterParser {
        private static final Comparator<String> LENGTH_COMPARATOR = new Comparator<String>() { // from class: org.threeten.bp.format.DateTimeFormatterBuilder.ZoneTextPrinterParser.1
            @Override // java.util.Comparator
            public int compare(String str, String str2) {
                int length = str2.length() - str.length();
                return length == 0 ? str.compareTo(str2) : length;
            }
        };
        private final TextStyle textStyle;

        ZoneTextPrinterParser(TextStyle textStyle) {
            this.textStyle = (TextStyle) Jdk8Methods.requireNonNull(textStyle, "textStyle");
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            ZoneId zoneId = (ZoneId) dateTimePrintContext.getValue(TemporalQueries.zoneId());
            if (zoneId == null) {
                return false;
            }
            if (zoneId.normalized() instanceof ZoneOffset) {
                sb.append(zoneId.getId());
                return true;
            }
            TemporalAccessor temporal = dateTimePrintContext.getTemporal();
            sb.append(TimeZone.getTimeZone(zoneId.getId()).getDisplayName(temporal.isSupported(ChronoField.INSTANT_SECONDS) ? zoneId.getRules().isDaylightSavings(Instant.ofEpochSecond(temporal.getLong(ChronoField.INSTANT_SECONDS))) : false, this.textStyle.asNormal() == TextStyle.FULL ? 1 : 0, dateTimePrintContext.getLocale()));
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            TreeMap treeMap = new TreeMap(LENGTH_COMPARATOR);
            for (String str : ZoneId.getAvailableZoneIds()) {
                treeMap.put(str, str);
                TimeZone timeZone = TimeZone.getTimeZone(str);
                int i2 = this.textStyle.asNormal() == TextStyle.FULL ? 1 : 0;
                treeMap.put(timeZone.getDisplayName(false, i2, dateTimeParseContext.getLocale()), str);
                treeMap.put(timeZone.getDisplayName(true, i2, dateTimeParseContext.getLocale()), str);
            }
            for (Map.Entry entry : treeMap.entrySet()) {
                String str2 = (String) entry.getKey();
                if (dateTimeParseContext.subSequenceEquals(charSequence, i, str2, 0, str2.length())) {
                    dateTimeParseContext.setParsed(ZoneId.of((String) entry.getValue()));
                    return i + str2.length();
                }
            }
            return ~i;
        }

        public String toString() {
            return "ZoneText(" + this.textStyle + ")";
        }
    }

    static final class ZoneIdPrinterParser implements DateTimePrinterParser {
        private static volatile Map.Entry<Integer, SubstringTree> cachedSubstringTree;
        private final String description;
        private final TemporalQuery<ZoneId> query;

        ZoneIdPrinterParser(TemporalQuery<ZoneId> temporalQuery, String str) {
            this.query = temporalQuery;
            this.description = str;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            ZoneId zoneId = (ZoneId) dateTimePrintContext.getValue(this.query);
            if (zoneId == null) {
                return false;
            }
            sb.append(zoneId.getId());
            return true;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            int length = charSequence.length();
            if (i > length) {
                throw new IndexOutOfBoundsException();
            }
            if (i == length) {
                return ~i;
            }
            char cCharAt = charSequence.charAt(i);
            if (cCharAt == '+' || cCharAt == '-') {
                DateTimeParseContext dateTimeParseContextCopy = dateTimeParseContext.copy();
                int i3 = OffsetIdPrinterParser.INSTANCE_ID.parse(dateTimeParseContextCopy, charSequence, i);
                if (i3 < 0) {
                    return i3;
                }
                dateTimeParseContext.setParsed(ZoneOffset.ofTotalSeconds((int) dateTimeParseContextCopy.getParsed(ChronoField.OFFSET_SECONDS).longValue()));
                return i3;
            }
            int i4 = i + 2;
            if (length >= i4) {
                char cCharAt2 = charSequence.charAt(i + 1);
                if (dateTimeParseContext.charEquals(cCharAt, Matrix.MATRIX_TYPE_RANDOM_UT) && dateTimeParseContext.charEquals(cCharAt2, 'T')) {
                    int i5 = i + 3;
                    if (length >= i5 && dateTimeParseContext.charEquals(charSequence.charAt(i4), 'C')) {
                        return parsePrefixedOffset(dateTimeParseContext, charSequence, i, i5);
                    }
                    return parsePrefixedOffset(dateTimeParseContext, charSequence, i, i4);
                }
                if (dateTimeParseContext.charEquals(cCharAt, 'G') && length >= (i2 = i + 3) && dateTimeParseContext.charEquals(cCharAt2, 'M') && dateTimeParseContext.charEquals(charSequence.charAt(i4), 'T')) {
                    return parsePrefixedOffset(dateTimeParseContext, charSequence, i, i2);
                }
            }
            Set<String> availableZoneIds = ZoneRulesProvider.getAvailableZoneIds();
            int size = availableZoneIds.size();
            Map.Entry<Integer, SubstringTree> simpleImmutableEntry = cachedSubstringTree;
            if (simpleImmutableEntry == null || simpleImmutableEntry.getKey().intValue() != size) {
                synchronized (this) {
                    simpleImmutableEntry = cachedSubstringTree;
                    if (simpleImmutableEntry == null || simpleImmutableEntry.getKey().intValue() != size) {
                        simpleImmutableEntry = new AbstractMap.SimpleImmutableEntry<>(Integer.valueOf(size), prepareParser(availableZoneIds));
                        cachedSubstringTree = simpleImmutableEntry;
                    }
                }
            }
            SubstringTree value = simpleImmutableEntry.getValue();
            String str = null;
            String str2 = null;
            while (value != null) {
                int i6 = value.length + i;
                if (i6 > length) {
                    break;
                }
                String string = charSequence.subSequence(i, i6).toString();
                value = value.get(string, dateTimeParseContext.isCaseSensitive());
                str2 = str;
                str = string;
            }
            ZoneId zoneIdConvertToZone = convertToZone(availableZoneIds, str, dateTimeParseContext.isCaseSensitive());
            if (zoneIdConvertToZone == null) {
                zoneIdConvertToZone = convertToZone(availableZoneIds, str2, dateTimeParseContext.isCaseSensitive());
                if (zoneIdConvertToZone == null) {
                    if (!dateTimeParseContext.charEquals(cCharAt, Matrix.MATRIX_TYPE_ZERO)) {
                        return ~i;
                    }
                    dateTimeParseContext.setParsed(ZoneOffset.UTC);
                    return i + 1;
                }
                str = str2;
            }
            dateTimeParseContext.setParsed(zoneIdConvertToZone);
            return i + str.length();
        }

        private ZoneId convertToZone(Set<String> set, String str, boolean z) {
            if (str == null) {
                return null;
            }
            if (z) {
                if (set.contains(str)) {
                    return ZoneId.of(str);
                }
                return null;
            }
            for (String str2 : set) {
                if (str2.equalsIgnoreCase(str)) {
                    return ZoneId.of(str2);
                }
            }
            return null;
        }

        private int parsePrefixedOffset(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i, int i2) {
            String upperCase = charSequence.subSequence(i, i2).toString().toUpperCase();
            DateTimeParseContext dateTimeParseContextCopy = dateTimeParseContext.copy();
            if (i2 < charSequence.length() && dateTimeParseContext.charEquals(charSequence.charAt(i2), Matrix.MATRIX_TYPE_ZERO)) {
                dateTimeParseContext.setParsed(ZoneId.ofOffset(upperCase, ZoneOffset.UTC));
                return i2;
            }
            int i3 = OffsetIdPrinterParser.INSTANCE_ID.parse(dateTimeParseContextCopy, charSequence, i2);
            if (i3 < 0) {
                dateTimeParseContext.setParsed(ZoneId.ofOffset(upperCase, ZoneOffset.UTC));
                return i2;
            }
            dateTimeParseContext.setParsed(ZoneId.ofOffset(upperCase, ZoneOffset.ofTotalSeconds((int) dateTimeParseContextCopy.getParsed(ChronoField.OFFSET_SECONDS).longValue())));
            return i3;
        }

        private static final class SubstringTree {
            final int length;
            private final Map<CharSequence, SubstringTree> substringMap;
            private final Map<String, SubstringTree> substringMapCI;

            private SubstringTree(int i) {
                this.substringMap = new HashMap();
                this.substringMapCI = new HashMap();
                this.length = i;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public SubstringTree get(CharSequence charSequence, boolean z) {
                if (z) {
                    return this.substringMap.get(charSequence);
                }
                return this.substringMapCI.get(charSequence.toString().toLowerCase(Locale.ENGLISH));
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void add(String str) {
                int length = str.length();
                int i = this.length;
                if (length == i) {
                    this.substringMap.put(str, null);
                    this.substringMapCI.put(str.toLowerCase(Locale.ENGLISH), null);
                } else if (length > i) {
                    String strSubstring = str.substring(0, i);
                    SubstringTree substringTree = this.substringMap.get(strSubstring);
                    if (substringTree == null) {
                        substringTree = new SubstringTree(length);
                        this.substringMap.put(strSubstring, substringTree);
                        this.substringMapCI.put(strSubstring.toLowerCase(Locale.ENGLISH), substringTree);
                    }
                    substringTree.add(str);
                }
            }
        }

        private static SubstringTree prepareParser(Set<String> set) {
            ArrayList arrayList = new ArrayList(set);
            Collections.sort(arrayList, DateTimeFormatterBuilder.LENGTH_SORT);
            SubstringTree substringTree = new SubstringTree(((String) arrayList.get(0)).length());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                substringTree.add((String) it.next());
            }
            return substringTree;
        }

        public String toString() {
            return this.description;
        }
    }

    static final class ChronoPrinterParser implements DateTimePrinterParser {
        private final TextStyle textStyle;

        ChronoPrinterParser(TextStyle textStyle) {
            this.textStyle = textStyle;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Chronology chronology = (Chronology) dateTimePrintContext.getValue(TemporalQueries.chronology());
            if (chronology == null) {
                return false;
            }
            if (this.textStyle == null) {
                sb.append(chronology.getId());
                return true;
            }
            try {
                sb.append(ResourceBundle.getBundle("org.threeten.bp.format.ChronologyText", dateTimePrintContext.getLocale(), DateTimeFormatterBuilder.class.getClassLoader()).getString(chronology.getId()));
                return true;
            } catch (MissingResourceException unused) {
                sb.append(chronology.getId());
                return true;
            }
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (i < 0 || i > charSequence.length()) {
                throw new IndexOutOfBoundsException();
            }
            Chronology chronology = null;
            int i2 = -1;
            for (Chronology chronology2 : Chronology.getAvailableChronologies()) {
                String id = chronology2.getId();
                int length = id.length();
                if (length > i2 && dateTimeParseContext.subSequenceEquals(charSequence, i, id, 0, length)) {
                    chronology = chronology2;
                    i2 = length;
                }
            }
            if (chronology == null) {
                return ~i;
            }
            dateTimeParseContext.setParsed(chronology);
            return i + i2;
        }
    }

    static final class LocalizedPrinterParser implements DateTimePrinterParser {
        private final FormatStyle dateStyle;
        private final FormatStyle timeStyle;

        LocalizedPrinterParser(FormatStyle formatStyle, FormatStyle formatStyle2) {
            this.dateStyle = formatStyle;
            this.timeStyle = formatStyle2;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return formatter(dateTimePrintContext.getLocale(), Chronology.from(dateTimePrintContext.getTemporal())).toPrinterParser(false).print(dateTimePrintContext, sb);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            return formatter(dateTimeParseContext.getLocale(), dateTimeParseContext.getEffectiveChronology()).toPrinterParser(false).parse(dateTimeParseContext, charSequence, i);
        }

        private DateTimeFormatter formatter(Locale locale, Chronology chronology) {
            return DateTimeFormatStyleProvider.getInstance().getFormatter(this.dateStyle, this.timeStyle, chronology, locale);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Localized(");
            Object obj = this.dateStyle;
            if (obj == null) {
                obj = "";
            }
            StringBuilder sbAppend = sb.append(obj).append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            FormatStyle formatStyle = this.timeStyle;
            return sbAppend.append(formatStyle != null ? formatStyle : "").append(")").toString();
        }
    }

    static final class WeekFieldsPrinterParser implements DateTimePrinterParser {
        private final int count;
        private final char letter;

        public WeekFieldsPrinterParser(char c, int i) {
            this.letter = c;
            this.count = i;
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return evaluate(WeekFields.of(dateTimePrintContext.getLocale())).print(dateTimePrintContext, sb);
        }

        @Override // org.threeten.bp.format.DateTimeFormatterBuilder.DateTimePrinterParser
        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            return evaluate(WeekFields.of(dateTimeParseContext.getLocale())).parse(dateTimeParseContext, charSequence, i);
        }

        private DateTimePrinterParser evaluate(WeekFields weekFields) {
            char c = this.letter;
            if (c == 'W') {
                return new NumberPrinterParser(weekFields.weekOfMonth(), 1, 2, SignStyle.NOT_NEGATIVE);
            }
            if (c == 'Y') {
                if (this.count == 2) {
                    return new ReducedPrinterParser(weekFields.weekBasedYear(), 2, 2, 0, ReducedPrinterParser.BASE_DATE);
                }
                TemporalField temporalFieldWeekBasedYear = weekFields.weekBasedYear();
                int i = this.count;
                return new NumberPrinterParser(temporalFieldWeekBasedYear, i, 19, i < 4 ? SignStyle.NORMAL : SignStyle.EXCEEDS_PAD, -1);
            }
            if (c == 'c') {
                return new NumberPrinterParser(weekFields.dayOfWeek(), this.count, 2, SignStyle.NOT_NEGATIVE);
            }
            if (c == 'e') {
                return new NumberPrinterParser(weekFields.dayOfWeek(), this.count, 2, SignStyle.NOT_NEGATIVE);
            }
            if (c != 'w') {
                return null;
            }
            return new NumberPrinterParser(weekFields.weekOfWeekBasedYear(), this.count, 2, SignStyle.NOT_NEGATIVE);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Localized(");
            char c = this.letter;
            if (c == 'Y') {
                int i = this.count;
                if (i == 1) {
                    sb.append("WeekBasedYear");
                } else if (i == 2) {
                    sb.append("ReducedValue(WeekBasedYear,2,2,2000-01-01)");
                } else {
                    sb.append("WeekBasedYear,").append(this.count).append(",19,").append(this.count < 4 ? SignStyle.NORMAL : SignStyle.EXCEEDS_PAD);
                }
            } else {
                if (c == 'c' || c == 'e') {
                    sb.append("DayOfWeek");
                } else if (c == 'w') {
                    sb.append("WeekOfWeekBasedYear");
                } else if (c == 'W') {
                    sb.append("WeekOfMonth");
                }
                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
                sb.append(this.count);
            }
            sb.append(")");
            return sb.toString();
        }
    }
}
