package com.sun.istack.logging;

import java.util.StringTokenizer;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes3.dex */
public class Logger {
    private static final Level METHOD_CALL_LEVEL_VALUE = Level.FINEST;
    private static final String ROOT_WS_PACKAGE = "com.sun.xml.ws.";
    private static final String WS_LOGGING_SUBSYSTEM_NAME_ROOT = "com.sun.metro";
    private final String componentClassName;
    private final java.util.logging.Logger logger;

    protected Logger(String str, String str2) {
        this.componentClassName = "[" + str2 + "] ";
        this.logger = java.util.logging.Logger.getLogger(str);
    }

    public static Logger getLogger(Class<?> cls) {
        return new Logger(getSystemLoggerName(cls), cls.getName());
    }

    public static Logger getLogger(String str, Class<?> cls) {
        return new Logger(str, cls.getName());
    }

    static final String getSystemLoggerName(Class<?> cls) {
        StringBuilder sb = new StringBuilder(cls.getPackage().getName());
        int iLastIndexOf = sb.lastIndexOf(ROOT_WS_PACKAGE);
        if (iLastIndexOf > -1) {
            sb.replace(0, iLastIndexOf + 15, "");
            StringTokenizer stringTokenizer = new StringTokenizer(sb.toString(), ".");
            sb = new StringBuilder(WS_LOGGING_SUBSYSTEM_NAME_ROOT).append(".");
            if (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                if ("api".equals(strNextToken)) {
                    strNextToken = stringTokenizer.nextToken();
                }
                sb.append(strNextToken);
            }
        }
        return sb.toString();
    }

    public void log(Level level, String str) {
        if (this.logger.isLoggable(level)) {
            this.logger.logp(level, this.componentClassName, getCallerMethodName(), str);
        }
    }

    public void log(Level level, String str, Object obj) {
        if (this.logger.isLoggable(level)) {
            this.logger.logp(level, this.componentClassName, getCallerMethodName(), str, obj);
        }
    }

    public void log(Level level, String str, Object[] objArr) {
        if (this.logger.isLoggable(level)) {
            this.logger.logp(level, this.componentClassName, getCallerMethodName(), str, objArr);
        }
    }

    public void log(Level level, String str, Throwable th) {
        if (this.logger.isLoggable(level)) {
            this.logger.logp(level, this.componentClassName, getCallerMethodName(), str, th);
        }
    }

    public void finest(String str) {
        if (this.logger.isLoggable(Level.FINEST)) {
            this.logger.logp(Level.FINEST, this.componentClassName, getCallerMethodName(), str);
        }
    }

    public void finest(String str, Object[] objArr) {
        if (this.logger.isLoggable(Level.FINEST)) {
            this.logger.logp(Level.FINEST, this.componentClassName, getCallerMethodName(), str, objArr);
        }
    }

    public void finest(String str, Throwable th) {
        if (this.logger.isLoggable(Level.FINEST)) {
            this.logger.logp(Level.FINEST, this.componentClassName, getCallerMethodName(), str, th);
        }
    }

    public void finer(String str) {
        if (this.logger.isLoggable(Level.FINER)) {
            this.logger.logp(Level.FINER, this.componentClassName, getCallerMethodName(), str);
        }
    }

    public void finer(String str, Object[] objArr) {
        if (this.logger.isLoggable(Level.FINER)) {
            this.logger.logp(Level.FINER, this.componentClassName, getCallerMethodName(), str, objArr);
        }
    }

    public void finer(String str, Throwable th) {
        if (this.logger.isLoggable(Level.FINER)) {
            this.logger.logp(Level.FINER, this.componentClassName, getCallerMethodName(), str, th);
        }
    }

    public void fine(String str) {
        if (this.logger.isLoggable(Level.FINE)) {
            this.logger.logp(Level.FINE, this.componentClassName, getCallerMethodName(), str);
        }
    }

    public void fine(String str, Throwable th) {
        if (this.logger.isLoggable(Level.FINE)) {
            this.logger.logp(Level.FINE, this.componentClassName, getCallerMethodName(), str, th);
        }
    }

    public void info(String str) {
        if (this.logger.isLoggable(Level.INFO)) {
            this.logger.logp(Level.INFO, this.componentClassName, getCallerMethodName(), str);
        }
    }

    public void info(String str, Object[] objArr) {
        if (this.logger.isLoggable(Level.INFO)) {
            this.logger.logp(Level.INFO, this.componentClassName, getCallerMethodName(), str, objArr);
        }
    }

    public void info(String str, Throwable th) {
        if (this.logger.isLoggable(Level.INFO)) {
            this.logger.logp(Level.INFO, this.componentClassName, getCallerMethodName(), str, th);
        }
    }

    public void config(String str) {
        if (this.logger.isLoggable(Level.CONFIG)) {
            this.logger.logp(Level.CONFIG, this.componentClassName, getCallerMethodName(), str);
        }
    }

    public void config(String str, Object[] objArr) {
        if (this.logger.isLoggable(Level.CONFIG)) {
            this.logger.logp(Level.CONFIG, this.componentClassName, getCallerMethodName(), str, objArr);
        }
    }

    public void config(String str, Throwable th) {
        if (this.logger.isLoggable(Level.CONFIG)) {
            this.logger.logp(Level.CONFIG, this.componentClassName, getCallerMethodName(), str, th);
        }
    }

    public void warning(String str) {
        if (this.logger.isLoggable(Level.WARNING)) {
            this.logger.logp(Level.WARNING, this.componentClassName, getCallerMethodName(), str);
        }
    }

    public void warning(String str, Object[] objArr) {
        if (this.logger.isLoggable(Level.WARNING)) {
            this.logger.logp(Level.WARNING, this.componentClassName, getCallerMethodName(), str, objArr);
        }
    }

    public void warning(String str, Throwable th) {
        if (this.logger.isLoggable(Level.WARNING)) {
            this.logger.logp(Level.WARNING, this.componentClassName, getCallerMethodName(), str, th);
        }
    }

    public void severe(String str) {
        if (this.logger.isLoggable(Level.SEVERE)) {
            this.logger.logp(Level.SEVERE, this.componentClassName, getCallerMethodName(), str);
        }
    }

    public void severe(String str, Object[] objArr) {
        if (this.logger.isLoggable(Level.SEVERE)) {
            this.logger.logp(Level.SEVERE, this.componentClassName, getCallerMethodName(), str, objArr);
        }
    }

    public void severe(String str, Throwable th) {
        if (this.logger.isLoggable(Level.SEVERE)) {
            this.logger.logp(Level.SEVERE, this.componentClassName, getCallerMethodName(), str, th);
        }
    }

    public boolean isMethodCallLoggable() {
        return this.logger.isLoggable(METHOD_CALL_LEVEL_VALUE);
    }

    public boolean isLoggable(Level level) {
        return this.logger.isLoggable(level);
    }

    public void setLevel(Level level) {
        this.logger.setLevel(level);
    }

    public void entering() {
        if (this.logger.isLoggable(METHOD_CALL_LEVEL_VALUE)) {
            this.logger.entering(this.componentClassName, getCallerMethodName());
        }
    }

    public void entering(Object... objArr) {
        if (this.logger.isLoggable(METHOD_CALL_LEVEL_VALUE)) {
            this.logger.entering(this.componentClassName, getCallerMethodName(), objArr);
        }
    }

    public void exiting() {
        if (this.logger.isLoggable(METHOD_CALL_LEVEL_VALUE)) {
            this.logger.exiting(this.componentClassName, getCallerMethodName());
        }
    }

    public void exiting(Object obj) {
        if (this.logger.isLoggable(METHOD_CALL_LEVEL_VALUE)) {
            this.logger.exiting(this.componentClassName, getCallerMethodName(), obj);
        }
    }

    public <T extends Throwable> T logSevereException(T t, Throwable th) {
        if (this.logger.isLoggable(Level.SEVERE)) {
            if (th == null) {
                this.logger.logp(Level.SEVERE, this.componentClassName, getCallerMethodName(), t.getMessage());
            } else {
                t.initCause(th);
                this.logger.logp(Level.SEVERE, this.componentClassName, getCallerMethodName(), t.getMessage(), th);
            }
        }
        return t;
    }

    public <T extends Throwable> T logSevereException(T t, boolean z) {
        if (this.logger.isLoggable(Level.SEVERE)) {
            if (z && t.getCause() != null) {
                this.logger.logp(Level.SEVERE, this.componentClassName, getCallerMethodName(), t.getMessage(), t.getCause());
            } else {
                this.logger.logp(Level.SEVERE, this.componentClassName, getCallerMethodName(), t.getMessage());
            }
        }
        return t;
    }

    public <T extends Throwable> T logSevereException(T t) {
        if (this.logger.isLoggable(Level.SEVERE)) {
            if (t.getCause() == null) {
                this.logger.logp(Level.SEVERE, this.componentClassName, getCallerMethodName(), t.getMessage());
            } else {
                this.logger.logp(Level.SEVERE, this.componentClassName, getCallerMethodName(), t.getMessage(), t.getCause());
            }
        }
        return t;
    }

    public <T extends Throwable> T logException(T t, Throwable th, Level level) {
        if (this.logger.isLoggable(level)) {
            if (th == null) {
                this.logger.logp(level, this.componentClassName, getCallerMethodName(), t.getMessage());
            } else {
                t.initCause(th);
                this.logger.logp(level, this.componentClassName, getCallerMethodName(), t.getMessage(), th);
            }
        }
        return t;
    }

    public <T extends Throwable> T logException(T t, boolean z, Level level) {
        if (this.logger.isLoggable(level)) {
            if (z && t.getCause() != null) {
                this.logger.logp(level, this.componentClassName, getCallerMethodName(), t.getMessage(), t.getCause());
            } else {
                this.logger.logp(level, this.componentClassName, getCallerMethodName(), t.getMessage());
            }
        }
        return t;
    }

    public <T extends Throwable> T logException(T t, Level level) {
        if (this.logger.isLoggable(level)) {
            if (t.getCause() == null) {
                this.logger.logp(level, this.componentClassName, getCallerMethodName(), t.getMessage());
            } else {
                this.logger.logp(level, this.componentClassName, getCallerMethodName(), t.getMessage(), t.getCause());
            }
        }
        return t;
    }

    private static String getCallerMethodName() {
        return getStackMethodName(5);
    }

    private static String getStackMethodName(int i) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return stackTrace.length > i + 1 ? stackTrace[i].getMethodName() : "UNKNOWN METHOD";
    }
}
