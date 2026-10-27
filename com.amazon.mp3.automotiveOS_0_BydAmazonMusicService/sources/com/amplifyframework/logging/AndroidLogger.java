package com.amplifyframework.logging;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class AndroidLogger implements Logger {
    private final String namespace;
    private final LogLevel threshold;

    AndroidLogger(@NonNull String str, @NonNull LogLevel logLevel) {
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
        if (this.threshold.above(LogLevel.ERROR)) {
            return;
        }
        Log.e(this.namespace, String.valueOf(str));
    }

    @Override // com.amplifyframework.logging.Logger
    public void error(@Nullable String str, @Nullable Throwable th) {
        if (this.threshold.above(LogLevel.ERROR)) {
            return;
        }
        Log.e(this.namespace, str, th);
    }

    @Override // com.amplifyframework.logging.Logger
    public void warn(@Nullable String str) {
        if (this.threshold.above(LogLevel.WARN)) {
            return;
        }
        Log.w(this.namespace, String.valueOf(str));
    }

    @Override // com.amplifyframework.logging.Logger
    public void warn(@Nullable String str, @Nullable Throwable th) {
        if (this.threshold.above(LogLevel.WARN)) {
            return;
        }
        Log.w(this.namespace, str, th);
    }

    @Override // com.amplifyframework.logging.Logger
    @SuppressLint({"LogConditional"})
    public void info(@Nullable String str) {
        if (this.threshold.above(LogLevel.INFO)) {
            return;
        }
        Log.i(this.namespace, String.valueOf(str));
    }

    @Override // com.amplifyframework.logging.Logger
    @SuppressLint({"LogConditional"})
    public void debug(@Nullable String str) {
        if (this.threshold.above(LogLevel.DEBUG)) {
            return;
        }
        Log.d(this.namespace, String.valueOf(str));
    }

    @Override // com.amplifyframework.logging.Logger
    @SuppressLint({"LogConditional"})
    public void verbose(@Nullable String str) {
        if (this.threshold.above(LogLevel.VERBOSE)) {
            return;
        }
        Log.v(this.namespace, String.valueOf(str));
    }
}
