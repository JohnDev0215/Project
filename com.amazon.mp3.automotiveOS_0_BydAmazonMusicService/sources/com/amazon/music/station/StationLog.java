package com.amazon.music.station;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class StationLog {
    private static final Logger LOG = LoggerFactory.getLogger(StationLog.class.getSimpleName());

    StationLog() {
    }

    public static final void debug(String str, String str2, int i, int i2) {
        LOG.debug("DMM_STATION" + String.format(" StationId: %s, Current Position: %d, TrackCount: %d, Message: %s", str2, Integer.valueOf(i), Integer.valueOf(i2), str));
    }

    public static final void error(String str, String str2, int i, int i2) {
        LOG.error("DMM_STATION" + String.format(" StationId: %s, Current Position: %d, TrackCount: %d, Message: %s", str2, Integer.valueOf(i), Integer.valueOf(i2), str));
    }
}
