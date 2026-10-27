package com.amplifyframework.logging;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class BroadcastLogger implements Logger {
    private final List<Logger> delegates;

    public BroadcastLogger(List<Logger> list) {
        this.delegates = new ArrayList(list);
    }

    @Override // com.amplifyframework.logging.Logger
    @NonNull
    public LogLevel getThresholdLevel() {
        if (this.delegates.isEmpty()) {
            throw new IllegalStateException("Cannot get threshold level for BroadcastLogger with no registered loggers.");
        }
        return this.delegates.get(0).getThresholdLevel();
    }

    @Override // com.amplifyframework.logging.Logger
    @NonNull
    public String getNamespace() {
        return this.delegates.isEmpty() ? "" : this.delegates.get(0).getNamespace();
    }

    @Override // com.amplifyframework.logging.Logger
    public void error(@Nullable String str) {
        Iterator<Logger> it = this.delegates.iterator();
        while (it.hasNext()) {
            it.next().error(str);
        }
    }

    @Override // com.amplifyframework.logging.Logger
    public void error(@Nullable String str, @Nullable Throwable th) {
        Iterator<Logger> it = this.delegates.iterator();
        while (it.hasNext()) {
            it.next().error(str, th);
        }
    }

    @Override // com.amplifyframework.logging.Logger
    public void warn(@Nullable String str) {
        Iterator<Logger> it = this.delegates.iterator();
        while (it.hasNext()) {
            it.next().warn(str);
        }
    }

    @Override // com.amplifyframework.logging.Logger
    public void warn(@Nullable String str, @Nullable Throwable th) {
        Iterator<Logger> it = this.delegates.iterator();
        while (it.hasNext()) {
            it.next().warn(str, th);
        }
    }

    @Override // com.amplifyframework.logging.Logger
    @SuppressLint({"LogConditional"})
    public void info(@Nullable String str) {
        Iterator<Logger> it = this.delegates.iterator();
        while (it.hasNext()) {
            it.next().info(str);
        }
    }

    @Override // com.amplifyframework.logging.Logger
    @SuppressLint({"LogConditional"})
    public void debug(@Nullable String str) {
        Iterator<Logger> it = this.delegates.iterator();
        while (it.hasNext()) {
            it.next().debug(str);
        }
    }

    @Override // com.amplifyframework.logging.Logger
    @SuppressLint({"LogConditional"})
    public void verbose(@Nullable String str) {
        Iterator<Logger> it = this.delegates.iterator();
        while (it.hasNext()) {
            it.next().verbose(str);
        }
    }
}
