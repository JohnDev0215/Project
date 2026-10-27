package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class o2 implements io.reactivex.rxjava3.functions.q {
    public final Observable c;
    public final long e;
    public final TimeUnit k;
    public final Scheduler l;
    public final boolean m;

    public o2(Observable observable, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.c = observable;
        this.e = j;
        this.k = timeUnit;
        this.l = scheduler;
        this.m = z;
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return this.c.replay(this.e, this.k, this.l, this.m);
    }
}
