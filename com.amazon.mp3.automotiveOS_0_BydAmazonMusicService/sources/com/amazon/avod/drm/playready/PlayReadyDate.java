package com.amazon.avod.drm.playready;

import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyDate {
    private static final long PLAYREADY_TO_UNIXTIME_OFFSET_MILLIS = TimeUnit.DAYS.toMillis(134774);

    public static Date javaDateFromPlayReadyDate(String str) {
        Preconditions.checkArgument(str.length() == 16);
        return new Date((((Long.decode("0x" + str.substring(8, 16)).longValue() << 32) + Long.decode("0x" + str.substring(0, 8)).longValue()) / 10000) - PLAYREADY_TO_UNIXTIME_OFFSET_MILLIS);
    }

    public static Date javaDateFromAndroidTimeStamp(String str) {
        Preconditions.checkArgument(str.length() == 8);
        return new Date(Long.decode("0x" + str).longValue() * 1000);
    }
}
