package com.amazon.mp3.util;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
public final class UniqueCodeUtil {
    private static AtomicInteger sNextUniqueCode = new AtomicInteger(10000);

    public static int nextUniqueCode() {
        return sNextUniqueCode.getAndAdd(1);
    }
}
