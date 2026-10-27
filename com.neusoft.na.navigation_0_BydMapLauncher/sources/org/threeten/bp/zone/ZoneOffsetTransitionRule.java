package org.threeten.bp.zone;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalAdjusters;

/* JADX INFO: loaded from: classes4.dex */
public final class ZoneOffsetTransitionRule implements Serializable {
    private static final long serialVersionUID = 6889046316657758795L;
    private final byte dom;
    private final DayOfWeek dow;
    private final Month month;
    private final ZoneOffset offsetAfter;
    private final ZoneOffset offsetBefore;
    private final ZoneOffset standardOffset;
    private final LocalTime time;
    private final TimeDefinition timeDefinition;
    private final boolean timeEndOfDay;

    public static ZoneOffsetTransitionRule of(Month month, int i, DayOfWeek dayOfWeek, LocalTime localTime, boolean z, TimeDefinition timeDefinition, ZoneOffset zoneOffset, ZoneOffset zoneOffset2, ZoneOffset zoneOffset3) {
        Jdk8Methods.requireNonNull(month, "month");
        Jdk8Methods.requireNonNull(localTime, "time");
        Jdk8Methods.requireNonNull(timeDefinition, "timeDefnition");
        Jdk8Methods.requireNonNull(zoneOffset, "standardOffset");
        Jdk8Methods.requireNonNull(zoneOffset2, "offsetBefore");
        Jdk8Methods.requireNonNull(zoneOffset3, "offsetAfter");
        if (i < -28 || i > 31 || i == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        }
        if (z && !localTime.equals(LocalTime.MIDNIGHT)) {
            throw new IllegalArgumentException("Time must be midnight when end of day flag is true");
        }
        return new ZoneOffsetTransitionRule(month, i, dayOfWeek, localTime, z, timeDefinition, zoneOffset, zoneOffset2, zoneOffset3);
    }

    ZoneOffsetTransitionRule(Month month, int i, DayOfWeek dayOfWeek, LocalTime localTime, boolean z, TimeDefinition timeDefinition, ZoneOffset zoneOffset, ZoneOffset zoneOffset2, ZoneOffset zoneOffset3) {
        this.month = month;
        this.dom = (byte) i;
        this.dow = dayOfWeek;
        this.time = localTime;
        this.timeEndOfDay = z;
        this.timeDefinition = timeDefinition;
        this.standardOffset = zoneOffset;
        this.offsetBefore = zoneOffset2;
        this.offsetAfter = zoneOffset3;
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    void writeExternal(DataOutput dataOutput) throws IOException {
        int secondOfDay = this.timeEndOfDay ? 86400 : this.time.toSecondOfDay();
        int totalSeconds = this.standardOffset.getTotalSeconds();
        int totalSeconds2 = this.offsetBefore.getTotalSeconds() - totalSeconds;
        int totalSeconds3 = this.offsetAfter.getTotalSeconds() - totalSeconds;
        int hour = secondOfDay % 3600 == 0 ? this.timeEndOfDay ? 24 : this.time.getHour() : 31;
        int i = totalSeconds % 900 == 0 ? (totalSeconds / 900) + 128 : 255;
        int i2 = (totalSeconds2 == 0 || totalSeconds2 == 1800 || totalSeconds2 == 3600) ? totalSeconds2 / 1800 : 3;
        int i3 = (totalSeconds3 == 0 || totalSeconds3 == 1800 || totalSeconds3 == 3600) ? totalSeconds3 / 1800 : 3;
        DayOfWeek dayOfWeek = this.dow;
        dataOutput.writeInt((this.month.getValue() << 28) + ((this.dom + 32) << 22) + ((dayOfWeek == null ? 0 : dayOfWeek.getValue()) << 19) + (hour << 14) + (this.timeDefinition.ordinal() << 12) + (i << 4) + (i2 << 2) + i3);
        if (hour == 31) {
            dataOutput.writeInt(secondOfDay);
        }
        if (i == 255) {
            dataOutput.writeInt(totalSeconds);
        }
        if (i2 == 3) {
            dataOutput.writeInt(this.offsetBefore.getTotalSeconds());
        }
        if (i3 == 3) {
            dataOutput.writeInt(this.offsetAfter.getTotalSeconds());
        }
    }

    static ZoneOffsetTransitionRule readExternal(DataInput dataInput) throws IOException {
        int i = dataInput.readInt();
        Month monthOf = Month.of(i >>> 28);
        int i2 = ((264241152 & i) >>> 22) - 32;
        int i3 = (3670016 & i) >>> 19;
        DayOfWeek dayOfWeekOf = i3 == 0 ? null : DayOfWeek.of(i3);
        int i4 = (507904 & i) >>> 14;
        TimeDefinition timeDefinition = TimeDefinition.values()[(i & 12288) >>> 12];
        int i5 = (i & 4080) >>> 4;
        int i6 = (i & 12) >>> 2;
        int i7 = i & 3;
        LocalTime localTimeOfSecondOfDay = i4 == 31 ? LocalTime.ofSecondOfDay(dataInput.readInt()) : LocalTime.of(i4 % 24, 0);
        ZoneOffset zoneOffsetOfTotalSeconds = ZoneOffset.ofTotalSeconds(i5 == 255 ? dataInput.readInt() : (i5 - 128) * 900);
        return of(monthOf, i2, dayOfWeekOf, localTimeOfSecondOfDay, i4 == 24, timeDefinition, zoneOffsetOfTotalSeconds, ZoneOffset.ofTotalSeconds(i6 == 3 ? dataInput.readInt() : zoneOffsetOfTotalSeconds.getTotalSeconds() + (i6 * 1800)), ZoneOffset.ofTotalSeconds(i7 == 3 ? dataInput.readInt() : zoneOffsetOfTotalSeconds.getTotalSeconds() + (i7 * 1800)));
    }

    public Month getMonth() {
        return this.month;
    }

    public int getDayOfMonthIndicator() {
        return this.dom;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dow;
    }

    public LocalTime getLocalTime() {
        return this.time;
    }

    public boolean isMidnightEndOfDay() {
        return this.timeEndOfDay;
    }

    public TimeDefinition getTimeDefinition() {
        return this.timeDefinition;
    }

    public ZoneOffset getStandardOffset() {
        return this.standardOffset;
    }

    public ZoneOffset getOffsetBefore() {
        return this.offsetBefore;
    }

    public ZoneOffset getOffsetAfter() {
        return this.offsetAfter;
    }

    public ZoneOffsetTransition createTransition(int i) {
        LocalDate localDateOf;
        byte b = this.dom;
        if (b < 0) {
            Month month = this.month;
            localDateOf = LocalDate.of(i, month, month.length(IsoChronology.INSTANCE.isLeapYear(i)) + 1 + this.dom);
            DayOfWeek dayOfWeek = this.dow;
            if (dayOfWeek != null) {
                localDateOf = localDateOf.with(TemporalAdjusters.previousOrSame(dayOfWeek));
            }
        } else {
            localDateOf = LocalDate.of(i, this.month, b);
            DayOfWeek dayOfWeek2 = this.dow;
            if (dayOfWeek2 != null) {
                localDateOf = localDateOf.with(TemporalAdjusters.nextOrSame(dayOfWeek2));
            }
        }
        if (this.timeEndOfDay) {
            localDateOf = localDateOf.plusDays(1L);
        }
        return new ZoneOffsetTransition(this.timeDefinition.createDateTime(LocalDateTime.of(localDateOf, this.time), this.standardOffset, this.offsetBefore), this.offsetBefore, this.offsetAfter);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ZoneOffsetTransitionRule)) {
            return false;
        }
        ZoneOffsetTransitionRule zoneOffsetTransitionRule = (ZoneOffsetTransitionRule) obj;
        return this.month == zoneOffsetTransitionRule.month && this.dom == zoneOffsetTransitionRule.dom && this.dow == zoneOffsetTransitionRule.dow && this.timeDefinition == zoneOffsetTransitionRule.timeDefinition && this.time.equals(zoneOffsetTransitionRule.time) && this.timeEndOfDay == zoneOffsetTransitionRule.timeEndOfDay && this.standardOffset.equals(zoneOffsetTransitionRule.standardOffset) && this.offsetBefore.equals(zoneOffsetTransitionRule.offsetBefore) && this.offsetAfter.equals(zoneOffsetTransitionRule.offsetAfter);
    }

    public int hashCode() {
        int secondOfDay = ((this.time.toSecondOfDay() + (this.timeEndOfDay ? 1 : 0)) << 15) + (this.month.ordinal() << 11) + ((this.dom + 32) << 5);
        DayOfWeek dayOfWeek = this.dow;
        return ((((secondOfDay + ((dayOfWeek == null ? 7 : dayOfWeek.ordinal()) << 2)) + this.timeDefinition.ordinal()) ^ this.standardOffset.hashCode()) ^ this.offsetBefore.hashCode()) ^ this.offsetAfter.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TransitionRule[");
        sb.append(this.offsetBefore.compareTo(this.offsetAfter) > 0 ? "Gap " : "Overlap ").append(this.offsetBefore).append(" to ").append(this.offsetAfter).append(", ");
        DayOfWeek dayOfWeek = this.dow;
        if (dayOfWeek != null) {
            byte b = this.dom;
            if (b == -1) {
                sb.append(dayOfWeek.name()).append(" on or before last day of ").append(this.month.name());
            } else if (b < 0) {
                sb.append(dayOfWeek.name()).append(" on or before last day minus ").append((-this.dom) - 1).append(" of ").append(this.month.name());
            } else {
                sb.append(dayOfWeek.name()).append(" on or after ").append(this.month.name()).append(' ').append((int) this.dom);
            }
        } else {
            sb.append(this.month.name()).append(' ').append((int) this.dom);
        }
        sb.append(" at ").append(this.timeEndOfDay ? "24:00" : this.time.toString()).append(StringUtils.SPACE).append(this.timeDefinition).append(", standard offset ").append(this.standardOffset).append(']');
        return sb.toString();
    }

    /* JADX INFO: renamed from: org.threeten.bp.zone.ZoneOffsetTransitionRule$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition;

        static {
            int[] iArr = new int[TimeDefinition.values().length];
            $SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition = iArr;
            try {
                iArr[TimeDefinition.UTC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition[TimeDefinition.STANDARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum TimeDefinition {
        UTC,
        WALL,
        STANDARD;

        public LocalDateTime createDateTime(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneOffset zoneOffset2) {
            int i = AnonymousClass1.$SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition[ordinal()];
            if (i != 1) {
                return i != 2 ? localDateTime : localDateTime.plusSeconds(zoneOffset2.getTotalSeconds() - zoneOffset.getTotalSeconds());
            }
            return localDateTime.plusSeconds(zoneOffset2.getTotalSeconds() - ZoneOffset.UTC.getTotalSeconds());
        }
    }
}
