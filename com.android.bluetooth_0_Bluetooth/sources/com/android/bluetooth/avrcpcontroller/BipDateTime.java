package com.android.bluetooth.avrcpcontroller;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class BipDateTime {
    private static final String TAG = "avrcpcontroller.BipDateTime";
    private Date mDate;
    private boolean mIsUtc;

    public BipDateTime(String time) {
        this.mDate = null;
        this.mIsUtc = false;
        try {
            Pattern p = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})T(\\d{2})(\\d{2})(\\d{2})([Z])?");
            Matcher m = p.matcher(time);
            if (m.matches()) {
                Calendar.Builder builder = new Calendar.Builder();
                builder.setLenient(false);
                builder.setDate(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)) - 1, Integer.parseInt(m.group(3)));
                builder.setTimeOfDay(Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)), 0);
                if (m.group(7) != null) {
                    TimeZone tz = TimeZone.getTimeZone("UTC");
                    tz.setRawOffset(0);
                    builder.setTimeZone(tz);
                    this.mIsUtc = true;
                } else {
                    this.mIsUtc = false;
                }
                this.mDate = builder.build().getTime();
                return;
            }
        } catch (IllegalArgumentException e) {
        } catch (NullPointerException e2) {
        }
        throw new ParseException("Failed to parse time '" + time + "'");
    }

    public BipDateTime(Date date) {
        this.mDate = null;
        this.mIsUtc = false;
        Objects.requireNonNull(date, "Date cannot be null");
        this.mDate = date;
        this.mIsUtc = true;
    }

    public boolean isUtc() {
        return this.mIsUtc;
    }

    public Date getTime() {
        return this.mDate;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BipDateTime)) {
            return false;
        }
        BipDateTime d = (BipDateTime) o;
        return d.isUtc() == isUtc() && d.getTime() == getTime();
    }

    public String toString() {
        Date d = getTime();
        if (d == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        if (isUtc()) {
            TimeZone utc = TimeZone.getTimeZone("UTC");
            utc.setRawOffset(0);
            cal.setTimeZone(utc);
            return String.format(Locale.US, "%04d%02d%02dT%02d%02d%02dZ", Integer.valueOf(cal.get(1)), Integer.valueOf(cal.get(2) + 1), Integer.valueOf(cal.get(5)), Integer.valueOf(cal.get(11)), Integer.valueOf(cal.get(12)), Integer.valueOf(cal.get(13)));
        }
        cal.setTimeZone(TimeZone.getDefault());
        return String.format(Locale.US, "%04d%02d%02dT%02d%02d%02d", Integer.valueOf(cal.get(1)), Integer.valueOf(cal.get(2) + 1), Integer.valueOf(cal.get(5)), Integer.valueOf(cal.get(11)), Integer.valueOf(cal.get(12)), Integer.valueOf(cal.get(13)));
    }
}
