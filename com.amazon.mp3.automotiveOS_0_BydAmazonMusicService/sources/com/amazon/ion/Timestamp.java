package com.amazon.ion;

import com.amazon.ion.util.IonTextUtils;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes3.dex */
public final class Timestamp implements Comparable<Timestamp>, Cloneable {
    private byte _day;
    private BigDecimal _fraction;
    private byte _hour;
    private byte _minute;
    private byte _month;
    private Integer _offset;
    private Precision _precision;
    private byte _second;
    private short _year;
    private static final BigDecimal NO_FRACTIONAL_SECONDS = null;
    static final BigDecimal MINIMUM_TIMESTAMP_IN_MILLIS_DECIMAL = new BigDecimal(-62135769600000L);
    static final BigDecimal MAXIMUM_ALLOWED_TIMESTAMP_IN_MILLIS_DECIMAL = new BigDecimal(253402300800000L);
    public static final Integer UNKNOWN_OFFSET = null;
    public static final Integer UTC_OFFSET = 0;
    private static final int HASH_SIGNATURE = 1021414227;
    private static final int[] LEAP_DAYS_IN_MONTH = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] NORMAL_DAYS_IN_MONTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static final int LEN_OF_NULL_IMAGE = 14;

    private static boolean isValidFollowChar(char c) {
        if (c == '\t' || c == '\n' || c == '\r' || c == '\"' || c == ',' || c == '{' || c == '}') {
            return true;
        }
        switch (c) {
            case '\'':
            case '(':
            case ')':
                return true;
            default:
                switch (c) {
                    case '[':
                    case '\\':
                    case ']':
                        return true;
                    default:
                        return false;
                }
        }
    }

    public enum Precision {
        YEAR(1),
        MONTH(3),
        DAY(7),
        MINUTE(15),
        SECOND(31),
        FRACTION(31);

        private final int flags;

        Precision(int i) {
            this.flags = i;
        }

        public boolean includes(Precision precision) {
            switch (AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision[precision.ordinal()]) {
                case 1:
                case 2:
                    return (this.flags & 16) != 0;
                case 3:
                    return (this.flags & 8) != 0;
                case 4:
                    return (this.flags & 4) != 0;
                case 5:
                    return (this.flags & 2) != 0;
                case 6:
                    return (this.flags & 1) != 0;
                default:
                    throw new IllegalStateException("unrecognized precision" + precision);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean alwaysUnknownOffset() {
            return ordinal() <= DAY.ordinal();
        }
    }

    /* JADX INFO: renamed from: com.amazon.ion.Timestamp$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$Timestamp$Precision;

        static {
            int[] iArr = new int[Precision.values().length];
            $SwitchMap$com$amazon$ion$Timestamp$Precision = iArr;
            try {
                iArr[Precision.FRACTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Precision.SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Precision.MINUTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Precision.DAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Precision.MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$Timestamp$Precision[Precision.YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static int last_day_in_month(int i, int i2) {
        boolean z = true;
        if (i % 4 != 0 || (i % 100 == 0 && i % 400 != 0)) {
            z = false;
        }
        return z ? LEAP_DAYS_IN_MONTH[i2] : NORMAL_DAYS_IN_MONTH[i2];
    }

    private void apply_offset(int i) {
        if (i == 0) {
            return;
        }
        if (i < -1440 || i > 1440) {
            throw new IllegalArgumentException("bad offset " + i);
        }
        int i2 = -i;
        int i3 = i2 / 60;
        int i4 = i2 - (i3 * 60);
        if (i2 < 0) {
            byte b = (byte) (this._minute + i4);
            this._minute = b;
            byte b2 = (byte) (this._hour + i3);
            this._hour = b2;
            if (b < 0) {
                this._minute = (byte) (b + 60);
                this._hour = (byte) (b2 - 1);
            }
            byte b3 = this._hour;
            if (b3 >= 0) {
                return;
            }
            this._hour = (byte) (b3 + 24);
            byte b4 = (byte) (this._day - 1);
            this._day = b4;
            if (b4 >= 1) {
                return;
            }
            byte b5 = (byte) (this._month - 1);
            this._month = b5;
            if (b5 >= 1) {
                this._day = (byte) (b4 + last_day_in_month(this._year, b5));
                return;
            }
            byte b6 = (byte) (b5 + MqttWireMessage.MESSAGE_TYPE_PINGREQ);
            this._month = b6;
            short s = (short) (this._year - 1);
            this._year = s;
            if (s < 1) {
                throw new IllegalArgumentException("year is less than 1");
            }
            this._day = (byte) (b4 + last_day_in_month(s, b6));
            return;
        }
        byte b7 = (byte) (this._minute + i4);
        this._minute = b7;
        byte b8 = (byte) (this._hour + i3);
        this._hour = b8;
        if (b7 > 59) {
            this._minute = (byte) (b7 + CBORConstants.BYTE_TAG_DECIMAL_FRACTION);
            this._hour = (byte) (b8 + 1);
        }
        byte b9 = this._hour;
        if (b9 < 24) {
            return;
        }
        this._hour = (byte) (b9 - 24);
        byte b10 = (byte) (this._day + 1);
        this._day = b10;
        if (b10 <= last_day_in_month(this._year, this._month)) {
            return;
        }
        this._day = (byte) 1;
        byte b11 = (byte) (this._month + 1);
        this._month = b11;
        if (b11 <= 12) {
            return;
        }
        this._month = (byte) (b11 - MqttWireMessage.MESSAGE_TYPE_PINGREQ);
        short s2 = (short) (this._year + 1);
        this._year = s2;
        if (s2 > 9999) {
            throw new IllegalArgumentException("year exceeds 9999");
        }
    }

    private static byte requireByte(int i, String str) {
        if (i > 127 || i < -128) {
            throw new IllegalArgumentException(String.format("%s of %d is out of range.", str, Integer.valueOf(i)));
        }
        return (byte) i;
    }

    private static short requireShort(int i, String str) {
        if (i > 32767 || i < -32768) {
            throw new IllegalArgumentException(String.format("%s of %d is out of range.", str, Integer.valueOf(i)));
        }
        return (short) i;
    }

    private void set_fields_from_millis(long j) {
        if (j < -62135769600000L) {
            throw new IllegalArgumentException("year is less than 1");
        }
        Date date = new Date(j);
        this._minute = requireByte(date.getMinutes(), "Minute");
        this._second = requireByte(date.getSeconds(), "Second");
        this._hour = requireByte(date.getHours(), "Hour");
        this._day = requireByte(date.getDate(), "Day");
        this._month = requireByte(date.getMonth() + 1, "Month");
        int i = -date.getTimezoneOffset();
        if (i < 0 && (-62135769600000L) - ((long) i) > j) {
            this._year = (short) 0;
        } else {
            this._year = requireShort(date.getYear() + 1900, "Year");
        }
        apply_offset(i);
        this._year = checkAndCastYear(this._year);
        byte bCheckAndCastMonth = checkAndCastMonth(this._month);
        this._month = bCheckAndCastMonth;
        this._day = checkAndCastDay(this._day, this._year, bCheckAndCastMonth);
        this._hour = checkAndCastHour(this._hour);
        this._minute = checkAndCastMinute(this._minute);
        this._second = checkAndCastSecond(this._second);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0066 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private Timestamp(com.amazon.ion.Timestamp.Precision r4, int r5, int r6, int r7, int r8, int r9, int r10, java.math.BigDecimal r11, java.lang.Integer r12, boolean r13) {
        /*
            r3 = this;
            r3.<init>()
            r0 = 1
            r3._month = r0
            r3._day = r0
            int[] r1 = com.amazon.ion.Timestamp.AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision
            int r2 = r4.ordinal()
            r1 = r1[r2]
            r2 = 0
            switch(r1) {
                case 1: goto L1e;
                case 2: goto L1e;
                case 3: goto L39;
                case 4: goto L47;
                case 5: goto L1c;
                case 6: goto L4e;
                default: goto L14;
            }
        L14:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "invalid Precision passed to constructor"
            r4.<init>(r5)
            throw r4
        L1c:
            r0 = r2
            goto L47
        L1e:
            if (r11 == 0) goto L30
            java.math.BigDecimal r1 = java.math.BigDecimal.ZERO
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L29
            goto L30
        L29:
            java.math.BigDecimal r11 = r11.abs()
            r3._fraction = r11
            goto L33
        L30:
            r11 = 0
            r3._fraction = r11
        L33:
            byte r10 = checkAndCastSecond(r10)
            r3._second = r10
        L39:
            byte r9 = checkAndCastMinute(r9)
            r3._minute = r9
            byte r8 = checkAndCastHour(r8)
            r3._hour = r8
            r3._offset = r12
        L47:
            byte r8 = checkAndCastMonth(r6)
            r3._month = r8
            r2 = r0
        L4e:
            short r8 = checkAndCastYear(r5)
            r3._year = r8
            if (r2 == 0) goto L5c
            byte r5 = checkAndCastDay(r7, r5, r6)
            r3._day = r5
        L5c:
            java.math.BigDecimal r5 = r3._fraction
            com.amazon.ion.Timestamp$Precision r4 = checkFraction(r4, r5)
            r3._precision = r4
            if (r13 == 0) goto L6f
            if (r12 == 0) goto L6f
            int r4 = r12.intValue()
            r3.apply_offset(r4)
        L6f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.Timestamp.<init>(com.amazon.ion.Timestamp$Precision, int, int, int, int, int, int, java.math.BigDecimal, java.lang.Integer, boolean):void");
    }

    @Deprecated
    public static Timestamp createFromUtcFields(Precision precision, int i, int i2, int i3, int i4, int i5, int i6, BigDecimal bigDecimal, Integer num) {
        return new Timestamp(precision, i, i2, i3, i4, i5, i6, bigDecimal, num, false);
    }

    @Deprecated
    public Timestamp(BigDecimal bigDecimal, Integer num) {
        this._month = (byte) 1;
        this._day = (byte) 1;
        if (bigDecimal == null) {
            throw new NullPointerException("millis is null");
        }
        if (bigDecimal.compareTo(MINIMUM_TIMESTAMP_IN_MILLIS_DECIMAL) < 0 || MAXIMUM_ALLOWED_TIMESTAMP_IN_MILLIS_DECIMAL.compareTo(bigDecimal) < 0) {
            throwTimestampOutOfRangeError(bigDecimal);
        }
        set_fields_from_millis(isIntegralZero(bigDecimal) ? 0L : bigDecimal.longValue());
        if (bigDecimal.scale() <= -3) {
            this._precision = Precision.SECOND;
            this._fraction = null;
        } else {
            BigDecimal bigDecimalMovePointLeft = bigDecimal.movePointLeft(3);
            BigDecimal bigDecimalSubtract = bigDecimalMovePointLeft.subtract(fastRoundZeroFloor(bigDecimalMovePointLeft));
            this._fraction = bigDecimalSubtract;
            this._precision = checkFraction(Precision.SECOND, bigDecimalSubtract);
        }
        this._offset = num;
    }

    private BigDecimal fastRoundZeroFloor(BigDecimal bigDecimal) {
        return isIntegralZero(bigDecimal) ? bigDecimal.signum() < 0 ? BigDecimal.ONE.negate() : BigDecimal.ZERO : bigDecimal.setScale(0, RoundingMode.FLOOR);
    }

    private boolean isIntegralZero(BigDecimal bigDecimal) {
        return bigDecimal.signum() == 0 || bigDecimal.scale() < -63 || bigDecimal.precision() - bigDecimal.scale() <= 0;
    }

    private static void throwTimestampOutOfRangeError(Number number) {
        throw new IllegalArgumentException("millis: " + number + " is outside of valid the range: from -62135769600000 (0001T), inclusive, to 253402300800000 (10000T) , exclusive");
    }

    @Deprecated
    public Timestamp(long j, Integer num) {
        this._month = (byte) 1;
        this._day = (byte) 1;
        if (j < -62135769600000L || j >= 253402300800000L) {
            throwTimestampOutOfRangeError(Long.valueOf(j));
        }
        set_fields_from_millis(j);
        BigDecimal bigDecimalMovePointLeft = BigDecimal.valueOf(j).movePointLeft(3);
        BigDecimal bigDecimalSubtract = bigDecimalMovePointLeft.subtract(bigDecimalMovePointLeft.setScale(0, RoundingMode.FLOOR));
        this._fraction = bigDecimalSubtract;
        this._precision = checkFraction(Precision.SECOND, bigDecimalSubtract);
        this._offset = num;
    }

    private static IllegalArgumentException fail(CharSequence charSequence, String str) {
        return new IllegalArgumentException("invalid timestamp: " + str + ": " + ((Object) IonTextUtils.printString(charSequence)));
    }

    private static IllegalArgumentException fail(CharSequence charSequence) {
        return new IllegalArgumentException("invalid timestamp: " + ((Object) IonTextUtils.printString(charSequence)));
    }

    public static Timestamp valueOf(CharSequence charSequence) {
        int i;
        Precision precision;
        int i2;
        int i3;
        int i4;
        int i5;
        BigDecimal bigDecimal;
        Integer numValueOf;
        Integer num;
        int i6;
        int length = charSequence.length();
        if (length == 0) {
            throw fail(charSequence);
        }
        if (charSequence.charAt(0) == 'n') {
            int i7 = LEN_OF_NULL_IMAGE;
            if (length >= i7 && "null.timestamp".contentEquals(charSequence.subSequence(0, i7))) {
                if (length <= i7 || isValidFollowChar(charSequence.charAt(i7))) {
                    return null;
                }
                throw fail(charSequence);
            }
            throw fail(charSequence);
        }
        if (length < 5) {
            throw fail(charSequence, "year is too short (must be at least yyyyT)");
        }
        Precision precision2 = Precision.YEAR;
        int i8 = read_digits(charSequence, 0, 4, -1, "year");
        char cCharAt = charSequence.charAt(4);
        int i9 = 7;
        if (cCharAt == 'T') {
            i5 = 0;
            i3 = 0;
            i4 = 0;
            precision = precision2;
            i = 1;
            i2 = 1;
            bigDecimal = null;
            i9 = 4;
        } else {
            if (cCharAt != '-') {
                throw fail(charSequence, "expected \"-\" between year and month, found " + IonTextUtils.printCodePointAsString(cCharAt));
            }
            if (length < 8) {
                throw fail(charSequence, "month is too short (must be yyyy-mmT)");
            }
            Precision precision3 = Precision.MONTH;
            i = read_digits(charSequence, 5, 2, -1, "month");
            char cCharAt2 = charSequence.charAt(7);
            if (cCharAt2 == 'T') {
                precision = precision3;
                i2 = 1;
            } else {
                if (cCharAt2 != '-') {
                    throw fail(charSequence, "expected \"-\" between month and day, found " + IonTextUtils.printCodePointAsString(cCharAt2));
                }
                if (length < 10) {
                    throw fail(charSequence, "too short for yyyy-mm-dd");
                }
                Precision precision4 = Precision.DAY;
                int i10 = read_digits(charSequence, 8, 2, -1, "day");
                if (length != 10) {
                    char cCharAt3 = charSequence.charAt(10);
                    if (cCharAt3 != 'T') {
                        throw fail(charSequence, "expected \"T\" after day, found " + IonTextUtils.printCodePointAsString(cCharAt3));
                    }
                    if (length != 11) {
                        if (length < 16) {
                            throw fail(charSequence, "too short for yyyy-mm-ddThh:mm");
                        }
                        int i11 = read_digits(charSequence, 11, 2, 58, "hour");
                        int i12 = read_digits(charSequence, 14, 2, -1, "minutes");
                        Precision precision5 = Precision.MINUTE;
                        if (length <= 16 || charSequence.charAt(16) != ':') {
                            i3 = i11;
                            i4 = i12;
                            precision = precision5;
                            i9 = 16;
                            i5 = 0;
                            i2 = i10;
                            bigDecimal = null;
                        } else {
                            if (length < 19) {
                                throw fail(charSequence, "too short for yyyy-mm-ddThh:mm:ss");
                            }
                            int i13 = read_digits(charSequence, 17, 2, -1, "seconds");
                            precision = Precision.SECOND;
                            if (length <= 19 || charSequence.charAt(19) != '.') {
                                i2 = i10;
                                i4 = i12;
                                bigDecimal = null;
                                i3 = i11;
                                i5 = i13;
                                i9 = 19;
                            } else {
                                int i14 = 20;
                                while (length > i14 && Character.isDigit(charSequence.charAt(i14))) {
                                    i14++;
                                }
                                if (i14 <= 20) {
                                    throw fail(charSequence, "must have at least one digit after decimal point");
                                }
                                BigDecimal bigDecimal2 = new BigDecimal(charSequence.subSequence(19, i14).toString());
                                i3 = i11;
                                i5 = i13;
                                i9 = i14;
                                i4 = i12;
                                i2 = i10;
                                bigDecimal = bigDecimal2;
                            }
                        }
                    }
                }
                precision = precision4;
                i2 = i10;
                i9 = 10;
            }
            i5 = 0;
            bigDecimal = null;
            i3 = 0;
            i4 = 0;
        }
        char cCharAt4 = i9 < length ? charSequence.charAt(i9) : '\n';
        if (cCharAt4 == 'Z') {
            numValueOf = 0;
            i9++;
        } else {
            if (cCharAt4 != '+' && cCharAt4 != '-') {
                int i15 = AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision[precision.ordinal()];
                if (i15 != 4 && i15 != 5 && i15 != 6) {
                    throw fail(charSequence, "missing local offset");
                }
                num = null;
                i6 = i9 + 1;
                if (length <= i6 && !isValidFollowChar(charSequence.charAt(i6))) {
                    throw fail(charSequence, "invalid excess characters");
                }
                return new Timestamp(precision, i8, i, i2, i3, i4, i5, bigDecimal, num, true);
            }
            if (length < i9 + 5) {
                throw fail(charSequence, "local offset too short");
            }
            int i16 = i9 + 1;
            int i17 = read_digits(charSequence, i16, 2, 58, "local offset hours");
            if (i17 < 0 || i17 > 23) {
                throw fail(charSequence, "local offset hours must be between 0 and 23 inclusive");
            }
            int i18 = i16 + 3;
            int i19 = read_digits(charSequence, i18, 2, -1, "local offset minutes");
            if (i19 > 59) {
                throw fail(charSequence, "local offset minutes must be between 0 and 59 inclusive");
            }
            i9 = i18 + 2;
            int i20 = (i17 * 60) + i19;
            if (cCharAt4 == '-') {
                i20 = -i20;
            }
            numValueOf = (i20 == 0 && cCharAt4 == '-') ? null : Integer.valueOf(i20);
        }
        num = numValueOf;
        i6 = i9 + 1;
        if (length <= i6) {
        }
        return new Timestamp(precision, i8, i, i2, i3, i4, i5, bigDecimal, num, true);
    }

    private static int read_digits(CharSequence charSequence, int i, int i2, int i3, String str) {
        int i4 = i + i2;
        if (charSequence.length() < i4) {
            throw fail(charSequence, str + " requires " + i2 + " digits");
        }
        int i5 = 0;
        while (i < i4) {
            char cCharAt = charSequence.charAt(i);
            if (!Character.isDigit(cCharAt)) {
                throw fail(charSequence, str + " has non-digit character " + IonTextUtils.printCodePointAsString(cCharAt));
            }
            i5 = (i5 * 10) + (cCharAt - '0');
            i++;
        }
        if (i3 != -1) {
            if (i >= charSequence.length() || charSequence.charAt(i) != i3) {
                throw fail(charSequence, str + " should end with " + IonTextUtils.printCodePointAsString(i3));
            }
        } else if (i < charSequence.length() && Character.isDigit(charSequence.charAt(i))) {
            throw fail(charSequence, str + " requires " + i2 + " digits but has more");
        }
        return i5;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Timestamp m102clone() {
        return new Timestamp(this._precision, this._year, this._month, this._day, this._hour, this._minute, this._second, this._fraction, this._offset, false);
    }

    private Timestamp make_localtime() {
        Integer num = this._offset;
        int iIntValue = num != null ? num.intValue() : 0;
        Timestamp timestamp = new Timestamp(this._precision, this._year, this._month, this._day, this._hour, this._minute, this._second, this._fraction, this._offset, false);
        timestamp.apply_offset(-iIntValue);
        return timestamp;
    }

    public static Timestamp forMillis(long j, Integer num) {
        return new Timestamp(j, num);
    }

    public Date dateValue() {
        return new Date(getMillis());
    }

    public long getMillis() {
        long jUTC = Date.UTC(this._year - 1900, this._month - 1, this._day, this._hour, this._minute, this._second);
        BigDecimal bigDecimal = this._fraction;
        if (bigDecimal == null) {
            return jUTC;
        }
        BigDecimal bigDecimalMovePointRight = bigDecimal.movePointRight(3);
        return jUTC + ((long) (isIntegralZero(bigDecimalMovePointRight) ? 0 : bigDecimalMovePointRight.intValue()));
    }

    public BigDecimal getDecimalMillis() {
        switch (AnonymousClass1.$SwitchMap$com$amazon$ion$Timestamp$Precision[this._precision.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                BigDecimal bigDecimalValueOf = BigDecimal.valueOf(Date.UTC(this._year - 1900, this._month - 1, this._day, this._hour, this._minute, this._second));
                BigDecimal bigDecimal = this._fraction;
                return bigDecimal != null ? bigDecimalValueOf.add(bigDecimal.movePointRight(3)) : bigDecimalValueOf;
            default:
                throw new IllegalArgumentException();
        }
    }

    public Precision getPrecision() {
        return this._precision;
    }

    public Integer getLocalOffset() {
        return this._offset;
    }

    public int getYear() {
        Integer num = this._offset;
        return ((num == null || num.intValue() == 0) ? this : make_localtime())._year;
    }

    public int getMonth() {
        Integer num = this._offset;
        return ((num == null || num.intValue() == 0) ? this : make_localtime())._month;
    }

    public int getDay() {
        Integer num = this._offset;
        return ((num == null || num.intValue() == 0) ? this : make_localtime())._day;
    }

    public int getHour() {
        Integer num = this._offset;
        return ((num == null || num.intValue() == 0) ? this : make_localtime())._hour;
    }

    public int getMinute() {
        Integer num = this._offset;
        return ((num == null || num.intValue() == 0) ? this : make_localtime())._minute;
    }

    public int getSecond() {
        return this._second;
    }

    public int getZYear() {
        return this._year;
    }

    public int getZMonth() {
        return this._month;
    }

    public int getZDay() {
        return this._day;
    }

    public int getZHour() {
        return this._hour;
    }

    public int getZMinute() {
        return this._minute;
    }

    public int getZSecond() {
        return this._second;
    }

    @Deprecated
    public BigDecimal getZFractionalSecond() {
        return this._fraction;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        try {
            print(sb);
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Exception printing to StringBuilder", e);
        }
    }

    public void print(Appendable appendable) throws IOException {
        Integer num = this._offset;
        print(appendable, (num == null || num.intValue() == 0) ? this : make_localtime());
    }

    private static void print(Appendable appendable, Timestamp timestamp) throws IOException {
        if (timestamp == null) {
            appendable.append("null.timestamp");
            return;
        }
        print_digits(appendable, timestamp._year, 4);
        if (timestamp._precision == Precision.YEAR) {
            appendable.append("T");
            return;
        }
        appendable.append("-");
        print_digits(appendable, timestamp._month, 2);
        if (timestamp._precision == Precision.MONTH) {
            appendable.append("T");
            return;
        }
        appendable.append("-");
        print_digits(appendable, timestamp._day, 2);
        if (timestamp._precision == Precision.DAY) {
            return;
        }
        appendable.append("T");
        print_digits(appendable, timestamp._hour, 2);
        appendable.append(":");
        print_digits(appendable, timestamp._minute, 2);
        if (timestamp._precision == Precision.SECOND) {
            appendable.append(":");
            print_digits(appendable, timestamp._second, 2);
            BigDecimal bigDecimal = timestamp._fraction;
            if (bigDecimal != null) {
                print_fractional_digits(appendable, bigDecimal);
            }
        }
        Integer num = timestamp._offset;
        if (num != UNKNOWN_OFFSET) {
            int iIntValue = num.intValue();
            if (iIntValue == 0) {
                appendable.append('Z');
                return;
            }
            if (iIntValue < 0) {
                iIntValue = -iIntValue;
                appendable.append('-');
            } else {
                appendable.append('+');
            }
            int i = iIntValue / 60;
            print_digits(appendable, i, 2);
            appendable.append(":");
            print_digits(appendable, iIntValue - (i * 60), 2);
            return;
        }
        appendable.append("-00:00");
    }

    private static void print_digits(Appendable appendable, int i, int i2) throws IOException {
        char[] cArr = new char[i2];
        int i3 = i2;
        while (i3 > 0) {
            i3--;
            int i4 = i / 10;
            cArr[i3] = (char) ((i - (i4 * 10)) + 48);
            i = i4;
        }
        while (i3 > 0) {
            i3--;
            cArr[i3] = '0';
        }
        for (int i5 = 0; i5 < i2; i5++) {
            appendable.append(cArr[i5]);
        }
    }

    private static void print_fractional_digits(Appendable appendable, BigDecimal bigDecimal) throws IOException {
        String plainString = bigDecimal.toPlainString();
        if (plainString.charAt(0) == '0') {
            plainString = plainString.substring(1);
        }
        appendable.append(plainString);
    }

    public int hashCode() {
        int i = HASH_SIGNATURE * 8191;
        BigDecimal bigDecimal = this._fraction;
        int iHashCode = i + (bigDecimal != null ? bigDecimal.hashCode() : 0);
        int i2 = ((((((((((((iHashCode ^ ((iHashCode << 19) ^ (iHashCode >> 13))) * 8191) + this._year) * 8191) + this._month) * 8191) + this._day) * 8191) + this._hour) * 8191) + this._minute) * 8191) + this._second;
        int i3 = i2 ^ ((i2 << 19) ^ (i2 >> 13));
        Precision precision = this._precision;
        if (precision == Precision.FRACTION) {
            precision = Precision.SECOND;
        }
        int iHashCode2 = (i3 * 8191) + precision.toString().hashCode();
        int i4 = (iHashCode2 ^ ((iHashCode2 << 19) ^ (iHashCode2 >> 13))) * 8191;
        Integer num = this._offset;
        int iHashCode3 = i4 + (num != null ? num.hashCode() : 0);
        return iHashCode3 ^ ((iHashCode3 << 19) ^ (iHashCode3 >> 13));
    }

    @Override // java.lang.Comparable
    public int compareTo(Timestamp timestamp) {
        long millis = getMillis();
        long millis2 = timestamp.getMillis();
        if (millis != millis2) {
            return millis < millis2 ? -1 : 1;
        }
        BigDecimal bigDecimal = this._fraction;
        if (bigDecimal == null) {
            bigDecimal = BigDecimal.ZERO;
        }
        BigDecimal bigDecimal2 = timestamp._fraction;
        if (bigDecimal2 == null) {
            bigDecimal2 = BigDecimal.ZERO;
        }
        return bigDecimal.compareTo(bigDecimal2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Timestamp) {
            return equals((Timestamp) obj);
        }
        return false;
    }

    public boolean equals(Timestamp timestamp) {
        if (this == timestamp) {
            return true;
        }
        if (timestamp == null) {
            return false;
        }
        Precision precision = this._precision;
        Precision precision2 = Precision.SECOND;
        Precision precision3 = precision.includes(precision2) ? precision2 : this._precision;
        if (!timestamp._precision.includes(precision2)) {
            precision2 = timestamp._precision;
        }
        if (precision3 != precision2) {
            return false;
        }
        Integer num = this._offset;
        if (num == null) {
            if (timestamp._offset != null) {
                return false;
            }
        } else if (timestamp._offset == null) {
            return false;
        }
        if (this._year != timestamp._year || this._month != timestamp._month || this._day != timestamp._day || this._hour != timestamp._hour || this._minute != timestamp._minute || this._second != timestamp._second) {
            return false;
        }
        if (num != null && num.intValue() != timestamp._offset.intValue()) {
            return false;
        }
        BigDecimal bigDecimal = this._fraction;
        if ((bigDecimal != null && timestamp._fraction == null) || (bigDecimal == null && timestamp._fraction != null)) {
            return false;
        }
        if (bigDecimal == null && timestamp._fraction == null) {
            return true;
        }
        return bigDecimal.equals(timestamp._fraction);
    }

    private static short checkAndCastYear(int i) {
        if (i < 1 || i > 9999) {
            throw new IllegalArgumentException(String.format("Year %s must be between 1 and 9999 inclusive", Integer.valueOf(i)));
        }
        return (short) i;
    }

    private static byte checkAndCastMonth(int i) {
        if (i < 1 || i > 12) {
            throw new IllegalArgumentException(String.format("Month %s must be between 1 and 12 inclusive", Integer.valueOf(i)));
        }
        return (byte) i;
    }

    private static byte checkAndCastDay(int i, int i2, int i3) {
        int iLast_day_in_month = last_day_in_month(i2, i3);
        if (i < 1 || i > iLast_day_in_month) {
            throw new IllegalArgumentException(String.format("Day %s for year %s and month %s must be between 1 and %s inclusive", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(iLast_day_in_month)));
        }
        return (byte) i;
    }

    private static byte checkAndCastHour(int i) {
        if (i < 0 || i > 23) {
            throw new IllegalArgumentException(String.format("Hour %s must be between 0 and 23 inclusive", Integer.valueOf(i)));
        }
        return (byte) i;
    }

    private static byte checkAndCastMinute(int i) {
        if (i < 0 || i > 59) {
            throw new IllegalArgumentException(String.format("Minute %s must be between between 0 and 59 inclusive", Integer.valueOf(i)));
        }
        return (byte) i;
    }

    private static byte checkAndCastSecond(int i) {
        if (i < 0 || i > 59) {
            throw new IllegalArgumentException(String.format("Second %s must be between between 0 and 59 inclusive", Integer.valueOf(i)));
        }
        return (byte) i;
    }

    private static Precision checkFraction(Precision precision, BigDecimal bigDecimal) {
        if (precision.includes(Precision.SECOND)) {
            if (bigDecimal != null && (bigDecimal.signum() == -1 || BigDecimal.ONE.compareTo(bigDecimal) != 1)) {
                throw new IllegalArgumentException(String.format("Fractional seconds %s must be greater than or equal to 0 and less than 1", bigDecimal));
            }
        } else if (bigDecimal != null) {
            throw new IllegalArgumentException("Fraction must be null for non-second precision: " + bigDecimal);
        }
        return precision;
    }
}
