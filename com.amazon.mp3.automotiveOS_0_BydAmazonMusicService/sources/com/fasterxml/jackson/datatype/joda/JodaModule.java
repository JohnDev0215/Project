package com.fasterxml.jackson.datatype.joda;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.deser.DateMidnightDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeZoneDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.DurationDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.IntervalDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.MonthDayDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.PeriodDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.key.DateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.key.DurationKeyDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.key.LocalDateKeyDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.key.LocalDateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.key.LocalTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.joda.deser.key.PeriodKeyDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateMidnightSerializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeZoneSerializer;
import com.fasterxml.jackson.datatype.joda.ser.DurationSerializer;
import com.fasterxml.jackson.datatype.joda.ser.InstantSerializer;
import com.fasterxml.jackson.datatype.joda.ser.IntervalSerializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.joda.ser.MonthDaySerializer;
import com.fasterxml.jackson.datatype.joda.ser.PeriodSerializer;
import com.fasterxml.jackson.datatype.joda.ser.YearMonthSerializer;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.MonthDay;
import org.joda.time.Period;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePeriod;
import org.joda.time.YearMonth;

/* JADX INFO: loaded from: classes6.dex */
public class JodaModule extends SimpleModule {
    private static final long serialVersionUID = 1;

    public boolean equals(Object obj) {
        return this == obj;
    }

    public JodaModule() {
        super(PackageVersion.VERSION);
        addDeserializer(DateTime.class, DateTimeDeserializer.forType(DateTime.class));
        addDeserializer(DateTimeZone.class, new DateTimeZoneDeserializer());
        addDeserializer(Duration.class, new DurationDeserializer());
        addDeserializer(Instant.class, new InstantDeserializer());
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        addDeserializer(LocalDate.class, new LocalDateDeserializer());
        addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        addDeserializer(Period.class, new PeriodDeserializer(true));
        addDeserializer(ReadablePeriod.class, new PeriodDeserializer(false));
        addDeserializer(ReadableDateTime.class, DateTimeDeserializer.forType(ReadableDateTime.class));
        addDeserializer(ReadableInstant.class, DateTimeDeserializer.forType(ReadableInstant.class));
        addDeserializer(Interval.class, new IntervalDeserializer());
        addDeserializer(MonthDay.class, new MonthDayDeserializer());
        addDeserializer(YearMonth.class, new YearMonthDeserializer());
        addSerializer(DateTime.class, new DateTimeSerializer());
        addSerializer(DateTimeZone.class, new DateTimeZoneSerializer());
        addSerializer(Duration.class, new DurationSerializer());
        addSerializer(Instant.class, new InstantSerializer());
        addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        addSerializer(LocalDate.class, new LocalDateSerializer());
        addSerializer(LocalTime.class, new LocalTimeSerializer());
        addSerializer(Period.class, new PeriodSerializer());
        addSerializer(Interval.class, new IntervalSerializer());
        addSerializer(MonthDay.class, new MonthDaySerializer());
        addSerializer(YearMonth.class, new YearMonthSerializer());
        addKeyDeserializer(DateTime.class, new DateTimeKeyDeserializer());
        addKeyDeserializer(LocalTime.class, new LocalTimeKeyDeserializer());
        addKeyDeserializer(LocalDate.class, new LocalDateKeyDeserializer());
        addKeyDeserializer(LocalDateTime.class, new LocalDateTimeKeyDeserializer());
        addKeyDeserializer(Duration.class, new DurationKeyDeserializer());
        addKeyDeserializer(Period.class, new PeriodKeyDeserializer());
        addDeserializer(DateMidnight.class, new DateMidnightDeserializer());
        addSerializer(DateMidnight.class, new DateMidnightSerializer());
    }

    @Override // com.fasterxml.jackson.databind.module.SimpleModule, com.fasterxml.jackson.databind.Module
    public String getModuleName() {
        return getClass().getSimpleName();
    }

    public int hashCode() {
        return getClass().hashCode();
    }
}
