package org.joda.time.format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import org.joda.time.DurationFieldType;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

/* JADX INFO: loaded from: classes6.dex */
public class PeriodFormatterBuilder {
    private static final ConcurrentMap<String, Pattern> PATTERNS = new ConcurrentHashMap();
    private List<Object> iElementPairs;
    private FieldFormatter[] iFieldFormatters;
    private int iMaxParsedDigits;
    private int iMinPrintedDigits;
    private boolean iNotParser;
    private boolean iNotPrinter;
    private PeriodFieldAffix iPrefix;
    private int iPrintZeroSetting;
    private boolean iRejectSignedValues;

    interface PeriodFieldAffix {
        int calculatePrintedLength(int i);

        void finish(Set<PeriodFieldAffix> set);

        String[] getAffixes();

        int parse(String str, int i);

        void printTo(StringBuffer stringBuffer, int i);

        int scan(String str, int i);
    }

    public PeriodFormatterBuilder() {
        clear();
    }

    public PeriodFormatter toFormatter() {
        PeriodFormatter formatter = toFormatter(this.iElementPairs, this.iNotPrinter, this.iNotParser);
        for (FieldFormatter fieldFormatter : this.iFieldFormatters) {
            if (fieldFormatter != null) {
                fieldFormatter.finish(this.iFieldFormatters);
            }
        }
        this.iFieldFormatters = (FieldFormatter[]) this.iFieldFormatters.clone();
        return formatter;
    }

    public void clear() {
        this.iMinPrintedDigits = 1;
        this.iPrintZeroSetting = 2;
        this.iMaxParsedDigits = 10;
        this.iRejectSignedValues = false;
        this.iPrefix = null;
        List<Object> list = this.iElementPairs;
        if (list == null) {
            this.iElementPairs = new ArrayList();
        } else {
            list.clear();
        }
        this.iNotPrinter = false;
        this.iNotParser = false;
        this.iFieldFormatters = new FieldFormatter[10];
    }

    public PeriodFormatterBuilder appendLiteral(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        clearPrefix();
        Literal literal = new Literal(str);
        append0(literal, literal);
        return this;
    }

    public PeriodFormatterBuilder appendYears() {
        appendField(0);
        return this;
    }

    public PeriodFormatterBuilder appendMonths() {
        appendField(1);
        return this;
    }

    public PeriodFormatterBuilder appendWeeks() {
        appendField(2);
        return this;
    }

    public PeriodFormatterBuilder appendDays() {
        appendField(3);
        return this;
    }

    public PeriodFormatterBuilder appendHours() {
        appendField(4);
        return this;
    }

    public PeriodFormatterBuilder appendMinutes() {
        appendField(5);
        return this;
    }

    public PeriodFormatterBuilder appendSecondsWithOptionalMillis() {
        appendField(9);
        return this;
    }

    private void appendField(int i) {
        appendField(i, this.iMinPrintedDigits);
    }

    private void appendField(int i, int i2) {
        FieldFormatter fieldFormatter = new FieldFormatter(i2, this.iPrintZeroSetting, this.iMaxParsedDigits, this.iRejectSignedValues, i, this.iFieldFormatters, this.iPrefix, null);
        append0(fieldFormatter, fieldFormatter);
        this.iFieldFormatters[i] = fieldFormatter;
        this.iPrefix = null;
    }

    public PeriodFormatterBuilder appendSuffix(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        return appendSuffix(new SimpleAffix(str));
    }

    private PeriodFormatterBuilder appendSuffix(PeriodFieldAffix periodFieldAffix) {
        Object obj;
        Object obj2 = null;
        if (this.iElementPairs.size() > 0) {
            obj2 = this.iElementPairs.get(r0.size() - 2);
            obj = this.iElementPairs.get(r0.size() - 1);
        } else {
            obj = null;
        }
        if (obj2 == null || obj == null || obj2 != obj || !(obj2 instanceof FieldFormatter)) {
            throw new IllegalStateException("No field to apply suffix to");
        }
        clearPrefix();
        FieldFormatter fieldFormatter = new FieldFormatter((FieldFormatter) obj2, periodFieldAffix);
        this.iElementPairs.set(r4.size() - 2, fieldFormatter);
        this.iElementPairs.set(r4.size() - 1, fieldFormatter);
        this.iFieldFormatters[fieldFormatter.getFieldType()] = fieldFormatter;
        return this;
    }

    public PeriodFormatterBuilder appendSeparatorIfFieldsAfter(String str) {
        return appendSeparator(str, str, null, false, true);
    }

    private PeriodFormatterBuilder appendSeparator(String str, String str2, String[] strArr, boolean z, boolean z2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException();
        }
        clearPrefix();
        List<Object> listSubList = this.iElementPairs;
        if (listSubList.size() == 0) {
            if (z2 && !z) {
                Literal literal = Literal.EMPTY;
                Separator separator = new Separator(str, str2, strArr, literal, literal, z, z2);
                append0(separator, separator);
            }
            return this;
        }
        Separator separator2 = null;
        int size = listSubList.size();
        while (true) {
            int i = size - 1;
            if (i < 0) {
                break;
            }
            if (listSubList.get(i) instanceof Separator) {
                separator2 = (Separator) listSubList.get(i);
                listSubList = listSubList.subList(i + 1, listSubList.size());
                break;
            }
            size = i - 1;
        }
        List<Object> list = listSubList;
        if (separator2 != null && list.size() == 0) {
            throw new IllegalStateException("Cannot have two adjacent separators");
        }
        Object[] objArrCreateComposite = createComposite(list);
        list.clear();
        Separator separator3 = new Separator(str, str2, strArr, (PeriodPrinter) objArrCreateComposite[0], (PeriodParser) objArrCreateComposite[1], z, z2);
        list.add(separator3);
        list.add(separator3);
        return this;
    }

    private void clearPrefix() throws IllegalStateException {
        if (this.iPrefix != null) {
            throw new IllegalStateException("Prefix not followed by field");
        }
        this.iPrefix = null;
    }

    private PeriodFormatterBuilder append0(PeriodPrinter periodPrinter, PeriodParser periodParser) {
        this.iElementPairs.add(periodPrinter);
        this.iElementPairs.add(periodParser);
        this.iNotPrinter = (periodPrinter == null) | this.iNotPrinter;
        this.iNotParser |= periodParser == null;
        return this;
    }

    private static PeriodFormatter toFormatter(List<Object> list, boolean z, boolean z2) {
        if (z && z2) {
            throw new IllegalStateException("Builder has created neither a printer nor a parser");
        }
        int size = list.size();
        if (size >= 2 && (list.get(0) instanceof Separator)) {
            Separator separator = (Separator) list.get(0);
            if (separator.iAfterParser == null && separator.iAfterPrinter == null) {
                PeriodFormatter formatter = toFormatter(list.subList(2, size), z, z2);
                Separator separatorFinish = separator.finish(formatter.getPrinter(), formatter.getParser());
                return new PeriodFormatter(separatorFinish, separatorFinish);
            }
        }
        Object[] objArrCreateComposite = createComposite(list);
        if (z) {
            return new PeriodFormatter(null, (PeriodParser) objArrCreateComposite[1]);
        }
        if (z2) {
            return new PeriodFormatter((PeriodPrinter) objArrCreateComposite[0], null);
        }
        return new PeriodFormatter((PeriodPrinter) objArrCreateComposite[0], (PeriodParser) objArrCreateComposite[1]);
    }

    private static Object[] createComposite(List<Object> list) {
        int size = list.size();
        if (size == 0) {
            Literal literal = Literal.EMPTY;
            return new Object[]{literal, literal};
        }
        if (size == 1) {
            return new Object[]{list.get(0), list.get(1)};
        }
        Composite composite = new Composite(list);
        return new Object[]{composite, composite};
    }

    static abstract class IgnorableAffix implements PeriodFieldAffix {
        private volatile String[] iOtherAffixes;

        IgnorableAffix() {
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public void finish(Set<PeriodFieldAffix> set) {
            if (this.iOtherAffixes == null) {
                int length = Integer.MAX_VALUE;
                String str = null;
                for (String str2 : getAffixes()) {
                    if (str2.length() < length) {
                        length = str2.length();
                        str = str2;
                    }
                }
                HashSet hashSet = new HashSet();
                for (PeriodFieldAffix periodFieldAffix : set) {
                    if (periodFieldAffix != null) {
                        for (String str3 : periodFieldAffix.getAffixes()) {
                            if (str3.length() > length || (str3.equalsIgnoreCase(str) && !str3.equals(str))) {
                                hashSet.add(str3);
                            }
                        }
                    }
                }
                this.iOtherAffixes = (String[]) hashSet.toArray(new String[hashSet.size()]);
            }
        }

        protected boolean matchesOtherAffix(int i, String str, int i2) {
            if (this.iOtherAffixes != null) {
                for (String str2 : this.iOtherAffixes) {
                    int length = str2.length();
                    if (i < length && str.regionMatches(true, i2, str2, 0, length)) {
                        return true;
                    }
                    if (i == length && str.regionMatches(false, i2, str2, 0, length)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    static class SimpleAffix extends IgnorableAffix {
        private final String iText;

        SimpleAffix(String str) {
            this.iText = str;
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int calculatePrintedLength(int i) {
            return this.iText.length();
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public void printTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append(this.iText);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int parse(String str, int i) {
            String str2 = this.iText;
            int length = str2.length();
            return (!str.regionMatches(true, i, str2, 0, length) || matchesOtherAffix(length, str, i)) ? ~i : i + length;
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int scan(String str, int i) {
            String str2 = this.iText;
            int length = str2.length();
            int length2 = str.length();
            for (int i2 = i; i2 < length2; i2++) {
                if (str.regionMatches(true, i2, str2, 0, length) && !matchesOtherAffix(length, str, i2)) {
                    return i2;
                }
                switch (str.charAt(i2)) {
                    case '+':
                    case ',':
                    case '-':
                    case '.':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        break;
                }
                return ~i;
            }
            return ~i;
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public String[] getAffixes() {
            return new String[]{this.iText};
        }
    }

    static class CompositeAffix extends IgnorableAffix {
        private final PeriodFieldAffix iLeft;
        private final String[] iLeftRightCombinations;
        private final PeriodFieldAffix iRight;

        CompositeAffix(PeriodFieldAffix periodFieldAffix, PeriodFieldAffix periodFieldAffix2) {
            this.iLeft = periodFieldAffix;
            this.iRight = periodFieldAffix2;
            HashSet hashSet = new HashSet();
            for (String str : periodFieldAffix.getAffixes()) {
                for (String str2 : this.iRight.getAffixes()) {
                    hashSet.add(str + str2);
                }
            }
            this.iLeftRightCombinations = (String[]) hashSet.toArray(new String[hashSet.size()]);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int calculatePrintedLength(int i) {
            return this.iLeft.calculatePrintedLength(i) + this.iRight.calculatePrintedLength(i);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public void printTo(StringBuffer stringBuffer, int i) {
            this.iLeft.printTo(stringBuffer, i);
            this.iRight.printTo(stringBuffer, i);
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int parse(String str, int i) {
            int i2 = this.iLeft.parse(str, i);
            return (i2 < 0 || (i2 = this.iRight.parse(str, i2)) < 0 || !matchesOtherAffix(parse(str, i2) - i2, str, i)) ? i2 : ~i;
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public int scan(String str, int i) {
            int iScan;
            int iScan2 = this.iLeft.scan(str, i);
            return (iScan2 < 0 || ((iScan = this.iRight.scan(str, this.iLeft.parse(str, iScan2))) >= 0 && matchesOtherAffix(this.iRight.parse(str, iScan) - iScan2, str, i))) ? ~i : iScan2 > 0 ? iScan2 : iScan;
        }

        @Override // org.joda.time.format.PeriodFormatterBuilder.PeriodFieldAffix
        public String[] getAffixes() {
            return (String[]) this.iLeftRightCombinations.clone();
        }
    }

    static class FieldFormatter implements PeriodPrinter, PeriodParser {
        private final FieldFormatter[] iFieldFormatters;
        private final int iFieldType;
        private final int iMaxParsedDigits;
        private final int iMinPrintedDigits;
        private final PeriodFieldAffix iPrefix;
        private final int iPrintZeroSetting;
        private final boolean iRejectSignedValues;
        private final PeriodFieldAffix iSuffix;

        FieldFormatter(int i, int i2, int i3, boolean z, int i4, FieldFormatter[] fieldFormatterArr, PeriodFieldAffix periodFieldAffix, PeriodFieldAffix periodFieldAffix2) {
            this.iMinPrintedDigits = i;
            this.iPrintZeroSetting = i2;
            this.iMaxParsedDigits = i3;
            this.iRejectSignedValues = z;
            this.iFieldType = i4;
            this.iFieldFormatters = fieldFormatterArr;
            this.iPrefix = periodFieldAffix;
            this.iSuffix = periodFieldAffix2;
        }

        FieldFormatter(FieldFormatter fieldFormatter, PeriodFieldAffix periodFieldAffix) {
            this.iMinPrintedDigits = fieldFormatter.iMinPrintedDigits;
            this.iPrintZeroSetting = fieldFormatter.iPrintZeroSetting;
            this.iMaxParsedDigits = fieldFormatter.iMaxParsedDigits;
            this.iRejectSignedValues = fieldFormatter.iRejectSignedValues;
            this.iFieldType = fieldFormatter.iFieldType;
            this.iFieldFormatters = fieldFormatter.iFieldFormatters;
            this.iPrefix = fieldFormatter.iPrefix;
            PeriodFieldAffix periodFieldAffix2 = fieldFormatter.iSuffix;
            this.iSuffix = periodFieldAffix2 != null ? new CompositeAffix(periodFieldAffix2, periodFieldAffix) : periodFieldAffix;
        }

        public void finish(FieldFormatter[] fieldFormatterArr) {
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            for (FieldFormatter fieldFormatter : fieldFormatterArr) {
                if (fieldFormatter != null && !equals(fieldFormatter)) {
                    hashSet.add(fieldFormatter.iPrefix);
                    hashSet2.add(fieldFormatter.iSuffix);
                }
            }
            PeriodFieldAffix periodFieldAffix = this.iPrefix;
            if (periodFieldAffix != null) {
                periodFieldAffix.finish(hashSet);
            }
            PeriodFieldAffix periodFieldAffix2 = this.iSuffix;
            if (periodFieldAffix2 != null) {
                periodFieldAffix2.finish(hashSet2);
            }
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            if (i <= 0) {
                return 0;
            }
            return (this.iPrintZeroSetting == 4 || getFieldValue(readablePeriod) != Long.MAX_VALUE) ? 1 : 0;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue == Long.MAX_VALUE) {
                return 0;
            }
            int iMax = Math.max(FormatUtils.calculateDigitCount(fieldValue), this.iMinPrintedDigits);
            if (this.iFieldType >= 8) {
                iMax = Math.max(iMax, fieldValue < 0 ? 5 : 4) + 1;
                if (this.iFieldType == 9 && Math.abs(fieldValue) % 1000 == 0) {
                    iMax -= 4;
                }
                fieldValue /= 1000;
            }
            int i = (int) fieldValue;
            PeriodFieldAffix periodFieldAffix = this.iPrefix;
            if (periodFieldAffix != null) {
                iMax += periodFieldAffix.calculatePrintedLength(i);
            }
            PeriodFieldAffix periodFieldAffix2 = this.iSuffix;
            return periodFieldAffix2 != null ? iMax + periodFieldAffix2.calculatePrintedLength(i) : iMax;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue == Long.MAX_VALUE) {
                return;
            }
            int i = (int) fieldValue;
            if (this.iFieldType >= 8) {
                i = (int) (fieldValue / 1000);
            }
            PeriodFieldAffix periodFieldAffix = this.iPrefix;
            if (periodFieldAffix != null) {
                periodFieldAffix.printTo(stringBuffer, i);
            }
            int length = stringBuffer.length();
            int i2 = this.iMinPrintedDigits;
            if (i2 <= 1) {
                FormatUtils.appendUnpaddedInteger(stringBuffer, i);
            } else {
                FormatUtils.appendPaddedInteger(stringBuffer, i, i2);
            }
            if (this.iFieldType >= 8) {
                int iAbs = (int) (Math.abs(fieldValue) % 1000);
                if (this.iFieldType == 8 || iAbs > 0) {
                    if (fieldValue < 0 && fieldValue > -1000) {
                        stringBuffer.insert(length, '-');
                    }
                    stringBuffer.append('.');
                    FormatUtils.appendPaddedInteger(stringBuffer, iAbs, 3);
                }
            }
            PeriodFieldAffix periodFieldAffix2 = this.iSuffix;
            if (periodFieldAffix2 != null) {
                periodFieldAffix2.printTo(stringBuffer, i);
            }
        }

        @Override // org.joda.time.format.PeriodParser
        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            int iScan;
            int iMin;
            int i2;
            PeriodFieldAffix periodFieldAffix;
            int i3;
            char cCharAt;
            int i4 = i;
            boolean z = this.iPrintZeroSetting == 4;
            if (i4 >= str.length()) {
                return z ? ~i4 : i4;
            }
            PeriodFieldAffix periodFieldAffix2 = this.iPrefix;
            if (periodFieldAffix2 != null) {
                i4 = periodFieldAffix2.parse(str, i4);
                if (i4 < 0) {
                    return !z ? ~i4 : i4;
                }
                z = true;
            }
            PeriodFieldAffix periodFieldAffix3 = this.iSuffix;
            int i5 = -1;
            if (periodFieldAffix3 == null || z) {
                iScan = -1;
            } else {
                iScan = periodFieldAffix3.scan(str, i4);
                if (iScan < 0) {
                    return !z ? ~iScan : iScan;
                }
                z = true;
            }
            if (!z && !isSupported(readWritablePeriod.getPeriodType(), this.iFieldType)) {
                return i4;
            }
            if (iScan > 0) {
                iMin = Math.min(this.iMaxParsedDigits, iScan - i4);
            } else {
                iMin = Math.min(this.iMaxParsedDigits, str.length() - i4);
            }
            int i6 = 0;
            boolean z2 = false;
            boolean z3 = false;
            while (i6 < iMin) {
                int i7 = i4 + i6;
                char cCharAt2 = str.charAt(i7);
                if (i6 != 0 || (!(cCharAt2 == '-' || cCharAt2 == '+') || this.iRejectSignedValues)) {
                    if (cCharAt2 >= '0' && cCharAt2 <= '9') {
                        z2 = true;
                    } else {
                        if ((cCharAt2 != '.' && cCharAt2 != ',') || (((i3 = this.iFieldType) != 8 && i3 != 9) || i5 >= 0)) {
                            break;
                        }
                        iMin = Math.min(iMin + 1, str.length() - i4);
                        i5 = i7 + 1;
                    }
                    i6++;
                } else {
                    z3 = cCharAt2 == '-';
                    int i8 = i6 + 1;
                    if (i8 >= iMin || (cCharAt = str.charAt(i7 + 1)) < '0' || cCharAt > '9') {
                        break;
                    }
                    if (z3) {
                        i6 = i8;
                    } else {
                        i4++;
                    }
                    iMin = Math.min(iMin + 1, str.length() - i4);
                }
            }
            if (!z2) {
                return ~i4;
            }
            if (iScan >= 0 && i4 + i6 != iScan) {
                return i4;
            }
            int i9 = this.iFieldType;
            if (i9 != 8 && i9 != 9) {
                setFieldValue(readWritablePeriod, i9, parseInt(str, i4, i6));
            } else if (i5 < 0) {
                setFieldValue(readWritablePeriod, 6, parseInt(str, i4, i6));
                setFieldValue(readWritablePeriod, 7, 0);
            } else {
                int i10 = parseInt(str, i4, (i5 - i4) - 1);
                setFieldValue(readWritablePeriod, 6, i10);
                int i11 = (i4 + i6) - i5;
                if (i11 <= 0) {
                    i2 = 0;
                } else {
                    if (i11 >= 3) {
                        i2 = parseInt(str, i5, 3);
                    } else {
                        int i12 = parseInt(str, i5, i11);
                        i2 = i11 == 1 ? i12 * 100 : i12 * 10;
                    }
                    if (z3 || i10 < 0) {
                        i2 = -i2;
                    }
                }
                setFieldValue(readWritablePeriod, 7, i2);
            }
            int i13 = i4 + i6;
            return (i13 < 0 || (periodFieldAffix = this.iSuffix) == null) ? i13 : periodFieldAffix.parse(str, i13);
        }

        private int parseInt(String str, int i, int i2) {
            if (i2 >= 10) {
                return Integer.parseInt(str.substring(i, i2 + i));
            }
            boolean z = false;
            if (i2 <= 0) {
                return 0;
            }
            int i3 = i + 1;
            char cCharAt = str.charAt(i);
            int i4 = i2 - 1;
            if (cCharAt == '-') {
                i4--;
                if (i4 < 0) {
                    return 0;
                }
                char cCharAt2 = str.charAt(i3);
                i3++;
                cCharAt = cCharAt2;
                z = true;
            }
            int i5 = cCharAt - '0';
            while (true) {
                int i6 = i4 - 1;
                if (i4 <= 0) {
                    break;
                }
                int iCharAt = (((i5 << 3) + (i5 << 1)) + str.charAt(i3)) - 48;
                i3++;
                i5 = iCharAt;
                i4 = i6;
            }
            return z ? -i5 : i5;
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0086  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        long getFieldValue(org.joda.time.ReadablePeriod r10) {
            /*
                Method dump skipped, instruction units count: 246
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.PeriodFormatterBuilder.FieldFormatter.getFieldValue(org.joda.time.ReadablePeriod):long");
        }

        boolean isZero(ReadablePeriod readablePeriod) {
            int size = readablePeriod.size();
            for (int i = 0; i < size; i++) {
                if (readablePeriod.getValue(i) != 0) {
                    return false;
                }
            }
            return true;
        }

        boolean isSupported(PeriodType periodType, int i) {
            switch (i) {
                case 0:
                    return periodType.isSupported(DurationFieldType.years());
                case 1:
                    return periodType.isSupported(DurationFieldType.months());
                case 2:
                    return periodType.isSupported(DurationFieldType.weeks());
                case 3:
                    return periodType.isSupported(DurationFieldType.days());
                case 4:
                    return periodType.isSupported(DurationFieldType.hours());
                case 5:
                    return periodType.isSupported(DurationFieldType.minutes());
                case 6:
                    return periodType.isSupported(DurationFieldType.seconds());
                case 7:
                    return periodType.isSupported(DurationFieldType.millis());
                case 8:
                case 9:
                    return periodType.isSupported(DurationFieldType.seconds()) || periodType.isSupported(DurationFieldType.millis());
                default:
                    return false;
            }
        }

        void setFieldValue(ReadWritablePeriod readWritablePeriod, int i, int i2) {
            switch (i) {
                case 0:
                    readWritablePeriod.setYears(i2);
                    break;
                case 1:
                    readWritablePeriod.setMonths(i2);
                    break;
                case 2:
                    readWritablePeriod.setWeeks(i2);
                    break;
                case 3:
                    readWritablePeriod.setDays(i2);
                    break;
                case 4:
                    readWritablePeriod.setHours(i2);
                    break;
                case 5:
                    readWritablePeriod.setMinutes(i2);
                    break;
                case 6:
                    readWritablePeriod.setSeconds(i2);
                    break;
                case 7:
                    readWritablePeriod.setMillis(i2);
                    break;
            }
        }

        int getFieldType() {
            return this.iFieldType;
        }
    }

    static class Literal implements PeriodPrinter, PeriodParser {
        static final Literal EMPTY = new Literal("");
        private final String iText;

        @Override // org.joda.time.format.PeriodPrinter
        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            return 0;
        }

        Literal(String str) {
            this.iText = str;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            return this.iText.length();
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            stringBuffer.append(this.iText);
        }

        @Override // org.joda.time.format.PeriodParser
        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            String str2 = this.iText;
            return str.regionMatches(true, i, str2, 0, str2.length()) ? i + this.iText.length() : ~i;
        }
    }

    static class Separator implements PeriodPrinter, PeriodParser {
        private volatile PeriodParser iAfterParser;
        private volatile PeriodPrinter iAfterPrinter;
        private final PeriodParser iBeforeParser;
        private final PeriodPrinter iBeforePrinter;
        private final String iFinalText;
        private final String[] iParsedForms;
        private final String iText;
        private final boolean iUseAfter;
        private final boolean iUseBefore;

        Separator(String str, String str2, String[] strArr, PeriodPrinter periodPrinter, PeriodParser periodParser, boolean z, boolean z2) {
            this.iText = str;
            this.iFinalText = str2;
            if ((str2 == null || str.equals(str2)) && (strArr == null || strArr.length == 0)) {
                this.iParsedForms = new String[]{str};
            } else {
                TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                treeSet.add(str);
                treeSet.add(str2);
                if (strArr != null) {
                    int length = strArr.length;
                    while (true) {
                        length--;
                        if (length < 0) {
                            break;
                        } else {
                            treeSet.add(strArr[length]);
                        }
                    }
                }
                ArrayList arrayList = new ArrayList(treeSet);
                Collections.reverse(arrayList);
                this.iParsedForms = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            this.iBeforePrinter = periodPrinter;
            this.iBeforeParser = periodParser;
            this.iUseBefore = z;
            this.iUseAfter = z2;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            int iCountFieldsToPrint = this.iBeforePrinter.countFieldsToPrint(readablePeriod, i, locale);
            return iCountFieldsToPrint < i ? iCountFieldsToPrint + this.iAfterPrinter.countFieldsToPrint(readablePeriod, i, locale) : iCountFieldsToPrint;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            int length;
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            int iCalculatePrintedLength = periodPrinter.calculatePrintedLength(readablePeriod, locale) + periodPrinter2.calculatePrintedLength(readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) <= 0) {
                    return iCalculatePrintedLength;
                }
                if (this.iUseAfter) {
                    int iCountFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                    if (iCountFieldsToPrint <= 0) {
                        return iCalculatePrintedLength;
                    }
                    length = (iCountFieldsToPrint > 1 ? this.iText : this.iFinalText).length();
                } else {
                    length = this.iText.length();
                }
            } else {
                if (!this.iUseAfter || periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) <= 0) {
                    return iCalculatePrintedLength;
                }
                length = this.iText.length();
            }
            return iCalculatePrintedLength + length;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            periodPrinter.printTo(stringBuffer, readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        int iCountFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                        if (iCountFieldsToPrint > 0) {
                            stringBuffer.append(iCountFieldsToPrint > 1 ? this.iText : this.iFinalText);
                        }
                    } else {
                        stringBuffer.append(this.iText);
                    }
                }
            } else if (this.iUseAfter && periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                stringBuffer.append(this.iText);
            }
            periodPrinter2.printTo(stringBuffer, readablePeriod, locale);
        }

        @Override // org.joda.time.format.PeriodParser
        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            int into = this.iBeforeParser.parseInto(readWritablePeriod, str, i, locale);
            if (into < 0) {
                return into;
            }
            int length = -1;
            boolean z = false;
            if (into > i) {
                String[] strArr = this.iParsedForms;
                int length2 = strArr.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    String str2 = strArr[i2];
                    if (str2 == null || str2.length() == 0 || str.regionMatches(true, into, str2, 0, str2.length())) {
                        length = str2 == null ? 0 : str2.length();
                        into += length;
                        z = true;
                    }
                }
            }
            int into2 = this.iAfterParser.parseInto(readWritablePeriod, str, into, locale);
            return into2 < 0 ? into2 : (z && into2 == into && length > 0) ? ~into : (into2 <= into || z || this.iUseBefore) ? into2 : ~into;
        }

        Separator finish(PeriodPrinter periodPrinter, PeriodParser periodParser) {
            this.iAfterPrinter = periodPrinter;
            this.iAfterParser = periodParser;
            return this;
        }
    }

    static class Composite implements PeriodPrinter, PeriodParser {
        private final PeriodParser[] iParsers;
        private final PeriodPrinter[] iPrinters;

        Composite(List<Object> list) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (arrayList.size() <= 0) {
                this.iPrinters = null;
            } else {
                this.iPrinters = (PeriodPrinter[]) arrayList.toArray(new PeriodPrinter[arrayList.size()]);
            }
            if (arrayList2.size() <= 0) {
                this.iParsers = null;
            } else {
                this.iParsers = (PeriodParser[]) arrayList2.toArray(new PeriodParser[arrayList2.size()]);
            }
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            PeriodPrinter[] periodPrinterArr = this.iPrinters;
            int length = periodPrinterArr.length;
            int iCountFieldsToPrint = 0;
            while (iCountFieldsToPrint < i) {
                length--;
                if (length < 0) {
                    break;
                }
                iCountFieldsToPrint += periodPrinterArr[length].countFieldsToPrint(readablePeriod, Integer.MAX_VALUE, locale);
            }
            return iCountFieldsToPrint;
        }

        @Override // org.joda.time.format.PeriodPrinter
        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            PeriodPrinter[] periodPrinterArr = this.iPrinters;
            int length = periodPrinterArr.length;
            int iCalculatePrintedLength = 0;
            while (true) {
                length--;
                if (length < 0) {
                    return iCalculatePrintedLength;
                }
                iCalculatePrintedLength += periodPrinterArr[length].calculatePrintedLength(readablePeriod, locale);
            }
        }

        @Override // org.joda.time.format.PeriodPrinter
        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            for (PeriodPrinter periodPrinter : this.iPrinters) {
                periodPrinter.printTo(stringBuffer, readablePeriod, locale);
            }
        }

        @Override // org.joda.time.format.PeriodParser
        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            PeriodParser[] periodParserArr = this.iParsers;
            if (periodParserArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = periodParserArr.length;
            for (int i2 = 0; i2 < length && i >= 0; i2++) {
                i = periodParserArr[i2].parseInto(readWritablePeriod, str, i, locale);
            }
            return i;
        }

        private void decompose(List<Object> list, List<Object> list2, List<Object> list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof PeriodPrinter) {
                    if (obj instanceof Composite) {
                        addArrayToList(list2, ((Composite) obj).iPrinters);
                    } else {
                        list2.add(obj);
                    }
                }
                Object obj2 = list.get(i + 1);
                if (obj2 instanceof PeriodParser) {
                    if (obj2 instanceof Composite) {
                        addArrayToList(list3, ((Composite) obj2).iParsers);
                    } else {
                        list3.add(obj2);
                    }
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
}
