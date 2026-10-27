package com.amazon.identity.auth.device;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class a6 implements Executor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ExecutorService f172a;

    public a6(ExecutorService executorService) {
        this.f172a = executorService;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (xa.a()) {
            runnable.run();
        } else {
            this.f172a.execute(runnable);
        }
    }
}
