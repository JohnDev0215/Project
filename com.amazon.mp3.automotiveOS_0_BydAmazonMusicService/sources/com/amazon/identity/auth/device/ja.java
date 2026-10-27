package com.amazon.identity.auth.device;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ja implements Executor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Executor f398a;

    public ja(String str) {
        this.f398a = new a6(Executors.newCachedThreadPool(new ka(str)));
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f398a.execute(runnable);
    }
}
