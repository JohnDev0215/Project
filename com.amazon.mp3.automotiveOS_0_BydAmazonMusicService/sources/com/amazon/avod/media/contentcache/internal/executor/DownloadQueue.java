package com.amazon.avod.media.contentcache.internal.executor;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadQueue {
    @Nullable
    Downloadable getNextItem();

    void updateItem(@Nonnull Downloadable downloadable, Downloadable.DownloadableState downloadableState, @Nullable ContentSession contentSession, @Nonnull Optional<String> optional, @Nullable TimeSpan timeSpan, long j);
}
