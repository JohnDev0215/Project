package com.amplifyframework.logging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface Logger {
    void debug(@Nullable String str);

    void error(@Nullable String str);

    void error(@Nullable String str, @Nullable Throwable th);

    @NonNull
    String getNamespace();

    @NonNull
    LogLevel getThresholdLevel();

    void info(@Nullable String str);

    void verbose(@Nullable String str);

    void warn(@Nullable String str);

    void warn(@Nullable String str, @Nullable Throwable th);
}
