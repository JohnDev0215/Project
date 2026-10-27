package com.amplifyframework.devmenu;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.logging.LogLevel;
import com.amplifyframework.logging.Logger;
import com.amplifyframework.util.Immutable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class PersistentLogger implements Logger {
    private static final int MAX_NUM_LOGS = 500;
    private final List<LogEntry> logs;
    private final String namespace;

    PersistentLogger(@NonNull String str) {
        Objects.requireNonNull(str);
        this.namespace = str;
        this.logs = new LinkedList();
    }

    @Override // com.amplifyframework.logging.Logger
    @NonNull
    public LogLevel getThresholdLevel() {
        return LogLevel.VERBOSE;
    }

    @Override // com.amplifyframework.logging.Logger
    @NonNull
    public String getNamespace() {
        return this.namespace;
    }

    @Override // com.amplifyframework.logging.Logger
    public void error(@Nullable String str) {
        addToLogs(str, null, LogLevel.ERROR);
    }

    @Override // com.amplifyframework.logging.Logger
    public void error(@Nullable String str, @Nullable Throwable th) {
        addToLogs(str, th, LogLevel.ERROR);
    }

    @Override // com.amplifyframework.logging.Logger
    public void warn(@Nullable String str) {
        addToLogs(str, null, LogLevel.WARN);
    }

    @Override // com.amplifyframework.logging.Logger
    public void warn(@Nullable String str, @Nullable Throwable th) {
        addToLogs(str, th, LogLevel.WARN);
    }

    @Override // com.amplifyframework.logging.Logger
    @SuppressLint({"LogConditional"})
    public void info(@Nullable String str) {
        addToLogs(str, null, LogLevel.INFO);
    }

    @Override // com.amplifyframework.logging.Logger
    @SuppressLint({"LogConditional"})
    public void debug(@Nullable String str) {
        addToLogs(str, null, LogLevel.DEBUG);
    }

    @Override // com.amplifyframework.logging.Logger
    @SuppressLint({"LogConditional"})
    public void verbose(@Nullable String str) {
        addToLogs(str, null, LogLevel.VERBOSE);
    }

    public List<LogEntry> getLogs() {
        return Immutable.of(this.logs);
    }

    private void addToLogs(String str, Throwable th, LogLevel logLevel) {
        if (this.logs.size() == 500) {
            this.logs.remove(0);
        }
        this.logs.add(new LogEntry(new Date(), this.namespace, str, th, logLevel));
    }
}
