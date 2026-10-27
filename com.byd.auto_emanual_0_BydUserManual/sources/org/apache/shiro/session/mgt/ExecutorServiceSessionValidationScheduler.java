package org.apache.shiro.session.mgt;

import java.lang.Thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class ExecutorServiceSessionValidationScheduler implements SessionValidationScheduler, Runnable {
    private static final Logger log = LoggerFactory.getLogger(ExecutorServiceSessionValidationScheduler.class);
    private ScheduledExecutorService service;
    ValidatingSessionManager sessionManager;
    private long interval = AbstractValidatingSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;
    private boolean enabled = false;
    private String threadNamePrefix = "SessionValidationThread-";

    public ExecutorServiceSessionValidationScheduler() {
    }

    public ExecutorServiceSessionValidationScheduler(ValidatingSessionManager validatingSessionManager) {
        this.sessionManager = validatingSessionManager;
    }

    public ValidatingSessionManager getSessionManager() {
        return this.sessionManager;
    }

    public void setSessionManager(ValidatingSessionManager validatingSessionManager) {
        this.sessionManager = validatingSessionManager;
    }

    public long getInterval() {
        return this.interval;
    }

    public void setInterval(long j) {
        this.interval = j;
    }

    @Override // org.apache.shiro.session.mgt.SessionValidationScheduler
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setThreadNamePrefix(String str) {
        this.threadNamePrefix = str;
    }

    public String getThreadNamePrefix() {
        return this.threadNamePrefix;
    }

    @Override // org.apache.shiro.session.mgt.SessionValidationScheduler
    public void enableSessionValidation() {
        if (this.interval > 0) {
            ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() { // from class: org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler.1
                private final AtomicInteger count = new AtomicInteger(1);

                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable);
                    thread.setDaemon(true);
                    thread.setName(ExecutorServiceSessionValidationScheduler.this.threadNamePrefix + this.count.getAndIncrement());
                    return thread;
                }
            });
            this.service = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
            long j = this.interval;
            scheduledExecutorServiceNewSingleThreadScheduledExecutor.scheduleAtFixedRate(this, j, j, TimeUnit.MILLISECONDS);
        }
        this.enabled = true;
    }

    @Override // java.lang.Runnable
    public void run() {
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            logger.debug("Executing session validation...");
        }
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: org.apache.shiro.session.mgt.-$$Lambda$ExecutorServiceSessionValidationScheduler$LUyZCrrZNbmRVQlT4Cn5PY427js
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                this.f$0.lambda$run$0$ExecutorServiceSessionValidationScheduler(thread, th);
            }
        });
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            this.sessionManager.validateSessions();
        } catch (RuntimeException e) {
            log.error("Error while validating the session", e);
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        Logger logger2 = log;
        if (logger2.isDebugEnabled()) {
            logger2.debug("Session validation completed successfully in " + (jCurrentTimeMillis2 - jCurrentTimeMillis) + " milliseconds.");
        }
    }

    public /* synthetic */ void lambda$run$0$ExecutorServiceSessionValidationScheduler(Thread thread, Throwable th) {
        log.error("Error while validating the session, the thread will be stopped and session validation disabled", th);
        disableSessionValidation();
    }

    @Override // org.apache.shiro.session.mgt.SessionValidationScheduler
    public void disableSessionValidation() {
        ScheduledExecutorService scheduledExecutorService = this.service;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
        this.enabled = false;
    }
}
