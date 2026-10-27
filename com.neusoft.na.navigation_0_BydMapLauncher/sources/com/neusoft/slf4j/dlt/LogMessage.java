package com.neusoft.slf4j.dlt;

/* JADX INFO: loaded from: classes.dex */
class LogMessage {
    final String message;
    final SlfLogLevel slfLogLevel;
    final Throwable throwable;

    LogMessage(SlfLogLevel slfLogLevel, String str, Throwable th) {
        this.slfLogLevel = slfLogLevel;
        this.message = str;
        this.throwable = th;
    }
}
