package io.reactivex.internal.util;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class AtomicThrowable extends AtomicReference<Throwable> {
    private static final long serialVersionUID = 3949248817947090603L;

    public boolean addThrowable(Throwable th) {
        return ExceptionHelper.a(this, th);
    }

    public boolean isTerminated() {
        return get() == ExceptionHelper.f5914a;
    }

    public Throwable terminate() {
        return ExceptionHelper.b(this);
    }
}
