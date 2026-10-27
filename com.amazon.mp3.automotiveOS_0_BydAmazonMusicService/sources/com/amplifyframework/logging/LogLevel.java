package com.amplifyframework.logging;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public enum LogLevel {
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    NONE;

    public boolean above(@NonNull LogLevel logLevel) {
        return ordinal() > logLevel.ordinal();
    }
}
