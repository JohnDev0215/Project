package org.threeten.bp.temporal;

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Year;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;

/* JADX INFO: loaded from: classes4.dex */
public final class WeekFields implements Serializable {
    private static final ConcurrentMap<String, WeekFields> CACHE = new ConcurrentHashMap(4, 0.75f, 2);
    public static final WeekFields ISO = new WeekFields(DayOfWeek.MONDAY, 4);
    public static final WeekFields SUNDAY_START = of(DayOfWeek.SUNDAY, 1);
    private static final long serialVersionUID = -1177360819670808121L;
    private final DayOfWeek firstDayOfWeek;
    private final int minimalDays;
    private final transient TemporalField dayOfWeek = ComputedDayOfField.ofDayOfWeekField(this);
    private final transient TemporalField weekOfMonth = ComputedDayOfField.ofWeekOfMonthField(this);
    private final transient TemporalField weekOfYear = ComputedDayOfField.ofWeekOfYearField(this);
    private final transient TemporalField weekOfWeekBasedYear = ComputedDayOfField.ofWeekOfWeekBasedYearField(this);
    private final transient TemporalField weekBasedYear = ComputedDayOfField.ofWeekBasedYearField(this);

    public static WeekFields of(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        return of(DayOfWeek.SUNDAY.plus(r4.getFirstDayOfWeek() - 1), new GregorianCalendar(new Locale(locale.getLanguage(), locale.getCountry())).getMinimalDaysInFirstWeek());
    }

    public static WeekFields of(DayOfWeek dayOfWeek, int i) {
        String str = dayOfWeek.toString() + i;
        ConcurrentMap<String, WeekFields> concurrentMap = CACHE;
        WeekFields weekFields = concurrentMap.get(str);
        if (weekFields != null) {
            return weekFields;
        }
        concurrentMap.putIfAbsent(str, new WeekFields(dayOfWeek, i));
        return concurrentMap.get(str);
    }

    private WeekFields(DayOfWeek dayOfWeek, int i) {
        Jdk8Methods.requireNonNull(dayOfWeek, "firstDayOfWeek");
        if (i < 1 || i > 7) {
            throw new IllegalArgumentException("Minimal number of days is invalid");
        }
        this.firstDayOfWeek = dayOfWeek;
        this.minimalDays = i;
    }

    private Object readResolve() throws InvalidObjectException {
        try {
            return of(this.firstDayOfWeek, this.minimalDays);
        } catch (IllegalArgumentException e) {
            throw new InvalidObjectException("Invalid WeekFields" + e.getMessage());
        }
    }

    public DayOfWeek getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    public int getMinimalDaysInFirstWeek() {
        return this.minimalDays;
    }

    public TemporalField dayOfWeek() {
        return this.dayOfWeek;
    }

    public TemporalField weekOfMonth() {
        return this.weekOfMonth;
    }

    public TemporalField weekOfYear() {
        return this.weekOfYear;
    }

    public TemporalField weekOfWeekBasedYear() {
        return this.weekOfWeekBasedYear;
    }

    public TemporalField weekBasedYear() {
        return this.weekBasedYear;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WeekFields) && hashCode() == obj.hashCode();
    }

    public int hashCode() {
        return (this.firstDayOfWeek.ordinal() * 7) + this.minimalDays;
    }

    public String toString() {
        return "WeekFields[" + this.firstDayOfWeek + ',' + this.minimalDays + ']';
    }

    static class ComputedDayOfField implements TemporalField {
        private final TemporalUnit baseUnit;
        private final String name;
        private final ValueRange range;
        private final TemporalUnit rangeUnit;
        private final WeekFields weekDef;
        private static final ValueRange DAY_OF_WEEK_RANGE = ValueRange.of(1, 7);
        private static final ValueRange WEEK_OF_MONTH_RANGE = ValueRange.of(0, 1, 4, 6);
        private static final ValueRange WEEK_OF_YEAR_RANGE = ValueRange.of(0, 1, 52, 54);
        private static final ValueRange WEEK_OF_WEEK_BASED_YEAR_RANGE = ValueRange.of(1, 52, 53);
        private static final ValueRange WEEK_BASED_YEAR_RANGE = ChronoField.YEAR.range();

        @Override // org.threeten.bp.temporal.TemporalField
        public boolean isDateBased() {
            return true;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public boolean isTimeBased() {
            return false;
        }

        static ComputedDayOfField ofDayOfWeekField(WeekFields weekFields) {
            return new ComputedDayOfField("DayOfWeek", weekFields, ChronoUnit.DAYS, ChronoUnit.WEEKS, DAY_OF_WEEK_RANGE);
        }

        static ComputedDayOfField ofWeekOfMonthField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfMonth", weekFields, ChronoUnit.WEEKS, ChronoUnit.MONTHS, WEEK_OF_MONTH_RANGE);
        }

        static ComputedDayOfField ofWeekOfYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfYear", weekFields, ChronoUnit.WEEKS, ChronoUnit.YEARS, WEEK_OF_YEAR_RANGE);
        }

        static ComputedDayOfField ofWeekOfWeekBasedYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfWeekBasedYear", weekFields, ChronoUnit.WEEKS, IsoFields.WEEK_BASED_YEARS, WEEK_OF_WEEK_BASED_YEAR_RANGE);
        }

        static ComputedDayOfField ofWeekBasedYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekBasedYear", weekFields, IsoFields.WEEK_BASED_YEARS, ChronoUnit.FOREVER, WEEK_BASED_YEAR_RANGE);
        }

        private ComputedDayOfField(String str, WeekFields weekFields, TemporalUnit temporalUnit, TemporalUnit temporalUnit2, ValueRange valueRange) {
            this.name = str;
            this.weekDef = weekFields;
            this.baseUnit = temporalUnit;
            this.rangeUnit = temporalUnit2;
            this.range = valueRange;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public long getFrom(TemporalAccessor temporalAccessor) {
            int iLocalizedWBY;
            int iFloorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                return iFloorMod;
            }
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                int i = temporalAccessor.get(ChronoField.DAY_OF_MONTH);
                iLocalizedWBY = computeWeek(startOfWeekOffset(i, iFloorMod), i);
            } else if (this.rangeUnit == ChronoUnit.YEARS) {
                int i2 = temporalAccessor.get(ChronoField.DAY_OF_YEAR);
                iLocalizedWBY = computeWeek(startOfWeekOffset(i2, iFloorMod), i2);
            } else if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                iLocalizedWBY = localizedWOWBY(temporalAccessor);
            } else if (this.rangeUnit == ChronoUnit.FOREVER) {
                iLocalizedWBY = localizedWBY(temporalAccessor);
            } else {
                throw new IllegalStateException("unreachable");
            }
            return iLocalizedWBY;
        }

        private int localizedDayOfWeek(TemporalAccessor temporalAccessor, int i) {
            return Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - i, 7) + 1;
        }

        private long localizedWeekOfMonth(TemporalAccessor temporalAccessor, int i) {
            int i2 = temporalAccessor.get(ChronoField.DAY_OF_MONTH);
            return computeWeek(startOfWeekOffset(i2, i), i2);
        }

        private long localizedWeekOfYear(TemporalAccessor temporalAccessor, int i) {
            int i2 = temporalAccessor.get(ChronoField.DAY_OF_YEAR);
            return computeWeek(startOfWeekOffset(i2, i), i2);
        }

        private int localizedWOWBY(TemporalAccessor temporalAccessor) {
            int iFloorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            long jLocalizedWeekOfYear = localizedWeekOfYear(temporalAccessor, iFloorMod);
            if (jLocalizedWeekOfYear == 0) {
                return ((int) localizedWeekOfYear(Chronology.from(temporalAccessor).date(temporalAccessor).minus(1L, (TemporalUnit) ChronoUnit.WEEKS), iFloorMod)) + 1;
            }
            if (jLocalizedWeekOfYear >= 53) {
                int iComputeWeek = computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), iFloorMod), (Year.isLeap((long) temporalAccessor.get(ChronoField.YEAR)) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek());
                if (jLocalizedWeekOfYear >= iComputeWeek) {
                    return (int) (jLocalizedWeekOfYear - ((long) (iComputeWeek - 1)));
                }
            }
            return (int) jLocalizedWeekOfYear;
        }

        private int localizedWBY(TemporalAccessor temporalAccessor) {
            int iFloorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            int i = temporalAccessor.get(ChronoField.YEAR);
            long jLocalizedWeekOfYear = localizedWeekOfYear(temporalAccessor, iFloorMod);
            if (jLocalizedWeekOfYear == 0) {
                return i - 1;
            }
            if (jLocalizedWeekOfYear < 53) {
                return i;
            }
            return jLocalizedWeekOfYear >= ((long) computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), iFloorMod), (Year.isLeap((long) i) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek())) ? i + 1 : i;
        }

        private int startOfWeekOffset(int i, int i2) {
            int iFloorMod = Jdk8Methods.floorMod(i - i2, 7);
            return iFloorMod + 1 > this.weekDef.getMinimalDaysInFirstWeek() ? 7 - iFloorMod : -iFloorMod;
        }

        private int computeWeek(int i, int i2) {
            return ((i + 7) + (i2 - 1)) / 7;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public <R extends Temporal> R adjustInto(R r, long j) {
            int iCheckValidIntValue = this.range.checkValidIntValue(j, this);
            if (iCheckValidIntValue == r.get(this)) {
                return r;
            }
            if (this.rangeUnit == ChronoUnit.FOREVER) {
                int i = r.get(this.weekDef.weekOfWeekBasedYear);
                Temporal temporalPlus = r.plus((long) ((j - ((long) r1)) * 52.1775d), ChronoUnit.WEEKS);
                if (temporalPlus.get(this) > iCheckValidIntValue) {
                    return (R) temporalPlus.minus(temporalPlus.get(this.weekDef.weekOfWeekBasedYear), ChronoUnit.WEEKS);
                }
                if (temporalPlus.get(this) < iCheckValidIntValue) {
                    temporalPlus = temporalPlus.plus(2L, ChronoUnit.WEEKS);
                }
                R r2 = (R) temporalPlus.plus(i - temporalPlus.get(this.weekDef.weekOfWeekBasedYear), ChronoUnit.WEEKS);
                return r2.get(this) > iCheckValidIntValue ? (R) r2.minus(1L, ChronoUnit.WEEKS) : r2;
            }
            return (R) r.plus(iCheckValidIntValue - r1, this.baseUnit);
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
            long jCheckValidIntValue;
            ChronoLocalDate chronoLocalDateDate;
            long jCheckValidIntValue2;
            ChronoLocalDate chronoLocalDateDate2;
            long jCheckValidIntValue3;
            int iLocalizedDayOfWeek;
            long jLocalizedWeekOfYear;
            int value = this.weekDef.getFirstDayOfWeek().getValue();
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                map.put(ChronoField.DAY_OF_WEEK, Long.valueOf(Jdk8Methods.floorMod((value - 1) + (this.range.checkValidIntValue(map.remove(this).longValue(), this) - 1), 7) + 1));
                return null;
            }
            if (!map.containsKey(ChronoField.DAY_OF_WEEK)) {
                return null;
            }
            if (this.rangeUnit == ChronoUnit.FOREVER) {
                if (!map.containsKey(this.weekDef.weekOfWeekBasedYear)) {
                    return null;
                }
                Chronology chronologyFrom = Chronology.from(temporalAccessor);
                int iFloorMod = Jdk8Methods.floorMod(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.get(ChronoField.DAY_OF_WEEK).longValue()) - value, 7) + 1;
                int iCheckValidIntValue = range().checkValidIntValue(map.get(this).longValue(), this);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    chronoLocalDateDate2 = chronologyFrom.date(iCheckValidIntValue, 1, this.weekDef.getMinimalDaysInFirstWeek());
                    jCheckValidIntValue3 = map.get(this.weekDef.weekOfWeekBasedYear).longValue();
                    iLocalizedDayOfWeek = localizedDayOfWeek(chronoLocalDateDate2, value);
                    jLocalizedWeekOfYear = localizedWeekOfYear(chronoLocalDateDate2, iLocalizedDayOfWeek);
                } else {
                    chronoLocalDateDate2 = chronologyFrom.date(iCheckValidIntValue, 1, this.weekDef.getMinimalDaysInFirstWeek());
                    jCheckValidIntValue3 = this.weekDef.weekOfWeekBasedYear.range().checkValidIntValue(map.get(this.weekDef.weekOfWeekBasedYear).longValue(), this.weekDef.weekOfWeekBasedYear);
                    iLocalizedDayOfWeek = localizedDayOfWeek(chronoLocalDateDate2, value);
                    jLocalizedWeekOfYear = localizedWeekOfYear(chronoLocalDateDate2, iLocalizedDayOfWeek);
                }
                ChronoLocalDate chronoLocalDatePlus = chronoLocalDateDate2.plus(((jCheckValidIntValue3 - jLocalizedWeekOfYear) * 7) + ((long) (iFloorMod - iLocalizedDayOfWeek)), (TemporalUnit) ChronoUnit.DAYS);
                if (resolverStyle == ResolverStyle.STRICT && chronoLocalDatePlus.getLong(this) != map.get(this).longValue()) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different year");
                }
                map.remove(this);
                map.remove(this.weekDef.weekOfWeekBasedYear);
                map.remove(ChronoField.DAY_OF_WEEK);
                return chronoLocalDatePlus;
            }
            if (!map.containsKey(ChronoField.YEAR)) {
                return null;
            }
            int iFloorMod2 = Jdk8Methods.floorMod(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.get(ChronoField.DAY_OF_WEEK).longValue()) - value, 7) + 1;
            int iCheckValidIntValue2 = ChronoField.YEAR.checkValidIntValue(map.get(ChronoField.YEAR).longValue());
            Chronology chronologyFrom2 = Chronology.from(temporalAccessor);
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                if (!map.containsKey(ChronoField.MONTH_OF_YEAR)) {
                    return null;
                }
                long jLongValue = map.remove(this).longValue();
                if (resolverStyle == ResolverStyle.LENIENT) {
                    chronoLocalDateDate = chronologyFrom2.date(iCheckValidIntValue2, 1, 1).plus(map.get(ChronoField.MONTH_OF_YEAR).longValue() - 1, (TemporalUnit) ChronoUnit.MONTHS);
                    int iLocalizedDayOfWeek2 = localizedDayOfWeek(chronoLocalDateDate, value);
                    jCheckValidIntValue2 = ((jLongValue - localizedWeekOfMonth(chronoLocalDateDate, iLocalizedDayOfWeek2)) * 7) + ((long) (iFloorMod2 - iLocalizedDayOfWeek2));
                } else {
                    chronoLocalDateDate = chronologyFrom2.date(iCheckValidIntValue2, ChronoField.MONTH_OF_YEAR.checkValidIntValue(map.get(ChronoField.MONTH_OF_YEAR).longValue()), 8);
                    int iLocalizedDayOfWeek3 = localizedDayOfWeek(chronoLocalDateDate, value);
                    jCheckValidIntValue2 = ((((long) this.range.checkValidIntValue(jLongValue, this)) - localizedWeekOfMonth(chronoLocalDateDate, iLocalizedDayOfWeek3)) * 7) + ((long) (iFloorMod2 - iLocalizedDayOfWeek3));
                }
                ChronoLocalDate chronoLocalDatePlus2 = chronoLocalDateDate.plus(jCheckValidIntValue2, (TemporalUnit) ChronoUnit.DAYS);
                if (resolverStyle == ResolverStyle.STRICT && chronoLocalDatePlus2.getLong(ChronoField.MONTH_OF_YEAR) != map.get(ChronoField.MONTH_OF_YEAR).longValue()) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
                map.remove(this);
                map.remove(ChronoField.YEAR);
                map.remove(ChronoField.MONTH_OF_YEAR);
                map.remove(ChronoField.DAY_OF_WEEK);
                return chronoLocalDatePlus2;
            }
            if (this.rangeUnit == ChronoUnit.YEARS) {
                long jLongValue2 = map.remove(this).longValue();
                ChronoLocalDate chronoLocalDateDate3 = chronologyFrom2.date(iCheckValidIntValue2, 1, 1);
                if (resolverStyle == ResolverStyle.LENIENT) {
                    int iLocalizedDayOfWeek4 = localizedDayOfWeek(chronoLocalDateDate3, value);
                    jCheckValidIntValue = ((jLongValue2 - localizedWeekOfYear(chronoLocalDateDate3, iLocalizedDayOfWeek4)) * 7) + ((long) (iFloorMod2 - iLocalizedDayOfWeek4));
                } else {
                    int iLocalizedDayOfWeek5 = localizedDayOfWeek(chronoLocalDateDate3, value);
                    jCheckValidIntValue = ((((long) this.range.checkValidIntValue(jLongValue2, this)) - localizedWeekOfYear(chronoLocalDateDate3, iLocalizedDayOfWeek5)) * 7) + ((long) (iFloorMod2 - iLocalizedDayOfWeek5));
                }
                ChronoLocalDate chronoLocalDatePlus3 = chronoLocalDateDate3.plus(jCheckValidIntValue, (TemporalUnit) ChronoUnit.DAYS);
                if (resolverStyle == ResolverStyle.STRICT && chronoLocalDatePlus3.getLong(ChronoField.YEAR) != map.get(ChronoField.YEAR).longValue()) {
                    throw new DateTimeException("Strict mode rejected date parsed to a different year");
                }
                map.remove(this);
                map.remove(ChronoField.YEAR);
                map.remove(ChronoField.DAY_OF_WEEK);
                return chronoLocalDatePlus3;
            }
            throw new IllegalStateException("unreachable");
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public TemporalUnit getBaseUnit() {
            return this.baseUnit;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public TemporalUnit getRangeUnit() {
            return this.rangeUnit;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public ValueRange range() {
            return this.range;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
            if (!temporalAccessor.isSupported(ChronoField.DAY_OF_WEEK)) {
                return false;
            }
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                return true;
            }
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                return temporalAccessor.isSupported(ChronoField.DAY_OF_MONTH);
            }
            if (this.rangeUnit == ChronoUnit.YEARS) {
                return temporalAccessor.isSupported(ChronoField.DAY_OF_YEAR);
            }
            if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY);
            }
            if (this.rangeUnit == ChronoUnit.FOREVER) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY);
            }
            return false;
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
            ChronoField chronoField;
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                return this.range;
            }
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                chronoField = ChronoField.DAY_OF_MONTH;
            } else if (this.rangeUnit == ChronoUnit.YEARS) {
                chronoField = ChronoField.DAY_OF_YEAR;
            } else {
                if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                    return rangeWOWBY(temporalAccessor);
                }
                if (this.rangeUnit == ChronoUnit.FOREVER) {
                    return temporalAccessor.range(ChronoField.YEAR);
                }
                throw new IllegalStateException("unreachable");
            }
            int iStartOfWeekOffset = startOfWeekOffset(temporalAccessor.get(chronoField), Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1);
            ValueRange valueRangeRange = temporalAccessor.range(chronoField);
            return ValueRange.of(computeWeek(iStartOfWeekOffset, (int) valueRangeRange.getMinimum()), computeWeek(iStartOfWeekOffset, (int) valueRangeRange.getMaximum()));
        }

        private ValueRange rangeWOWBY(TemporalAccessor temporalAccessor) {
            int iFloorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            long jLocalizedWeekOfYear = localizedWeekOfYear(temporalAccessor, iFloorMod);
            if (jLocalizedWeekOfYear == 0) {
                return rangeWOWBY(Chronology.from(temporalAccessor).date(temporalAccessor).minus(2L, (TemporalUnit) ChronoUnit.WEEKS));
            }
            if (jLocalizedWeekOfYear >= computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), iFloorMod), (Year.isLeap((long) temporalAccessor.get(ChronoField.YEAR)) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek())) {
                return rangeWOWBY(Chronology.from(temporalAccessor).date(temporalAccessor).plus(2L, (TemporalUnit) ChronoUnit.WEEKS));
            }
            return ValueRange.of(1L, r0 - 1);
        }

        @Override // org.threeten.bp.temporal.TemporalField
        public String getDisplayName(Locale locale) {
            Jdk8Methods.requireNonNull(locale, "locale");
            return this.rangeUnit == ChronoUnit.YEARS ? "Week" : toString();
        }

        public String toString() {
            return this.name + "[" + this.weekDef.toString() + "]";
        }
    }
}
