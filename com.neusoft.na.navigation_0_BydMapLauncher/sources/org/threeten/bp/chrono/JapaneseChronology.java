package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.ValueRange;

/* JADX INFO: loaded from: classes4.dex */
public final class JapaneseChronology extends Chronology implements Serializable {
    private static final Map<String, String[]> ERA_FULL_NAMES;
    private static final Map<String, String[]> ERA_NARROW_NAMES;
    private static final Map<String, String[]> ERA_SHORT_NAMES;
    private static final String FALLBACK_LANGUAGE = "en";
    private static final long serialVersionUID = 459996390165777884L;
    private static final String TARGET_LANGUAGE = "ja";
    static final Locale LOCALE = new Locale(TARGET_LANGUAGE, "JP", "JP");
    public static final JapaneseChronology INSTANCE = new JapaneseChronology();

    @Override // org.threeten.bp.chrono.Chronology
    public String getCalendarType() {
        return "japanese";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String getId() {
        return "Japanese";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public /* bridge */ /* synthetic */ ChronoLocalDate resolveDate(Map map, ResolverStyle resolverStyle) {
        return resolveDate((Map<TemporalField, Long>) map, resolverStyle);
    }

    static {
        HashMap map = new HashMap();
        ERA_NARROW_NAMES = map;
        HashMap map2 = new HashMap();
        ERA_SHORT_NAMES = map2;
        HashMap map3 = new HashMap();
        ERA_FULL_NAMES = map3;
        map.put(FALLBACK_LANGUAGE, new String[]{"Unknown", "K", "M", "T", "S", "H"});
        map.put(TARGET_LANGUAGE, new String[]{"Unknown", "K", "M", "T", "S", "H"});
        map2.put(FALLBACK_LANGUAGE, new String[]{"Unknown", "K", "M", "T", "S", "H"});
        map2.put(TARGET_LANGUAGE, new String[]{"Unknown", "慶", "明", "大", "昭", "平"});
        map3.put(FALLBACK_LANGUAGE, new String[]{"Unknown", "Keio", "Meiji", "Taisho", "Showa", "Heisei"});
        map3.put(TARGET_LANGUAGE, new String[]{"Unknown", "慶応", "明治", "大正", "昭和", "平成"});
    }

    private JapaneseChronology() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public JapaneseDate date(Era era, int i, int i2, int i3) {
        if (!(era instanceof JapaneseEra)) {
            throw new ClassCastException("Era must be JapaneseEra");
        }
        return JapaneseDate.of((JapaneseEra) era, i, i2, i3);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public JapaneseDate date(int i, int i2, int i3) {
        return new JapaneseDate(LocalDate.of(i, i2, i3));
    }

    @Override // org.threeten.bp.chrono.Chronology
    public JapaneseDate dateYearDay(Era era, int i, int i2) {
        if (!(era instanceof JapaneseEra)) {
            throw new ClassCastException("Era must be JapaneseEra");
        }
        return JapaneseDate.ofYearDay((JapaneseEra) era, i, i2);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public JapaneseDate dateYearDay(int i, int i2) {
        LocalDate localDateOfYearDay = LocalDate.ofYearDay(i, i2);
        return date(i, localDateOfYearDay.getMonthValue(), localDateOfYearDay.getDayOfMonth());
    }

    @Override // org.threeten.bp.chrono.Chronology
    public JapaneseDate dateEpochDay(long j) {
        return new JapaneseDate(LocalDate.ofEpochDay(j));
    }

    @Override // org.threeten.bp.chrono.Chronology
    public JapaneseDate date(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof JapaneseDate) {
            return (JapaneseDate) temporalAccessor;
        }
        return new JapaneseDate(LocalDate.from(temporalAccessor));
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoLocalDateTime<JapaneseDate> localDateTime(TemporalAccessor temporalAccessor) {
        return super.localDateTime(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<JapaneseDate> zonedDateTime(TemporalAccessor temporalAccessor) {
        return super.zonedDateTime(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<JapaneseDate> zonedDateTime(Instant instant, ZoneId zoneId) {
        return super.zonedDateTime(instant, zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public JapaneseDate dateNow() {
        return (JapaneseDate) super.dateNow();
    }

    @Override // org.threeten.bp.chrono.Chronology
    public JapaneseDate dateNow(ZoneId zoneId) {
        return (JapaneseDate) super.dateNow(zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public JapaneseDate dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return (JapaneseDate) super.dateNow(clock);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public boolean isLeapYear(long j) {
        return IsoChronology.INSTANCE.isLeapYear(j);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public int prolepticYear(Era era, int i) {
        if (!(era instanceof JapaneseEra)) {
            throw new ClassCastException("Era must be JapaneseEra");
        }
        int year = (((JapaneseEra) era).startDate().getYear() + i) - 1;
        ValueRange.of(1L, (r6.endDate().getYear() - r6.startDate().getYear()) + 1).checkValidValue(i, ChronoField.YEAR_OF_ERA);
        return year;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public JapaneseEra eraOf(int i) {
        return JapaneseEra.of(i);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public List<Era> eras() {
        return Arrays.asList(JapaneseEra.values());
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ValueRange range(ChronoField chronoField) {
        switch (AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoField[chronoField.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                return chronoField.range();
            default:
                Calendar calendar = Calendar.getInstance(LOCALE);
                int i = 0;
                switch (chronoField) {
                    case ERA:
                        JapaneseEra[] japaneseEraArrValues = JapaneseEra.values();
                        return ValueRange.of(japaneseEraArrValues[0].getValue(), japaneseEraArrValues[japaneseEraArrValues.length - 1].getValue());
                    case YEAR:
                        JapaneseEra[] japaneseEraArrValues2 = JapaneseEra.values();
                        return ValueRange.of(JapaneseDate.MIN_DATE.getYear(), japaneseEraArrValues2[japaneseEraArrValues2.length - 1].endDate().getYear());
                    case YEAR_OF_ERA:
                        JapaneseEra[] japaneseEraArrValues3 = JapaneseEra.values();
                        int year = (japaneseEraArrValues3[japaneseEraArrValues3.length - 1].endDate().getYear() - japaneseEraArrValues3[japaneseEraArrValues3.length - 1].startDate().getYear()) + 1;
                        int iMin = Integer.MAX_VALUE;
                        while (i < japaneseEraArrValues3.length) {
                            iMin = Math.min(iMin, (japaneseEraArrValues3[i].endDate().getYear() - japaneseEraArrValues3[i].startDate().getYear()) + 1);
                            i++;
                        }
                        return ValueRange.of(1L, 6L, iMin, year);
                    case MONTH_OF_YEAR:
                        return ValueRange.of(calendar.getMinimum(2) + 1, calendar.getGreatestMinimum(2) + 1, calendar.getLeastMaximum(2) + 1, calendar.getMaximum(2) + 1);
                    case DAY_OF_YEAR:
                        JapaneseEra[] japaneseEraArrValues4 = JapaneseEra.values();
                        int iMin2 = 366;
                        while (i < japaneseEraArrValues4.length) {
                            iMin2 = Math.min(iMin2, (japaneseEraArrValues4[i].startDate().lengthOfYear() - japaneseEraArrValues4[i].startDate().getDayOfYear()) + 1);
                            i++;
                        }
                        return ValueRange.of(1L, iMin2, 366L);
                    default:
                        throw new UnsupportedOperationException("Unimplementable field: " + chronoField);
                }
        }
    }

    @Override // org.threeten.bp.chrono.Chronology
    public JapaneseDate resolveDate(Map<TemporalField, Long> map, ResolverStyle resolverStyle) {
        if (map.containsKey(ChronoField.EPOCH_DAY)) {
            return dateEpochDay(map.remove(ChronoField.EPOCH_DAY).longValue());
        }
        Long lRemove = map.remove(ChronoField.PROLEPTIC_MONTH);
        if (lRemove != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.PROLEPTIC_MONTH.checkValidValue(lRemove.longValue());
            }
            updateResolveMap(map, ChronoField.MONTH_OF_YEAR, Jdk8Methods.floorMod(lRemove.longValue(), 12) + 1);
            updateResolveMap(map, ChronoField.YEAR, Jdk8Methods.floorDiv(lRemove.longValue(), 12L));
        }
        Long l = map.get(ChronoField.ERA);
        JapaneseEra japaneseEraEraOf = l != null ? eraOf(range(ChronoField.ERA).checkValidIntValue(l.longValue(), ChronoField.ERA)) : null;
        Long l2 = map.get(ChronoField.YEAR_OF_ERA);
        if (l2 != null) {
            int iCheckValidIntValue = range(ChronoField.YEAR_OF_ERA).checkValidIntValue(l2.longValue(), ChronoField.YEAR_OF_ERA);
            if (japaneseEraEraOf == null && resolverStyle != ResolverStyle.STRICT && !map.containsKey(ChronoField.YEAR)) {
                List<Era> listEras = eras();
                japaneseEraEraOf = (JapaneseEra) listEras.get(listEras.size() - 1);
            }
            if (japaneseEraEraOf != null && map.containsKey(ChronoField.MONTH_OF_YEAR) && map.containsKey(ChronoField.DAY_OF_MONTH)) {
                map.remove(ChronoField.ERA);
                map.remove(ChronoField.YEAR_OF_ERA);
                return resolveEYMD(map, resolverStyle, japaneseEraEraOf, iCheckValidIntValue);
            }
            if (japaneseEraEraOf != null && map.containsKey(ChronoField.DAY_OF_YEAR)) {
                map.remove(ChronoField.ERA);
                map.remove(ChronoField.YEAR_OF_ERA);
                return resolveEYD(map, resolverStyle, japaneseEraEraOf, iCheckValidIntValue);
            }
        }
        if (map.containsKey(ChronoField.YEAR)) {
            if (map.containsKey(ChronoField.MONTH_OF_YEAR)) {
                if (map.containsKey(ChronoField.DAY_OF_MONTH)) {
                    int iCheckValidIntValue2 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return date(iCheckValidIntValue2, 1, 1).plusMonths(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_MONTH).longValue(), 1L));
                    }
                    int iCheckValidIntValue3 = range(ChronoField.MONTH_OF_YEAR).checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), ChronoField.MONTH_OF_YEAR);
                    int iCheckValidIntValue4 = range(ChronoField.DAY_OF_MONTH).checkValidIntValue(map.remove(ChronoField.DAY_OF_MONTH).longValue(), ChronoField.DAY_OF_MONTH);
                    if (resolverStyle == ResolverStyle.SMART && iCheckValidIntValue4 > 28) {
                        iCheckValidIntValue4 = Math.min(iCheckValidIntValue4, date(iCheckValidIntValue2, iCheckValidIntValue3, 1).lengthOfMonth());
                    }
                    return date(iCheckValidIntValue2, iCheckValidIntValue3, iCheckValidIntValue4);
                }
                if (map.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                    if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                        int iCheckValidIntValue5 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            return date(iCheckValidIntValue5, 1, 1).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.MONTHS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                        }
                        int iCheckValidIntValue6 = ChronoField.MONTH_OF_YEAR.checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue());
                        JapaneseDate japaneseDatePlus = date(iCheckValidIntValue5, iCheckValidIntValue6, 1).plus(((ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue()) - 1) * 7) + (ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue()) - 1), (TemporalUnit) ChronoUnit.DAYS);
                        if (resolverStyle != ResolverStyle.STRICT || japaneseDatePlus.get(ChronoField.MONTH_OF_YEAR) == iCheckValidIntValue6) {
                            return japaneseDatePlus;
                        }
                        throw new DateTimeException("Strict mode rejected date parsed to a different month");
                    }
                    if (map.containsKey(ChronoField.DAY_OF_WEEK)) {
                        int iCheckValidIntValue7 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            return date(iCheckValidIntValue7, 1, 1).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.MONTHS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                        }
                        int iCheckValidIntValue8 = ChronoField.MONTH_OF_YEAR.checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue());
                        JapaneseDate japaneseDateWith = date(iCheckValidIntValue7, iCheckValidIntValue8, 1).plus(ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue()) - 1, (TemporalUnit) ChronoUnit.WEEKS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.remove(ChronoField.DAY_OF_WEEK).longValue()))));
                        if (resolverStyle != ResolverStyle.STRICT || japaneseDateWith.get(ChronoField.MONTH_OF_YEAR) == iCheckValidIntValue8) {
                            return japaneseDateWith;
                        }
                        throw new DateTimeException("Strict mode rejected date parsed to a different month");
                    }
                }
            }
            if (map.containsKey(ChronoField.DAY_OF_YEAR)) {
                int iCheckValidIntValue9 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return dateYearDay(iCheckValidIntValue9, 1).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_YEAR).longValue(), 1L));
                }
                return dateYearDay(iCheckValidIntValue9, ChronoField.DAY_OF_YEAR.checkValidIntValue(map.remove(ChronoField.DAY_OF_YEAR).longValue()));
            }
            if (map.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
                if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
                    int iCheckValidIntValue10 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return date(iCheckValidIntValue10, 1, 1).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                    }
                    JapaneseDate japaneseDatePlusDays = date(iCheckValidIntValue10, 1, 1).plusDays(((ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue()) - 1) * 7) + (ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue()) - 1));
                    if (resolverStyle != ResolverStyle.STRICT || japaneseDatePlusDays.get(ChronoField.YEAR) == iCheckValidIntValue10) {
                        return japaneseDatePlusDays;
                    }
                    throw new DateTimeException("Strict mode rejected date parsed to a different year");
                }
                if (map.containsKey(ChronoField.DAY_OF_WEEK)) {
                    int iCheckValidIntValue11 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return date(iCheckValidIntValue11, 1, 1).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                    }
                    JapaneseDate japaneseDateWith2 = date(iCheckValidIntValue11, 1, 1).plus(ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue()) - 1, (TemporalUnit) ChronoUnit.WEEKS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.remove(ChronoField.DAY_OF_WEEK).longValue()))));
                    if (resolverStyle != ResolverStyle.STRICT || japaneseDateWith2.get(ChronoField.YEAR) == iCheckValidIntValue11) {
                        return japaneseDateWith2;
                    }
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
            }
        }
        return null;
    }

    private JapaneseDate resolveEYMD(Map<TemporalField, Long> map, ResolverStyle resolverStyle, JapaneseEra japaneseEra, int i) {
        if (resolverStyle == ResolverStyle.LENIENT) {
            int year = (japaneseEra.startDate().getYear() + i) - 1;
            return date(year, 1, 1).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.MONTHS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
        }
        int iCheckValidIntValue = range(ChronoField.MONTH_OF_YEAR).checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), ChronoField.MONTH_OF_YEAR);
        int iCheckValidIntValue2 = range(ChronoField.DAY_OF_MONTH).checkValidIntValue(map.remove(ChronoField.DAY_OF_MONTH).longValue(), ChronoField.DAY_OF_MONTH);
        if (resolverStyle != ResolverStyle.SMART) {
            return date((Era) japaneseEra, i, iCheckValidIntValue, iCheckValidIntValue2);
        }
        if (i < 1) {
            throw new DateTimeException("Invalid YearOfEra: " + i);
        }
        int year2 = (japaneseEra.startDate().getYear() + i) - 1;
        if (iCheckValidIntValue2 > 28) {
            iCheckValidIntValue2 = Math.min(iCheckValidIntValue2, date(year2, iCheckValidIntValue, 1).lengthOfMonth());
        }
        JapaneseDate japaneseDateDate = date(year2, iCheckValidIntValue, iCheckValidIntValue2);
        if (japaneseDateDate.getEra() != japaneseEra) {
            if (Math.abs(japaneseDateDate.getEra().getValue() - japaneseEra.getValue()) > 1) {
                throw new DateTimeException("Invalid Era/YearOfEra: " + japaneseEra + StringUtils.SPACE + i);
            }
            if (japaneseDateDate.get(ChronoField.YEAR_OF_ERA) != 1 && i != 1) {
                throw new DateTimeException("Invalid Era/YearOfEra: " + japaneseEra + StringUtils.SPACE + i);
            }
        }
        return japaneseDateDate;
    }

    private JapaneseDate resolveEYD(Map<TemporalField, Long> map, ResolverStyle resolverStyle, JapaneseEra japaneseEra, int i) {
        if (resolverStyle == ResolverStyle.LENIENT) {
            int year = (japaneseEra.startDate().getYear() + i) - 1;
            return dateYearDay(year, 1).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
        }
        return dateYearDay((Era) japaneseEra, i, range(ChronoField.DAY_OF_YEAR).checkValidIntValue(map.remove(ChronoField.DAY_OF_YEAR).longValue(), ChronoField.DAY_OF_YEAR));
    }
}
