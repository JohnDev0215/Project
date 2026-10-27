package com.amazon.avod.media.contentcache.internal.retry;

import com.amazon.avod.media.contentcache.internal.WritableCacheRecord;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface RetryPolicy {
    boolean canRetry(@Nonnull WritableCacheRecord writableCacheRecord);
}
