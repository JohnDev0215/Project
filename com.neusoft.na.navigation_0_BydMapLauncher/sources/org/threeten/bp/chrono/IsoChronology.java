package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Month;
import org.threeten.bp.Year;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.ValueRange;

/* JADX INFO: loaded from: classes4.dex */
public final class IsoChronology extends Chronology implements Serializable {
    public static final IsoChronology INSTANCE = new IsoChronology();
    private static final long serialVersionUID = -1440403870442975015L;

    @Override // org.threeten.bp.chrono.Chronology
    public String getCalendarType() {
        return "iso8601";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public String getId() {
        return "ISO";
    }

    @Override // org.threeten.bp.chrono.Chronology
    public /* bridge */ /* synthetic */ ChronoLocalDate resolveDate(Map map, ResolverStyle resolverStyle) {
        return resolveDate((Map<TemporalField, Long>) map, resolverStyle);
    }

    private IsoChronology() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // org.threeten.bp.chrono.Chronology
    public LocalDate date(Era era, int i, int i2, int i3) {
        return date(prolepticYear(era, i), i2, i3);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public LocalDate date(int i, int i2, int i3) {
        return LocalDate.of(i, i2, i3);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public LocalDate dateYearDay(Era era, int i, int i2) {
        return dateYearDay(prolepticYear(era, i), i2);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public LocalDate dateYearDay(int i, int i2) {
        return LocalDate.ofYearDay(i, i2);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public LocalDate dateEpochDay(long j) {
        return LocalDate.ofEpochDay(j);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public LocalDate date(TemporalAccessor temporalAccessor) {
        return LocalDate.from(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public LocalDateTime localDateTime(TemporalAccessor temporalAccessor) {
        return LocalDateTime.from(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ZonedDateTime zonedDateTime(TemporalAccessor temporalAccessor) {
        return ZonedDateTime.from(temporalAccessor);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ZonedDateTime zonedDateTime(Instant instant, ZoneId zoneId) {
        return ZonedDateTime.ofInstant(instant, zoneId);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public LocalDate dateNow() {
        return dateNow(Clock.systemDefaultZone());
    }

    @Override // org.threeten.bp.chrono.Chronology
    public LocalDate dateNow(ZoneId zoneId) {
        return dateNow(Clock.system(zoneId));
    }

    @Override // org.threeten.bp.chrono.Chronology
    public LocalDate dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return date((TemporalAccessor) LocalDate.now(clock));
    }

    @Override // org.threeten.bp.chrono.Chronology
    public boolean isLeapYear(long j) {
        return (3 & j) == 0 && (j % 100 != 0 || j % 400 == 0);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public int prolepticYear(Era era, int i) {
        if (era instanceof IsoEra) {
            return era == IsoEra.CE ? i : 1 - i;
        }
        throw new ClassCastException("Era must be IsoEra");
    }

    @Override // org.threeten.bp.chrono.Chronology
    public IsoEra eraOf(int i) {
        return IsoEra.of(i);
    }

    @Override // org.threeten.bp.chrono.Chronology
    public List<Era> eras() {
        return Arrays.asList(IsoEra.values());
    }

    @Override // org.threeten.bp.chrono.Chronology
    public ValueRange range(ChronoField chronoField) {
        return chronoField.range();
    }

    @Override // org.threeten.bp.chrono.Chronology
    public LocalDate resolveDate(Map<TemporalField, Long> map, ResolverStyle resolverStyle) {
        if (map.containsKey(ChronoField.EPOCH_DAY)) {
            return LocalDate.ofEpochDay(map.remove(ChronoField.EPOCH_DAY).longValue());
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
                int iSafeToInt = Jdk8Methods.safeToInt(map.remove(ChronoField.MONTH_OF_YEAR).longValue());
                int iSafeToInt2 = Jdk8Methods.safeToInt(map.remove(ChronoField.DAY_OF_MONTH).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return LocalDate.of(iCheckValidIntValue, 1, 1).plusMonths(Jdk8Methods.safeSubtract(iSafeToInt, 1)).plusDays(Jdk8Methods.safeSubtract(iSafeToInt2, 1));
                }
                if (resolverStyle == ResolverStyle.SMART) {
                    ChronoField.DAY_OF_MONTH.checkValidValue(iSafeToInt2);
                    if (iSafeToInt == 4 || iSafeToInt == 6 || iSafeToInt == 9 || iSafeToInt == 11) {
                        iSafeToInt2 = Math.min(iSafeToInt2, 30);
                    } else if (iSafeToInt == 2) {
                        iSafeToInt2 = Math.min(iSafeToInt2, Month.FEBRUARY.length(Year.isLeap(iCheckValidIntValue)));
                    }
                    return LocalDate.of(iCheckValidIntValue, iSafeToInt, iSafeToInt2);
                }
                return LocalDate.of(iCheckValidIntValue, iSafeToInt, iSafeToInt2);
            }
            if (map.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                    int iCheckValidIntValue2 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return LocalDate.of(iCheckValidIntValue2, 1, 1).plusMonths(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L)).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue(), 1L));
                    }
                    int iCheckValidIntValue3 = ChronoField.MONTH_OF_YEAR.checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue());
                    LocalDate localDatePlusDays = LocalDate.of(iCheckValidIntValue2, iCheckValidIntValue3, 1).plusDays(((ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue()) - 1) * 7) + (ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue()) - 1));
                    if (resolverStyle != ResolverStyle.STRICT || localDatePlusDays.get(ChronoField.MONTH_OF_YEAR) == iCheckValidIntValue3) {
                        return localDatePlusDays;
                    }
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
                if (map.containsKey(ChronoField.DAY_OF_WEEK)) {
                    int iCheckValidIntValue4 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return LocalDate.of(iCheckValidIntValue4, 1, 1).plusMonths(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1L)).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L));
                    }
                    int iCheckValidIntValue5 = ChronoField.MONTH_OF_YEAR.checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue());
                    LocalDate localDateWith = LocalDate.of(iCheckValidIntValue4, iCheckValidIntValue5, 1).plusWeeks(ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue()) - 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.remove(ChronoField.DAY_OF_WEEK).longValue()))));
                    if (resolverStyle != ResolverStyle.STRICT || localDateWith.get(ChronoField.MONTH_OF_YEAR) == iCheckValidIntValue5) {
                        return localDateWith;
                    }
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
            }
        }
        if (map.containsKey(ChronoField.DAY_OF_YEAR)) {
            int iCheckValidIntValue6 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
            if (resolverStyle == ResolverStyle.LENIENT) {
                return LocalDate.ofYearDay(iCheckValidIntValue6, 1).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_YEAR).longValue(), 1L));
            }
            return LocalDate.ofYearDay(iCheckValidIntValue6, ChronoField.DAY_OF_YEAR.checkValidIntValue(map.remove(ChronoField.DAY_OF_YEAR).longValue()));
        }
        if (!map.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
            return null;
        }
        if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
            int iCheckValidIntValue7 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
            if (resolverStyle == ResolverStyle.LENIENT) {
                return LocalDate.of(iCheckValidIntValue7, 1, 1).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue(), 1L));
            }
            LocalDate localDatePlusDays2 = LocalDate.of(iCheckValidIntValue7, 1, 1).plusDays(((ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue()) - 1) * 7) + (ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue()) - 1));
            if (resolverStyle != ResolverStyle.STRICT || localDatePlusDays2.get(ChronoField.YEAR) == iCheckValidIntValue7) {
                return localDatePlusDays2;
            }
            throw new DateTimeException("Strict mode rejected date parsed to a different year");
        }
        if (!map.containsKey(ChronoField.DAY_OF_WEEK)) {
            return null;
        }
        int iCheckValidIntValue8 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
        if (resolverStyle == ResolverStyle.LENIENT) {
            return LocalDate.of(iCheckValidIntValue8, 1, 1).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1L)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1L));
        }
        LocalDate localDateWith2 = LocalDate.of(iCheckValidIntValue8, 1, 1).plusWeeks(ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue()) - 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.remove(ChronoField.DAY_OF_WEEK).longValue()))));
        if (resolverStyle != ResolverStyle.STRICT || localDateWith2.get(ChronoField.YEAR) == iCheckValidIntValue8) {
            return localDateWith2;
        }
        throw new DateTimeException("Strict mode rejected date parsed to a different month");
    }
}
