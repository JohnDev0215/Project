package com.android.launcher3.util;

import android.os.SystemClock;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.Log;
import android.util.MutableLong;

/* JADX INFO: loaded from: classes4.dex */
public class TraceHelper {
    private static final boolean ENABLED;
    private static final boolean SYSTEM_TRACE;
    private static final ArrayMap<String, MutableLong> sUpTimes;

    static {
        boolean zIsLoggable = Log.isLoggable("LAUNCHER_TRACE", 2);
        ENABLED = zIsLoggable;
        SYSTEM_TRACE = zIsLoggable;
        sUpTimes = zIsLoggable ? new ArrayMap<>() : null;
    }

    public static void beginSection(String sectionName) {
        if (ENABLED) {
            ArrayMap<String, MutableLong> arrayMap = sUpTimes;
            synchronized (arrayMap) {
                MutableLong mutableLong = arrayMap.get(sectionName);
                if (mutableLong == null) {
                    mutableLong = new MutableLong(Log.isLoggable(sectionName, 2) ? 0L : -1L);
                    arrayMap.put(sectionName, mutableLong);
                }
                if (mutableLong.value >= 0) {
                    if (SYSTEM_TRACE) {
                        Trace.beginSection(sectionName);
                    }
                    mutableLong.value = SystemClock.uptimeMillis();
                }
            }
        }
    }

    public static void partitionSection(String sectionName, String partition) {
        if (ENABLED) {
            ArrayMap<String, MutableLong> arrayMap = sUpTimes;
            synchronized (arrayMap) {
                MutableLong mutableLong = arrayMap.get(sectionName);
                if (mutableLong != null && mutableLong.value >= 0) {
                    if (SYSTEM_TRACE) {
                        Trace.endSection();
                        Trace.beginSection(sectionName);
                    }
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    Log.d(sectionName, partition + " : " + (jUptimeMillis - mutableLong.value));
                    mutableLong.value = jUptimeMillis;
                }
            }
        }
    }

    public static void endSection(String sectionName) {
        if (ENABLED) {
            endSection(sectionName, "End");
        }
    }

    public static void endSection(String sectionName, String msg) {
        if (ENABLED) {
            ArrayMap<String, MutableLong> arrayMap = sUpTimes;
            synchronized (arrayMap) {
                MutableLong mutableLong = arrayMap.get(sectionName);
                if (mutableLong != null && mutableLong.value >= 0) {
                    if (SYSTEM_TRACE) {
                        Trace.endSection();
                    }
                    Log.d(sectionName, msg + " : " + (SystemClock.uptimeMillis() - mutableLong.value));
                }
            }
        }
    }
}
