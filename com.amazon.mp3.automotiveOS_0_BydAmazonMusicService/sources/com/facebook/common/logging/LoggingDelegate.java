package com.facebook.common.logging;

/* JADX INFO: loaded from: classes5.dex */
public interface LoggingDelegate {
    void d(String tag, String msg);

    void d(String tag, String msg, Throwable tr);

    void e(String tag, String msg);

    void e(String tag, String msg, Throwable tr);

    int getMinimumLoggingLevel();

    void i(String tag, String msg);

    boolean isLoggable(int level);

    void setMinimumLoggingLevel(int level);

    void v(String tag, String msg);

    void w(String tag, String msg);

    void w(String tag, String msg, Throwable tr);

    void wtf(String tag, String msg);

    void wtf(String tag, String msg, Throwable tr);
}
