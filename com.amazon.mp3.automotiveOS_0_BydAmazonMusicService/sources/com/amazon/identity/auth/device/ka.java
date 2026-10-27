package com.amazon.identity.auth.device;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ka implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f408a;
    public final AtomicInteger b = new AtomicInteger(1);

    public ka(String str) {
        this.f408a = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, String.format("%s#%d", this.f408a, Integer.valueOf(this.b.getAndIncrement())));
    }
}
