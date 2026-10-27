package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: classes7.dex */
public final class AtomicThrowable extends AtomicReference<Throwable> {
    public boolean tryAddThrowable(Throwable t) {
        return ExceptionHelper.addThrowable(this, t);
    }

    public boolean tryAddThrowableOrReport(Throwable t) {
        if (tryAddThrowable(t)) {
            return true;
        }
        RxJavaPlugins.onError(t);
        return false;
    }

    public Throwable terminate() {
        return ExceptionHelper.terminate(this);
    }

    public void tryTerminateAndReport() {
        Throwable thTerminate = terminate();
        if (thTerminate == null || thTerminate == ExceptionHelper.TERMINATED) {
            return;
        }
        RxJavaPlugins.onError(thTerminate);
    }

    public void tryTerminateConsumer(Subscriber<?> consumer) {
        Throwable thTerminate = terminate();
        if (thTerminate == null) {
            consumer.onComplete();
        } else if (thTerminate != ExceptionHelper.TERMINATED) {
            consumer.onError(thTerminate);
        }
    }

    public void tryTerminateConsumer(Observer<?> consumer) {
        Throwable thTerminate = terminate();
        if (thTerminate == null) {
            consumer.onComplete();
        } else if (thTerminate != ExceptionHelper.TERMINATED) {
            consumer.onError(thTerminate);
        }
    }

    public void tryTerminateConsumer(CompletableObserver consumer) {
        Throwable thTerminate = terminate();
        if (thTerminate == null) {
            consumer.onComplete();
        } else if (thTerminate != ExceptionHelper.TERMINATED) {
            consumer.onError(thTerminate);
        }
    }
}
