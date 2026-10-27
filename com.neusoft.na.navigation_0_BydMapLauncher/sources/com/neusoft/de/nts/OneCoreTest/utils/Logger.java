package com.neusoft.de.nts.OneCoreTest.utils;

import java9.util.concurrent.CompletionException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes2.dex */
public class Logger {
    private final org.slf4j.Logger logger;

    public Logger(Class<?> cls) {
        this.logger = LoggerFactory.getLogger(cls);
    }

    public Logger(String str) {
        this.logger = LoggerFactory.getLogger(str);
    }

    public static Logger getLogger(Class<?> cls) {
        return new Logger(cls);
    }

    public static Logger getLogger(String str) {
        return new Logger(str);
    }

    private static Throwable unwrapCompletionException(Throwable th) {
        return (!(th instanceof CompletionException) || th.getCause() == null) ? th : th.getCause();
    }

    private static String logMessageWithException(String str, Throwable th) {
        Object[] objArr = new Object[3];
        objArr[0] = th.getClass().getSimpleName();
        objArr[1] = str;
        objArr[2] = th.getMessage() != null ? th.getMessage() : "";
        return StringUtils.joinWith(StringUtils.SPACE, objArr);
    }

    public void debug(String str) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(str);
        }
    }

    public void debug(String str, Object... objArr) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(String.format("" + str, objArr));
        }
    }

    public void debug(Throwable th, String str, Object... objArr) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(logMessageWithException(String.format("" + str, objArr), unwrapCompletionException(th)));
        }
    }

    public void debug(Throwable th) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(logMessageWithException("", unwrapCompletionException(th)));
        }
    }

    public void info(String str) {
        if (this.logger.isInfoEnabled()) {
            this.logger.info(str);
        }
    }

    public void info(String str, Object... objArr) {
        if (this.logger.isInfoEnabled()) {
            this.logger.info(String.format("" + str, objArr));
        }
    }

    public void info(Throwable th, String str, Object... objArr) {
        if (this.logger.isInfoEnabled()) {
            this.logger.info(logMessageWithException(String.format("" + str, objArr), unwrapCompletionException(th)));
        }
    }

    public void info(Throwable th) {
        if (this.logger.isInfoEnabled()) {
            this.logger.info(logMessageWithException("", unwrapCompletionException(th)));
        }
    }

    public void warn(String str) {
        if (this.logger.isWarnEnabled()) {
            this.logger.warn(str);
        }
    }

    public void warn(String str, Object... objArr) {
        if (this.logger.isWarnEnabled()) {
            this.logger.warn(String.format("" + str, objArr));
        }
    }

    public void warn(Throwable th, String str, Object... objArr) {
        if (this.logger.isWarnEnabled()) {
            this.logger.warn(logMessageWithException(String.format("" + str, objArr), unwrapCompletionException(th)));
        }
    }

    public void warn(Throwable th) {
        if (this.logger.isWarnEnabled()) {
            this.logger.warn(logMessageWithException("", unwrapCompletionException(th)));
        }
    }

    public void error(String str) {
        if (this.logger.isErrorEnabled()) {
            this.logger.error(str);
        }
    }

    public void error(String str, Object... objArr) {
        if (this.logger.isErrorEnabled()) {
            this.logger.error(String.format("" + str, objArr));
        }
    }

    public void error(Throwable th, String str, Object... objArr) {
        if (this.logger.isErrorEnabled()) {
            this.logger.error(logMessageWithException(String.format("" + str, objArr), unwrapCompletionException(th)));
        }
    }

    public void error(Throwable th) {
        if (this.logger.isErrorEnabled()) {
            this.logger.error(logMessageWithException("", unwrapCompletionException(th)));
        }
    }
}
