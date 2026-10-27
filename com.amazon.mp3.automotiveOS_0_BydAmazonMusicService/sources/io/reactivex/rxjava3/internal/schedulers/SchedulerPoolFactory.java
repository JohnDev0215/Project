package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes7.dex */
public final class SchedulerPoolFactory {
    public static final boolean PURGE_ENABLED = getBooleanProperty(true, "rx3.purge-enabled", true, true, new SystemPropertyAccessor());

    private SchedulerPoolFactory() {
        throw new IllegalStateException("No instances!");
    }

    static boolean getBooleanProperty(boolean enabled, String key, boolean defaultNotFound, boolean defaultNotEnabled, Function<String, String> propertyAccessor) {
        if (!enabled) {
            return defaultNotEnabled;
        }
        try {
            String strApply = propertyAccessor.apply(key);
            return strApply == null ? defaultNotFound : "true".equals(strApply);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            return defaultNotFound;
        }
    }

    static final class SystemPropertyAccessor implements Function<String, String> {
        SystemPropertyAccessor() {
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public String apply(String t) {
            return System.getProperty(t);
        }
    }

    public static ScheduledExecutorService create(ThreadFactory factory) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, factory);
        scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(PURGE_ENABLED);
        return scheduledThreadPoolExecutor;
    }
}
