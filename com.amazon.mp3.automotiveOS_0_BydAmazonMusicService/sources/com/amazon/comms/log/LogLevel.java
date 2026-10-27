package com.amazon.comms.log;

/* JADX INFO: loaded from: classes2.dex */
public enum LogLevel {
    Error(6),
    Warning(5),
    Info(4),
    Debug(3),
    Verbose(2);

    int value;

    LogLevel(int i) {
        this.value = i;
    }
}
