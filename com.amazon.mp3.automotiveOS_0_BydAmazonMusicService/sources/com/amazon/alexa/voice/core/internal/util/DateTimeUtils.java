package com.amazon.alexa.voice.core.internal.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class DateTimeUtils {
    private static final SimpleDateFormat ISO_8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US);

    private DateTimeUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static synchronized String iso8601(long j) {
        return ISO_8601.format(new Date(j));
    }
}
