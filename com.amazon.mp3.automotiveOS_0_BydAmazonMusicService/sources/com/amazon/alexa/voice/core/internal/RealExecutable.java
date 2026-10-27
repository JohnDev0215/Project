package com.amazon.alexa.voice.core.internal;

import com.amazon.alexa.voice.core.Action;
import com.amazon.alexa.voice.core.Cancellable;
import com.amazon.alexa.voice.core.Consumer;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public final class RealExecutable<T> implements Runnable, Cancellable {
    private volatile boolean cancelled;
    private volatile boolean executed;
    private final Callable<T> function;
    private final Action onCancel;
    private final Consumer<T> onComplete;
    private final Consumer<Throwable> onError;

    public RealExecutable(Callable<T> callable, Consumer<T> consumer, Consumer<Throwable> consumer2, Action action) {
        this.function = callable;
        this.onComplete = consumer;
        this.onError = consumer2;
        this.onCancel = action;
    }

    @Override // com.amazon.alexa.voice.core.Cancellable
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override // com.amazon.alexa.voice.core.Cancellable
    public void cancel() {
        this.cancelled = true;
    }

    @Override // java.lang.Runnable
    public void run() {
        T tCall;
        if (isCancelled()) {
            try {
                this.onCancel.call();
                return;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Throwable th = null;
        try {
            tCall = this.function.call();
            this.executed = true;
        } catch (Throwable th2) {
            this.executed = true;
            tCall = null;
            th = th2;
        }
        try {
            if (isCancelled()) {
                this.onCancel.call();
            } else if (th == null) {
                this.onComplete.accept(tCall);
            } else {
                this.onError.accept(th);
            }
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
