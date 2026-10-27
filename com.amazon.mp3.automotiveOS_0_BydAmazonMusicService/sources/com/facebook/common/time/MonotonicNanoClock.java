package com.facebook.common.time;

import com.facebook.common.internal.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public interface MonotonicNanoClock {
    @DoNotStrip
    long nowNanos();
}
