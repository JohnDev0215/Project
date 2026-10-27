package com.amazon.avod.content.readytowatch;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ReadyToWatch {
    @Nonnull
    ReadyToWatchResult canResumeFromTimeInNanos(long j);

    @Nonnull
    ReadyToWatchResult canStartWatchingFromTimeInNanos(long j);
}
