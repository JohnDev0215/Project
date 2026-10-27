package com.amazon.avod.perf;

import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public final class TraceKey {
    private final String mName;
    private final Object[] mNameArgs;
    private final long mThreadId;
    private final String mThreadName;

    TraceKey(@Nonnull Thread thread, @Nonnull String str, @Nullable Object... objArr) {
        this(str, thread.getId(), thread.getName(), objArr);
    }

    TraceKey(@Nonnull String str, long j, @Nonnull String str2, @Nullable Object... objArr) {
        this.mName = str;
        this.mNameArgs = objArr;
        this.mThreadId = j;
        this.mThreadName = str2;
    }

    @Nonnull
    public String getName() {
        Object[] objArr = this.mNameArgs;
        if (objArr != null && objArr.length != 0) {
            return String.format(Locale.US, this.mName, objArr);
        }
        return this.mName;
    }

    @Nonnull
    public long getThreadId() {
        return this.mThreadId;
    }

    @Nonnull
    public String getThreadName() {
        return this.mThreadName;
    }

    public String toString() {
        return String.format(Locale.US, "TraceKey[name=%s, thread=%s %s]", this.mName, Long.valueOf(this.mThreadId), this.mThreadName);
    }
}
