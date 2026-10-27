package com.neusoft.de.nts.OneCoreTest.communication.future;

import com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast;
import com.neusoft.de.nts.OneCoreTest.utils.CommonUtil;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java9.util.concurrent.CompletableFuture;
import org.threeten.bp.Duration;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes2.dex */
public class LimitCompletableFuture<T> extends CompletableFuture<T> {
    private final Subscription subscription;

    private LimitCompletableFuture(final Builder<T> builder) {
        Observable observableFilter;
        final Class notifyClass;
        if (((Builder) builder).broadcast != null) {
            observableFilter = ((Builder) builder).broadcast.getObservable();
            notifyClass = ((Builder) builder).broadcast.getNotifyClass();
        } else {
            observableFilter = ((Builder) builder).observable;
            notifyClass = null;
        }
        observableFilter = ((Builder) builder).filter != null ? observableFilter.filter(((Builder) builder).filter) : observableFilter;
        Observable observableTake = (((Builder) builder).timeout != null ? observableFilter.timeout(((Builder) builder).timeout.toMillis(), TimeUnit.MILLISECONDS) : observableFilter).take(1);
        this.subscription = (((Builder) builder).executor != null ? observableTake.observeOn(Schedulers.from(((Builder) builder).executor)) : observableTake).subscribe(new Action1<T>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.LimitCompletableFuture.2
            @Override // rx.functions.Action1
            public void call(T t) {
                LimitCompletableFuture.this.complete(t);
            }
        }, new Action1<Throwable>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.LimitCompletableFuture.1
            @Override // rx.functions.Action1
            public void call(Throwable th) {
                Class cls = notifyClass;
                LimitCompletableFuture.this.completeExceptionally(new RuntimeException(String.format("Exception in until-future: %s%s", cls != null ? String.format("Type '%s' ", cls.getSimpleName()) : "", CommonUtil.isTimeout(th) ? String.format("Timeout exceeded: %s ", CommonUtil.humanReadableDuration(builder.timeout)) : ""), th));
            }
        });
    }

    public static <T> CompletableFuture<T> until(Observable<T> observable) {
        return new Builder().with(observable).build();
    }

    public static <T> CompletableFuture<T> until(IBroadcast<T> iBroadcast) {
        return new Builder().with(iBroadcast).build();
    }

    public static <T> CompletableFuture<T> until(Observable<T> observable, Func1<T, Boolean> func1) {
        return new Builder().with(observable).with(func1).build();
    }

    public static <T> CompletableFuture<T> until(IBroadcast<T> iBroadcast, Func1<T, Boolean> func1) {
        return new Builder().with(iBroadcast).with(func1).build();
    }

    public static <T> CompletableFuture<T> until(Observable<T> observable, Executor executor) {
        return new Builder().with(observable).with(executor).build();
    }

    public static <T> CompletableFuture<T> until(IBroadcast<T> iBroadcast, Executor executor) {
        return new Builder().with(iBroadcast).with(executor).build();
    }

    public static <T> CompletableFuture<T> until(Observable<T> observable, Duration duration) {
        return new Builder().with(observable).with(duration).build();
    }

    public static <T> CompletableFuture<T> until(IBroadcast<T> iBroadcast, Duration duration) {
        return new Builder().with(iBroadcast).with(duration).build();
    }

    public static <T> CompletableFuture<T> until(Observable<T> observable, Duration duration, Executor executor) {
        return new Builder().with(observable).with(duration).with(executor).build();
    }

    public static <T> CompletableFuture<T> until(IBroadcast<T> iBroadcast, Duration duration, Executor executor) {
        return new Builder().with(iBroadcast).with(duration).with(executor).build();
    }

    public static <T> CompletableFuture<T> until(Observable<T> observable, Func1<T, Boolean> func1, Executor executor) {
        return new Builder().with(observable).with(func1).with(executor).build();
    }

    public static <T> CompletableFuture<T> until(IBroadcast<T> iBroadcast, Func1<T, Boolean> func1, Executor executor) {
        return new Builder().with(iBroadcast).with(func1).with(executor).build();
    }

    public static <T> CompletableFuture<T> until(Observable<T> observable, Func1<T, Boolean> func1, Duration duration) {
        return new Builder().with(observable).with(func1).with(duration).build();
    }

    public static <T> CompletableFuture<T> until(IBroadcast<T> iBroadcast, Func1<T, Boolean> func1, Duration duration) {
        return new Builder().with(iBroadcast).with(func1).with(duration).build();
    }

    public static <T> CompletableFuture<T> until(Observable<T> observable, Func1<T, Boolean> func1, Duration duration, Executor executor) {
        return new Builder().with(observable).with(func1).with(duration).with(executor).build();
    }

    public static <T> CompletableFuture<T> until(IBroadcast<T> iBroadcast, Func1<T, Boolean> func1, Duration duration, Executor executor) {
        return new Builder().with(iBroadcast).with(func1).with(duration).with(executor).build();
    }

    @Override // java9.util.concurrent.CompletableFuture, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        this.subscription.unsubscribe();
        return super.cancel(z);
    }

    private static class Builder<T> {
        private IBroadcast<T> broadcast;
        private Executor executor;
        private Func1<T, Boolean> filter;
        private Observable<T> observable;
        private Duration timeout;

        private Builder() {
            this.observable = null;
            this.broadcast = null;
            this.filter = null;
            this.timeout = null;
            this.executor = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public LimitCompletableFuture<T> build() {
            return new LimitCompletableFuture<>(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder<T> with(Observable<T> observable) {
            this.observable = observable;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder<T> with(IBroadcast<T> iBroadcast) {
            this.broadcast = iBroadcast;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder<T> with(Func1<T, Boolean> func1) {
            this.filter = func1;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder<T> with(Duration duration) {
            this.timeout = duration;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Builder<T> with(Executor executor) {
            this.executor = executor;
            return this;
        }
    }
}
