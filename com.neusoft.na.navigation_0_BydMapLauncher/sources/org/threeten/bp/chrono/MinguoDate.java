package org.threeten.bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import org.threeten.bp.Clock;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Period;
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
public final class MinguoDate extends ChronoDateImpl<MinguoDate> implements Serializable {
    private static final long serialVersionUID = 1300372329181994526L;
    private final LocalDate isoDate;

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.temporal.Temporal
    public /* bridge */ /* synthetic */ long until(Temporal temporal, TemporalUnit temporalUnit) {
        return super.until(temporal, temporalUnit);
    }

    public static MinguoDate now() {
        return now(Clock.systemDefaultZone());
    }

    public static MinguoDate now(ZoneId zoneId) {
        return now(Clock.system(zoneId));
    }

    public static MinguoDate now(Clock clock) {
        return new MinguoDate(LocalDate.now(clock));
    }

    public static MinguoDate of(int i, int i2, int i3) {
        return MinguoChronology.INSTANCE.date(i, i2, i3);
    }

    public static MinguoDate from(TemporalAccessor temporalAccessor) {
        return MinguoChronology.INSTANCE.date(temporalAccessor);
    }

    MinguoDate(LocalDate localDate) {
        Jdk8Methods.requireNonNull(localDate, "date");
        this.isoDate = localDate;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public MinguoChronology getChronology() {
        return MinguoChronology.INSTANCE;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public MinguoEra getEra() {
        return (MinguoEra) super.getEra();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int lengthOfMonth() {
        return this.isoDate.lengthOfMonth();
    }

    @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            if (isSupported(temporalField)) {
                ChronoField chronoField = (ChronoField) temporalField;
                int i = AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoField[chronoField.ordinal()];
                if (i == 1 || i == 2 || i == 3) {
                    return this.isoDate.range(temporalField);
                }
                if (i == 4) {
                    ValueRange valueRangeRange = ChronoField.YEAR.range();
                    return ValueRange.of(1L, getProlepticYear() <= 0 ? (-valueRangeRange.getMinimum()) + 1 + 1911 : valueRangeRange.getMaximum() - 1911);
                }
                return getChronology().range(chronoField);
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    /* JADX INFO: renamed from: org.threeten.bp.chrono.MinguoDate$1, reason: invalid class name */
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
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.PROLEPTIC_MONTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.YEAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoField[ChronoField.ERA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i = AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            if (i == 4) {
                int prolepticYear = getProlepticYear();
                if (prolepticYear < 1) {
                    prolepticYear = 1 - prolepticYear;
                }
                return prolepticYear;
            }
            if (i == 5) {
                return getProlepticMonth();
            }
            if (i == 6) {
                return getProlepticYear();
            }
            if (i != 7) {
                return this.isoDate.getLong(temporalField);
            }
            return getProlepticYear() < 1 ? 0 : 1;
        }
        return temporalField.getFrom(this);
    }

    private long getProlepticMonth() {
        return ((((long) getProlepticYear()) * 12) + ((long) this.isoDate.getMonthValue())) - 1;
    }

    private int getProlepticYear() {
        return this.isoDate.getYear() - 1911;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    public MinguoDate with(TemporalAdjuster temporalAdjuster) {
        return (MinguoDate) super.with(temporalAdjuster);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.Temporal
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.threeten.bp.chrono.MinguoDate with(org.threeten.bp.temporal.TemporalField r7, long r8) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof org.threeten.bp.temporal.ChronoField
            if (r0 == 0) goto L96
            r0 = r7
            org.threeten.bp.temporal.ChronoField r0 = (org.threeten.bp.temporal.ChronoField) r0
            long r1 = r6.getLong(r0)
            int r1 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r1 != 0) goto L10
            return r6
        L10:
            int[] r1 = org.threeten.bp.chrono.MinguoDate.AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoField
            int r2 = r0.ordinal()
            r1 = r1[r2]
            r2 = 7
            r3 = 6
            r4 = 4
            if (r1 == r4) goto L3a
            r5 = 5
            if (r1 == r5) goto L25
            if (r1 == r3) goto L3a
            if (r1 == r2) goto L3a
            goto L55
        L25:
            org.threeten.bp.chrono.MinguoChronology r7 = r6.getChronology()
            org.threeten.bp.temporal.ValueRange r7 = r7.range(r0)
            r7.checkValidValue(r8, r0)
            long r0 = r6.getProlepticMonth()
            long r8 = r8 - r0
            org.threeten.bp.chrono.MinguoDate r7 = r6.plusMonths(r8)
            return r7
        L3a:
            org.threeten.bp.chrono.MinguoChronology r1 = r6.getChronology()
            org.threeten.bp.temporal.ValueRange r1 = r1.range(r0)
            int r1 = r1.checkValidIntValue(r8, r0)
            int[] r5 = org.threeten.bp.chrono.MinguoDate.AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoField
            int r0 = r0.ordinal()
            r0 = r5[r0]
            r5 = 1
            if (r0 == r4) goto L7f
            if (r0 == r3) goto L72
            if (r0 == r2) goto L60
        L55:
            org.threeten.bp.LocalDate r0 = r6.isoDate
            org.threeten.bp.LocalDate r7 = r0.with(r7, r8)
            org.threeten.bp.chrono.MinguoDate r7 = r6.with(r7)
            return r7
        L60:
            org.threeten.bp.LocalDate r7 = r6.isoDate
            int r8 = r6.getProlepticYear()
            int r5 = r5 - r8
            int r5 = r5 + 1911
            org.threeten.bp.LocalDate r7 = r7.withYear(r5)
            org.threeten.bp.chrono.MinguoDate r7 = r6.with(r7)
            return r7
        L72:
            org.threeten.bp.LocalDate r7 = r6.isoDate
            int r1 = r1 + 1911
            org.threeten.bp.LocalDate r7 = r7.withYear(r1)
            org.threeten.bp.chrono.MinguoDate r7 = r6.with(r7)
            return r7
        L7f:
            org.threeten.bp.LocalDate r7 = r6.isoDate
            int r8 = r6.getProlepticYear()
            if (r8 < r5) goto L8a
            int r1 = r1 + 1911
            goto L8d
        L8a:
            int r5 = r5 - r1
            int r1 = r5 + 1911
        L8d:
            org.threeten.bp.LocalDate r7 = r7.withYear(r1)
            org.threeten.bp.chrono.MinguoDate r7 = r6.with(r7)
            return r7
        L96:
            org.threeten.bp.temporal.Temporal r7 = r7.adjustInto(r6, r8)
            org.threeten.bp.chrono.MinguoDate r7 = (org.threeten.bp.chrono.MinguoDate) r7
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.chrono.MinguoDate.with(org.threeten.bp.temporal.TemporalField, long):org.threeten.bp.chrono.MinguoDate");
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    public MinguoDate plus(TemporalAmount temporalAmount) {
        return (MinguoDate) super.plus(temporalAmount);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.temporal.Temporal
    public MinguoDate plus(long j, TemporalUnit temporalUnit) {
        return (MinguoDate) super.plus(j, temporalUnit);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    public MinguoDate minus(TemporalAmount temporalAmount) {
        return (MinguoDate) super.minus(temporalAmount);
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate, org.threeten.bp.jdk8.DefaultInterfaceTemporal, org.threeten.bp.temporal.Temporal
    public MinguoDate minus(long j, TemporalUnit temporalUnit) {
        return (MinguoDate) super.minus(j, temporalUnit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public MinguoDate plusYears(long j) {
        return with(this.isoDate.plusYears(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public MinguoDate plusMonths(long j) {
        return with(this.isoDate.plusMonths(j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.chrono.ChronoDateImpl
    public MinguoDate plusDays(long j) {
        return with(this.isoDate.plusDays(j));
    }

    private MinguoDate with(LocalDate localDate) {
        return localDate.equals(this.isoDate) ? this : new MinguoDate(localDate);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    public final ChronoLocalDateTime<MinguoDate> atTime(LocalTime localTime) {
        return super.atTime(localTime);
    }

    @Override // org.threeten.bp.chrono.ChronoDateImpl, org.threeten.bp.chrono.ChronoLocalDate
    public ChronoPeriod until(ChronoLocalDate chronoLocalDate) {
        Period periodUntil = this.isoDate.until(chronoLocalDate);
        return getChronology().period(periodUntil.getYears(), periodUntil.getMonths(), periodUntil.getDays());
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public long toEpochDay() {
        return this.isoDate.toEpochDay();
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MinguoDate) {
            return this.isoDate.equals(((MinguoDate) obj).isoDate);
        }
        return false;
    }

    @Override // org.threeten.bp.chrono.ChronoLocalDate
    public int hashCode() {
        return getChronology().getId().hashCode() ^ this.isoDate.hashCode();
    }

    private Object writeReplace() {
        return new Ser((byte) 5, this);
    }

    void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(get(ChronoField.YEAR));
        dataOutput.writeByte(get(ChronoField.MONTH_OF_YEAR));
        dataOutput.writeByte(get(ChronoField.DAY_OF_MONTH));
    }

    static ChronoLocalDate readExternal(DataInput dataInput) throws IOException {
        return MinguoChronology.INSTANCE.date(dataInput.readInt(), (int) dataInput.readByte(), (int) dataInput.readByte());
    }
}
