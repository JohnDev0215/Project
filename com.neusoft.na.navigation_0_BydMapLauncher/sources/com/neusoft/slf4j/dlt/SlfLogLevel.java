package com.neusoft.slf4j.dlt;

/* JADX INFO: loaded from: classes.dex */
enum SlfLogLevel {
    OFF,
    ERROR,
    WARN,
    INFO,
    DEBUG,
    TRACE;

    boolean isAtLeast(SlfLogLevel slfLogLevel) {
        return ordinal() >= slfLogLevel.ordinal();
    }
}
