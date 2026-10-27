package com.amplifyframework.core.model.temporal;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class Temporal {
    private Temporal() {
    }

    public static final class Date implements Comparable<Date> {
        private final LocalDate localDate;
        private final ZoneOffset zoneOffset;

        public Date(@NonNull java.util.Date date) {
            this.zoneOffset = null;
            this.localDate = Instant.ofEpochMilli(date.getTime()).atOffset(ZoneOffset.UTC).toLocalDate();
        }

        public Date(@NonNull java.util.Date date, int i) {
            ZoneOffset zoneOffsetOfTotalSeconds = ZoneOffset.ofTotalSeconds(i);
            this.zoneOffset = zoneOffsetOfTotalSeconds;
            this.localDate = Instant.ofEpochMilli(date.getTime()).atOffset(zoneOffsetOfTotalSeconds).toLocalDate();
        }

        public Date(@NonNull String str) {
            LocalDate localDateFrom;
            ZoneOffset zoneOffsetFrom;
            try {
                OffsetDateTime offsetDateTime = OffsetDateTime.parse(str, getOffsetDateTimeFormatter());
                localDateFrom = LocalDate.from((TemporalAccessor) offsetDateTime);
                zoneOffsetFrom = ZoneOffset.from((TemporalAccessor) offsetDateTime);
            } catch (Exception e) {
                try {
                    localDateFrom = LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE);
                    zoneOffsetFrom = null;
                } catch (Exception unused) {
                    throw new IllegalArgumentException("Failed to create Temporal.Date object from " + str, e);
                }
            }
            this.localDate = localDateFrom;
            this.zoneOffset = zoneOffsetFrom;
        }

        public String format() {
            ZoneOffset zoneOffset = this.zoneOffset;
            if (zoneOffset != null) {
                return getOffsetDateTimeFormatter().format(OffsetDateTime.of(this.localDate, LocalTime.MIDNIGHT, zoneOffset));
            }
            return DateTimeFormatter.ISO_LOCAL_DATE.format(this.localDate);
        }

        private DateTimeFormatter getOffsetDateTimeFormatter() {
            return new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_OFFSET_DATE).parseDefaulting(ChronoField.HOUR_OF_DAY, 0L).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0L).toFormatter();
        }

        public java.util.Date toDate() {
            ZoneOffset zoneOffset = this.zoneOffset;
            if (zoneOffset == null) {
                zoneOffset = ZoneOffset.UTC;
            }
            return new java.util.Date(OffsetDateTime.of(this.localDate, LocalTime.MIDNIGHT, zoneOffset).toInstant().toEpochMilli());
        }

        public int getOffsetTotalSeconds() throws IllegalStateException {
            ZoneOffset zoneOffset = this.zoneOffset;
            if (zoneOffset != null) {
                return zoneOffset.getTotalSeconds();
            }
            throw new IllegalStateException("Temporal.Date instance does not have a timezone offset.");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Date.class != obj.getClass()) {
                return false;
            }
            Date date = (Date) obj;
            return ObjectsCompat.equals(this.localDate, date.localDate) && ObjectsCompat.equals(this.zoneOffset, date.zoneOffset);
        }

        public int hashCode() {
            int iHashCode = this.localDate.hashCode() * 31;
            ZoneOffset zoneOffset = this.zoneOffset;
            return iHashCode + (zoneOffset != null ? zoneOffset.hashCode() : 0);
        }

        public String toString() {
            return "Temporal.Date{localDate='" + this.localDate + "', zoneOffset='" + this.zoneOffset + "'}";
        }

        @Override // java.lang.Comparable
        public int compareTo(Date date) {
            Objects.requireNonNull(date);
            return toDate().compareTo(date.toDate());
        }
    }

    public static final class DateTime implements Comparable<DateTime> {
        private final OffsetDateTime offsetDateTime;

        public DateTime(@NonNull java.util.Date date, int i) {
            this.offsetDateTime = Instant.ofEpochMilli(date.getTime()).atOffset(ZoneOffset.ofTotalSeconds(i));
        }

        public DateTime(@NonNull String str) {
            try {
                this.offsetDateTime = OffsetDateTime.parse(str, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to create Temporal.DateTime object from " + str, e);
            }
        }

        public String format() {
            return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.offsetDateTime);
        }

        public java.util.Date toDate() {
            return new java.util.Date(this.offsetDateTime.toInstant().toEpochMilli());
        }

        public int getOffsetTotalSeconds() {
            return this.offsetDateTime.getOffset().getTotalSeconds();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || DateTime.class != obj.getClass()) {
                return false;
            }
            return ObjectsCompat.equals(this.offsetDateTime, ((DateTime) obj).offsetDateTime);
        }

        public int hashCode() {
            return this.offsetDateTime.hashCode();
        }

        public String toString() {
            return "Temporal.DateTime{offsetDateTime='" + this.offsetDateTime + "'}";
        }

        @Override // java.lang.Comparable
        public int compareTo(DateTime dateTime) {
            Objects.requireNonNull(dateTime);
            return toDate().compareTo(dateTime.toDate());
        }
    }

    public static final class Time implements Comparable<Time> {
        private final LocalTime localTime;
        private final ZoneOffset zoneOffset;

        public Time(@NonNull java.util.Date date) {
            this.zoneOffset = null;
            this.localTime = Instant.ofEpochMilli(date.getTime()).atOffset(ZoneOffset.UTC).toLocalTime();
        }

        public Time(@NonNull java.util.Date date, int i) {
            ZoneOffset zoneOffsetOfTotalSeconds = ZoneOffset.ofTotalSeconds(i);
            this.zoneOffset = zoneOffsetOfTotalSeconds;
            this.localTime = Instant.ofEpochMilli(date.getTime()).atOffset(zoneOffsetOfTotalSeconds).toLocalTime();
        }

        public Time(@NonNull String str) {
            LocalTime localTimeFrom;
            ZoneOffset zoneOffsetFrom;
            try {
                OffsetTime offsetTime = OffsetTime.parse(str, DateTimeFormatter.ISO_OFFSET_TIME);
                localTimeFrom = LocalTime.from(offsetTime);
                zoneOffsetFrom = ZoneOffset.from((TemporalAccessor) offsetTime);
            } catch (Exception e) {
                try {
                    localTimeFrom = LocalTime.parse(str, DateTimeFormatter.ISO_LOCAL_TIME);
                    zoneOffsetFrom = null;
                } catch (Exception unused) {
                    throw new IllegalArgumentException("Failed to create Temporal.Time object from " + str, e);
                }
            }
            this.localTime = localTimeFrom;
            this.zoneOffset = zoneOffsetFrom;
        }

        public String format() {
            ZoneOffset zoneOffset = this.zoneOffset;
            if (zoneOffset != null) {
                return DateTimeFormatter.ISO_OFFSET_TIME.format(OffsetTime.of(this.localTime, zoneOffset));
            }
            return DateTimeFormatter.ISO_LOCAL_TIME.format(this.localTime);
        }

        @NonNull
        public java.util.Date toDate() {
            ZoneOffset zoneOffset = this.zoneOffset;
            if (zoneOffset == null) {
                zoneOffset = ZoneOffset.UTC;
            }
            return new java.util.Date(OffsetDateTime.of(LocalDate.ofEpochDay(0L), this.localTime, zoneOffset).toInstant().toEpochMilli());
        }

        public int getOffsetTotalSeconds() throws IllegalStateException {
            ZoneOffset zoneOffset = this.zoneOffset;
            if (zoneOffset != null) {
                return zoneOffset.getTotalSeconds();
            }
            throw new IllegalStateException("Temporal.Time instance does not have a timezone offset.");
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Time.class != obj.getClass()) {
                return false;
            }
            Time time = (Time) obj;
            return ObjectsCompat.equals(this.localTime, time.localTime) && ObjectsCompat.equals(this.zoneOffset, time.zoneOffset);
        }

        public int hashCode() {
            int iHashCode = this.localTime.hashCode() * 31;
            ZoneOffset zoneOffset = this.zoneOffset;
            return iHashCode + (zoneOffset != null ? zoneOffset.hashCode() : 0);
        }

        public String toString() {
            return "Temporal.Time{localTime='" + this.localTime + "', zoneOffset='" + this.zoneOffset + "'}";
        }

        @Override // java.lang.Comparable
        public int compareTo(Time time) {
            Objects.requireNonNull(time);
            return toDate().compareTo(time.toDate());
        }
    }

    public static final class Timestamp implements Comparable<Timestamp> {
        private final long secondsSinceEpoch;

        public Timestamp() {
            this(new java.util.Date());
        }

        public Timestamp(long j, TimeUnit timeUnit) {
            this.secondsSinceEpoch = timeUnit.toSeconds(j);
        }

        public Timestamp(@NonNull java.util.Date date) {
            this(date.getTime(), TimeUnit.MILLISECONDS);
        }

        public static Timestamp now() {
            return new Timestamp();
        }

        public long getSecondsSinceEpoch() {
            return this.secondsSinceEpoch;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && Timestamp.class == obj.getClass() && this.secondsSinceEpoch == ((Timestamp) obj).secondsSinceEpoch;
        }

        public int hashCode() {
            long j = this.secondsSinceEpoch;
            return (int) (j ^ (j >>> 32));
        }

        public String toString() {
            return "Temporal.Timestamp{timestamp=" + this.secondsSinceEpoch + '}';
        }

        @Override // java.lang.Comparable
        public int compareTo(Timestamp timestamp) {
            Objects.requireNonNull(timestamp);
            return Long.compare(getSecondsSinceEpoch(), timestamp.getSecondsSinceEpoch());
        }
    }
}
