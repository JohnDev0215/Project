package org.joda.time;

/* JADX INFO: loaded from: classes6.dex */
public interface ReadWritableDateTime extends ReadableDateTime, ReadableInstant {
    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getCenturyOfEra();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getDayOfMonth();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getDayOfWeek();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getDayOfYear();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getEra();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getHourOfDay();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getMillisOfDay();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getMillisOfSecond();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getMinuteOfDay();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getMinuteOfHour();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getMonthOfYear();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getSecondOfDay();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getSecondOfMinute();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getWeekOfWeekyear();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getWeekyear();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getYear();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getYearOfCentury();

    @Override // org.joda.time.ReadableDateTime
    /* synthetic */ int getYearOfEra();

    void setDayOfMonth(int i);

    void setDayOfWeek(int i);

    void setDayOfYear(int i);

    void setHourOfDay(int i);

    void setMillisOfDay(int i);

    void setMillisOfSecond(int i);

    void setMinuteOfDay(int i);

    void setMinuteOfHour(int i);

    void setMonthOfYear(int i);

    void setSecondOfDay(int i);

    void setSecondOfMinute(int i);

    void setWeekOfWeekyear(int i);

    void setWeekyear(int i);

    void setYear(int i);
}
