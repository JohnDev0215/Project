package com.amazon.music.media.playback.util;

import androidx.annotation.NonNull;
import java.util.concurrent.Callable;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class RunnableCaller<T> implements Callable<T> {
    private static final Logger logger = LoggerFactory.getLogger("RunnableCaller");
    private final T result;
    private final Runnable runnable;

    public RunnableCaller(@NonNull Runnable runnable) {
        this(runnable, null);
    }

    public RunnableCaller(@NonNull Runnable runnable, T t) {
        this.runnable = (Runnable) Validate.notNull(runnable);
        this.result = t;
    }

    public T getResult() {
        return this.result;
    }

    @Override // java.util.concurrent.Callable
    public T call() {
        try {
            this.runnable.run();
            return getResult();
        } catch (RuntimeException e) {
            logger.error(e.getClass().getSimpleName() + " in " + this.runnable.getClass().getName() + ".run()", (Throwable) e);
            throw e;
        }
    }
}
