package com.amazon.avod.content;

import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.time.DurationKt;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class LiveWindowDuration {
    final AtomicLong mRestrictedDurationMillisAtomic = new AtomicLong(DurationKt.MAX_MILLIS);

    public long getRestrictedDurationMillis() {
        return this.mRestrictedDurationMillisAtomic.get();
    }
}
