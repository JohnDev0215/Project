package com.amazon.alexa.voice.core.internal.util;

import com.amazon.alexa.voice.core.Action;
import com.amazon.alexa.voice.core.Consumer;
import com.amazon.alexa.voice.core.Scheduler;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public final class Functions {
    private static final Consumer<Object> EMPTY_CONSUMER = new Consumer<Object>() { // from class: com.amazon.alexa.voice.core.internal.util.Functions.1
        @Override // com.amazon.alexa.voice.core.Consumer
        public void accept(Object obj) throws Exception {
        }
    };
    private static final Action EMPTY_ACTION = new Action() { // from class: com.amazon.alexa.voice.core.internal.util.Functions.2
        @Override // com.amazon.alexa.voice.core.Action
        public void call() throws Exception {
        }
    };

    private Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Consumer<T> emptyConsumer() {
        return (Consumer<T>) EMPTY_CONSUMER;
    }

    public static Action emptyAction() {
        return EMPTY_ACTION;
    }

    public static <T> Consumer<T> scheduledConsumer(Consumer<T> consumer, Scheduler scheduler) {
        return new ScheduledConsumer(consumer, scheduler);
    }

    public static Action scheduledAction(Action action, Scheduler scheduler) {
        return new ScheduledAction(action, scheduler);
    }

    public static <T> Consumer<T> actionConsumer(Consumer<T> consumer, Action action) {
        return new ActionConsumer(consumer, action);
    }

    public static <T> Callable<T> justCallable(T t) {
        return new JustValue(t);
    }

    static class JustValue<T> implements Callable<T> {
        private final T value;

        public JustValue(T t) {
            this.value = t;
        }

        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            return this.value;
        }
    }

    static class ActionConsumer<T> implements Consumer<T> {
        private final Action action;
        private final Consumer<T> consumer;

        public ActionConsumer(Consumer<T> consumer, Action action) {
            this.consumer = consumer;
            this.action = action;
        }

        @Override // com.amazon.alexa.voice.core.Consumer
        public void accept(T t) throws Exception {
            this.consumer.accept(t);
            this.action.call();
        }
    }

    static class ScheduledAction implements Action {
        private final Action action;
        private final Scheduler scheduler;

        public ScheduledAction(Action action, Scheduler scheduler) {
            this.action = action;
            this.scheduler = scheduler;
        }

        @Override // com.amazon.alexa.voice.core.Action
        public void call() throws Exception {
            this.scheduler.schedule(new Runnable() { // from class: com.amazon.alexa.voice.core.internal.util.Functions.ScheduledAction.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ScheduledAction.this.action.call();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    static class ScheduledConsumer<T> implements Consumer<T> {
        private final Consumer<T> consumer;
        private final Scheduler scheduler;

        public ScheduledConsumer(Consumer<T> consumer, Scheduler scheduler) {
            this.consumer = consumer;
            this.scheduler = scheduler;
        }

        @Override // com.amazon.alexa.voice.core.Consumer
        public void accept(final T t) throws Exception {
            this.scheduler.schedule(new Runnable() { // from class: com.amazon.alexa.voice.core.internal.util.Functions.ScheduledConsumer.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ScheduledConsumer.this.consumer.accept(t);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
}
