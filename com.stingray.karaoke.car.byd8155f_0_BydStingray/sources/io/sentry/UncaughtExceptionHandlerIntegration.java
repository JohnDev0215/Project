package io.sentry;

import h8.AbstractC1387d;
import java.io.Closeable;
import java.lang.Thread;

/* JADX INFO: loaded from: classes.dex */
public final class UncaughtExceptionHandlerIntegration implements Z, Thread.UncaughtExceptionHandler, Closeable {
    public Thread.UncaughtExceptionHandler l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public E f15968m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public F1 f15969n;
    public boolean o = false;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this == Thread.getDefaultUncaughtExceptionHandler()) {
            Thread.setDefaultUncaughtExceptionHandler(this.l);
            F1 f12 = this.f15969n;
            if (f12 != null) {
                f12.getLogger().k(EnumC1495p1.DEBUG, "UncaughtExceptionHandlerIntegration removed.", new Object[0]);
            }
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        E e10 = E.f15834a;
        if (this.o) {
            f12.getLogger().k(EnumC1495p1.ERROR, "Attempt to register a UncaughtExceptionHandlerIntegration twice.", new Object[0]);
            return;
        }
        this.o = true;
        this.f15968m = e10;
        this.f15969n = f12;
        K logger = f12.getLogger();
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        logger.k(enumC1495p1, "UncaughtExceptionHandlerIntegration enabled: %s", Boolean.valueOf(this.f15969n.isEnableUncaughtExceptionHandler()));
        if (this.f15969n.isEnableUncaughtExceptionHandler()) {
            Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (defaultUncaughtExceptionHandler != null) {
                this.f15969n.getLogger().k(enumC1495p1, "default UncaughtExceptionHandler class='" + defaultUncaughtExceptionHandler.getClass().getName() + "'", new Object[0]);
                if (defaultUncaughtExceptionHandler instanceof UncaughtExceptionHandlerIntegration) {
                    this.l = ((UncaughtExceptionHandlerIntegration) defaultUncaughtExceptionHandler).l;
                } else {
                    this.l = defaultUncaughtExceptionHandler;
                }
            }
            Thread.setDefaultUncaughtExceptionHandler(this);
            this.f15969n.getLogger().k(enumC1495p1, "UncaughtExceptionHandlerIntegration installed.", new Object[0]);
            n7.z.c("UncaughtExceptionHandler");
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        io.sentry.protocol.t tVar;
        F1 f12 = this.f15969n;
        if (f12 == null || this.f15968m == null) {
            return;
        }
        f12.getLogger().k(EnumC1495p1.INFO, "Uncaught exception received.", new Object[0]);
        try {
            e2 e2Var = new e2(this.f15969n.getFlushTimeoutMillis(), this.f15969n.getLogger());
            io.sentry.protocol.j jVar = new io.sentry.protocol.j();
            jVar.o = Boolean.FALSE;
            jVar.l = "UncaughtExceptionHandler";
            C1480k1 c1480k1 = new C1480k1(new io.sentry.exception.a(jVar, th, thread, false));
            c1480k1.f16601F = EnumC1495p1.FATAL;
            if (this.f15968m.i() == null && (tVar = c1480k1.l) != null) {
                e2Var.g(tVar);
            }
            C1523x c1523xC = AbstractC1387d.c(e2Var);
            boolean zEquals = this.f15968m.s(c1480k1, c1523xC).equals(io.sentry.protocol.t.f16774m);
            io.sentry.hints.e eVar = (io.sentry.hints.e) c1523xC.b(io.sentry.hints.e.class, "sentry:eventDropReason");
            if ((!zEquals || io.sentry.hints.e.MULTITHREADED_DEDUPLICATION.equals(eVar)) && !e2Var.d()) {
                this.f15969n.getLogger().k(EnumC1495p1.WARNING, "Timed out waiting to flush event to disk before crashing. Event: %s", c1480k1.l);
            }
        } catch (Throwable th2) {
            this.f15969n.getLogger().s(EnumC1495p1.ERROR, "Error sending uncaught exception to Sentry.", th2);
        }
        if (this.l != null) {
            this.f15969n.getLogger().k(EnumC1495p1.INFO, "Invoking inner uncaught exception handler.", new Object[0]);
            this.l.uncaughtException(thread, th);
        } else if (this.f15969n.isPrintUncaughtStackTrace()) {
            th.printStackTrace();
        }
    }
}
