package com.amazon.avod.media.playback.reporting.aloysius;

import com.amazon.avod.media.TimeSpan;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AloysiusInteractionReporterInterface {
    void reportNextEvent();

    void reportSeekEvent(@Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2);
}
