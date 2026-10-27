package io.reactivex.rxjava3.android.schedulers;

import io.reactivex.rxjava3.core.Scheduler;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static final Scheduler a;

    static {
        try {
            Scheduler scheduler = (Scheduler) new a().call();
            if (scheduler == null) {
                throw new NullPointerException("Scheduler Callable returned null");
            }
            a = scheduler;
        } catch (Throwable th) {
            throw io.reactivex.rxjava3.internal.util.c.f(th);
        }
    }

    public static Scheduler a() {
        Scheduler scheduler = a;
        if (scheduler != null) {
            return scheduler;
        }
        throw new NullPointerException("scheduler == null");
    }
}
