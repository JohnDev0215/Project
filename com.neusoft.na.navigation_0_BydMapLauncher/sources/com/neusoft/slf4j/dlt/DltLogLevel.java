package com.neusoft.slf4j.dlt;

/* JADX INFO: loaded from: classes.dex */
enum DltLogLevel {
    DLT_LOG_FATAL(16),
    DLT_LOG_ERROR(32),
    DLT_LOG_WARN(48),
    DLT_LOG_INFO(64),
    DLT_LOG_DEBUG(80),
    DLT_LOG_VERBOSE(96);

    private final byte extHeader;

    DltLogLevel(int i) {
        this.extHeader = (byte) i;
    }

    public byte asExtHeader() {
        return asExtHeader(true);
    }

    public byte asExtHeader(boolean z) {
        return (byte) ((z ? 1 : 0) | this.extHeader);
    }
}
