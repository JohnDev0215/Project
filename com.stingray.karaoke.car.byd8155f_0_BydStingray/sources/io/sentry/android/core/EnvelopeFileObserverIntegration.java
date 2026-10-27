package io.sentry.android.core;

import io.sentry.D0;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public abstract class EnvelopeFileObserverIntegration implements io.sentry.Z, Closeable {
    public I l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public io.sentry.K f16048m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f16049n = false;
    public final Object o = new Object();

    public static final class OutboxEnvelopeFileObserverIntegration extends EnvelopeFileObserverIntegration {
        private OutboxEnvelopeFileObserverIntegration() {
        }

        public /* synthetic */ OutboxEnvelopeFileObserverIntegration(int i7) {
            this();
        }
    }

    public final void b(F1 f12, String str) {
        I i7 = new I(str, new D0(io.sentry.E.f15834a, f12.getEnvelopeReader(), f12.getSerializer(), f12.getLogger(), f12.getFlushTimeoutMillis(), f12.getMaxQueueSize()), f12.getLogger(), f12.getFlushTimeoutMillis());
        this.l = i7;
        try {
            i7.startWatching();
            f12.getLogger().k(EnumC1495p1.DEBUG, "EnvelopeFileObserverIntegration installed.", new Object[0]);
        } catch (Throwable th) {
            f12.getLogger().s(EnumC1495p1.ERROR, "Failed to initialize EnvelopeFileObserverIntegration.", th);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.o) {
            this.f16049n = true;
        }
        I i7 = this.l;
        if (i7 != null) {
            i7.stopWatching();
            io.sentry.K k10 = this.f16048m;
            if (k10 != null) {
                k10.k(EnumC1495p1.DEBUG, "EnvelopeFileObserverIntegration removed.", new Object[0]);
            }
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        this.f16048m = f12.getLogger();
        String outboxPath = f12.getOutboxPath();
        if (outboxPath == null) {
            this.f16048m.k(EnumC1495p1.WARNING, "Null given as a path to EnvelopeFileObserverIntegration. Nothing will be registered.", new Object[0]);
            return;
        }
        this.f16048m.k(EnumC1495p1.DEBUG, "Registering EnvelopeFileObserverIntegration for path: %s", outboxPath);
        try {
            f12.getExecutorService().submit(new T(this, f12, outboxPath, 2));
        } catch (Throwable th) {
            this.f16048m.s(EnumC1495p1.DEBUG, "Failed to start EnvelopeFileObserverIntegration on executor thread.", th);
        }
    }
}
