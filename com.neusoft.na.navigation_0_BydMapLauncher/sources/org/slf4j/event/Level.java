package org.slf4j.event;

import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;

/* JADX INFO: loaded from: classes4.dex */
public enum Level {
    ERROR(40, ResultCodeEnum.ERROR),
    WARN(30, "WARN"),
    INFO(20, "INFO"),
    DEBUG(10, "DEBUG"),
    TRACE(0, "TRACE");

    private int levelInt;
    private String levelStr;

    Level(int i, String str) {
        this.levelInt = i;
        this.levelStr = str;
    }

    public int toInt() {
        return this.levelInt;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.levelStr;
    }
}
