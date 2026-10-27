package com.amazon.alexa.voice.core;

import com.amazon.alexa.voice.core.internal.RealExecutable;
import com.amazon.alexa.voice.core.internal.util.Functions;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.schedulers.Schedulers;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public final class Executable<T> {
    private Scheduler consumeScheduler;
    private Scheduler executeScheduler;
    private final Function<Task<T>, T> function;
    private Action onCancel;

    public Executable(Function<Task<T>, T> function) {
        Preconditions.notNull(function, "function == null");
        this.function = function;
        this.executeScheduler = Schedulers.immediate();
        this.consumeScheduler = Schedulers.immediate();
        this.onCancel = Functions.emptyAction();
    }

    public Executable(ActionValue<Task<T>> actionValue) {
        this(new ActionValueFunction(actionValue));
        Preconditions.notNull(actionValue, "action == null");
    }

    public Executable(Action action) {
        this(new ActionFunction(action));
        Preconditions.notNull(action, "action == null");
    }

    public Executable<T> consumeOn(Scheduler scheduler) {
        Preconditions.notNull(scheduler, "scheduler == null");
        this.consumeScheduler = scheduler;
        return this;
    }

    public Executable<T> executeOn(Scheduler scheduler) {
        Preconditions.notNull(scheduler, "scheduler == null");
        this.executeScheduler = scheduler;
        return this;
    }

    public Executable<T> onCancel(Action action) {
        Preconditions.notNull(action, "onCancel == null");
        this.onCancel = action;
        return this;
    }

    public Cancellable execute(Consumer<T> consumer, Consumer<Throwable> consumer2) {
        Preconditions.notNull(consumer, "onComplete == null");
        Preconditions.notNull(consumer2, "onError == null");
        final CompositeCancellable compositeCancellable = new CompositeCancellable();
        Action action = new Action() { // from class: com.amazon.alexa.voice.core.Executable.1
            @Override // com.amazon.alexa.voice.core.Action
            public void call() throws Exception {
                compositeCancellable.cancel();
            }
        };
        final Consumer consumerActionConsumer = Functions.actionConsumer(Functions.scheduledConsumer(consumer, this.consumeScheduler), action);
        final Consumer consumerActionConsumer2 = Functions.actionConsumer(Functions.scheduledConsumer(consumer2, this.consumeScheduler), action);
        RealExecutable realExecutable = new RealExecutable(new Callable<T>() { // from class: com.amazon.alexa.voice.core.Executable.2
            @Override // java.util.concurrent.Callable
            public T call() throws Exception {
                return (T) Executable.this.function.apply(new Task<T>() { // from class: com.amazon.alexa.voice.core.Executable.2.1
                    @Override // com.amazon.alexa.voice.core.Task
                    public void add(Cancellable cancellable) {
                        Preconditions.notNull(cancellable, "cancellable == null");
                        compositeCancellable.add(cancellable);
                    }
                });
            }
        }, consumerActionConsumer, consumerActionConsumer2, Functions.scheduledAction(this.onCancel, this.consumeScheduler));
        compositeCancellable.add(realExecutable);
        this.executeScheduler.schedule(realExecutable);
        return compositeCancellable;
    }

    public Cancellable execute(Consumer<Throwable> consumer) {
        Preconditions.notNull(consumer, "onError == null");
        return execute(Functions.emptyConsumer(), consumer);
    }

    static class ActionFunction<T> implements Function<Task<T>, T> {
        private final Action action;

        public ActionFunction(Action action) {
            this.action = action;
        }

        @Override // com.amazon.alexa.voice.core.Function
        public T apply(Task<T> task) throws Exception {
            this.action.call();
            return null;
        }
    }

    static class ActionValueFunction<T> implements Function<Task<T>, T> {
        private final ActionValue<Task<T>> action;

        public ActionValueFunction(ActionValue<Task<T>> actionValue) {
            this.action = actionValue;
        }

        @Override // com.amazon.alexa.voice.core.Function
        public T apply(Task<T> task) throws Exception {
            this.action.call(task);
            return null;
        }
    }
}
