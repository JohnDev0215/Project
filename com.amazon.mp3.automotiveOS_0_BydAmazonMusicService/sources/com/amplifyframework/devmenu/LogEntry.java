package com.amplifyframework.devmenu;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.logging.LogLevel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class LogEntry implements Comparable<LogEntry> {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private final Date date;
    private final LogLevel logLevel;
    private final String message;
    private final String namespace;
    private final Throwable throwable;

    public LogEntry(@NonNull Date date, @Nullable String str, @Nullable String str2, @Nullable Throwable th, @NonNull LogLevel logLevel) {
        Objects.requireNonNull(date);
        this.date = date;
        Objects.requireNonNull(logLevel);
        this.logLevel = logLevel;
        this.namespace = str;
        this.message = str2;
        this.throwable = th;
    }

    public Date getDate() {
        return this.date;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getMessage() {
        return this.message;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    @Override // java.lang.Comparable
    public int compareTo(LogEntry logEntry) {
        return getDate().compareTo(logEntry.getDate());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LogEntry.class != obj.getClass()) {
            return false;
        }
        LogEntry logEntry = (LogEntry) obj;
        return this.date.equals(logEntry.getDate()) && ObjectsCompat.equals(this.namespace, logEntry.getNamespace()) && ObjectsCompat.equals(this.message, logEntry.getMessage()) && this.logLevel == logEntry.getLogLevel() && ObjectsCompat.equals(this.throwable, logEntry.getThrowable());
    }

    public int hashCode() {
        return (((((((getDate().hashCode() * 31) + (getNamespace() != null ? getNamespace().hashCode() : 0)) * 31) + (getMessage() != null ? getMessage().hashCode() : 0)) * 31) + (getThrowable() != null ? getThrowable().hashCode() : 0)) * 31) + getLogLevel().hashCode();
    }

    public String toString() {
        Locale locale = Locale.US;
        String str = new SimpleDateFormat(DATE_TIME_FORMAT, locale).format(this.date);
        Throwable th = this.throwable;
        String stackTraceString = th == null ? "" : Log.getStackTraceString(th);
        if (!stackTraceString.isEmpty() && !stackTraceString.endsWith("\n")) {
            stackTraceString = stackTraceString + "\n";
        }
        return String.format(locale, "[%s] %s %s: %s\n%s", this.logLevel.name(), str, this.namespace, this.message, stackTraceString);
    }
}
