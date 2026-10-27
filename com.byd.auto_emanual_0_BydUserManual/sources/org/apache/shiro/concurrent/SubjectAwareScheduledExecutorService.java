package org.apache.shiro.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class SubjectAwareScheduledExecutorService extends SubjectAwareExecutorService implements ScheduledExecutorService {
    private ScheduledExecutorService targetScheduledExecutorService;

    public SubjectAwareScheduledExecutorService() {
    }

    public SubjectAwareScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        setTargetScheduledExecutorService(scheduledExecutorService);
    }

    public ScheduledExecutorService getTargetScheduledExecutorService() {
        return this.targetScheduledExecutorService;
    }

    public void setTargetScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        super.setTargetExecutorService(scheduledExecutorService);
        this.targetScheduledExecutorService = scheduledExecutorService;
    }

    @Override // org.apache.shiro.concurrent.SubjectAwareExecutorService, org.apache.shiro.concurrent.SubjectAwareExecutor
    public void setTargetExecutor(Executor executor) {
        if (!(executor instanceof ScheduledExecutorService)) {
            throw new IllegalArgumentException("The " + getClass().getName() + " implementation only accepts " + ScheduledExecutorService.class.getName() + " target instances.");
        }
        super.setTargetExecutorService((ScheduledExecutorService) executor);
    }

    @Override // org.apache.shiro.concurrent.SubjectAwareExecutorService
    public void setTargetExecutorService(ExecutorService executorService) {
        if (!(executorService instanceof ScheduledExecutorService)) {
            throw new IllegalArgumentException("The " + getClass().getName() + " implementation only accepts " + ScheduledExecutorService.class.getName() + " target instances.");
        }
        super.setTargetExecutorService(executorService);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.targetScheduledExecutorService.schedule(associateWithSubject(runnable), j, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        return this.targetScheduledExecutorService.schedule(associateWithSubject(callable), j, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.targetScheduledExecutorService.scheduleAtFixedRate(associateWithSubject(runnable), j, j2, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.targetScheduledExecutorService.scheduleWithFixedDelay(associateWithSubject(runnable), j, j2, timeUnit);
    }
}
