package com.android.car.developeroptions.display;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import com.android.car.developeroptions.R;
import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class NightDisplayTimeFormatter {
    private DateFormat mTimeFormatter;

    NightDisplayTimeFormatter(Context context) {
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(context);
        this.mTimeFormatter = timeFormat;
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public String getFormattedTimeString(LocalTime localTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(this.mTimeFormatter.getTimeZone());
        calendar.set(11, localTime.getHour());
        calendar.set(12, localTime.getMinute());
        calendar.set(13, 0);
        calendar.set(14, 0);
        return this.mTimeFormatter.format(calendar.getTime());
    }

    public String getAutoModeTimeSummary(Context context, ColorDisplayManager colorDisplayManager) {
        return context.getString(colorDisplayManager.isNightDisplayActivated() ? R.string.night_display_summary_on : R.string.night_display_summary_off, getAutoModeSummary(context, colorDisplayManager));
    }

    private String getAutoModeSummary(Context context, ColorDisplayManager colorDisplayManager) {
        boolean zIsNightDisplayActivated = colorDisplayManager.isNightDisplayActivated();
        int nightDisplayAutoMode = colorDisplayManager.getNightDisplayAutoMode();
        if (nightDisplayAutoMode == 1) {
            if (zIsNightDisplayActivated) {
                return context.getString(R.string.night_display_summary_on_auto_mode_custom, getFormattedTimeString(colorDisplayManager.getNightDisplayCustomEndTime()));
            }
            return context.getString(R.string.night_display_summary_off_auto_mode_custom, getFormattedTimeString(colorDisplayManager.getNightDisplayCustomStartTime()));
        }
        if (nightDisplayAutoMode == 2) {
            return context.getString(zIsNightDisplayActivated ? R.string.night_display_summary_on_auto_mode_twilight : R.string.night_display_summary_off_auto_mode_twilight);
        }
        return context.getString(zIsNightDisplayActivated ? R.string.night_display_summary_on_auto_mode_never : R.string.night_display_summary_off_auto_mode_never);
    }
}
