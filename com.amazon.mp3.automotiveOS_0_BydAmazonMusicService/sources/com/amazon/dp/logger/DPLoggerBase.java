package com.amazon.dp.logger;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DPLoggerBase {

    protected enum DPLevel {
        FATAL,
        ERROR,
        WARN,
        INFO,
        DEBUG,
        TRACE,
        VERBOSE
    }

    protected abstract boolean isEnabled(DPLevel dPLevel);

    protected abstract void log(DPLevel dPLevel, DPFormattedMessage dPFormattedMessage);

    public boolean isDebugEnabled() {
        return isEnabled(DPLevel.DEBUG);
    }

    public void fatal(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.FATAL, str, str2, objArr);
    }

    public void error(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.ERROR, str, str2, objArr);
    }

    public void warn(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.WARN, str, str2, objArr);
    }

    public void info(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.INFO, str, str2, objArr);
    }

    public void debug(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.DEBUG, str, str2, objArr);
    }

    public void verbose(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.VERBOSE, str, str2, objArr);
    }

    private void logIfEnabled(DPLevel dPLevel, String str, String str2, Object... objArr) {
        logIfEnabled(dPLevel, new DPFormattedMessage(str, str2, objArr));
    }

    private void logIfEnabled(DPLevel dPLevel, DPFormattedMessage dPFormattedMessage) {
        if (isEnabled(dPLevel)) {
            log(dPLevel, dPFormattedMessage);
        }
    }
}
