package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class i2 implements io.reactivex.rxjava3.functions.q {
    public final Observable c;
    public final int e;
    public final long k;
    public final TimeUnit l;
    public final Scheduler m;
    public final boolean n;

    public i2(Observable observable, int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.c = observable;
        this.e = i;
        this.k = j;
        this.l = timeUnit;
        this.m = scheduler;
        this.n = z;
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return this.c.replay(this.e, this.k, this.l, this.m, this.n);
    }
}
