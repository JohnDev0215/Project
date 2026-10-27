package org.apache.shiro.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public class SubjectAwareExecutorService extends SubjectAwareExecutor implements ExecutorService {
    private ExecutorService targetExecutorService;

    public SubjectAwareExecutorService() {
    }

    public SubjectAwareExecutorService(ExecutorService executorService) {
        setTargetExecutorService(executorService);
    }

    public ExecutorService getTargetExecutorService() {
        return this.targetExecutorService;
    }

    public void setTargetExecutorService(ExecutorService executorService) {
        super.setTargetExecutor(executorService);
        this.targetExecutorService = executorService;
    }

    @Override // org.apache.shiro.concurrent.SubjectAwareExecutor
    public void setTargetExecutor(Executor executor) {
        if (!(executor instanceof ExecutorService)) {
            throw new IllegalArgumentException("The " + getClass().getName() + " implementation only accepts " + ExecutorService.class.getName() + " target instances.");
        }
        super.setTargetExecutor(executor);
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.targetExecutorService.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return this.targetExecutorService.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.targetExecutorService.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.targetExecutorService.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.targetExecutorService.awaitTermination(j, timeUnit);
    }

    protected <T> Callable<T> associateWithSubject(Callable<T> callable) {
        return getSubject().associateWith(callable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return this.targetExecutorService.submit(associateWithSubject(callable));
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.targetExecutorService.submit(associateWithSubject(runnable), t);
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return this.targetExecutorService.submit(associateWithSubject(runnable));
    }

    protected <T> Collection<Callable<T>> associateWithSubject(Collection<? extends Callable<T>> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<? extends Callable<T>> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(associateWithSubject(it.next()));
        }
        return arrayList;
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.targetExecutorService.invokeAll(associateWithSubject(collection));
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.targetExecutorService.invokeAll(associateWithSubject(collection), j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
        return (T) this.targetExecutorService.invokeAny(associateWithSubject(collection));
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return (T) this.targetExecutorService.invokeAny(associateWithSubject(collection), j, timeUnit);
    }
}
