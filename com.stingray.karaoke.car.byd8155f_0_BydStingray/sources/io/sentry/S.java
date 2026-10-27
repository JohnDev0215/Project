package io.sentry;

import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public interface S {
    boolean f();

    void l(long j3);

    Future r(Runnable runnable, long j3);

    Future submit(Runnable runnable);
}
