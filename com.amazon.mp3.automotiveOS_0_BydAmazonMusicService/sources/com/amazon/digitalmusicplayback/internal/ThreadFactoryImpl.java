package com.amazon.digitalmusicplayback.internal;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class ThreadFactoryImpl extends PlatformThreadFactory {
    private LoggingFacade mLoggingFacade;

    public ThreadFactoryImpl(LoggingFacade loggingFacade) {
        this.mLoggingFacade = loggingFacade;
    }

    @Override // com.amazon.digitalmusicplayback.internal.PlatformThreadFactory
    public PlatformThread createThread(@NonNull String str, @NonNull ThreadRunnable threadRunnable) {
        return new ThreadImpl(str, threadRunnable, this.mLoggingFacade);
    }
}
