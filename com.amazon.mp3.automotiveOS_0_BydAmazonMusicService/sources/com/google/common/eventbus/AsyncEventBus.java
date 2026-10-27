package com.google.common.eventbus;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes6.dex */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super("default", executor, Dispatcher.legacyAsync(), subscriberExceptionHandler);
    }
}
