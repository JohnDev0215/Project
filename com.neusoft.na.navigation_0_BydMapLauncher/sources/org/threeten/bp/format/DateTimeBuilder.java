package org.threeten.bp.format;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoZonedDateTime;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;

/* JADX INFO: loaded from: classes4.dex */
final class DateTimeBuilder extends DefaultInterfaceTemporalAccessor implements TemporalAccessor, Cloneable {
    Chronology chrono;
    ChronoLocalDate date;
    Period excessDays;
    final Map<TemporalField, Long> fieldValues = new HashMap();
    boolean leapSecond;
    LocalTime time;
    ZoneId zone;

    public DateTimeBuilder() {
    }

    public DateTimeBuilder(TemporalField temporalField, long j) {
        addFieldValue(temporalField, j);
    }

    private Long getFieldValue0(TemporalField temporalField) {
        return this.fieldValues.get(temporalField);
    }

    DateTimeBuilder addFieldValue(TemporalField temporalField, long j) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Long fieldValue0 = getFieldValue0(temporalField);
        if (fieldValue0 != null && fieldValue0.longValue() != j) {
            throw new DateTimeException("Conflict found: " + temporalField + StringUtils.SPACE + fieldValue0 + " differs from " + temporalField + StringUtils.SPACE + j + ": " + this);
        }
        return putFieldValue0(temporalField, j);
    }

    private DateTimeBuilder putFieldValue0(TemporalField temporalField, long j) {
        this.fieldValues.put(temporalField, Long.valueOf(j));
        return this;
    }

    void addObject(ChronoLocalDate chronoLocalDate) {
        this.date = chronoLocalDate;
    }

    void addObject(LocalTime localTime) {
        this.time = localTime;
    }

    public DateTimeBuilder resolve(ResolverStyle resolverStyle, Set<TemporalField> set) {
        ChronoLocalDate chronoLocalDate;
        if (set != null) {
            this.fieldValues.keySet().retainAll(set);
        }
        mergeInstantFields();
        mergeDate(resolverStyle);
        mergeTime(resolverStyle);
        if (resolveFields(resolverStyle)) {
            mergeInstantFields();
            mergeDate(resolverStyle);
            mergeTime(resolverStyle);
        }
        resolveTimeInferZeroes(resolverStyle);
        crossCheck();
        Period period = this.excessDays;
        if (period != null && !period.isZero() && (chronoLocalDate = this.date) != null && this.time != null) {
            this.date = chronoLocalDate.plus((TemporalAmount) this.excessDays);
            this.excessDays = Period.ZERO;
        }
        resolveFractional();
        resolveInstant();
        return this;
    }

    private boolean resolveFields(ResolverStyle resolverStyle) {
        int i = 0;
        loop0: while (i < 100) {
            Iterator<Map.Entry<TemporalField, Long>> it = this.fieldValues.entrySet().iterator();
            while (it.hasNext()) {
                TemporalField key = it.next().getKey();
                TemporalAccessor temporalAccessorResolve = key.resolve(this.fieldValues, this, resolverStyle);
                if (temporalAccessorResolve != null) {
                    if (temporalAccessorResolve instanceof ChronoZonedDateTime) {
                        ChronoZonedDateTime chronoZonedDateTime = (ChronoZonedDateTime) temporalAccessorResolve;
                        ZoneId zoneId = this.zone;
                        if (zoneId == null) {
                            this.zone = chronoZonedDateTime.getZone();
                        } else if (!zoneId.equals(chronoZonedDateTime.getZone())) {
                            throw new DateTimeException("ChronoZonedDateTime must use the effective parsed zone: " + this.zone);
                        }
                        temporalAccessorResolve = chronoZonedDateTime.toLocalDateTime();
                    }
                    if (temporalAccessorResolve instanceof ChronoLocalDate) {
                        resolveMakeChanges(key, (ChronoLocalDate) temporalAccessorResolve);
                    } else if (temporalAccessorResolve instanceof LocalTime) {
                        resolveMakeChanges(key, (LocalTime) temporalAccessorResolve);
                    } else if (temporalAccessorResolve instanceof ChronoLocalDateTime) {
                        ChronoLocalDateTime chronoLocalDateTime = (ChronoLocalDateTime) temporalAccessorResolve;
                        resolveMakeChanges(key, chronoLocalDateTime.toLocalDate());
                        resolveMakeChanges(key, chronoLocalDateTime.toLocalTime());
                    } else {
                        throw new DateTimeException("Unknown type: " + temporalAccessorResolve.getClass().getName());
                    }
                } else if (!this.fieldValues.containsKey(key)) {
                    break;
                }
                i++;
            }
        }
        if (i != 100) {
            return i > 0;
        }
        throw new DateTimeException("Badly written field");
    }

    private void resolveMakeChanges(TemporalField temporalField, ChronoLocalDate chronoLocalDate) {
        if (!this.chrono.equals(chronoLocalDate.getChronology())) {
            throw new DateTimeException("ChronoLocalDate must use the effective parsed chronology: " + this.chrono);
        }
        long epochDay = chronoLocalDate.toEpochDay();
        Long lPut = this.fieldValues.put(ChronoField.EPOCH_DAY, Long.valueOf(epochDay));
        if (lPut != null && lPut.longValue() != epochDay) {
            throw new DateTimeException("Conflict found: " + LocalDate.ofEpochDay(lPut.longValue()) + " differs from " + LocalDate.ofEpochDay(epochDay) + " while resolving  " + temporalField);
        }
    }

    private void resolveMakeChanges(TemporalField temporalField, LocalTime localTime) {
        long nanoOfDay = localTime.toNanoOfDay();
        Long lPut = this.fieldValues.put(ChronoField.NANO_OF_DAY, Long.valueOf(nanoOfDay));
        if (lPut != null && lPut.longValue() != nanoOfDay) {
            throw new DateTimeException("Conflict found: " + LocalTime.ofNanoOfDay(lPut.longValue()) + " differs from " + localTime + " while resolving  " + temporalField);
        }
    }

    private void mergeDate(ResolverStyle resolverStyle) {
        if (this.chrono instanceof IsoChronology) {
            checkDate(IsoChronology.INSTANCE.resolveDate(this.fieldValues, resolverStyle));
        } else if (this.fieldValues.containsKey(ChronoField.EPOCH_DAY)) {
            checkDate(LocalDate.ofEpochDay(this.fieldValues.remove(ChronoField.EPOCH_DAY).longValue()));
        }
    }

    private void checkDate(LocalDate localDate) {
        if (localDate != null) {
            addObject(localDate);
            for (TemporalField temporalField : this.fieldValues.keySet()) {
                if ((temporalField instanceof ChronoField) && temporalField.isDateBased()) {
                    try {
                        long j = localDate.getLong(temporalField);
                        Long l = this.fieldValues.get(temporalField);
                        if (j != l.longValue()) {
                            throw new DateTimeException("Conflict found: Field " + temporalField + StringUtils.SPACE + j + " differs from " + temporalField + StringUtils.SPACE + l + " derived from " + localDate);
                        }
                    } catch (DateTimeException unused) {
                        continue;
                    }
                }
            }
        }
    }

    private void mergeTime(ResolverStyle resolverStyle) {
        if (this.fieldValues.containsKey(ChronoField.CLOCK_HOUR_OF_DAY)) {
            long jLongValue = this.fieldValues.remove(ChronoField.CLOCK_HOUR_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT && (resolverStyle != ResolverStyle.SMART || jLongValue != 0)) {
                ChronoField.CLOCK_HOUR_OF_DAY.checkValidValue(jLongValue);
            }
            ChronoField chronoField = ChronoField.HOUR_OF_DAY;
            if (jLongValue == 24) {
                jLongValue = 0;
            }
            addFieldValue(chronoField, jLongValue);
        }
        if (this.fieldValues.containsKey(ChronoField.CLOCK_HOUR_OF_AMPM)) {
            long jLongValue2 = this.fieldValues.remove(ChronoField.CLOCK_HOUR_OF_AMPM).longValue();
            if (resolverStyle != ResolverStyle.LENIENT && (resolverStyle != ResolverStyle.SMART || jLongValue2 != 0)) {
                ChronoField.CLOCK_HOUR_OF_AMPM.checkValidValue(jLongValue2);
            }
            addFieldValue(ChronoField.HOUR_OF_AMPM, jLongValue2 != 12 ? jLongValue2 : 0L);
        }
        if (resolverStyle != ResolverStyle.LENIENT) {
            if (this.fieldValues.containsKey(ChronoField.AMPM_OF_DAY)) {
                ChronoField.AMPM_OF_DAY.checkValidValue(this.fieldValues.get(ChronoField.AMPM_OF_DAY).longValue());
            }
            if (this.fieldValues.containsKey(ChronoField.HOUR_OF_AMPM)) {
                ChronoField.HOUR_OF_AMPM.checkValidValue(this.fieldValues.get(ChronoField.HOUR_OF_AMPM).longValue());
            }
        }
        if (this.fieldValues.containsKey(ChronoField.AMPM_OF_DAY) && this.fieldValues.containsKey(ChronoField.HOUR_OF_AMPM)) {
            addFieldValue(ChronoField.HOUR_OF_DAY, (this.fieldValues.remove(ChronoField.AMPM_OF_DAY).longValue() * 12) + this.fieldValues.remove(ChronoField.HOUR_OF_AMPM).longValue());
        }
        if (this.fieldValues.containsKey(ChronoField.NANO_OF_DAY)) {
            long jLongValue3 = this.fieldValues.remove(ChronoField.NANO_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.NANO_OF_DAY.checkValidValue(jLongValue3);
            }
            addFieldValue(ChronoField.SECOND_OF_DAY, jLongValue3 / 1000000000);
            addFieldValue(ChronoField.NANO_OF_SECOND, jLongValue3 % 1000000000);
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_DAY)) {
            long jLongValue4 = this.fieldValues.remove(ChronoField.MICRO_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MICRO_OF_DAY.checkValidValue(jLongValue4);
            }
            addFieldValue(ChronoField.SECOND_OF_DAY, jLongValue4 / 1000000);
            addFieldValue(ChronoField.MICRO_OF_SECOND, jLongValue4 % 1000000);
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_DAY)) {
            long jLongValue5 = this.fieldValues.remove(ChronoField.MILLI_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MILLI_OF_DAY.checkValidValue(jLongValue5);
            }
            addFieldValue(ChronoField.SECOND_OF_DAY, jLongValue5 / 1000);
            addFieldValue(ChronoField.MILLI_OF_SECOND, jLongValue5 % 1000);
        }
        if (this.fieldValues.containsKey(ChronoField.SECOND_OF_DAY)) {
            long jLongValue6 = this.fieldValues.remove(ChronoField.SECOND_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.SECOND_OF_DAY.checkValidValue(jLongValue6);
            }
            addFieldValue(ChronoField.HOUR_OF_DAY, jLongValue6 / 3600);
            addFieldValue(ChronoField.MINUTE_OF_HOUR, (jLongValue6 / 60) % 60);
            addFieldValue(ChronoField.SECOND_OF_MINUTE, jLongValue6 % 60);
        }
        if (this.fieldValues.containsKey(ChronoField.MINUTE_OF_DAY)) {
            long jLongValue7 = this.fieldValues.remove(ChronoField.MINUTE_OF_DAY).longValue();
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.MINUTE_OF_DAY.checkValidValue(jLongValue7);
            }
            addFieldValue(ChronoField.HOUR_OF_DAY, jLongValue7 / 60);
            addFieldValue(ChronoField.MINUTE_OF_HOUR, jLongValue7 % 60);
        }
        if (resolverStyle != ResolverStyle.LENIENT) {
            if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND)) {
                ChronoField.MILLI_OF_SECOND.checkValidValue(this.fieldValues.get(ChronoField.MILLI_OF_SECOND).longValue());
            }
            if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
                ChronoField.MICRO_OF_SECOND.checkValidValue(this.fieldValues.get(ChronoField.MICRO_OF_SECOND).longValue());
            }
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND) && this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
            addFieldValue(ChronoField.MICRO_OF_SECOND, (this.fieldValues.remove(ChronoField.MILLI_OF_SECOND).longValue() * 1000) + (this.fieldValues.get(ChronoField.MICRO_OF_SECOND).longValue() % 1000));
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND) && this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
            addFieldValue(ChronoField.MICRO_OF_SECOND, this.fieldValues.get(ChronoField.NANO_OF_SECOND).longValue() / 1000);
            this.fieldValues.remove(ChronoField.MICRO_OF_SECOND);
        }
        if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND) && this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
            addFieldValue(ChronoField.MILLI_OF_SECOND, this.fieldValues.get(ChronoField.NANO_OF_SECOND).longValue() / 1000000);
            this.fieldValues.remove(ChronoField.MILLI_OF_SECOND);
        }
        if (this.fieldValues.containsKey(ChronoField.MICRO_OF_SECOND)) {
            addFieldValue(ChronoField.NANO_OF_SECOND, this.fieldValues.remove(ChronoField.MICRO_OF_SECOND).longValue() * 1000);
        } else if (this.fieldValues.containsKey(ChronoField.MILLI_OF_SECOND)) {
            addFieldValue(ChronoField.NANO_OF_SECOND, this.fieldValues.remove(ChronoField.MILLI_OF_SECOND).longValue() * 1000000);
        }
    }

    private void resolveTimeInferZeroes(ResolverStyle resolverStyle) {
        Long l = this.fieldValues.get(ChronoField.HOUR_OF_DAY);
        Long l2 = this.fieldValues.get(ChronoField.MINUTE_OF_HOUR);
        Long l3 = this.fieldValues.get(ChronoField.SECOND_OF_MINUTE);
        Long l4 = this.fieldValues.get(ChronoField.NANO_OF_SECOND);
        if (l == null) {
            return;
        }
        if (l2 != null || (l3 == null && l4 == null)) {
            if (l2 == null || l3 != null || l4 == null) {
                if (resolverStyle != ResolverStyle.LENIENT) {
                    if (l != null) {
                        if (resolverStyle == ResolverStyle.SMART && l.longValue() == 24 && ((l2 == null || l2.longValue() == 0) && ((l3 == null || l3.longValue() == 0) && (l4 == null || l4.longValue() == 0)))) {
                            l = 0L;
                            this.excessDays = Period.ofDays(1);
                        }
                        int iCheckValidIntValue = ChronoField.HOUR_OF_DAY.checkValidIntValue(l.longValue());
                        if (l2 != null) {
                            int iCheckValidIntValue2 = ChronoField.MINUTE_OF_HOUR.checkValidIntValue(l2.longValue());
                            if (l3 != null) {
                                int iCheckValidIntValue3 = ChronoField.SECOND_OF_MINUTE.checkValidIntValue(l3.longValue());
                                if (l4 != null) {
                                    addObject(LocalTime.of(iCheckValidIntValue, iCheckValidIntValue2, iCheckValidIntValue3, ChronoField.NANO_OF_SECOND.checkValidIntValue(l4.longValue())));
                                } else {
                                    addObject(LocalTime.of(iCheckValidIntValue, iCheckValidIntValue2, iCheckValidIntValue3));
                                }
                            } else if (l4 == null) {
                                addObject(LocalTime.of(iCheckValidIntValue, iCheckValidIntValue2));
                            }
                        } else if (l3 == null && l4 == null) {
                            addObject(LocalTime.of(iCheckValidIntValue, 0));
                        }
                    }
                } else if (l != null) {
                    long jLongValue = l.longValue();
                    if (l2 == null) {
                        int iSafeToInt = Jdk8Methods.safeToInt(Jdk8Methods.floorDiv(jLongValue, 24L));
                        addObject(LocalTime.of(Jdk8Methods.floorMod(jLongValue, 24), 0));
                        this.excessDays = Period.ofDays(iSafeToInt);
                    } else if (l3 != null) {
                        if (l4 == null) {
                            l4 = 0L;
                        }
                        long jSafeAdd = Jdk8Methods.safeAdd(Jdk8Methods.safeAdd(Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(jLongValue, 3600000000000L), Jdk8Methods.safeMultiply(l2.longValue(), 60000000000L)), Jdk8Methods.safeMultiply(l3.longValue(), 1000000000L)), l4.longValue());
                        int iFloorDiv = (int) Jdk8Methods.floorDiv(jSafeAdd, 86400000000000L);
                        addObject(LocalTime.ofNanoOfDay(Jdk8Methods.floorMod(jSafeAdd, 86400000000000L)));
                        this.excessDays = Period.ofDays(iFloorDiv);
                    } else {
                        long jSafeAdd2 = Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(jLongValue, 3600L), Jdk8Methods.safeMultiply(l2.longValue(), 60L));
                        int iFloorDiv2 = (int) Jdk8Methods.floorDiv(jSafeAdd2, 86400L);
                        addObject(LocalTime.ofSecondOfDay(Jdk8Methods.floorMod(jSafeAdd2, 86400L)));
                        this.excessDays = Period.ofDays(iFloorDiv2);
                    }
                }
                this.fieldValues.remove(ChronoField.HOUR_OF_DAY);
                this.fieldValues.remove(ChronoField.MINUTE_OF_HOUR);
                this.fieldValues.remove(ChronoField.SECOND_OF_MINUTE);
                this.fieldValues.remove(ChronoField.NANO_OF_SECOND);
            }
        }
    }

    private void mergeInstantFields() {
        if (this.fieldValues.containsKey(ChronoField.INSTANT_SECONDS)) {
            ZoneId zoneId = this.zone;
            if (zoneId != null) {
                mergeInstantFields0(zoneId);
                return;
            }
            Long l = this.fieldValues.get(ChronoField.OFFSET_SECONDS);
            if (l != null) {
                mergeInstantFields0(ZoneOffset.ofTotalSeconds(l.intValue()));
            }
        }
    }

    private void mergeInstantFields0(ZoneId zoneId) {
        ChronoZonedDateTime<?> chronoZonedDateTimeZonedDateTime = this.chrono.zonedDateTime(Instant.ofEpochSecond(this.fieldValues.remove(ChronoField.INSTANT_SECONDS).longValue()), zoneId);
        if (this.date == null) {
            addObject(chronoZonedDateTimeZonedDateTime.toLocalDate());
        } else {
            resolveMakeChanges(ChronoField.INSTANT_SECONDS, chronoZonedDateTimeZonedDateTime.toLocalDate());
        }
        addFieldValue(ChronoField.SECOND_OF_DAY, chronoZonedDateTimeZonedDateTime.toLocalTime().toSecondOfDay());
    }

    private void crossCheck() {
        LocalTime localTime;
        if (this.fieldValues.size() > 0) {
            ChronoLocalDate chronoLocalDate = this.date;
            if (chronoLocalDate != null && (localTime = this.time) != null) {
                crossCheck(chronoLocalDate.atTime(localTime));
                return;
            }
            if (chronoLocalDate != null) {
                crossCheck(chronoLocalDate);
                return;
            }
            TemporalAccessor temporalAccessor = this.time;
            if (temporalAccessor != null) {
                crossCheck(temporalAccessor);
            }
        }
    }

    private void crossCheck(TemporalAccessor temporalAccessor) {
        Iterator<Map.Entry<TemporalField, Long>> it = this.fieldValues.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<TemporalField, Long> next = it.next();
            TemporalField key = next.getKey();
            long jLongValue = next.getValue().longValue();
            if (temporalAccessor.isSupported(key)) {
                try {
                    long j = temporalAccessor.getLong(key);
                    if (j != jLongValue) {
                        throw new DateTimeException("Cross check failed: " + key + StringUtils.SPACE + j + " vs " + key + StringUtils.SPACE + jLongValue);
                    }
                    it.remove();
                } catch (RuntimeException unused) {
                    continue;
                }
            }
        }
    }

    private void resolveFractional() {
        if (this.time == null) {
            if (this.fieldValues.containsKey(ChronoField.INSTANT_SECONDS) || this.fieldValues.containsKey(ChronoField.SECOND_OF_DAY) || this.fieldValues.containsKey(ChronoField.SECOND_OF_MINUTE)) {
                if (this.fieldValues.containsKey(ChronoField.NANO_OF_SECOND)) {
                    long jLongValue = this.fieldValues.get(ChronoField.NANO_OF_SECOND).longValue();
                    this.fieldValues.put(ChronoField.MICRO_OF_SECOND, Long.valueOf(jLongValue / 1000));
                    this.fieldValues.put(ChronoField.MILLI_OF_SECOND, Long.valueOf(jLongValue / 1000000));
                } else {
                    this.fieldValues.put(ChronoField.NANO_OF_SECOND, 0L);
                    this.fieldValues.put(ChronoField.MICRO_OF_SECOND, 0L);
                    this.fieldValues.put(ChronoField.MILLI_OF_SECOND, 0L);
                }
            }
        }
    }

    private void resolveInstant() {
        LocalTime localTime;
        ChronoLocalDate chronoLocalDate = this.date;
        if (chronoLocalDate == null || (localTime = this.time) == null) {
            return;
        }
        if (this.zone != null) {
            this.fieldValues.put(ChronoField.INSTANT_SECONDS, Long.valueOf(chronoLocalDate.atTime(localTime).atZone(this.zone).getLong(ChronoField.INSTANT_SECONDS)));
            return;
        }
        Long l = this.fieldValues.get(ChronoField.OFFSET_SECONDS);
        if (l != null) {
            this.fieldValues.put(ChronoField.INSTANT_SECONDS, Long.valueOf(this.date.atTime(this.time).atZone(ZoneOffset.ofTotalSeconds(l.intValue())).getLong(ChronoField.INSTANT_SECONDS)));
        }
    }

    public <R> R build(TemporalQuery<R> temporalQuery) {
        return temporalQuery.queryFrom(this);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        ChronoLocalDate chronoLocalDate;
        LocalTime localTime;
        if (temporalField == null) {
            return false;
        }
        return this.fieldValues.containsKey(temporalField) || ((chronoLocalDate = this.date) != null && chronoLocalDate.isSupported(temporalField)) || ((localTime = this.time) != null && localTime.isSupported(temporalField));
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Long fieldValue0 = getFieldValue0(temporalField);
        if (fieldValue0 == null) {
            ChronoLocalDate chronoLocalDate = this.date;
            if (chronoLocalDate != null && chronoLocalDate.isSupported(temporalField)) {
                return this.date.getLong(temporalField);
            }
            LocalTime localTime = this.time;
            if (localTime != null && localTime.isSupported(temporalField)) {
                return this.time.getLong(temporalField);
            }
            throw new DateTimeException("Field not found: " + temporalField);
        }
        return fieldValue0.longValue();
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.zoneId()) {
            return (R) this.zone;
        }
        if (temporalQuery == TemporalQueries.chronology()) {
            return (R) this.chrono;
        }
        if (temporalQuery == TemporalQueries.localDate()) {
            ChronoLocalDate chronoLocalDate = this.date;
            if (chronoLocalDate != null) {
                return (R) LocalDate.from((TemporalAccessor) chronoLocalDate);
            }
            return null;
        }
        if (temporalQuery == TemporalQueries.localTime()) {
            return (R) this.time;
        }
        if (temporalQuery == TemporalQueries.zone() || temporalQuery == TemporalQueries.offset()) {
            return temporalQuery.queryFrom(this);
        }
        if (temporalQuery == TemporalQueries.precision()) {
            return null;
        }
        return temporalQuery.queryFrom(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("DateTimeBuilder[");
        if (this.fieldValues.size() > 0) {
            sb.append("fields=").append(this.fieldValues);
        }
        sb.append(", ").append(this.chrono);
        sb.append(", ").append(this.zone);
        sb.append(", ").append(this.date);
        sb.append(", ").append(this.time);
        sb.append(']');
        return sb.toString();
    }
}
