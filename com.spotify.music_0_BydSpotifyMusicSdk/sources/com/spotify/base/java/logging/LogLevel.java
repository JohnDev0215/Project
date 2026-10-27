package com.spotify.base.java.logging;

import com.spotify.voiceassistants.playermodels.ContextResponseKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class LogLevel {
    public static final LogLevel c;
    public static final LogLevel e;
    public static final LogLevel k;
    public static final LogLevel l;
    public static final LogLevel m;
    public static final LogLevel n;
    public static final LogLevel o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ LogLevel[] f47p;

    static {
        LogLevel logLevel = new LogLevel("VERBOSE", 0);
        c = logLevel;
        LogLevel logLevel2 = new LogLevel("DEBUG", 1);
        e = logLevel2;
        LogLevel logLevel3 = new LogLevel("INFO", 2);
        k = logLevel3;
        LogLevel logLevel4 = new LogLevel("WARNING", 3);
        l = logLevel4;
        LogLevel logLevel5 = new LogLevel(ContextResponseKt.RESULT_ERROR, 4);
        m = logLevel5;
        LogLevel logLevel6 = new LogLevel("BREADCRUMB", 5);
        n = logLevel6;
        LogLevel logLevel7 = new LogLevel("YELL", 6);
        o = logLevel7;
        f47p = new LogLevel[]{logLevel, logLevel2, logLevel3, logLevel4, logLevel5, logLevel6, logLevel7};
    }

    public static LogLevel valueOf(String str) {
        return (LogLevel) Enum.valueOf(LogLevel.class, str);
    }

    public static LogLevel[] values() {
        return (LogLevel[]) f47p.clone();
    }
}
