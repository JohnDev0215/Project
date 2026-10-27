package io.reactivex.rxjava3.schedulers;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.functions.g;
import io.reactivex.rxjava3.internal.schedulers.x;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    public static final Scheduler a = RxJavaPlugins.a(new g(4));
    public static final Scheduler b = RxJavaPlugins.a(new g(1));
    public static final Scheduler c = RxJavaPlugins.a(new g(2));
    public static final x d = x.c;

    static {
        RxJavaPlugins.a(new g(3));
    }
}
