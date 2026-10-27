package org.joda.time.format;

import com.amazon.mp3.skiplimit.impl.HardCodedRefreshStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadablePartial;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;

/* JADX INFO: loaded from: classes6.dex */
public class DateTimeFormatterBuilder {
    private ArrayList<Object> iElementPairs = new ArrayList<>();
    private Object iFormatter;

    public DateTimeFormatter toFormatter() {
        Object formatter = getFormatter();
        InternalPrinter internalPrinter = isPrinter(formatter) ? (InternalPrinter) formatter : null;
        InternalParser internalParser = isParser(formatter) ? (InternalParser) formatter : null;
        if (internalPrinter != null || internalParser != null) {
            return new DateTimeFormatter(internalPrinter, internalParser);
        }
        throw new UnsupportedOperationException("Both printing and parsing not supported");
    }

    public DateTimeParser toParser() {
        Object formatter = getFormatter();
        if (isParser(formatter)) {
            return InternalParserDateTimeParser.of((InternalParser) formatter);
        }
        throw new UnsupportedOperationException("Parsing is not supported");
    }

    public boolean canBuildFormatter() {
        return isFormatter(getFormatter());
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null) {
            throw new IllegalArgumentException("No formatter supplied");
        }
        return append0(dateTimeFormatter.getPrinter0(), dateTimeFormatter.getParser0());
    }

    public DateTimeFormatterBuilder append(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, DateTimeParserInternalParser.of(dateTimeParser));
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter, DateTimeParser[] dateTimeParserArr) {
        if (dateTimePrinter != null) {
            checkPrinter(dateTimePrinter);
        }
        if (dateTimeParserArr == null) {
            throw new IllegalArgumentException("No parsers supplied");
        }
        int length = dateTimeParserArr.length;
        int i = 0;
        if (length == 1) {
            if (dateTimeParserArr[0] == null) {
                throw new IllegalArgumentException("No parser supplied");
            }
            return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), DateTimeParserInternalParser.of(dateTimeParserArr[0]));
        }
        InternalParser[] internalParserArr = new InternalParser[length];
        while (i < length - 1) {
            InternalParser internalParserOf = DateTimeParserInternalParser.of(dateTimeParserArr[i]);
            internalParserArr[i] = internalParserOf;
            if (internalParserOf == null) {
                throw new IllegalArgumentException("Incomplete parser array");
            }
            i++;
        }
        internalParserArr[i] = DateTimeParserInternalParser.of(dateTimeParserArr[i]);
        return append0(DateTimePrinterInternalPrinter.of(dateTimePrinter), new MatchingParser(internalParserArr));
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, new MatchingParser(new InternalParser[]{DateTimeParserInternalParser.of(dateTimeParser), null}));
    }

    private void checkParser(DateTimeParser dateTimeParser) {
        if (dateTimeParser == null) {
            throw new IllegalArgumentException("No parser supplied");
        }
    }

    private void checkPrinter(DateTimePrinter dateTimePrinter) {
        if (dateTimePrinter == null) {
            throw new IllegalArgumentException("No printer supplied");
        }
    }

    private DateTimeFormatterBuilder append0(Object obj) {
        this.iFormatter = null;
        this.iElementPairs.add(obj);
        this.iElementPairs.add(obj);
        return this;
    }

    private DateTimeFormatterBuilder append0(InternalPrinter internalPrinter, InternalParser internalParser) {
        this.iFormatter = null;
        this.iElementPairs.add(internalPrinter);
        this.iElementPairs.add(internalParser);
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(char c) {
        return append0(new CharacterLiteral(c));
    }

    public DateTimeFormatterBuilder appendLiteral(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        int length = str.length();
        if (length == 0) {
            return this;
        }
        if (length == 1) {
            return append0(new CharacterLiteral(str.charAt(0)));
        }
        return append0(new StringLiteral(str));
    }

    public DateTimeFormatterBuilder appendDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        }
        if (i <= 1) {
            return append0(new UnpaddedNumber(dateTimeFieldType, i2, false));
        }
        return append0(new PaddedNumber(dateTimeFieldType, i2, false, i));
    }

    public DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType dateTimeFieldType, int i) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("Illegal number of digits: " + i);
        }
        return append0(new FixedNumber(dateTimeFieldType, i, false));
    }

    public DateTimeFormatterBuilder appendSignedDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        }
        if (i <= 1) {
            return append0(new UnpaddedNumber(dateTimeFieldType, i2, true));
        }
        return append0(new PaddedNumber(dateTimeFieldType, i2, true, i));
    }

    public DateTimeFormatterBuilder appendText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        return append0(new TextField(dateTimeFieldType, false));
    }

    public DateTimeFormatterBuilder appendShortText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        return append0(new TextField(dateTimeFieldType, true));
    }

    public DateTimeFormatterBuilder appendFraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        }
        return append0(new Fraction(dateTimeFieldType, i, i2));
    }

    public DateTimeFormatterBuilder appendFractionOfSecond(int i, int i2) {
        return appendFraction(DateTimeFieldType.secondOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfMinute(int i, int i2) {
        return appendFraction(DateTimeFieldType.minuteOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfHour(int i, int i2) {
        return appendFraction(DateTimeFieldType.hourOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendMillisOfSecond(int i) {
        return appendDecimal(DateTimeFieldType.millisOfSecond(), i, 3);
    }

    public DateTimeFormatterBuilder appendSecondOfMinute(int i) {
        return appendDecimal(DateTimeFieldType.secondOfMinute(), i, 2);
    }

    public DateTimeFormatterBuilder appendMinuteOfHour(int i) {
        return appendDecimal(DateTimeFieldType.minuteOfHour(), i, 2);
    }

    public DateTimeFormatterBuilder appendHourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.hourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendHourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.hourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfWeek(int i) {
        return appendDecimal(DateTimeFieldType.dayOfWeek(), i, 1);
    }

    public DateTimeFormatterBuilder appendDayOfMonth(int i) {
        return appendDecimal(DateTimeFieldType.dayOfMonth(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfYear(int i) {
        return appendDecimal(DateTimeFieldType.dayOfYear(), i, 3);
    }

    public DateTimeFormatterBuilder appendWeekOfWeekyear(int i) {
        return appendDecimal(DateTimeFieldType.weekOfWeekyear(), i, 2);
    }

    public DateTimeFormatterBuilder appendWeekyear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.weekyear(), i, i2);
    }

    public DateTimeFormatterBuilder appendMonthOfYear(int i) {
        return appendDecimal(DateTimeFieldType.monthOfYear(), i, 2);
    }

    public DateTimeFormatterBuilder appendYear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.year(), i, i2);
    }

    public DateTimeFormatterBuilder appendTwoDigitYear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.year(), i, z));
    }

    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.weekyear(), i, z));
    }

    public DateTimeFormatterBuilder appendYearOfEra(int i, int i2) {
        return appendDecimal(DateTimeFieldType.yearOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendCenturyOfEra(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.centuryOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendHalfdayOfDayText() {
        return appendText(DateTimeFieldType.halfdayOfDay());
    }

    public DateTimeFormatterBuilder appendDayOfWeekText() {
        return appendText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendDayOfWeekShortText() {
        return appendShortText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendMonthOfYearText() {
        return appendText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendMonthOfYearShortText() {
        return appendShortText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendEraText() {
        return appendText(DateTimeFieldType.era());
    }

    public DateTimeFormatterBuilder appendTimeZoneName() {
        return append0(new TimeZoneName(0, null), null);
    }

    public DateTimeFormatterBuilder appendTimeZoneShortName(Map<String, DateTimeZone> map) {
        TimeZoneName timeZoneName = new TimeZoneName(1, map);
        return append0(timeZoneName, timeZoneName);
    }

    public DateTimeFormatterBuilder appendTimeZoneId() {
        TimeZoneId timeZoneId = TimeZoneId.INSTANCE;
        return append0(timeZoneId, timeZoneId);
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str, z, i, i2));
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str2, z, i, i2));
    }

    private Object getFormatter() {
        Object composite = this.iFormatter;
        if (composite == null) {
            if (this.iElementPairs.size() == 2) {
                Object obj = this.iElementPairs.get(0);
                Object obj2 = this.iElementPairs.get(1);
                if (obj == null) {
                    composite = obj2;
                } else if (obj == obj2 || obj2 == null) {
                    composite = obj;
                }
            }
            if (composite == null) {
                composite = new Composite(this.iElementPairs);
            }
            this.iFormatter = composite;
        }
        return composite;
    }

    private boolean isPrinter(Object obj) {
        if (!(obj instanceof InternalPrinter)) {
            return false;
        }
        if (obj instanceof Composite) {
            return ((Composite) obj).isPrinter();
        }
        return true;
    }

    private boolean isParser(Object obj) {
        if (!(obj instanceof InternalParser)) {
            return false;
        }
        if (obj instanceof Composite) {
            return ((Composite) obj).isParser();
        }
        return true;
    }

    private boolean isFormatter(Object obj) {
        return isPrinter(obj) || isParser(obj);
    }

    static void appendUnknownString(Appendable appendable, int i) throws IOException {
        while (true) {
            i--;
            if (i < 0) {
                return;
            } else {
                appendable.append((char) 65533);
            }
        }
    }

    static class CharacterLiteral implements InternalPrinter, InternalParser {
        private final char iValue;

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return 1;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return 1;
        }

        CharacterLiteral(char c) {
            this.iValue = c;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            char upperCase;
            char upperCase2;
            if (i >= charSequence.length()) {
                return ~i;
            }
            char cCharAt = charSequence.charAt(i);
            char c = this.iValue;
            return (cCharAt == c || (upperCase = Character.toUpperCase(cCharAt)) == (upperCase2 = Character.toUpperCase(c)) || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2)) ? i + 1 : ~i;
        }
    }

    static class StringLiteral implements InternalPrinter, InternalParser {
        private final String iValue;

        StringLiteral(String str) {
            this.iValue = str;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iValue.length();
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iValue.length();
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            return DateTimeFormatterBuilder.csStartsWithIgnoreCase(charSequence, i, this.iValue) ? i + this.iValue.length() : ~i;
        }
    }

    static abstract class NumberFormatter implements InternalPrinter, InternalParser {
        protected final DateTimeFieldType iFieldType;
        protected final int iMaxParsedDigits;
        protected final boolean iSigned;

        NumberFormatter(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iMaxParsedDigits = i;
            this.iSigned = z;
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iMaxParsedDigits;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            int i3;
            char cCharAt;
            int iMin = Math.min(this.iMaxParsedDigits, charSequence.length() - i);
            int i4 = 0;
            boolean z = false;
            boolean z2 = false;
            while (i4 < iMin) {
                int i5 = i + i4;
                char cCharAt2 = charSequence.charAt(i5);
                if (i4 == 0 && ((cCharAt2 == '-' || cCharAt2 == '+') && this.iSigned)) {
                    boolean z3 = cCharAt2 == '-';
                    boolean z4 = cCharAt2 == '+';
                    int i6 = i4 + 1;
                    if (i6 >= iMin || (cCharAt = charSequence.charAt(i5 + 1)) < '0' || cCharAt > '9') {
                        boolean z5 = z3;
                        z2 = z4;
                        z = z5;
                        break;
                    }
                    iMin = Math.min(iMin + 1, charSequence.length() - i);
                    i4 = i6;
                    boolean z6 = z3;
                    z2 = z4;
                    z = z6;
                } else {
                    if (cCharAt2 < '0' || cCharAt2 > '9') {
                        break;
                    }
                    i4++;
                }
            }
            if (i4 == 0) {
                return ~i;
            }
            if (i4 < 9) {
                int i7 = (z || z2) ? i + 1 : i;
                int i8 = i7 + 1;
                try {
                    int iCharAt = charSequence.charAt(i7) - '0';
                    i2 = i + i4;
                    while (i8 < i2) {
                        int i9 = (iCharAt << 3) + (iCharAt << 1);
                        int i10 = i8 + 1;
                        int iCharAt2 = (i9 + charSequence.charAt(i8)) - 48;
                        i8 = i10;
                        iCharAt = iCharAt2;
                    }
                    i3 = z ? -iCharAt : iCharAt;
                } catch (StringIndexOutOfBoundsException unused) {
                    return ~i;
                }
            } else if (z2) {
                i2 = i + i4;
                i3 = Integer.parseInt(charSequence.subSequence(i + 1, i2).toString());
            } else {
                int i11 = i + i4;
                i3 = Integer.parseInt(charSequence.subSequence(i, i11).toString());
                i2 = i11;
            }
            dateTimeParserBucket.saveField(this.iFieldType, i3);
            return i2;
        }
    }

    static class UnpaddedNumber extends NumberFormatter {
        protected UnpaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z);
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                FormatUtils.appendUnpaddedInteger(appendable, this.iFieldType.getField(chronology).get(j));
            } catch (RuntimeException unused) {
                appendable.append((char) 65533);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendUnpaddedInteger(appendable, readablePartial.get(this.iFieldType));
                    return;
                } catch (RuntimeException unused) {
                    appendable.append((char) 65533);
                    return;
                }
            }
            appendable.append((char) 65533);
        }
    }

    static class PaddedNumber extends NumberFormatter {
        protected final int iMinPrintedDigits;

        protected PaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z, int i2) {
            super(dateTimeFieldType, i, z);
            this.iMinPrintedDigits = i2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                FormatUtils.appendPaddedInteger(appendable, this.iFieldType.getField(chronology).get(j), this.iMinPrintedDigits);
            } catch (RuntimeException unused) {
                DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendPaddedInteger(appendable, readablePartial.get(this.iFieldType), this.iMinPrintedDigits);
                    return;
                } catch (RuntimeException unused) {
                    DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
                    return;
                }
            }
            DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
        }
    }

    static class FixedNumber extends PaddedNumber {
        protected FixedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z, i);
        }

        @Override // org.joda.time.format.DateTimeFormatterBuilder.NumberFormatter, org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            char cCharAt;
            int into = super.parseInto(dateTimeParserBucket, charSequence, i);
            if (into < 0 || into == (i2 = this.iMaxParsedDigits + i)) {
                return into;
            }
            if (this.iSigned && ((cCharAt = charSequence.charAt(i)) == '-' || cCharAt == '+')) {
                i2++;
            }
            return into > i2 ? ~(i2 + 1) : into < i2 ? ~into : into;
        }
    }

    static class TwoDigitYear implements InternalPrinter, InternalParser {
        private final boolean iLenientParse;
        private final int iPivot;
        private final DateTimeFieldType iType;

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return 2;
        }

        TwoDigitYear(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iType = dateTimeFieldType;
            this.iPivot = i;
            this.iLenientParse = z;
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iLenientParse ? 4 : 2;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            int i3;
            int i4;
            int length = charSequence.length() - i;
            if (this.iLenientParse) {
                int i5 = 0;
                boolean z = false;
                boolean z2 = false;
                while (i5 < length) {
                    char cCharAt = charSequence.charAt(i + i5);
                    if (i5 == 0 && (cCharAt == '-' || cCharAt == '+')) {
                        z2 = cCharAt == '-';
                        if (z2) {
                            i5++;
                        } else {
                            i++;
                            length--;
                        }
                        z = true;
                    } else {
                        if (cCharAt < '0' || cCharAt > '9') {
                            break;
                        }
                        i5++;
                    }
                }
                if (i5 == 0) {
                    return ~i;
                }
                if (z || i5 != 2) {
                    if (i5 >= 9) {
                        i2 = i5 + i;
                        i3 = Integer.parseInt(charSequence.subSequence(i, i2).toString());
                    } else {
                        int i6 = z2 ? i + 1 : i;
                        int i7 = i6 + 1;
                        try {
                            int iCharAt = charSequence.charAt(i6) - '0';
                            i2 = i5 + i;
                            while (i7 < i2) {
                                int iCharAt2 = (((iCharAt << 3) + (iCharAt << 1)) + charSequence.charAt(i7)) - 48;
                                i7++;
                                iCharAt = iCharAt2;
                            }
                            i3 = z2 ? -iCharAt : iCharAt;
                        } catch (StringIndexOutOfBoundsException unused) {
                            return ~i;
                        }
                    }
                    dateTimeParserBucket.saveField(this.iType, i3);
                    return i2;
                }
            } else if (Math.min(2, length) < 2) {
                return ~i;
            }
            char cCharAt2 = charSequence.charAt(i);
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                return ~i;
            }
            int i8 = cCharAt2 - '0';
            char cCharAt3 = charSequence.charAt(i + 1);
            if (cCharAt3 < '0' || cCharAt3 > '9') {
                return ~i;
            }
            int i9 = (((i8 << 3) + (i8 << 1)) + cCharAt3) - 48;
            int iIntValue = this.iPivot;
            if (dateTimeParserBucket.getPivotYear() != null) {
                iIntValue = dateTimeParserBucket.getPivotYear().intValue();
            }
            int i10 = iIntValue - 50;
            if (i10 >= 0) {
                i4 = i10 % 100;
            } else {
                i4 = ((i10 + 1) % 100) + 99;
            }
            dateTimeParserBucket.saveField(this.iType, i9 + ((i10 + (i9 < i4 ? 100 : 0)) - i4));
            return i + 2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            int twoDigitYear = getTwoDigitYear(j, chronology);
            if (twoDigitYear < 0) {
                appendable.append((char) 65533);
                appendable.append((char) 65533);
            } else {
                FormatUtils.appendPaddedInteger(appendable, twoDigitYear, 2);
            }
        }

        private int getTwoDigitYear(long j, Chronology chronology) {
            try {
                int i = this.iType.getField(chronology).get(j);
                if (i < 0) {
                    i = -i;
                }
                return i % 100;
            } catch (RuntimeException unused) {
                return -1;
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            int twoDigitYear = getTwoDigitYear(readablePartial);
            if (twoDigitYear < 0) {
                appendable.append((char) 65533);
                appendable.append((char) 65533);
            } else {
                FormatUtils.appendPaddedInteger(appendable, twoDigitYear, 2);
            }
        }

        private int getTwoDigitYear(ReadablePartial readablePartial) {
            if (!readablePartial.isSupported(this.iType)) {
                return -1;
            }
            try {
                int i = readablePartial.get(this.iType);
                if (i < 0) {
                    i = -i;
                }
                return i % 100;
            } catch (RuntimeException unused) {
                return -1;
            }
        }
    }

    static class TextField implements InternalPrinter, InternalParser {
        private static Map<Locale, Map<DateTimeFieldType, Object[]>> cParseCache = new ConcurrentHashMap();
        private final DateTimeFieldType iFieldType;
        private final boolean iShort;

        TextField(DateTimeFieldType dateTimeFieldType, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iShort = z;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iShort ? 6 : 20;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                appendable.append(print(j, chronology, locale));
            } catch (RuntimeException unused) {
                appendable.append((char) 65533);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            try {
                appendable.append(print(readablePartial, locale));
            } catch (RuntimeException unused) {
                appendable.append((char) 65533);
            }
        }

        private String print(long j, Chronology chronology, Locale locale) {
            DateTimeField field = this.iFieldType.getField(chronology);
            if (this.iShort) {
                return field.getAsShortText(j, locale);
            }
            return field.getAsText(j, locale);
        }

        private String print(ReadablePartial readablePartial, Locale locale) {
            if (!readablePartial.isSupported(this.iFieldType)) {
                return "�";
            }
            DateTimeField field = this.iFieldType.getField(readablePartial.getChronology());
            if (this.iShort) {
                return field.getAsShortText(readablePartial, locale);
            }
            return field.getAsText(readablePartial, locale);
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int iIntValue;
            Map concurrentHashMap;
            Locale locale = dateTimeParserBucket.getLocale();
            Map<DateTimeFieldType, Object[]> concurrentHashMap2 = cParseCache.get(locale);
            if (concurrentHashMap2 == null) {
                concurrentHashMap2 = new ConcurrentHashMap<>();
                cParseCache.put(locale, concurrentHashMap2);
            }
            Object[] objArr = concurrentHashMap2.get(this.iFieldType);
            if (objArr == null) {
                concurrentHashMap = new ConcurrentHashMap(32);
                MutableDateTime.Property property = new MutableDateTime(0L, DateTimeZone.UTC).property(this.iFieldType);
                int minimumValueOverall = property.getMinimumValueOverall();
                int maximumValueOverall = property.getMaximumValueOverall();
                if (maximumValueOverall - minimumValueOverall > 32) {
                    return ~i;
                }
                iIntValue = property.getMaximumTextLength(locale);
                while (minimumValueOverall <= maximumValueOverall) {
                    property.set(minimumValueOverall);
                    String asShortText = property.getAsShortText(locale);
                    Boolean bool = Boolean.TRUE;
                    concurrentHashMap.put(asShortText, bool);
                    concurrentHashMap.put(property.getAsShortText(locale).toLowerCase(locale), bool);
                    concurrentHashMap.put(property.getAsShortText(locale).toUpperCase(locale), bool);
                    concurrentHashMap.put(property.getAsText(locale), bool);
                    concurrentHashMap.put(property.getAsText(locale).toLowerCase(locale), bool);
                    concurrentHashMap.put(property.getAsText(locale).toUpperCase(locale), bool);
                    minimumValueOverall++;
                }
                if ("en".equals(locale.getLanguage()) && this.iFieldType == DateTimeFieldType.era()) {
                    Boolean bool2 = Boolean.TRUE;
                    concurrentHashMap.put("BCE", bool2);
                    concurrentHashMap.put("bce", bool2);
                    concurrentHashMap.put("CE", bool2);
                    concurrentHashMap.put("ce", bool2);
                    iIntValue = 3;
                }
                concurrentHashMap2.put(this.iFieldType, new Object[]{concurrentHashMap, Integer.valueOf(iIntValue)});
            } else {
                Map map = (Map) objArr[0];
                iIntValue = ((Integer) objArr[1]).intValue();
                concurrentHashMap = map;
            }
            for (int iMin = Math.min(charSequence.length(), iIntValue + i); iMin > i; iMin--) {
                String string = charSequence.subSequence(i, iMin).toString();
                if (concurrentHashMap.containsKey(string)) {
                    dateTimeParserBucket.saveField(this.iFieldType, string, locale);
                    return iMin;
                }
            }
            return ~i;
        }
    }

    static class Fraction implements InternalPrinter, InternalParser {
        private final DateTimeFieldType iFieldType;
        protected int iMaxDigits;
        protected int iMinDigits;

        protected Fraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
            this.iFieldType = dateTimeFieldType;
            i2 = i2 > 18 ? 18 : i2;
            this.iMinDigits = i;
            this.iMaxDigits = i2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iMaxDigits;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            printTo(appendable, j, chronology);
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            printTo(appendable, readablePartial.getChronology().set(readablePartial, 0L), readablePartial.getChronology());
        }

        protected void printTo(Appendable appendable, long j, Chronology chronology) throws IOException {
            String string;
            DateTimeField field = this.iFieldType.getField(chronology);
            int i = this.iMinDigits;
            try {
                long jRemainder = field.remainder(j);
                if (jRemainder != 0) {
                    long[] fractionData = getFractionData(jRemainder, field);
                    long j2 = fractionData[0];
                    int i2 = (int) fractionData[1];
                    if ((2147483647L & j2) == j2) {
                        string = Integer.toString((int) j2);
                    } else {
                        string = Long.toString(j2);
                    }
                    int length = string.length();
                    while (length < i2) {
                        appendable.append('0');
                        i--;
                        i2--;
                    }
                    if (i < i2) {
                        while (i < i2 && length > 1 && string.charAt(length - 1) == '0') {
                            i2--;
                            length--;
                        }
                        if (length < string.length()) {
                            for (int i3 = 0; i3 < length; i3++) {
                                appendable.append(string.charAt(i3));
                            }
                            return;
                        }
                    }
                    appendable.append(string);
                    return;
                }
                while (true) {
                    i--;
                    if (i < 0) {
                        return;
                    } else {
                        appendable.append('0');
                    }
                }
            } catch (RuntimeException unused) {
                DateTimeFormatterBuilder.appendUnknownString(appendable, i);
            }
        }

        private long[] getFractionData(long j, DateTimeField dateTimeField) {
            long j2;
            long unitMillis = dateTimeField.getDurationField().getUnitMillis();
            int i = this.iMaxDigits;
            while (true) {
                switch (i) {
                    case 1:
                        j2 = 10;
                        break;
                    case 2:
                        j2 = 100;
                        break;
                    case 3:
                        j2 = 1000;
                        break;
                    case 4:
                        j2 = 10000;
                        break;
                    case 5:
                        j2 = 100000;
                        break;
                    case 6:
                        j2 = 1000000;
                        break;
                    case 7:
                        j2 = 10000000;
                        break;
                    case 8:
                        j2 = 100000000;
                        break;
                    case 9:
                        j2 = 1000000000;
                        break;
                    case 10:
                        j2 = 10000000000L;
                        break;
                    case 11:
                        j2 = 100000000000L;
                        break;
                    case 12:
                        j2 = 1000000000000L;
                        break;
                    case 13:
                        j2 = 10000000000000L;
                        break;
                    case 14:
                        j2 = 100000000000000L;
                        break;
                    case 15:
                        j2 = 1000000000000000L;
                        break;
                    case 16:
                        j2 = 10000000000000000L;
                        break;
                    case 17:
                        j2 = 100000000000000000L;
                        break;
                    case 18:
                        j2 = 1000000000000000000L;
                        break;
                    default:
                        j2 = 1;
                        break;
                }
                if ((unitMillis * j2) / j2 == unitMillis) {
                    return new long[]{(j * j2) / unitMillis, i};
                }
                i--;
            }
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iMaxDigits;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            DateTimeField field = this.iFieldType.getField(dateTimeParserBucket.getChronology());
            int iMin = Math.min(this.iMaxDigits, charSequence.length() - i);
            long unitMillis = field.getDurationField().getUnitMillis() * 10;
            long j = 0;
            int i2 = 0;
            while (i2 < iMin) {
                char cCharAt = charSequence.charAt(i + i2);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i2++;
                unitMillis /= 10;
                j += ((long) (cCharAt - '0')) * unitMillis;
            }
            long j2 = j / 10;
            if (i2 != 0 && j2 <= 2147483647L) {
                dateTimeParserBucket.saveField(new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, field.getDurationField()), (int) j2);
                return i + i2;
            }
            return ~i;
        }
    }

    static class TimeZoneOffset implements InternalPrinter, InternalParser {
        private final int iMaxFields;
        private final int iMinFields;
        private final boolean iShowSeparators;
        private final String iZeroOffsetParseText;
        private final String iZeroOffsetPrintText;

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        TimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
            this.iZeroOffsetPrintText = str;
            this.iZeroOffsetParseText = str2;
            this.iShowSeparators = z;
            if (i <= 0 || i2 < i) {
                throw new IllegalArgumentException();
            }
            if (i > 4) {
                i = 4;
                i2 = 4;
            }
            this.iMinFields = i;
            this.iMaxFields = i2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            int i = this.iMinFields;
            int i2 = (i + 1) << 1;
            if (this.iShowSeparators) {
                i2 += i - 1;
            }
            String str = this.iZeroOffsetPrintText;
            return (str == null || str.length() <= i2) ? i2 : this.iZeroOffsetPrintText.length();
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            String str;
            if (dateTimeZone == null) {
                return;
            }
            if (i == 0 && (str = this.iZeroOffsetPrintText) != null) {
                appendable.append(str);
                return;
            }
            if (i >= 0) {
                appendable.append('+');
            } else {
                appendable.append('-');
                i = -i;
            }
            int i2 = i / 3600000;
            FormatUtils.appendPaddedInteger(appendable, i2, 2);
            if (this.iMaxFields == 1) {
                return;
            }
            int i3 = i - (i2 * 3600000);
            if (i3 != 0 || this.iMinFields > 1) {
                int i4 = i3 / HardCodedRefreshStrategy.MS_IN_MINUTE;
                if (this.iShowSeparators) {
                    appendable.append(':');
                }
                FormatUtils.appendPaddedInteger(appendable, i4, 2);
                if (this.iMaxFields == 2) {
                    return;
                }
                int i5 = i3 - (i4 * HardCodedRefreshStrategy.MS_IN_MINUTE);
                if (i5 != 0 || this.iMinFields > 2) {
                    int i6 = i5 / 1000;
                    if (this.iShowSeparators) {
                        appendable.append(':');
                    }
                    FormatUtils.appendPaddedInteger(appendable, i6, 2);
                    if (this.iMaxFields == 3) {
                        return;
                    }
                    int i7 = i5 - (i6 * 1000);
                    if (i7 != 0 || this.iMinFields > 3) {
                        if (this.iShowSeparators) {
                            appendable.append('.');
                        }
                        FormatUtils.appendPaddedInteger(appendable, i7, 3);
                    }
                }
            }
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        /* JADX WARN: Removed duplicated region for block: B:66:0x00bd  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x00d3  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x00f2  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x00f4  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x00f6  */
        @Override // org.joda.time.format.InternalParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int parseInto(org.joda.time.format.DateTimeParserBucket r12, java.lang.CharSequence r13, int r14) {
            /*
                Method dump skipped, instruction units count: 294
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatterBuilder.TimeZoneOffset.parseInto(org.joda.time.format.DateTimeParserBucket, java.lang.CharSequence, int):int");
        }

        private int digitCount(CharSequence charSequence, int i, int i2) {
            int i3 = 0;
            for (int iMin = Math.min(charSequence.length() - i, i2); iMin > 0; iMin--) {
                char cCharAt = charSequence.charAt(i + i3);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i3++;
            }
            return i3;
        }
    }

    static class TimeZoneName implements InternalPrinter, InternalParser {
        private final Map<String, DateTimeZone> iParseLookup;
        private final int iType;

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        TimeZoneName(int i, Map<String, DateTimeZone> map) {
            this.iType = i;
            this.iParseLookup = map;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(print(j - ((long) i), dateTimeZone, locale));
        }

        private String print(long j, DateTimeZone dateTimeZone, Locale locale) {
            if (dateTimeZone == null) {
                return "";
            }
            int i = this.iType;
            if (i != 0) {
                return i != 1 ? "" : dateTimeZone.getShortName(j, locale);
            }
            return dateTimeZone.getName(j, locale);
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            Map<String, DateTimeZone> defaultTimeZoneNames = this.iParseLookup;
            if (defaultTimeZoneNames == null) {
                defaultTimeZoneNames = DateTimeUtils.getDefaultTimeZoneNames();
            }
            String str = null;
            for (String str2 : defaultTimeZoneNames.keySet()) {
                if (DateTimeFormatterBuilder.csStartsWith(charSequence, i, str2) && (str == null || str2.length() > str.length())) {
                    str = str2;
                }
            }
            if (str == null) {
                return ~i;
            }
            dateTimeParserBucket.setZone(defaultTimeZoneNames.get(str));
            return i + str.length();
        }
    }

    enum TimeZoneId implements InternalPrinter, InternalParser {
        INSTANCE;

        private static final List<String> ALL_IDS;
        private static final List<String> BASE_GROUPED_IDS = new ArrayList();
        private static final Map<String, List<String>> GROUPED_IDS;
        static final int MAX_LENGTH;
        static final int MAX_PREFIX_LENGTH;

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        static {
            ArrayList<String> arrayList = new ArrayList(DateTimeZone.getAvailableIDs());
            ALL_IDS = arrayList;
            Collections.sort(arrayList);
            GROUPED_IDS = new HashMap();
            int iMax = 0;
            int iMax2 = 0;
            for (String str : arrayList) {
                int iIndexOf = str.indexOf(47);
                if (iIndexOf >= 0) {
                    iIndexOf = iIndexOf < str.length() ? iIndexOf + 1 : iIndexOf;
                    iMax2 = Math.max(iMax2, iIndexOf);
                    String strSubstring = str.substring(0, iIndexOf + 1);
                    String strSubstring2 = str.substring(iIndexOf);
                    Map<String, List<String>> map = GROUPED_IDS;
                    if (!map.containsKey(strSubstring)) {
                        map.put(strSubstring, new ArrayList());
                    }
                    map.get(strSubstring).add(strSubstring2);
                } else {
                    BASE_GROUPED_IDS.add(str);
                }
                iMax = Math.max(iMax, str.length());
            }
            MAX_LENGTH = iMax;
            MAX_PREFIX_LENGTH = iMax2;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return MAX_LENGTH;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(dateTimeZone != null ? dateTimeZone.getID() : "");
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return MAX_LENGTH;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            String string;
            int length;
            List<String> list = BASE_GROUPED_IDS;
            int length2 = charSequence.length();
            int iMin = Math.min(length2, MAX_PREFIX_LENGTH + i);
            int i2 = i;
            while (true) {
                if (i2 >= iMin) {
                    string = "";
                    length = i;
                    break;
                }
                if (charSequence.charAt(i2) == '/') {
                    int i3 = i2 + 1;
                    string = charSequence.subSequence(i, i3).toString();
                    length = string.length() + i;
                    list = GROUPED_IDS.get(i2 < length2 - 1 ? string + charSequence.charAt(i3) : string);
                    if (list == null) {
                        return ~i;
                    }
                } else {
                    i2++;
                }
            }
            String str = null;
            for (int i4 = 0; i4 < list.size(); i4++) {
                String str2 = list.get(i4);
                if (DateTimeFormatterBuilder.csStartsWith(charSequence, length, str2) && (str == null || str2.length() > str.length())) {
                    str = str2;
                }
            }
            if (str == null) {
                return ~i;
            }
            dateTimeParserBucket.setZone(DateTimeZone.forID(string + str));
            return length + str.length();
        }
    }

    static class Composite implements InternalPrinter, InternalParser {
        private final int iParsedLengthEstimate;
        private final InternalParser[] iParsers;
        private final int iPrintedLengthEstimate;
        private final InternalPrinter[] iPrinters;

        Composite(List<Object> list) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (arrayList.contains(null) || arrayList.isEmpty()) {
                this.iPrinters = null;
                this.iPrintedLengthEstimate = 0;
            } else {
                int size = arrayList.size();
                this.iPrinters = new InternalPrinter[size];
                int iEstimatePrintedLength = 0;
                for (int i = 0; i < size; i++) {
                    InternalPrinter internalPrinter = (InternalPrinter) arrayList.get(i);
                    iEstimatePrintedLength += internalPrinter.estimatePrintedLength();
                    this.iPrinters[i] = internalPrinter;
                }
                this.iPrintedLengthEstimate = iEstimatePrintedLength;
            }
            if (arrayList2.contains(null) || arrayList2.isEmpty()) {
                this.iParsers = null;
                this.iParsedLengthEstimate = 0;
                return;
            }
            int size2 = arrayList2.size();
            this.iParsers = new InternalParser[size2];
            int iEstimateParsedLength = 0;
            for (int i2 = 0; i2 < size2; i2++) {
                InternalParser internalParser = (InternalParser) arrayList2.get(i2);
                iEstimateParsedLength += internalParser.estimateParsedLength();
                this.iParsers[i2] = internalParser;
            }
            this.iParsedLengthEstimate = iEstimateParsedLength;
        }

        @Override // org.joda.time.format.InternalPrinter
        public int estimatePrintedLength() {
            return this.iPrintedLengthEstimate;
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            InternalPrinter[] internalPrinterArr = this.iPrinters;
            if (internalPrinterArr == null) {
                throw new UnsupportedOperationException();
            }
            Locale locale2 = locale == null ? Locale.getDefault() : locale;
            for (InternalPrinter internalPrinter : internalPrinterArr) {
                internalPrinter.printTo(appendable, j, chronology, i, dateTimeZone, locale2);
            }
        }

        @Override // org.joda.time.format.InternalPrinter
        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            InternalPrinter[] internalPrinterArr = this.iPrinters;
            if (internalPrinterArr == null) {
                throw new UnsupportedOperationException();
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }
            for (InternalPrinter internalPrinter : internalPrinterArr) {
                internalPrinter.printTo(appendable, readablePartial, locale);
            }
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        @Override // org.joda.time.format.InternalParser
        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            InternalParser[] internalParserArr = this.iParsers;
            if (internalParserArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = internalParserArr.length;
            for (int i2 = 0; i2 < length && i >= 0; i2++) {
                i = internalParserArr[i2].parseInto(dateTimeParserBucket, charSequence, i);
            }
            return i;
        }

        boolean isPrinter() {
            return this.iPrinters != null;
        }

        boolean isParser() {
            return this.iParsers != null;
        }

        private void decompose(List<Object> list, List<Object> list2, List<Object> list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof Composite) {
                    addArrayToList(list2, ((Composite) obj).iPrinters);
                } else {
                    list2.add(obj);
                }
                Object obj2 = list.get(i + 1);
                if (obj2 instanceof Composite) {
                    addArrayToList(list3, ((Composite) obj2).iParsers);
                } else {
                    list3.add(obj2);
                }
            }
        }

        private void addArrayToList(List<Object> list, Object[] objArr) {
            if (objArr != null) {
                for (Object obj : objArr) {
                    list.add(obj);
                }
            }
        }
    }

    static class MatchingParser implements InternalParser {
        private final int iParsedLengthEstimate;
        private final InternalParser[] iParsers;

        MatchingParser(InternalParser[] internalParserArr) {
            int iEstimateParsedLength;
            this.iParsers = internalParserArr;
            int length = internalParserArr.length;
            int i = 0;
            while (true) {
                length--;
                if (length >= 0) {
                    InternalParser internalParser = internalParserArr[length];
                    if (internalParser != null && (iEstimateParsedLength = internalParser.estimateParsedLength()) > i) {
                        i = iEstimateParsedLength;
                    }
                } else {
                    this.iParsedLengthEstimate = i;
                    return;
                }
            }
        }

        @Override // org.joda.time.format.InternalParser
        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x0041, code lost:
        
            if (r6 > r12) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0043, code lost:
        
            if (r6 != r12) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0045, code lost:
        
            if (r3 == false) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0049, code lost:
        
            return ~r7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x004a, code lost:
        
            if (r4 == null) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x004c, code lost:
        
            r10.restoreState(r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x004f, code lost:
        
            return r6;
         */
        @Override // org.joda.time.format.InternalParser
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int parseInto(org.joda.time.format.DateTimeParserBucket r10, java.lang.CharSequence r11, int r12) {
            /*
                r9 = this;
                org.joda.time.format.InternalParser[] r0 = r9.iParsers
                int r1 = r0.length
                java.lang.Object r2 = r10.saveState()
                r3 = 0
                r4 = 0
                r6 = r12
                r7 = r6
                r5 = r3
            Lc:
                if (r5 >= r1) goto L41
                r8 = r0[r5]
                if (r8 != 0) goto L17
                if (r6 > r12) goto L15
                return r12
            L15:
                r3 = 1
                goto L41
            L17:
                int r8 = r8.parseInto(r10, r11, r12)
                if (r8 < r12) goto L35
                if (r8 <= r6) goto L3b
                int r4 = r11.length()
                if (r8 >= r4) goto L34
                int r4 = r5 + 1
                if (r4 >= r1) goto L34
                r4 = r0[r4]
                if (r4 != 0) goto L2e
                goto L34
            L2e:
                java.lang.Object r4 = r10.saveState()
                r6 = r8
                goto L3b
            L34:
                return r8
            L35:
                if (r8 >= 0) goto L3b
                int r8 = ~r8
                if (r8 <= r7) goto L3b
                r7 = r8
            L3b:
                r10.restoreState(r2)
                int r5 = r5 + 1
                goto Lc
            L41:
                if (r6 > r12) goto L4a
                if (r6 != r12) goto L48
                if (r3 == 0) goto L48
                goto L4a
            L48:
                int r10 = ~r7
                return r10
            L4a:
                if (r4 == 0) goto L4f
                r10.restoreState(r4)
            L4f:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatterBuilder.MatchingParser.parseInto(org.joda.time.format.DateTimeParserBucket, java.lang.CharSequence, int):int");
        }
    }

    static boolean csStartsWith(CharSequence charSequence, int i, String str) {
        int length = str.length();
        if (charSequence.length() - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (charSequence.charAt(i + i2) != str.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    static boolean csStartsWithIgnoreCase(CharSequence charSequence, int i, String str) {
        char upperCase;
        char upperCase2;
        int length = str.length();
        if (charSequence.length() - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = charSequence.charAt(i + i2);
            char cCharAt2 = str.charAt(i2);
            if (cCharAt != cCharAt2 && (upperCase = Character.toUpperCase(cCharAt)) != (upperCase2 = Character.toUpperCase(cCharAt2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                return false;
            }
        }
        return true;
    }
}
