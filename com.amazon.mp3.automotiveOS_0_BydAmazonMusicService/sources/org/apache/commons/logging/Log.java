package org.apache.commons.logging;

/* JADX INFO: loaded from: classes7.dex */
public interface Log {
    void debug(Object obj);

    void debug(Object obj, Throwable th);

    void error(Object obj);

    void info(Object obj);

    boolean isDebugEnabled();

    boolean isErrorEnabled();

    boolean isInfoEnabled();

    boolean isWarnEnabled();

    void warn(Object obj);

    void warn(Object obj, Throwable th);
}
