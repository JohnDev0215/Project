package com.amazon.avod.media.framework.event;

import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.util.Throwables2;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SingleThreadedEventDispatcher extends EventDispatcher {
    private final ExecutorService mExecutor;

    public SingleThreadedEventDispatcher(@Nonnull String str) {
        this(ExecutorBuilder.newBuilderFor(SingleThreadedEventDispatcher.class, str).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build());
    }

    private SingleThreadedEventDispatcher(@Nonnull ExecutorService executorService) {
        super(new AsyncEventBus(executorService, new RuntimeExceptionPropagator()));
        this.mExecutor = executorService;
    }

    @Override // com.amazon.avod.media.framework.event.EventDispatcher
    public void shutdown() {
        super.shutdown();
        this.mExecutor.shutdown();
    }

    private static class RuntimeExceptionPropagator implements SubscriberExceptionHandler {
        private RuntimeExceptionPropagator() {
        }

        @Override // com.google.common.eventbus.SubscriberExceptionHandler
        public void handleException(Throwable th, SubscriberExceptionContext subscriberExceptionContext) {
            Throwable cause = th.getCause();
            if (cause != null) {
                th = cause;
            }
            Throwables2.propagateIfWeakMode(th);
        }
    }
}
