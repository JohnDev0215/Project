package com.amplifyframework.logging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class JavaLogger implements Logger {
    private final String namespace;
    private final LogLevel threshold;

    JavaLogger(@NonNull String str, @NonNull LogLevel logLevel) {
        Objects.requireNonNull(logLevel);
        this.threshold = logLevel;
        Objects.requireNonNull(str);
        this.namespace = str;
    }

    @Override // com.amplifyframework.logging.Logger
    @NonNull
    public LogLevel getThresholdLevel() {
        return this.threshold;
    }

    @Override // com.amplifyframework.logging.Logger
    @NonNull
    public String getNamespace() {
        return this.namespace;
    }

    @Override // com.amplifyframework.logging.Logger
    public void error(@Nullable String str) {
        LogLevel logLevel = this.threshold;
        LogLevel logLevel2 = LogLevel.ERROR;
        if (logLevel.above(logLevel2)) {
            return;
        }
        log(logLevel2, str);
    }

    @Override // com.amplifyframework.logging.Logger
    public void error(@Nullable String str, @Nullable Throwable th) {
        LogLevel logLevel = this.threshold;
        LogLevel logLevel2 = LogLevel.ERROR;
        if (logLevel.above(logLevel2)) {
            return;
        }
        log(logLevel2, str, th);
    }

    @Override // com.amplifyframework.logging.Logger
    public void warn(@Nullable String str) {
        LogLevel logLevel = this.threshold;
        LogLevel logLevel2 = LogLevel.WARN;
        if (logLevel.above(logLevel2)) {
            return;
        }
        log(logLevel2, str);
    }

    @Override // com.amplifyframework.logging.Logger
    public void warn(@Nullable String str, @Nullable Throwable th) {
        LogLevel logLevel = this.threshold;
        LogLevel logLevel2 = LogLevel.WARN;
        if (logLevel.above(logLevel2)) {
            return;
        }
        log(logLevel2, str, th);
    }

    @Override // com.amplifyframework.logging.Logger
    public void info(@Nullable String str) {
        LogLevel logLevel = this.threshold;
        LogLevel logLevel2 = LogLevel.INFO;
        if (logLevel.above(logLevel2)) {
            return;
        }
        log(logLevel2, str);
    }

    @Override // com.amplifyframework.logging.Logger
    public void debug(@Nullable String str) {
        LogLevel logLevel = this.threshold;
        LogLevel logLevel2 = LogLevel.DEBUG;
        if (logLevel.above(logLevel2)) {
            return;
        }
        log(logLevel2, str);
    }

    @Override // com.amplifyframework.logging.Logger
    public void verbose(@Nullable String str) {
        LogLevel logLevel = this.threshold;
        LogLevel logLevel2 = LogLevel.VERBOSE;
        if (logLevel.above(logLevel2)) {
            return;
        }
        log(logLevel2, str);
    }

    private void log(@NonNull LogLevel logLevel, @Nullable String str) {
        log(logLevel, str, null);
    }

    private void log(@NonNull LogLevel logLevel, @Nullable String str, @Nullable Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(logLevel);
        sb.append("/");
        sb.append(this.namespace);
        sb.append(": ");
        sb.append(str);
        if (th != null) {
            sb.append("\n");
            sb.append(th);
        }
        System.out.println(sb.toString());
    }
}
