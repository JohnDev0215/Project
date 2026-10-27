package com.amazon.avod.perf;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class PlaybackExtras {
    @Nonnull
    public static Extra newPlaybackTTFFExtra(@Nonnull String str) {
        Preconditions.checkNotNull(str, "information");
        return new Extra(str);
    }
}
