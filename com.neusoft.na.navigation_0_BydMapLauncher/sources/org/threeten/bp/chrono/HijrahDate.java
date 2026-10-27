package org.threeten.bp.chrono;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.io.IOUtils;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;

/* JADX INFO: loaded from: classes4.dex */
public final class HijrahDate extends ChronoDateImpl<HijrahDate> implements Serializable {
    private static final Long[] ADJUSTED_CYCLES;
    private static final HashMap<Integer, Integer[]> ADJUSTED_CYCLE_YEARS;
    private static final Integer[] ADJUSTED_LEAST_MAX_VALUES;
    private static final Integer[] ADJUSTED_MAX_VALUES;
    private static final Integer[] ADJUSTED_MIN_VALUES;
    private static final HashMap<Integer, Integer[]> ADJUSTED_MONTH_DAYS;
    private static final HashMap<Integer, Integer[]> ADJUSTED_MONTH_LENGTHS;
    private static final String DEFAULT_CONFIG_FILENAME = "hijrah_deviation.cfg";
    private static final String DEFAULT_CONFIG_PATH;
    private static final Integer[] DEFAULT_CYCLE_YEARS;
    private static final Integer[] DEFAULT_LEAP_MONTH_DAYS;
    private static final Integer[] DEFAULT_LEAP_MONTH_LENGTHS;
    private static final Integer[] DEFAULT_MONTH_DAYS;
    private static final Integer[] DEFAULT_MONTH_LENGTHS;
    private static final char FILE_SEP;
    private static final int HIJRAH_JAN_1_1_GREGORIAN_DAY = -492148;
    private static final int MAX_ADJUSTED_CYCLE = 334;
    public static final int MIN_VALUE_OF_ERA = 1;
    private static final int[] NUM_DAYS;
    private static final String PATH_SEP;
    private static final int POSITION_DAY_OF_MONTH = 5;
    private static final int POSITION_DAY_OF_YEAR = 6;
    private static final long serialVersionUID = -5207853542612002020L;
    private final transient int dayOfMonth;
    private final transient DayOfWeek dayOfWeek;
    private final transient int dayOfYear;
    private final transient HijrahEra era;
    private final long gregorianEpochDay;
    private final transient boolean isLeapYear;
    private final transient int monthOfYear;
    private final transient int yearOfEra;
    private static final int[] LEAP_NUM_DAYS = {0, 30, 59, 89, 118, 148, 177, 207, 236, 266, 295, 325};
    private static final int[] MONTH_LENGTH = {30, 29, 30, 29, 30, 29, 30, 29, 30, 29, 30, 29};
    private static final int[] LEAP_MONTH_LENGTH = {30, 29, 30, 29, 30, 29, 30, 29, 30, 29, 30, 30};
    private static final int[] MIN_VALUES = {0, 1, 0, 1, 0, 1, 1};
    public static final int MAX_VALUE_OF_ERA = 9999;
    private static final int[] LEAST_MAX_VALUES = {1, MAX_VALUE_OF_ERA, 11, 51, 5, 29, 354};
    private static final int[] MAX_VALUES = {1, MAX_VALUE_OF_ERA, 11, 52, 6, 30, 355};
    private static final int[] CYCLEYEAR_START_DATE = {0, 354, 709, 1063, 1417, 1772, 2126, 2481, 2835, 3189, 3544, 3898, 4252, 4607, 4961, 5315, 5670, 6024, 6379, 6733, 7087, 7442, 7796, 8150, 8505, 8859, 9214, 9568, 9922, 10277};

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.temporal.Temporal
    public /* bridge */ /* synthetic */ long until(Temporal temporal, TemporalUnit temporalUnit) {
        return super.until(temporal, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    public /* bridge */ /* synthetic */ ChronoPeriod until(ChronoLocalDate chronoLocalDate) {
        return super.until(chronoLocalDate);
    }

    static {
        int[] iArr = {0, 30, 59, 89, 118, 148, 177, 207, 236, 266, 295, 325};
        NUM_DAYS = iArr;
        char c = File.separatorChar;
        FILE_SEP = c;
        PATH_SEP = File.pathSeparator;
        DEFAULT_CONFIG_PATH = "org" + c + "threeten" + c + "bp" + c + "chrono";
        ADJUSTED_MONTH_DAYS = new HashMap<>();
        ADJUSTED_MONTH_LENGTHS = new HashMap<>();
        ADJUSTED_CYCLE_YEARS = new HashMap<>();
        DEFAULT_MONTH_DAYS = new Integer[iArr.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr2 = NUM_DAYS;
            if (i2 >= iArr2.length) {
                break;
            }
            DEFAULT_MONTH_DAYS[i2] = new Integer(iArr2[i2]);
            i2++;
        }
        DEFAULT_LEAP_MONTH_DAYS = new Integer[LEAP_NUM_DAYS.length];
        int i3 = 0;
        while (true) {
            int[] iArr3 = LEAP_NUM_DAYS;
            if (i3 >= iArr3.length) {
                break;
            }
            DEFAULT_LEAP_MONTH_DAYS[i3] = new Integer(iArr3[i3]);
            i3++;
        }
        DEFAULT_MONTH_LENGTHS = new Integer[MONTH_LENGTH.length];
        int i4 = 0;
        while (true) {
            int[] iArr4 = MONTH_LENGTH;
            if (i4 >= iArr4.length) {
                break;
            }
            DEFAULT_MONTH_LENGTHS[i4] = new Integer(iArr4[i4]);
            i4++;
        }
        DEFAULT_LEAP_MONTH_LENGTHS = new Integer[LEAP_MONTH_LENGTH.length];
        int i5 = 0;
        while (true) {
            int[] iArr5 = LEAP_MONTH_LENGTH;
            if (i5 >= iArr5.length) {
                break;
            }
            DEFAULT_LEAP_MONTH_LENGTHS[i5] = new Integer(iArr5[i5]);
            i5++;
        }
        DEFAULT_CYCLE_YEARS = new Integer[CYCLEYEAR_START_DATE.length];
        int i6 = 0;
        while (true) {
            int[] iArr6 = CYCLEYEAR_START_DATE;
            if (i6 >= iArr6.length) {
                break;
            }
            DEFAULT_CYCLE_YEARS[i6] = new Integer(iArr6[i6]);
            i6++;
        }
        ADJUSTED_CYCLES = new Long[334];
        int i7 = 0;
        while (true) {
            Long[] lArr = ADJUSTED_CYCLES;
            if (i7 >= lArr.length) {
                break;
            }
            lArr[i7] = new Long(i7 * 10631);
            i7++;
        }
        ADJUSTED_MIN_VALUES = new Integer[MIN_VALUES.length];
        int i8 = 0;
        while (true) {
            int[] iArr7 = MIN_VALUES;
            if (i8 >= iArr7.length) {
                break;
            }
            ADJUSTED_MIN_VALUES[i8] = new Integer(iArr7[i8]);
            i8++;
        }
        ADJUSTED_LEAST_MAX_VALUES = new Integer[LEAST_MAX_VALUES.length];
        int i9 = 0;
        while (true) {
            int[] iArr8 = LEAST_MAX_VALUES;
            if (i9 >= iArr8.length) {
                break;
            }
            ADJUSTED_LEAST_MAX_VALUES[i9] = new Integer(iArr8[i9]);
            i9++;
        }
        ADJUSTED_MAX_VALUES = new Integer[MAX_VALUES.length];
        while (true) {
            int[] iArr9 = MAX_VALUES;
            if (i < iArr9.length) {
                ADJUSTED_MAX_VALUES[i] = new Integer(iArr9[i]);
                i++;
            } else {
                try {
                    readDeviationConfig();
                    return;
                } catch (IOException | ParseException unused) {
                    return;
                }
            }
        }
    }

    public static HijrahDate now() {
        return now(Clock.systemDefaultZone());
    }

    public static HijrahDate now(ZoneId zoneId) {
        return now(Clock.system(zoneId));
    }

    public static HijrahDate now(Clock clock) {
        return HijrahChronology.INSTANCE.dateNow(clock);
    }

    public static HijrahDate of(int i, int i2, int i3) {
        return i >= 1 ? of(HijrahEra.AH, i, i2, i3) : of(HijrahEra.BEFORE_AH, 1 - i, i2, i3);
    }

    static HijrahDate of(HijrahEra hijrahEra, int i, int i2, int i3) {
        Jdk8Methods.requireNonNull(hijrahEra, "era");
        checkValidYearOfEra(i);
        checkValidMonth(i2);
        checkValidDayOfMonth(i3);
        return new HijrahDate(getGregorianEpochDay(hijrahEra.prolepticYear(i), i2, i3));
    }

    private static void checkValidYearOfEra(int i) {
        if (i < 1 || i > 9999) {
            throw new DateTimeException("Invalid year of Hijrah Era");
        }
    }

    private static void checkValidDayOfYear(int i) {
        if (i < 1 || i > getMaximumDayOfYear()) {
            throw new DateTimeException("Invalid day of year of Hijrah date");
        }
    }

    private static void checkValidMonth(int i) {
        if (i < 1 || i > 12) {
            throw new DateTimeException("Invalid month of Hijrah date");
        }
    }

    private static void checkValidDayOfMonth(int i) {
        if (i < 1 || i > getMaximumDayOfMonth()) {
            throw new DateTimeException("Invalid day of month of Hijrah date, day " + i + " greater than " + getMaximumDayOfMonth() + " or less than 1");
        }
    }

    static HijrahDate of(LocalDate localDate) {
        return new HijrahDate(localDate.toEpochDay());
    }

    static HijrahDate ofEpochDay(long j) {
        return new HijrahDate(j);
    }

    public static HijrahDate from(TemporalAccessor temporalAccessor) {
        return HijrahChronology.INSTANCE.date(temporalAccessor);
    }

    private HijrahDate(long j) {
        int[] hijrahDateInfo = getHijrahDateInfo(j);
        checkValidYearOfEra(hijrahDateInfo[1]);
        checkValidMonth(hijrahDateInfo[2]);
        checkValidDayOfMonth(hijrahDateInfo[3]);
        checkValidDayOfYear(hijrahDateInfo[4]);
        this.era = HijrahEra.of(hijrahDateInfo[0]);
        int i = hijrahDateInfo[1];
        this.yearOfEra = i;
        this.monthOfYear = hijrahDateInfo[2];
        this.dayOfMonth = hijrahDateInfo[3];
        this.dayOfYear = hijrahDateInfo[4];
        this.dayOfWeek = DayOfWeek.of(hijrahDateInfo[5]);
        this.gregorianEpochDay = j;
        this.isLeapYear = isLeapYear(i);
    }

    private Object readResolve() {
        return new HijrahDate(this.gregorianEpochDay);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public HijrahChronology getChronology() {
        return HijrahChronology.INSTANCE;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public HijrahEra getEra() {
        return this.era;
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (isSupported(temporalField)) {
                ChronoField chronoField = (ChronoField) temporalField;
                int i = AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoField[chronoField.ordinal()];
                if (i == 1) {
                    return ValueRange.of(1L, lengthOfMonth());
                }
                if (i == 2) {
                    return ValueRange.of(1L, lengthOfYear());
                }
                if (i == 3) {
                    return ValueRange.of(1L, 5L);
                }
                if (i == 4) {
                    return ValueRange.of(1L, 1000L);
                }
                return getChronology().range(chronoField);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    /* JADX INFO: renamed from: org.threeten.bp.chrono.HijrahDate$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoField;

        static {
            int[] iArr = new int[ChronoField.values().length];
            $SwitchMap$org$threeten$bp$temporal$ChronoField = iArr;
            try {
                iArr[ChronoField.DAY_OF_MONTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.DAY_OF_YEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR_OF_ERA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.DAY_OF_WEEK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.EPOCH_DAY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ALIGNED_WEEK_OF_YEAR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.MONTH_OF_YEAR.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ERA.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        int value;
        int i;
        if (temporalField instanceof ChronoField) {
            switch (AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoField[((ChronoField) temporalField).ordinal()]) {
                case 1:
                    value = this.dayOfMonth;
                    return value;
                case 2:
                    value = this.dayOfYear;
                    return value;
                case 3:
                    i = (this.dayOfMonth - 1) / 7;
                    value = i + 1;
                    return value;
                case 4:
                    value = this.yearOfEra;
                    return value;
                case 5:
                    value = this.dayOfWeek.getValue();
                    return value;
                case 6:
                    i = (this.dayOfMonth - 1) % 7;
                    value = i + 1;
                    return value;
                case 7:
                    i = (this.dayOfYear - 1) % 7;
                    value = i + 1;
                    return value;
                case 8:
                    return toEpochDay();
                case 9:
                    i = (this.dayOfYear - 1) / 7;
                    value = i + 1;
                    return value;
                case 10:
                    value = this.monthOfYear;
                    return value;
                case 11:
                    value = this.yearOfEra;
                    return value;
                case 12:
                    value = this.era.getValue();
                    return value;
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            }
        }
        return temporalField.getFrom(this);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    public HijrahDate with(TemporalAdjuster temporalAdjuster) {
        return (HijrahDate) super.with(temporalAdjuster);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.Temporal
    public HijrahDate with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            chronoField.checkValidValue(j);
            int i = (int) j;
            switch (AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoField[chronoField.ordinal()]) {
                case 1:
                    return resolvePreviousValid(this.yearOfEra, this.monthOfYear, i);
                case 2:
                    int i2 = i - 1;
                    return resolvePreviousValid(this.yearOfEra, (i2 / 30) + 1, (i2 % 30) + 1);
                case 3:
                    return plusDays((j - getLong(ChronoField.ALIGNED_WEEK_OF_MONTH)) * 7);
                case 4:
                    if (this.yearOfEra < 1) {
                        i = 1 - i;
                    }
                    return resolvePreviousValid(i, this.monthOfYear, this.dayOfMonth);
                case 5:
                    return plusDays(j - ((long) this.dayOfWeek.getValue()));
                case 6:
                    return plusDays(j - getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
                case 7:
                    return plusDays(j - getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
                case 8:
                    return new HijrahDate(i);
                case 9:
                    return plusDays((j - getLong(ChronoField.ALIGNED_WEEK_OF_YEAR)) * 7);
                case 10:
                    return resolvePreviousValid(this.yearOfEra, i, this.dayOfMonth);
                case 11:
                    return resolvePreviousValid(i, this.monthOfYear, this.dayOfMonth);
                case 12:
                    return resolvePreviousValid(1 - this.yearOfEra, this.monthOfYear, this.dayOfMonth);
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            }
        }
        return (HijrahDate) temporalField.adjustInto(this, j);
    }

    private static HijrahDate resolvePreviousValid(int i, int i2, int i3) {
        int monthDays = getMonthDays(i2 - 1, i);
        if (i3 > monthDays) {
            i3 = monthDays;
        }
        return of(i, i2, i3);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    public HijrahDate plus(TemporalAmount temporalAmount) {
        return (HijrahDate) super.plus(temporalAmount);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.Temporal
    public HijrahDate plus(long j, TemporalUnit temporalUnit) {
        return (HijrahDate) super.plus(j, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    public HijrahDate minus(TemporalAmount temporalAmount) {
        return (HijrahDate) super.minus(temporalAmount);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    public HijrahDate minus(long j, TemporalUnit temporalUnit) {
        return (HijrahDate) super.minus(j, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    public final ChronoLocalDateTime<HijrahDate> atTime(LocalTime localTime) {
        return super.atTime(localTime);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public long toEpochDay() {
        return getGregorianEpochDay(this.yearOfEra, this.monthOfYear, this.dayOfMonth);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public boolean isLeapYear() {
        return this.isLeapYear;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public HijrahDate plusYears(long j) {
        if (j == 0) {
            return this;
        }
        return of(this.era, Jdk8Methods.safeAdd(this.yearOfEra, (int) j), this.monthOfYear, this.dayOfMonth);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public HijrahDate plusMonths(long j) {
        if (j == 0) {
            return this;
        }
        int i = (this.monthOfYear - 1) + ((int) j);
        int iSafeSubtract = i / 12;
        int i2 = i % 12;
        while (i2 < 0) {
            i2 += 12;
            iSafeSubtract = Jdk8Methods.safeSubtract(iSafeSubtract, 1);
        }
        return of(this.era, Jdk8Methods.safeAdd(this.yearOfEra, iSafeSubtract), i2 + 1, this.dayOfMonth);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public HijrahDate plusDays(long j) {
        return new HijrahDate(this.gregorianEpochDay + j);
    }

    private static int[] getHijrahDateInfo(long j) {
        int monthOfYear;
        int dayOfMonth;
        int value;
        int dayOfYear;
        int i;
        long j2 = j - (-492148);
        if (j2 >= 0) {
            int cycleNumber = getCycleNumber(j2);
            int dayOfCycle = getDayOfCycle(j2, cycleNumber);
            int yearInCycle = getYearInCycle(cycleNumber, dayOfCycle);
            dayOfYear = getDayOfYear(cycleNumber, dayOfCycle, yearInCycle);
            i = (cycleNumber * 30) + yearInCycle + 1;
            monthOfYear = getMonthOfYear(dayOfYear, i);
            dayOfMonth = getDayOfMonth(dayOfYear, monthOfYear, i) + 1;
            value = HijrahEra.AH.getValue();
        } else {
            int i2 = (int) j2;
            int i3 = i2 / 10631;
            int i4 = i2 % 10631;
            if (i4 == 0) {
                i3++;
                i4 = -10631;
            }
            int yearInCycle2 = getYearInCycle(i3, i4);
            int dayOfYear2 = getDayOfYear(i3, i4, yearInCycle2);
            int i5 = 1 - ((i3 * 30) - yearInCycle2);
            int i6 = isLeapYear((long) i5) ? dayOfYear2 + 355 : dayOfYear2 + 354;
            monthOfYear = getMonthOfYear(i6, i5);
            dayOfMonth = getDayOfMonth(i6, monthOfYear, i5) + 1;
            value = HijrahEra.BEFORE_AH.getValue();
            dayOfYear = i6;
            i = i5;
        }
        int i7 = (int) ((j2 + 5) % 7);
        return new int[]{value, i, monthOfYear + 1, dayOfMonth, dayOfYear + 1, i7 + (i7 <= 0 ? 7 : 0)};
    }

    private static long getGregorianEpochDay(int i, int i2, int i3) {
        return yearToGregorianEpochDay(i) + ((long) getMonthDays(i2 - 1, i)) + ((long) i3);
    }

    private static long yearToGregorianEpochDay(int i) {
        Long l;
        int i2 = i - 1;
        int i3 = i2 / 30;
        int i4 = i2 % 30;
        int iIntValue = getAdjustedCycle(i3)[Math.abs(i4)].intValue();
        if (i4 < 0) {
            iIntValue = -iIntValue;
        }
        try {
            l = ADJUSTED_CYCLES[i3];
        } catch (ArrayIndexOutOfBoundsException unused) {
            l = null;
        }
        if (l == null) {
            l = new Long(i3 * 10631);
        }
        return ((l.longValue() + ((long) iIntValue)) - 492148) - 1;
    }

    private static int getCycleNumber(long j) {
        Long[] lArr = ADJUSTED_CYCLES;
        for (int i = 0; i < lArr.length; i++) {
            try {
                if (j < lArr[i].longValue()) {
                    return i - 1;
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                return ((int) j) / 10631;
            }
        }
        return ((int) j) / 10631;
    }

    private static int getDayOfCycle(long j, int i) {
        Long l;
        try {
            l = ADJUSTED_CYCLES[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            l = null;
        }
        if (l == null) {
            l = new Long(i * 10631);
        }
        return (int) (j - l.longValue());
    }

    private static int getYearInCycle(int i, long j) {
        Integer[] adjustedCycle = getAdjustedCycle(i);
        int i2 = 0;
        if (j == 0) {
            return 0;
        }
        if (j > 0) {
            while (i2 < adjustedCycle.length) {
                if (j < adjustedCycle[i2].intValue()) {
                    return i2 - 1;
                }
                i2++;
            }
            return 29;
        }
        long j2 = -j;
        while (i2 < adjustedCycle.length) {
            if (j2 <= adjustedCycle[i2].intValue()) {
                return i2 - 1;
            }
            i2++;
        }
        return 29;
    }

    private static Integer[] getAdjustedCycle(int i) {
        Integer[] numArr;
        try {
            numArr = ADJUSTED_CYCLE_YEARS.get(new Integer(i));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        return numArr == null ? DEFAULT_CYCLE_YEARS : numArr;
    }

    private static Integer[] getAdjustedMonthDays(int i) {
        Integer[] numArr;
        try {
            numArr = ADJUSTED_MONTH_DAYS.get(new Integer(i));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr != null) {
            return numArr;
        }
        if (isLeapYear(i)) {
            return DEFAULT_LEAP_MONTH_DAYS;
        }
        return DEFAULT_MONTH_DAYS;
    }

    private static Integer[] getAdjustedMonthLength(int i) {
        Integer[] numArr;
        try {
            numArr = ADJUSTED_MONTH_LENGTHS.get(new Integer(i));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr != null) {
            return numArr;
        }
        if (isLeapYear(i)) {
            return DEFAULT_LEAP_MONTH_LENGTHS;
        }
        return DEFAULT_MONTH_LENGTHS;
    }

    private static int getDayOfYear(int i, int i2, int i3) {
        Integer[] adjustedCycle = getAdjustedCycle(i);
        if (i2 > 0) {
            return i2 - adjustedCycle[i3].intValue();
        }
        return adjustedCycle[i3].intValue() + i2;
    }

    private static int getMonthOfYear(int i, int i2) {
        Integer[] adjustedMonthDays = getAdjustedMonthDays(i2);
        int i3 = 0;
        if (i >= 0) {
            while (i3 < adjustedMonthDays.length) {
                if (i < adjustedMonthDays[i3].intValue()) {
                    return i3 - 1;
                }
                i3++;
            }
            return 11;
        }
        int i4 = isLeapYear((long) i2) ? i + 355 : i + 354;
        while (i3 < adjustedMonthDays.length) {
            if (i4 < adjustedMonthDays[i3].intValue()) {
                return i3 - 1;
            }
            i3++;
        }
        return 11;
    }

    private static int getDayOfMonth(int i, int i2, int i3) {
        int iIntValue;
        Integer[] adjustedMonthDays = getAdjustedMonthDays(i3);
        if (i < 0) {
            i = isLeapYear((long) i3) ? i + 355 : i + 354;
            if (i2 <= 0) {
                return i;
            }
            iIntValue = adjustedMonthDays[i2].intValue();
        } else {
            if (i2 <= 0) {
                return i;
            }
            iIntValue = adjustedMonthDays[i2].intValue();
        }
        return i - iIntValue;
    }

    static boolean isLeapYear(long j) {
        if (j <= 0) {
            j = -j;
        }
        return ((j * 11) + 14) % 30 < 11;
    }

    private static int getMonthDays(int i, int i2) {
        return getAdjustedMonthDays(i2)[i].intValue();
    }

    static int getMonthLength(int i, int i2) {
        return getAdjustedMonthLength(i2)[i].intValue();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int lengthOfMonth() {
        return getMonthLength(this.monthOfYear - 1, this.yearOfEra);
    }

    static int getYearLength(int i) {
        Integer[] numArr;
        int i2 = i - 1;
        int i3 = i2 / 30;
        try {
            numArr = ADJUSTED_CYCLE_YEARS.get(Integer.valueOf(i3));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr == null) {
            return isLeapYear((long) i) ? 355 : 354;
        }
        int i4 = i2 % 30;
        if (i4 == 29) {
            Long[] lArr = ADJUSTED_CYCLES;
            return (lArr[i3 + 1].intValue() - lArr[i3].intValue()) - numArr[i4].intValue();
        }
        return numArr[i4 + 1].intValue() - numArr[i4].intValue();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int lengthOfYear() {
        return getYearLength(this.yearOfEra);
    }

    static int getMaximumDayOfMonth() {
        return ADJUSTED_MAX_VALUES[5].intValue();
    }

    static int getSmallestMaximumDayOfMonth() {
        return ADJUSTED_LEAST_MAX_VALUES[5].intValue();
    }

    static int getMaximumDayOfYear() {
        return ADJUSTED_MAX_VALUES[6].intValue();
    }

    static int getSmallestMaximumDayOfYear() {
        return ADJUSTED_LEAST_MAX_VALUES[6].intValue();
    }

    private static void addDeviationAsHijrah(int i, int i2, int i3, int i4, int i5) {
        if (i < 1) {
            throw new IllegalArgumentException("startYear < 1");
        }
        if (i3 < 1) {
            throw new IllegalArgumentException("endYear < 1");
        }
        if (i2 < 0 || i2 > 11) {
            throw new IllegalArgumentException("startMonth < 0 || startMonth > 11");
        }
        if (i4 < 0 || i4 > 11) {
            throw new IllegalArgumentException("endMonth < 0 || endMonth > 11");
        }
        if (i3 > 9999) {
            throw new IllegalArgumentException("endYear > 9999");
        }
        if (i3 < i) {
            throw new IllegalArgumentException("startYear > endYear");
        }
        if (i3 == i && i4 < i2) {
            throw new IllegalArgumentException("startYear == endYear && endMonth < startMonth");
        }
        boolean zIsLeapYear = isLeapYear(i);
        Integer[] numArr = ADJUSTED_MONTH_DAYS.get(new Integer(i));
        if (numArr == null) {
            if (zIsLeapYear) {
                numArr = new Integer[LEAP_NUM_DAYS.length];
                int i6 = 0;
                while (true) {
                    int[] iArr = LEAP_NUM_DAYS;
                    if (i6 >= iArr.length) {
                        break;
                    }
                    numArr[i6] = new Integer(iArr[i6]);
                    i6++;
                }
            } else {
                numArr = new Integer[NUM_DAYS.length];
                int i7 = 0;
                while (true) {
                    int[] iArr2 = NUM_DAYS;
                    if (i7 >= iArr2.length) {
                        break;
                    }
                    numArr[i7] = new Integer(iArr2[i7]);
                    i7++;
                }
            }
        }
        Integer[] numArr2 = new Integer[numArr.length];
        for (int i8 = 0; i8 < 12; i8++) {
            if (i8 > i2) {
                numArr2[i8] = new Integer(numArr[i8].intValue() - i5);
            } else {
                numArr2[i8] = new Integer(numArr[i8].intValue());
            }
        }
        ADJUSTED_MONTH_DAYS.put(new Integer(i), numArr2);
        Integer[] numArr3 = ADJUSTED_MONTH_LENGTHS.get(new Integer(i));
        if (numArr3 == null) {
            if (zIsLeapYear) {
                numArr3 = new Integer[LEAP_MONTH_LENGTH.length];
                int i9 = 0;
                while (true) {
                    int[] iArr3 = LEAP_MONTH_LENGTH;
                    if (i9 >= iArr3.length) {
                        break;
                    }
                    numArr3[i9] = new Integer(iArr3[i9]);
                    i9++;
                }
            } else {
                numArr3 = new Integer[MONTH_LENGTH.length];
                int i10 = 0;
                while (true) {
                    int[] iArr4 = MONTH_LENGTH;
                    if (i10 >= iArr4.length) {
                        break;
                    }
                    numArr3[i10] = new Integer(iArr4[i10]);
                    i10++;
                }
            }
        }
        Integer[] numArr4 = new Integer[numArr3.length];
        for (int i11 = 0; i11 < 12; i11++) {
            if (i11 == i2) {
                numArr4[i11] = new Integer(numArr3[i11].intValue() - i5);
            } else {
                numArr4[i11] = new Integer(numArr3[i11].intValue());
            }
        }
        ADJUSTED_MONTH_LENGTHS.put(new Integer(i), numArr4);
        if (i != i3) {
            int i12 = i - 1;
            int i13 = i12 / 30;
            int i14 = i12 % 30;
            Integer[] numArr5 = ADJUSTED_CYCLE_YEARS.get(new Integer(i13));
            if (numArr5 == null) {
                int length = CYCLEYEAR_START_DATE.length;
                Integer[] numArr6 = new Integer[length];
                for (int i15 = 0; i15 < length; i15++) {
                    numArr6[i15] = new Integer(CYCLEYEAR_START_DATE[i15]);
                }
                numArr5 = numArr6;
            }
            for (int i16 = i14 + 1; i16 < CYCLEYEAR_START_DATE.length; i16++) {
                numArr5[i16] = new Integer(numArr5[i16].intValue() - i5);
            }
            ADJUSTED_CYCLE_YEARS.put(new Integer(i13), numArr5);
            int i17 = i3 - 1;
            int i18 = i17 / 30;
            if (i13 != i18) {
                int i19 = i13 + 1;
                while (true) {
                    Long[] lArr = ADJUSTED_CYCLES;
                    if (i19 >= lArr.length) {
                        break;
                    }
                    lArr[i19] = new Long(lArr[i19].longValue() - ((long) i5));
                    i19++;
                }
                int i20 = i18 + 1;
                while (true) {
                    Long[] lArr2 = ADJUSTED_CYCLES;
                    if (i20 >= lArr2.length) {
                        break;
                    }
                    lArr2[i20] = new Long(lArr2[i20].longValue() + ((long) i5));
                    i20++;
                    i18 = i18;
                }
            }
            int i21 = i18;
            int i22 = i17 % 30;
            Integer[] numArr7 = ADJUSTED_CYCLE_YEARS.get(new Integer(i21));
            if (numArr7 == null) {
                int length2 = CYCLEYEAR_START_DATE.length;
                Integer[] numArr8 = new Integer[length2];
                for (int i23 = 0; i23 < length2; i23++) {
                    numArr8[i23] = new Integer(CYCLEYEAR_START_DATE[i23]);
                }
                numArr7 = numArr8;
            }
            for (int i24 = i22 + 1; i24 < CYCLEYEAR_START_DATE.length; i24++) {
                numArr7[i24] = new Integer(numArr7[i24].intValue() + i5);
            }
            ADJUSTED_CYCLE_YEARS.put(new Integer(i21), numArr7);
        }
        boolean zIsLeapYear2 = isLeapYear(i3);
        Integer[] numArr9 = ADJUSTED_MONTH_DAYS.get(new Integer(i3));
        if (numArr9 == null) {
            if (zIsLeapYear2) {
                numArr9 = new Integer[LEAP_NUM_DAYS.length];
                int i25 = 0;
                while (true) {
                    int[] iArr5 = LEAP_NUM_DAYS;
                    if (i25 >= iArr5.length) {
                        break;
                    }
                    numArr9[i25] = new Integer(iArr5[i25]);
                    i25++;
                }
            } else {
                numArr9 = new Integer[NUM_DAYS.length];
                int i26 = 0;
                while (true) {
                    int[] iArr6 = NUM_DAYS;
                    if (i26 >= iArr6.length) {
                        break;
                    }
                    numArr9[i26] = new Integer(iArr6[i26]);
                    i26++;
                }
            }
        }
        Integer[] numArr10 = new Integer[numArr9.length];
        for (int i27 = 0; i27 < 12; i27++) {
            if (i27 > i4) {
                numArr10[i27] = new Integer(numArr9[i27].intValue() + i5);
            } else {
                numArr10[i27] = new Integer(numArr9[i27].intValue());
            }
        }
        ADJUSTED_MONTH_DAYS.put(new Integer(i3), numArr10);
        Integer[] numArr11 = ADJUSTED_MONTH_LENGTHS.get(new Integer(i3));
        if (numArr11 == null) {
            if (zIsLeapYear2) {
                numArr11 = new Integer[LEAP_MONTH_LENGTH.length];
                int i28 = 0;
                while (true) {
                    int[] iArr7 = LEAP_MONTH_LENGTH;
                    if (i28 >= iArr7.length) {
                        break;
                    }
                    numArr11[i28] = new Integer(iArr7[i28]);
                    i28++;
                }
            } else {
                numArr11 = new Integer[MONTH_LENGTH.length];
                int i29 = 0;
                while (true) {
                    int[] iArr8 = MONTH_LENGTH;
                    if (i29 >= iArr8.length) {
                        break;
                    }
                    numArr11[i29] = new Integer(iArr8[i29]);
                    i29++;
                }
            }
        }
        Integer[] numArr12 = new Integer[numArr11.length];
        for (int i30 = 0; i30 < 12; i30++) {
            if (i30 == i4) {
                numArr12[i30] = new Integer(numArr11[i30].intValue() + i5);
            } else {
                numArr12[i30] = new Integer(numArr11[i30].intValue());
            }
        }
        HashMap<Integer, Integer[]> map = ADJUSTED_MONTH_LENGTHS;
        map.put(new Integer(i3), numArr12);
        Integer[] numArr13 = map.get(new Integer(i));
        Integer[] numArr14 = map.get(new Integer(i3));
        HashMap<Integer, Integer[]> map2 = ADJUSTED_MONTH_DAYS;
        Integer[] numArr15 = map2.get(new Integer(i));
        Integer[] numArr16 = map2.get(new Integer(i3));
        int iIntValue = numArr13[i2].intValue();
        int iIntValue2 = numArr14[i4].intValue();
        int iIntValue3 = numArr15[11].intValue() + numArr13[11].intValue();
        int iIntValue4 = numArr16[11].intValue() + numArr14[11].intValue();
        Integer[] numArr17 = ADJUSTED_MAX_VALUES;
        int iIntValue5 = numArr17[5].intValue();
        Integer[] numArr18 = ADJUSTED_LEAST_MAX_VALUES;
        int iIntValue6 = numArr18[5].intValue();
        if (iIntValue5 < iIntValue) {
            iIntValue5 = iIntValue;
        }
        if (iIntValue5 < iIntValue2) {
            iIntValue5 = iIntValue2;
        }
        numArr17[5] = new Integer(iIntValue5);
        if (iIntValue6 <= iIntValue) {
            iIntValue = iIntValue6;
        }
        if (iIntValue <= iIntValue2) {
            iIntValue2 = iIntValue;
        }
        numArr18[5] = new Integer(iIntValue2);
        int iIntValue7 = numArr17[6].intValue();
        int iIntValue8 = numArr18[6].intValue();
        if (iIntValue7 < iIntValue3) {
            iIntValue7 = iIntValue3;
        }
        if (iIntValue7 < iIntValue4) {
            iIntValue7 = iIntValue4;
        }
        numArr17[6] = new Integer(iIntValue7);
        if (iIntValue8 <= iIntValue3) {
            iIntValue3 = iIntValue8;
        }
        if (iIntValue3 <= iIntValue4) {
            iIntValue4 = iIntValue3;
        }
        numArr18[6] = new Integer(iIntValue4);
    }

    private static void readDeviationConfig() throws Throwable {
        InputStream configFileInputStream = getConfigFileInputStream();
        if (configFileInputStream == null) {
            return;
        }
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(configFileInputStream));
            int i = 0;
            while (true) {
                try {
                    String line = bufferedReader2.readLine();
                    if (line != null) {
                        i++;
                        parseLine(line.trim(), i);
                    } else {
                        bufferedReader2.close();
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void parseLine(String str, int i) throws ParseException {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            int iIndexOf = strNextToken.indexOf(58);
            if (iIndexOf != -1) {
                try {
                    int i2 = Integer.parseInt(strNextToken.substring(iIndexOf + 1, strNextToken.length()));
                    int iIndexOf2 = strNextToken.indexOf(45);
                    if (iIndexOf2 != -1) {
                        String strSubstring = strNextToken.substring(0, iIndexOf2);
                        String strSubstring2 = strNextToken.substring(iIndexOf2 + 1, iIndexOf);
                        int iIndexOf3 = strSubstring.indexOf(47);
                        int iIndexOf4 = strSubstring2.indexOf(47);
                        if (iIndexOf3 != -1) {
                            String strSubstring3 = strSubstring.substring(0, iIndexOf3);
                            String strSubstring4 = strSubstring.substring(iIndexOf3 + 1, strSubstring.length());
                            try {
                                int i3 = Integer.parseInt(strSubstring3);
                                try {
                                    int i4 = Integer.parseInt(strSubstring4);
                                    if (iIndexOf4 != -1) {
                                        String strSubstring5 = strSubstring2.substring(0, iIndexOf4);
                                        String strSubstring6 = strSubstring2.substring(iIndexOf4 + 1, strSubstring2.length());
                                        try {
                                            int i5 = Integer.parseInt(strSubstring5);
                                            try {
                                                int i6 = Integer.parseInt(strSubstring6);
                                                if (i3 != -1 && i4 != -1 && i5 != -1 && i6 != -1) {
                                                    addDeviationAsHijrah(i3, i4, i5, i6, i2);
                                                } else {
                                                    throw new ParseException("Unknown error at line " + i + ".", i);
                                                }
                                            } catch (NumberFormatException unused) {
                                                throw new ParseException("End month is not properly set at line " + i + ".", i);
                                            }
                                        } catch (NumberFormatException unused2) {
                                            throw new ParseException("End year is not properly set at line " + i + ".", i);
                                        }
                                    } else {
                                        throw new ParseException("End year/month has incorrect format at line " + i + ".", i);
                                    }
                                } catch (NumberFormatException unused3) {
                                    throw new ParseException("Start month is not properly set at line " + i + ".", i);
                                }
                            } catch (NumberFormatException unused4) {
                                throw new ParseException("Start year is not properly set at line " + i + ".", i);
                            }
                        } else {
                            throw new ParseException("Start year/month has incorrect format at line " + i + ".", i);
                        }
                    } else {
                        throw new ParseException("Start and end year/month has incorrect format at line " + i + ".", i);
                    }
                } catch (NumberFormatException unused5) {
                    throw new ParseException("Offset is not properly set at line " + i + ".", i);
                }
            } else {
                throw new ParseException("Offset has incorrect format at line " + i + ".", i);
            }
        }
    }

    private static InputStream getConfigFileInputStream() throws IOException {
        ZipFile zipFile;
        String property = System.getProperty("org.threeten.bp.i18n.HijrahDate.deviationConfigFile");
        if (property == null) {
            property = DEFAULT_CONFIG_FILENAME;
        }
        String property2 = System.getProperty("org.threeten.bp.i18n.HijrahDate.deviationConfigDir");
        if (property2 != null) {
            if (property2.length() != 0 || !property2.endsWith(System.getProperty("file.separator"))) {
                property2 = property2 + System.getProperty("file.separator");
            }
            File file = new File(property2 + FILE_SEP + property);
            if (!file.exists()) {
                return null;
            }
            try {
                return new FileInputStream(file);
            } catch (IOException e) {
                throw e;
            }
        }
        StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.class.path"), PATH_SEP);
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            File file2 = new File(strNextToken);
            if (file2.exists()) {
                if (file2.isDirectory()) {
                    StringBuilder sbAppend = new StringBuilder().append(strNextToken);
                    char c = FILE_SEP;
                    StringBuilder sbAppend2 = sbAppend.append(c);
                    String str = DEFAULT_CONFIG_PATH;
                    if (new File(sbAppend2.append(str).toString(), property).exists()) {
                        try {
                            return new FileInputStream(strNextToken + c + str + c + property);
                        } catch (IOException e2) {
                            throw e2;
                        }
                    }
                } else {
                    try {
                        zipFile = new ZipFile(file2);
                    } catch (IOException unused) {
                        zipFile = null;
                    }
                    if (zipFile != null) {
                        StringBuilder sbAppend3 = new StringBuilder().append(DEFAULT_CONFIG_PATH);
                        char c2 = FILE_SEP;
                        String string = sbAppend3.append(c2).append(property).toString();
                        ZipEntry entry = zipFile.getEntry(string);
                        if (entry == null) {
                            if (c2 == '/') {
                                string = string.replace(IOUtils.DIR_SEPARATOR_UNIX, IOUtils.DIR_SEPARATOR_WINDOWS);
                            } else if (c2 == '\\') {
                                string = string.replace(IOUtils.DIR_SEPARATOR_WINDOWS, IOUtils.DIR_SEPARATOR_UNIX);
                            }
                            entry = zipFile.getEntry(string);
                        }
                        if (entry != null) {
                            try {
                                return zipFile.getInputStream(entry);
                            } catch (IOException e3) {
                                throw e3;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(get(ChronoField.YEAR));
        dataOutput.writeByte(get(ChronoField.MONTH_OF_YEAR));
        dataOutput.writeByte(get(ChronoField.DAY_OF_MONTH));
    }

    static ChronoLocalDate readExternal(DataInput dataInput) throws IOException {
        return HijrahChronology.INSTANCE.date(dataInput.readInt(), (int) dataInput.readByte(), (int) dataInput.readByte());
    }
}
