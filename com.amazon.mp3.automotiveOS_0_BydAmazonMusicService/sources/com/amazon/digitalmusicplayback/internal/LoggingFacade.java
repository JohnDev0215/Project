package com.amazon.digitalmusicplayback.internal;

import com.amazon.digitalmusicplayback.LoggingListener;
import com.amazon.digitalmusicplayback.PlayerLogLevel;
import java.util.Formatter;
import java.util.IllegalFormatException;

/* JADX INFO: loaded from: classes2.dex */
public class LoggingFacade extends LoggingSettingsListener {
    private PlayerLogLevel mLoggingLevel = PlayerLogLevel.TRACE;
    private LoggingListener mLoggingListener;

    public LoggingFacade(LoggingListener loggingListener) {
        this.mLoggingListener = loggingListener;
    }

    void logWithLevel(String str, PlayerLogLevel playerLogLevel, String str2, Object... objArr) {
        Formatter formatter = new Formatter(new StringBuilder());
        formatter.format("%s [%s] ", ThreadRunnable.currentThreadLoggingTag(), str);
        try {
            formatter.format(str2, objArr);
        } catch (IllegalFormatException unused) {
            formatter.format("%s -- could not format this log message --", str2);
            for (int i = 0; i < objArr.length; i++) {
                formatter.format(" [%d]=%s", Integer.valueOf(i), objArr[i]);
            }
        }
        this.mLoggingListener.logMessage(System.currentTimeMillis(), playerLogLevel, formatter.toString());
    }

    class Logger {
        private String mSourceFile;

        Logger(String str) {
            this.mSourceFile = str;
        }

        public void trace(String str, Object... objArr) {
            int iOrdinal = LoggingFacade.this.mLoggingLevel.ordinal();
            PlayerLogLevel playerLogLevel = PlayerLogLevel.TRACE;
            if (iOrdinal <= playerLogLevel.ordinal()) {
                LoggingFacade.this.logWithLevel(this.mSourceFile, playerLogLevel, str, objArr);
            }
        }

        public void debug(String str, Object... objArr) {
            int iOrdinal = LoggingFacade.this.mLoggingLevel.ordinal();
            PlayerLogLevel playerLogLevel = PlayerLogLevel.DEBUG;
            if (iOrdinal <= playerLogLevel.ordinal()) {
                LoggingFacade.this.logWithLevel(this.mSourceFile, playerLogLevel, str, objArr);
            }
        }

        public void info(String str, Object... objArr) {
            int iOrdinal = LoggingFacade.this.mLoggingLevel.ordinal();
            PlayerLogLevel playerLogLevel = PlayerLogLevel.INFO;
            if (iOrdinal <= playerLogLevel.ordinal()) {
                LoggingFacade.this.logWithLevel(this.mSourceFile, playerLogLevel, str, objArr);
            }
        }

        public void warning(String str, Object... objArr) {
            int iOrdinal = LoggingFacade.this.mLoggingLevel.ordinal();
            PlayerLogLevel playerLogLevel = PlayerLogLevel.WARNING;
            if (iOrdinal <= playerLogLevel.ordinal()) {
                LoggingFacade.this.logWithLevel(this.mSourceFile, playerLogLevel, str, objArr);
            }
        }

        public void error(String str, Object... objArr) {
            int iOrdinal = LoggingFacade.this.mLoggingLevel.ordinal();
            PlayerLogLevel playerLogLevel = PlayerLogLevel.ERROR;
            if (iOrdinal <= playerLogLevel.ordinal()) {
                LoggingFacade.this.logWithLevel(this.mSourceFile, playerLogLevel, str, objArr);
            }
        }

        public void fatal(String str, Object... objArr) {
            int iOrdinal = LoggingFacade.this.mLoggingLevel.ordinal();
            PlayerLogLevel playerLogLevel = PlayerLogLevel.FATAL;
            if (iOrdinal <= playerLogLevel.ordinal()) {
                LoggingFacade.this.logWithLevel(this.mSourceFile, playerLogLevel, str, objArr);
            }
        }

        public LoggingFacade getLoggingFacade() {
            return LoggingFacade.this;
        }
    }

    public Logger currentSourceFileLog() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length > 3) {
            return new Logger(stackTrace[3].getFileName());
        }
        return new Logger("java");
    }

    @Override // com.amazon.digitalmusicplayback.internal.LoggingSettingsListener
    public void loggingLevelChanged(PlayerLogLevel playerLogLevel) {
        this.mLoggingLevel = playerLogLevel;
    }
}
