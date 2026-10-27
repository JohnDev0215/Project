package com.google.common.util.concurrent;

import java.util.concurrent.locks.LockSupport;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
final class OverflowAvoidingLockSupport {
    private OverflowAvoidingLockSupport() {
    }

    static void parkNanos(@CheckForNull Object obj, long j) {
        LockSupport.parkNanos(obj, Math.min(j, 2147483647999999999L));
    }
}
