package com.amazon.avod.ads.parser.vast;

import com.amazon.avod.util.DLog;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VastTimeSpan {
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d\\d):([0-5]\\d):([0-5]\\d)\\.?(\\d\\d\\d)?");
    private static final VastTimeSpan ZERO_TIME_SPAN = new VastTimeSpan(0);
    private final long mMilliseconds;

    public static final VastTimeSpan getZeroTimeSpan() {
        return ZERO_TIME_SPAN;
    }

    private VastTimeSpan(long j) {
        this.mMilliseconds = j;
    }

    public static VastTimeSpan parseXmlTime(String str) {
        long timeMilliseconds;
        Matcher matcher = TIME_PATTERN.matcher(str);
        long j = 0;
        if (matcher.find()) {
            int iGroupCount = matcher.groupCount();
            for (int i = 1; i <= iGroupCount; i++) {
                if (i == 1) {
                    timeMilliseconds = getTimeMilliseconds(TimeUnit.HOURS, matcher.group(i));
                } else if (i == 2) {
                    timeMilliseconds = getTimeMilliseconds(TimeUnit.MINUTES, matcher.group(i));
                } else if (i != 3) {
                    if (i != 4) {
                        DLog.warnf("Unexpected group [%d] in VastTimeSpan matcher, ignoring.", Integer.valueOf(i));
                    } else if (matcher.group(i) != null) {
                        timeMilliseconds = getTimeMilliseconds(TimeUnit.MILLISECONDS, matcher.group(i));
                    }
                } else {
                    timeMilliseconds = getTimeMilliseconds(TimeUnit.SECONDS, matcher.group(i));
                }
                j += timeMilliseconds;
            }
        }
        return new VastTimeSpan(j);
    }

    public long getTotalMilliseconds() {
        return this.mMilliseconds;
    }

    public String toString() {
        Locale locale = Locale.US;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        return String.format(locale, "%02d:%02d:%02d.%03d", Long.valueOf(timeUnit.toHours(this.mMilliseconds)), Long.valueOf(timeUnit.toMinutes(this.mMilliseconds) % 60), Long.valueOf(timeUnit.toSeconds(this.mMilliseconds) % 60), Long.valueOf(this.mMilliseconds % 1000));
    }

    private static long getTimeMilliseconds(TimeUnit timeUnit, String str) {
        return timeUnit.toMillis(Integer.parseInt(str));
    }

    @Nonnull
    public static VastTimeSpan buildVastTimeSpanFromMilliSeconds(long j) {
        return new VastTimeSpan(j);
    }
}
