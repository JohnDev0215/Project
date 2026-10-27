package io.sentry;

import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class ShutdownHookIntegration implements Z, Closeable {
    public final Runtime l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Thread f15953m;

    public ShutdownHookIntegration() {
        Runtime runtime = Runtime.getRuntime();
        h6.e.q(runtime, "Runtime is required");
        this.l = runtime;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f15953m != null) {
            try {
                this.l.removeShutdownHook(this.f15953m);
            } catch (IllegalStateException e10) {
                String message = e10.getMessage();
                if (message == null || !(message.equals("Shutdown in progress") || message.equals("VM already shutting down"))) {
                    throw e10;
                }
            }
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        if (!f12.isEnableShutdownHook()) {
            f12.getLogger().k(EnumC1495p1.INFO, "enableShutdownHook is disabled.", new Object[0]);
            return;
        }
        this.f15953m = new Thread(new S0(f12, 3));
        try {
            this.l.addShutdownHook(this.f15953m);
            f12.getLogger().k(EnumC1495p1.DEBUG, "ShutdownHookIntegration installed.", new Object[0]);
            n7.z.c("ShutdownHook");
        } catch (IllegalStateException e10) {
            String message = e10.getMessage();
            if (message == null || !(message.equals("Shutdown in progress") || message.equals("VM already shutting down"))) {
                throw e10;
            }
        }
    }
}
