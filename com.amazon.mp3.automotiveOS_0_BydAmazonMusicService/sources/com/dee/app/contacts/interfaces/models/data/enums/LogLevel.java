package com.dee.app.contacts.interfaces.models.data.enums;

/* JADX INFO: loaded from: classes5.dex */
public enum LogLevel {
    Error(6),
    Warning(5),
    Info(4),
    Debug(3),
    Verbose(2);

    int value;

    public int getValue() {
        return this.value;
    }

    LogLevel(int i) {
        this.value = i;
    }
}
