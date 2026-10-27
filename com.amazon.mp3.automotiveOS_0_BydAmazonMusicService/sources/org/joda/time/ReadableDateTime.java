package org.joda.time;

/* JADX INFO: loaded from: classes6.dex */
public interface ReadableDateTime extends ReadableInstant {
    int getCenturyOfEra();

    int getDayOfMonth();

    int getDayOfWeek();

    int getDayOfYear();

    int getEra();

    int getHourOfDay();

    int getMillisOfDay();

    int getMillisOfSecond();

    int getMinuteOfDay();

    int getMinuteOfHour();

    int getMonthOfYear();

    int getSecondOfDay();

    int getSecondOfMinute();

    int getWeekOfWeekyear();

    int getWeekyear();

    int getYear();

    int getYearOfCentury();

    int getYearOfEra();

    DateTime toDateTime();
}
