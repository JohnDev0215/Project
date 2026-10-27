package com.amazon.music.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class CachingDateFormatter {
    final SimpleDateFormat sdf;
    long lastTimestamp = -1;
    String cachedStr = null;

    CachingDateFormatter(String str, Locale locale) {
        this.sdf = new SimpleDateFormat(str, locale);
    }

    public final synchronized String format(long j) {
        if (j != this.lastTimestamp) {
            this.lastTimestamp = j;
            this.cachedStr = this.sdf.format(new Date(j));
        }
        return this.cachedStr;
    }
}
