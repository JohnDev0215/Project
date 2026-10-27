package com.amazon.avod.metrics.aloysius;

import com.amazon.avod.media.events.MediaEventReporter;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AloysiusDownloadEventReporter implements MediaEventReporter {
    public static final int MINIMUM_UPDATE_OFFSET = 25;

    public enum Action {
        Queue,
        Start,
        Update,
        Stop,
        Complete,
        Error,
        Delete,
        Disable
    }

    public abstract void reportDownloadEvent(@Nonnull Action action, int i, @Nonnull String str);
}
