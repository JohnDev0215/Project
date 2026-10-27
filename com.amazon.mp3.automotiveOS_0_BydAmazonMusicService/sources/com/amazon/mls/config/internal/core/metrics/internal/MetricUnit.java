package com.amazon.mls.config.internal.core.metrics.internal;

/* JADX INFO: loaded from: classes3.dex */
public enum MetricUnit {
    None,
    Microseconds,
    Milliseconds,
    Seconds,
    Count,
    Count_Second,
    Percent,
    Bits,
    Bits_Second,
    Kilobits,
    Kilobits_Second,
    Megabits,
    Megabits_Second,
    Gigabits,
    Gigabits_Second,
    Terabits,
    Terabits_Second,
    Bytes,
    Bytes_Second,
    Kilobytes,
    Kilobytes_Second,
    Megabytes,
    Megabytes_Second,
    Gigabytes,
    Gigabytes_Second,
    Terabytes,
    Terabytes_Second;

    public String getName() {
        return toString();
    }
}
