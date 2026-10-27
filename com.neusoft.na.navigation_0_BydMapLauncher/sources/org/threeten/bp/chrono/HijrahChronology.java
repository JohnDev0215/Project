package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public final class HijrahChronology extends Chronology implements Serializable {
    private static final HashMap<String, String[]> ERA_FULL_NAMES;
    private static final HashMap<String, String[]> ERA_NARROW_NAMES;
    private static final HashMap<String, String[]> ERA_SHORT_NAMES;
    private static final String FALLBACK_LANGUAGE = "en";
    public static final HijrahChronology INSTANCE = new HijrahChronology();
    private static final long serialVersionUID = 3127340209035924785L;

    @Override // org.threeten.bp.chrono.Chronology
    public String getCalendarType() {
        return "islamic-umalqura";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String getId() {
        return "Hijrah-umalqura";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public /* bridge */ /* synthetic */ ChronoLocalDate resolveDate(Map map, ResolverStyle resolverStyle) {
        return resolveDate((Map<TemporalField, Long>) map, resolverStyle);
    }

    static {
        HashMap<String, String[]> map = new HashMap<>();
        ERA_NARROW_NAMES = map;
        HashMap<String, String[]> map2 = new HashMap<>();
        ERA_SHORT_NAMES = map2;
        HashMap<String, String[]> map3 = new HashMap<>();
        ERA_FULL_NAMES = map3;
        map.put(FALLBACK_LANGUAGE, new String[]{"BH", "HE"});
        map2.put(FALLBACK_LANGUAGE, new String[]{"B.H.", "H.E."});
        map3.put(FALLBACK_LANGUAGE, new String[]{"Before Hijrah", "Hijrah Era"});
    }

    private HijrahChronology() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public HijrahDate date(Era era, int i, int i2, int i3) {
        return (HijrahDate) super.date(era, i, i2, i3);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public HijrahDate date(int i, int i2, int i3) {
        return HijrahDate.of(i, i2, i3);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public HijrahDate dateYearDay(Era era, int i, int i2) {
        return (HijrahDate) super.dateYearDay(era, i, i2);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public HijrahDate dateYearDay(int i, int i2) {
        return HijrahDate.of(i, 1, 1).plusDays(i2 - 1);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public HijrahDate dateEpochDay(long j) {
        return HijrahDate.of(LocalDate.ofEpochDay(j));
    }

    @Override // org.threeten.bp.chrono.Chronology
    public HijrahDate date(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof HijrahDate) {
            return (HijrahDate) temporalAccessor;
        }
        return HijrahDate.ofEpochDay(temporalAccessor.getLong(ChronoField.EPOCH_DAY));
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoLocalDateTime<HijrahDate> localDateTime(TemporalAccessor temporalAccessor) {
        return super.localDateTime(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<HijrahDate> zonedDateTime(TemporalAccessor temporalAccessor) {
        return super.zonedDateTime(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ChronoZonedDateTime<HijrahDate> zonedDateTime(Instant instant, ZoneId zoneId) {
        return super.zonedDateTime(instant, zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public HijrahDate dateNow() {
        return (HijrahDate) super.dateNow();
    }

    @Override // org.threeten.bp.chrono.Chronology
    public HijrahDate dateNow(ZoneId zoneId) {
        return (HijrahDate) super.dateNow(zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public HijrahDate dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return (HijrahDate) super.dateNow(clock);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public boolean isLeapYear(long j) {
        return HijrahDate.isLeapYear(j);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public int prolepticYear(Era era, int i) {
        if (era instanceof HijrahEra) {
            return era == HijrahEra.AH ? i : 1 - i;
        }
        throw new ClassCastException("Era must be HijrahEra");
    }

    @Override // org.threeten.bp.chrono.Chronology
    public HijrahEra eraOf(int i) {
        if (i == 0) {
            return HijrahEra.BEFORE_AH;
        }
        if (i == 1) {
            return HijrahEra.AH;
        }
        throw new DateTimeException("invalid Hijrah era");
    }

    @Override // org.threeten.bp.chrono.Chronology
    public List<Era> eras() {
        return Arrays.asList(HijrahEra.values());
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ValueRange range(ChronoField chronoField) {
        return chronoField.range();
    }

    @Override // org.threeten.bp.chrono.Chronology
    public HijrahDate resolveDate(Map<TemporalField, Long> map, ResolverStyle resolverStyle) {
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
        Long lRemove2 = map.remove(ChronoField.YEAR_OF_ERA);
        if (lRemove2 != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.YEAR_OF_ERA.checkValidValue(lRemove2.longValue());
            }
            Long lRemove3 = map.remove(ChronoField.ERA);
            if (lRemove3 == null) {
                Long l = map.get(ChronoField.YEAR);
                if (resolverStyle != ResolverStyle.STRICT) {
                    updateResolveMap(map, ChronoField.YEAR, (l == null || l.longValue() > 0) ? lRemove2.longValue() : Jdk8Methods.safeSubtract(1L, lRemove2.longValue()));
                } else if (l != null) {
                    updateResolveMap(map, ChronoField.YEAR, l.longValue() > 0 ? lRemove2.longValue() : Jdk8Methods.safeSubtract(1L, lRemove2.longValue()));
                } else {
                    map.put(ChronoField.YEAR_OF_ERA, lRemove2);
                }
            } else if (lRemove3.longValue() == 1) {
                updateResolveMap(map, ChronoField.YEAR, lRemove2.longValue());
            } else if (lRemove3.longValue() == 0) {
                updateResolveMap(map, ChronoField.YEAR, Jdk8Methods.safeSubtract(1L, lRemove2.longValue()));
            } else {
                throw new DateTimeException("Invalid value for era: " + lRemove3);
            }
        } else if (map.containsKey(ChronoField.ERA)) {
            ChronoField.ERA.checkValidValue(map.get(ChronoField.ERA).longValue());
        }
        if (!map.containsKey(ChronoField.YEAR)) {
            return null;
        }
        if (map.containsKey(ChronoField.MONTH_OF_YEAR)) {
            if (map.containsKey(ChronoField.DAY_OF_MONTH)) {
                int iCheckValidIntValue = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return date(iCheckValidIntValue, 1, 1).plusMonths(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_MONTH).longValue(), 1L));
                }
                int iCheckValidIntValue2 = range(ChronoField.MONTH_OF_YEAR).checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), ChronoField.MONTH_OF_YEAR);
                int iCheckValidIntValue3 = range(ChronoField.DAY_OF_MONTH).checkValidIntValue(map.remove(ChronoField.DAY_OF_MONTH).longValue(), ChronoField.DAY_OF_MONTH);
                if (resolverStyle == ResolverStyle.SMART && iCheckValidIntValue3 > 28) {
                    iCheckValidIntValue3 = Math.min(iCheckValidIntValue3, date(iCheckValidIntValue, iCheckValidIntValue2, 1).lengthOfMonth());
                }
                return date(iCheckValidIntValue, iCheckValidIntValue2, iCheckValidIntValue3);
            }
            if (map.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                    int iCheckValidIntValue4 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return date(iCheckValidIntValue4, 1, 1).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.MONTHS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                    }
                    int iCheckValidIntValue5 = ChronoField.MONTH_OF_YEAR.checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue());
                    HijrahDate hijrahDatePlus = date(iCheckValidIntValue4, iCheckValidIntValue5, 1).plus(((ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue()) - 1) * 7) + (ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue()) - 1), (TemporalUnit) ChronoUnit.DAYS);
                    if (resolverStyle != ResolverStyle.STRICT || hijrahDatePlus.get(ChronoField.MONTH_OF_YEAR) == iCheckValidIntValue5) {
                        return hijrahDatePlus;
                    }
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
                if (map.containsKey(ChronoField.DAY_OF_WEEK)) {
                    int iCheckValidIntValue6 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return date(iCheckValidIntValue6, 1, 1).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.MONTHS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
                    }
                    int iCheckValidIntValue7 = ChronoField.MONTH_OF_YEAR.checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue());
                    HijrahDate hijrahDateWith = date(iCheckValidIntValue6, iCheckValidIntValue7, 1).plus(ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue()) - 1, (TemporalUnit) ChronoUnit.WEEKS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.remove(ChronoField.DAY_OF_WEEK).longValue()))));
                    if (resolverStyle != ResolverStyle.STRICT || hijrahDateWith.get(ChronoField.MONTH_OF_YEAR) == iCheckValidIntValue7) {
                        return hijrahDateWith;
                    }
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
            }
        }
        if (map.containsKey(ChronoField.DAY_OF_YEAR)) {
            int iCheckValidIntValue8 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
            if (resolverStyle == ResolverStyle.LENIENT) {
                return dateYearDay(iCheckValidIntValue8, 1).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_YEAR).longValue(), 1L));
            }
            return dateYearDay(iCheckValidIntValue8, ChronoField.DAY_OF_YEAR.checkValidIntValue(map.remove(ChronoField.DAY_OF_YEAR).longValue()));
        }
        if (!map.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
            return null;
        }
        if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
            int iCheckValidIntValue9 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
            if (resolverStyle == ResolverStyle.LENIENT) {
                return date(iCheckValidIntValue9, 1, 1).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
            }
            HijrahDate hijrahDatePlusDays = date(iCheckValidIntValue9, 1, 1).plusDays(((ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue()) - 1) * 7) + (ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue()) - 1));
            if (resolverStyle != ResolverStyle.STRICT || hijrahDatePlusDays.get(ChronoField.YEAR) == iCheckValidIntValue9) {
                return hijrahDatePlusDays;
            }
            throw new DateTimeException("Strict mode rejected date parsed to a different year");
        }
        if (!map.containsKey(ChronoField.DAY_OF_WEEK)) {
            return null;
        }
        int iCheckValidIntValue10 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
        if (resolverStyle == ResolverStyle.LENIENT) {
            return date(iCheckValidIntValue10, 1, 1).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L), (TemporalUnit) ChronoUnit.DAYS);
        }
        HijrahDate hijrahDateWith2 = date(iCheckValidIntValue10, 1, 1).plus(ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue()) - 1, (TemporalUnit) ChronoUnit.WEEKS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.remove(ChronoField.DAY_OF_WEEK).longValue()))));
        if (resolverStyle != ResolverStyle.STRICT || hijrahDateWith2.get(ChronoField.YEAR) == iCheckValidIntValue10) {
            return hijrahDateWith2;
        }
        throw new DateTimeException("Strict mode rejected date parsed to a different month");
    }
}
