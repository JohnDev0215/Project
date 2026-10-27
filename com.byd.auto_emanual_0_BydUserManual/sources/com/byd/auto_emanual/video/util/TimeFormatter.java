package com.byd.auto_emanual.video.util;

import java.util.Formatter;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class TimeFormatter {
    private StringBuilder mFormatBuilder = new StringBuilder();
    private Formatter mFormatter = new Formatter(this.mFormatBuilder, Locale.getDefault());

    public static TimeFormatter instance() {
        return new TimeFormatter();
    }

    private TimeFormatter() {
    }

    public String stringForTime(int timeMs) {
        double d = (((double) timeMs) * 1.0d) / 1000.0d;
        int i = timeMs / 1000;
        int iRound = (int) Math.round(d);
        int i2 = iRound % 60;
        int i3 = (iRound / 60) % 60;
        int i4 = iRound / 3600;
        this.mFormatBuilder.setLength(0);
        return i4 > 0 ? this.mFormatter.format("%d:%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i3), Integer.valueOf(i2)).toString() : this.mFormatter.format("%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i2)).toString();
    }
}
