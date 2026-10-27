package com.amazon.bolthttp;

import com.amazon.bolthttp.internal.Logger;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface LogEmitter {
    String getStackTraceString(@Nonnull Throwable th);

    void log(@Nonnull Logger.Type type, @Nonnull String str, @Nonnull String str2);
}
